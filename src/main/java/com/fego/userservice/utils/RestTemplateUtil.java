package com.fego.userservice.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@Component
public class RestTemplateUtil {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);
	private static final String NO_DATA_FOUND = "No data found";

	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;

	@Autowired
	public RestTemplateUtil(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
		this.restTemplate = restTemplateBuilder.build();
		this.objectMapper = objectMapper;
	}

	public <T> T getForEntity(Class<T> clazz, String url, Object... uriVariables) {
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);
			JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
			return readValue(response, javaType);
		} catch (HttpClientErrorException exception) {
			if (exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				logger.info("{} - {}", NO_DATA_FOUND, url);
			} else {
				logger.info("Rest Client Exception {}", exception.getMessage());
			}
		}
		return null;
	}

	public <T> List<T> getForList(Class<T> clazz, String url, Object... uriVariables) {
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);
			objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
			return readValue(response, collectionType);
		} catch (HttpClientErrorException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.info("{} - {}", NO_DATA_FOUND, url);
			} else {
				logger.info("Rest Client Exception - {}", exception.getMessage());
			}
		}
		return Collections.emptyList();
	}

	public <T, R> T postForEntity(Class<T> clazz, String url, R body, Object... uriVariables) {
		HttpEntity<R> request = new HttpEntity<>(body);
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class, uriVariables);
		JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
		return readValue(response, javaType);
	}

	public <T, R> T putForEntity(Class<T> clazz, String url, R body, Object... uriVariables) {
		HttpEntity<R> request = new HttpEntity<>(body);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class,
				uriVariables);
		JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
		return readValue(response, javaType);
	}

	public void delete(String url, Object... uriVariables) {
		try {
			restTemplate.delete(url, uriVariables);
		} catch (RestClientException e) {
			logger.error("Exception occured - {}", e.getMessage());
		}
	}

	private <T> T readValue(ResponseEntity<String> response, JavaType javaType) {
		T result = null;
		if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
			try {
				result = objectMapper.readValue(response.getBody(), javaType);
			} catch (IOException e) {
				logger.error("Exception occured - {}", e.getMessage());
			}
		} else {
			logger.info("{} - {}", NO_DATA_FOUND, response.getStatusCode());
		}
		return result;
	}
}