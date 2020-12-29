package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fego.userservice.consent.dto.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RDDecryptedJSONResponseDto {
	@JsonProperty("Account")
	private Account account;
}