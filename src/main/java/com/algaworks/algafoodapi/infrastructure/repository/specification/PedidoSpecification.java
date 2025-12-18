package com.algaworks.algafoodapi.infrastructure.repository.specification;

import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PedidoSpecification implements Specification<Pedido> {

    private String codigo;

    public PedidoSpecification(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public Predicate toPredicate(Root<Pedido> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if(codigo == null || codigo.isBlank()) {
            return criteriaBuilder.conjunction();
        }

        return criteriaBuilder.equal(root.get("codInterno"), codigo);
    }
}
