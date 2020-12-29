package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "status", "message" })
public class ConsentStatusResponseDto {

	@JsonProperty("status")
	private Boolean status;

	@JsonProperty("message")
	private String message;
}