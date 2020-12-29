package com.fego.userservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fego.userservice.constants.Constants;

public class UtcTimeUtil {

	public static String currentUtcDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
		return sdf.format(new Date());
	}
}