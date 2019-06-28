package org.opencps.api.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.opencps.api.dossier.model.DossierPublishImportModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossierfile.model.DossierFileResultsModel;
import org.opencps.exception.model.ExceptionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/dossiers")
@Api(value = "", tags = "dossiers")
public interface ImportDataManagement {

	@POST
	@Path("/{id}/files/importData")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "addDossierFileByDossierId)", response = DossierFileModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response addDossierFileByDossierId(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "Attachment files", required = false) @Multipart("file") Attachment file,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("referenceUid") String referenceUid,
			@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("dossierTemplateNo") String dossierTemplateNo,
			@ApiParam(value = "Metadata of DossierFile", required = true) @Multipart("dossierPartNo") String dossierPartNo,
			@ApiParam(value = "Metadata of DossierFile", required = true) @Multipart("dossierPartType") String dossierPartType,
			@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("fileTemplateNo") String fileTemplateNo,
			@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("displayName") String displayName,
			@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("fileType") String fileType,
			@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("isSync") String isSync);
			//@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart(value = "createDate", required = false) @Nullable Long createDate,
			//@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart(value = "modifiedDate", required = false) @Nullable Long modifiedDate);

	@POST
	@Path("/publish/importData")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add dossier publish")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response addDossierImportData(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam DossierPublishImportModel input);

	@POST
	@Path("/createMutiplite/files")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "import file xml)", response = DossierFileModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response uploadFileDossiers(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file);

	@POST
	@Path("/importQuestion/files")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "import file xml)", response = DossierFileModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response uploadFileQuestion(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file);
}
