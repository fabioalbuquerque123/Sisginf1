package util;


import java.util.ArrayList;
import java.util.Date;

import beans.Processo;
import beans.Projeto;
import beans.Usuario;

public class LoadBean {

	private static Usuario usuario;
	private static Processo processo;
	private static Projeto projeto;

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
		processo.setProjeto(new ArrayList<>());
		return processo;
	}

	public static Projeto getProjeto(){
		projeto = new Projeto();
		projeto.setNome("Projeto Teste");
		return projeto;		
	}
}
