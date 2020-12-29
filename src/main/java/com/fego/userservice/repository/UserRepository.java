package com.fego.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fego.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select usr from User usr where usr.mobile=:mobileNumber")
	public User findByMobileNumber(@Param("mobileNumber") String mobileNumber);
}