package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.applicantdata.model.ApplicantDataDetailModel;
import org.opencps.api.controller.ApplicantDataManagement;
import org.opencps.api.controller.util.ApplicantDataUtils;
import org.opencps.usermgt.model.ApplicantData;
import org.opencps.usermgt.service.ApplicantDataLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ApplicantDataManagementImpl implements ApplicantDataManagement {

	@Override
	public Response addApplicantData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Attachment file, String fileNo, String fileName,
			String applicantIdNo) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
		ApplicantData applicantData = null;
		
		try {
			applicantData = ApplicantDataLocalServiceUtil.createApplicantData(groupId, fileNo, fileName, applicantIdNo, dataHandler.getName(), dataHandler.getInputStream(), serviceContext);
			ApplicantDataDetailModel result = ApplicantDataUtils.mappingToApplicantDataModel(applicantData);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
