package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.ContadorPJ;
import dataAcessObject.PessoaFisicaDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestContadorPJDAO extends TestCase{
	
	private DataAcessObject dao;
	private ContadorPJ contadorPJ;
	
	@Test
	public void testAinsertBean(){
		try{
			dao = new PessoaFisicaDAO();
			contadorPJ = LoadBean.getContadorPJ();
			dao.insertBean(contadorPJ);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBupdateBean(){
		try{
			dao = new PessoaFisicaDAO();
			contadorPJ = (ContadorPJ) dao.findAllBean().get(0);
			contadorPJ.setNome("Contador Atualizado");
			dao.updateBean(contadorPJ);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testCdeleteBean(){
		try{
			dao = new PessoaFisicaDAO();
			contadorPJ = (ContadorPJ) dao.findAllBean().get(0);
			dao.deleteBean(contadorPJ);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
