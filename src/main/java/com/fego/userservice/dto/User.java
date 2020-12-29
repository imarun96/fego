package com.fego.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String name;
	private String mobile;
	private String termsAndConditions;
	private String isKycVerfied;
	private String emailId;
	private String addressId;
	private String pan;
	private String dob;
	private String type;
	private String isOtp;
	private String otpReference;
	private String userId;
	private String vua;
	private String createdDateTime;
	private String lastUpdatedDateTime;
	private Integer version;
}