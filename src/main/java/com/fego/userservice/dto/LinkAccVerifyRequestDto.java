package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkAccVerifyRequestDto {
	@JsonProperty("refNumber")
	private String refNumber;
	@JsonProperty("authToken")
	private String authToken;
}