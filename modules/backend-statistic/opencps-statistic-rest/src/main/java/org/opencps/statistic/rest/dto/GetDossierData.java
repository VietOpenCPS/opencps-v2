package org.opencps.statistic.rest.dto;

import java.util.Date;

public class GetDossierData {
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

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public boolean getOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getExtendDate() {
		return extendDate;
	}

	public void setExtendDate(Date extendDate) {
		this.extendDate = extendDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	private String dossierStatus;
	private String dossierSubStatus;
	private Date receiveDate;
	private boolean online;
	private Date dueDate;
	private Date extendDate;
	private Date releaseDate;
	private String serviceCode;
	private String govAgencyCode;
	private long groupId;
	
}
