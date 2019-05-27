package com.hamster.pos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hamster.pos.dto.HamsterUserDTO;
import com.hamster.pos.service.HamsterUserService;

@CrossOrigin(origins="*")
@RestController
public class UserController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	UserService userService;

	@Autowired
	HamsterUserService hamsterUserService;

	@PostMapping(value = "/authenticate")
	public Object login(@RequestBody HamsterUserDTO hamsterUserDTO) {
		System.out.println("hamsterUserDTO"+hamsterUserDTO.getPassword());
		LOGGER.debug("login method {}", hamsterUserDTO.getId(), hamsterUserDTO.getPassword());

		return hamsterUserService.authenticateUser(hamsterUserDTO);

	}

	@GetMapping(value = "/get")
	public String get() {
		System.out.println("test");
		//hamsterUserService.applyLicense();
		return "test";

	}
	
	@GetMapping(value = "/admin/licences")
	public Object getAllLicences() {
		System.out.println("getAllLicences");
		return hamsterUserService.getAllLicences();

	}
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public ResponseEntity<Object> save(@RequestBody User userDTO) {
//		LOGGER.debug("save method {}", userDTO);
//		Long saveUserDetails = userService.save(userDTO);
//		if (saveUserDetails == 0L) {
//			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//		}
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

//	@RequestMapping(value = "/updatepassword", method = RequestMethod.PUT)
//	public ResponseEntity<Object> updatePassword(@RequestBody User userDTO) {
//		LOGGER.debug("update password method  {}", userDTO);
//		Long updateUserPassword = userService.updatePassword(userDTO);
//		if (updateUserPassword == 0L) {
//			return  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//		}		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
}
