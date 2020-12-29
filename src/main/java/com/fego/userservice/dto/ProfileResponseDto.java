package com.fego.userservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "userData", "status", "userIdentifiers" })
public class ProfileResponseDto {

	@JsonProperty("userData")
	private UserData userData;

	@JsonProperty("status")
	private Boolean status;

	@JsonProperty("userIdentifiers")
	private List<UserIdentifiers> userIdentifiers;
}