package br.com.caelum.relatorio.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.relatorio.ConnectionFactory;
import br.com.caelum.relatorio.gerador.GeradorRelatorio;

@WebServlet("/gastos")
public class GastosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String nome = request.getServletContext().getRealPath("/WEB-INF/jasper/gasto_por_mes.jasper");
			Connection connection = new ConnectionFactory().getConnection();
			
			String dataIni = request.getParameter("data_ini");
			String dataFim = request.getParameter("data_fim");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicial = format.parse(dataIni);
			Date dataFinal = format.parse(dataFim);

			Map<String, Object> params = new HashMap<>();
			params.put("DATA_INI", dataInicial);
			params.put("DATA_FIM", dataFinal);
			
			GeradorRelatorio gerador = new GeradorRelatorio(nome, params, connection);
			gerador.geraPDFParaOutputStream(response.getOutputStream());

			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ParseException e) {
			throw new ServletException(e);
		} 
	}
}
