package br.com.security.control.DTO;

public class ProblemaDTO {
	
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	
	public ProblemaDTO(Integer status,String title,String type, String detail) {
		super();
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
	}
	
	
	public ProblemaDTO() {
		super();
	}


	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

}
