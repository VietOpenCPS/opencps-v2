package org.opencps.statistic.rest.dto;

public class GetDossierData {

	private String dossierStatus;
	private String dossierSubStatus;
	private String receiveDate;
	private boolean online;
	private String dueDate;
	private String extendDate;
	private String releaseDate;
	private String finishDate;
	private String serviceCode;
	private String serviceName;
	private String govAgencyCode;
	private String govAgencyName;
	private long groupId;
	private String domainCode;
	private String domainName;
	private String lockState;
	private String system;
	private int viaPostal;
	private int serviceLevel;
	private int fromViaPostal;
	private String dossierNo;

	public boolean isOnline() {
		return online;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDossierNo() {
		return dossierNo;
	}

	public void setDossierNo(String dossierNo) {
		this.dossierNo = dossierNo;
	}

	public int getFromViaPostal() {
		return fromViaPostal;
	}

	public void setFromViaPostal(int fromViaPostal) {
		this.fromViaPostal = fromViaPostal;
	}

	public int getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(int serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public int getViaPostal() {
		return viaPostal;
	}

	public void setViaPostal(int viaPostal) {
		this.viaPostal = viaPostal;
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

	public String getGovAgencyCode() {
		return govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public boolean getOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}


	public String getGovAgencyName() {
		return govAgencyName;
	}

	public void setGovAgencyName(String govAgencyName) {
		this.govAgencyName = govAgencyName;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getExtendDate() {
		return extendDate;
	}

	public void setExtendDate(String extendDate) {
		this.extendDate = extendDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getLockState() {
		return lockState;
	}

	public void setLockState(String lockState) {
		this.lockState = lockState;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}
