package com.algaworks.algafoodapi.api.controller.restaurante;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.RestauranteInput;
import com.algaworks.algafoodapi.api.model.response.restaurante.RestauranteMovResponse;
import com.algaworks.algafoodapi.api.model.response.restaurante.RestauranteResponse;
import com.algaworks.algafoodapi.api.model.response.usuario.UsuarioResumeResponse;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.model.entity.restaurante.Restaurante;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<RestauranteResponse> filtrarTodas() {
        return genericResponseAssembler.toCollectionModel(
                restauranteService.filtrarTodas(), RestauranteResponse.class);
    }

    @GetMapping("/por-nome")
    public List<RestauranteResponse> filtrarNome(String nome) {
        return genericResponseAssembler.toCollectionModel(
                restauranteService.filtrarPorNome(nome), RestauranteResponse.class);
    }

    @GetMapping("{id}")
    public RestauranteResponse filtrarPorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.filtrarPorID(id);
        return genericResponseAssembler.toModel(restaurante, RestauranteResponse.class);
    }

    @GetMapping("/{id}/responsaveis")
    public List<UsuarioResumeResponse> responsaveis(@PathVariable Long id) {
        var restarante = restauranteService.filtrarPorID(id);
        List<Usuario> usuarios = new ArrayList<>(restarante.getUsuarios());

        return genericResponseAssembler.toCollectionModel(usuarios, UsuarioResumeResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse inserir(@RequestBody @Valid RestauranteInput restauranteInput) {

        Restaurante restaurante = genericInputAssembler.toEntity(restauranteInput, Restaurante.class);
        return genericResponseAssembler.toModel(
                restauranteService.inserirOuAtualizar(restaurante, restauranteInput), RestauranteResponse.class);
    }

    @PutMapping("/{id}")
    public RestauranteResponse atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
        var restaurante = genericInputAssembler.toEntity(restauranteInput, Restaurante.class);
        var restauranteAtual = restauranteService.filtrarPorID(id);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "dataCadastro");

        return genericResponseAssembler.toModel(
                restauranteService.inserirOuAtualizar(restauranteAtual, restauranteInput), RestauranteResponse.class);

    }

    @PutMapping("/{id}/abrir")
    public RestauranteMovResponse abrir(@PathVariable Long id,
                                        @RequestParam BigDecimal valorMovimento, @RequestParam Long idUsuario) {
        var movimento = restauranteService.abrirRestaurante(id, valorMovimento, idUsuario);
        return genericResponseAssembler.toModel(movimento, RestauranteMovResponse.class);

    }

    @PutMapping("/{id}/fechar")
    public RestauranteMovResponse fechar(@PathVariable Long id, @RequestParam Long idUsuario) {
        var movimento = restauranteService.fecharRestaurante(id, idUsuario);
        return genericResponseAssembler.toModel(movimento, RestauranteMovResponse.class);
    }

    @PutMapping("/{idRestaurante}/usuarios/{idUsuario}")
    public void associarUsuario(@PathVariable Long idRestaurante, @PathVariable Long idUsuario) {
        restauranteService.asassociarUsuarioToRestaurante(idRestaurante, idUsuario);
    }

    @DeleteMapping("/{idRestaurante}/usuarios/{idUsuario}")
    public void desassociarUsuario(@PathVariable Long idRestaurante, @PathVariable Long idUsuario) {
        restauranteService.desassociarUsuarioToRestaurante(idRestaurante, idUsuario);
    }

    @PutMapping("/ativacoes")
    public void ativarRestaurantes(@RequestBody List<Long> idsRestaurantes) {
        restauranteService.ativarRestaurantesMultiplos(idsRestaurantes);
    }

    @PutMapping("/inativacoes")
    public void inativarRestaurantes(@RequestBody List<Long> idsRestaurantes) {
        restauranteService.inativarRestaurantesMultiplos(idsRestaurantes);
    }
}
