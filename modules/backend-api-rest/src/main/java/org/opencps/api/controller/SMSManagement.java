
package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.sms.model.IPacificSearchSMS;
import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/sms")
@Api(value = "/sms", tags = "sms")
public interface SMSManagement {

	@POST
	@Path("/test/sendmt")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a applicant was created", response = ApplicantModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response sendSMS(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @FormParam("body") String body,
		@FormParam("toTelNo") String toTelNo);

	@GET
	@Path("/inet")
	@Consumes({
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	public Response getInetSMS(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@BeanParam IPacificSearchSMS query);

	@GET
	@Path("/zaloid")
	@Consumes({
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	public Response getZaloUIdByTelNo(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@FormParam("toTelNo") String toTelNo);

	@GET
	@Path("/test/calculate/duedate")
	@Consumes({
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	public Response calculateDueDate(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@FormParam("startDate") String startDate,
		@FormParam("durationCount") double durationCount,
		@FormParam("durationUnit") int durationUnit,
		@FormParam("groupId") long groupId);

}
