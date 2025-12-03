package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.RestauranteNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new NegocioException("Erro ao buscar todos os restaurantes");
        }
    }

    public List<Restaurante> filtrarPorNome(String nome) {
        try {
            return restauranteRepository.findByNomeContaining(nome);
        } catch (RuntimeException e) {
            throw new NegocioException("Erro ao buscar restaurantes por nome");
        }
    }

    public Restaurante filtrarPorID(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() ->
                new RestauranteNaoEncontradaException(id));
    }

    public Restaurante inserirOuAtualizar(Restaurante restaurante) {

        if (restaurante.getNome() == null || restaurante.getNome().isEmpty()) {
            throw new NegocioException("O nome do restaurante esta inválido. Por favor, verifique e tente novamente.");
        }

        if (restaurante.getAtivo() == null) {
            throw new NegocioException("O status do restaurante esta inválido. Por favor, verifique e tente novamente.");
        }


        try {
            var cozinha = cozinhaService.filtrarPorId(restaurante.getCozinha().getId());

            List<FormaPagamento> formasPagamentoCompletas = restaurante.getFormasPagamento()
                    .stream()
                    .map(fp ->
                            formaPagamentoService.filtrarPorID(fp.getId())).collect(Collectors.toList());

            restaurante.setCozinha(cozinha);
            restaurante.setFormasPagamento(formasPagamentoCompletas);
            return restauranteRepository.save(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        } catch (Exception e) {
            throw new NegocioException(
                    "Erro inesperado ao salvar restaurante. Por favor, verifique os dados e tente novamente.", e);
        }
    }
}
