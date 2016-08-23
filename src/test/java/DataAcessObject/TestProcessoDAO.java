package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.Processo;
import dataAcessObject.ProcessoDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProcessoDAO extends TestCase{

	private DataAcessObject dao;
	private Processo processo;
	
	@Test
	public void testAinsertBean(){
		try{
			dao = new ProcessoDAO();
			processo = LoadBean.getProcesso();
			dao.insertBean(processo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBupdateBean(){
		try{
			dao = new ProcessoDAO();
			processo = (Processo) dao.findAllBean().get(0);
			processo.setLocalizacao("Localizacao Atualizada");
			dao.updateBean(processo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testCdeleteBean(){
		try{
			dao = new ProcessoDAO();
			processo = (Processo) dao.findAllBean().get(0);
			dao.deleteBean(processo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
