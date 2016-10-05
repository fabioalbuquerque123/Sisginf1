package DataAcessObject;

import java.util.List;

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
	private List<String> list;
	
	
	/*public void testAinsertBean(){
		try{
			dao = new MunicipioDAO();
			municipio = LoadBean.getMunicipio();
			dao.insertBean(municipio);
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testBFindByUF(){
		try{
			dao = new MunicipioDAO();
			municipio = LoadBean.getMunicipio();
			list = ((MunicipioDAO)dao).findByUF("DF");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
/*	public void testCdeleteBean(){
		try{
			dao = new MunicipioDAO();
			municipio = LoadBean.getMunicipio();
			municipio = (Municipio) (dao.findAllBean()).get(0);
			dao.deleteBean(municipio);
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	
}
