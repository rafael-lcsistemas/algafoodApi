package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.api.model.input.RestauranteInput;
import com.algaworks.algafoodapi.domain.exceptions.*;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.model.entity.restaurante.Restaurante;
import com.algaworks.algafoodapi.domain.model.entity.restaurante.RestauranteMov;
import com.algaworks.algafoodapi.domain.model.entity.restaurante.TipoMov;
import com.algaworks.algafoodapi.domain.repository.RestauranteMovRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteMovRepository restauranteMovRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private UsuarioService usuarioService;

    public List<Restaurante> filtrarTodas() {
        try {
            return restauranteRepository.findAll();
        } catch (RuntimeException e) {
            throw new NegocioException("Erro ao buscar todos os restaurantes");
        }
    }

    public List<Restaurante> filtrarPorNome(String nome) {
        try {
            return restauranteRepository.findByNomeContaining(nome);
        } catch (RuntimeException e) {
            throw new NegocioException("Erro ao buscar restaurantes por nome");
        }
    }

    public Restaurante filtrarPorID(UUID id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradaException(id));
    }

    @Transactional
    public Restaurante inserirOuAtualizar(Restaurante restaurante, RestauranteInput restauranteInput) {

        try {
            if (restaurante.getCodInterno() == null) {
                restaurante.setCodInterno(getLastCodInterno() + 1);
            }

            var cozinha = cozinhaService.filtrarPorId(restauranteInput.getIdcozinha());
            restaurante.setCozinha(cozinha);

            var cidade = cidadeService.filtrarPorId(restauranteInput.getEndereco().getIdcidade());
            restaurante.getEndereco().setCidade(cidade);

            return restauranteRepository.save(restaurante);
        } catch (FormaPagamentoNaoEncontradaException | CozinhaNaoEncontradaException |
                 CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void desassociarFormaPagamentoToRestaurante(UUID idRestaurante, UUID idFormaPagamento) {
        Restaurante restaurante = filtrarPorID(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoService.filtrarPorID(idFormaPagamento);

        restaurante.desassociarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void asassociarFormaPagamentoToRestaurante(UUID idRestaurante, UUID idFormaPagamento) {
        Restaurante restaurante = filtrarPorID(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoService.filtrarPorID(idFormaPagamento);

        restaurante.associarFormaPagamento(formaPagamento);
    }

    @Transactional
    public RestauranteMov abrirRestaurante(UUID idRestaurante, UUID idUsuario, BigDecimal valorMovimento) {
        try {
            Restaurante restaurante = filtrarPorID(idRestaurante);
            Usuario usuario = usuarioService.filtrarPorID(idUsuario);

            if (restaurante.getAberto()) {
                throw new NegocioException("Restaurante já está aberto");
            }

            restaurante.abrirRestaurante();

            RestauranteMov movimento = new RestauranteMov();
            movimento.setCodInterno(getLastCodInternoMov() + 1);
            movimento.setTipoMovimento(TipoMov.ABERTO);
            movimento.setValorMovimento(valorMovimento);
            movimento.setObservacoes("Abertura do restaurante");
            movimento.setUsuario(usuario);

            restaurante.adicionarMovimento(movimento);

            return movimento;
        } catch (UsuarioNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public RestauranteMov fecharRestaurante(UUID idRestaurante, UUID idUsuario) {
        try {
            Restaurante restaurante = filtrarPorID(idRestaurante);
            Usuario usuario = usuarioService.filtrarPorID(idUsuario);

            if (!restaurante.getAberto()) {
                throw new NegocioException("Restaurante já está fechado");
            }

            restaurante.fecharRestaurante();

            RestauranteMov movimento = new RestauranteMov();
            movimento.setCodInterno(getLastCodInternoMov() + 1);
            movimento.setTipoMovimento(TipoMov.FECHADO);
            movimento.setValorMovimento(BigDecimal.ZERO);
            movimento.setObservacoes("Fechamento do restaurante");
            movimento.setUsuario(usuario);

            restaurante.adicionarMovimento(movimento);

            return movimento;
        } catch (UsuarioNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void asassociarUsuarioToRestaurante(UUID idRestaurante, UUID idUsuario) {
        try {
            Restaurante restaurante = filtrarPorID(idRestaurante);
            Usuario usuario = usuarioService.filtrarPorID(idUsuario);

            restaurante.associarUsuario(usuario);
        } catch (UsuarioNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void desassociarUsuarioToRestaurante(UUID idRestaurante, UUID idUsuario) {
        try {
            Restaurante restaurante = filtrarPorID(idRestaurante);
            Usuario usuario = usuarioService.filtrarPorID(idUsuario);

            restaurante.desassociarUsuario(usuario);
        } catch (UsuarioNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void ativarRestaurantesMultiplos(List<UUID> idsRestaurantes) {
        idsRestaurantes.forEach(id -> atualizarStatusRestaurante(id, Boolean.TRUE));
    }

    @Transactional
    public void inativarRestaurantesMultiplos(List<UUID> idsRestaurantes) {
        idsRestaurantes.forEach(id -> atualizarStatusRestaurante(id, Boolean.FALSE));
    }

    private void atualizarStatusRestaurante(UUID idRestaurante, Boolean status) {
        try {
            var restaurante = filtrarPorID(idRestaurante);
            restaurante.setStatus(status);
        } catch (RestauranteNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    private Integer getLastCodInterno() {
        return restauranteRepository.getLastCodInterno();
    }

    private Integer getLastCodInternoMov() {
        return restauranteMovRepository.getLastCodInterno();
    }
}
