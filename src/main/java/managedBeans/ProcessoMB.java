package managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import arquitetura.DataAcessObject;
import beans.Processo;

@ManagedBean(name="ProcessoMB")
@SessionScoped
public class ProcessoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3485897920630680504L;
	private static Processo processo;
	private DataAcessObject processoDAO;
	private static ProcessoMB processoMB = new ProcessoMB();
	
	public ProcessoMB() {
		super();
		processo = new Processo();
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		ProcessoMB.processo = processo;
	}

	public DataAcessObject getProcessoDAO() {
		return processoDAO;
	}

	public void setProcessoDAO(DataAcessObject processoDAO) {
		this.processoDAO = processoDAO;
	}
	
	public static ProcessoMB getInstance() {
		return processoMB;
	}
	
	

}
