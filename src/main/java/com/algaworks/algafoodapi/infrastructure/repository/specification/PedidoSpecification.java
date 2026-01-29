package com.algaworks.algafoodapi.infrastructure.repository.specification;

import com.algaworks.algafoodapi.domain.model.entity.pedido.OrdemPedido;
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
    private OrdemPedido ordemPedido;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime datahoraInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime datahoraFim;

    public PedidoSpecification(Long codigo, UUID idUsuario, UUID idRestaurante, UUID idFormaPagamento, OffsetDateTime datahoraInicio, OffsetDateTime datahoraFim, OrdemPedido ordemPedido) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.idRestaurante = idRestaurante;
        this.idFormaPagamento = idFormaPagamento;
        this.datahoraInicio = datahoraInicio;
        this.datahoraFim = datahoraFim;
        this.ordemPedido = ordemPedido;
    }

    @Override
    public Predicate toPredicate(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (query.getResultType().equals(Pedido.class)) {
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("usuario");
        }

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

        if (ordemPedido != null && ordemPedido.equals(OrdemPedido.DATA_HORA)) {
            query.orderBy(builder.desc(root.get("datahoraPedido")));
        } else {
            query.orderBy(
                    builder.asc(root.get("usuario").get("nome")),
                    builder.desc(root.get("datahoraPedido"))
            );
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
