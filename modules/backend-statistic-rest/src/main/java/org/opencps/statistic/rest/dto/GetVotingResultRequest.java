package org.opencps.statistic.rest.dto;

public class GetVotingResultRequest extends CommonRequest{

	private String month;
	private String year;
	private boolean calculate;
	private String fromVotingDate;
	private String toVotingDate;
	private String votingCode;
	private String domain;
	private String govAgencyCode;
	private String className;

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public boolean isCalculate() {
		return calculate;
	}
	public void setCalculate(boolean calculate) {
		this.calculate = calculate;
	}
	public String getFromVotingDate() {
		return fromVotingDate;
	}
	public void setFromVotingDate(String fromVotingDate) {
		this.fromVotingDate = fromVotingDate;
	}
	public String getToVotingDate() {
		return toVotingDate;
	}
	public void setToVotingDate(String toVotingDate) {
		this.toVotingDate = toVotingDate;
	}
	public String getVotingCode() {
		return votingCode;
	}
	public void setVotingCode(String votingCode) {
		this.votingCode = votingCode;
	}
	public String getGovAgencyCode() {
		return govAgencyCode;
	}
	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

}
