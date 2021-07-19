package org.opencps.api.controller;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.opencps.api.dossier.model.DossierResultsModel;
import org.opencps.exception.model.ExceptionModel;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.Locale;

@Path("")
@Api(value = "", tags = "")
public interface SupportSearchManagement {

    @GET
    @Path("/supportSearch/{dossierId}")
    @Consumes({
            MediaType.APPLICATION_JSON
    })
    @Produces({
            MediaType.APPLICATION_JSON
    })
    @ApiOperation(value = "support Search Dossiers")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of Dossiers have been filtered", response = DossierResultsModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
    })
    public Response getSupportSearchDossiers(
            @Context HttpServletRequest request, @Context HttpHeaders header,
            @Context Company company, @Context Locale locale, @Context User user,
            @Context ServiceContext serviceContext,
            @PathParam("dossierId") String dossierId,
            @QueryParam("isCallAgain") Boolean isCallAgain,
            @QueryParam("refUid") String referenceUid);

    @POST
    @Path("/supportSearch/{id}")
    @Consumes({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED
    })
    @Produces({
            MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED
    })
    @ApiOperation(value = "support Search Dossiers")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of Dossiers have been filtered", response = DossierResultsModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
    })
    public Response resetStateRetry(
            @Context HttpServletRequest request, @Context HttpHeaders header,
            @Context Company company, @Context Locale locale, @Context User user,
            @Context ServiceContext serviceContext, @PathParam("id") long id,
            @FormParam("status") int status, @FormParam("table") String table
            );



    @PUT
    @Path("/supportUser")
    @Consumes({
            MediaType.APPLICATION_JSON
    })
    @Produces({
            MediaType.APPLICATION_JSON
    })
    @ApiOperation(value = "update Email user")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of Dossiers have been filtered", response = DossierResultsModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
    })
    public Response supportUser(
            @Context HttpServletRequest request, @Context HttpHeaders header,
            @Context Company company, @Context Locale locale, @Context User user,
            @Context ServiceContext serviceContext
            ) throws PortalException;
}
