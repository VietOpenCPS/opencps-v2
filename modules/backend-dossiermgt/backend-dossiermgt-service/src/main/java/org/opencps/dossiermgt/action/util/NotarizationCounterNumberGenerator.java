package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.UUID;
import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.constants.ConstantsUtils;

public class NotarizationCounterNumberGenerator {
	private static Log _log = LogFactoryUtil.getLog(ConfigCounterNumberGenerator.class.getName());

	public static String generateReferenceUID(long groupId) {

		return UUID.randomUUID().toString();
	}
	
	public static long countByServiceCode(String serviceCode, String govAgencyCode) {
			
			long _counterNumber = 0;
			Date now = new Date();
			try {
				String year = String.valueOf(DateTimeUtils.getYearFromDate(now));
				String certConfigName = ConstantsUtils.PRE_FIX_COUNTER + govAgencyCode 
						+ "-" + year + "-" + serviceCode;
				
				_log.info("___certConfigId" + certConfigName);
			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigName);
	
			if (Validator.isNotNull(counterConfig)) {
				// create counter config
				_counterNumber = counterConfig.getCurrentId() + 1;
				//
				counterConfig.setCurrentId(_counterNumber);
				CounterLocalServiceUtil.updateCounter(counterConfig);
					
				} else {
					_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");
					counterConfig = CounterLocalServiceUtil.createCounter(certConfigName);
					//increment CurrentCounter 
					counterConfig.setCurrentId(1);
					_counterNumber = 1;
					CounterLocalServiceUtil.updateCounter(counterConfig);
				}
		} catch (Exception e) {
			_log.debug(e);
		}
	
		return _counterNumber;
	
	}
}
