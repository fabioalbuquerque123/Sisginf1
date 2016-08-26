package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dataAcessObject.ProcessoDAO;

@ManagedBean(name="ReidiMB")
@ViewScoped
public class ReidiMB {
	
	private ProcessoMB processoMB;
	private ProcessoDAO processoDAO;
	
	public ReidiMB() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	public void regra(){
		inserirProcesso();
	}
	
	private void inserirProcesso(){
		try{
			processoMB = ProcessoMB.getInstance();
			processoDAO = new ProcessoDAO();
			processoDAO.insertBean(processoMB.getProcesso());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public ProcessoMB getProcessoMB() {
		return processoMB;
	}

	public void setProcessoMB(ProcessoMB processoMB) {
		this.processoMB = processoMB;
	}

	public ProcessoDAO getProcessoDAO() {
		return processoDAO;
	}

	public void setProcessoDAO(ProcessoDAO processoDAO) {
		this.processoDAO = processoDAO;
	}
}
