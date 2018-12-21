package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SMSManagement;
import org.opencps.communication.sms.utils.ViettelSMSUtils;

import backend.auth.api.exception.BusinessExceptionImpl;

public class SMSManagementImpl implements SMSManagement {

	@Override
	public Response sendSMS(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String body, String toTelNo) {
		try {
			ViettelSMSUtils.sendSMS(body, StringPool.BLANK, "84916676884");
			
			return Response.status(200).entity(StringPool.BLANK).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
