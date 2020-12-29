package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsentOTPResponseDto {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;
}