package com.hamster.pos.dto;

public class CustomerDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String shopName;
	private String emailAddress;
	private String username;
	private String password;
	private String status;
	private Boolean temporaryPassword;
	private String confirmPassword;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(Long id, String firstName, String lastName, String phoneNumber, String address, String shopName,
			String emailAddress, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.shopName = shopName;
		this.emailAddress = emailAddress;
		this.status =  status;
	}
	


	public CustomerDTO(Long id,Boolean temporaryPassword) {
		super();
		this.id = id;
		this.temporaryPassword = temporaryPassword;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(Boolean temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


}
