package com.fego.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthSessionParameters {

	private String authType;
	private String refNumber;
	private Udf udf;
}