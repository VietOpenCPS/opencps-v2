package org.opencps.statistic.rest.engine.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class StatisticUtils {

	private static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss";
	private static Log _log = LogFactoryUtil.getLog(StatisticUtils.class);

	public static Date convertStringToDate(String source) {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		if (sdf != null && Validator.isNotNull(source)) {
			try {
				return sdf.parse(source);

			} catch (ParseException e) {
				_log.error(e);
			}
		}
		return null;
	}

}
