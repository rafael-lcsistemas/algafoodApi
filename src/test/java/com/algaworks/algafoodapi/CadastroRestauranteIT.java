package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private static final Long idInexistente = 10L;
    private static final Long idExistente = 1L;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = "/restaurantes/listar";
        RestAssured.port = port;

        databaseCleaner.clearTables();

        prepararDadosIniciarDeTeste();
    }

    @Test
    public void deveRetornarStatus200_QuandoBuscarUmRestauranteExistente() {
        RestAssured.given()
                .basePath("/restaurantes")
                .accept(ContentType.JSON)
                .when()
                .get("/{id}", idExistente)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus404_QuandoBuscarUmRestauranteInexistente() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/{id}", idInexistente)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarUmRestauranteComTodosOsDadosCorretos() {

        File json = new File("src/test/resources/json/cadastro-restaurante.json");

        RestAssured.given()
                .basePath("/restaurantes")
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarUmRestauranteComTaxaZeroESemDescricaoDeFreteGratis() {

        File json = new File("src/test/resources/json/cadastro-restaurante-sem-taxa.json");

        RestAssured.given()
                .basePath("/restaurantes")
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarUmRestauranteComUmaCozinhaInexistente() {

        File json = new File("src/test/resources/json/cadastro-restaurante-cozinha-inexistente.json");

        RestAssured.given()
                .basePath("/restaurantes")
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarUmRestauranteComUmaFormaPagamentoInexistente() {

        File json = new File("src/test/resources/json/cadastro-restaurante-forma-pagamento-inexistente.json");

        RestAssured.given()
                .basePath("/restaurantes")
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    private void prepararDadosIniciarDeTeste() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Padaria");
        cozinha.setAtivo(true);

        cozinha = cozinhaRepository.save(cozinha);

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setNome("Sem pagamento");
        formaPagamento.setAtivo(true);

        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Aurora do Pará - Frete Grátis");
        restaurante.setTaxaFrete(BigDecimal.ZERO);
        restaurante.setStatus(true);
        restaurante.setCozinha(cozinha);
        restaurante.getFormasPagamento().add(formaPagamento);

        restauranteRepository.save(restaurante);
    }
}
