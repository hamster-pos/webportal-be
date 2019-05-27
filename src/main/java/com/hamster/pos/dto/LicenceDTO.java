package com.hamster.pos.dto;

public class LicenceDTO {
	private Long id;
	private String code;
	private String license_key;
	private String status;
	private Integer validity;
	
	public LicenceDTO(Long id, String code, String license_key, String status) {
		super();
		System.out.println("licenceList");
		this.id = id;
		this.code = code;
		this.license_key = license_key;
		this.status = status;
	}

	public LicenceDTO(Long id, String code, String license_key, String status, Integer validity) {
		super();
		System.out.println("licenceList 2");
		this.id = id;
		this.code = code;
		this.license_key = license_key;
		this.status = status;
		this.validity = validity;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLicense_key() {
		return license_key;
	}
	public void setLicense_key(String license_key) {
		this.license_key = license_key;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

}
