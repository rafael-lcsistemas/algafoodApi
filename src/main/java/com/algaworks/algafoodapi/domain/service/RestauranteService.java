package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.api.model.input.RestauranteInput;
import com.algaworks.algafoodapi.domain.exceptions.*;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;
    @Autowired
    private ModelMapper modelMapper;

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
    public Restaurante inserirOuAtualizar(Restaurante restaurante, RestauranteInput restauranteInput) {

        try {
            var cozinha = cozinhaService.filtrarPorId(restauranteInput.getIdcozinha());
            restaurante.setCozinha(cozinha);

            var cidade = cidadeService.filtrarPorId(restauranteInput.getEndereco().getIdcidade());
            restaurante.getEndereco().setCidade(cidade);

            return restauranteRepository.save(restaurante);
        } catch (FormaPagamentoNaoEncontradaException | CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void desassociarFormaPagamentoToRestaurante(Long idRestaurante, Long idFormaPagamento) {
        Restaurante restaurante = filtrarPorID(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoService.filtrarPorID(idFormaPagamento);

        restaurante.desassociarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void asassociarFormaPagamentoToRestaurante(Long idRestaurante, Long idFormaPagamento) {
        Restaurante restaurante = filtrarPorID(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoService.filtrarPorID(idFormaPagamento);

        restaurante.associarFormaPagamento(formaPagamento);
    }
}
