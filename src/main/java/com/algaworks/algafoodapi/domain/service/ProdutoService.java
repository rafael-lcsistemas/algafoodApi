package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        try {
            return produtoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar todos os produtos");
        }
    }

    public List<Produto> filtrarPorNome(String nome) {
        try {
            return produtoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar produtos por nome");
        }
    }

    public Produto filtrarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Produto do código %d não encontado", id)));
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
