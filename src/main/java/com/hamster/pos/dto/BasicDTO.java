package com.hamster.pos.dto;

public class BasicDTO {

	private long id;
	private String username;
	private String message;
	private String code;

	public BasicDTO(long id, String username, String message) {
		super();
		this.id = id;
		this.username = username;
		this.message = message;
	}
	
	public BasicDTO(long id,String code) {
		super();
		this.id = id;
		this.code = code;
	}
	
	public BasicDTO(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
