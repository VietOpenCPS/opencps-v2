package org.opencps.statistic.rest.engine.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.liferay.portal.kernel.util.Validator;

public class StatisticUtils {

	public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public static Date convertStringToDate(String source) {

		if (Validator.isNotNull(source)) {
			try {
				return SIMPLE_DATE_TIME_FORMAT.parse(source);


			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}

	}

}
