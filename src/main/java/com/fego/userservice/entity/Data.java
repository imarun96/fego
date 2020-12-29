package com.fego.userservice.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fego.userservice.dto.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Data {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	@JsonIgnore
	private Long id;
	@Column(name = "userId", nullable = false)
	@JsonIgnore
	private long userId;
	@Column(name = "productNumber")
	private String productNumber;
	@Column(name = "maskedProductNumber")
	private String maskedProductNumber;
	@Column(name = "partnerId")
	private String partnerId;
	@Column(name = "partnerName")
	private String partnerName;
	@Column(name = "partnerType")
	private String partnerType;
	@Column(name = "fullName")
	private String fullName;
	@Column(name = "productType")
	private String productType;
	@Column(name = "nominee")
	private String nominee;
	@Column(name = "type")
	private String type;
	@Column(name = "openingDate")
	private String openingDate;
	@Column(name = "currentOdLimit")
	private String currentOdLimit;
	@Column(name = "drawingLimit")
	private String drawingLimit;
	@Column(name = "status")
	private String status;
	@Column(name = "ifscCode")
	private String ifscCode;
	@Column(name = "branch")
	private String branch;
	@Column(name = "micrCode")
	private String micrCode;
	@Column(name = "currency")
	private String currency;
	@Column(name = "facility")
	private String facility;
	@Column(name = "longitude")
	private String longitude;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "fipId")
	private String fipId;
	@Column(name = "linkAccRefNumber")
	private String linkAccRefNumber;
	@JsonIgnore
	@Column(name = "isAccountLinked")
	private String isAccountLinked;
	@JsonIgnore
	@Column(name = "createdDateTime")
	private String createdDateTime;
	@JsonIgnore
	@Column(name = "lastUpdatedDateTime")
	private String lastUpdatedDateTime;
	@Column(name = "version")
	@JsonIgnore
	private Integer version;
	@Transient
	private UserInfo userInfo;
	@Transient
	private Map<String, Object> additionalProperties = new HashMap<>();
}