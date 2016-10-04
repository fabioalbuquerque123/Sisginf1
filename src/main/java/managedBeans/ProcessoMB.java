package managedBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 
import javax.faces.context.FacesContext;



import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.PessoaJuridica;
import beans.Processo;
import beans.Projeto;
import dataAcessObject.PessoaJuridicaDAO;
import dataAcessObject.ProcessoDAO;
import dataAcessObject.ProjetoDAO;
import util.EstadosBrasileiros;
import util.LocalizacaoProcesso;
import util.StatusProjeto;

@ManagedBean(name="ProcessoMB")
@SessionScoped
public class ProcessoMB implements Serializable{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 3485897920630680504L;
	private static ProcessoMB processoMB = new ProcessoMB();
	
	//Processo	
	private Processo processo;
	private ProcessoDAO processoDAO;
	private List<String> localizaoSituacaoProcesso;
	private List<Processo> processos;
	private Processo processoUpdate;
	
	//Projeto
	private ProjetoMB projetoMB;
	private ProjetoDAO projetoDAO;
	private Projeto projetoSelecionado;
	private Projeto projeto;
	private List<String> statusProjeto;
	
	//Pessoa Jurídica
	private List<Bean> pessoasJuridicas;
	private PessoaJuridicaDAO pessoaJuridicaDAO;
	private PessoaJuridica pessoaJuridicaSelecionada;
	
	//Estados
	private List<String> estados;

	public ProcessoMB() {
		super();
		try{						
			//Processo
			processo = new Processo();
			
			//Projeto
			projetoSelecionado = new Projeto();
			
			//Pessoa Juridica
			pessoaJuridicaDAO = new PessoaJuridicaDAO();
			pessoaJuridicaSelecionada = new PessoaJuridica();
			
			this.findAllReid();
			
			//Carregar array para selectOneMenu de localizacao do processo
			localizaoSituacaoProcesso = LocalizacaoProcesso.getLocalizaoSituacaoProcesso();
			
			//Carregar array para selectOneMenu de status do Projeto
			statusProjeto = StatusProjeto.getListStatusProjeto();
			
			//Carregar Estados
			estados = EstadosBrasileiros.getEstados();
			
			projetoMB = ProjetoMB.getInstance();
			
			projeto = new Projeto();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static ProcessoMB getInstance() {
		return processoMB;
	}
	
	//Metodos para processos
	@SuppressWarnings("static-access")
	public String inserirProcesso(){
		try{
			projetoMB = ProjetoMB.getInstance();			
			processoDAO = new ProcessoDAO();
			processo.setProjeto(projetoMB.getProjetosParaReidiMB());
			processoDAO.insertBean(processo);
			this.inserirProjetos();
			this.clearFields();
			projetoMB.clearFields();
			this.findAllReid();
			FacesContext.getCurrentInstance().
	       	 addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Processo inserido com sucesso!"));
			return "insertReidOK";
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().
	       	 addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Processo não inserido!"));
			return "insertReidFail";
		}
	}
	
	public void findAllReid(){
		try{
			processoDAO = new ProcessoDAO();
			processos = processoDAO.findAllReidi();
			for(Processo processo : processos){
				System.out.println(((Processo)processo).getNumeroApensoANTAQ());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteProcesso(){
		try{
			Processo processoTemp;
			processoDAO = new ProcessoDAO();
			processoTemp = processoDAO.findById(projetoSelecionado.getProcesso().getIdProcesso());
			processoDAO.deleteBean(processoTemp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Metodos para projeto
	
	@SuppressWarnings("static-access")
	private void inserirProjetos(){
		try {			
			projetoDAO = new ProjetoDAO();
			for(Bean projeto : projetoMB.getProjetosParaReidiMB()){
			((Projeto)projeto).setProcesso(processo);
				projetoDAO.insertBean(projeto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public String updateProjeto(){
		try{			
			projetoDAO = new ProjetoDAO();
			projetoDAO.updateBean(projetoSelecionado);
			findAllReid();
			projetoSelecionado = new Projeto();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "updateOK";
	}
	
	public void deleteProjeto(){
		try{	
			boolean flag = false;
			if(projetoSelecionado.getProcesso().getProjeto().size() == 1)
				flag = true;			
			projetoDAO = new ProjetoDAO();
			if(projetoSelecionado != null){
				projetoSelecionado = projetoDAO.findById(projetoSelecionado.getIdProjeto());
				projetoDAO.deleteBean(projetoSelecionado);
			}
			if(flag){
				this.deleteProcesso();
			}
				
			findAllReid();						
			projetoSelecionado = new Projeto();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//Metodos Pessoa Jurídica
	public void findAllPessoasJuridicas(){
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoasJuridicas = pessoaJuridicaDAO.findAllBean();
	}
	
	public void selecionarPessoaJuridica(){
		projetoSelecionado.setPessoaJuridica(pessoaJuridicaSelecionada);
	}
	
	public String novoReidi(){
		return "novoReidi";
	}
	
	public String logout(){
		return "logout";
	}
	
	public String homePage(){
		return "homePage";
	}
	
	private void loadProjeto(){
		if(projetoSelecionado.getProcesso().getProjeto().size() == 1){
			projeto = (Projeto) projetoSelecionado.getProcesso().getProjeto().get(0);
		}
		else{
			for(Bean projeto : projetoSelecionado.getProcesso().getProjeto()){				
				if(projetoSelecionado.equals((Projeto)projeto)){
					projeto = ((Projeto)projeto);
				}
			}
		}
			
		//projeto = projetoSelecionado;
	}
	public String alterarProjeto(){		
		//loadProjeto();
		processoUpdate = new Processo();
		processoUpdate = projetoSelecionado.getProcesso();
		return "updateProjeto";
	}
	
	private void clearFields(){
		processo = new Processo();
	}
	
	public void teste(){
		System.out.println("Teste");
	}


	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public DataAcessObject getProcessoDAO() {
		return processoDAO;
	}

	public ProjetoMB getProjetoMB() {
		return projetoMB;
	}

	public void setProjetoMB(ProjetoMB projetoMB) {
		this.projetoMB = projetoMB;
	}

	public ProjetoDAO getProjetoDAO() {
		return projetoDAO;
	}

	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}

	public void setProcessoDAO(ProcessoDAO processoDAO) {
		this.processoDAO = processoDAO;
	}

	public List<String> getLocalizaoSituacaoProcesso() {
		return localizaoSituacaoProcesso;
	}

	public void setLocalizaoSituacaoProcesso(List<String> localizaoSituacaoProcesso) {
		this.localizaoSituacaoProcesso = localizaoSituacaoProcesso;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}


	public Projeto getProjeto() {
		return projeto;
	}


	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	public List<String> getStatusProjeto() {
		return statusProjeto;
	}


	public void setStatusProjeto(List<String> statusProjeto) {
		this.statusProjeto = statusProjeto;
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

	public List<String> getEstados() {
		return estados;
	}

	public void setEstados(List<String> estados) {
		this.estados = estados;
	}

	public Processo getProcessoUpdate() {
		return processoUpdate;
	}

	public void setProcessoUpdate(Processo processoUpdate) {
		this.processoUpdate = processoUpdate;
	}
}
