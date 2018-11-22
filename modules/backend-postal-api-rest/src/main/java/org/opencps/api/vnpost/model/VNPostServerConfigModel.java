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

	public VNPostServerConfigModel(String apiPostOrder, String apiGetOrderTracking, String apiGetToken,
			String apiCancelOrder, String customerKey, String secretKey, boolean active, String customerCode,
			Integer senderProvince, Integer senderDistrict, String senderAddress, String senderEmail, String senderTel) {
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

	
}
