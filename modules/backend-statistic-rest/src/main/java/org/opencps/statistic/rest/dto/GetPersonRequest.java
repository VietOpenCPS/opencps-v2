package org.opencps.statistic.rest.dto;

public class GetPersonRequest extends CommonRequest{

	private String month;
	private String year;
	private boolean calculate;
	private String fromStatisticDate;
	private String toStatisticDate;
	private String votingCode;
	private String employeeId;
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
	public String getFromStatisticDate() {
		return fromStatisticDate;
	}
	public void setFromStatisticDate(String fromStatisticDate) {
		this.fromStatisticDate = fromStatisticDate;
	}
	public String getToStatisticDate() {
		return toStatisticDate;
	}
	public void setToStatisticDate(String toStatisticDate) {
		this.toStatisticDate = toStatisticDate;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}	
}
