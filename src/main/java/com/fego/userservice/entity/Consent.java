package com.fego.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consent", schema = "PUBLIC")
public class Consent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long id;
	@Column(name = "userId", nullable = false)
	private long userId;
	@Column(name = "consentId")
	private String consentId;
	@Column(name = "customerVua")
	private String customerVua;
	@Column(name = "consentStart")
	private String consentStart;
	@Column(name = "consentExpiry")
	private String consentExpiry;
	@Column(name = "fiDataFrom")
	private String fiDataFrom;
	@Column(name = "fiDataTo")
	private String fiDataTo;
	@Column(name = "frequencyUnit")
	private String frequencyUnit;
	@Column(name = "frequencyValue")
	private Integer frequencyValue;
}