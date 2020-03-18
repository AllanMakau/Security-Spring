package br.com.security.control.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoAssunto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idSup;
	private Integer nivel;
	private String descricao;
	private Boolean sintetico;
	private Boolean ativo;
	
	
	/**
	 * @param idSup
	 * @param nivel
	 * @param descricao
	 * @param sintetico
	 * @param ativo
	 */
	public TipoAssunto(Long idSup, Integer nivel, String descricao, Boolean sintetico, Boolean ativo) {
		super();
		this.idSup = idSup;
		this.nivel = nivel;
		this.descricao = descricao;
		this.sintetico = sintetico;
		this.ativo = ativo;
	}
	
	
	/**
	 * 
	 */
	public TipoAssunto() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdSup() {
		return idSup;
	}
	public void setIdSup(Long idSup) {
		this.idSup = idSup;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getSintetico() {
		return sintetico;
	}
	public void setSintetico(Boolean sintetico) {
		this.sintetico = sintetico;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
