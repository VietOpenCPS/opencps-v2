package org.opencps.api.controller.util;

public class EInvoiceVNPTTerm {
	
	//Key config in epaymentConfig
	public static final String ACCOUNT = "account";
	public static final String ACPASS = "acpass";
	public static final String USER_NAME = "username";
	public static final String PASS_WORD = "password";
	public static final String PATTERN = "pattern";
	public static final String SERIAL = "serial";
	public static final String CONVERT = "convert";
	public static final String UPDATE_CUS_SOAP_ENDPOINT = "updateCusSoapEndPoint";
	public static final String IMPORTANDPUBLISHINV_SOAP_ENDPOINT = "ImportAndPublishInvSoapEndPoint";
	public static final String DOWNLOADINVPDFFKEYNOPAY_SOAP_ENDPOINT = "downloadInvPDFFkeyNoPaySoapEndPoint";


	//common
	public static final String NAME_SPACE = "tem";
	public static final String NAME_SPACE_URI = "http://tempuri.org/";
	public static final String updateCusRequestFile = "UpdateCusRequest.xml";	
	public static final String importAndPublishInvFile = "ImportAndPublishInv.xml";	
	public static final String downloadInvPDFFkeyNoPayFile = "downloadInvPDFFkeyNoPay.xml";
	
	//XML Node
	public static final String XML_INVDATA = "tem:xmlInvData";
	public static final String XML_UPDATE_CUS = "tem:UpdateCus";
	public static final String XML_CUSDATA = "tem:XMLCusData";
	public static final String XML_ACCOUNT = "tem:Account";
	public static final String XML_ACPASS= "tem:ACpass";
	public static final String XML_USERNAME = "tem:username";
	public static final String XML_PASSWORD = "tem:password";
	public static final String XML_PATTERN = "tem:pattern";
	public static final String XML_SERIAL = "tem:serial";
	public static final String XML_CONVERT = "tem:convert";
	public static final String XML_PASS = "tem:pass";
	public static final String XML_UserName = "tem:userName";
	public static final String XML_UserPass = "tem:userPass";
	public static final String XML_FKEY = "tem:fkey";

	
	//api update customer
	public static final String UPDATE_CUS_SOAP_ACTION = 
			"http://tempuri.org/UpdateCus";
	public static final String UPDATE_CUS_RES = "UpdateCusResponse";
	public static final String UPDATE_CUS_RES_MESSAGE_ERR1 = "Tài khoản đăng nhập sai hoặc không có quyền thêm khách hàng";
	public static final String UPDATE_CUS_RES_MESSAGE_ERR2 = "Không import được khách hàng vào db";
	public static final String UPDATE_CUS_RES_MESSAGE_ERR3 = "Dữ liệu xml đầu vào không đúng quy định";
	public static final String UPDATE_CUS_RES_MESSAGE_SUCCESS = "Import dữ liệu khách hàng thành công";

	//api ImportAndPublishInv
	public static final String IMPORTANDPUBLISHINV = "tem:ImportAndPublishInv";
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

	//api downloadInvPDFFkeyNoPay
	public static final String DOWNLOADINVPDFFKEYNOPAY = "tem:downloadInvPDFFkeyNoPay";
	public static final String DOWNLOADINVPDFFKEYNOPAY_SOAP_ACTION = 
			"http://tempuri.org/downloadInvPDFFkeyNoPay";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES = "downloadInvPDFFkeyNoPayResponse";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR1 = "Tài khoản đăng nhập sai";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR6 = "Chuỗi fkey không chính xác, BIÊN LAI không tồn tại";
	public static final String DOWNLOADINVPDFFKEYNOPAY_RES_MESSAGE_ERR7 = "Công ty không tồn tại";

}
