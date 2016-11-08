package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.UploadedFile;

import arquitetura.Bean;
import beans.Municipio;
import beans.PessoaFisica;
import beans.PessoaJuridica;
import beans.Processo;
import beans.Projeto;
import dataAcessObject.MunicipioDAO;
import dataAcessObject.PessoaFisicaDAO;
import dataAcessObject.PessoaJuridicaDAO;
import dataAcessObject.ProjetoDAO;
import util.EstadosBrasileiros;
import util.LocalizacaoProcesso;
import util.StatusProjeto;

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
	private List<String> statusProjeto;
	
	//File
	private UploadedFile uploadedFile;
	
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
	
	//Estados
	private List<String> estados;
	private List<String> localizaoSituacaoProcesso;
	
	//Municipio
	private MunicipioDAO municipioDAO;
	private Municipio municipio;
	private List<String> listMunicipios;
	
	//ProcessoMB
	private ProcessoMB processoMB;
	
	//Construtor
	public ProjetoMB() {
		super();
		
		//Projeto
		projetoDAO = new ProjetoDAO();
		//this.novoProjeto();
		projetos = new ArrayList<Bean>();
		projetosParaReidiMB = new ArrayList<Bean>();
		
		//Pessoa Juridica
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		//this.findAllPessoasJuridicas();
		pessoaJuridicaSelecionada = new PessoaJuridica();
		
		//Pessoa Fisica
		pessoaFisicaDAO = new PessoaFisicaDAO();
		//this.findAllPessoasFisicas();
		pessoaFisicaSelecionada = new PessoaFisica();
		renderedPF = false;
		renderedPJ = true;
		
		//Carregar array para selectOneMenu de estados
		estados = EstadosBrasileiros.getEstados();
		
		//Carregar array para selectOneMenu de status do Projeto
		statusProjeto = StatusProjeto.getListStatusProjeto();
		
		//Carregar array para selectOneMenu de localizacao do processo
		localizaoSituacaoProcesso = LocalizacaoProcesso.getLocalizaoSituacaoProcesso();
		
		//ProcessoMB
		processoMB = ProcessoMB.getInstance();
		
		projeto = new Projeto();
		projeto.setProcesso(new Processo());
		projeto.setPessoaJuridica(new PessoaJuridica());
		//projeto = processoMB.getProjetoSelecionado();
		
		listMunicipios = new ArrayList<String>();
	}

	
	
	//Metodo Upload Portaria
	public void upload() {
        if(this.uploadedFile != null) {
            FacesMessage message = new FacesMessage("Upload realizado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.getProjeto().setPathPortaria(this.uploadedFile.getFileName());
        }
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

	public List<String> getStatusProjeto() {
		return statusProjeto;
	}

	public void setStatusProjeto(List<String> statusProjeto) {
		this.statusProjeto = statusProjeto;
	}

	public List<String> getEstados() {
		return estados;
	}

	public void setEstados(List<String> estados) {
		this.estados = estados;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	//Novos metodos
	public List<String> getLocalizaoSituacaoProcesso() {
		return localizaoSituacaoProcesso;
	}

	public void setLocalizaoSituacaoProcesso(List<String> localizaoSituacaoProcesso) {
		this.localizaoSituacaoProcesso = localizaoSituacaoProcesso;
	}

	public ProcessoMB getProcessoMB() {
		return processoMB;
	}

	public void setProcessoMB(ProcessoMB processoMB) {
		this.processoMB = processoMB;
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
