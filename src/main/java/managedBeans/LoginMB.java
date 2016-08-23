package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.Usuario;
import dataAcessObject.UsuarioDAO;

@ManagedBean(name="LoginMB")
@ViewScoped
public class LoginMB{

	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private String mensagem;
	private boolean mensagemRendered;
	
	 public LoginMB() {
		super();
		try{
			usuario = new Usuario();
			usuarioDAO = new UsuarioDAO();
			mensagemRendered = false;
			mensagem = "";
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String envia() {
         
         usuario = usuarioDAO.findUsuarioLogin(usuario.getNome(), usuario.getSenha());
         if (usuario == null) {
        	 mensagem = "Usuario ou senha inv�lidos!";
        	 return "loginOk";
         } else {               
               mensagem = "Login efetuado com sucesso!";
               return "loginOk";
         }   
   }
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isMensagemRendered() {
		return mensagemRendered;
	}

	public void setMensagemRendered(boolean mensagemRendered) {
		this.mensagemRendered = mensagemRendered;
	}
	
}