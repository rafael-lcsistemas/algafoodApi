package com.algaworks.algafoodapi.domain.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VendaDiaria {

    private Date data;
    private Long quantidadeVendas;
    private BigDecimal totalVendas;

    public VendaDiaria() {};

    public VendaDiaria(Date data, Long quantidadeVendas, BigDecimal totalVendas) {
        this.data = data;
        this.quantidadeVendas = quantidadeVendas;
        this.totalVendas = totalVendas;
    };

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(Long quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }

    public BigDecimal getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(BigDecimal totalVendas) {
        this.totalVendas = totalVendas;
    }
}
