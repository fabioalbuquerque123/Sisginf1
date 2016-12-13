package beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import arquitetura.Bean;

@Entity
@Table(name="tb_projeto")
@SequenceGenerator(name="SEQ_PROJETO", sequenceName="SEQ_PROJETO_ID", initialValue=1, allocationSize=1)
public class Projeto extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958471613065624127L;
	
	@Id
	@Column(name="idProjeto",nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PROJETO")
	private int idProjeto;
	
	@ManyToOne
	@JoinColumn(name="idProcesso", referencedColumnName="idProcesso")
	private Processo processo;
	
	@ManyToOne
	@JoinColumn(name="idPessoaJuridica", referencedColumnName="idPessoaJuridica")
	private PessoaJuridica pessoaJuridica; 
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String uf;
	
	@Column
	private String localidade;
	
	@Column
	private String numeroContrato;
	
	@Column
	private Date dataInicio;
	
	@Column
	private Date dataFim;
	
	@Column
	private double valorTotalProjeto;
	
	@Column
	private double projecaoImpactoFinal;
	
	@Column
	private String numeroNotaTecnica;
	
	@Column
	private String numeoPortaria;
	
	@Column
	private Date dataAprovRejeicao;
	
	@Column
	private String statusProjeto;
	
	@Column
	private double estimativaValBensCom;
	
	@Column
	private double estimativaValServicosCom;
	
	@Column
	private double estimativaValOutrosCom;
	
	@Column
	private double estimativaValBensSem;
	
	@Column
	private double estimativaValServicosSem;
	
	@Column
	private double estimativaValOutrosSem;
	
	@Column
	private Date dataLimiteRelatorio;
	
	@Column
	private Date dataRecebimentoRelatorio;
	
	@Column
	private Date dataCienciaOficioPJ;
	
	@Column
	private String pathPortaria;
	
	@Transient
	private String nomeEmpresarial;
	
	@Transient
	private String numeroOriginalANTAQ;
	
	@Transient
	private String cnpj;

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public double getValorTotalProjeto() {
		return valorTotalProjeto;
	}

	public void setValorTotalProjeto(double valorTotalProjeto) {
		this.valorTotalProjeto = valorTotalProjeto;
	}

	public double getProjecaoImpactoFinal() {
		return projecaoImpactoFinal;
	}

	public void setProjecaoImpactoFinal(double projecaoImpactoFinal) {
		this.projecaoImpactoFinal = projecaoImpactoFinal;
	}

	public String getNumeroNotaTecnica() {
		return numeroNotaTecnica;
	}

	public void setNumeroNotaTecnica(String numeroNotaTecnica) {
		this.numeroNotaTecnica = numeroNotaTecnica;
	}

	public String getNumeoPortaria() {
		return numeoPortaria;
	}

	public void setNumeoPortaria(String numeoPortaria) {
		this.numeoPortaria = numeoPortaria;
	}

	public Date getDataAprovRejeicao() {
		return dataAprovRejeicao;
	}

	public void setDataAprovRejeicao(Date dataAprovRejeicao) {
		this.dataAprovRejeicao = dataAprovRejeicao;
	}

	public String getStatusProjeto() {
		return statusProjeto;
	}

	public void setStatusProjeto(String statusProjeto) {
		this.statusProjeto = statusProjeto;
	}

	public double getEstimativaValBensCom() {
		return estimativaValBensCom;
	}

	public void setEstimativaValBensCom(double estimativaValBensCom) {
		this.estimativaValBensCom = estimativaValBensCom;
	}

	public double getEstimativaValServicosCom() {
		return estimativaValServicosCom;
	}

	public void setEstimativaValServicosCom(double estimativaValServicosCom) {
		this.estimativaValServicosCom = estimativaValServicosCom;
	}

	public double getEstimativaValOutrosCom() {
		return estimativaValOutrosCom;
	}

	public void setEstimativaValOutrosCom(double estimativaValOutrosCom) {
		this.estimativaValOutrosCom = estimativaValOutrosCom;
	}

	public double getEstimativaValBensSem() {
		return estimativaValBensSem;
	}

	public void setEstimativaValBensSem(double estimativaValBensSem) {
		this.estimativaValBensSem = estimativaValBensSem;
	}

	public double getEstimativaValServicosSem() {
		return estimativaValServicosSem;
	}

	public void setEstimativaValServicosSem(double estimativaValServicosSem) {
		this.estimativaValServicosSem = estimativaValServicosSem;
	}

	public double getEstimativaValOutrosSem() {
		return estimativaValOutrosSem;
	}

	public void setEstimativaValOutrosSem(double estimativaValOutrosSem) {
		this.estimativaValOutrosSem = estimativaValOutrosSem;
	}

	public Date getDataLimiteRelatorio() {
		return dataLimiteRelatorio;
	}

	public void setDataLimiteRelatorio(Date dataLimiteRelatorio) {
		this.dataLimiteRelatorio = dataLimiteRelatorio;
	}

	public Date getDataRecebimentoRelatorio() {
		return dataRecebimentoRelatorio;
	}

	public void setDataRecebimentoRelatorio(Date dataRecebimentoRelatorio) {
		this.dataRecebimentoRelatorio = dataRecebimentoRelatorio;
	}

	public Date getDataCienciaOficioPJ() {
		return dataCienciaOficioPJ;
	}

	public void setDataCienciaOficioPJ(Date dataCienciaOficioPJ) {
		this.dataCienciaOficioPJ = dataCienciaOficioPJ;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public String getPathPortaria() {
		return pathPortaria;
	}

	public void setPathPortaria(String pathPortaria) {
		this.pathPortaria = pathPortaria;
	}

	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}

	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}

	public String getNumeroOriginalANTAQ() {
		return numeroOriginalANTAQ;
	}

	public void setNumeroOriginalANTAQ(String numeroOriginalANTAQ) {
		this.numeroOriginalANTAQ = numeroOriginalANTAQ;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
