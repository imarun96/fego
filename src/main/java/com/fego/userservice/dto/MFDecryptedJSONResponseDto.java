package com.fego.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fego.userservice.consent.dto.AccountMF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MFDecryptedJSONResponseDto {

    @JsonProperty("Account")
    private AccountMF account;
}