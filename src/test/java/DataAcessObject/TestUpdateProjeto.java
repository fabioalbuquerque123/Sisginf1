package DataAcessObject;


import org.junit.Test;

import beans.Projeto;

import dataAcessObject.ProjetoDAO;
import junit.framework.TestCase;

public class TestUpdateProjeto extends TestCase{

	private ProjetoDAO projetoDAO;
	@SuppressWarnings("unused")
	private Projeto projeto;	
	
	@Test
	public void testAfindProjetoById(){
		try{
			projetoDAO = new ProjetoDAO();
			projeto = projetoDAO.findById(40);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
