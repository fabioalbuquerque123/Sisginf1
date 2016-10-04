package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Bean;
@Entity
@Table(name="tb_processo")
@SequenceGenerator(name="SEQ_PROCESSO", sequenceName="SEQ_PROCESSO_ID", initialValue=1, allocationSize=1)
public class Processo extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8906449870536264773L;
	
	@Id
	@Column(name="idProcesso",nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PROCESSO")
	private int idProcesso;
	
	@Column
	private String numeroOriginalANTAQ;
	
	@Column
	private String numeroApensoANTAQ;
	
	@Column
	private String numeroProcessoSEP;
	
	@Column
	private Date dataProtocoloSEP;
	
	@Column
	private String localizacao;
		
	@OneToMany(mappedBy="processo", targetEntity=Projeto.class)
	private List<Bean> projeto;	

	public int getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(int idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getNumeroOriginalANTAQ() {
		return numeroOriginalANTAQ;
	}

	public void setNumeroOriginalANTAQ(String numeroOriginalANTAQ) {
		this.numeroOriginalANTAQ = numeroOriginalANTAQ;
	}

	public String getNumeroApensoANTAQ() {
		return numeroApensoANTAQ;
	}

	public void setNumeroApensoANTAQ(String numeroApensoANTAQ) {
		this.numeroApensoANTAQ = numeroApensoANTAQ;
	}

	public String getNumeroProcessoSEP() {
		return numeroProcessoSEP;
	}

	public void setNumeroProcessoSEP(String numeroProcessoSEP) {
		this.numeroProcessoSEP = numeroProcessoSEP;
	}

	public Date getDataProtocoloSEP() {
		return dataProtocoloSEP;
	}

	public void setDataProtocoloSEP(Date dataProtocoloSEP) {
		this.dataProtocoloSEP = dataProtocoloSEP;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public List<Bean> getProjeto() {
		return projeto;
	}

	public void setProjeto(List<Bean> projeto) {
		this.projeto = projeto;
	}
}
