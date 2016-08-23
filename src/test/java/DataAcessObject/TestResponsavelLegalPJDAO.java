package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.ResponsavelLegalPJ;
import dataAcessObject.PessoaFisicaDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestResponsavelLegalPJDAO extends TestCase{
	
	private DataAcessObject dao;
	private ResponsavelLegalPJ responsavelLegalPJ;
	
	@Test
	public void testAinsertBean(){
		try{
			dao = new PessoaFisicaDAO();
			responsavelLegalPJ = LoadBean.getResponsavelLegalPJ();
			dao.insertBean(responsavelLegalPJ);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBupdateBean(){
		try{
			dao = new PessoaFisicaDAO();
			responsavelLegalPJ = (ResponsavelLegalPJ) dao.findAllBean().get(0);
			responsavelLegalPJ.setNome("Responsavel Atualizado");
			dao.updateBean(responsavelLegalPJ);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testCdeleteBean(){
		try{
			dao = new PessoaFisicaDAO();
			responsavelLegalPJ = (ResponsavelLegalPJ) dao.findAllBean().get(0);
			dao.deleteBean(responsavelLegalPJ);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
