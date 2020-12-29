package com.fego.userservice.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fego.userservice.annotation.ApiPageable;
import com.fego.userservice.dto.ConsentApproveRequestDto;
import com.fego.userservice.dto.ConsentApproveResponseDto;
import com.fego.userservice.dto.ConsentOTPRefResponseDto;
import com.fego.userservice.dto.ConsentRejectRequestDto;
import com.fego.userservice.dto.ConsentRejectResponseDto;
import com.fego.userservice.dto.ConsentRequestBodyDto;
import com.fego.userservice.dto.CustomerRegistrationRequestDto;
import com.fego.userservice.dto.CustomerRegistrationResponseDto;
import com.fego.userservice.dto.CustomerRegistrationVerifyDto;
import com.fego.userservice.dto.LinkAccRequestDto;
import com.fego.userservice.dto.LinkAccResponseDto;
import com.fego.userservice.dto.LinkAccVerifyRequestDto;
import com.fego.userservice.dto.LinkAccountVerificationDto;
import com.fego.userservice.entity.Accounts;
import com.fego.userservice.service.UserService;
import com.fego.userservice.utils.SuccessResponse;

import io.swagger.annotations.Api;

@Api(tags = "User Controller")
@RestController
@RequestMapping(path = "v1/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Transactional
	@ApiPageable
	@PostMapping(path = "/register")
	public SuccessResponse<CustomerRegistrationResponseDto> add(
			@RequestBody @Valid CustomerRegistrationRequestDto userRegistraionDto) {
		return new SuccessResponse<CustomerRegistrationResponseDto>(userService.getOtpReference(userRegistraionDto),
				HttpStatus.OK);
	}

	@Transactional
	@ApiPageable
	@PostMapping(path = "/otp_verify")
	public SuccessResponse<List<Accounts>> otpVerify(
			@RequestBody @Valid CustomerRegistrationVerifyDto customerRegistrationVerifyDto) {
		return new SuccessResponse<List<Accounts>>(userService.verifyOtp(customerRegistrationVerifyDto), HttpStatus.OK);
	}

	@Transactional
	@ApiPageable
	@PostMapping(path = "/link_account")
	public SuccessResponse<LinkAccResponseDto> link(@RequestBody @Valid LinkAccRequestDto linkAccRequestDto) {
		return new SuccessResponse<LinkAccResponseDto>(userService.link(linkAccRequestDto), HttpStatus.OK);
	}

	@Transactional
	@ApiPageable
	@PostMapping(path = "/verify_link_account")
	public SuccessResponse<LinkAccountVerificationDto> verifyLinkedAccount(
			@RequestBody @Valid LinkAccVerifyRequestDto linkAccVerifyRequestDto) {
		return new SuccessResponse<LinkAccountVerificationDto>(userService.verifyLinkAccount(linkAccVerifyRequestDto),
				HttpStatus.OK);
	}

	@Transactional
	@ApiPageable
	@PostMapping(path = "/consent_request")
	public SuccessResponse<ConsentOTPRefResponseDto> requestConsent(
			@RequestBody ConsentRequestBodyDto consentRequestBodyDto) {
		return new SuccessResponse<ConsentOTPRefResponseDto>(userService.requestConsentDetails(consentRequestBodyDto),
				HttpStatus.OK);
	}

	@Transactional
	@ApiPageable
	@PostMapping(path = "/approve_consent")
	public SuccessResponse<ConsentApproveResponseDto> approveConsent(
			@RequestBody ConsentApproveRequestDto consentApproveRequestDto) {
		return new SuccessResponse<ConsentApproveResponseDto>(userService.approveConsent(consentApproveRequestDto),
				HttpStatus.OK);
	}

	@Transactional
	@ApiPageable
	@PostMapping(path = "/reject_consent")
	public SuccessResponse<ConsentRejectResponseDto> rejectConsent(
			@RequestBody ConsentRejectRequestDto consentApproveRequestDto) {
		return new SuccessResponse<ConsentRejectResponseDto>(userService.rejectConsent(consentApproveRequestDto),
				HttpStatus.OK);
	}
}