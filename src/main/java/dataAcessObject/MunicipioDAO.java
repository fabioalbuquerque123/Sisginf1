package dataAcessObject;

import java.util.List;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.Municipio;
import util.Conn;

public class MunicipioDAO extends DataAcessObject{

	private List<Bean> list;
	private Municipio municipio;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bean> findAllBean() {
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select u from Municipio u").getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public Municipio findByUF(String uf){
		try{
			super.em = Conn.getEntityManager();
			municipio = (Municipio) super.em.createQuery("select m from Municipio m where m.uf = :uf").setParameter("uf", uf).getSingleResult();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return municipio;
	}

}
