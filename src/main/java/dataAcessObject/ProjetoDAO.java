package dataAcessObject;

import java.io.Serializable;
import java.util.List;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.Projeto;
import util.Conn;

public class ProjetoDAO extends DataAcessObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7339042436820482350L;
	private List<Bean> list;
	private Projeto projeto;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bean> findAllBean() {
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select u from Projeto u").getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public Projeto findById(int idProjeto){
		try{
			super.em = Conn.getEntityManager();
			projeto = (Projeto) super.em.createQuery("select c from Projeto c where c.idProjeto = :idProjeto").setParameter("idProjeto", idProjeto).getSingleResult();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return projeto;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Bean> findToReport(int idProjeto){
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery(
							"select projeto "+
							"from Projeto projeto "+
							"inner join fetch projeto.processo as processo "+
							"inner join fetch projeto.pessoaJuridica as pessoaJuridica where projeto.idProjeto = :idProjeto").setParameter("idProjeto", idProjeto).getResultList();					
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
