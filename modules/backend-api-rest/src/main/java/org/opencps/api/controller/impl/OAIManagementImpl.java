package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.OAIManagement;
import org.opencps.api.controller.util.OAIBuilderUtils;
import org.opencps.api.oai.model.oaipmh.OAIPMHtype;
import org.opencps.api.oai.model.oaipmh.RequestType;

public class OAIManagementImpl implements OAIManagement {

	private static final Log _log = LogFactoryUtil.getLog(OAIManagementImpl	.class);

	@Override
	public Response getOAI(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, RequestType query) {

		_log.debug("oaiQuery=" + query);

		OAIBuilderUtils builder = new OAIBuilderUtils(query);
		OAIPMHtype results = builder.getResults();

		return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
	}

}
