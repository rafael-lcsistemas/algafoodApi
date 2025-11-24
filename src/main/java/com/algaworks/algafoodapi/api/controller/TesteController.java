package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.entity.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> listarTodasPorNome(String nome) {
        if(nome.isEmpty()) {
            throw new RuntimeException("O nome não pode ser vazio.") ;
        }

        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> listarUnicaPorNome(String nome) {
        if(nome.isEmpty()) {
            throw new RuntimeException("O nome não pode ser vazio.") ;
        }

        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> listarPorTaxaFrete(BigDecimal taxaInicio, BigDecimal taxaFim) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicio, taxaFim);
    }

    @GetMapping("/restaurantes/por-nome-e-cozinha-nome")
    public List<Restaurante> listarPorNomeECozinhaNome(String nome, Long cozinhaId) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> listarPorNomeETaxaFrete(String nome, BigDecimal taxaFreteinicial, BigDecimal taxaFretefinal) {
        return restauranteRepository.find(nome, taxaFreteinicial, taxaFretefinal);
    }
}
