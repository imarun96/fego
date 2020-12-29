package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DiscoverAccResponseDto {
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();
	@JsonProperty("status")
	private String status;
	@JsonProperty("accounts")
	private List<Account> account;
}