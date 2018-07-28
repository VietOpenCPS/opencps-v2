package org.opencps.statistic.rest.dto;

public class DossierStatisticRequest {
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

	private int end;
	private String domain;
	private String govAgencyCode;
	private String groupAgencyCode;
	private boolean reporting;
	private long groupId;
	private int start;

}
