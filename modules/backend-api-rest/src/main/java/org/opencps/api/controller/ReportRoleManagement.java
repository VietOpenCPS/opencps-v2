package org.opencps.api.controller;

import com.liferay.portal.kernel.service.ServiceContext;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.reportrole.model.ReportRoleInputCodeModel;

import io.swagger.annotations.ApiParam;

public interface ReportRoleManagement {
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addReportRoleCode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "json object store ReportRoleModel") @BeanParam ReportRoleInputCodeModel input);

}
