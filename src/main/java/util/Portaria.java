package util;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;


import dataAcessObject.ProjetoDAO;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;


public class Portaria {
	
	private static Map<String, Object> parametros;
	private static JasperDesign desenho;
	private static JasperReport relatorio;
	private static JasperPrint impressao;
	private static JasperViewer viewer;
	private static EntityManager em;
	private static ProjetoDAO projetoDAO;
	@SuppressWarnings("deprecation")
	private static JRExporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> exporter;

	@SuppressWarnings("deprecation")
	public static String gerarPortaria(int idProjeto,String parametro,String caminhoRelatorio, OutputStream outputStream,String path) {							
			try {
				desenho = JRXmlLoader.load(caminhoRelatorio);
				relatorio = JasperCompileManager.compileReport(desenho);			
				parametros = new HashMap<>();				
				parametros.put(parametro,idProjeto);			
				projetoDAO = new ProjetoDAO();
				JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(projetoDAO.findToReport(idProjeto));
				impressao = JasperFillManager.fillReport(relatorio, parametros, beanCollectionDataSource);						
		
				JasperExportManager.exportReportToPdfFile(impressao,path+"/portaria.pdf");
				exporter = new JRPdfExporter();
		
	            exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
	            exporter.exportReport(); 
	 
	           
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return "file:///"+ System.getProperty("user.home") + "//portaria.pdf";
	}

	public static Map<String, Object> getParametros() {
		return parametros;
	} 
	

	public static void setParametros(Map<String, Object> parametros) {
		Portaria.parametros = parametros;
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

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		Portaria.em = em;
	}
}
