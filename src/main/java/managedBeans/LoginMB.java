package managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Usuario;
import dataAcessObject.UsuarioDAO;

@ManagedBean(name="LoginMB")
@ViewScoped
public class LoginMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3892584845488867801L;
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
        	 FacesContext.getCurrentInstance().
        	 addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nome de us�ario ou senha inv�lidos!", ""));
        	 usuario = new Usuario();
        	 return "loginFail";
         } else {               
        	   FacesContext.getCurrentInstance().
          	   addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Login efetuado com sucesso!"));
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
