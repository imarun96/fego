package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationVerifyDto {
	@JsonProperty("phone_number")
	private String phoneNumber;
	@JsonProperty("otp_reference")
	private String otpReference;
	private String code;
}