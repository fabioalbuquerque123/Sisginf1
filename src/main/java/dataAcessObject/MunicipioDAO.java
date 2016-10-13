package dataAcessObject;

import java.util.List;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import util.Conn;

public class MunicipioDAO extends DataAcessObject{

	private List<Bean> list;
	private List<String> listMunicipios;
	
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
	
	@SuppressWarnings("unchecked")
	public List<String> findByUF(String uf){
		try{
			super.em = Conn.getEntityManager();
			listMunicipios = super.em.createQuery("select m.municipio from Municipio m where m.uf = :uf").setParameter("uf", uf).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return listMunicipios;
	}

}
