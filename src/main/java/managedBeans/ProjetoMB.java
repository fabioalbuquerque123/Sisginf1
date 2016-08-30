package managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import arquitetura.Bean;
import beans.Projeto;
import dataAcessObject.ProjetoDAO;

@ManagedBean(name="ProjetoMB")
@ViewScoped
public class ProjetoMB {

	private Projeto projeto;
	private ProjetoDAO projetoDAO;
	private List<Bean> projetos;

	public ProjetoMB() {
		super();
		projetoDAO = new ProjetoDAO();
		projeto = new Projeto();
		projetos = new ArrayList<Bean>();
		this.findAllBean();
		// TODO Auto-generated constructor stub
	}

	public void insertBean(){
		try {
			projetoDAO.insertBean(projeto);
			projeto = new Projeto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.findAllBean();
		}
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
}
