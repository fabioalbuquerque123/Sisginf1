package util;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Portaria {
	
	private static Map<String, Object> parametros;
	private static InputStream inputStream;
	private static JasperDesign desenho;
	private static JasperReport relatorio;
	private static JRResultSetDataSource jrRS;
	private static JasperPrint impressao;
	private static JasperViewer viewer;
	
	public static void gerarPortaria(int id){						
		try{
			desenho = JRXmlLoader.load("");
			relatorio = JasperCompileManager.compileReport(desenho);
			//jrRS = new JRResultSetDataSource(rs);			
			parametros = new HashedMap<>();
			parametros.put("ID_PROJETO",id);
			impressao = JasperFillManager.fillReport(relatorio, parametros);
			viewer = new JasperViewer( impressao , true );
			viewer.show();
		}catch(Exception e){
			e.printStackTrace();
		}				
	}

	public static Map<String, Object> getParametros() {
		return parametros;
	}

	public static void setParametros(Map<String, Object> parametros) {
		Portaria.parametros = parametros;
	}

	public static InputStream getInputStream() {
		return inputStream;
	}

	public static void setInputStream(InputStream inputStream) {
		Portaria.inputStream = inputStream;
	}

	public static JasperDesign getDesenho() {
		return desenho;
	}

	public static void setDesenho(JasperDesign desenho) {
		Portaria.desenho = desenho;
	}

	public static JasperReport getRelatorio() {
		return relatorio;
	}

	public static void setRelatorio(JasperReport relatorio) {
		Portaria.relatorio = relatorio;
	}

	public static JRResultSetDataSource getJrRS() {
		return jrRS;
	}

	public static void setJrRS(JRResultSetDataSource jrRS) {
		Portaria.jrRS = jrRS;
	}

	public static JasperPrint getImpressao() {
		return impressao;
	}

	public static void setImpressao(JasperPrint impressao) {
		Portaria.impressao = impressao;
	}

	public static JasperViewer getViewer() {
		return viewer;
	}

	public static void setViewer(JasperViewer viewer) {
		Portaria.viewer = viewer;
	}
}
