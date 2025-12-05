package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.ProdutoNaoEncontradaException;
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
            throw new NegocioException("Erro inesperado ao buscar todos os produtos");
        }
    }

    public List<Produto> filtrarPorNome(String nome) {
        try {
            return produtoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar produtos por nome");
        }
    }

    public Produto filtrarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradaException(id));
    }

    public Produto inserirOuAtualizar(Produto produto) {
        return produtoRepository.save(produto);
    }
}
