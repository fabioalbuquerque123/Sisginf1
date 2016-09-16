package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import arquitetura.Bean;
import beans.PessoaFisica;
import beans.PessoaJuridica;
import beans.Projeto;
import dataAcessObject.PessoaFisicaDAO;
import dataAcessObject.PessoaJuridicaDAO;
import dataAcessObject.ProjetoDAO;

@ManagedBean(name="ProjetoMB")
@ViewScoped
public class ProjetoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4351551810155304204L;
	private static ProjetoMB projetoMB = new ProjetoMB();
	
	//Projeto
	private Projeto projeto;
	private ProjetoDAO projetoDAO;
	private List<Bean> projetos;
	private static List<Bean> projetosParaReidiMB;
	private Projeto projetoSelecionado;
	private List<Bean> projetosSelecionados;
	
	//Pessoa Jurídica
	private List<Bean> pessoasJuridicas;
	private PessoaJuridicaDAO pessoaJuridicaDAO;
	private PessoaJuridica pessoaJuridicaSelecionada;
	
	//Pessoa Física
	private List<Bean> pessoasFisicas;
	private PessoaFisicaDAO pessoaFisicaDAO;
	private PessoaFisica pessoaFisicaSelecionada;
	
	private boolean renderedPF;
	private boolean renderedPJ;
	
	
	//Construtor
	public ProjetoMB() {
		super();
		projetoDAO = new ProjetoDAO();
		this.novoProjeto();
		projetos = new ArrayList<Bean>();
		projetosParaReidiMB = new ArrayList<Bean>();
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		//this.findAllPessoasJuridicas();
		pessoaJuridicaSelecionada = new PessoaJuridica();
		pessoaFisicaDAO = new PessoaFisicaDAO();
		this.findAllPessoasFisicas();
		pessoaFisicaSelecionada = new PessoaFisica();
		renderedPF = false;
		renderedPJ = true;
		// TODO Auto-generated constructor stub
	}

	//Métodos Projeto
	public void addProjeto(){
		try {
			projetos.add(projeto);
			projetosParaReidiMB.add(projeto);
			this.novoProjeto();
			pessoaJuridicaSelecionada = new PessoaJuridica();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novoProjeto(){
		projeto = new Projeto();
		projeto.setPessoaJuridica(new PessoaJuridica());
		projetoSelecionado = new Projeto();
	}
	
    public void deleteProjeto() {
        projetos.remove(projetoSelecionado);
        projetosParaReidiMB.remove(projetoSelecionado);
        projetoSelecionado = null;
    }
    
    public void deleteProjetos() {
        projetos.removeAll(projetosSelecionados);
        projetosParaReidiMB.removeAll(projetosSelecionados);
        projetosSelecionados = new ArrayList<Bean>();
    }
    
	public void findAllBean(){
		projetos = projetoDAO.findAllBean();
		projetosParaReidiMB = projetos;
	}
	
	
	//Metodos Pessoa Jurídica
	public void findAllPessoasJuridicas(){
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoasJuridicas = pessoaJuridicaDAO.findAllBean();
	}
	
	public void selecionarPessoaJuridica(){
		projeto.setPessoaJuridica(pessoaJuridicaSelecionada);
	}
	
	public void salvarPessoaJurica(){
		if(pessoaFisicaSelecionada == null){
			FacesContext.getCurrentInstance().
       	   	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Pessoa Física deve ser selecionada!"));
			limparPJePF();
		}else{			
			if(pessoaJuridicaSelecionada == null){
				FacesContext.getCurrentInstance().
	          	   addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Pessoa Jurídica deve ser selecionada!"));
				limparPJePF();
			}else{
				selecionarPessoaJuridica();
			}
		}
	}
	
	public void clearFields(){
		projetos = new ArrayList<Bean>();
		projetosParaReidiMB = projetos;
		projetosSelecionados = new ArrayList<Bean>();
		projetoSelecionado = new Projeto();
	}
	
	private void limparPJePF(){
		pessoaFisicaSelecionada = new PessoaFisica();
		pessoaJuridicaSelecionada = new PessoaJuridica();
	}
	
	public void openTabPF(){
		renderedPF = true;
		renderedPJ = false;
	}
	
	public void closeTabPF(){
		renderedPF = false;
		renderedPJ = true;
	}
	
	//Metodos Pessoa Física
	private void findAllPessoasFisicas(){
		pessoasFisicas = pessoaFisicaDAO.findAllBean();
	}
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public ProjetoDAO getProjetoDAO() {
		return projetoDAO;
	}

	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}

	public List<Bean> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Bean> projetos) {
		this.projetos = projetos;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	public List<Bean> getProjetosSelecionados() {
		return projetosSelecionados;
	}

	public void setProjetosSelecionados(List<Bean> projetosSelecionados) {
		this.projetosSelecionados = projetosSelecionados;
	}

	public List<Bean> getPessoasJuridicas() {
		return pessoasJuridicas;
	}

	public void setPessoasJuridicas(List<Bean> pessoasJuridicas) {
		this.pessoasJuridicas = pessoasJuridicas;
	}

	public PessoaJuridicaDAO getPessoaJuridicaDAO() {
		return pessoaJuridicaDAO;
	}

	public void setPessoaJuridicaDAO(PessoaJuridicaDAO pessoaJuridicaDAO) {
		this.pessoaJuridicaDAO = pessoaJuridicaDAO;
	}

	public PessoaJuridica getPessoaJuridicaSelecionada() {
		return pessoaJuridicaSelecionada;
	}

	public void setPessoaJuridicaSelecionada(PessoaJuridica pessoaJuridicaSelecionada) {
		this.pessoaJuridicaSelecionada = pessoaJuridicaSelecionada;
	}

	public List<Bean> getPessoasFisicas() {
		return pessoasFisicas;
	}

	public void setPessoasFisicas(List<Bean> pessoasFisicas) {
		this.pessoasFisicas = pessoasFisicas;
	}

	public PessoaFisicaDAO getPessoaFisicaDAO() {
		return pessoaFisicaDAO;
	}

	public void setPessoaFisicaDAO(PessoaFisicaDAO pessoaFisicaDAO) {
		this.pessoaFisicaDAO = pessoaFisicaDAO;
	}

	public PessoaFisica getPessoaFisicaSelecionada() {
		return pessoaFisicaSelecionada;
	}

	public void setPessoaFisicaSelecionada(PessoaFisica pessoaFisicaSelecionada) {
		this.pessoaFisicaSelecionada = pessoaFisicaSelecionada;
	}

	public boolean isRenderedPF() {
		return renderedPF;
	}

	public void setRenderedPF(boolean renderedPF) {
		this.renderedPF = renderedPF;
	}

	public boolean isRenderedPJ() {
		return renderedPJ;
	}

	public void setRenderedPJ(boolean renderedPJ) {
		this.renderedPJ = renderedPJ;
	}	
	
	public static ProjetoMB getInstance() {
		return projetoMB;
	}

	public static List<Bean> getProjetosParaReidiMB() {
		return projetosParaReidiMB;
	}

	public static void setProjetosParaReidiMB(List<Bean> projetosParaReidiMB) {
		ProjetoMB.projetosParaReidiMB = projetosParaReidiMB;
	}
}
