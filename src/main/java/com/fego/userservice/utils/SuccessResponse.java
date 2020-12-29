package com.fego.userservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fego.userservice.constants.Constants;

/**
 * <p>
 * Generic success response
 * </p>
 *
 * @author Rajasekar Nagarajan created on March 30, 2020
 *
 * @param <T>
 */
public class SuccessResponse<T> extends ResponseEntity<Object> {

    public SuccessResponse(String message, HttpStatus responseCode) {
        this(message, new EmptyJsonResponse(), responseCode);
    }

    public SuccessResponse(OneBasedIndexedPage<T> entity, HttpStatus responseCode) {
        this(Constants.OK, entity, responseCode);
    }

    public SuccessResponse(Object entity, HttpStatus responseCode) {
        this(Constants.OK, entity, responseCode);
    }

    public SuccessResponse(HttpStatus responseCode) {
        this(Constants.OK, new EmptyJsonResponse(), responseCode);
    }

    public SuccessResponse(String message, Object entity, HttpStatus httpStatus) {
        super(new SuccessMessage<T>(message, entity, httpStatus.value()), httpStatus);
    }
}