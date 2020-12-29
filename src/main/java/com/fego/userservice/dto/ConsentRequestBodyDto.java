package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsentRequestBodyDto {
	@JsonProperty("FIDataRange")
	private FIDataRange fiDataRange;
}