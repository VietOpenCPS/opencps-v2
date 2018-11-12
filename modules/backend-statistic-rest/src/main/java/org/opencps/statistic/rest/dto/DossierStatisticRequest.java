package org.opencps.statistic.rest.dto;

public class DossierStatisticRequest extends CommonRequest {
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getGovAgencyCode() {
		return govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}

	public String getGroupAgencyCode() {
		return groupAgencyCode;
	}

	public void setGroupAgencyCode(String groupAgencyCode) {
		this.groupAgencyCode = groupAgencyCode;
	}

	public boolean isReporting() {
		return reporting;
	}

	public void setReporting(boolean reporting) {
		this.reporting = reporting;
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

	public boolean isCalculate() {
		return calculate;
	}

	public void setCalculate(boolean calculate) {
		this.calculate = calculate;
	}

	private String domain;
	private String govAgencyCode;
	private String groupAgencyCode;
	private boolean reporting;
	private int month;
	private int year;
	private String fromStatisticDate;
	private String toStatisticDate;
	private boolean calculate;
}
