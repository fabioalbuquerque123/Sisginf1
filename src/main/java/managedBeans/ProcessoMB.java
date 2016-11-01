package managedBeans;


import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.selectonemenu.SelectOneMenu;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.Municipio;
import beans.PessoaJuridica;
import beans.Processo;
import beans.Projeto;
import dataAcessObject.MunicipioDAO;
import dataAcessObject.PessoaJuridicaDAO;
import dataAcessObject.ProcessoDAO;
import dataAcessObject.ProjetoDAO;
import util.EstadosBrasileiros;
import util.LocalizacaoProcesso;
import util.Portaria;
import util.StatusProjeto;

@ManagedBean(name="ProcessoMB")
@SessionScoped
public class ProcessoMB extends HttpServlet implements Serializable{
	
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
	private Processo processoSelecionado;
	
	//Projeto
	private ProjetoMB projetoMB;
	private ProjetoDAO projetoDAO;
	private Projeto projetoSelecionado;
	private Projeto projeto;
	private List<String> statusProjeto;
	
	//Pessoa Jur�dica
	private List<Bean> pessoasJuridicas;
	private PessoaJuridicaDAO pessoaJuridicaDAO;
	private PessoaJuridica pessoaJuridicaSelecionada;	
	
	//Estados
	private List<String> estados;
	
	//Municipio
	private MunicipioDAO municipioDAO;
	private Municipio municipio;
	private List<String> listMunicipios;
		
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
	
	//Metodos para estados e municipios
	public void carregarMunicipios(AjaxBehaviorEvent event){
		try{
			SelectOneMenu selectOneMenu = (SelectOneMenu) event.getSource();	
			String ufTela = (String) selectOneMenu.getValue();
			municipioDAO = new MunicipioDAO();
			listMunicipios =  municipioDAO.findByUF(ufTela);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Metodos para processos
	public String inserirProcesso(){
		try{
			if(processo.getNumeroOriginalANTAQ().length()==0 || processo.getNumeroOriginalANTAQ() == null){
				FacesContext.getCurrentInstance().
	        	 addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Campos!", ""));
				return "insertReidFail";
			}else{
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
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().
	       	 addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Processo n�o inserido!"));
			return "insertReidFail";
		}
	}
	
	
	public String updateProcesso(){
		try{
			processoDAO = new ProcessoDAO();
			processoDAO.updateBean(processoSelecionado);
			findAllReid();
			processoSelecionado = new Processo();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "updateProcessoOK";		
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
	
	
	//Metodos Pessoa Jur�dica
	public void findAllPessoasJuridicas(){
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoasJuridicas = pessoaJuridicaDAO.findAllBean();
	}
	
	public void selecionarPessoaJuridica(){
		projetoSelecionado.setPessoaJuridica(pessoaJuridicaSelecionada);
	}
	
	//Metodo para gerar o relatorio
	public void gerarPortaria(){
		try{
			FacesContext context = FacesContext.getCurrentInstance(); 
			HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();			
			String path = context.getExternalContext().getRealPath("/");
			ServletOutputStream saida = response.getOutputStream();		
			response.sendRedirect("portaria.pdf");
			String str = Portaria.gerarPortaria(
					projetoSelecionado.getIdProjeto(),
					"ID_PROJETO","C:\\Users\\luizhoa\\apache-tomcat-7.0.70\\webapps\\relatorios\\minuta_portaria_last.jrxml",
					saida,
					path);					
/*			String str = Portaria.gerarPortaria(
					projetoSelecionado.getIdProjeto(),
					"ID_PROJETO","C:\\apache-tomcat-7.0.72\\webapps\\relatorios\\minuta_portaria_last.jrxml",
					saida,
					path);					
*/			
			context.responseComplete();					

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public String novoReidi(){
		return "novoReidi";
	}
	
	public String logout(){
		return "logout";
	}
	
	public String homePage(){
		clearFields();
		return "homePage";
	}	
	
	public String alterarProjeto(){
		municipioDAO = new MunicipioDAO();
		listMunicipios =  municipioDAO.findByUF(projetoSelecionado.getUf());
		return "updateProjeto";
	}
	
	public String alterarProcesso(){	
		return "updateProcesso";
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

	public Processo getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(Processo processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public MunicipioDAO getMunicipioDAO() {
		return municipioDAO;
	}

	public void setMunicipioDAO(MunicipioDAO municipioDAO) {
		this.municipioDAO = municipioDAO;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<String> getListMunicipios() {
		return listMunicipios;
	}

	public void setListMunicipios(List<String> listMunicipios) {
		this.listMunicipios = listMunicipios;
	}		

}
