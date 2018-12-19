package org.opencps.statistic.rest.dto;

public class VotingResultData {
	private int selected;
	private long votingId;
	private String modifiedDate;
	private String createDate;
	private long groupId;
	private long userId;
	private String className;
	private String classPK;
	private String votingCode;
	private String serviceCode;
	private String serviceName;
	private String govAgencyCode;
	private String govAgencyName;

	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public long getVotingId() {
		return votingId;
	}
	public void setVotingId(long votingId) {
		this.votingId = votingId;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassPK() {
		return classPK;
	}
	public void setClassPK(String classPK) {
		this.classPK = classPK;
	}
	public String getVotingCode() {
		return votingCode;
	}
	public void setVotingCode(String votingCode) {
		this.votingCode = votingCode;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getGovAgencyCode() {
		return govAgencyCode;
	}
	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}
	public String getGovAgencyName() {
		return govAgencyName;
	}
	public void setGovAgencyName(String govAgencyName) {
		this.govAgencyName = govAgencyName;
	}

}
