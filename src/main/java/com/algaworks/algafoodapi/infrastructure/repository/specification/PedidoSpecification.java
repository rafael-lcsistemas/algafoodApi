package com.algaworks.algafoodapi.infrastructure.repository.specification;

import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class PedidoSpecification implements Specification<Pedido> {

    private Long codigo;
    private UUID idUsuario;
    private UUID idRestaurante;
    private UUID idFormaPagamento;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime datahoraInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime datahoraFim;

    public PedidoSpecification(Long codigo, UUID idUsuario, UUID idRestaurante, UUID idFormaPagamento, OffsetDateTime datahoraInicio, OffsetDateTime datahoraFim) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.idRestaurante = idRestaurante;
        this.idFormaPagamento = idFormaPagamento;
        this.datahoraInicio = datahoraInicio;
        this.datahoraFim = datahoraFim;
    }

    @Override
    public Predicate toPredicate(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        root.fetch("restaurante").fetch("cozinha");
        root.fetch("usuario");

        var predicates = new ArrayList<Predicate>();

        if (this.codigo != null) {
            predicates.add(builder.equal(root.get("codInterno"), codigo));
        }

        if (this.idUsuario != null) {
            predicates.add(builder.equal(root.get("usuario").get("id"), idUsuario));
        }

        if (this.idRestaurante != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), idRestaurante));
        }

        if (this.idFormaPagamento != null) {
            predicates.add(builder.equal(root.get("formaPagamento").get("id"), idFormaPagamento));
        }

        if (datahoraInicio != null && datahoraFim != null) {
            predicates.add(builder.between(root.get("datahoraPedido"), datahoraInicio, datahoraFim)
            );
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
