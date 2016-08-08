package DataAcessObject;

import javax.persistence.Query;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.Usuario;
import dataAcessObject.UsuarioDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUsuarioDAO extends TestCase{

	private DataAcessObject dao;
	private Usuario usuario;
	
	@Test
	public void testAinsertBean(){
		try{
			dao = new UsuarioDAO();
			usuario = LoadBean.getUsuario();
			dao.insertBean(usuario);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBupdateBean(){
		try{
			dao = new UsuarioDAO();
			usuario = (Usuario) dao.findAllBean().get(0);
			usuario.setNome("Usuario Atualizado");
			dao.updateBean(usuario);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testCdeleteBean(){
		try{
			dao = new UsuarioDAO();
			usuario = (Usuario) dao.findAllBean().get(0);
			dao.deleteBean(usuario);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
