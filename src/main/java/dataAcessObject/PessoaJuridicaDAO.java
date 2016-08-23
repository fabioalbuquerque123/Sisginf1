package dataAcessObject;

import java.io.Serializable;
import java.util.List;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import util.Conn;

public class PessoaJuridicaDAO extends DataAcessObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2828293812432330861L;
	private List<Bean> list;

	@SuppressWarnings("unchecked")
	@Override
	public List<Bean> findAllBean() {
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select u from PessoaJuridica u").getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
