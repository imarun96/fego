package com.fego.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsentVerifyDto {
	private String actionType;
	private String identifierValue;
	private String identifierType;
}
