package DataAcessObject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import arquitetura.DataAcessObject;
import beans.Municipio;
import dataAcessObject.MunicipioDAO;
import junit.framework.TestCase;
import util.LoadBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMunicipioDAO extends TestCase{
	
	private DataAcessObject dao;
	private Municipio municipio;
	
	@Test
	public void testAinsertBean(){
		try{
			dao = new MunicipioDAO();
			municipio = LoadBean.getMunicipio();
			dao.insertBean(municipio);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testBFindByUF(){
		try{
			dao = new MunicipioDAO();
			municipio = LoadBean.getMunicipio();
			municipio = ((MunicipioDAO)dao).findByUF(municipio.getUf());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCdeleteBean(){
		try{
			dao = new MunicipioDAO();
			municipio = LoadBean.getMunicipio();
			municipio = (Municipio) (dao.findAllBean()).get(0);
			dao.deleteBean(municipio);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
