package com.algaworks.algafoodapi.api.controller.dashboard;

import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoodapi.domain.model.dto.VendaDiaria;
import com.algaworks.algafoodapi.domain.service.interfaces.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard/vendas")
public class DashboardVendasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping(path = "/vendas-diaria", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filter, timeOffset);
    }
}
