package com.algaworks.algafoodapi.api.controller.usuario;

import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.response.grupo.GrupoPermissaoResumeResponse;
import com.algaworks.algafoodapi.api.model.response.grupo.GrupoResumeResponse;
import com.algaworks.algafoodapi.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios/listar/{idUsuario}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @GetMapping
    public List<GrupoResumeResponse> listarGruposByUsuario(@PathVariable Long idUsuario) {
        var usuario = usuarioService.filtrarPorID(idUsuario);
        return genericResponseAssembler.toCollectionModel(usuario.getGrupos(), GrupoResumeResponse.class);
    }

    @GetMapping("/permissoes")
    public List<GrupoPermissaoResumeResponse> listarPermissoesByGrupoByUsuario(@PathVariable Long idUsuario) {
        var usuario = usuarioService.filtrarPorID(idUsuario);
        return genericResponseAssembler.toCollectionModel(usuario.getGrupos(), GrupoPermissaoResumeResponse.class);
    }
}
