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
import java.util.UUID;

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

    public Produto filtrarPorId(UUID id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradaException(id));
    }

    @Transactional
    public Produto inserirOuAtualizar(Produto produto, ProdutoInput input) {
        try {
            if(produto.getCodInterno() == null) {
                produto.setCodInterno(getLastCodInterno() + 1);
            }

            var restaurante = restauranteService.filtrarPorID(input.getIdrestaurante());

            produto.setRestaurante(restaurante);

            return produtoRepository.save(produto);
        } catch (RestauranteNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    private Integer getLastCodInterno() {
        return produtoRepository.getLastCodInterno();
    }
}
