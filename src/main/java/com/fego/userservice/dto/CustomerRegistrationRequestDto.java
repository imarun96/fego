package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationRequestDto {
	@JsonProperty("name")
	private String name;
	@JsonProperty("phone_number")
	private String phoneNumber;
	@JsonProperty("terms_and_conditions")
	private Boolean termsAndConditions;
	@JsonProperty("vua")
	private String vua;
}