package com.fego.userservice.consent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Holding {
	private String amc;
	private String registrar;
	private String schemeCode;
	private String isin;
	private String ucc;
	private String amfiCode;
	private String folioNo;
	private String dividendType;
	private String mode;
	@JsonProperty("FatcaStatus")
	private String fatcaStatus;
	private String units;
	private String closingUnits;
	private String lienUnits;
	private String rate;
	private String nav;
	private String lockingUnits;
}