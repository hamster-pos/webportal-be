package com.hamster.pos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hamster.pos.dto.BasicDTO;
import com.hamster.pos.dto.LicenceDTO;
import com.hamster.pos.model.License;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {
	
	@Query("select new com.hamster.pos.dto.BasicDTO (l.id,l.code) from License l where l.customer.id = (:customerId)")
	List<BasicDTO> listLicencesByCustomerId(@Param("customerId")Long customerId);
	
	@Query("select new com.hamster.pos.dto.LicenceDTO (l.id,l.code,l.license_key,l.status,l.validity) from License l where l.customer.id = (:customerId)")
	List<LicenceDTO> filterLicencesByCustomerId(@Param("customerId")Long customerId);
	
//	@Query("select l.id from License l")
//	List<Long> listLicencesByCustomerId(@Param("customerId")Long customerId);
//	

}

