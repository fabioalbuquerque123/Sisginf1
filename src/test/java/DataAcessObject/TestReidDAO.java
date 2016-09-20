package DataAcessObject;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import beans.Processo;
import dataAcessObject.ProcessoDAO;
import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestReidDAO extends TestCase{
	
	private ProcessoDAO processoDAO;
	private Processo processo;
	private List<Processo> list;
	
	@Test
	public void testAinsertBean(){
		try{
			processoDAO = new ProcessoDAO();
			list = processoDAO.findAllReidi();
			for(Processo processo : list){
				System.out.println(processo.getNumeroApensoANTAQ());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public ProcessoDAO getProcessoDAO() {
		return processoDAO;
	}

	public void setProcessoDAO(ProcessoDAO processoDAO) {
		this.processoDAO = processoDAO;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Processo> getList() {
		return list;
	}

	public void setList(List<Processo> list) {
		this.list = list;
	}

}
