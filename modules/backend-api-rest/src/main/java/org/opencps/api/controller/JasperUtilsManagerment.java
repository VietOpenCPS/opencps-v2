
package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.dossier.model.DossierResultsModel;
import org.opencps.exception.model.ExceptionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/jaspers")
@Api(value = "/jaspers", tags = "jaspers")
public interface JasperUtilsManagerment {

	@POST
	@Path("/preview")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of Plugins have been filtered", response = DossierResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})

	public Response getPreview(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@FormParam("jsonDataStr") String jsonDataStr,
		@FormParam("scriptStr") String scriptStr);

}
