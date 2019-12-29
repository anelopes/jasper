package br.com.caelum.financas.relatorio.teste;

import br.com.caelum.financas.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class TesteRelatorio {

    public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {
        Connection conn = new ConnectionFactory().getConnection();

//        JasperCompileManager.compileReportToFile("gasto_por_mes.jrxml");
//        JasperCompileManager.compileReportToFile("gasto_por_mes_subreport1.jrxml");

        HashMap<String, Object> params = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport("gasto_por_mes.jasper", params, conn);

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("gasto_por_mes.pdf"));
        exporter.exportReport();

        conn.close();
    }
}
