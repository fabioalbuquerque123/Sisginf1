package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.Projeto;

@ManagedBean(name="ProjetoMB")
@ViewScoped
public class ProjetoMB {

	private Projeto projeto;

	public ProjetoMB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}
