package org.opencps.statistic.rest.dto;


public class DossierStatisticKey {
	
	private String govAgencyCode;
	private String domainCode;
	private String system;
	private String groupAgencyCode;
	private int month;
	private int year;
	private int reporting;

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
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getGroupAgencyCode() {
		return groupAgencyCode;
	}
	public void setGroupAgencyCode(String groupAgencyCode) {
		this.groupAgencyCode = groupAgencyCode;
	}
	public int getReporting() {
		return reporting;
	}
	public void setReporting(int reporting) {
		this.reporting = reporting;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	

}
