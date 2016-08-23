package beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Bean;

@Entity
@Table(name="tb_pessoaJuridica")
@SequenceGenerator(name="SEQ_PJ", sequenceName="SEQ_PJ_ID", initialValue=1, allocationSize=1)
public class PessoaJuridica extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6464060657113448107L;

	@Id
	@Column(name="idPessoaJuridica",nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PJ")
	private int idPessoaJuridica;
	
	@Column
	private String nomeEmpresarial;
	
	@Column
	private String cnpj;
	
	@Column
	private String Endereco;
	
	@Column
	private String Bairro;
	
	@Column
	private String cep;
	
	@Column
	private String municipio;
	
	@Column
	private String uf;
	
	@Column
	private String telefones;
	
	/*****RELACIONAMENTO*****/
	@ManyToOne
	@JoinColumn(name="idPessoaFisica", referencedColumnName="idPessoaFisica")
	private PessoaFisica pessoaFisica;
	
	@OneToMany(mappedBy="pessoaJuridica", targetEntity=Projeto.class)
	private List<Projeto> projetos;	

	public int getIdPessoaJuridica() {
		return idPessoaJuridica;
	}

	public void setIdPessoaJuridica(int idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}

	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}

	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
}
