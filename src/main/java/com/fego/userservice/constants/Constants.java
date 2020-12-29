package com.fego.userservice.constants;

/**
 * <p>
 * Common constants variable user all over the application.
 * </p>
 * 
 * @author Rajasekar Nagarajan created on March 30, 2020
 */
public final class Constants {

	private Constants() {
	}

	// Symbols
	public static final String ASTERISK_SYMBOL = "*";
	public static final String FORWARD_SLASH = "/";
	public static final String CARET = "^";
	public static final String HYPHEN = "-";
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String DOT = ".";
	public static final String UNDERSCORE = "_";
	public static final String NEW_LINE = "\n";

	// Common
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String WARNING = "WARNING";
	public static final String INFO = "INFO";
	public static final String UTF_8 = "UTF-8";
	public static final String KEYS = "keys";
	public static final String OK = "OK";
	public static final boolean FALSE = false;
	public static final boolean TRUE = true;
	public static final String PROFILE_ACTIVE_PRODUCTION = "production";
	public static final String DEFAULT_PAGE_NUMBER = "1";
	public static final String DEFAULT_PAGE_SIZE = "10";

	// Spring Components and warning
	public static final String SERIAL = "serial";
	public static final String PROPERTY_SOURCE = "classpath:application.properties";

	public static final String SORT_BY = "sortBy";
	public static final String DEFAULT_SORT_COLUMN = "createdAt";
	public static final String DIRECTION = "direction";
	public static final String EMPTY = "";

	public static final String SUB_TOPIC_ALL = "All";
	public static final String CAMEL_SITE_IMAGE = "siteImage";
	public static final String CAMEL_SITE_DESCRIPTION = "siteDescription";
	public static final String CAMEL_SITE_TITLE = "siteTitle";
	public static final String CONTENT = "content";
	public static final String META_TITLE = "meta[property=og:title]";
	public static final String META_DESCRIPTION = "meta[property=og:description]";
	public static final String META_IMAGE = "meta[property=og:image]";
	public static final String PROBLEM_WHILE_FETCHING_URL_DATA = "Problem while fetching data from url ";

	public static final String DEFAULT_PASSWORD = "test1$3";
	public static final String NOT_APPLICABLE = "N/A";
	public static final String HTTP = "http";
	public static final String IMAGE_REGEX = "link[href~=.*\\.(ico|png|jpg|jpeg|svg)]";
	public static final String IMG = "img";
	public static final String SRC = "src";
	public static final String HREF = "href";
	public static final String GENERAL = " General";
	public static final String CHROME_USER_AGENT = "Chrome/85.0.4183.102";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_ZONE = "UTC";
	public static final String FALSE_VALUE = "false";
	public static final String TRUE_VALUE = "true";
	public static final Integer INTIAL_VALUE = 1;
	public static final String AUTHORIZATION = "Authorization";

	public static final String SYSKEY = "SYSKEY";
	public static final String UNCHECKED = "UNCHECKED";
	public static final String OTP_REFERRRENCE = "bc624de2-6eae-4d5b-9e30-5e30079bf050";
	public static final String SESSION_ID = "4f79a896d9b26027117eb188ed5d2d7f:1fe99b1fbac4cc2e3806ab1f862a6ed0214d12a477a9abf570da8e1f9f9d366a779926c49912d237634bc84086d8e1dc7a4098647ec3905bdc7945cdaf8239eb";
}