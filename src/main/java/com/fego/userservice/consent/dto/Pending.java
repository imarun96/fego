package com.fego.userservice.consent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pending {
	private String transactionType;
	private String amount;
}