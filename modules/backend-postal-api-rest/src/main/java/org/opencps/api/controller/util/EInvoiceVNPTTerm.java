package org.opencps.api.controller.util;

public class EInvoiceVNPTTerm {

	//common
	public static final String NAME_SPACE = "tem";
	public static final String NAME_SPACE_URI = "http://tempuri.org/";
	public static final String updateCusRequestFile = "UpdateCusRequest.xml";	
	public static final String importAndPublishInvFile = "ImportAndPublishInv.xml";	
	public static final String downloadInvPDFFkeyNoPayFile = "downloadInvPDFFkeyNoPay.xml";
	
	//Soap xml request api UpdateCus
	public static final String UPDATE_CUS = "tem:UpdateCus";
	public static final String XMLCUSDATA = "tem:XMLCusData";
	public static final String UPDATE_CUS_SOAP_ENDPOINT = 
			"https://vpboxaydungadmindemo.vnpt-invoice.com.vn/PublishService.asmx";
	public static final String UPDATE_CUS_SOAP_ACTION = 
			"http://tempuri.org/UpdateCus";
	public static final String UPDATE_CUS_RES = "UpdateCusResponse";
	public static final String UPDATE_CUS_RES_MESSAGE_ERR1 = "Tài khoản đăng nhập sai hoặc không có quyền thêm khách hàng";
	public static final String UPDATE_CUS_RES_MESSAGE_ERR2 = "Không import được khách hàng vào db";
	public static final String UPDATE_CUS_RES_MESSAGE_ERR3 = "Dữ liệu xml đầu vào không đúng quy định";
	public static final String UPDATE_CUS_RES_MESSAGE_SUCCESS = "Import dữ liệu khách hàng thành công";

	
	
	
	public static final String IMPORTANDPUBLISHINV = "tem:ImportAndPublishInv";
	public static final String XMLINVDATA = "tem:xmlInvData";
	public static final String IMPORTANDPUBLISHINV_SOAP_ENDPOINT = 
			"https://vpboxaydungadmindemo.vnpt-invoice.com.vn/PublishService.asmx";
	public static final String IMPORTANDPUBLISHINV_SOAP_ACTION = 
			"http://tempuri.org/ImportAndPublishInv";
	public static final String IMPORTANDPUBLISHINV_RES = "ImportAndPublishInvResponse";
	public static final String IMPORTANDPUBLISHINV_RES_MESSAGE_ERR1 = "Tài khoản đăng nhập sai hoặc không có quyền thêm khách hàng";
	public static final String IMPORTANDPUBLISHINV_RES_MESSAGE_ERR3 = "Dữ liệu xml đầu vào không đúng quy định";
	public static final String IMPORTANDPUBLISHINV_RES_MESSAGE_ERR5 = "Không phát hành được BIÊN LAI";
	public static final String IMPORTANDPUBLISHINV_RES_MESSAGE_ERR7 = "User name không phù hợp, không tìm thấy company tương ứng cho user.";
	public static final String IMPORTANDPUBLISHINV_RES_MESSAGE_ERR13 = "Trùng mã fkey khi phát hành biên lai";
	public static final String IMPORTANDPUBLISHINV_RES_MESSAGE_ERR20 = "Pattern và serial không phù hợp, hoặc không tồn tại BIÊN LAI đã đăng kí có sử dụng Pattern và serial truyền vào";
	public static final String IMPORTANDPUBLISHINV_RES_MESSAGE_SUCCESS = "Biên lai đã phát hành thành công";

	public static final String DOWNLOADINVPDFFKEYNOPAY = "downloadInvPDFFkeyNoPay";
	public static final String FKEY = "fkey";
	public static final String DOWNLOADINVPDFFKEYNOPAY_SOAP_ENDPOINT = 
			"https://vpboxaydungadmindemo.vnpt-invoice.com.vn/PortalService.asmx?wsdl";
	public static final String DOWNLOADINVPDFFKEYNOPAY_SOAP_ACTION = 
			"http://tempuri.org/downloadInvPDFFkeyNoPay";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES = "downloadInvPDFFkeyNoPayResponse";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR1 = "Tài khoản đăng nhập sai";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR6 = "Chuỗi fkey không chính xác, BIÊN LAI không tồn tại";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR7 = "Công ty không tồn tại";

}
