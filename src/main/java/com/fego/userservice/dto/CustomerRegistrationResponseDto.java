package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationResponseDto {

	@JsonProperty("otp_reference")
	private String otpReference;
	@JsonProperty("status")
	private Boolean status;
}