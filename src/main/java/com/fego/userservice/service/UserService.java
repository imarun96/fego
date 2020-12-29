package com.fego.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

@Service
public interface UserService {
	public CustomerRegistrationResponseDto getOtpReference(CustomerRegistrationRequestDto userDto);

	public List<Accounts> verifyOtp(CustomerRegistrationVerifyDto customerRegistrationVerifyDto);

	public LinkAccResponseDto link(LinkAccRequestDto linkAccRequestDto);

	public LinkAccountVerificationDto verifyLinkAccount(LinkAccVerifyRequestDto linkAccVerifyRequestDto);

	public ConsentApproveResponseDto approveConsent(ConsentApproveRequestDto consentApproveRequestDto);

	public ConsentRejectResponseDto rejectConsent(ConsentRejectRequestDto consentRejectRequestDto);

	public ConsentOTPRefResponseDto requestConsentDetails(ConsentRequestBodyDto consentRequestBodyDto);
}
