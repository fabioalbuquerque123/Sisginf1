package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.ContadorPJ;
import beans.PessoaFisica;
import beans.ResponsavelLegalPJ;
import dataAcessObject.PessoaFisicaDAO;

@ManagedBean(name="PessoaFisicaMB")
@ViewScoped
public class PessoaFisicaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3186275717788300128L;

	//Campos globais
	private String nome;
	private String cpf;
	private String telefones;
	private String email;
	
	//Radio Button
	private List<String> listRadio;
	private String tipoPessoaFisica;
	
	//Pessoa Física
	private PessoaFisica pessoaFisica;
	private PessoaFisica pessoaFisicaTela;
	private DataAcessObject pessoaFisicaDAO;
	private List<Bean> listPessoasFisicas;
	
	public PessoaFisicaMB() {
		super();
		pessoaFisicaTela = new PessoaFisica();
		pessoaFisicaDAO = new PessoaFisicaDAO();
		listPessoasFisicas = new ArrayList<Bean>();
		this.setListRadio(new ArrayList<String>());
		// TODO Auto-generated constructor stub
	}
	
	
	public void findAllBean(){
		try{
			listPessoasFisicas = pessoaFisicaDAO.findAllBean();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/*	public void saveBean(){
		try{
			if(tipoPessoaFisica.equals(listRadio.get(0))){
				pessoaFisica = new ResponsavelLegalPJ();
				pessoaFisica.setTipo("RESP");
				this.setPessoaFisicaGeral();
				this.insertBean();
			}else{
				pessoaFisica = new ContadorPJ();
				this.setPessoaFisicaGeral();
				pessoaFisica.setTipo("CONT");
				this.insertBean();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}*/
	
	public void saveBean(){
		try{
			pessoaFisica = new ResponsavelLegalPJ();
			pessoaFisica.setTipo("RESP");
			this.setPessoaFisicaGeral();
			this.insertBean();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void insertBean(){
		try{
			if(pessoaFisica.getNome() == null || pessoaFisica.getNome().length() < 1 ||
					pessoaFisica.getTipo() == null || pessoaFisica.getTipo().length() < 1 ||
					pessoaFisica.getCpf() == null || pessoaFisica.getCpf().length() < 1){				
		        FacesContext context = FacesContext.getCurrentInstance();		         
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Favor inserir um nome, um cpf e selecionar um tipo (Responsável/Contador)!","") );
			}else{
				pessoaFisicaDAO = new PessoaFisicaDAO();
				pessoaFisicaDAO.insertBean(pessoaFisica);
				clearFields();
				FacesContext context = FacesContext.getCurrentInstance();	         
		        context.addMessage(null, new FacesMessage("",  "Cadastro realizado com sucesso!") );
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void clearFields(){
		pessoaFisicaTela = new PessoaFisica();		
		tipoPessoaFisica = "";
	}

	private void setPessoaFisicaGeral(){
		pessoaFisica.setCpf(pessoaFisicaTela.getCpf());
		pessoaFisica.setNome(pessoaFisicaTela.getNome());
		pessoaFisica.setTelefones(pessoaFisicaTela.getTelefones());
		pessoaFisica.setEmail(pessoaFisicaTela.getEmail());
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}


	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}


	public DataAcessObject getPessoaFisicaDAO() {
		return pessoaFisicaDAO;
	}

	public void setPessoaFisicaDAO(DataAcessObject pessoaFisicaDAO) {
		this.pessoaFisicaDAO = pessoaFisicaDAO;
	}

	public List<Bean> getListPessoasFisicas() {
		return listPessoasFisicas;
	}

	public void setListPessoasFisicas(List<Bean> listPessoasFisicas) {
		this.listPessoasFisicas = listPessoasFisicas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<String> getListRadio() {
		return listRadio;
	}


	public void setListRadio(List<String> listRadio) {
		this.listRadio = listRadio;
		listRadio.add("responsavel");
		listRadio.add("contador");
	}


	public String getTipoPessoaFisica() {
		return tipoPessoaFisica;
	}


	public void setTipoPessoaFisica(String tipoPessoaFisica) {
		this.tipoPessoaFisica = tipoPessoaFisica;
	}


	public PessoaFisica getPessoaFisicaTela() {
		return pessoaFisicaTela;
	}


	public void setPessoaFisicaTela(PessoaFisica pessoaFisicaTela) {
		this.pessoaFisicaTela = pessoaFisicaTela;
	}
}
