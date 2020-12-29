package com.fego.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fego.userservice.entity.Consent;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {

}