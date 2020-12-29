package com.fego.userservice.consent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {
	@JsonProperty("Holders")
	private Holders holders;
}