package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.*;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Data {
	@JsonProperty("accType")
	private String accType;
	@JsonProperty("accRefNumber")
	private String accRefNumber;
	@JsonProperty("maskedAccNumber")
	private String maskedAccNumber;
	@JsonProperty("fipId")
	private String fipId;
	@JsonProperty("userInfo")
	private UserInfo userInfo;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();
}