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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.adminconfig.model.ApiManagerDetailModel;
import org.opencps.api.adminconfig.model.ApiManagerInputModel;
import org.opencps.api.adminconfig.model.ApiManagerResultsModel;
import org.opencps.api.adminconfig.model.ApiRoleDetailModel;
import org.opencps.api.adminconfig.model.ApiRoleInputModel;
import org.opencps.api.adminconfig.model.ApiRoleResultsModel;
import org.opencps.api.adminconfig.model.DtoResponse;
import org.opencps.api.adminconfig.model.SyncTrackingQuery;
import org.opencps.api.faq.model.QuestionDetailModel;
import org.opencps.api.faq.model.QuestionInputModel;
import org.opencps.api.faq.model.QuestionResultsModel;
import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.exception.model.ExceptionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/socket/web")
@Api(value = "/socket", tags = "socket")
public interface AdminConfigManagement {
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a applicant was created", response = ApplicantModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response onMessage(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@FormParam("text") String text);

	@POST
	@Path("/export-excel")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	public Response exportDataConfig(@Context HttpServletRequest request, @Context HttpHeaders header,
									 @Context Company company, @Context Locale locale, @Context User user,
									 @Context ServiceContext serviceContext, @FormParam("columnName") String columnName,
									 @FormParam("content") String content);
	
	@GET
	@Path("/apiManagers")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Get all api managers", response = ApiManagerResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getApiManagers(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext);
	
	@POST
	@Path("/apiManagers")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Add a question", response = ApiManagerDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns an apimanager was created", response = ApiManagerDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response addApiManager(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@BeanParam ApiManagerInputModel input);
	
	@PUT
	@Path("/apiManagers/{id}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Update a api manager", response = ApiManagerDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a question was created", response = QuestionDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response updateApiManager(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @PathParam("id") String id,
		@BeanParam ApiManagerInputModel input);
	
	@DELETE
	@Path("/apiManagers/{id}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Delete a api manager", response = ApiManagerDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a api manager was deleted", response = ApiManagerDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response deleteApiManager(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @PathParam("id") String id);
	
	@OPTIONS
	@Path("/apiManagers/{id}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Detail a api manager", response = ApiManagerDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a api manager was created", response = ApiManagerDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response detailApiManager(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @PathParam("id") String id);
	
	@GET
	@Path("/apiRoles")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Get all api roles", response = ApiRoleResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getApiRoles(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext);
	
	@POST
	@Path("/apiRoles")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Add a api role", response = ApiRoleDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns an api role was created", response = ApiRoleDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response addApiRole(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@BeanParam ApiRoleInputModel input);
	
	@PUT
	@Path("/apiRoles/{id}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Update a api role", response = ApiRoleDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a apr role was created", response = ApiRoleDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response updateApiRole(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @PathParam("id") String id,
		@BeanParam ApiRoleInputModel input);
	
	@DELETE
	@Path("/apiRoles/{id}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Delete a api role", response = ApiRoleDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a api role was deleted", response = ApiRoleDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response deleteApiRole(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @PathParam("id") String id);
	
	@OPTIONS
	@Path("/apiRoles/{id}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Detail a api role", response = ApiRoleDetailModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a api role was created", response = ApiRoleDetailModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)
	})
	public Response detailApiRole(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @PathParam("id") String id);
	
	
	@GET
	@Path("/log-report")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "Get all log report", response = DtoResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getLogReport(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext, @BeanParam SyncTrackingQuery input);
}
