package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    public List<Restaurante> filtrarTodas() {
        try {
            return restauranteRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar todos os restaurantes");
        }
    }

    public List<Restaurante> filtrarPorNome(String nome) {
        try {
            return restauranteRepository.findByNomeContaining(nome);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar restaurantes por nome");
        }
    }

    public Restaurante filtrarPorID(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Restaurante do código %d não encontrado", id)));
    }

    public Restaurante inserirOuAtualizar(Restaurante restaurante) {
        if (restaurante == null) {
            throw new RuntimeException("Erro inesperado ao inserir restaurante");
        }

        if (restaurante.getNome() == null | restaurante.getNome().isEmpty()) {
            throw new EntidadeNaoEncontradaException("O nome do restaurante não pode ser vazia ou nula");
        }

        var cozinha = cozinhaService.filtrarPorId(restaurante.getCozinha().getId());

        List<FormaPagamento> formasPagamentoCompletas = restaurante.getFormasPagamento()
                .stream()
                .map(fp ->
                        formaPagamentoService.filtrarPorID(fp.getId())).collect(Collectors.toList());

        restaurante.setCozinha(cozinha);
        restaurante.setFormasPagamento(formasPagamentoCompletas);

        return restauranteRepository.save(restaurante);
    }
}
