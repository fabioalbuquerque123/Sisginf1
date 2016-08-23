package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.ContadorPJ;
import beans.PessoaFisica;
import beans.PessoaJuridica;
import beans.Processo;
import beans.Projeto;
import dataAcessObject.PessoaFisicaDAO;
import dataAcessObject.PessoaJuridicaDAO;
import dataAcessObject.ProcessoDAO;
import dataAcessObject.ProjetoDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjetoDAO extends TestCase{

	private DataAcessObject dao;
	private Processo processo;
	private Projeto projeto;
	private PessoaJuridica pessoaJuridica;
	private PessoaFisica pessoaFisica;
	
	@Test
	public void testAinsertBean(){
		try{
			//Pessoa Fisica
			dao = new PessoaFisicaDAO();
			pessoaFisica = LoadBean.getContadorPJ();
			dao.insertBean(pessoaFisica);			
			pessoaFisica = new ContadorPJ();
			pessoaFisica = (ContadorPJ) dao.findAllBean().get(0);
			
			//Pessoa Juridica
			pessoaJuridica = LoadBean.getPessoaJuridica();
			pessoaJuridica.setPessoaFisica(pessoaFisica);
			dao = new PessoaJuridicaDAO();
			dao.insertBean(pessoaJuridica);	
			pessoaJuridica = new PessoaJuridica();
			pessoaJuridica = (PessoaJuridica) dao.findAllBean().get(0);
						
			//Processo
			dao = new ProcessoDAO();
			processo = LoadBean.getProcesso();
			dao.insertBean(processo);			
			processo = new Processo();
			processo = (Processo) dao.findAllBean().get(0);		
			
			//Projeto
			projeto = LoadBean.getProjeto();
			projeto.setProcesso(processo);
			projeto.setPessoaJuridica(pessoaJuridica);
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
			
			//Delete Projeto
			dao = new ProjetoDAO();
			projeto = (Projeto) dao.findAllBean().get(0);
			dao.deleteBean(projeto);
			
			//Delete Processo
			dao = new ProcessoDAO();
			processo = (Processo) dao.findAllBean().get(0);	
			dao.deleteBean(processo);
			
			//Delete Pessoa Juridica
			dao = new PessoaJuridicaDAO();
			pessoaJuridica = (PessoaJuridica) dao.findAllBean().get(0);	
			dao.deleteBean(pessoaJuridica);
			
			//Delete Pessoa Fisica
			dao = new PessoaFisicaDAO();
			pessoaFisica = (PessoaFisica) dao.findAllBean().get(0);
			dao.deleteBean(pessoaFisica);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
