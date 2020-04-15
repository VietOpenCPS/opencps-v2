package org.fds.opencps.paygate.integration.util;

public class PayGateTerm {
	public static final String ATTACHMENT_FILENAME_FORMAT = "attachment; filename=\"%s\"";
	public static final String PNG_TYPE = "image/png";
	
	public static final String VTP_PROTOCOL = "VTP_QRCODE";
	public static final String PRIORITY = "priority";
	public static final String KEY = "key";
	public static final String VALUE = "value";
	public static final String VERSION = "version";
	public static final String TYPE = "type";
	public static final String BILLCODE = "billcode";
	public static final String ORDER_ID = "order_id";
	public static final String AMOUNT = "amount";
	public static final String MERCHANT_CODE = "merchant_code";
	public static final String DOT_PNG = ".png";
	public static final String PNG_UPPER = "PNG";
	public static final String ERROR_CODE_03 = "03";
	public static final String ERROR_CODE_01 = "01";
	public static final String ERROR_CODE_02 = "02";
	public static final String ERROR_CODE_00 = "00";
	public static final String ACCESS_CODE = "access_code";
	public static final String HASH_KEY = "hash_key";
	public static final String HMAC_SHA1 = "HmacSHA1";
	public static final String CHECK_SUM = "check_sum";
	public static final String TRANS_AMOUNT = "trans_amount";
	public static final String ERROR_CODE = "error_code";
	public static final String HEX_FORMAT = "%02x";
	public static final String RETURN_URL = "return_url";
	public static final String RETURN_BILL_CODE = "return_bill_code";
	public static final String RETURN_OTHER_INFO = "return_other_info";

	public static final String URL_SEARCH = "url_search";
	public static final String CMD_SEARCH = "cmd_search";
	public static final String VERSION_SEARCH = "version_search";
	public static final String ENCODING_CHECKSUM = "version_search";

	public static final String CUST_MSISDN = "cust_msisdn";
	public static final String VT_TRANSACTION_ID = "vt_transaction_id";
	public static final String PAYMENT_STATUS = "payment_status";

	public static final String ENDPOINT_CONFIRM = "o/pgi/vtp/mcpaymentconfirm";
	public static final String ENDPOINT_RECEIVER = "o/pgi/vtp/mcreceiveresult";
	public static final String ENDPOINT_SEARCH = "o/pgi/vtp/mcsearch";
	public static final String ENDPOINT_DVCRECEIVER = "o/pgi/vtp/dvcreceiveresult";

	public static final String ACTION_IS_ONLINE = "actionIsOnline";
	public static final String ACTION_IS_NOT_ONLINE = "actionIsNotOnline";
	public static final String ACTION_CODE = "actionCode";
	public static final String PAYMENT = "payment";
	public static final String URL = "url";
	public static final String USERNAME = "userName";
	public static final String PWD = "pwd";
	public static final String ACTION_ENPOINT = "actions";
	
	public static String buildPathDoAction (String path, long dossierId) {
		
		return path + "/o/rest/v2/dossiers/" + dossierId + "/actions";
	};
}
