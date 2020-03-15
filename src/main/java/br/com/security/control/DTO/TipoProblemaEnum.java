package br.com.security.control.DTO;

public enum TipoProblemaEnum {

	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ERRO_GENERICO("/erro-generico", "erro não tratado"),
	ERRO_DATA_INTEGRITY("/erro-integridade", "Impossível excluir esta informação. Entidade está relacionada."),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrada", "Recurso não encontrado"),
	MENSAGEM_INVALIDA("/mensagem-invalida", "Mensagem Inválida"),
	ARGUMENTO_INVALIDO("/argumento-invalido", "Argumento Inválido"),
	ACCESS_DANIED_EXCEPTION("/acesso-inegado", "Acesso Negado");	
	
	private String uri;
	private String titulo;
	
	
	private TipoProblemaEnum( String path, String titulo) {
		this.titulo = titulo;
		this.uri ="https://control.com.br" + path ;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getUri() {
		return uri;
	}
	
	
	
	
}
