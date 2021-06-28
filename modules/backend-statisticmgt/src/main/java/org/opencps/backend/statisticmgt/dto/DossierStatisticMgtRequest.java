package org.opencps.backend.statisticmgt.dto;

public class DossierStatisticMgtRequest {

	private String govAgencyCode;
	private String domainCode;
	private int month;
	private int year;
	private long groupId;
	private int start;
	private int end;
	private int groupBy;
	public String getGovAgencyCode() {
		return govAgencyCode;
	}
	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}
	
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(int groupBy) {
		this.groupBy = groupBy;
	}
	
	
}
