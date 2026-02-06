package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.entity.ProdutoFoto;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;
import com.algaworks.algafoodapi.domain.service.interfaces.FotoStorageService;
import com.algaworks.algafoodapi.domain.service.interfaces.FotoStorageService.NovaFoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class ProdutoFotoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public ProdutoFoto salvarFoto(ProdutoFoto foto, InputStream stream) {
        var idProduto = foto.getProduto().getId();
        var novoNomeArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());

        Optional<ProdutoFoto> fotoExistente = produtoRepository.findProdutoFotoById(idProduto);

        if(fotoExistente.isPresent()) {
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(novoNomeArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(stream)
                .build();

        fotoStorage.armazenar(novaFoto);

        return foto;
    }
}
