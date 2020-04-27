package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.opencps.api.applicantdata.model.ApplicantDataResultsModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.exception.model.ExceptionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/applicantdatas")
@Api(value = "/applicantdatas", tags = "applicantdatas")
public interface ApplicantDataManagement {
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "addApplicantData", response = DossierFileModel.class)
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
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart("fileNo") String fileNo,
		@ApiParam(value = "Metadata of ApplicantData", required = false) @Multipart("fileName") String fileName,
		@ApiParam(value = "Metadata of ApplicantData", required = true) @Multipart("applicantIdNo") String applicantIdNo);

}
