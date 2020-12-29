package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

	@JsonProperty("type")
	private String type;

	@JsonProperty("data")
	private Data data;
}
