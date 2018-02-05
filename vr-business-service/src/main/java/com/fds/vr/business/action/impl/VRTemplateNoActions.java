package com.fds.vr.business.action.impl;

import java.util.Calendar;
import java.util.Date;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;

public class VRTemplateNoActions {
	public String getTemplateNo(String pattern) {
		String patternNumber = StringPool.BLANK;
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date());
		
		int currYear = cal.get(Calendar.YEAR);
		
		
		
		//Counter counter = CounterLocalServiceUtil.getCounter();
		
		return null;
	}
	
	private void _initCounter(String pattern, int year) {
		
		try {
			Counter counter = CounterLocalServiceUtil.getCounter(pattern+year);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
