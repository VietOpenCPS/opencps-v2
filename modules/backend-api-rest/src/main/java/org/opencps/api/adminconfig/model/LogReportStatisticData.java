package org.opencps.api.adminconfig.model;

public class LogReportStatisticData {

	private String apiCode;
	private String apiName;
	private String apiDescription;
	private long groupId;
	private int totalAccess = 0;
	private int totalAccessSuc = 0;
	private int totalAccessFal = 0;
	public String getApiCode() {
		return apiCode;
	}
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public int getTotalAccess() {
		return totalAccess;
	}
	public void setTotalAccess(int totalAccess) {
		this.totalAccess = totalAccess;
	}
	public int getTotalAccessSuc() {
		return totalAccessSuc;
	}
	public void setTotalAccessSuc(int totalAccessSuc) {
		this.totalAccessSuc = totalAccessSuc;
	}
	public int getTotalAccessFal() {
		return totalAccessFal;
	}
	public void setTotalAccessFal(int totalAccessFal) {
		this.totalAccessFal = totalAccessFal;
	}
	public String getApiDescription() {
		return apiDescription;
	}
	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
	}
	
	
}
