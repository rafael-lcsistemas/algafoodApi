package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.model.CozinhasXmlRepresentation;
import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlRepresentation listarEmXml() {
        return new CozinhasXmlRepresentation(cozinhaRepository.findAll());
    }

    @GetMapping("/listar")
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id);

        if(cozinha == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinha);
    }

    @PostMapping
    public Cozinha inserirOrUpdate(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.insertOrUpdate(cozinha);
    }
}
