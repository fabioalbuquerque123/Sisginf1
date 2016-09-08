package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.ContadorPJ;
import beans.PessoaFisica;
import beans.PessoaJuridica;
import beans.ResponsavelLegalPJ;
import dataAcessObject.PessoaFisicaDAO;
import dataAcessObject.PessoaJuridicaDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPessoaJuridicaDAO extends TestCase{

	private PessoaFisicaDAO pessoaFisicaDAO;
	private DataAcessObject dao;
	private PessoaJuridica pessoaJuridica;
	private ContadorPJ contadorPJ;
	private ResponsavelLegalPJ responsavel;
	
	@Test
	public void testAinsertBean(){
		try{
			pessoaFisicaDAO = new PessoaFisicaDAO();
			contadorPJ = LoadBean.getContadorPJ();
			responsavel = LoadBean.getResponsavelLegalPJ();
			dao.insertBean(contadorPJ);
			dao.insertBean(responsavel);	
			contadorPJ = new ContadorPJ();
			contadorPJ = (ContadorPJ) pessoaFisicaDAO.findAllContador().get(0);		
			responsavel = new ResponsavelLegalPJ();
			responsavel = (ResponsavelLegalPJ) pessoaFisicaDAO.findAllResp().get(0);	
			pessoaJuridica = LoadBean.getPessoaJuridica();
			pessoaJuridica.setContadorPJ(contadorPJ);
			pessoaJuridica.setResponsavelLegalPJ(responsavel);
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
			pessoaFisicaDAO = new PessoaFisicaDAO();
			contadorPJ = (ContadorPJ) pessoaFisicaDAO.findAllContador().get(0);
			dao.deleteBean(contadorPJ);
			responsavel = (ResponsavelLegalPJ) pessoaFisicaDAO.findAllResp().get(0);
			dao.deleteBean(responsavel);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
