package com.fego.userservice.consent.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include. NON_NULL)
public class Account {

    private String linkedAccRef;
    private String maskedAccNumber;
    private String xmlns;
    @JsonProperty("xmlns:xsi")
    @JsonIgnore
    private String xsi;
    @JsonProperty("xsi:schemaLocation")
    @JsonIgnore
    private String schemaLocation;
    private String type;
    private String version;
    @JsonProperty("Profile")
    private Profile profile;
    @JsonProperty("Summary")
    private Summary summary;
    @JsonProperty("Transactions")
    private Transactions transactions;
}