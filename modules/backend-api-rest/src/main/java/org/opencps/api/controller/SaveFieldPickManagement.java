package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.opencps.exception.model.ExceptionModel;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.Locale;

@Path("/fieldpick")
@Api(value = "/fieldpick", tags = "fieldpick")
public interface SaveFieldPickManagement
{
	@GET
	@Path("/classpk/{classPK}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get field pick saved", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of all ServerConfig", response = String.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response getFieldPick(@Context HttpServletRequest request, @Context HttpHeaders header,@Context Company company,
		@Context Locale locale, @Context User user,@Context ServiceContext serviceContext,
		@PathParam(value = "classPK") String classPK);

	@POST
	@Path("/classpk/{classPK}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "save field pick ", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of all ServerConfig", response = String.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response updateFieldPick(@Context HttpServletRequest request, @Context HttpHeaders header,@Context Company company,
		@Context Locale locale, @Context User user,@Context ServiceContext serviceContext,
		@PathParam(value = "classPK") String classPK, @FormParam(value = "formData") String formData);

}
