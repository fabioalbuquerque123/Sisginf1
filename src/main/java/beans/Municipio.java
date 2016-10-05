package beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import arquitetura.Bean;

@Entity
@Table(name="tb_municipio")
public class Municipio extends Bean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1247082250023409013L;

	@Id
	@Column(name="codigo",nullable=false)
	private int codigo;
	
	@Column
	private String municipio;
	
	@Column
	private String uf;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
}
