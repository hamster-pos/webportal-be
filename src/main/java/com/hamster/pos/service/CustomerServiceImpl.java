package com.hamster.pos.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.management.relation.Role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hamster.pos.dto.BasicDTO;
import com.hamster.pos.dto.CustomerDTO;
import com.hamster.pos.dto.CustomerStatus;
import com.hamster.pos.dto.Roles;
import com.hamster.pos.model.Customer;
import com.hamster.pos.model.License;
import com.hamster.pos.repository.CustomerRepository;
import com.hamster.pos.repository.LicenseRepository;
import com.hamster.pos.utils.AES;
import com.hamster.pos.utils.EmailUtility;

import antlr.collections.List;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Value("${validupto}")
	private String validityOfLicence;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	LicenseRepository licenseRepository;
	
	@Autowired
	EmailUtility emailUtility;


	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	
	


	@Override
	public Long save(Customer userDTO){
		System.out.println("update api"+userDTO.getId());
		Long saveUserDetails = 0L;

		if (userDTO != null && userDTO.getId() == null) {	
			System.out.println("Customer create");
			Customer user = customerRepository.findByEmailAddress(userDTO.getEmailAddress());
			if (user == null) {
				if (!userDTO.getEmailAddress().equals(null) && !userDTO.getEmailAddress().equals(" ") ) {
					Customer userDBO = new Customer();
					userDBO.setFirstName(userDTO.getFirstName());
					userDBO.setLastName(userDTO.getLastName());
					userDBO.setPhoneNumber(userDTO.getPhoneNumber());
					userDBO.setAddress(userDTO.getAddress());
					userDBO.setShopName(userDTO.getShopName());
					userDBO.setEmailAddress(userDTO.getEmailAddress());
					userDBO.setStatus(CustomerStatus.PENDING.toString());
					userDBO.setUsername("");
					userDBO.setPassword("");
					userDBO.setRole(Roles.CUSTOMER.toString());
					userDBO.setTemporaryPassword(false);
					user = customerRepository.save(userDBO);
					saveUserDetails = 1L;

					return saveUserDetails;				
				}else {
					LOGGER.error("please enter valid email id");
				}

			}
		}
		else if(userDTO.getId() != null) {
			System.out.println("Customer Update");
			 if(customerRepository.findById(userDTO.getId()).isPresent()) {
				 	customerRepository.save(new Customer(userDTO.getId(),userDTO.getFirstName(),userDTO.getLastName(),userDTO.getPhoneNumber(),userDTO.getAddress(),userDTO.getShopName(),userDTO.getEmailAddress(),userDTO.getStatus()));
				 	saveUserDetails = 1L;	}
			 }
		return saveUserDetails;
	}


	@Override
	public ArrayList getAllCustomers() {
		System.out.println("getAllCustomers");
		ArrayList<CustomerDTO> customerList = new ArrayList<CustomerDTO>();
		customerRepository.findAll().forEach(customer ->customerList.add(new CustomerDTO(customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getPhoneNumber(),customer.getAddress(),customer.getShopName(),customer.getEmailAddress(),customer.getStatus())));		
		if(!customerList.isEmpty()) {
			return customerList;
		}
		return null;
	}
	
	@Override
	public Object searchCustomers(String value) {
		System.out.println("searchCustomer"+value);
		Optional<java.util.List<Customer>> customerlist = customerRepository.searchCustomer(value);
		ArrayList<CustomerDTO> searchedCustomers = new ArrayList<CustomerDTO>();
		if(customerlist.isPresent()) {
			customerlist.get().forEach(customer ->searchedCustomers.add(new CustomerDTO(customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getPhoneNumber(),customer.getAddress(),customer.getShopName(),customer.getEmailAddress(),customer.getStatus())));
		}
		else return new ResponseEntity<Object>("Customer Not Presnt : ",HttpStatus.NO_CONTENT);
		return new ResponseEntity<Object>(customerlist, HttpStatus.OK); 
	} 


	@Override
	public Object deleteCustomer(Long customerId) {
		try {
			customerRepository.deleteById(customerId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>("Error deleting Customer : ",HttpStatus.EXPECTATION_FAILED);
		}
	}


	@Override
	public Object generateLicenseDetailsForCustomer(Long id) {	
		int[] intArray = new int[]{ 30,30,30,30,30,30,30,30,30,30,30,30,15,365,15,365,15,365,15,365,15,365,15,365,15,365,15,365,15,365,15 }; 
		System.out.println("intArray"+intArray.length);
		for(int i=0;i<intArray.length; i++) {
			UUID uuid = UUID.randomUUID();
			String encryptedString = AES.encrypt(String.valueOf(id.toString()+"-"+intArray[i]),uuid.toString());
			if(customerRepository.findById(id).get() != null && !uuid.toString().equals("") && !encryptedString.equals("")) {
				licenseRepository.save(new License(encryptedString,uuid.toString(),CustomerStatus.PENDING.toString(),customerRepository.findById(id).get()));
			}
			else {
				return new ResponseEntity<Object>("Error while generating licenses for Customer : ",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	} 
	
	@Override
	public Object getLicenseDetailsForCustomer(Long id) {	
		java.util.List<BasicDTO> licenseList = licenseRepository.listLicencesByCustomerId(id);
		if(!licenseList.isEmpty() && licenseList.size() != 0) {
			return new ResponseEntity<Object>(licenseList,HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Error while fetching licenses for Customer : ",HttpStatus.EXPECTATION_FAILED);
	}


	@Override
	public Object doForgetPasswordActicity(String customerEmail) {
		System.out.println("service side :"+customerEmail);
		Customer customer = customerRepository.findByEmailAddress(customerEmail);
		if(customer != null) {
			System.out.println("service side 2 :"+customerEmail);
			String tempPassword = UUID.randomUUID().toString();
			customer.setPassword(tempPassword);
			customer.setTemporaryPassword(true);
			customerRepository.save(customer);
			emailUtility.send(customer.getEmailAddress(),tempPassword);
		}
		
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object authenticate(CustomerDTO customerDTO) {
		if (customerDTO.getUsername() != "" && customerDTO.getPassword() != "") {
			if (customerRepository.authenticateUser(customerDTO.getUsername(), customerDTO.getPassword())
					.isPresent()) {
				Customer customer = customerRepository.authenticateUser(customerDTO.getUsername(), customerDTO.getPassword()).get();
				System.out.println(""+customer.getId()+""+customer.getTemporaryPassword());
				return new ResponseEntity<Object>(customer, HttpStatus.OK);
			}

		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}


	@Override
	public Object resetPassword(CustomerDTO customerDTO) {
		System.out.println("under service of resetPassword");
		Customer customer;
		try {
			customer = customerRepository.findById(customerDTO.getId()).get();
		}catch (Exception e) {
			customer = null;
		}
		
		if(customer != null) {
			System.out.println("under if loop"+customerDTO.getConfirmPassword());
			customer.setPassword(customerDTO.getConfirmPassword());
			customer.setTemporaryPassword(false);
			customerRepository.save(customer);
			return new ResponseEntity<>("Password Reset Successful", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Customer Not found", HttpStatus.NOT_FOUND);
		}
	}


}
