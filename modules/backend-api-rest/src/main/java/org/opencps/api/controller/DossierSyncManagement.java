package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.v21.dossiersync.model.DossierSyncV21ResultsModel;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/dossiersyncs")
@Api(value = "/dossiersyncs", tags = "dossiersyncs")
public interface DossierSyncManagement {
	
//	@GET
//	@Path("/server/{serverNo}")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@ApiOperation(value = "Get the list of Dossiers that need to sync in queue", response = DossierSyncResultsModel.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the list of DossierSyncs", response = DossierSyncResultsModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
//
//	public Response getDossierSyncs(@Context HttpServletRequest request, @Context HttpHeaders header,
//			@Context Company company, @Context Locale locale, @Context User user,
//			@Context ServiceContext serviceContext, @PathParam("serverNo") String serverNo);
//
//	@GET
//	@Path("/{id}/sending")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@ApiOperation(value = "Sending a DossierSync have to sync", response = DossierSyncSendingModel.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "The DossierSync has been synchronized", response = DossierSyncSendingModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
//
//	public Response sendDossierSync(@Context HttpServletRequest request, @Context HttpHeaders header,
//			@Context Company company, @Context Locale locale, @Context User user,
//			@Context ServiceContext serviceContext, @PathParam("id") long id);
//
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get a DossierSync of dossier", response = DossierSyncV21ResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "The DossierSync has been synchronized", response = DossierSyncV21ResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getDossierSyncsByApplicant(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @QueryParam("action") String action,
			@QueryParam("start") Integer start, @QueryParam("end") Integer end);	
}
