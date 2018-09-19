package org.opencps.api.vnpost.model;

import com.liferay.portal.kernel.json.JSONObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import backend.postal.api.rest.controller.impl.VnPostTerm;

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

	public VNPostServerConfigModel(String apiPostOrder, String apiGetOrderTracking, String apiGetToken,
			String apiCancelOrder, String customerKey, String secretKey, Boolean active) {
		this.apiPostOrder = apiPostOrder;
		this.apiGetOrderTracking = apiGetOrderTracking;
		this.apiCancelOrder = apiCancelOrder;
		this.apiGetToken = apiGetToken;
		this.customerKey = customerKey;
		this.secretKey = secretKey;
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
