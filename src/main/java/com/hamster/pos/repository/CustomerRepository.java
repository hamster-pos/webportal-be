package com.hamster.pos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hamster.pos.dto.BasicDTO;
import com.hamster.pos.dto.CustomerDTO;
import com.hamster.pos.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	public Customer findByUsernameAndPassword(String username, String password);
	
	public Customer findByEmailAddress(String emailAddress);
	
	@Query("select customer from Customer customer where customer.username = (:userName) AND customer.password = (:password)")
	Optional<Customer> authenticateUser(@Param("userName") String userName, @Param("password") String password);
	
	@Query("select customer from Customer customer where customer.firstName LIKE concat(:firstNameOrLastName,'%') OR customer.lastName LIKE concat(:firstNameOrLastName,'%')")
	Optional<List<Customer>> searchCustomer(@Param("firstNameOrLastName") String firstNameOrLastName);
	
	
	//public User updatePasswordByEmailAddress(String emailAddress);
}
