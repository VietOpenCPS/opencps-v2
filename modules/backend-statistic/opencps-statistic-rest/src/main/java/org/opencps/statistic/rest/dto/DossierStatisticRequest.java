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

	private String domain;
	private String govAgencyCode;
	private String groupAgencyCode;
	private boolean reporting;

}
