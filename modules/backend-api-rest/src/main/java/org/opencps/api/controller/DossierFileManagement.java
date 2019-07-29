
package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "getDossierFilesByDossierId", response = DossierFileResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getDossierFilesByDossierId(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
		@ApiParam(value = "password for access dossier file", required = false) @PathParam("password") String password);

	@POST
	@Path("/{id}/files")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "addDossierFileByDossierId)", response = DossierFileModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response addDossierFileByDossierId(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "Attachment files", required = false) @Multipart("file") Attachment file,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("referenceUid") String referenceUid,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("dossierTemplateNo") String dossierTemplateNo,
		@ApiParam(value = "Metadata of DossierFile", required = true) @Multipart("dossierPartNo") String dossierPartNo,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("fileTemplateNo") String fileTemplateNo,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("displayName") String displayName,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("fileType") String fileType,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("isSync") String isSync,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart("formData") @Nullable String formData,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart(value = "removed", required = false) @Nullable String removed,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart(value = "eForm", required = false) @Nullable String eForm,
		@ApiParam(value = "Metadata of DossierFile", required = false) @Multipart(value = "modifiedDate", required = false) @Nullable Long modifiedDate);

	@POST
	@Path("/{id}/files/copyfile")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "clone a DossierFile", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response cloneDossierFile(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "body params for post") @BeanParam DossierFileCopyInputModel input);

	@GET
	@Path("/{id}/files/{referenceUid}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "getDossierFilesByDossierReferenceUid", response = DossierFileResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "downloadByDossierId_ReferenceUid"),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response downloadByDossierId_ReferenceUid(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
		@ApiParam(value = "password for access dossier file", required = false) @PathParam("password") String password);

	@GET
	@Path("/{id}/files/{referenceUid}/public/{password}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "getDossierFilesByDossierReferenceUid", response = DossierFileResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "downloadByDossierId_ReferenceUid"),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response downloadByPublicUser(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
		@ApiParam(value = "password for access dossier file", required = false) @PathParam("password") String password);

	@POST
	@Path("/{id}/files/{referenceUid}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "update DossierFile", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response updateDossierFile(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "reference of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
		@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file);

	@GET
	@Path("/{id}/files/{referenceUid}/formdata")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "getDossierFilesByDossierReferenceUid", response = JSONObject.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a formdata", response = JSONObject.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getFormDataByDossierId_ReferenceUid(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid);

	@GET
	@Path("/{id}/files/{referenceUid}/formscript")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "getDossierFilesByDossierReferenceUid", response = JSONObject.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a formscript", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getFormScriptByDossierId_ReferenceUid(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid);

	@PUT
	@Path("/{id}/files/{referenceUid}/formdata")
	@Consumes({
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "update DossierFile")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response updateDossierFileFormData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
		@ApiParam(value = "formdata of dossierfile", required = true) @FormParam("formdata") String formdata);

	@PUT
	@Path("/{id}/files/{referenceUid}/resetformdata")
	@ApiOperation(value = "update DossierFile")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response resetformdataDossierFileFormData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid,
		@ApiParam(value = "formdata of dossierfile", required = true) @FormParam("formdata") String formdata);

	@DELETE
	@Path("/{id}/files/{fileTemplateNo}/all")
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "update DossierFile")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response removeAllDossierFileFormData(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "fileTemplateNo of dossierfile", required = true) @PathParam("fileTemplateNo") String fileTemplateNo);

	@DELETE
	@Path("/{id}/files/{referenceUid}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "Delete ServiceInfo by Id)")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the detail of ServiceInfo"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response deleteDossierFile(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "referenceUid of dossierfile", required = true) @PathParam("referenceUid") String referenceUid);

	@GET
	@Path("/dossierfiles")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "Get all DossierFiles", response = DossierFileSearchResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of all DossierFiles", response = DossierFileSearchResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})

	public Response getDossierFiles(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@BeanParam DossierFileSearchModel query);

	@GET
	@Path("/{id}/download")
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "downloadByDossierId")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "downloadByDossierId"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})

	public Response downloadByDossierId(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "password for access dossier file", required = false) @PathParam("password") String password);

	@GET
	@Path("/file/{deliverableCode}")
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "deliverableCode")
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "deliverableCode"),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})

	public Response getDossierFileByDeliverableCode(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "deliverable Code", required = true) @PathParam("deliverableCode") String deliverableCode);

	// Get all dossierFile by dossierID
	@GET
	@Path("/{id}/all/files")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "getDossierFilesByDossierId", response = DossierFileResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getAllDossierFilesByDossierId(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id);

	// TODO: Get dossierFile have dossierPartNo = PPC
	@GET
	@Path("/{id}/files/fileTemplateNo/{fileTemplateNo}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "getDossierFilesByfileTemplateNo", response = JSONObject.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a formscript", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getDossierFileByDossierId_FileTemplateNo(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "fileTemplateNo of dossierfile", required = true) @PathParam("fileTemplateNo") String fileTemplateNo);

	// LamTV_ Import XML
	@POST
	@Path("/import/files")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "import file xml)", response = DossierFileModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response uploadFileEntry(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file);

	@GET
	@Path("/{id}/applicant/{applicantIdNo}/files/{fileTemplateNo}/search")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getAlreadyHasFileTemplateNo(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "id no of applicant", required = true) @PathParam("applicantIdNo") String applicantIdNo,
		@ApiParam(value = "", required = false) @PathParam("fileTemplateNo") String fileTemplateNo);

	@POST
	@Path("/{id}/files/useoriginal")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@ApiOperation(value = "clone a DossierFile", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response useOriginalDossierFile(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossier", required = true) @PathParam("id") long id,
		@ApiParam(value = "body params for post") @BeanParam DossierFileCopyInputModel input);

	@GET
	@Path("/applicant/{applicantIdNo}/fileDone/search")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getFileDoneOfApplicant(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id no of applicant", required = true) @PathParam("applicantIdNo") String applicantIdNo,
		@BeanParam DossierFileSearchModel query);

	@DELETE
	@Path("/fileDone/{dossierFileId}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response removeDossierOfProfileApplicant(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossierFile", required = true) @PathParam("dossierFileId") long dossierFileId);

	@POST
	@Path("/dossierfiles")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@ApiOperation(value = "addDossierFiles)", response = DossierFileResultsModel.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns the DossierFileModel was updated", response = DossierFileResultsModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = org.opencps.auth.api.exception.UnauthenticationException.class),
		@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
		@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response addDossierFiles(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "Id of DossierFile", required = true) @FormParam("dossierIds") String dossierIds,
		@ApiParam(value = "Metadata of  DossierFile", required = true) @FormParam("referenceUid") String referenceUid,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("dossierTemplateNo") String dossierTemplateNo,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("dossierPartNo") String dossierPartNo,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("dossierPartType") int dossierPartType,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("fileTemplateNo") String fileTemplateNo,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("displayName") String displayName,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("formData") @Nullable String formData,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("fileEntryId") long fileEntryId,
		@ApiParam(value = "Metadata of DossierFile", required = false, defaultValue = "false") @DefaultValue("false") @FormParam("original") Boolean original,
		@ApiParam(value = "Metadata of DossierFile", required = false, defaultValue = "false") @DefaultValue("false") @FormParam("eForm") Boolean eForm,
		@ApiParam(value = "Metadata of DossierFile", required = false, defaultValue = "false") @DefaultValue("false") @FormParam("isNew") Boolean isNew,
		@ApiParam(value = "Metadata of DossierFile", required = false, defaultValue = "false") @DefaultValue("false") @FormParam("removed") Boolean removed,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("signCheck") int signCheck,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("signInfo") String signInfo,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("formScript") String formScript,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("formReport") String formReport,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("formSchema") String formSchema,
		@ApiParam(value = "Metadata of DossierFile", required = false) @FormParam("deliverableCode") String deliverableCode);

	@DELETE
	@Path("/clean/{dossierFileId}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response cleanDossierFile(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@ApiParam(value = "id of dossierFile", required = true) @PathParam("dossierFileId") long dossierFileId);
}
