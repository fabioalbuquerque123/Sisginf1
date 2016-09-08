package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.ContadorPJ;
import beans.PessoaFisica;
import beans.PessoaJuridica;
import beans.ResponsavelLegalPJ;
import dataAcessObject.PessoaFisicaDAO;
import dataAcessObject.PessoaJuridicaDAO;

@ManagedBean(name="PessoaJuridicaMB")
@ViewScoped
public class PessoaJuridicaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3379473621951882479L;
	
	//Pessoa Jurídica
	private PessoaJuridica pessoaJuridica;
	private DataAcessObject pessoaJuridicaDAO;
	private List<Bean> pessoasJuridicas;
	
	//Pessoa Física
	private PessoaFisica pessoaFisica;
	private PessoaFisicaDAO pessoaFisicaDAO;
	private List<Bean> pessoasFisicas;
	
	private List<Bean> listContador;
	private List<Bean> listResponsavel;
	private ContadorPJ contadorSelecionado;
	private ResponsavelLegalPJ responsavelSelecionado;
	
	
	public PessoaJuridicaMB() {
		super();
		
		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setContadorPJ(new ContadorPJ());
		pessoaJuridica.setResponsavelLegalPJ(new ResponsavelLegalPJ());
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoasJuridicas = new ArrayList<Bean>();
		
		pessoaFisica = new PessoaFisica();
		pessoaFisicaDAO = new PessoaFisicaDAO();
		pessoasFisicas = new ArrayList<Bean>();
		
		// TODO Auto-generated constructor stub
	}
	
	public void selecionaResponsavel(){
		pessoaJuridica.setResponsavelLegalPJ(responsavelSelecionado);
	}
	
	public void selecionaContador(){
		pessoaJuridica.setContadorPJ(contadorSelecionado);
	}
	
	public void findAllResp(){
		pessoaFisicaDAO = new PessoaFisicaDAO();
		listResponsavel = pessoaFisicaDAO.findAllResp();
	}
	
	public void findAllCont(){
		pessoaFisicaDAO = new PessoaFisicaDAO();
		listContador = pessoaFisicaDAO.findAllContador();
	}
	
	public void findAllBean(){
		try{
			pessoasJuridicas = pessoaJuridicaDAO.findAllBean();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void findAllPessoasFisicas(){
		try{
			pessoasFisicas = pessoaFisicaDAO.findAllBean();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public DataAcessObject getPessoaJuridicaDAO() {
		return pessoaJuridicaDAO;
	}

	public void setPessoaJuridicaDAO(DataAcessObject pessoaJuridicaDAO) {
		this.pessoaJuridicaDAO = pessoaJuridicaDAO;
	}

	public List<Bean> getPessoasJuridicas() {
		return pessoasJuridicas;
	}

	public void setPessoasJuridicas(List<Bean> pessoasJuridicas) {
		this.pessoasJuridicas = pessoasJuridicas;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaFisicaDAO getPessoaFisicaDAO() {
		return pessoaFisicaDAO;
	}

	public void setPessoaFisicaDAO(PessoaFisicaDAO pessoaFisicaDAO) {
		this.pessoaFisicaDAO = pessoaFisicaDAO;
	}

	public List<Bean> getPessoasFisicas() {
		return pessoasFisicas;
	}

	public void setPessoasFisicas(List<Bean> pessoasFisicas) {
		this.pessoasFisicas = pessoasFisicas;
	}

	public List<Bean> getListContador() {
		return listContador;
	}

	public void setListContador(List<Bean> listContador) {
		this.listContador = listContador;
	}

	public List<Bean> getListResponsavel() {
		return listResponsavel;
	}

	public void setListResponsavel(List<Bean> listResponsavel) {
		this.listResponsavel = listResponsavel;
	}

	public ContadorPJ getContadorSelecionado() {
		return contadorSelecionado;
	}

	public void setContadorSelecionado(ContadorPJ contadorSelecionado) {
		this.contadorSelecionado = contadorSelecionado;
	}

	public ResponsavelLegalPJ getResponsavelSelecionado() {
		return responsavelSelecionado;
	}

	public void setResponsavelSelecionado(ResponsavelLegalPJ responsavelSelecionado) {
		this.responsavelSelecionado = responsavelSelecionado;
	}
}
