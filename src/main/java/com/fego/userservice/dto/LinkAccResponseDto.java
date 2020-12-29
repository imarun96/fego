package com.fego.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkAccResponseDto {
	private String status;
	private AuthSessionParameters authSessionParameters;
}