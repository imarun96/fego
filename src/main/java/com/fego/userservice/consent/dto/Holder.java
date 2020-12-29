package com.fego.userservice.consent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Holder {
	private String address;
	private String ckycCompliance;
	private String dob;
	private String email;
	private String landline;
	private String mobile;
	private String name;
	private String nominee;
	private String pan;
	private String dematId;
}