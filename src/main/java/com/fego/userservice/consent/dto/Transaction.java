package com.fego.userservice.consent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
	private String amount;
	private String currentBalance;
	private String balance;
	private String mode;
	private String narration;
	private String reference;
	private String transactionTimestamp;
	private String txnId;
	private String type;
	private String valueDate;
	private String transactionDateTime;
	private String amc;
	private String registrar;
	private String schemeCode;
	private String schemePlan;
	private String schemeTypes;
	private String schemeCategory;
	private String isin;
	private String amfiCode;
	private String schemeOption;
	private String fundType;
	private String ucc;
	private String closingUnits;
	private String lienUnits;
	private String nav;
	private String navDate;
	private String orderDate;
	private String executionDate;
	@JsonProperty("lock-inDays")
	private String lockinDays;
	@JsonProperty("lock-inFlag")
	private String lockinFlag;
}