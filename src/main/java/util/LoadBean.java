package util;


import java.util.ArrayList;
import java.util.Date;

import arquitetura.Bean;
import beans.ContadorPJ;
import beans.Municipio;
import beans.PessoaJuridica;
import beans.Processo;
import beans.Projeto;
import beans.ResponsavelLegalPJ;
import beans.Usuario;

public class LoadBean {

	private static Usuario usuario;
	private static Processo processo;
	private static Projeto projeto;
	private static ResponsavelLegalPJ responsavelLegalPJ;
	private static ContadorPJ contadorPJ;
	private static PessoaJuridica pessoaJuridica;
	private static Municipio municipio;

	public static Usuario getUsuario() {
		usuario = new Usuario();
		usuario.setNome("admin");
		usuario.setLogin("admin");
		usuario.setEmail("admin@portosdobrasil.gov.br");
		usuario.setSenha("123");
		return usuario;
	}
	
	public static Processo getProcesso() {
		processo = new Processo();
		processo.setDataProtocoloSEP(new Date());
		processo.setLocalizacao("Localizacao Teste");
		processo.setNumeroApensoANTAQ("Numero Apenso Antaq");
		processo.setNumeroOriginalANTAQ("Num Original Antaq");
		processo.setNumeroProcessoSEP("Numero Processo SEP");
		processo.setProjeto(new ArrayList<Bean>());
		return processo;
	}

	public static Projeto getProjeto(){
		projeto = new Projeto();
		projeto.setNome("Projeto Teste");
		return projeto;		
	}
	
	public static ResponsavelLegalPJ getResponsavelLegalPJ(){
		responsavelLegalPJ = new ResponsavelLegalPJ();
		responsavelLegalPJ.setCpf("123");
		responsavelLegalPJ.setEmail("teste@teste");
		responsavelLegalPJ.setNome("Responsavel Teste");
		responsavelLegalPJ.setTelefones("123456");
		responsavelLegalPJ.setTipo("RESP");
		return responsavelLegalPJ;		
	}
	
	public static ContadorPJ getContadorPJ(){
		contadorPJ = new ContadorPJ();
		contadorPJ.setCpf("123");
		contadorPJ.setEmail("teste@teste");
		contadorPJ.setNome("Contador Teste");
		contadorPJ.setTelefones("123456");
		contadorPJ.setTipo("CONT");
		return contadorPJ;		
	}
	
	public static PessoaJuridica getPessoaJuridica(){
		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setNomeEmpresarial("Empresa Teste");
		return pessoaJuridica;		
	}
	
	public static Municipio getMunicipio(){
		municipio = new Municipio();
		municipio.setCodigo(10001);
		municipio.setMunicipio("Municipio Teste");
		municipio.setUf("UF");
		return municipio;
	}
}
