package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.ContadorPJ;
import beans.PessoaFisica;
import beans.PessoaJuridica;
import dataAcessObject.PessoaFisicaDAO;
import dataAcessObject.PessoaJuridicaDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPessoaJuridicaDAO extends TestCase{

	private DataAcessObject dao;
	private PessoaJuridica pessoaJuridica;
	private PessoaFisica pessoaFisica;
	
	@Test
	public void testAinsertBean(){
		try{
			dao = new PessoaFisicaDAO();
			pessoaFisica = LoadBean.getContadorPJ();
			dao.insertBean(pessoaFisica);			
			pessoaFisica = new ContadorPJ();
			pessoaFisica = (ContadorPJ) dao.findAllBean().get(0);			
			pessoaJuridica = LoadBean.getPessoaJuridica();
			pessoaJuridica.setPessoaFisica(pessoaFisica);
			dao = new PessoaJuridicaDAO();
			dao.insertBean(pessoaJuridica);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBupdateBean(){
		try{
			dao = new PessoaJuridicaDAO();
			pessoaJuridica = (PessoaJuridica) dao.findAllBean().get(0);
			pessoaJuridica.setNomeEmpresarial("Empresa Atualizada");
			dao.updateBean(pessoaJuridica);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCdeleteBean(){
		try{
			dao = new PessoaJuridicaDAO();
			pessoaJuridica = (PessoaJuridica) dao.findAllBean().get(0);	
			dao.deleteBean(pessoaJuridica);
			dao = new PessoaFisicaDAO();
			pessoaFisica = (PessoaFisica) dao.findAllBean().get(0);
			dao.deleteBean(pessoaFisica);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
