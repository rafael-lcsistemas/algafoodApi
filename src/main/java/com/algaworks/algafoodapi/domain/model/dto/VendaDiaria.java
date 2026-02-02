package com.algaworks.algafoodapi.domain.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VendaDiaria {

    private Date data;
    private Long quantidade;
    private BigDecimal total;

    public VendaDiaria() {};

    public VendaDiaria(Date data, Long quantidade, BigDecimal total) {
        this.data = data;
        this.quantidade = quantidade;
        this.total = total;
    };

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
