package com.hamster.pos.service;

import java.util.ArrayList;

import com.hamster.pos.dto.CustomerDTO;
import com.hamster.pos.model.Customer;

import antlr.collections.List;

public interface CustomerService {
	
	public Long save(Customer customer);
	public ArrayList<CustomerDTO> getAllCustomers();
	public Object deleteCustomer(Long id);
	public Object generateLicenseDetailsForCustomer(Long id);
	public Object getLicenseDetailsForCustomer(Long id);
	public Object doForgetPasswordActicity(String customerEmail);
	public Object authenticate(CustomerDTO customerDTO);
	public Object resetPassword(CustomerDTO customerDTO);
	public Object searchCustomers(String searchCustomer);
	public Object filterLicencesByCustomerId(Long id);
//	public Long updatePassword(User userDTO);
}
