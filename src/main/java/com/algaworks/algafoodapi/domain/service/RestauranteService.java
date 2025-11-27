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

    public List<Restaurante> findAll() {
        try {
            return restauranteRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar restaurantes");
        }
    }

    public Optional<Restaurante> findById(Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);

        if (restaurante.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante do código %d não encontrado", id));
        }

        return restaurante;
    }

    public Restaurante insert(Restaurante restaurante) {
        if (restaurante == null) {
            throw new RuntimeException("Erro inesperado ao inserir restaurante");
        }

        if (restaurante.getNome() == null | restaurante.getNome().isEmpty()) {
            throw new EntidadeNaoEncontradaException("O nome do restaurante não pode ser vazia ou nula");
        }

        Optional<Cozinha> cozinha = cozinhaService.filtrarPorId(restaurante.getCozinha().getId());

        if (cozinha.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha do código %d não encontrada",
                    restaurante.getCozinha().getId()));
        }

        List<FormaPagamento> formasPagamentoCompletas = restaurante.getFormasPagamento()
                .stream()
                .map(fp -> formaPagamentoService.filtrarPorID(fp.getId())
                        .orElseThrow(() -> new EntidadeNaoEncontradaException(
                                String.format("Forma de pagamento do código %d não encontrada", fp.getId())
                        ))
                ).collect(Collectors.toList());



        restaurante.setCozinha(cozinha.get());
        restaurante.setFormasPagamento(formasPagamentoCompletas);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante update(Restaurante restaurante) {
        Optional<Cozinha> cozinha = cozinhaService.filtrarPorId(restaurante.getCozinha().getId());
        List<FormaPagamento> formasPagamento = restaurante.getFormasPagamento();

        if (restaurante.getNome() == null | restaurante.getNome().isEmpty()) {
            throw new EntidadeIntegridadeException("O nome do restaurante não pode ser vazio ou nulo");
        }

        if (restaurante.getCozinha().getId() == null) {
            throw new EntidadeIntegridadeException("Código da cozinha é obrigatoria");
        } else if (cozinha.isEmpty()) {
            throw new EntidadeIntegridadeException(String.format("Cozinha de código %d não escontrada",
                    restaurante.getCozinha().getId()));
        }

        formasPagamento.forEach(formaPagamento -> {
            var pagamento = formaPagamentoService.filtrarPorID(formaPagamento.getId());

            if (pagamento.get().getId() == null) {
                throw new EntidadeIntegridadeException("Código da forma de pagamento é obrigatoria");
            } else if (pagamento.isEmpty()) {
                throw new EntidadeIntegridadeException(String.format("Forma de pagamento de código %d não escontrada",
                        pagamento.get().getId()));
            }
        });

        restaurante.setCozinha(cozinha.get());
        restaurante.setFormasPagamento(formasPagamento);

        return restauranteRepository.save(restaurante);
    }
}
