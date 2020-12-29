package com.fego.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdentifiers {
	private int id;
	private String user_identifier_type;
	private String user_identifier_value;
	private String user_identifier_category;
	private String verificationStatus;
	private String fipId;
}