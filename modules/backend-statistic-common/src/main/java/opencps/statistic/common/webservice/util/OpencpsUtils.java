/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package opencps.statistic.common.webservice.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author khoavu
 *
 */
public final class OpencpsUtils {

	private static final String ISO_LOCALE_SEPERATOR = "_";
	private static final String ONSTAR_LOCALE_SEPERATOR = "-";

	private static final String TIMESTAMP_SEPERATOR = "T";
	private static final String TIMESTAMP_POST_FIX = "Z";
	private static final String TIMESTAMP_DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIMESTAMP_TIME_FORMAT = "hh:mm:ss.sss";

	public static final long ADVISOR_ID = 300034733L;
	public static final String ADVISOR_LOGIN_NAME = "webadvisor";

	public static final String ISO_LOCALE_ENGLISH_US = "en_US";

	public static final String ISO_LOCALE_SPANISH_MX = "es_MX";
	public static final String ISO_LOCALE_SPANISH_US = "es_US";

	public static final String ISO_COUNTRY_ID_MX = "MX";
	public static final String ISO_COUNTRY_ID_US = "US";

	private static final Logger LOG = LoggerFactory.getLogger(OpencpsUtils.class);

	private OpencpsUtils() {
		throw new UnsupportedOperationException("OpencpsUtils cannot be instantiated");
	}


	public static String formatISOToOnStarLocale(String localeString) {
		return (localeString == null ? null : localeString.replace(ISO_LOCALE_SEPERATOR, ONSTAR_LOCALE_SEPERATOR));
	}


	public static String formatOnStarToISOLocale(String localeString) {

		return (localeString == null ? null : localeString.replace(ONSTAR_LOCALE_SEPERATOR, ISO_LOCALE_SEPERATOR));
	}

	public static String changeToOnStarAcceptedLocale(String localeString) {

		if (!StringUtils.equalsIgnoreCase(localeString, ISO_LOCALE_SPANISH_US)) {
			return localeString;
		}

		return ISO_LOCALE_SPANISH_MX;
	}


	public static String getSystemTimeStampInOnStarFormat() {
		return getTimeStampInOnStarFormat(new Date());
	}


	public static String getTimeStampInOnStarFormat(Date date) {
		return formatDate(TIMESTAMP_DATE_FORMAT, date) + TIMESTAMP_SEPERATOR + formatDate(TIMESTAMP_TIME_FORMAT, date)
				+ TIMESTAMP_POST_FIX;
	}


	public static String formatDate(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}


	public static String getLanguageCode(String locale) {
		return (locale == null ? null : locale.substring(0, locale.indexOf(ISO_LOCALE_SEPERATOR)));
	}


	public static String getCountryCode(String locale) {
		return (locale == null ? null : locale.substring(locale.indexOf(ISO_LOCALE_SEPERATOR) + 1));
	}


	public static boolean orEquals(Object e, Object... objects) {
		if (e != null && objects != null && objects.length > 0) {
			for (Object obj : objects) {
				if (e.equals(obj)) {
					return true;
				}
			}
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	public static <T, C extends Collection<T>> C filter(C collection, Predicate<T> filter) {
		final boolean isDebugEnabled = LOG.isDebugEnabled();
		if (collection == null || collection.isEmpty()) {
			return (C) Collections.emptyList();
		}

		C newCollection = null;
		try {
			newCollection = (C) collection.getClass().newInstance();
			for (T t : collection) {
				if (filter.test(t)) {
					newCollection.add(t);
				}
			}
		} catch (InstantiationException e) {
			LOG.error("Error:", e);
			if (isDebugEnabled) {
				LOG.debug("Dropping InstantiationException", e.getMessage());
			}
		} catch (IllegalAccessException e) {
			LOG.error("Error:", e);
			if (isDebugEnabled) {
				LOG.debug("Dropping IllegalAccessException", e.getMessage());
			}
		}

		return newCollection;
	}


	public static boolean isNotNull(Object obj) {
		return (null != obj);
	}


	public static boolean compareString(String string1, String string2) {
		return StringUtils.equals(StringUtils.defaultString(string1), StringUtils.defaultString(string2));
	}

	public static String getISOLocaleSeparator() {
		return ISO_LOCALE_SEPERATOR;
	}

	public static void logAsFormattedJson(Logger logger, Object obj){
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String contentFormatted = mapper.writeValueAsString(obj);
//            logger.debug("REST request: \n {}", contentFormatted);
//        } catch (JsonProcessingException e) {
//        	logger.error("Error:", e);
//            logger.debug("Error printing REST request! {}", e);
//        }
    }

}