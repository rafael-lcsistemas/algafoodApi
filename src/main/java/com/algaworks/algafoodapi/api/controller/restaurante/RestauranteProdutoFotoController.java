package com.algaworks.algafoodapi.api.controller.restaurante;

import com.algaworks.algafoodapi.api.model.input.FotoProdutoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/produtos/{idProduto}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable UUID idRestaurante, @PathVariable UUID idProduto, FotoProdutoInput input) {

        var arquivo = input.getArquivo();
        var nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
        var arquivoFoto = Path.of("C:/Users/rafae/Imagens/Catalago", nomeArquivo);

        System.out.println(input.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(arquivo.getContentType());

        try {
            arquivo.transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
