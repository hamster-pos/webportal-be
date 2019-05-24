package com.hamster.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hamster.pos.dto.BasicDTO;
import com.hamster.pos.dto.CustomerDTO;
import com.hamster.pos.dto.HamsterUserDTO;
import com.hamster.pos.model.Customer;
import com.hamster.pos.service.CustomerServiceImpl;

@CrossOrigin(origins="*")
@RestController
public class CustomerController {
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	
	@PostMapping(value = "/customer/authenticate")
	public Object login(@RequestBody CustomerDTO customerDTO) {
		System.out.println("auth controller");
		//System.out.println("customerDTO"+customerDTO.getPassword());
		//LOGGER.debug("login method {}", hamsterUserDTO.getId(), hamsterUserDTO.getPassword());

		return customerServiceImpl.authenticate(customerDTO);

	}
	
	@GetMapping(value = "/customer")
	public Object getAllCustomers() {
		return customerServiceImpl.getAllCustomers();
	}
	
	@GetMapping(value = "/customer/{searchCustomer}")
	public Object searchCustomer(@PathVariable String searchCustomer) {
		return customerServiceImpl.searchCustomers(searchCustomer);
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		//LOGGER.debug("save method {}", customer);
		Long saveUserDetails = customerServiceImpl.save(customer);
		if (saveUserDetails == 0L) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/customer/{custmerId}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long custmerId) {
		return (ResponseEntity<Object>) customerServiceImpl.deleteCustomer(custmerId);
	}
	
	@GetMapping(value = "/customer/forget/password/{customerEmail}")
	public ResponseEntity<Object> doForgetPasswordActicity(@PathVariable String customerEmail) {
		return (ResponseEntity<Object>) customerServiceImpl.doForgetPasswordActicity(customerEmail);
	}
	
	@PutMapping(value = "/customer/reset/password")
	public ResponseEntity<Object> resetPassword(@RequestBody CustomerDTO customerDTO) {
		System.out.println("controller side: "+ customerDTO.getPassword()+":"+customerDTO.getConfirmPassword());
		return (ResponseEntity<Object>) customerServiceImpl.resetPassword(customerDTO);
	}
	
	@PutMapping(value = "/customer/licenses/{custmerId}")
	public ResponseEntity<Object> generateLicenseDetailsForCustomer(@PathVariable Long custmerId) {
		return (ResponseEntity<Object>) customerServiceImpl.generateLicenseDetailsForCustomer(custmerId);
		
	}
	
	@GetMapping(value = "/customer/licenses/{custmerId}")
	public ResponseEntity<Object> getLicenseDetailsForCustomer(@PathVariable Long custmerId) {
		System.out.println("id"+custmerId);
		return (ResponseEntity<Object>) customerServiceImpl.getLicenseDetailsForCustomer(custmerId);
		
	}

}
