package com.algaworks.algafoodapi.domain.service.interfaces;

import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] generateReportVendasDiaria(VendaDiariaFilter filtro, String timeOffset) ;
}
