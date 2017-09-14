package org.opencps.api.controller;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.headers.Header;
import org.opencps.dossiertemplate.model.DossierTemplateResultsModel;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/dossiertemplates")
@Api(value="/dossiertemplates", description="APIs for Dossiertemplates")
public interface DossierTemplate {
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get list of DossierTemplate", response = DossierTemplateResultsModel.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of DossierTemplate", response = DossierTemplateResultsModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getServiceInfos(@Context HttpServletRequest request, @Context Header header, @Context ServiceContext serviceContext);

}
