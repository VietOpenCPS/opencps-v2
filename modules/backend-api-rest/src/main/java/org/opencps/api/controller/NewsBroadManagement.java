package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
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

import org.opencps.api.dossier.model.ActionResultModel;
import org.opencps.api.newsbroad.model.NewsBoardInputModel;
import org.opencps.api.newsbroad.model.NewsBoardSearchModel;
import org.opencps.exception.model.ExceptionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("/newsBoards")
@Api(value = "/newsBoards", description = "APIs for NewBroad", tags = "newsBoards")
public interface NewsBroadManagement {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get List NewsBoard", response = ActionResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns list NewsBoard", response = ActionResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response getListNewsBoard(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam NewsBoardSearchModel search);

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get List NewsBoard", response = ActionResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns NewsBoard detail", response = ActionResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response getNewsBoardDetail(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id);

	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get List NewsBoard", response = ActionResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns NewsBoard detail", response = ActionResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response addNewsBoard(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam NewsBoardInputModel newsBoardInputModel);

	@PUT
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get List NewsBoard", response = ActionResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns NewsBoard detail", response = ActionResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response updateNewsBoard(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam NewsBoardInputModel newsBoardInputModel,
			@PathParam("id") long id);

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get List NewsBoard", response = ActionResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns NewsBoard detail", response = ActionResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class) })
	public Response deleteNewsBoard(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("id") long id);

}
