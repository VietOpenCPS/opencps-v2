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
import org.opencps.api.paymentFile.model.EpaymentProfileJsonModel;
import org.opencps.api.paymentFile.model.PaymentFileInputModel;
import org.opencps.api.paymentFile.model.PaymentFileResultModel;
import org.opencps.api.paymentFile.model.PaymentFileSearchModel;
import org.opencps.api.paymentFile.model.PaymentFileSearchResultModel;
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
	/* Get List of payment bill - START */
	@GET
	@Path("/{id}/payments")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "getPaymentFilesByPamentId", response = PaymentFileResultModel.class)
	@ApiResponses(value = {
				@ApiResponse (code = HttpURLConnection.HTTP_OK, message = "Return a list Payment", response = PaymentFileResultModel.class),
				@ApiResponse (code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
				@ApiResponse (code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accsess denied", response = ExceptionModel.class) })
	public Response getPaymentFilesByPaymentId(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of Payment", required = true) @PathParam("id") String id );
	/* Get List of payment bill - END */

	//2
	/* Create new a payment bill - START */
	@POST
	@Path("/{id}/payments")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "addPaymentFileByPaymentId", response = PaymentFileInputModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "", response = PaymentFileInputModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "", response = ExceptionModel.class) })
	public Response addPaymentFileByPaymentId(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "", required = true) @BeanParam PaymentFileInputModel input);
	/* Create new a payment bill - END */

	//3
	/* Get List of payment bill follow ReferenceUid - START */
	@GET
	@Path("/{id}/payments/{referenceUid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Get list Payment File By refercenceUid", response = PaymentFileResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "", response = PaymentFileResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "", response = ExceptionModel.class) })
	public Response getPaymentFileByReferenceUid (@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "", required = true) @PathParam("id") Long id,
			@ApiParam(value = "", required = true) @PathParam("referenceUid") String referenceUid);
	/* Get List of payment bill follow ReferenceUid - END */

	//4
	/* */
	@GET
	@Path("/{id}/payments/{referenceUid}/epaymentprofile")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "get the epayment profile", response = EpaymentProfileJsonModel.class)
	@ApiResponses(value = {
				@ApiResponse (code = HttpURLConnection.HTTP_OK, message = "", response = EpaymentProfileJsonModel.class),
				@ApiResponse (code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found", response = ExceptionModel.class),
				@ApiResponse (code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accsess denied", response = ExceptionModel.class),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal error", response = ExceptionModel.class)})
	public Response getEpaymentProfile(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "id of Payment", required = true) @PathParam("id") String id,
			@ApiParam (value = "", required = true) @PathParam("referenceUid") String referenceUid);
	/* */

	//5
	/* */
	@POST
	@Path("/{id}/payments/{referenceUid}/epaymentprofile")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add epaymentprofile", response = EpaymentProfileJsonModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns epaymentprofile was added", response = EpaymentProfileJsonModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class) })
	public Response addEpaymentProfile(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext,
			@ApiParam(value = "id of Payment", required = true) @PathParam("id") String id,
			@ApiParam (value = "", required = true) @PathParam("referenceUid") String referenceUid,
			@BeanParam EpaymentProfileJsonModel input);
	/* */

	//6
	/* */
	@PUT
	@Path("/{id}/payments/{referenceUid}/confirm")
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
			@ApiParam(value = "id of payments", required = true) @PathParam("id") long id,
			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid,
			@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file);
	/* */

	//7
	/**/
	@PUT
	@Path("/{id}/payments/{referenceUid}/approval")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "update DossierFile")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class) })
	public Response updatePaymentFileApproval(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, 
			@ApiParam(value = "id of payment", required = true) @PathParam("id") long id,
			@ApiParam(value = "reference of paymentFile", required = true) @PathParam("referenceUid") String referenceUid,
			@ApiParam(value = "Attachment files", required = true) @Multipart("file") Attachment file);
	/**/
	//8
	/* Download file confirm - START */
	@GET
	@Path("/{id}/payments/{referenceUid}/confirmfile")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Download confirm file")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = ""),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "", response = ExceptionModel.class) })
	public Response downloadConfirmFile(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "", required = true) @PathParam("id") Long id,
			@ApiParam (value = "", required = true) @PathParam("referenceUid") String referenceUid);
	/* Download file confirm - END */

	//9
	/* Download file invoice - START */
	@GET
	@Path("/{id}/payments/{referenceUid}/invoicefile")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Download invoice file")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = ""),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "", response = ExceptionModel.class) })
	public Response downloadInvoiceFile(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "", required = true) @PathParam("id") Long id,
			@ApiParam(value = "", required = true) @PathParam("referenceUid") String referenceUid);
	/* Download file invoice - END */
	
	//10
	/* Search List of payment bill - START */
	@GET
	@Path("/paymentfiles")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@ApiOperation(value = "Get info Payment files", response = PaymentFileSearchResultModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return a list of PaymentFile", response = PaymentFileSearchResultModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "", response = ExceptionModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "", response = ExceptionModel.class) })
	public Response getPaymentFiles(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "", required = true) @BeanParam PaymentFileSearchModel search);
	/* Search List of payment bill - END */



}
