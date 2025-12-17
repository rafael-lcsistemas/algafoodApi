package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.CategoriaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Categoria;
import com.algaworks.algafoodapi.domain.repository.CategoriaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRespository categoriaRespository;

    public List<Categoria> listarTodas() {
        try {
            return categoriaRespository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao listar as categorias");
        }
    }

    public List<Categoria> listarPorNome(String nome) {
        try {
            return categoriaRespository.findCategoriaByNome(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao listar as categorias");
        }
    }

    public Categoria buscarPorId(UUID id) {
        return categoriaRespository.findById(id).orElseThrow(() ->
                new CategoriaNaoEncontradaException(id));
    }

    public Categoria inserirOuAtualizar(Categoria categoria) {
        if(categoria.getCodInterno() == null) {
            categoria.setCodInterno(getLastCodInterno() + 1);
        }

        return categoriaRespository.save(categoria);
    }

    private Integer getLastCodInterno() {
        return categoriaRespository.getLastCodInterno();
    }
}
