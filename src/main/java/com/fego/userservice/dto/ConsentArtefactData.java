package com.fego.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsentArtefactData {
	private String customerID;
	private String consentId;
	private String consentStart;
	private String consentExpiry;
	private String frequencyUnit;
	private int frequencyValue;
}