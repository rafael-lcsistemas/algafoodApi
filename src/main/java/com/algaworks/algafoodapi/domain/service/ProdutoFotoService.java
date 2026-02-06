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
        String nomeArquivoNovo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoAntigo = null;

        Optional<ProdutoFoto> fotoExistente = produtoRepository.findProdutoFotoById(idProduto);

        if(fotoExistente.isPresent()) {
            nomeArquivoAntigo = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeArquivoNovo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(stream)
                .build();

        fotoStorage.substituir(nomeArquivoAntigo, novaFoto);

        return foto;
    }
}
