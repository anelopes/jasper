package br.com.caelum.relatorio.teste;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.relatorio.GeradorRelatorio;
import br.com.caelum.relatorio.modelo.Movimentacao;
import br.com.caelum.relatorio.modelo.MovimentacaoRelatorio;
import br.com.caelum.relatorio.modelo.TipoMovimentacao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TesteGeraRelatorioComBeanDataSource {

	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {

		JasperCompileManager.compileReportToFile("movimentacoes.jrxml");

		Map<String, Object> parametros = new HashMap<String, Object>();

		List<Movimentacao> lista = Arrays.asList(
				new Movimentacao(Calendar.getInstance(), "Telefone", TipoMovimentacao.SAIDA, new BigDecimal("143.7")),
				new Movimentacao(Calendar.getInstance(), "Agua", TipoMovimentacao.SAIDA, new BigDecimal("36.8")),
				new Movimentacao(Calendar.getInstance(), "Luz", TipoMovimentacao.SAIDA, new BigDecimal("46.91")));

		List<MovimentacaoRelatorio> listaRelatorio = new ArrayList<>();

		for (Movimentacao movimentacao : lista) {
			listaRelatorio.add(new MovimentacaoRelatorio(movimentacao));
		}

		JRDataSource dataSource = new JRBeanCollectionDataSource(listaRelatorio);

		GeradorRelatorio geradorRelatorio = new GeradorRelatorio("movimentacoes.jasper", parametros, dataSource);
		geradorRelatorio.geraPDFPara(new FileOutputStream("movimentacoesFixas.pdf"));
	}
}
