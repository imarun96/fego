package com.fego.userservice.dto;

import com.fego.userservice.consent.dto.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SavingsDecryptedJSONResponseDto {

	@JsonProperty("Account")
	private Account account;
}
