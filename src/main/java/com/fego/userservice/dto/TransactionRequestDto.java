package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fego.userservice.consent.dto.TDDecryptedJSONResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDto {

	@JsonProperty("UserId")
	private long userId;

	@JsonProperty("SavingsAccTransactionData")
	private SavingsDecryptedJSONResponseDto savingsDecryptedJSONResponseDto;

	@JsonProperty("MutualFundTransactionData")
	private MFDecryptedJSONResponseDto mfDecryptedJSONResponseDto;

	@JsonProperty("TermDepositTransactionData")
	private TDDecryptedJSONResponseDto tdDecryptedJSONResponseDto;

	@JsonProperty("RDTransactionData")
	private RDDecryptedJSONResponseDto rdDecryptedJSONResponseDto;
}