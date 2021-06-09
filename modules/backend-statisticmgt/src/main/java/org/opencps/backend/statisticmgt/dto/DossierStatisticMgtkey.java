package org.opencps.backend.statisticmgt.dto;

public class DossierStatisticMgtkey {
	private String govAgencyCode;
	private String domainCode;
	private int year;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(int groupBy) {
		this.groupBy = groupBy;
	}
	
}
