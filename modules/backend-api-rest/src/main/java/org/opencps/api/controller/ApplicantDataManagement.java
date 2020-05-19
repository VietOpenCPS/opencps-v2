package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.opencps.api.applicantdata.model.ApplicantDataModel;
import org.opencps.api.applicantdata.model.ApplicantDataResultsModel;
import org.opencps.api.applicantdata.model.ApplicantDataSearchModel;
import org.opencps.exception.model.ExceptionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/applicantdatas")
@Api(value = "/applicantdatas", tags = "applicantdatas")
public interface ApplicantDataManagement {
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@ApiOperation(value = "Get all applicant datas", response = ApplicantDataResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response getApplicantDatas(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam ApplicantDataSearchModel query);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "addApplicantData", response = ApplicantDataModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the ApplicantDataModel was updated", response = ApplicantDataResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response addApplicantData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "Attachment files", required = false) @Multipart("file") Attachment file,
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart("fileTemplateNo") String fileTemplateNo,
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart("fileNo") String fileNo,
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart("fileName") String fileName,
		@ApiParam(value = "Metadata of ApplicantData", required = true) @Multipart("applicantIdNo") String applicantIdNo,
		@ApiParam(value = "Metadata of ApplicantData", required = true) @Multipart("status") String status);

	@DELETE
	@Path("/{id}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "Delete applicant data by Id)")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the applicant data"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response deleteApplicantData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of applicant data", required = true) @PathParam("id") long id);
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "Update applicant data by Id)")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the applicant data"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response updateApplicantData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of applicant data", required = true) @PathParam("id") long id,
		@ApiParam(value = "Attachment files", required = false) @Multipart(value="file", required=false) Attachment file,
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart(value="fileTemplateNo", required=false) String fileTemplateNo,
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart(value="fileNo", required=false) String fileNo,
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart(value="fileName", required=false) String fileName,
		@ApiParam(value = "Metadata of ApplicantData", required = true) @Multipart(value="applicantIdNo", required=false) String applicantIdNo,
		@ApiParam(value = "Metadata of ApplicantData", required = true) @Multipart(value="status", required=false) String status);
	
	@POST
	@Path("/{id}/active")
	@ApiOperation(value = "Active applicant data by Id)")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the applicant data"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response activeApplicantData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of applicant data", required = true) @PathParam("id") long id);

	@POST
	@Path("/{id}/inactive")
	@ApiOperation(value = "Inactive applicant data by Id)")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the applicant data"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response inactiveApplicantData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of applicant data", required = true) @PathParam("id") long id);
}
