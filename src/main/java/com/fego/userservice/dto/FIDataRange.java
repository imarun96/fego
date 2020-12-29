package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "from", "to" })
public class FIDataRange {
	private String from;
	private String to;
}