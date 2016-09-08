package dataAcessObject;

import java.io.Serializable;
import java.util.List;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import util.Conn;

public class PessoaFisicaDAO extends DataAcessObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1552948183711471049L;
	private List<Bean> list;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bean> findAllBean() {
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select u from PessoaFisica u").getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Bean> findAllContador(){
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select c from PessoaFisica c where c.tipo like 'C%' ").getResultList();					
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Bean> findAllResp(){
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select c from PessoaFisica c where c.tipo like 'R%' ").getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;	
	}
}
