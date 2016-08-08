package arquitetura;

import java.util.List;

import javax.persistence.EntityManager;
import util.Conn;

public abstract class DataAcessObject {

	protected EntityManager em;
	
	public void insertBean(Bean bean) throws Exception{
		try{
			em = Conn.getEntityManager();
			em.getTransaction().begin();
			em.persist(bean);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			Conn.closeConn(em);
		}		
	}
	
	public void updateBean(Bean bean) throws Exception{
		try{
			em = Conn.getEntityManager();
			em.getTransaction().begin();
			em.merge(bean);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			Conn.closeConn(em);
		}		
	}
	
	public void deleteBean(Bean bean){
		try{
			em = Conn.getEntityManager();
			em.getTransaction().begin();
			em.remove(bean);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			Conn.closeConn(em);
		}		
	}
	
	public abstract List<Bean> findAllBean();

	
}
