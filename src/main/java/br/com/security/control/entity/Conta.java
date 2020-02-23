package br.com.security.control.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Conta implements Serializable {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipoConta;
	private String login;
	private String senha;
	private Integer porta;
	private String smtp;
	public Conta(Long id, String tipoConta, String login, String senha, Integer porta, String smtp) {
		super();
		this.id = id;
		this.tipoConta = tipoConta;
		this.login = login;
		this.senha = senha;
		this.porta = porta;
		this.smtp = smtp;
	}
	
	
	
	

}
