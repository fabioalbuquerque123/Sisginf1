package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import arquitetura.Bean;
import arquitetura.DataAcessObject;
import beans.PessoaJuridica;
import dataAcessObject.PessoaJuridicaDAO;

@ManagedBean(name="PessoaJuridicaMB")
@ViewScoped
public class PessoaJuridicaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3379473621951882479L;
	private PessoaJuridica pessoaJuridica;
	private DataAcessObject pessoaJuridicaDAO;
	private List<Bean> pessoasJuridicas;
	public PessoaJuridicaMB() {
		super();
		pessoaJuridica = new PessoaJuridica();
		pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoasJuridicas = new ArrayList<Bean>();
		this.findAllBean();
		// TODO Auto-generated constructor stub
	}

	public void findAllBean(){
		try{
			pessoasJuridicas = pessoaJuridicaDAO.findAllBean();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public DataAcessObject getPessoaJuridicaDAO() {
		return pessoaJuridicaDAO;
	}

	public void setPessoaJuridicaDAO(DataAcessObject pessoaJuridicaDAO) {
		this.pessoaJuridicaDAO = pessoaJuridicaDAO;
	}

	public List<Bean> getPessoasJuridicas() {
		return pessoasJuridicas;
	}

	public void setPessoasJuridicas(List<Bean> pessoasJuridicas) {
		this.pessoasJuridicas = pessoasJuridicas;
	}
}
