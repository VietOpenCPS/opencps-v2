//package org.opencps.api.controller;
//
//import java.net.HttpURLConnection;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.apache.cxf.headers.Header;
//import org.opencps.api.notificationtemplate.model.NotificationTemplatesInputModel;
//import org.opencps.api.notificationtemplate.model.NotificationTemplatesModel;
//import org.opencps.api.notificationtemplate.model.NotificationTemplatesResults;
//import org.opencps.exception.model.ExceptionModel;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@Path("/notificationTemplates")
//@Api(value="/notificationTemplates", description="APIs for NotificationTemplates", tags="notificationTemplates")
//public interface NotificationTemplate {
//	@GET
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@ApiOperation(value = "Get all NotificationTemplates", response = NotificationTemplatesResults.class)
//	@ApiResponses (value = {
//	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of NotificationTemplates", response = NotificationTemplatesResults.class),
//	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
//	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
//	})
//	public Response getNotificationTemplate(@Context HttpServletRequest request, @Context Header header);
//	
//	/**
//	 * @param request
//	 * @param header
//	 * @param type
//	 * @return
//	 */
//	@GET
//	@Path("/{type}")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@ApiOperation(value = "Get a NotificationTemplatesResults detail by its code", response = NotificationTemplatesModel.class)
//	public Response getNotificationTemplate(@Context HttpServletRequest request, @Context Header header,
//			@ApiParam(value = "type that need to be get detail", required = true) @PathParam("type") String type);
//	
//	/**
//	 * @param request
//	 * @param header
//	 * @param type
//	 * @param input
//	 * @return
//	 */
//	@PUT
//	@Path("/{type}")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@ApiOperation(value = "Update NotificationTemplate by its code", response = NotificationTemplatesModel.class)
//	@ApiResponses(value = {
//	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a certain NotificationTemplatesModel that has been updated", response = NotificationTemplatesModel.class),
//	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
//	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
//	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
//	})
//	public Response updateNotificationTemplates(@Context HttpServletRequest request, @Context Header header,
//			@ApiParam(value = "type that need to be update", required = true) @PathParam("type") String type, @ApiParam NotificationTemplatesInputModel input);
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
