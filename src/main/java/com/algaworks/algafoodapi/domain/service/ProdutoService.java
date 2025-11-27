package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> filtrarPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    public Optional<Produto> filtrarPorId(Long id) {
        var produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Produto do código %d não encontado", id));
        }

        return produto;
    }

    public Produto inserirOuAtualizar(Produto produto) {

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new EntidadeIntegridadeException("Nome do produto inválido. Por favor, verifique e tente novamente.");
        }

        if (produto.getPreco() == null || produto.getPreco().equals(BigDecimal.ZERO)) {
            throw new EntidadeIntegridadeException("Preço do produto inválido. Por favor, verifique e tente novamente.");
        }

        return produtoRepository.save(produto);
    }
}
