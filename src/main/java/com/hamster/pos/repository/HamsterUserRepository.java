package com.hamster.pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hamster.pos.model.HamsterUser;

@Repository
public interface HamsterUserRepository extends CrudRepository<HamsterUser, Long> {

	@Query("select username from HamsterUser where username = (:userName) AND password = (:password)")
	Optional<String> authenticateUser(@Param("userName") String userName, @Param("password") String password);

}
