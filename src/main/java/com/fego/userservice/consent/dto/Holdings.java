package com.fego.userservice.consent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Holdings {
	@JsonProperty("Holding")
	private Holding holding;
}