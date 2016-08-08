package beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Bean;


@Entity
@Table(name="tb_usuario")
@SequenceGenerator(name="SEQ_USER", sequenceName="SEQ_USER_ID", initialValue=1, allocationSize=1)
public class Usuario extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2779241966179182307L;
	
	@Id
	@Column(name="id",nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_USER")
	private int id;
	
	@Column
	private String nome;
	
	@Column
	private String login;
	
	@Column
	private String email;
	
	@Column
	private String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
