package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsentOTPRefResponseDto {
	private String consentHandle;
	@JsonProperty("otpResponse")
	private ConsentOTPResponseDto consentOTPResponseDto;
}