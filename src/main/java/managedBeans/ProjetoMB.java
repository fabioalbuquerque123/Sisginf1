package managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import arquitetura.Bean;
import beans.PessoaJuridica;
import beans.Projeto;
import dataAcessObject.ProjetoDAO;

@ManagedBean(name="ProjetoMB")
@ViewScoped
public class ProjetoMB {

	private Projeto projeto;
	private ProjetoDAO projetoDAO;
	private List<Bean> projetos;
	private Projeto projetoSelecionado;
	private List<Bean> projetosSelecionados;
	public ProjetoMB() {
		super();
		projetoDAO = new ProjetoDAO();
		this.novoProjeto();
		projetos = new ArrayList<Bean>();
		// TODO Auto-generated constructor stub
	}

	public void addProjeto(){
		try {
			projetos.add(projeto);
			this.novoProjeto();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novoProjeto(){
		projeto = new Projeto();
		projeto.setPessoaJuridica(new PessoaJuridica());
	}
	
    public void deleteProjeto() {
        projetos.remove(projetoSelecionado);
        projetoSelecionado = null;
    }
    
    public void deleteProjetos() {
        projetos.removeAll(projetosSelecionados);
        projetosSelecionados = new ArrayList<Bean>();
    }
    
	public void findAllBean(){
		projetos = projetoDAO.findAllBean();
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
}
