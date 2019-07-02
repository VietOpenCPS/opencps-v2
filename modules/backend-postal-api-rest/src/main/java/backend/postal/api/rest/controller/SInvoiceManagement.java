package backend.postal.api.rest.controller;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.sinvoice.model.SearchInputModel;

/**
 * @author trungnt
 *
 */
@Path("/invoice")
public interface SInvoiceManagement {

	@POST
	@Path("/sinvoice/{code}/create")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	public Response getCreateInvoice(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code,
			@FormParam("dossier") String dossier, @FormParam("paymentConfig") String paymentConfig);

	/**
	 * @param request
	 * @param header
	 * @param company
	 * @param locale
	 * @param user
	 * @param serviceContext
	 * @param code
	 * @param supplierTaxCode
	 * @param invoiceNo
	 * @param transactionUuid
	 * @param fileType
	 * @param templateCode
	 * @param paid
	 * @return
	 * 
	 * @see
	 * 
	 * 		uri: /InvoiceAPI/InvoiceUtilsWS/getInvoiceRepresentationFile
	 * 
	 * 
	 *      supplierTaxCode Required: true, DataType: String, Minlength: 10,
	 *      Maxlength: 14 Format : [0-9-]+
	 * 
	 *      invoiceNo Required : true, DataType: String, Format :
	 *      [A-Z]{2}/[a-zA-Z0-9]{10}
	 * 
	 *      templateCode Required : true, DataType: String, Minlength : 11,
	 *      Maxlength : 11, Format : [a-zA-Z0-9]{7}/[0-9]{3}
	 * 
	 *      transactionUuid Required : false, DataType: String, Minlength : 10,
	 *      Maxlength : 36, Format : N/A
	 * 
	 *      fileType Required : false, DataType: String Minlength : 3, Maxlength :
	 *      3, Format : N/A(ZIP, PDF)
	 * 
	 *      paid Required : false, DataType: boolean, Minlength : 3, Maxlength : 3,
	 *      Format : N/A(true - da thanh toan, false - chua thanh toan)
	 */

	@POST
	@Path("/sinvoice/{code}/file")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })

	public Response getInvoiceFile(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code,
			@FormParam("supplierTaxCode") String supplierTaxCode, @FormParam("invoiceNo") String invoiceNo,
			@FormParam("templateCode") String templateCode, @FormParam("transactionUuid") String transactionUuid,
			@FormParam("fileType") String fileType, @FormParam("paid") Boolean paid);

	/**
	 * @param request
	 * @param header
	 * @param company
	 * @param locale
	 * @param user
	 * @param serviceContext
	 * @param code
	 * @param supplierTaxCode
	 * @param invoiceNo
	 * @param buyerIdNo
	 * @param reservationCode
	 * @param fileType
	 * @param templateCode
	 * @param strIssueDate
	 * @return
	 * 
	 * @see
	 * 
	 * 		uri: /InvoiceAPI/InvoiceUtilsWS/getInvoiceFilePortal
	 * 
	 *      supplierTaxCode Required: true, DataType: String, Minlength: Maxlength:
	 *      11, Format: [a-zA-Z0-9/]+
	 * 
	 *      templateCode Required : true, DataType: String, Minlength : 11,
	 *      Maxlength : 11, Format : [a-zA-Z0-9]{7}/[0-9]{3}
	 * 
	 *      invoiceNo Required: true, DataType: String, Minlength: 7, Maxlength: 13,
	 *      Format: [a-zA-Z0-9/]+
	 * 
	 *      buyerIdNo Required: false, DataType: String, Minlength: Maxlength: 100,
	 *      Format:
	 * 
	 *      reservationCode Required: true, DataType: String, Minlength: Maxlength:
	 *      100, Format:
	 * 
	 *      fileType Required: true, DataType: String, Minlength: Maxlength: 100,
	 *      Format:
	 * 
	 *      strIssueDate Required: true, DataType: String, Minlength: Maxlength:
	 *      Format: yyyyMMddHHmmss
	 */

	@POST
	@Path("/sinvoice/{code}/filewithreservationcode")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })

	public Response getInvoiceFile(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code,
			@FormParam("supplierTaxCode") String supplierTaxCode, @FormParam("invoiceNo") String invoiceNo,
			@FormParam("templateCode") String templateCode, @FormParam("strIssueDate") String strIssueDate,
			@FormParam("buyerIdNo") String buyerIdNo, @FormParam("reservationCode") String reservationCode,
			@FormParam("fileType") String fileType);

	/**
	 * @param request
	 * @param header
	 * @param company
	 * @param locale
	 * @param user
	 * @param serviceContext
	 * @param code
	 * @param supplierTaxCode
	 * @param invoiceNo
	 * @param templateCode
	 * @param strIssueDate
	 * @param exchangeUser
	 * @return
	 * 
	 * @see
	 * 
	 * 		uri: /InvoiceAPI/InvoiceWS/createExchangeInvoiceFile
	 * 
	 *      supplierTaxCode Required : true, DataType: String, Minlength : Maxlength
	 *      : 11, Format : [a-zA-Z0-9/]+
	 * 
	 *      templateCode Required : true, DataType: String, Minlength : 11,
	 *      Maxlength : 11, Format : [a-zA-Z0-9]{7}/[0-9]{3}
	 * 
	 *      invoiceNo Required : true, DataType: String, Minlength : 7, Maxlength :
	 *      13, Format : [a-zA-Z0-9/]+
	 * 
	 *      strIssueDate Required : true, DataType: String, Minlength : Maxlength :
	 *      Format: yyyyMMdd
	 * 
	 *      exchangeUser Required : true, DataType: String, Minlength : 1, Maxlength
	 *      : 100
	 * 
	 * 
	 */
	@POST
	@Path("/sinvoice/{code}/createexchangeinvoicefile")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })

	public Response createExchangeInvoiceFile(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code,
			@FormParam("supplierTaxCode") String supplierTaxCode, @FormParam("invoiceNo") String invoiceNo,
			@FormParam("templateCode") String templateCode, @FormParam("strIssueDate") String strIssueDate,
			@FormParam("exchangeUser") String exchangeUser);

	/**
	 * @param request
	 * @param header
	 * @param company
	 * @param locale
	 * @param user
	 * @param serviceContext
	 * @param code
	 * @param supplierTaxCode
	 * @param invoiceNo
	 * @param templateCode
	 * @param strIssueDate
	 * @param additionalReferenceDesc
	 * @param additionalReferenceDate
	 * @return
	 * 
	 * @see
	 * 
	 * 		uri: /InvoiceAPI/InvoiceWS/cancelTransactionInvoice
	 * 
	 *      supplierTaxCode Required : true, DataType: String, Minlength : Maxlength
	 *      : 11, Format : [a-zA-Z0-9/]+
	 * 
	 *      templateCode Required : true, DataType: String, Minlength : 11,
	 *      Maxlength : 11, Format : [a-zA-Z0-9]{7}/[0-9]{3}
	 * 
	 *      invoiceNo Required : true, DataType: String, Minlength : 7, Maxlength :
	 *      13, Format : [a-zA-Z0-9/]+
	 * 
	 *      strIssueDate Required : true, DataType: String Minlength : Maxlength :,
	 *      Format: yyyyMMddHHmmss
	 * 
	 *      additionalReferenceDesc Required : true, DataType: String, Minlength :
	 *      1, Maxlength : 100 - Tên văn bản thỏa thuận hủy hóa đơn
	 * 
	 *      additionalReferenceDate Required : true, DataType: String, Minlength :
	 *      Maxlength :, Format: yyyyMMddHHmmss - Ngày thỏa thuận Cần chi tiết
	 *      format hỗ trợ (không vượt quá ngày hiện tại)
	 * 
	 */
	@POST
	@Path("/sinvoice/{code}/canceltransactioninvoice")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })

	public Response cancelTransactionInvoice(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code,
			@FormParam("supplierTaxCode") String supplierTaxCode, @FormParam("invoiceNo") String invoiceNo,
			@FormParam("templateCode") String templateCode, @FormParam("strIssueDate") String strIssueDate,
			@FormParam("additionalReferenceDesc") String additionalReferenceDesc,
			@FormParam("additionalReferenceDate") String additionalReferenceDate);

	/**
	 * @param request
	 * @param header
	 * @param company
	 * @param locale
	 * @param user
	 * @param serviceContext
	 * @param code
	 * @param supplierTaxCode
	 * @param invoiceNo
	 * @param buyerEmailAddress
	 * @param strIssueDate
	 * @param paymentType
	 * @param paymentTypeName
	 * @param cusGetInvoiceRight
	 * @return
	 * 
	 * @see
	 * 
	 * 		uri: /InvoiceAPI/InvoiceWS/updatePaymentStatus
	 * 
	 *      supplierTaxCode,Required: true,DataType: String,Minlength:,Maxlength:
	 *      11,Format: [a-zA-Z0-9/]+
	 * 
	 *      invoiceNo,Required: true,DataType: String,Minlength: 7,Maxlength:
	 *      13,Format: [a-zA-Z0-9/]+
	 * 
	 *      buyerEmailAddress,Required: false,DataType: String,Minlength:,Maxlength:
	 *      50,Format:
	 *      ^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$
	 *      Email người mua
	 * 
	 *      strIssueDate,Required: true,DataType:
	 *      String,Minlength:,Maxlength:,Format: yyyyMMddHHmmss Ngày lập hóa đơn
	 * 
	 *      paymentType,Required: true,DataType: String,Minlength:,Maxlength:
	 *      30,Format: [a-zA-Z0-9-_./]+ Loại hình thức thanh toán
	 * 
	 *      paymentTypeName,Required: true,DataType: String,Minlength:,Maxlength:
	 *      100,Format: => Tên phương thức thanh toán
	 * 
	 *      cusGetInvoiceRight,Required: true,DataType:
	 *      Boolean,Minlength:,Maxlength: 1,Format: => Cho khách hàng lấy hóa đơn
	 * 
	 * 
	 */
	@POST
	@Path("/sinvoice/{code}/updatepaymentstatus")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })

	public Response updatePaymentStatus(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code,
			@FormParam("supplierTaxCode") String supplierTaxCode, @FormParam("invoiceNo") String invoiceNo,
			@FormParam("templateCode") String templateCode, @FormParam("strIssueDate") String strIssueDate,
			@FormParam("buyerEmailAddress") String buyerEmailAddress, @FormParam("paymentType") String paymentType,
			@FormParam("paymentTypeName") String paymentTypeName,
			@FormParam("cusGetInvoiceRight") boolean cusGetInvoiceRight);

	/**
	 * @param request
	 * @param header
	 * @param company
	 * @param locale
	 * @param user
	 * @param serviceContext
	 * @param code
	 * @param supplierTaxCode
	 * @param invoiceNo
	 * @param strIssueDate
	 * @return
	 * 
	 * @see
	 * 
	 * 		uri: /InvoiceAPI/InvoiceWS/cancelPaymentStatus
	 * 
	 *      supplierTaxCode,Required: true,DataType: String,Minlength:,Maxlength:
	 *      11,Format: [a-zA-Z0-9/]+
	 * 
	 *      invoiceNo,Required: true,DataType: String,Minlength: 7,Maxlength:
	 *      13,Format: [a-zA-Z0-9/]+
	 * 
	 *      strIssueDate,Required: true,DataType:
	 *      String,Minlength:,Maxlength:,Format: yyyyMMddHHmmss Ngày lập hóa đơn
	 */
	@POST
	@Path("/sinvoice/{code}/cancelpaymentstatus")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })

	public Response cancelPaymentStatus(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam("code") String code,
			@FormParam("supplierTaxCode") String supplierTaxCode, @FormParam("invoiceNo") String invoiceNo,
			@FormParam("strIssueDate") String strIssueDate);

	/**
	 * @param request
	 * @param header
	 * @param company
	 * @param locale
	 * @param user
	 * @param serviceContext
	 * @param code
	 * @param body
	 * @return
	 * 
	 * @see
	 * 
	 * 		uri: /InvoiceAPI/InvoiceUtilsWS/getInvoices/{supplierTaxCode}
	 */

	@POST
	@Path("/sinvoice/{code}/search")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })

	public Response search(@Context HttpServletRequest request, @Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code, SearchInputModel body);
}
