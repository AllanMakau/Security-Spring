package br.com.security.control.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Manifestacao implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private Date data;
	private String senha;
	private String protocolo;
	private String texto;
	

	private Tipologia tipologia;

	private TipoAssunto tipoAssunto;

	private Meio meio;
	@ManyToOne
	private Manifestante manifestante;
	
	
	
	
	/**
	 * @param data
	 * @param senha
	 * @param protocolo
	 * @param texto
	 * @param tipologia
	 * @param tipoAssunto
	 * @param meioEntrada
	 * @param manifestante
	 */
	public Manifestacao(Date data, String senha, String protocolo, String texto, Tipologia tipologia,
			TipoAssunto tipoAssunto, Meio meio, Manifestante manifestante) {
		super();
		this.data = data;
		this.senha = senha;
		this.protocolo = protocolo;
		this.texto = texto;
		this.tipologia = tipologia;
		this.tipoAssunto = tipoAssunto;
		this.meio = meio;
		this.manifestante = manifestante;
	}

	/**
	 * 
	 */
	public Manifestacao() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getProtocolo() {
		return protocolo;
	}


	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public Tipologia getTipologia() {
		return tipologia;
	}


	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}


	public TipoAssunto getTipoAssunto() {
		return tipoAssunto;
	}


	public void setTipoAssunto(TipoAssunto tipoAssunto) {
		this.tipoAssunto = tipoAssunto;
	}


	public Meio getMeio() {
		return meio;
	}


	public void setMeio(Meio meio){
		this.meio = meio;
	}


	public Manifestante getManifestante() {
		return manifestante;
	}


	public void setManifestante(Manifestante manifestante) {
		this.manifestante = manifestante;
	}
	
	
}
