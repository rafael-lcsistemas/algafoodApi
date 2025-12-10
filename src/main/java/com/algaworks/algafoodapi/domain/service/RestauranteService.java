package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.RestauranteNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    public Restaurante inserirOuAtualizar(Restaurante restaurante) {

        try {
            var cozinha = cozinhaService.filtrarPorId(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);

            List<FormaPagamento> formasPagamentoCompletas = restaurante.getFormasPagamento()
                    .stream()
                    .map(fp ->
                            formaPagamentoService.filtrarPorID(fp.getId())).collect(Collectors.toList());

            restaurante.setFormasPagamento(formasPagamentoCompletas);
        } catch (FormaPagamentoNaoEncontradaException | CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

        return restauranteRepository.save(restaurante);
    }
}
