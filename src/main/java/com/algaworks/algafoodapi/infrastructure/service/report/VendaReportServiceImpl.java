package com.algaworks.algafoodapi.infrastructure.service.report;

import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoodapi.domain.service.interfaces.VendaQueryService;
import com.algaworks.algafoodapi.domain.service.interfaces.VendaReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class VendaReportServiceImpl implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] generateReportVendasDiaria(VendaDiariaFilter filtro, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream("/relatorios/VENDAS_DIARIA.jasper");

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReportException("não foi possivel gerar relatório de vendas diárias", e);
        }
    }
}
