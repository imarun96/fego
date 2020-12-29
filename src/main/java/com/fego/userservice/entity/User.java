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
@Table(name = "user", schema = "PUBLIC")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "mobile", nullable = false)
	private String mobile;
	@Column(name = "termsAndConditions")
	private String termsAndConditions;
	@Column(name = "isKycVerfied")
	private String isKycVerfied;
	@Column(name = "emailId")
	private String emailId;
	@Column(name = "addressId")
	private long addressId;
	@Column(name = "pan")
	private String pan;
	@Column(name = "dob")
	private String dob;
	@Column(name = "type")
	private String type;
	@Column(name = "isOtp")
	private String isOtp;
	@Column(name = "otp_reference")
	private String otpReference;
	@Column(name = "userId")
	private String userId;
	@Column(name = "vua")
	private String vua;
	@Column(name = "createdDateTime")
	private String createdDateTime;
	@Column(name = "lastUpdatedDateTime")
	private String lastUpdatedDateTime;
	@Column(name = "version")
	private Integer version;
}