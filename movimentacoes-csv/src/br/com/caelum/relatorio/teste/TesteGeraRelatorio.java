package br.com.caelum.relatorio.teste;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.relatorio.ConnectionFactory;
import br.com.caelum.relatorio.GeradorRelatorio;
import br.com.caelum.relatorio.dao.MovimentacaoDAO;
import br.com.caelum.relatorio.modelo.Movimentacao;
import br.com.caelum.relatorio.modelo.MovimentacaoRelatorio;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TesteGeraRelatorio {
    public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {

        JasperCompileManager.compileReportToFile("movimentacoes.jrxml"); 

        Map<String, Object> parametros = new HashMap<String, Object>();
        Connection conexao = new ConnectionFactory().getConnection();
        
        List<Movimentacao> movimentacoes = new MovimentacaoDAO(conexao).todos();
        List<MovimentacaoRelatorio> listaRelatorio = new ArrayList<MovimentacaoRelatorio>();
        
        for (Movimentacao movimentacao : movimentacoes) {
			listaRelatorio.add(new MovimentacaoRelatorio(movimentacao));
		}
        
        JRDataSource dataSource = new JRBeanCollectionDataSource(listaRelatorio);
        
        GeradorRelatorio gerador = new GeradorRelatorio("movimentacoes.jasper", parametros, dataSource);
        gerador.geraPDFPara(new FileOutputStream("movimentacoes.pdf"));

        conexao.close();
    }
}