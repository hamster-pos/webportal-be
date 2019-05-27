package com.hamster.pos.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hamster.pos.dto.BasicDTO;
import com.hamster.pos.dto.CustomerDTO;
import com.hamster.pos.dto.HamsterUserDTO;
import com.hamster.pos.dto.LicenceDTO;
import com.hamster.pos.model.Customer;
import com.hamster.pos.model.License;
import com.hamster.pos.repository.CustomerRepository;
import com.hamster.pos.repository.HamsterUserRepository;
import com.hamster.pos.repository.LicenseRepository;
import com.hamster.pos.service.HamsterUserService;

@Service
public class HamsterUserServiceImpl implements HamsterUserService {

	@Autowired
	HamsterUserRepository hamsterUserRepository;
	
	@Autowired
	LicenseRepository licenseRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Object authenticateUser(HamsterUserDTO hamsterUserDTO) {
		if (hamsterUserDTO.getUsername() != "" && hamsterUserDTO.getPassword() != "") {
			if (hamsterUserRepository.authenticateUser(hamsterUserDTO.getUsername(), hamsterUserDTO.getPassword())
					.isPresent()) {
				return new ResponseEntity<>(HttpStatus.OK);
			}

		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@Override
	public void applyLicense() {
		System.out.println("applyLicense service");
		Optional<Customer> customer = customerRepository.findById(103L);
		License li = new License();
		//li.setName("license 1");
		li.setCustomer(customer.get());
		licenseRepository.save(li);
		
		Optional<Customer> customer2 = customerRepository.findById(104L);
		License li2 = new License();
		//li2.setName("license 2");
		li2.setCustomer(customer.get());
		licenseRepository.save(li2);
		
	}

	@Override
	public Object getAllLicences() {
		System.out.println("admin->licence");
		ArrayList<LicenceDTO> licenceList = new ArrayList<LicenceDTO>();
		licenseRepository.findAll().forEach(licence ->licenceList.add(new LicenceDTO(licence.getId(),licence.getCode(),licence.getLicense_key(),licence.getStatus(), licence.getValidity())));		
		
		if(!licenceList.isEmpty()) {
			return new ResponseEntity<Object>(licenceList, HttpStatus.OK); 
		}
		return new ResponseEntity<Object>("Error while getting licences", HttpStatus.EXPECTATION_FAILED); 
	}
	

}
