package br.com.security.control.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Perfil implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private Boolean ativo;
	
	
	 @ManyToMany(fetch = FetchType.EAGER)
     @JoinTable(name = "Perfil_Funcionalidade",
     joinColumns =
     @JoinColumn(name = "id_perfil"),
     inverseJoinColumns =
     @JoinColumn(name = "id_funcionalidade"))
    private Set<Funcionalidade> funcionalidades = new HashSet<Funcionalidade>();
	 
	 
	@ManyToMany(mappedBy = "perfil",fetch = FetchType.EAGER)
	@JsonBackReference
    private Set<Usuario> usuario = new HashSet<Usuario>();
	
	public Perfil(Long id, String nome, String descricao, Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}

	public Perfil() {
		super();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public Set<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}

	public Set<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(Set<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}


	

}
