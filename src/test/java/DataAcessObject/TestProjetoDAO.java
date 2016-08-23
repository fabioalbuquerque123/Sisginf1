package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.Processo;
import beans.Projeto;
import dataAcessObject.ProcessoDAO;
import dataAcessObject.ProjetoDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjetoDAO extends TestCase{

	private DataAcessObject dao;
	private Processo processo;
	private Projeto projeto;
	
	@Test
	public void testAinsertBean(){
		try{
			dao = new ProcessoDAO();
			processo = LoadBean.getProcesso();
			dao.insertBean(processo);			
			processo = new Processo();
			processo = (Processo) dao.findAllBean().get(0);			
			projeto = LoadBean.getProjeto();
			projeto.setProcesso(processo);
			dao = new ProjetoDAO();
			dao.insertBean(projeto);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBupdateBean(){
		try{
			dao = new ProjetoDAO();
			projeto = (Projeto) dao.findAllBean().get(0);
			projeto.setNome("Projeto Atualizado");
			dao.updateBean(projeto);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCdeleteBean(){
		try{
			dao = new ProjetoDAO();
			projeto = (Projeto) dao.findAllBean().get(0);
			dao.deleteBean(projeto);
			dao = new ProcessoDAO();
			processo = (Processo) dao.findAllBean().get(0);	
			dao.deleteBean(processo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
