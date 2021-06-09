package org.opencps.backend.statisticmgt.dto;

public class DossierStatisticMgtData {
	
	private long groupId;
	private int betimesCount = 0;
	private int doneCount = 0;
	private String govAgencyCode;
	private String govAgencyName;
	private String domainCode;
	private String domainName;
	private String serviceCode;
	private String serviceName;
	private int onegateCount = 0;
	private int onlineCount = 0;
	private int ontimeCount = 0;
	private int overdueCount = 0;
	private int overtimeCount = 0;
	private int processingCount = 0;
	private int receivedCount = 0;
	private int releaseCount = 0;
	private int releasingCount = 0;
	private int remainingCount = 0;
	private int totalCount = 0;
	private int undueCount = 0;
	private int month = 0;
	private int year = 0;
	private int waitingCount = 0;
	private int processCount = 0;
	private int ontimePercentage = 0;
	private int groupBy;
	public int getBetimesCount() {
		return betimesCount;
	}
	public void setBetimesCount(int betimesCount) {
		this.betimesCount = betimesCount;
	}
	public int getDoneCount() {
		return doneCount;
	}
	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
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
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getOnegateCount() {
		return onegateCount;
	}
	public void setOnegateCount(int onegateCount) {
		this.onegateCount = onegateCount;
	}
	public int getOnlineCount() {
		return onlineCount;
	}
	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}
	public int getOntimeCount() {
		return ontimeCount;
	}
	public void setOntimeCount(int ontimeCount) {
		this.ontimeCount = ontimeCount;
	}
	public int getOverdueCount() {
		return overdueCount;
	}
	public void setOverdueCount(int overdueCount) {
		this.overdueCount = overdueCount;
	}
	public int getOvertimeCount() {
		return overtimeCount;
	}
	public void setOvertimeCount(int overtimeCount) {
		this.overtimeCount = overtimeCount;
	}
	public int getProcessingCount() {
		return processingCount;
	}
	public void setProcessingCount(int processingCount) {
		this.processingCount = processingCount;
	}
	public int getReceivedCount() {
		return receivedCount;
	}
	public void setReceivedCount(int receivedCount) {
		this.receivedCount = receivedCount;
	}
	public int getReleaseCount() {
		return releaseCount;
	}
	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}
	public int getReleasingCount() {
		return releasingCount;
	}
	public void setReleasingCount(int releasingCount) {
		this.releasingCount = releasingCount;
	}
	public int getRemainingCount() {
		return remainingCount;
	}
	public void setRemainingCount(int remainingCount) {
		this.remainingCount = remainingCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getUndueCount() {
		return undueCount;
	}
	public void setUndueCount(int undueCount) {
		this.undueCount = undueCount;
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
	public int getWaitingCount() {
		return waitingCount;
	}
	public void setWaitingCount(int waitingCount) {
		this.waitingCount = waitingCount;
	}
	public int getProcessCount() {
		return processCount;
	}
	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}
	public int getOntimePercentage() {
		return ontimePercentage;
	}
	public void setOntimePercentage(int ontimePercentage) {
		this.ontimePercentage = ontimePercentage;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public int getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(int groupBy) {
		this.groupBy = groupBy;
	}	
	
	
}
