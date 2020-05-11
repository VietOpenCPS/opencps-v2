package org.opencps.dossiermgt.constants;

public class VTPayTerm {

	// VTPAY
	public static final String VTPAY_GENQR =  "genQRCode";
	public static final String ATTACHMENT_FILENAME_FORMAT = "attachment; filename=\"%s\"";
	public static final String PNG_TYPE = "image/png";
	
	public static final String VTP_CONFIG = "vietPayConfig";
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
	public static final String MC_URL = "mc_url";
	public static final String DVC_URL = "dvc_url";
	
	public static final String createBillCode (String govAgencyCode, String invoiceNo) {
		String billcode =  govAgencyCode + "___" + invoiceNo;
		if (billcode.length() >= 20) {
			return billcode.substring(0, 20);
		} else {
			return billcode;
		}
	}
	public static final String createOrderId (long dossierId, String dossierNo) {
		String orderId = dossierId + "___" + dossierNo;
		if (orderId.length() >= 20) {
			return orderId.substring(0, 20);
		} else {
			return orderId;
		}
	}

	public static final String getInvoiceNoByBillCode (String billcode) {
		return billcode.split("___")[1];
	}
	public static final long getDossierIdByOrderId (String order_id) {
		return Long.parseLong(order_id.split("___")[0]);
	}
	public static final String getDossierNoByOrderId (String order_id) {
		return order_id.split("___")[1];
	}
}
