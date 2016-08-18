package dataAcessObject;

import java.io.Serializable;
import java.util.List;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.Usuario;
import util.Conn;

public class UsuarioDAO extends DataAcessObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6219250800366903449L;
	private Usuario usuario;
	private List<Bean> list;

	@SuppressWarnings("unchecked")
	@Override
	public List<Bean> findAllBean() {
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select u from Usuario u").getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public Usuario findUsuarioLogin(String nomeUsuario, String senha) {
		try{
			super.em = Conn.getEntityManager();
			usuario = (Usuario) super.em.createQuery(
				"SELECT u from Usuario u where u.nome = :name and u.senha = :senha")
                .setParameter("name", nomeUsuario)
                .setParameter("senha", senha).getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return usuario;
  }
}
