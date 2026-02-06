package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.ProdutoFotoInput;
import com.algaworks.algafoodapi.api.model.response.ProdutoFotoResponse;
import com.algaworks.algafoodapi.domain.model.entity.ProdutoFoto;
import com.algaworks.algafoodapi.domain.service.ProdutoFotoService;
import com.algaworks.algafoodapi.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/produtos/{idProduto}/foto")
public class ProdutoFotoController {

    @Autowired
    private ProdutoFotoService produtoFotoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProdutoFotoResponse atualizarFoto(@PathVariable UUID idProduto, @Valid ProdutoFotoInput input) {

        var produto = produtoService.filtrarPorId(idProduto);
        var arquivo = input.getArquivo();

        ProdutoFoto foto = new ProdutoFoto();
        foto.setProduto(produto);
        foto.setDescricao(input.getDescricao());
        foto.setNomeArquivo(arquivo.getOriginalFilename());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());

        var fotoSalva = produtoFotoService.salvarFoto(foto);

        return genericResponseAssembler.toModel(fotoSalva, ProdutoFotoResponse.class);
    }
}
