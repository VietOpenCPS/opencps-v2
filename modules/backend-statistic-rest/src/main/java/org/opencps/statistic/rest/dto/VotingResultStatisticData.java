package org.opencps.statistic.rest.dto;

public class VotingResultStatisticData {

	private int month = 0;
	private int year = 0;
	private long companyId;
	private long groupId;
	//private int totalCount = 0;
	private int totalVoted = 0;
	private int veryGoodCount = 0;
	private int goodCount = 0;
	private int badCount = 0;
	private int percentVeryGood = 0;
	private int percentGood = 0;
	private int percentBad = 0;
	private String govAgencyCode;
	private String govAgencyName;
	private String domain;
	private String domainName;
	private String votingCode;
	private String votingSubject;

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
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
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
	public String getVotingCode() {
		return votingCode;
	}
	public void setVotingCode(String votingCode) {
		this.votingCode = votingCode;
	}
	public int getTotalVoted() {
		return totalVoted;
	}
	public void setTotalVoted(int totalVoted) {
		this.totalVoted = totalVoted;
	}
	public int getPercentVeryGood() {
		return percentVeryGood;
	}
	public void setPercentVeryGood(int percentVeryGood) {
		this.percentVeryGood = percentVeryGood;
	}
	public int getPercentGood() {
		return percentGood;
	}
	public void setPercentGood(int percentGood) {
		this.percentGood = percentGood;
	}
	public int getPercentBad() {
		return percentBad;
	}
	public void setPercentBad(int percentBad) {
		this.percentBad = percentBad;
	}
	public int getVeryGoodCount() {
		return veryGoodCount;
	}
	public void setVeryGoodCount(int veryGoodCount) {
		this.veryGoodCount = veryGoodCount;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public int getBadCount() {
		return badCount;
	}
	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getVotingSubject() {
		return votingSubject;
	}
	public void setVotingSubject(String votingSubject) {
		this.votingSubject = votingSubject;
	}

}
