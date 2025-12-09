package com.algaworks.algafoodapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = "/cozinhas/listar";
        RestAssured.port = port;
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
    public void deveRetornar9Cozinhas_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(9))
                .body("nome", Matchers.hasItems("PADRAO", "Italiana"));
    }
}
