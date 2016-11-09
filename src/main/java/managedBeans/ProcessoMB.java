package managedBeans;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.Municipio;
import beans.PessoaFisica;
import beans.PessoaJuridica;
import beans.Processo;
import beans.Projeto;
import dataAcessObject.MunicipioDAO;
import dataAcessObject.PessoaFisicaDAO;
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
	/*private ProjetoMB projetoMB;*/
	private ProjetoDAO projetoDAO;
	private Projeto projetoSelecionado;
	private Projeto projeto;
	private List<String> statusProjeto;	
	//Projeto ProjetoMB
	private List<Bean> projetos;
	private static List<Bean> projetosParaReidiMB;	
	private List<Bean> projetosSelecionados;
	
	//Pessoa Jurídica
	private List<Bean> pessoasJuridicas;
	private PessoaJuridicaDAO pessoaJuridicaDAO;
	private PessoaJuridica pessoaJuridicaSelecionada;	
	
	//Pessoa Física
	private List<Bean> pessoasFisicas;
	private PessoaFisicaDAO pessoaFisicaDAO;
	private PessoaFisica pessoaFisicaSelecionada;

	
	//Estados
	private List<String> estados;
	
	//Municipio
	private MunicipioDAO municipioDAO;
	private Municipio municipio;
	private List<String> listMunicipios;
	
	private boolean renderedPF;
	private boolean renderedPJ;
	private boolean messageProcesso;
	
	private List<Processo> filterProcessos;
	private List<Projeto> filterProjeto;
		
	public ProcessoMB() {
		super();
		try{						
			//Processo			
			processo = new Processo();
			
			//Projeto
			projetoSelecionado = new Projeto();
			projetoDAO = new ProjetoDAO();
			this.novoProjeto();
			projetos = new ArrayList<Bean>();
			projetosParaReidiMB = new ArrayList<Bean>();
			
			//Pessoa Juridica
			pessoaJuridicaDAO = new PessoaJuridicaDAO();
			pessoaJuridicaSelecionada = new PessoaJuridica();
			
			//Pessoa Fisica
			pessoaFisicaDAO = new PessoaFisicaDAO();
			this.findAllPessoasFisicas();
			pessoaFisicaSelecionada = new PessoaFisica();
			renderedPF = false;
			renderedPJ = true;
			
			this.findAllReid();
			
			//Carregar array para selectOneMenu de localizacao do processo
			localizaoSituacaoProcesso = LocalizacaoProcesso.getLocalizaoSituacaoProcesso();
			
			//Carregar array para selectOneMenu de status do Projeto
			statusProjeto = StatusProjeto.getListStatusProjeto();
			
			//Carregar Estados
			estados = EstadosBrasileiros.getEstados();
			
			//projetoMB = ProjetoMB.getInstance();
			
			projeto = new Projeto();							
			projeto.setProcesso(new Processo());
			projeto.setPessoaJuridica(new PessoaJuridica());
			
			listMunicipios = new ArrayList<String>();
			
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
	
/*	public String saveProcesso(){
		if(processo.getNumeroOriginalANTAQ().length()==0 || processo.getNumeroOriginalANTAQ() == null || projetosParaReidiMB.size() == 0){
			FacesContext context = FacesContext.getCurrentInstance();		         
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Favor preencher campo Nº original Antaq e verificar se há projeto inserido!","") );	
	        messageProcesso = true;
	        return "";
		}else{
			inserirProcesso();
		}
	}*/
	
	//Metodos para processos
	public String inserirProcesso(){
		try{				
			if(processo.getNumeroOriginalANTAQ().length()==0 || processo.getNumeroOriginalANTAQ() == null || projetosParaReidiMB.size() == 0){
				FacesContext context = FacesContext.getCurrentInstance();		         
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Favor preencher campo Nº original Antaq e verificar se há projeto inserido!","") );	
		        messageProcesso = true;
		        return "error";
			}else{
				//projetoMB = ProjetoMB.getInstance();			
				processoDAO = new ProcessoDAO();
				processo.setProjeto(this.getProjetosParaReidiMB());
				processoDAO.insertBean(processo);
				this.inserirProjetos();
				this.clearFields();
				this.clearFieldsProjeto();
				this.findAllReid();
				FacesContext.getCurrentInstance().
			    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Processo inserido com sucesso!"));
				return "insertReidOK";
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().
	       	 addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Processo não inserido!"));
			return "insertReidFail";
		}
	}
	
	
	public String updateProcesso(){
		try{
			if(processoSelecionado.getNumeroOriginalANTAQ().length()==0 || processoSelecionado.getNumeroOriginalANTAQ() == null){
				FacesContext context = FacesContext.getCurrentInstance();		         
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Favor preencher campo Nº original Antaq!","") );			        
		        return "error";
			}else{
				processoDAO = new ProcessoDAO();
				processoDAO.updateBean(processoSelecionado);
				findAllReid();
				processoSelecionado = new Processo();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "updateProcessoOK";		
	}
	
	public void findAllReid(){
		try{
			processoDAO = new ProcessoDAO();
			processos = processoDAO.findAllReidi();
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
	
	public void testar(TabChangeEvent event){		
		FacesContext context = FacesContext.getCurrentInstance();		
	}
	
	//Metodos para lista de projetos	
	@SuppressWarnings("static-access")
	private void inserirProjetos(){
		try {			
			projetoDAO = new ProjetoDAO();
			for(Bean projeto : this.getProjetosParaReidiMB()){
			((Projeto)projeto).setProcesso(processo);
				projetoDAO.insertBean(projeto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public String alterarProjeto(){
		municipioDAO = new MunicipioDAO();
		listMunicipios =  municipioDAO.findByUF(projetoSelecionado.getUf());
		return "updateProjeto";
	}
	
	public String updateProjeto(){
		try{			
			if(projetoSelecionado.getNome().length() == 0 || projetoSelecionado.getNome() == null ||	projetoSelecionado.getPessoaJuridica() == null ||  projetoSelecionado.getPessoaJuridica().getNomeEmpresarial() == null || projetoSelecionado.getPessoaJuridica().getNomeEmpresarial().length() < 1){
				FacesContext context = FacesContext.getCurrentInstance();		         
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Favor preencher campos obrigatórios (*) para o projeto!","") );
		        return "updateFail";
			}else{
				if(
					((projetoSelecionado.getEstimativaValBensCom() + projetoSelecionado.getEstimativaValOutrosCom() + projetoSelecionado.getEstimativaValServicosCom()) -
						(projetoSelecionado.getEstimativaValBensSem() + projetoSelecionado.getEstimativaValOutrosSem() + projetoSelecionado.getEstimativaValServicosSem())) != projetoSelecionado.getProjecaoImpactoFinal() 
							){
						RequestContext.getCurrentInstance().execute("PF('pjDialogIF').show()");
						return "updateFail";
					}else{
						return this.saveUpdateProjeto();
					}											
			}
		}catch(Exception e){
			e.printStackTrace();
			return "updateOK";
		}
		
	}
	
	public String saveUpdateProjeto() throws Exception{
		projetoDAO = new ProjetoDAO();
		projetoDAO.updateBean(projetoSelecionado);
		findAllReid();
		projetoSelecionado = new Projeto();
		return "updateOK";
	}
	
/*	public void deleteProjeto(){
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
	}*/
	
	//Métodos Projeto ProjetoMB
		public void addProjeto(){
			try {
				if(projeto.getNome().length() == 0 || projeto.getNome() == null ||	projeto.getPessoaJuridica() == null ||  projeto.getPessoaJuridica().getNomeEmpresarial() == null || projeto.getPessoaJuridica().getNomeEmpresarial().length() < 1){
					FacesContext context = FacesContext.getCurrentInstance();		         
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Favor preencher campos obrigatórios (*) para o projeto!","") );
			        messageProcesso = false;
				}else{										
					if(
						((projeto.getEstimativaValBensCom() + projeto.getEstimativaValOutrosCom() + projeto.getEstimativaValServicosCom()) -
						(projeto.getEstimativaValBensSem() + projeto.getEstimativaValOutrosSem() + projeto.getEstimativaValServicosSem())) != projeto.getProjecaoImpactoFinal() 
							){
						RequestContext.getCurrentInstance().execute("PF('pjDialogIF').show()");
					}else{
						this.insertProjeto();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insertProjeto(){
			projetos.add(projeto);
			projetosParaReidiMB.add(projeto);
			this.novoProjeto();
			pessoaJuridicaSelecionada = new PessoaJuridica();
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
	    
/*		public String updateProjeto(){
			try{
				this.selecionarPessoaJuridica();
				projetoDAO = new ProjetoDAO();
				projetoDAO.updateBean(this.projeto);
				this.clearFields();
			}catch(Exception e){
				e.printStackTrace();
			}
			return "updateOK";
		}*/
	    
		public void findAllBean(){
			projetos = projetoDAO.findAllBean();
			projetosParaReidiMB = projetos;
		}
	
		//Metodos Pessoa Jurídica
		public void selecionarPessoaJuridicaInicial(){
			projeto.setPessoaJuridica(pessoaJuridicaSelecionada);
		}
		
		public void selecionarPessoaJuridicaUpdate(){
			projetoSelecionado.setPessoaJuridica(pessoaJuridicaSelecionada);
		}
		
/*		public void salvarPessoaJurica(){
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
		}*/
		
		public void clearFieldsProjeto(){
			projeto = new Projeto();
			projeto.setPessoaJuridica(new PessoaJuridica());
			projetos = new ArrayList<Bean>();
			projetosParaReidiMB = projetos;
			projetosSelecionados = new ArrayList<Bean>();
			projetoSelecionado = new Projeto();
			listMunicipios = new ArrayList<String>();
		}
		
		public void novoProjeto(){
			projeto = new Projeto();
			projeto.setPessoaJuridica(new PessoaJuridica());
			projetoSelecionado = new Projeto();
			listMunicipios = new ArrayList<String>();
		}
		
		public void openTabPF(){
			renderedPF = true;
			renderedPJ = false;
		}
		
		public void closeTabPF(){
			renderedPF = false;
			renderedPJ = true;
		}
		
		private void limparPJePF(){
			pessoaFisicaSelecionada = new PessoaFisica();
			pessoaJuridicaSelecionada = new PessoaJuridica();
		}
	//Metodos Pessoa Jurídica
	public void findAllPessoasJuridicas(){
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoasJuridicas = pessoaJuridicaDAO.findAllBean();
	}
	
/*	public void selecionarPessoaJuridica(){
		projetoSelecionado.setPessoaJuridica(pessoaJuridicaSelecionada);
	}*/
	
	//Metodos Pessoa Física
		private void findAllPessoasFisicas(){
			pessoasFisicas = pessoaFisicaDAO.findAllBean();
		}
		
		//Metodos para estados e municipios
/*		public void carregarMunicipios(AjaxBehaviorEvent event){
			try{
				SelectOneMenu selectOneMenu = (SelectOneMenu) event.getSource();	
				String ufTela = (String) selectOneMenu.getValue();
				municipioDAO = new MunicipioDAO();
				listMunicipios =  municipioDAO.findByUF(ufTela);
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
	
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
					path);*/					
			
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
	
	public String alterarProcesso(){	
		return "updateProcesso";
	}
	
	private void clearFields(){
		processo = new Processo();
		projetosParaReidiMB = new ArrayList<>();
		projetosSelecionados = new ArrayList<>();
		projetos = new ArrayList<>();
		projeto = new Projeto();
		listMunicipios = new ArrayList<>();
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

/*	public ProjetoMB getProjetoMB() {
		return projetoMB;
	}

	public void setProjetoMB(ProjetoMB projetoMB) {
		this.projetoMB = projetoMB;
	}*/

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

	public List<Bean> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Bean> projetos) {
		this.projetos = projetos;
	}

	public static List<Bean> getProjetosParaReidiMB() {
		return projetosParaReidiMB;
	}

	public static void setProjetosParaReidiMB(List<Bean> projetosParaReidiMB) {
		ProcessoMB.projetosParaReidiMB = projetosParaReidiMB;
	}

	public List<Bean> getProjetosSelecionados() {
		return projetosSelecionados;
	}

	public void setProjetosSelecionados(List<Bean> projetosSelecionados) {
		this.projetosSelecionados = projetosSelecionados;
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

	public boolean isMessageProcesso() {
		return messageProcesso;
	}

	public void setMessageProcesso(boolean messageProcesso) {
		this.messageProcesso = messageProcesso;
	}

	public List<Processo> getFilterProcessos() {
		return filterProcessos;
	}

	public void setFilterProcessos(List<Processo> filterProcessos) {
		this.filterProcessos = filterProcessos;
	}

	public List<Projeto> getFilterProjeto() {
		return filterProjeto;
	}

	public void setFilterProjeto(List<Projeto> filterProjeto) {
		this.filterProjeto = filterProjeto;
	}
}
