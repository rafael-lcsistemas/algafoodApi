package com.algaworks.algafoodapi.api.exceptionhandler;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ControllerAdvice
public class ApiExceptionHanlder extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof UnrecognizedPropertyException) {
            return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);
        } else if (rootCause instanceof IgnoredPropertyException) {
            return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.REQUISICAO_INVALIDA;
        String detail = "Corpo da requisição está inválido. Por favor, verifique os erros de sintaxes e tente novamente.";

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format(
                "Não conseguimos localizar o recurso %s que foi passado. Por favor, verifique e tente novamente.",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = ex.getMessage();

        System.out.println(detail);

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .detail(ex.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .status(status.value())
                    .title(body.toString())
                    .detail(ex.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        return new ResponseEntity<>(body, headers, status);
    }

    private ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.REQUISICAO_INVALIDA;
        String detail = String.format("A propriedade '%s' recebeu um valor '%s' do tipo %s incompatível. " +
                        "Por favor, efetue a correção com um valor do tipo '%s' compatível.",
                path, ex.getValue(), ex.getValue().getClass().getSimpleName(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyException(
            UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.REQUISICAO_INVALIDA;
        String detail = String.format("A propriedade '%s' não existe na representação dessa entidade.", path);

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleIgnoredPropertyException(
            IgnoredPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.REQUISICAO_INVALIDA;
        String detail = String.format("A propriedade '%s' não poder ser alterada.", path);

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timestamp(LocalDateTime.now());
    }
}
