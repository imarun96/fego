package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDiscoverAccountResponseDto {
	@JsonProperty("UserProfileDetails")
	private ProfileResponseDto profileResponseDto;
	@JsonProperty("DiscoveredAccounts")
	private DiscoverAccResponseDto discoverAccResponseDto;
}