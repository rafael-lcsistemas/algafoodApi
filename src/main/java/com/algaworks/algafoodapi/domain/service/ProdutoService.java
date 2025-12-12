package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.api.model.input.ProdutoInput;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.ProdutoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.RestauranteNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

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
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradaException(id));
    }

    @Transactional
    public Produto inserirOuAtualizar(Produto produto, ProdutoInput input) {
        try {
            var restaurante = restauranteService.filtrarPorID(input.getIdrestaurante());

            produto.setRestaurante(restaurante);

            return produtoRepository.save(produto);
        } catch (RestauranteNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
