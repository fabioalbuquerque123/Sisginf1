package beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Bean;

@Entity
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Table(name="tb_pessoaFisica")
@SequenceGenerator(name="SEQ_PF", sequenceName="SEQ_PF_ID", initialValue=1, allocationSize=1)
public class PessoaFisica extends Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3762603380178462809L;
	

	@Id
	@Column(name="idPessoaFisica",nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PF")
	protected int idPessoaFisica;
	
	@Column
	protected String cpf;
	
	@Column
	protected String nome;
	
	@Column
	protected String email;
	
	@Column
	protected String telefones;
	
	@Column
	protected String tipo;
	
	/*****RELACIONAMENTO*****/
	@OneToMany(mappedBy="contadorPJ", targetEntity=PessoaJuridica.class)
	private List<ContadorPJ> pessoaJuridicasCont;	
	
	@OneToMany(mappedBy="responsavelLegalPJ", targetEntity=PessoaJuridica.class)
	private List<ContadorPJ> pessoaJuridicasResp;	

	public int getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(int idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public List<ContadorPJ> getPessoaJuridicasCont() {
		return pessoaJuridicasCont;
	}

	public void setPessoaJuridicasCont(List<ContadorPJ> pessoaJuridicasCont) {
		this.pessoaJuridicasCont = pessoaJuridicasCont;
	}

	public List<ContadorPJ> getPessoaJuridicasResp() {
		return pessoaJuridicasResp;
	}

	public void setPessoaJuridicasResp(List<ContadorPJ> pessoaJuridicasResp) {
		this.pessoaJuridicasResp = pessoaJuridicasResp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
