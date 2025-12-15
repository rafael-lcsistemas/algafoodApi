package com.algaworks.algafoodapi.api.controller.grupo;

import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.response.PermissaoResponse;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grupos/listar/{idGrupo}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @GetMapping
    public List<PermissaoResponse> listarPemissoesByGrupo(@PathVariable Long idGrupo){
        var grupo = grupoService.filtrarPorId(idGrupo);
        List<Permissao> permissoes = new ArrayList<>(grupo.getPermissoes());
        return genericResponseAssembler.toCollectionModel(permissoes, PermissaoResponse.class);
    }

    @PutMapping("/{idPermissao}")
    public void associarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
        grupoService.associar(idGrupo, idPermissao);
    }

    @DeleteMapping("/{idPermissao}")
    public void desassociarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
        grupoService.desassociar(idGrupo, idPermissao);
    }
}
