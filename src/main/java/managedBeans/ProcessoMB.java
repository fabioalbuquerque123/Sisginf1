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
import beans.Processo;
import beans.Projeto;
import dataAcessObject.ProcessoDAO;
import dataAcessObject.ProjetoDAO;

@ManagedBean(name="ProcessoMB")
@ViewScoped
public class ProcessoMB implements Serializable{
	
	/**
	 * 
	 */
	//Processo
	private static final long serialVersionUID = 3485897920630680504L;
	private Processo processo;
	private ProcessoDAO processoDAO;
	private List<String> localizaoSituacaoProcesso;
	private List<Processo> processos;
	
	//Projeto
	private ProjetoMB projetoMB;
	private ProjetoDAO projetoDAO;
	
	public ProcessoMB() {
		super();
		processo = new Processo();
		this.loadSituacaoLocalizaoProcesso();
		this.findAllReid();
	}
	
	public String inserirProcesso(){
		try{
			processoDAO = new ProcessoDAO();
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
	
	
	@SuppressWarnings("static-access")
	private void inserirProjetos(){
		projetoMB = ProjetoMB.getInstance();
		projetoDAO = new ProjetoDAO();
		for(Bean projeto : projetoMB.getProjetosParaReidiMB()){
			((Projeto)projeto).setProcesso(processo);
			try {
				projetoDAO.insertBean(projeto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void loadSituacaoLocalizaoProcesso(){
		localizaoSituacaoProcesso = new ArrayList<String>();
		localizaoSituacaoProcesso.add("");
		localizaoSituacaoProcesso.add("Arquivado no DGLP");
		localizaoSituacaoProcesso.add("Em análise pela CGDP/DGLP");
		localizaoSituacaoProcesso.add("Encaminhado para ASSJUR");
		localizaoSituacaoProcesso.add("Com pendências após análise da ASSJUR");
		localizaoSituacaoProcesso.add("Aguardando chancela da portaria pela ASSJUR");
		localizaoSituacaoProcesso.add("Aguardando assinatura da portaira pelo Ministro");
		localizaoSituacaoProcesso.add("Enviado à Antaq");		
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
}
