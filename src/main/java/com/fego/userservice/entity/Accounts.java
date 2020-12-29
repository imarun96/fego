package com.fego.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account", schema = "PUBLIC")
public class Accounts extends Data {
	@JsonProperty("type")
	private String type;
}