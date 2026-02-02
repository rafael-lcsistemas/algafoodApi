package com.algaworks.algafoodapi.infrastructure.service;

import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoodapi.domain.model.dto.VendaDiaria;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.model.entity.pedido.StatusPedido;
import com.algaworks.algafoodapi.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var predicates = new ArrayList<Predicate>();

        var functionConvertDateTimeOffset = builder.function(
                "convert_tz",
                Date.class,
                root.get("datahoraPedido"),
                builder.literal("+00:00"),
                builder.literal(timeOffset));

        var functionDateDataPedido = builder.function(
                "date", Date.class, functionConvertDateTimeOffset);

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataPedido,
                builder.count(root.get("codInterno")),
                builder.sum(root.get("subtotal")));

        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
        }

        if (filtro.getDataPedidoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(functionConvertDateTimeOffset,
                    Date.from(filtro.getDataPedidoInicio().toInstant())));
        }

        if (filtro.getDataPedidoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(functionConvertDateTimeOffset,
                    Date.from(filtro.getDataPedidoFim().toInstant())));
        }

        predicates.add(root.get("statusPedido").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(Predicate[]::new));
        query.groupBy(functionDateDataPedido);
        query.orderBy(builder.asc(functionDateDataPedido));

        return manager.createQuery(query).getResultList();
    }
}
