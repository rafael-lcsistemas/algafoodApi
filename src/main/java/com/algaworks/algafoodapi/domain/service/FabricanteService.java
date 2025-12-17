package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.FabricanteNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Fabricante;
import com.algaworks.algafoodapi.domain.repository.FabricanteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRespository fabricanteRespository;

    public List<Fabricante> listarTodos() {
        try {
            return fabricanteRespository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao listar os fabricantes");
        }
    }

    public List<Fabricante> listarPorNome(String nome) {
        try {
            return fabricanteRespository.findFabricanteByNome(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao listar os fabricantes");
        }
    }

    public Fabricante buscarPorId(UUID id) {
        return fabricanteRespository.findById(id).orElseThrow(() ->
                new FabricanteNaoEncontradaException(id));
    }

    public Fabricante inserirOuAtualizar(Fabricante fabricante) {
        if (fabricante.getCodInterno() == null) {
            fabricante.setCodInterno(getLastCodInterno() + 1);
        }

        return fabricanteRespository.save(fabricante);
    }

    private Integer getLastCodInterno() {
        return fabricanteRespository.getLastCodInterno();
    }
}
