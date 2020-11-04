package org.opencps.api.vnpost.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VNPostServerConfigModel", propOrder = { "apiPostOrder", "apiGetOrderTracking", "apiGetToken",
		"apiCancelOrder", "customerKey", "secretKey" })
public class VNPostServerConfigModel {
	private String apiPostOrder;

	private String apiGetOrderTracking;

	private String apiGetToken;

	private String apiCancelOrder;

	private String customerKey;

	private String secretKey;
	
	private boolean active;
	
	private String customerCode;
	
	private Integer senderProvince;
	
	private Integer senderDistrict;
	
	private String senderAddress;
	
	private String senderEmail;

	private String senderTel;

	private String senderName;

	private boolean isLGSP;

	private String keyToken;
	
	private boolean lgspActive;
	private String lgspUsername;
	private String lgspPassword;
	private String lgspSercureKey;
	private String lgspCustomerCode;
	private String lgspConsumerKey;
	private String lgspTokenUrl;
	private String lgspVnpostUrl;
	private String lgspVnpostOrderPost;
	private String lgspVnpostOrderCancel;
	private String lgspVnpostOrderTracking;
	private String lgspVnpostGetPostagevas;
	private String lgspVnpostGetInfomationPost;
	private String lgspVnpostPricehcc;
	private String lgspVnpostDocument;
	private String lgspVnpostGetPrice;
	


	public boolean isLgspActive() {
		return lgspActive;
	}

	public void setLgspActive(boolean lgspActive) {
		this.lgspActive = lgspActive;
	}

	public String getLgspUsername() {
		return lgspUsername;
	}

	public void setLgspUsername(String lgspUsername) {
		this.lgspUsername = lgspUsername;
	}

	public String getLgspPassword() {
		return lgspPassword;
	}

	public void setLgspPassword(String lgspPassword) {
		this.lgspPassword = lgspPassword;
	}

	public String getLgspSercureKey() {
		return lgspSercureKey;
	}

	public void setLgspSercureKey(String lgspSercureKey) {
		this.lgspSercureKey = lgspSercureKey;
	}

	public String getLgspCustomerCode() {
		return lgspCustomerCode;
	}

	public void setLgspCustomerCode(String lgspCustomerCode) {
		this.lgspCustomerCode = lgspCustomerCode;
	}

	public String getLgspConsumerKey() {
		return lgspConsumerKey;
	}

	public void setLgspConsumerKey(String lgspConsumerKey) {
		this.lgspConsumerKey = lgspConsumerKey;
	}

	public String getLgspTokenUrl() {
		return lgspTokenUrl;
	}

	public void setLgspTokenUrl(String lgspTokenUrl) {
		this.lgspTokenUrl = lgspTokenUrl;
	}

	public String getLgspVnpostUrl() {
		return lgspVnpostUrl;
	}

	public void setLgspVnpostUrl(String lgspVnpostUrl) {
		this.lgspVnpostUrl = lgspVnpostUrl;
	}

	public String getLgspVnpostOrderPost() {
		return lgspVnpostOrderPost;
	}

	public void setLgspVnpostOrderPost(String lgspVnpostOrderPost) {
		this.lgspVnpostOrderPost = lgspVnpostOrderPost;
	}

	public String getLgspVnpostOrderCancel() {
		return lgspVnpostOrderCancel;
	}

	public void setLgspVnpostOrderCancel(String lgspVnpostOrderCancel) {
		this.lgspVnpostOrderCancel = lgspVnpostOrderCancel;
	}

	public String getLgspVnpostOrderTracking() {
		return lgspVnpostOrderTracking;
	}

	public void setLgspVnpostOrderTracking(String lgspVnpostOrderTracking) {
		this.lgspVnpostOrderTracking = lgspVnpostOrderTracking;
	}

	public String getLgspVnpostGetPostagevas() {
		return lgspVnpostGetPostagevas;
	}

	public void setLgspVnpostGetPostagevas(String lgspVnpostGetPostagevas) {
		this.lgspVnpostGetPostagevas = lgspVnpostGetPostagevas;
	}

	public String getLgspVnpostGetInfomationPost() {
		return lgspVnpostGetInfomationPost;
	}

	public void setLgspVnpostGetInfomationPost(String lgspVnpostGetInfomationPost) {
		this.lgspVnpostGetInfomationPost = lgspVnpostGetInfomationPost;
	}

	public String getLgspVnpostPricehcc() {
		return lgspVnpostPricehcc;
	}

	public void setLgspVnpostPricehcc(String lgspVnpostPricehcc) {
		this.lgspVnpostPricehcc = lgspVnpostPricehcc;
	}

	public String getLgspVnpostDocument() {
		return lgspVnpostDocument;
	}

	public void setLgspVnpostDocument(String lgspVnpostDocument) {
		this.lgspVnpostDocument = lgspVnpostDocument;
	}

	public String getLgspVnpostGetPrice() {
		return lgspVnpostGetPrice;
	}

	public void setLgspVnpostGetPrice(String lgspVnpostGetPrice) {
		this.lgspVnpostGetPrice = lgspVnpostGetPrice;
	}

	public boolean isActive() {
		return active;
	}

	public String getKeyToken() {
		return keyToken;
	}

	public void setKeyToken(String keyToken) {
		this.keyToken = keyToken;
	}

	public boolean isLGSP() {
		return isLGSP;
	}

	public void setLGSP(boolean LGSP) {
		isLGSP = LGSP;
	}

	public VNPostServerConfigModel(String apiPostOrder, String apiGetOrderTracking, String apiGetToken,
								   String apiCancelOrder, String customerKey, String secretKey, boolean active, String customerCode,
								   Integer senderProvince, Integer senderDistrict, String senderAddress, String senderEmail, String senderTel, String senderName,
								   boolean lgspActive,String lgspUsername,String lgspPassword,String lgspSercureKey,String lgspCustomerCode,String lgspTokenUrl,String lgspVnpostUrl,
								   String lgspVnpostOrderPost,String lgspVnpostOrderCancel,String lgspVnpostOrderTracking,String lgspVnpostGetPostagevas,String lgspVnpostGetInfomationPost,
								   String lgspVnpostPricehcc,String lgspVnpostDocument,String lgspVnpostGetPrice,String lgspConsumerKey) {
		super();
		this.apiPostOrder = apiPostOrder;
		this.apiGetOrderTracking = apiGetOrderTracking;
		this.apiGetToken = apiGetToken;
		this.apiCancelOrder = apiCancelOrder;
		this.customerKey = customerKey;
		this.secretKey = secretKey;
		this.active = active;
		this.customerCode = customerCode;
		this.senderProvince = senderProvince;
		this.senderDistrict = senderDistrict;
		this.senderAddress = senderAddress;
		this.senderEmail = senderEmail;
		this.senderTel = senderTel;
		this.senderName = senderName;
		
		this.lgspActive = lgspActive;
		this.lgspUsername = lgspUsername;
		this.lgspPassword = lgspPassword;
		this.lgspSercureKey = lgspSercureKey;
		this.lgspCustomerCode = lgspCustomerCode;
		this.lgspConsumerKey = lgspConsumerKey;
		this.lgspTokenUrl = lgspTokenUrl;
		this.lgspVnpostUrl = lgspVnpostUrl;
		this.lgspVnpostOrderPost = lgspVnpostOrderPost;
		this.lgspVnpostOrderCancel = lgspVnpostOrderCancel;
		this.lgspVnpostOrderTracking = lgspVnpostOrderTracking;
		this.lgspVnpostGetPostagevas =lgspVnpostGetPostagevas;
		this.lgspVnpostGetInfomationPost = lgspVnpostGetInfomationPost;
		this.lgspVnpostPricehcc = lgspVnpostPricehcc;
		this.lgspVnpostDocument = lgspVnpostDocument;
		this.lgspVnpostGetPrice = lgspVnpostGetPrice;
	}
	

	public String getCustomerCode() {
		return customerCode;
	}


	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}


	public Integer getSenderProvince() {
		return senderProvince;
	}


	public void setSenderProvince(Integer senderProvince) {
		this.senderProvince = senderProvince;
	}


	public Integer getSenderDistrict() {
		return senderDistrict;
	}


	public void setSenderDistrict(Integer senderDistrict) {
		this.senderDistrict = senderDistrict;
	}


	public String getSenderAddress() {
		return senderAddress;
	}


	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}


	public String getSenderEmail() {
		return senderEmail;
	}


	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}


	public String getSenderTel() {
		return senderTel;
	}


	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getApiPostOrder() {
		return apiPostOrder;
	}

	public void setApiPostOrder(String apiPostOrder) {
		this.apiPostOrder = apiPostOrder;
	}

	public String getApiGetOrderTracking() {
		return apiGetOrderTracking;
	}

	public void setApiGetOrderTracking(String apiGetOrderTracking) {
		this.apiGetOrderTracking = apiGetOrderTracking;
	}

	public String getApiGetToken() {
		return apiGetToken;
	}

	public void setApiGetToken(String apiGetToken) {
		this.apiGetToken = apiGetToken;
	}

	public String getApiCancelOrder() {
		return apiCancelOrder;
	}

	public void setApiCancelOrder(String apiCancelOrder) {
		this.apiCancelOrder = apiCancelOrder;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	
}
