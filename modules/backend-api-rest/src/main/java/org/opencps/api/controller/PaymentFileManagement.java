package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
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
import org.opencps.api.paymentfile.model.PaymentFileInputModel;
import org.opencps.api.paymentfile.model.PaymentFileResultModel;
import org.opencps.exception.model.ExceptionModel;

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
public interface PaymentFileManagement {
	//1
	/* Get List of payment File - START */
//	@GET
//	@Path("/{id}/payments")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@ApiOperation(value = "getPaymentFilesByDossierId", response = PaymentFileResultModel.class)
//	@ApiResponses(value = {
//				@ApiResponse (code = HttpURLConnection.HTTP_OK, message = "Return a list Payment", response = PaymentFileResultModel.class),
//				@ApiResponse (code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
//				@ApiResponse (code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accsess denied", response = ExceptionModel.class) })
//	public Response getPaymentFilesByDossierId(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
//			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
//			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id, @BeanParam PaymentFileSearchModel search);
	/* Get List of payment File - END */

	//2
	/* Create new a payment File - START */
//	@POST
//	@Path("/{id}/payments")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@ApiOperation(value = "addPaymentFileByDosierId", response = PaymentFileInputModel.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns epaymentprofile was create", response = PaymentFileInputModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
//	public Response createPaymentFileByDossierId(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
//			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
//			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
//			@ApiParam(value = "body params for post", required = true) @BeanParam PaymentFileInputModel input);
	/* Create new a payment File - END */

	//3
	/* Get detail payment File follow ReferenceUid - START */
//	@GET
//	@Path("/{id}/payments/{referenceUid}")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@ApiOperation(value = "Get detail Payment File By refercenceUid", response = PaymentFileResultModel.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Get detail payment file", response = PaymentFileResultModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accsess denied", response = ExceptionModel.class) })
//	public Response getPaymentFileByReferenceUid (@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
//			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
//			@ApiParam(value = "id of dossier", required = true) @PathParam("id") Long id,
//			@ApiParam(value = "referenceUid of Payment", required = true) @PathParam("referenceUid") String referenceUid);
	/* Get detail payment File follow ReferenceUid - END */

	//LamTV_Process new API Payment
	@GET
	@Path("/{id}/payment")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Get detail Payment File By dossierId", response = PaymentFileResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Get detail payment file", response = PaymentFileResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accsess denied", response = ExceptionModel.class) })
	public Response getPaymentFileByDossierId(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id);

	//4
	/* Get info epaymentProfile - START */
	@GET
	@Path("/{id}/payments/{referenceUid}/epaymentprofile")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "get info epayment profile", response = String.class)
	@ApiResponses(value = {
				@ApiResponse (code = HttpURLConnection.HTTP_OK, message = "Get info epayment profile", response = String.class),
				@ApiResponse (code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
				@ApiResponse (code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accsess denied", response = ExceptionModel.class),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)})
	public Response getEpaymentProfile(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam (value = "referenceUid of Payment", required = true) @PathParam("referenceUid") String referenceUid);
	/* Get info epaymentProfile - END */

	//5
	/* Update info EpaymentProfile - START */
//	@POST
//	@Path("/{id}/payments/{referenceUid}/epaymentprofile")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@ApiOperation(value = "Add epaymentprofile", response = JSONObject.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns epaymentprofile was update", response = JSONObject.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class) })
//	public Response updateEpaymentProfile(@Context HttpServletRequest request, @Context HttpHeaders header,
//			@Context Company company, @Context Locale locale, @Context User user,
//			@Context ServiceContext serviceContext,
//			@ApiParam(value = "id of Payment", required = true) @PathParam("id") String id,
//			@ApiParam (value = "referenceUid of Payment", required = true) @PathParam("referenceUid") String referenceUid,
//			@BeanParam PaymentFileInputModel input);
	/* Update info EpaymentProfile - END */

	//6
	/* Confirm payment - START */
	@PUT
	@Path("/{id}/payments/{referenceUid}/confirmfile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "upload PaymentFile")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response updatePaymentFileConfirm(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, 
			@ApiParam(value = "id of payments", required = true) @PathParam("id") String id,
			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid,
			@ApiParam(value = "Attachment files") @Multipart("file") Attachment file/*,
			@ApiParam(value = "Metadata of PaymentFile") @Multipart("confirmNote") String confirmNote,
			@ApiParam(value = "Metadata of PaymentFile") @Multipart("paymentMethod") String paymentMethod,
			@ApiParam(value = "Metadata of PaymentFile") @Multipart("confirmPayload") String confirmPayload*/);
	
//	@PUT
//	@Path("/{id}/payments/{referenceUid}/confirm/noattachment")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@ApiOperation(value = "Update PaymentFile")
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
//	public Response updatePaymentFileConfirmNoAttachment(@Context HttpServletRequest request, @Context HttpHeaders header,
//			@Context Company company, @Context Locale locale, @Context User user,
//			@Context ServiceContext serviceContext, 
//			@ApiParam(value = "id of payments", required = true) @PathParam("id") String id,
//			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid,
//			@BeanParam PaymentFileInputModel input);
	/* Confirm payment - END */

	//7
	/* Approved payment - START */
//	@PUT
//	@Path("/{id}/payments/{referenceUid}/approval")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@ApiOperation(value = "update DossierFile")
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
//	public Response updatePaymentFileApproval(@Context HttpServletRequest request, @Context HttpHeaders header,
//			@Context Company company, @Context Locale locale, @Context User user,
//			@Context ServiceContext serviceContext, 
//			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
//			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid,
//			@ApiParam(value = "Attachment files") @Multipart("file") Attachment file,
//			@ApiParam(value = "Metadata of PaymentFile") @Multipart("approveDatetime") String approveDatetime,
//			@ApiParam(value = "Metadata of PaymentFile") @Multipart("accountUserName") String accountUserName,
//			@ApiParam(value = "Metadata of PaymentFile") @Multipart("govAgencyTaxNo") String govAgencyTaxNo,
//			@ApiParam(value = "Metadata of PaymentFile") @Multipart("invoiceTemplateNo") String invoiceTemplateNo,
//			@ApiParam(value = "Metadata of PaymentFile") @Multipart("invoiceIssueNo") String invoiceIssueNo,
//			@ApiParam(value = "Metadata of PaymentFile") @Multipart("invoiceNo") String invoiceNo);
	
//	@PUT
//	@Path("/{id}/payments/{referenceUid}/approval/noattachment")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@ApiOperation(value = "Update PaymentFile")
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
//	public Response updatePaymentFileApprovalNoAttachment(@Context HttpServletRequest request, @Context HttpHeaders header,
//			@Context Company company, @Context Locale locale, @Context User user,
//			@Context ServiceContext serviceContext, 
//			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
//			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid,
//			@BeanParam PaymentFileInputModel input);
	/* Approved payment - END */

	//8
	/* Download file confirm - START */
	@GET
	@Path("/{id}/payments/{referenceUid}/confirmfile")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Download confirm file")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access defined", response = ExceptionModel.class) })
	public Response downloadConfirmFile(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam (value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid);
	/* Download file confirm - END */

	//9
	/* Download file invoice - START */
	@GET
	@Path("/{id}/payments/{referenceUid}/invoicefile")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Download invoice file")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access defined", response = ExceptionModel.class) })
	public Response downloadInvoiceFile(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid);
	/* Download file invoice - END */

	//10
	//TODO: path using is (/dossier/payments)
	/* Search List of payment bill - START */
//	@GET
//	@Path("/paymentfiles")
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
//	@ApiOperation(value = "Get info Payment files", response = PaymentFileSearchResultModel.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return a list of PaymentFile", response = PaymentFileSearchResultModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "", response = ExceptionModel.class),
//			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "", response = ExceptionModel.class) })
//	public Response getPaymentFiles(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
//			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
//			@ApiParam(value = "body params of post", required = true) @BeanParam PaymentFileSearchModel search);
	/* Search List of payment bill - END */	
	
	@PUT
	@Path("/keypay/{dossierUUid}/{paymentFileUUid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	public Response processingKeyPay(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("dossierUUid") String dossierUUid, @PathParam("paymentFileUUid") String paymentFileUUid);
	/* Search List of payment bill - END */

	@POST
	@Path("/{id}/payment")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@ApiOperation(value = "addPaymentFileByDosierId", response = PaymentFileInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns epaymentprofile was create", response = PaymentFileInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response createPaymentFileByDossierId(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam(value = "body params for post", required = true) @BeanParam PaymentFileInputModel input);	
	
	@GET
	@Path("/{id}/payments/{referenceUid}/invoicefile/preview")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Download invoice file")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access defined", response = ExceptionModel.class) })
	public Response previewInvoiceFile(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of dossier", required = true) @PathParam("id") String id,
			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid);	
}
