package com.fego.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fego.userservice.entity.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

	@Query("select acc from Accounts acc where acc.productNumber=:accRefNumber")
	public Accounts findByProductNumber(@Param("accRefNumber") String accRefNumber);
	
	@Query("select acc from Accounts acc where acc.linkAccRefNumber=:linkAccRefNumber")
	public Accounts findByLinkAccRefNumber(@Param("linkAccRefNumber") String linkAccRefNumber);
}