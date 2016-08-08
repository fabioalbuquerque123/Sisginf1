package dataAcessObject;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.Usuario;
import util.Conn;

public class UsuarioDAO extends DataAcessObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6219250800366903449L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Bean> findAllBean() {
		super.em = Conn.getEntityManager();
		Query query = em.createQuery("select u from Usuario u");
		return query.getResultList();
	}
}
