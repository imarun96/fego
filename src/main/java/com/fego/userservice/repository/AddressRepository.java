package com.fego.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fego.userservice.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}