package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    /*
     *
     * @Test ----------------> Informa que o metodo será executado na ação de TESTE
     * @Ignore --------------> Ignora a execução do TESTE
     * @LocalServerPort -----> Pega o numero da porta virtual criada pelo servidor no momento da execução
     * @Before --------------> Executar o metodo setUp() por primeiro antes dos testes
     *
     */

    @LocalServerPort
    private int port;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    private static final Long idInexistente = 10L;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = "/cozinhas/listar";
        RestAssured.port = port;

        databaseCleaner.clearTables();

        prepararDadosIniciaisDeTeste();
    }

    @Test
    public void deveRetornarStatus200_QuandoBuscarTodasCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornar1Cozinha_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(1));
    }

    @Test
    public void deveRetornar1CozinhaComNome_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(1))
                .body("nome", Matchers.hasItems("PADRAO"));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        File json = new File("src/test/resources/json/cadastro-cozinha.json");

        RestAssured.given()
                .basePath("/cozinhas")
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetoarnarStatus200_QuandoFiltrarUmaCozinhaPorId() {
        RestAssured.given()
                .basePath("/cozinhas")
                .pathParam("id", 1L)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetoarnarStatus404_QuandoFiltrarUmaCozinhaPorIdInexistente() {
        RestAssured.given()
                .basePath("/cozinhas")
                .pathParam("id", idInexistente)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDadosIniciaisDeTeste() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("PADRAO");
        cozinha.setStatus(true);

        cozinhaRepository.save(cozinha);
    }
}
