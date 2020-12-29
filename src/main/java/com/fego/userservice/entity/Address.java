package com.fego.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address", schema = "PUBLIC")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long id;
	@Column(name = "userId", nullable = false)
	private long userId;
	@Column(name = "street1")
	private String street1;
	@Column(name = "street2")
	private String street2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "countryName")
	private String countryName;
	@Column(name = "countryAlpha2")
	private String countryAlpha2;
	@Column(name = "countryAlpha3")
	private String countryAlpha3;
	@Column(name = "countryUncode")
	private String countryUncode;
	@Column(name = "postalCode")
	private String postalCode;
	@Column(name = "type")
	private String type;
}