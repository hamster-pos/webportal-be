package com.hamster.pos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "customerLicense")
public class CustomerLicense implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String code;
	private String key;
	private String status;
	
	public CustomerLicense() {
		
	}
	
	public CustomerLicense(Long id, String code, String key, String phoneNumber) {
		super();
		this.id = id;
		this.code = code;
		this.key = key;
		this.status = status;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
//				+ ", address=" + address + ", shopName=" + shopName + ", emailAddress=" + emailAddress + ", username="
//				+ username + ", password=" + password + ", role=" + status + "]";
//	}

	
	
	

}
