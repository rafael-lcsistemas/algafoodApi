package com.algaworks.algafoodapi.api.controller.usuario;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.UsuarioInput;
import com.algaworks.algafoodapi.api.model.input.UsuarioInputAtualizarSenha;
import com.algaworks.algafoodapi.api.model.input.UsuarioInputSemSenha;
import com.algaworks.algafoodapi.api.model.response.UsuarioResponse;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class
UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<UsuarioResponse> listarTodos() {
        return genericResponseAssembler.toCollectionModel(usuarioService.listarTodos(), UsuarioResponse.class);
    }

    @GetMapping("/por-nome")
    public List<UsuarioResponse> filtrarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(usuarioService.filtrarPorNome(nome), UsuarioResponse.class);
    }

    @GetMapping("/{id}")
    public UsuarioResponse filtrarPorID(@PathVariable Long id) {
        return genericResponseAssembler.toModel(usuarioService.filtrarPorID(id), UsuarioResponse.class);
    }

    @PostMapping
    public UsuarioResponse inserir(@RequestBody @Valid UsuarioInput usuarioInput) {
        var usuario = genericInputAssembler.toEntity(usuarioInput, Usuario.class);

        try {
            return genericResponseAssembler.toModel(
                    usuarioService.iserirOuAtualizar(usuario, usuarioInput.getIdsGrupos()), UsuarioResponse.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInputSemSenha usuarioSemSenhaInput) {
        var usuario = genericInputAssembler.toEntity(usuarioSemSenhaInput, Usuario.class);

        var usuarioAtual = usuarioService.filtrarPorID(id);
        BeanUtils.copyProperties(usuario, usuarioAtual, "id", "senha", "dataCadastro");

        try {
            return genericResponseAssembler.toModel(
                    usuarioService.iserirOuAtualizar(usuarioAtual, usuarioSemSenhaInput.getIdsGrupos()), UsuarioResponse.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/atualizar-senha/{id}")
    public void atualizarSenha(@PathVariable Long id, @RequestBody @Valid UsuarioInputAtualizarSenha inputAtualizarSenha) {
        usuarioService.atualizarSenha(id, inputAtualizarSenha);
    }
}
