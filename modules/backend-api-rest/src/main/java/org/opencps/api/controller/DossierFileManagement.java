package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.annotation.Nullable;
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

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.opencps.api.dossierfile.model.DossierFileCopyInputModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossierfile.model.DossierFileResultsModel;
import org.opencps.api.dossierfile.model.DossierFileSearchModel;
import org.opencps.api.dossierfile.model.DossierFileSearchResultsModel;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/dossiers")
@Api(value = "", tags = "dossiers")
public interface DossierFileManagement {
	@GET
	@Path("/{id}/files")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "getDossierFilesByDossierId", response = DossierFileResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response getDossierFilesByDossierId(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "password for access dossier file", required = false) @PathParam("password") String password);

	/*
	 * @GET
	 * 
	 * @Path("/{referenceUid}/files")
	 * 
	 * @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
	 * MediaType.APPLICATION_FORM_URLENCODED })
	 * 
	 * @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
	 * MediaType.APPLICATION_FORM_URLENCODED })
	 * 
	 * @ApiOperation(value = "getDossierFilesByDossierReferenceUid", response =
	 * DossierFileResultsModel.class)
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_OK, message =
	 * "Returns a list", response = DossierFileResultsModel.class),
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message =
	 * "Not found", response = ExceptionModel.class),
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message =
	 * "Access denied", response = ExceptionModel.class) }) public Response
	 * getDossierFilesByDossierReferenceUid(@Context HttpServletRequest
	 * request, @Context HttpHeaders header,
	 * 
	 * @Context Company company, @Context Locale locale, @Context User user,
	 * 
	 * @Context ServiceContext serviceContext,
	 * 
	 * @ApiParam(value = "reference of dossierfile", required =
	 * true) @PathParam("referenceUid") String referenceUid);
	 */
	@POST
	@Path("/{id}/files")
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
			@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam(value = "Metadata of DossierFile", required = true) @Multipart("referenceUid") String referenceUid,
			@ApiParam(value = "Metadata of DossierFile") @Multipart("dossierTemplateNo") String dossierTemplateNo,
			@ApiParam(value = "Metadata of DossierFile") @Multipart("dossierPartNo") String dossierPartNo,
			@ApiParam(value = "Metadata of DossierFile") @Multipart("fileTemplateNo") String fileTemplateNo,
			@ApiParam(value = "Metadata of DossierFile") @Multipart("displayName") String displayName,
			@ApiParam(value = "Metadata of DossierFile") @Multipart("fileType") String fileType,
			@ApiParam(value = "Metadata of DossierFile") @Multipart("isSync") String isSync,
			@ApiParam(value = "Metadata of DossierFile") @Multipart("formData") @Nullable String formData);

	/*
	 * @POST
	 * 
	 * @Path("/{referenceUid}/files")
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	 * 
	 * @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	 * 
	 * @ApiOperation(value = "addDossierFileByDossierReferenceUid)", response =
	 * DossierFileModel.class)
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_OK, message =
	 * "Returns the DossierFileModel was updated", response =
	 * DossierFileResultsModel.class),
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message =
	 * "Unauthorized", response = ExceptionModel.class),
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message =
	 * "Not found", response = ExceptionModel.class),
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message =
	 * "Access denied", response = ExceptionModel.class) }) public Response
	 * addDossierFileByDossierReferenceUid(@Context HttpServletRequest
	 * request, @Context HttpHeaders header,
	 * 
	 * @Context Company company, @Context Locale locale, @Context User user,
	 * 
	 * @Context ServiceContext serviceContext,
	 * 
	 * @ApiParam(value = "Attachment files", required = true) @Multipart("file")
	 * Attachment file,
	 * 
	 * @ApiParam(value = "referenceUid of dossier", required =
	 * true) @PathParam("referenceUid") String dosserReferenceUid,
	 * 
	 * @ApiParam(value = "Metadata of DossierFile", required =
	 * true) @Multipart("referenceUid") String referenceUid,
	 * 
	 * @ApiParam(value =
	 * "Metadata of DossierFile") @Multipart("dossierTemplateNo") String
	 * dossierTemplateNo,
	 * 
	 * @ApiParam(value = "Metadata of DossierFile") @Multipart("dossierPartNo")
	 * String dossierPartNo,
	 * 
	 * @ApiParam(value = "Metadata of DossierFile") @Multipart("fileTemplateNo")
	 * String fileTemplateNo,
	 * 
	 * @ApiParam(value = "Metadata of DossierFile") @Multipart("displayName")
	 * String displayName);
	 */
	@POST
	@Path("/{id}/files/copyfile")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@ApiOperation(value = "clone a DossierFile", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response cloneDossierFile(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "body params for post") @BeanParam DossierFileCopyInputModel input);

	@GET
	@Path("/{id}/files/{referenceUid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@ApiOperation(value = "getDossierFilesByDossierReferenceUid", response = DossierFileResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "downloadByDossierId_ReferenceUid"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response downloadByDossierId_ReferenceUid(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
			@ApiParam(value = "password for access dossier file", required = false) @PathParam("password") String password);

	/*
	 * @GET
	 * 
	 * @Path("/{dossierReferenceUid}/files/{referenceUid}")
	 * 
	 * @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
	 * MediaType.APPLICATION_FORM_URLENCODED })
	 * 
	 * @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
	 * MediaType.APPLICATION_FORM_URLENCODED })
	 * 
	 * @ApiOperation(value = "getDossierFilesByDossierReferenceUid", response =
	 * DossierFileResultsModel.class)
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_OK, message =
	 * "downloadByDossierReferenceUid_ReferenceUid"),
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message =
	 * "Not found", response = ExceptionModel.class),
	 * 
	 * @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message =
	 * "Access denied", response = ExceptionModel.class) }) public Response
	 * downloadByDossierReferenceUid_ReferenceUid(@Context HttpServletRequest
	 * request, @Context HttpHeaders header,
	 * 
	 * @Context Company company, @Context Locale locale, @Context User user,
	 * 
	 * @Context ServiceContext serviceContext,
	 * 
	 * @ApiParam(value = "referenceUid of dossier", required =
	 * true) @PathParam("dossierReferenceUid") String dossierReferenceUid,
	 * 
	 * @ApiParam(value = "referenceUid of dossierfile", required =
	 * true) @PathParam("referenceUid") String referenceUid,
	 * 
	 * @ApiParam(value = "password for access dossier file", required =
	 * false) @PathParam("password") String password);
	 */
	@POST
	@Path("/{id}/files/{referenceUid}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "update DossierFile", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response updateDossierFile(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "reference of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
			@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file);

	@GET
	@Path("/{id}/files/{referenceUid}/formdata")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@ApiOperation(value = "getDossierFilesByDossierReferenceUid", response = JSONObject.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a formdata", response = JSONObject.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response getFormDataByDossierId_ReferenceUid(@Context HttpServletRequest request,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid);

	@GET
	@Path("/{id}/files/{referenceUid}/formscript")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@ApiOperation(value = "getDossierFilesByDossierReferenceUid", response = JSONObject.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a formscript", response = DossierFileResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response getFormScriptByDossierId_ReferenceUid(@Context HttpServletRequest request,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid);

	@PUT
	@Path("/{id}/files/{referenceUid}/formdata")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "update DossierFile")
	@ApiResponses(value = { @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response updateDossierFileFormData(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
			@ApiParam(value = "formdata of dossierfile", required = true) @FormParam("formdata") String formdata);

	@PUT
	@Path("/{id}/files/{referenceUid}/resetformdata")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "update DossierFile")
	@ApiResponses(value = { @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response resetformdataDossierFileFormData(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
			@ApiParam(value = "formdata of dossierfile", required = true) @FormParam("formdata") String formdata);
	
	@DELETE
	@Path("/{id}/files/{fileTemplateNo}/all")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "update DossierFile")
	@ApiResponses(value = { @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response removeAllDossierFileFormData(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "fileTemplateNo of dossierfile", required = true) @PathParam("fileTemplateNo") String fileTemplateNo);


	@DELETE
	@Path("/{id}/files/{referenceUid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete ServiceInfo by Id)")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the detail of ServiceInfo"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response deleteDossierFile(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid);

	@GET
	@Path("/dossierfiles")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all DossierFiles", response = DossierFileSearchResultsModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of all DossierFiles", response = DossierFileSearchResultsModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getDossierFiles(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @BeanParam DossierFileSearchModel query);

	@GET
	@Path("/{id}/download")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "downloadByDossierId")
	@ApiResponses(value = { @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "downloadByDossierId"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response downloadByDossierId(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
			@ApiParam(value = "password for access dossier file", required = false) @PathParam("password") String password);

	@GET
	@Path("/file/{deliverableCode}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "deliverableCode")
	@ApiResponses(value = { @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "deliverableCode"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })

	public Response getDossierFileByDeliverableCode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "deliverable Code", required = true) @PathParam("deliverableCode") String deliverableCode);
}
