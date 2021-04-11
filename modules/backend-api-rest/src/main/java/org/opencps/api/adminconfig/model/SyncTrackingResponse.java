package org.opencps.api.adminconfig.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncTrackingResponse", propOrder = {
    "dossierNo",
    "createDate",
    "stateSync",
    "serviceCode",
    "fromUnit",
    "toUnit",
    "bodyRequest",
    "bodyResponse",
    "api",
    "trackingId"
})
public class SyncTrackingResponse {
    public String dossierNo;
    public Long createDate;
    public Integer stateSync;
    public String serviceCode;
    public String fromUnit;
    public String toUnit;
    public String bodyRequest;
    public String bodyResponse;
    public String api;
    public Long trackingId;
	public String getDossierNo() {
		return dossierNo;
	}
	public void setDossierNo(String dossierNo) {
		this.dossierNo = dossierNo;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public Integer getStateSync() {
		return stateSync;
	}
	public void setStateSync(Integer stateSync) {
		this.stateSync = stateSync;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getFromUnit() {
		return fromUnit;
	}
	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
	}
	public String getToUnit() {
		return toUnit;
	}
	public void setToUnit(String toUnit) {
		this.toUnit = toUnit;
	}
	public String getBodyRequest() {
		return bodyRequest;
	}
	public void setBodyRequest(String bodyRequest) {
		this.bodyRequest = bodyRequest;
	}
	public String getBodyResponse() {
		return bodyResponse;
	}
	public void setBodyResponse(String bodyResponse) {
		this.bodyResponse = bodyResponse;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public Long getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(Long trackingId) {
		this.trackingId = trackingId;
	}
    
    
}
