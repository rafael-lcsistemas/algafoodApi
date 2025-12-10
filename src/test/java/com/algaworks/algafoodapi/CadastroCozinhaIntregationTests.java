package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.exceptions.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.service.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntregationTests {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    public void deveCadastrar_CozinhaQuandoTodosOsDadosCorretos() {
        // Cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Italiana");
        cozinha.setStatus(true);

        // Ação
        cozinha = cozinhaService.inserirOuAtualizar(cozinha);

        // Validação
        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_CozinhaSemNome() {
        // Cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);
        cozinha.setStatus(null);

        // Ação
        cozinha = cozinhaService.inserirOuAtualizar(cozinha);

        // Validação
        assertThat(cozinha).isNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_CozinhaSemStatus() {
        // Cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Indiana");
        cozinha.setStatus(null);

        // Ação
        cozinha = cozinhaService.inserirOuAtualizar(cozinha);

        // Validação
        assertThat(cozinha).isNull();
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        cozinhaService.remove(2L);
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
        cozinhaService.remove(100L);
    }
}
