package util;

import beans.Usuario;

public class LoadBean {

	private static Usuario usuario;

	public static Usuario getUsuario() {
		usuario = new Usuario();
		usuario.setNome("admin");
		usuario.setLogin("admin");
		usuario.setEmail("admin@portosdobrasil.gov.br");
		usuario.setSenha("123");
		return usuario;
	}
	
	
}
