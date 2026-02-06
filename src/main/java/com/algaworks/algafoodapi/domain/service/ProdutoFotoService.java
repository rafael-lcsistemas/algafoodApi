package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.entity.ProdutoFoto;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoFotoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoFoto salvarFoto(ProdutoFoto foto) {
        var idProduto = foto.getProduto().getId();

        Optional<ProdutoFoto> fotoExistente = produtoRepository.findProdutoFotoById(idProduto);

        if(fotoExistente.isPresent()) {
            produtoRepository.delete(fotoExistente.get());
        }

        return produtoRepository.save(foto);
    }
}
