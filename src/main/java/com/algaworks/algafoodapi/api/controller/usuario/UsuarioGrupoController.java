package com.algaworks.algafoodapi.api.controller.usuario;

import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.response.grupo.GrupoPermissaoResumeResponse;
import com.algaworks.algafoodapi.api.model.response.grupo.GrupoResumeResponse;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Grupo> listGrupos = new ArrayList<>(usuario.getGrupos());

        return genericResponseAssembler.toCollectionModel(listGrupos, GrupoResumeResponse.class);
    }

    @GetMapping("/permissoes")
    public List<GrupoPermissaoResumeResponse> listarPermissoesByGrupoByUsuario(@PathVariable Long idUsuario) {
        var usuario = usuarioService.filtrarPorID(idUsuario);
        List<Grupo> listGrupos = new ArrayList<>(usuario.getGrupos());

        return genericResponseAssembler.toCollectionModel(listGrupos, GrupoPermissaoResumeResponse.class);
    }

    @PutMapping("/{idGrupo}")
    public void associarGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo) {
        usuarioService.associarGrupo(idUsuario, idGrupo);
    }

    @DeleteMapping("/{idGrupo}")
    public void desassociarGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo) {
        usuarioService.desassociarGrupo(idUsuario, idGrupo);
    }
}
