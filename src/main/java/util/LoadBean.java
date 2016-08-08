package util;

import beans.Usuario;

public class LoadBean {

	private static Usuario usuario;

	public static Usuario getUsuario() {
		usuario = new Usuario();
		usuario.setNome("Usuario Teste");
		usuario.setLogin("usuarioTeste");
		usuario.setEmail("usuarioTeste@portosdobrasil.gov.br");
		usuario.setSenha("123");
		return usuario;
	}
	
	
}
