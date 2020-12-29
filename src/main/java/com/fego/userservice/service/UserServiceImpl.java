package com.fego.userservice.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fego.userservice.consent.dto.Holder;
import com.fego.userservice.consent.dto.Holders;
import com.fego.userservice.consent.dto.Summary;
import com.fego.userservice.constants.Constants;
import com.fego.userservice.dto.ConsentApproveRequestDto;
import com.fego.userservice.dto.ConsentApproveResponseDto;
import com.fego.userservice.dto.ConsentArtefactData;
import com.fego.userservice.dto.ConsentOTPRefResponseDto;
import com.fego.userservice.dto.ConsentRejectRequestDto;
import com.fego.userservice.dto.ConsentRejectResponseDto;
import com.fego.userservice.dto.ConsentRequestBodyDto;
import com.fego.userservice.dto.ConsentTransactionResponseDto;
import com.fego.userservice.dto.CustomerRegistrationRequestDto;
import com.fego.userservice.dto.CustomerRegistrationResponseDto;
import com.fego.userservice.dto.CustomerRegistrationVerifyDto;
import com.fego.userservice.dto.LinkAccRequestDto;
import com.fego.userservice.dto.LinkAccResponseDto;
import com.fego.userservice.dto.LinkAccVerifyRequestDto;
import com.fego.userservice.dto.LinkAccountVerificationDto;
import com.fego.userservice.dto.ProfileDiscoverAccountResponseDto;
import com.fego.userservice.dto.TransactionRequestDto;
import com.fego.userservice.dto.UserData;
import com.fego.userservice.entity.Accounts;
import com.fego.userservice.entity.Address;
import com.fego.userservice.entity.Consent;
import com.fego.userservice.entity.User;
import com.fego.userservice.repository.AccountRepository;
import com.fego.userservice.repository.AddressRepository;
import com.fego.userservice.repository.ConsentRepository;
import com.fego.userservice.repository.UserRepository;
import com.fego.userservice.utils.RestTemplateUtil;
import com.fego.userservice.utils.UtcTimeUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final AccountRepository accountRepository;
	private final ConsentRepository consentRepository;
	private final AmazonS3 amazonS3;
	private final RestTemplate template;
	private final RestTemplateUtil restTemplateUtil;
	private String from = StringUtils.EMPTY;
	private String to = StringUtils.EMPTY;

	@Value("${integration.baseUrl}")
	String baseURLOfIntegration;

	@Value("${integration.otpSend}")
	String otpSendURL;

	@Value("${integration.otpVerify}")
	String otpVerifyURL;

	@Value("${integration.linkAccount}")
	String linkAccountURL;

	@Value("${integration.verifyAccountLink}")
	String verifyAccountLinkURL;

	@Value("${integration.consentRequest}")
	String consentRequestURL;

	@Value("${integration.approveConsentRequest}")
	String approveConsentRequestURL;

	@Value("${integration.rejectConsentRequest}")
	String rejectConsentRequestURL;

	@Value("${aws.bucketName}")
	private String bucketName;

	@Value("${transaction.baseURL}")
	private String txnBaseURL;

	@Value("${transaction.save}")
	private String txnsaveURL;

	public UserServiceImpl(UserRepository userRepository, RestTemplateUtil restTemplateUtil,
			AccountRepository accountRepository, AddressRepository addressRepository,
			ConsentRepository consentRepository, AmazonS3 amazonS3, RestTemplate template) {
		this.userRepository = userRepository;
		this.restTemplateUtil = restTemplateUtil;
		this.accountRepository = accountRepository;
		this.addressRepository = addressRepository;
		this.consentRepository = consentRepository;
		this.amazonS3 = amazonS3;
		this.template = template;
	}

	public CustomerRegistrationResponseDto getOtpReference(CustomerRegistrationRequestDto userDto) {
		return restTemplateUtil.postForEntity(CustomerRegistrationResponseDto.class, baseURLOfIntegration + otpSendURL,
				userDto);
	}

	public List<Accounts> verifyOtp(CustomerRegistrationVerifyDto customerRegistrationVerifyDto) {
		ProfileDiscoverAccountResponseDto profileDiscoverAccountResponseDto = restTemplateUtil.postForEntity(
				ProfileDiscoverAccountResponseDto.class, baseURLOfIntegration + otpVerifyURL,
				customerRegistrationVerifyDto);
		UserData userData = profileDiscoverAccountResponseDto.getProfileResponseDto().getUserData();
		long userIdFromDb = userRepository
				.save(convertUserDataToUserDto(userData, customerRegistrationVerifyDto.getOtpReference())).getId();
		saveAddress(userIdFromDb);
		log.info("User has been saved in the table");
		List<Accounts> accountsList = new ArrayList<>();
		profileDiscoverAccountResponseDto.getDiscoverAccResponseDto().getAccount().stream().forEach(action -> {
			Accounts account = new Accounts();
			String utcTime = UtcTimeUtil.currentUtcDateTime();
			account.setUserId(userIdFromDb);
			account.setProductNumber(action.getData().getAccRefNumber());
			account.setMaskedProductNumber(action.getData().getMaskedAccNumber());
			account.setProductType(action.getData().getAccType());
			account.setIsAccountLinked(Constants.FALSE_VALUE);
			account.setAdditionalProperties(action.getData().getAdditionalProperties());
			account.setFipId(action.getData().getFipId());
			account.setUserInfo(action.getData().getUserInfo());
			account.setType(action.getType());
			account.setCreatedDateTime(utcTime);
			account.setLastUpdatedDateTime(utcTime);
			account.setVersion(Constants.INTIAL_VALUE);
			accountsList.add(account);
		});
		accountRepository.saveAll(accountsList);
		return accountsList;
	}

	private void saveAddress(long userId) {
		Address address = new Address();
		address.setUserId(userId);
		long addressId = addressRepository.save(address).getId();
		updateUser(userId, addressId);
	}

	private void updateUser(long userId, long addressId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User updateUser = user.get();
			updateUser.setAddressId(addressId);
			userRepository.save(updateUser);
		} else {
			log.error("The requested User is not found in DataBase.");
		}
	}

	private User convertUserDataToUserDto(UserData userData, String otpReference) {
		String utcDateTime = UtcTimeUtil.currentUtcDateTime();
		User user = new User();
		user.setCreatedDateTime(utcDateTime);
		user.setLastUpdatedDateTime(utcDateTime);
		user.setUserId(userData.getUserID());
		user.setMobile(userData.getMobile());
		user.setName(userData.getFirstName());
		user.setVua(userData.getVua());
		user.setEmailId(userData.getEmail());
		user.setTermsAndConditions(
				userData.getTermsAndConditions() != null ? userData.getTermsAndConditions().toString() : "");
		user.setOtpReference(otpReference);
		user.setVersion(Constants.INTIAL_VALUE);
		user.setIsOtp(Constants.TRUE_VALUE);
		return user;
	}

	public LinkAccResponseDto link(LinkAccRequestDto linkAccRequestDto) {
		return restTemplateUtil.postForEntity(LinkAccResponseDto.class, baseURLOfIntegration + linkAccountURL,
				linkAccRequestDto);
	}

	public LinkAccountVerificationDto verifyLinkAccount(LinkAccVerifyRequestDto linkAccVerifyRequestDto) {
		LinkAccountVerificationDto linkAccountVerificationDto = restTemplateUtil.postForEntity(
				LinkAccountVerificationDto.class, baseURLOfIntegration + verifyAccountLinkURL, linkAccVerifyRequestDto);
		if (linkAccountVerificationDto.getStatus().equals(Constants.SUCCESS)) {
			log.info("The response from the Integration part is Success");
			Accounts accountUpdate = accountRepository
					.findByProductNumber(linkAccountVerificationDto.getAccountRefNumber());
			if (Objects.nonNull(accountUpdate)) {
				accountUpdate.setLinkAccRefNumber(linkAccountVerificationDto.getLinkRefNumber());
				accountUpdate.setLastUpdatedDateTime(UtcTimeUtil.currentUtcDateTime());
				accountUpdate.setVersion(accountUpdate.getVersion() + 1);
				accountUpdate.setIsAccountLinked(Constants.TRUE_VALUE);
				accountRepository.save(accountUpdate);
				log.info("The Account {} has been updated with the value - {}", accountUpdate.getMaskedProductNumber(),
						accountUpdate.getIsAccountLinked());
			}
		} else {
			log.info("The response from the Integration part is Failure.");
		}
		return linkAccountVerificationDto;
	}

	public ConsentOTPRefResponseDto requestConsentDetails(ConsentRequestBodyDto consentRequestBodyDto) {
		from = consentRequestBodyDto.getFiDataRange().getFrom();
		to = consentRequestBodyDto.getFiDataRange().getTo();
		return restTemplateUtil.postForEntity(ConsentOTPRefResponseDto.class, baseURLOfIntegration + consentRequestURL,
				consentRequestBodyDto);
	}

	public ConsentApproveResponseDto approveConsent(ConsentApproveRequestDto consentApproveRequestDto) {
		ConsentTransactionResponseDto consentTransactionResponseDto = restTemplateUtil.postForEntity(
				ConsentTransactionResponseDto.class, baseURLOfIntegration + approveConsentRequestURL,
				consentApproveRequestDto);
		if (consentTransactionResponseDto.getConsentApproveResponseDto().getStatus().equals(Constants.SUCCESS)) {
			Holders holders = consentTransactionResponseDto.getSavingsDecryptedJSONResponseDto().getAccount()
					.getProfile().getHolders();
			Holder holder = holders.getHolder();
			ConsentArtefactData artefactData = consentTransactionResponseDto.getConsentArtefactData();
			String mobile = artefactData.getCustomerID();
			long userId = userRepository.findByMobileNumber(holder.getMobile()).getId();
			saveConsentIntoTable(userId, mobile, artefactData);
			updateUserDetails(holder, holders);
			String accRefNumberFromDb = consentTransactionResponseDto.getSavingsDecryptedJSONResponseDto().getAccount()
					.getLinkedAccRef();
			Summary summary = consentTransactionResponseDto.getSavingsDecryptedJSONResponseDto().getAccount()
					.getSummary();
			updateAccountDetails(accRefNumberFromDb, summary, holder);
			saveJsonFileIntoS3(consentTransactionResponseDto);
			saveTxnDetails(userId, consentTransactionResponseDto);

		}
		return consentTransactionResponseDto.getConsentApproveResponseDto();
	}

	private void saveTxnDetails(long userId, ConsentTransactionResponseDto consentTransactionResponseDto) {
		TransactionRequestDto txnRequestDto = new TransactionRequestDto();
		txnRequestDto.setUserId(userId);
		txnRequestDto
				.setSavingsDecryptedJSONResponseDto(consentTransactionResponseDto.getSavingsDecryptedJSONResponseDto());
		txnRequestDto.setMfDecryptedJSONResponseDto(consentTransactionResponseDto.getMfDecryptedJSONResponseDto());
		txnRequestDto.setRdDecryptedJSONResponseDto(consentTransactionResponseDto.getRdDecryptedJSONResponseDto());
		txnRequestDto.setTdDecryptedJSONResponseDto(consentTransactionResponseDto.getTdDecryptedJSONResponseDto());
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set(Constants.AUTHORIZATION,
				"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB0cmliZXR0ZXIuY29tIiwiZXhwIjoxNjA5MjM0OTI2LCJpYXQiOjE2MDkyMTY5MjZ9.bxRmMO49sRhFipOyoNQ5wIE7gaWf8gjAepCt81kg4g77QO8XcXShroxvAqmTw2ClKVQYmlw33P2rM-d_oB5q0A");
		HttpEntity<TransactionRequestDto> entity = new HttpEntity<>(txnRequestDto, header);
		ResponseEntity<ConsentRejectResponseDto> consentRejectResponseDto = template.exchange(txnBaseURL + txnsaveURL,
				HttpMethod.POST, entity, ConsentRejectResponseDto.class);
		log.info("The status returned from the txn service is - {}", consentRejectResponseDto.getBody().getStatus());
	}

	private void saveJsonFileIntoS3(ConsentTransactionResponseDto consentTransactionResponseDto) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = StringUtils.EMPTY;
		try {
			jsonString = mapper.writeValueAsString(consentTransactionResponseDto);
		} catch (JsonProcessingException e) {
			log.error("Problem in Converting Object into JSON {}", e.getMessage());
		}
		try {
			File myObj = new File("txn.json");
			FileWriter fileWriter = new FileWriter(myObj);
			fileWriter.write(jsonString);
			fileWriter.flush();
			fileWriter.close();
			final String uniqueFileName = LocalDateTime.now() + "_" + myObj.getName();
			log.info("Uploading file with name= {}", uniqueFileName);
			final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, myObj);
			amazonS3.putObject(putObjectRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateAccountDetails(String accRefNumberFromDb, Summary summary, Holder holder) {
		Accounts account = accountRepository.findByLinkAccRefNumber(accRefNumberFromDb);
		account.setCurrentOdLimit(summary.getCurrentODLimit());
		account.setCurrency(summary.getCurrency());
		account.setBranch(summary.getBranch());
		account.setIfscCode(summary.getIfscCode());
		account.setFacility(summary.getFacility());
		account.setOpeningDate(summary.getOpeningDate());
		account.setMicrCode(summary.getMicrCode());
		account.setFullName(holder.getName());
		account.setDrawingLimit(summary.getDrawingLimit());
		account.setStatus(summary.getStatus());
		account.setLastUpdatedDateTime(UtcTimeUtil.currentUtcDateTime());
		account.setVersion(account.getVersion() + 1);
		account.setProductType(summary.getType());
		account.setNominee(holder.getNominee());
		accountRepository.save(account);
		log.info("Account details has been updated in the table.");
	}

	private void updateUserDetails(Holder holder, Holders holders) {
		User user = userRepository.findByMobileNumber(holder.getMobile());
		user.setDob(holder.getDob());
		user.setEmailId(holder.getEmail());
		user.setName(holder.getName());
		user.setPan(holder.getPan());
		user.setIsKycVerfied(holder.getCkycCompliance());
		user.setType(holders.getType());
		user.setLastUpdatedDateTime(UtcTimeUtil.currentUtcDateTime());
		user.setVersion(user.getVersion() + 1);
		userRepository.save(user);
		log.info("User Details has been updated in the Database.");
	}

	private void saveConsentIntoTable(long userId, String mobile, ConsentArtefactData artefactData) {
		Consent consent = new Consent();
		consent.setUserId(userId);
		consent.setConsentId(artefactData.getConsentId());
		consent.setCustomerVua(mobile);
		consent.setConsentStart(artefactData.getConsentStart());
		consent.setConsentExpiry(artefactData.getConsentExpiry());
		consent.setFrequencyUnit(artefactData.getFrequencyUnit());
		consent.setFrequencyValue(artefactData.getFrequencyValue());
		consent.setFiDataFrom(from);
		consent.setFiDataTo(to);
		consentRepository.save(consent);
		from = StringUtils.EMPTY;
		to = StringUtils.EMPTY;
		log.info("Consent Details has been saved in the Database.");
	}

	public ConsentRejectResponseDto rejectConsent(ConsentRejectRequestDto consentRejectRequestDto) {
		return restTemplateUtil.postForEntity(ConsentRejectResponseDto.class,
				baseURLOfIntegration + rejectConsentRequestURL, consentRejectRequestDto);
	}
}