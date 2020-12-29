package com.fego.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
	private String userID;
	private String mobile;
	private String firstName;
	private String middleName;
	private String lastName;
	private String vua;
	private String email;
	private Boolean termsAndConditions;
	private String createdDate;
}