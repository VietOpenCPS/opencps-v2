package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.opencps.api.registration.model.RegistrationResultsModel;
import org.opencps.api.registrationlog.model.RegistrationLogModel;
import org.opencps.api.registrationlog.model.RegistrationLogSearchModel;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/registrations", description = "APIs for Deliverables")
public interface RegistrationLogManagement {

	@GET
	@Path("/registrations/{id}/logs")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response getRegistrationLogsbyRegId(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("id") long registrationId);

	@POST
	@Path("/registrations/{id}/logs")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "addRegistrationByRegistrationId)", response = RegistrationLogModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the RegistrationgModel was updated", response = RegistrationResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response addRegistrationByRegistrationId(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of registration", required = true) @PathParam("id") long id,
			@ApiParam(value = "Metadata of RegistrationLog") @Multipart("author") String author,
			@ApiParam(value = "Metadata of RegistrationLog") @Multipart("payload") String payload,
			@ApiParam(value = "Metadata of RegistrationLog") @Multipart("content") String content);

//search Lucene
	@GET
	@Path("/registrations/{id}/log")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response getRegistrationLogs(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company, @Context Locale locale,
			@Context User user, @Context ServiceContext serviceContext, @PathParam("id") long registrationId, @BeanParam RegistrationLogSearchModel query);
}
