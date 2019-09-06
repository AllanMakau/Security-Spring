package br.com.security.control.entity;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class Cargo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Boolean ativo;
	
	
	public Cargo() {
		super();
	}


	public Cargo(String nome, Boolean ativo) {
		super();
		this.nome = nome;
		this.ativo = ativo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
	
}
