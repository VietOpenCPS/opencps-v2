package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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

import org.opencps.api.processsequence.model.ProcessSequenceInputModel;
import org.opencps.api.processsequence.model.ProcessSequenceOutputModel;
import org.opencps.api.processsequence.model.ProcessSequenceResultModel;
import org.opencps.api.serviceprocess.model.ProcessActionInputModel;
import org.opencps.api.serviceprocess.model.ProcessActionResultsModel;
import org.opencps.api.serviceprocess.model.ProcessActionSearchModel;
import org.opencps.api.serviceprocess.model.ProcessStepInputModel;
import org.opencps.api.serviceprocess.model.ProcessStepResultsModel;
import org.opencps.api.serviceprocess.model.ProcessStepSearchModel;
import org.opencps.api.serviceprocess.model.RoleInputModel;
import org.opencps.api.serviceprocess.model.RoleResultsModel;
import org.opencps.api.serviceprocess.model.ServiceProcessDetailModel;
import org.opencps.api.serviceprocess.model.ServiceProcessInputModel;
import org.opencps.api.serviceprocess.model.ServiceProcessResultsModel;
import org.opencps.api.serviceprocess.model.ServiceProcessSearchModel;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/serviceprocesses")
@Api(value = "/serviceprocesses", tags = "serviceprocesses")
public interface ServiceProcessManagement {
	
	
	@POST
	@Path("/initsp")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "init the DEFAULT ServiceProcesses", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the ServiceProcesses was created", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })

	public Response initServiceProcesses(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext);

	@POST
	@Path("/{id}/clone")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "clone a ServiceProcesses", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the ServiceProcesses was clone", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })

	public Response cloneServiceProcesses(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @FormParam("processNo") String processNo);


	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all ServiceProcesses", response = ServiceProcessResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of all ServiceProcesses", response = ServiceProcessResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getServiceProcesses(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam ServiceProcessSearchModel query);

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add a ServiceProcesses", response = ServiceProcessInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the ServiceProcesses was created", response = ServiceProcessInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })

	public Response addServiceProcesses(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam ServiceProcessInputModel input);

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get the ServiceProcesses by primekey", response = ServiceProcessDetailModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns detail of ServiceProcesses", response = ServiceProcessDetailModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })

	public Response getServiceProcessDetail(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id);

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update a ServiceProcesses", response = ServiceProcessDetailModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the ServiceProcesses was update", response = ServiceProcessDetailModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })

	public Response updateServiceProcess(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id,
			@BeanParam ServiceProcessInputModel input);

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Remove a ServiceProcesses", response = ServiceProcessDetailModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the ServiceProcesses was removed", response = ServiceProcessDetailModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })

	public Response removeServiceProcess(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id);

	@GET
	@Path("/{id}/roles")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all ServiceProcessRoles of a ServiceProcess by its id", response = ServiceProcessResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of all ServiceProcessRoles", response = ServiceProcessResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getServiceProcessRoles(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id);

	@POST
	@Path("/{id}/roles")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add the ServiceProcessRole of a ServiceProcess", response = RoleInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ServiceProcessRole was added", response = RoleInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response addServiceProcessRole(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @BeanParam RoleInputModel input);

	@PUT
	@Path("/{id}/roles/{roleid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update the ServiceProcessRole of a ServiceProcess by its id", response = RoleInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ServiceProcessRole was update", response = RoleInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response updateServiceProcessRole(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("roleid") long roleid,
			@BeanParam RoleInputModel input);

	@DELETE
	@Path("/{id}/roles/{roleid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Remove a ServiceProcessRole of a ServiceProcess", response = RoleInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ServiceProcessRole was deleted", response = RoleInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response removeServiceProcessRole(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("roleid") long roleid);

	@GET
	@Path("/{id}/steps")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all ProcessSteps of a ServiceProcess", response = ProcessStepResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of all ProcessSteps", response = ProcessStepResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getProcessSteps(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @BeanParam ProcessStepSearchModel query);

	@POST
	@Path("/{id}/steps")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add the ProcessStep of a ServiceProcess", response = ProcessStepInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessStep was added", response = ProcessStepInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response addProcessStep(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @BeanParam ProcessStepInputModel input);

	@PUT
	@Path("/{id}/steps/{code}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update the ProcessStep of a ServiceProcess", response = ProcessStepInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessStep was updated", response = ProcessStepInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response updateProcessStep(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("code") String code,
			@BeanParam ProcessStepInputModel input);

	@GET
	@Path("/{id}/steps/{code}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update the ProcessStep of a ServiceProcess", response = ProcessStepInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessStep was updated", response = ProcessStepInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getProcessStep(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("code") String code);
	
	@DELETE
	@Path("/{id}/steps/{code}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Remove the ProcessStep of a ServiceProcess", response = ProcessStepInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessStep was removed", response = ProcessStepInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response removeProcessStep(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("code") String code);

	@GET
	@Path("/{id}/steps/{code}/roles")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all ProcessStepRoles of a ProcessStep", response = RoleResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list ProcessStepRoles of a ProcessStep", response = RoleResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getProcessStepRoles(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("code") String code);

	@POST
	@Path("/{id}/steps/{code}/roles")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add the ProcessStepRole of a ProcessStep", response = RoleInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessStepRole was added", response = RoleInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response addProcessStepRole(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("code") String code,
			@BeanParam RoleInputModel input);

	@PUT
	@Path("/{id}/steps/{code}/roles/{roleid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update the ProcessStepRole of a ProcessStep", response = RoleInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return a ProcessStepRole was updated", response = RoleInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response updateProcessStepRole(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("code") String code,
			@PathParam("roleid") long roleid, @BeanParam RoleInputModel input);

	@DELETE
	@Path("/{id}/steps/{code}/roles/{roleid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Remove the ProcessStepRole of a ProcessStep", response = RoleInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return a ProcessStepRole was deleted", response = RoleInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response removeProcessStepRole(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("code") String code,
			@PathParam("roleid") long roleid);

	@GET
	@Path("/{id}/actions")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all ProcessActions of a ServiceProcess", response = ProcessActionResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list ProcessStepRoles of a ProcessStep", response = ProcessActionResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getProcessActions(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @BeanParam ProcessActionSearchModel query);

	@POST
	@Path("/{id}/actions")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add the ProcessAction of a ServiceProcess", response = ProcessActionInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessAction was added", response = ProcessActionInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response addProcessAction(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @BeanParam ProcessActionInputModel input);

	@PUT
	@Path("/{id}/actions/{actionid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add the ProcessAction of a ServiceProcess", response = ProcessActionInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessAction was added", response = ProcessActionInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response updateProcessAction(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("actionid") long actionid,
			@BeanParam ProcessActionInputModel input);

	@GET
	@Path("/{id}/actions/{actionid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add the ProcessAction of a ServiceProcess", response = ProcessActionInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessAction was added", response = ProcessActionInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getProcessAction(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("actionid") long actionid);
	
	@DELETE
	@Path("/{id}/actions/{actionid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Remove the ProcessAction of a ServiceProcess", response = ProcessActionInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessAction was removed", response = ProcessActionInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response removeProcessAction(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("actionid") long actionid);

	@GET
	@Path("/{id}/sequences")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all ProcessActions of a ServiceProcess", response = ProcessSequenceResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list ProcessStepRoles of a ProcessStep", response = ProcessActionResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getProcessSequences(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id);
	
	@POST
	@Path("/{id}/sequences")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add the ProcessSequence of a ServiceProcess", response = ProcessSequenceOutputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessAction was added", response = ProcessActionInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response addProcessSequence(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @BeanParam ProcessSequenceInputModel input);
	
	@PUT
	@Path("/{id}/sequences/{sequenceNo}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update the ProcessSequence of a ServiceProcess", response = ProcessSequenceOutputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessAction was added", response = ProcessActionInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response updateProcessSequence(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("sequenceNo") String sequenceNo, @BeanParam ProcessSequenceInputModel input);

	@DELETE
	@Path("/{id}/sequences/{sequenceNo}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update the ProcessSequence of a ServiceProcess", response = ProcessSequenceOutputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a ProcessAction was added", response = ProcessActionInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response deleteProcessSequence(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id, @PathParam("sequenceNo") String sequenceNo);
	
	@GET
	@Path("/{id}/mermaidgraph")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get the ServiceProcesses by primekey", response = ServiceProcessDetailModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns detail of ServiceProcesses", response = ServiceProcessDetailModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })

	public Response getServiceProcessMermaidGraph(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") String id);

}
