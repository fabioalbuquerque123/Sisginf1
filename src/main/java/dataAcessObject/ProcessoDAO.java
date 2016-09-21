package dataAcessObject;

import java.io.Serializable;
import java.util.List;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.Processo;
import util.Conn;

public class ProcessoDAO extends DataAcessObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5021541415576158479L;
	private List<Bean> list;
	private Processo processo;	

	@SuppressWarnings("unchecked")
	@Override
	public List<Bean> findAllBean() {
		try{
			super.em = Conn.getEntityManager();
			list = super.em.createQuery("select u from Processo u").getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public Processo findByNumeroProcessoSEP(String numeroProcessoSEP){
		try{
			super.em = Conn.getEntityManager();
			processo = (Processo) super.em.createQuery("select c from Processo c where c.numeroProcessoSEP = :numeroProcesso").setParameter("numeroProcesso", numeroProcessoSEP).getSingleResult();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return processo;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Processo> findAllReidi(){
		try{
			super.em = Conn.getEntityManager();
			//Já retorna a lista de processos
			return super.em.createQuery("select distinct processo from Processo processo "
											+ "inner join fetch processo.projeto as projeto").getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		

	}
}
