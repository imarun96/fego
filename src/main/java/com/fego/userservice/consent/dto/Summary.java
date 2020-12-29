package com.fego.userservice.consent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {
	private String currentBalance;
	private String currency;
	private String branch;
	private String balanceDateTime;
	private String currentODLimit;
	private String drawingLimit;
	private String exchgeRate;
	private String facility;
	private String ifscCode;
	private String micrCode;
	private String openingDate;
	private String status;
	private String type;
	@JsonProperty("Pending")
	private Pending pending;
	private String ifsc;
	private String accountType;
	private String maturityAmount;
	private String maturityDate;
	private String description;
	private String interestPayout;
	private String interestRate;
	private String principalAmount;
	private String tenureDays;
	private String tenureMonths;
	private String tenureYears;
	private String interestComputation;
	private String compoundingFrequency;
	private String interestPeriodicPayoutAmount;
	private String interestOnMaturity;
	private String currentValue;
	private String recurringAmount;
	private String recurringDepositDay;
	private String investmentValue;
	@JsonProperty("Investment")
	private Investment investment;
}