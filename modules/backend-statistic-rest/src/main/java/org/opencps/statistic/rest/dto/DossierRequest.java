package org.opencps.statistic.rest.dto;

public class DossierRequest extends CommonRequest {

	public String getRegisterBookCode() {
		return registerBookCode;
	}
	public void setRegisterBookCode(String registerBookCode) {
		this.registerBookCode = registerBookCode;
	}
	public String getProcessNo() {
		return processNo;
	}
	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getGovAgencyCode() {
		return govAgencyCode;
	}
	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}
	public String getApplicantIdType() {
		return applicantIdType;
	}
	public void setApplicantIdType(String applicantIdType) {
		this.applicantIdType = applicantIdType;
	}
	public String getApplicantIdNo() {
		return applicantIdNo;
	}
	public void setApplicantIdNo(String applicantIdNo) {
		this.applicantIdNo = applicantIdNo;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getContactTelNo() {
		return contactTelNo;
	}
	public void setContactTelNo(String contactTelNo) {
		this.contactTelNo = contactTelNo;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getDelegateIdNo() {
		return delegateIdNo;
	}
	public void setDelegateIdNo(String delegateIdNo) {
		this.delegateIdNo = delegateIdNo;
	}
	public String getDelegateTelNo() {
		return delegateTelNo;
	}
	public void setDelegateTelNo(String delegateTelNo) {
		this.delegateTelNo = delegateTelNo;
	}
	public String getDossierStatus() {
		return dossierStatus;
	}
	public void setDossierStatus(String dossierStatus) {
		this.dossierStatus = dossierStatus;
	}
	public String getDossierSubStatus() {
		return dossierSubStatus;
	}
	public void setDossierSubStatus(String dossierSubStatus) {
		this.dossierSubStatus = dossierSubStatus;
	}
	public long getDossierActionId() {
		return dossierActionId;
	}
	public void setDossierActionId(long dossierActionId) {
		this.dossierActionId = dossierActionId;
	}
	public int getViaPostal() {
		return viaPostal;
	}
	public void setViaPostal(int viaPostal) {
		this.viaPostal = viaPostal;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public int getOriginality() {
		return originality;
	}
	public void setOriginality(int originality) {
		this.originality = originality;
	}
	public String getServerNo() {
		return serverNo;
	}
	public void setServerNo(String serverNo) {
		this.serverNo = serverNo;
	}
	public long getOriginDossierId() {
		return originDossierId;
	}
	public void setOriginDossierId(long originDossierId) {
		this.originDossierId = originDossierId;
	}
	public boolean isOnlineValue() {
		return onlineValue;
	}
	public void setOnlineValue(boolean onlineValue) {
		this.onlineValue = onlineValue;
	}
	
	public boolean isReceived() {
		return received;
	}
	public void setReceived(boolean received) {
		this.received = received;
	}
	public boolean isReleased() {
		return released;
	}
	public void setReleased(boolean released) {
		this.released = released;
	}
	public boolean isBetime() {
		return betime;
	}
	public void setBetime(boolean betime) {
		this.betime = betime;
	}
	public boolean isOntime() {
		return ontime;
	}
	public void setOntime(boolean ontime) {
		this.ontime = ontime;
	}

	public boolean isUndue() {
		return undue;
	}
	public void setUndue(boolean undue) {
		this.undue = undue;
	}

	private String registerBookCode;
	private String processNo;
	private String serviceCode;
	private String govAgencyCode;
	private String applicantIdType;
	private String applicantIdNo;
	private String cityCode;
	private String districtCode;
	private String wardCode;
	private String contactTelNo;
	private String contactEmail;
	private String delegateIdNo;
	private String delegateTelNo;
	private String dossierStatus;
	private String dossierSubStatus;
	private long dossierActionId;
	private int viaPostal;
	private boolean online;
	private boolean onlineValue;
	private int originality;
	private String serverNo;
	private long originDossierId;
	private boolean received;
	private boolean released;
	private boolean betime;
	private boolean ontime;
	private boolean undue;
}
