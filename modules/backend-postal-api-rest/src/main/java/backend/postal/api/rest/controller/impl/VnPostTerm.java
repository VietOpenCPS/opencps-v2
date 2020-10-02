package backend.postal.api.rest.controller.impl;

public class VnPostTerm {
	
	public static final String SERVER_CONFIG_EINVOICE = "EINVOICE";
	public static final String SERVER_CONFIG_VIA_POSTAL = "VIA_POSTAL";
	public static final String SERVER_CONFIG_VIA_POSTAL_SERVERNO_D = "VNPOST";
	
	public static final String MIME_HEADER_SOAPACTION = "http://tempuri.org/Fs_NH_GTGT";
	public static final String MIME_HEADER_CONTENT_TYPE = "text/xml; charset=utf-8";
	public static final String MIME_HEADER_PROXY_CONNECTION = "keep-alive";
	public static final String SOAP_CONNECTION_CONTENT_TYPE = "text/xml";
	public static final String SOAP_CONNECTION_ACCEPT = "application/soap+xml, text/*";
	public static final int SOAP_CONNECTION_TIMEOUT = 3 * 1000;
	public static final int SOAP_CONNECTION_READ_TIMEOUT = 3 * 1000;
	public static final String HOADONT_MA = "01GTKT0/001";
	public static final String HOADONT_MA_N_THUE = "01";
	public static final String HOADONT_KIEU_SO = "G";

	public static final String EVALUATION_SCORE_DEFAULT = "0";
	
	public static final String INVOKE_MESSAGE_ERROR_SERVER_CONFIG = "NULL_SERVECE_CONFIG";
	public static final String INVOKE_MESSAGE_ERROR_SERVER_CONFIG_DESC = "Not found server config with code = ";
	public static final String INVOKE_MESSAGE_ERROR_NOT_ACCEPT_IP_ADDRESS_OR_CONNECTED_ERROR = "NOT_ACCEPT_IP_ADDRESS_OR_CONNECTED_ERROR";
	public static final String INVOKE_MESSAGE_ERROR_NOT_ACCEPT_IP_ADDRESS_OR_CONNECTED_ERROR_DESC = "Not accept ip address or connected error";
	
	public static final String S_INVOICE_CMD_CREATE_INVOICE = "createInvoice";
	public static final String S_INVOICE_CMD_GET_INVOICE_FILE = "getInvoiceFile";
	public static final String S_INVOICE_CMD_GET_INVOICE_FILE_PORTAL = "getInvoiceFilePortal";
	public static final String S_INVOICE_CMD_CREATE_EXCHANGE_INVOICE_FILE = "createExchangeInvoiceFile";
	public static final String S_INVOICE_CMD_CANCEL_TRANSACTION_INVOICE = "cancelTransactionInvoice";
	public static final String S_INVOICE_CMD_UPDATE_PAYMENT_STATUS = "updatePaymentStatus";
	public static final String S_INVOICE_CMD_CANCEL_PAYMENT_STATUS = "cancelPaymentStatus";
	public static final String S_INVOICE_CMD_GET_INVOICES = "getInvoices";
	
	public static final String S_INVOICE_CHARSET = "UTF-8";
	public static final String S_INVOICE_CHARSET_LOWER = "utf-8";

	
	public static final String SERVER_APIPOSTORDER = "apiPostOrder";
	public static final String SERVER_APICANCELORDER = "apiCancelOrder";
	public static final String SERVER_APIGETORDERTRACKING = "apiGetOrderTracking";
	public static final String SERVER_APIGETTOKEN = "apiGetToken";
	public static final String SERVER_CUSTOMERKEY = "customerKey";
	public static final String SERVER_SECRETKEY = "secretKey";
	public static final String SERVER_ACTIVE = "active";
	public static final String SERVER_KEY_TOKEN_LGSP = "keyToken";
	public static final String SERVER_IS_THROUGH_LGSP = "isThroughLGSP";
	public static final String SERVER_CUSTOMERCODE = "customerCode";
	public static final String SERVER_SENDERPROVINCE = "senderProvince";
	public static final String SERVER_SENDERDISTRICT = "senderDistrict";
	public static final String SERVER_SENDERADDRESS = "senderAddress";
	public static final String SERVER_SENDEREMAIL = "senderEmail";
	public static final String SERVER_SENDERTEL = "senderTel";
	public static final String SERVER_SENDERNAME = "senderName";
	
	public static final String STATUS_CODE = "statusCode";
	public static final String STATUS_MESSAGE = "statusMessage";
	
	public static String getVNPostServerNo(String govAgencyCode) {
		return "VNPOST_" + govAgencyCode;
	}
}
