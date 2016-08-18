package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conn {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager(){
		if(entityManagerFactory == null || entityManagerFactory.isOpen() == false)
			entityManagerFactory = Persistence.createEntityManagerFactory("sisginf_versao1");
		if(entityManager == null || entityManager.isOpen() == false)
			entityManager = entityManagerFactory.createEntityManager();
		return entityManager;		
	}
	
	public static void closeConn(EntityManager em){
		if(em != null && em.isOpen()==true){
			em.close();
			entityManager = null;
			entityManagerFactory = null;
		}
	}
}
