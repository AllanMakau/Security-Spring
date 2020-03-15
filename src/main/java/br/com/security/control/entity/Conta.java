package br.com.security.control.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipoConta;
	private String login;
	private String senha;
	private Integer porta;
	private String smtp;
	private Boolean ativo;
	
	
	/**
	 * @param id
	 * @param tipoConta
	 * @param login
	 * @param senha
	 * @param porta
	 * @param smtp
	 * @param ativo
	 */
	public Conta(Long id, String tipoConta, String login, String senha, Integer porta, String smtp, Boolean ativo) {
		super();
		this.id = id;
		this.tipoConta = tipoConta;
		this.login = login;
		this.senha = senha;
		this.porta = porta;
		this.smtp = smtp;
		this.ativo = ativo;
	}


	public Conta() {
		super();
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
	

}
