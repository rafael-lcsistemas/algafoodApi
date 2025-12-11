package com.algaworks.algafoodapi.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PrecoValidacaoValidator implements ConstraintValidator<PrecoValidacao, BigDecimal> {

    @Override
    public void initialize(PrecoValidacao constraintAnnotation) {
    }

    @Override
    public boolean isValid(BigDecimal valor, ConstraintValidatorContext context) {

        if (valor == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Preço não pode ser nulo")
                    .addConstraintViolation();
            return false;
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Preço deve ser maior que zero")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
