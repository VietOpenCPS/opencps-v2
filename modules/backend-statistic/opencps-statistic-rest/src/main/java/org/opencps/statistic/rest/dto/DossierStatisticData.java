package org.opencps.statistic.rest.dto;

public class DossierStatisticData {
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getDeniedCount() {
		return deniedCount;
	}
	public void setDeniedCount(int deniedCount) {
		this.deniedCount = deniedCount;
	}
	public int getCancelledCount() {
		return cancelledCount;
	}
	public void setCancelledCount(int cancelledCount) {
		this.cancelledCount = cancelledCount;
	}
	public int getProcessCount() {
		return processCount;
	}
	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}
	public int getRemainingCount() {
		return remainingCount;
	}
	public void setRemainingCount(int remainingCount) {
		this.remainingCount = remainingCount;
	}
	public int getReceivedCount() {
		return receivedCount;
	}
	public void setReceivedCount(int receivedCount) {
		this.receivedCount = receivedCount;
	}
	public int getOnlineCount() {
		return onlineCount;
	}
	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}
	public int getReleaseCount() {
		return releaseCount;
	}
	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}
	public int getBetimesCount() {
		return betimesCount;
	}
	public void setBetimesCount(int betimesCount) {
		this.betimesCount = betimesCount;
	}
	public int getOntimeCount() {
		return ontimeCount;
	}
	public void setOntimeCount(int ontimeCount) {
		this.ontimeCount = ontimeCount;
	}
	public int getOvertimeCount() {
		return overtimeCount;
	}
	public void setOvertimeCount(int overtimeCount) {
		this.overtimeCount = overtimeCount;
	}
	public int getOvertimeInside() {
		return overtimeInside;
	}
	public void setOvertimeInside(int overtimeInside) {
		this.overtimeInside = overtimeInside;
	}
	public int getOvertimeOutside() {
		return overtimeOutside;
	}
	public void setOvertimeOutside(int overtimeOutside) {
		this.overtimeOutside = overtimeOutside;
	}
	public int getDoneCount() {
		return doneCount;
	}
	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}
	public int getReleasingCount() {
		return releasingCount;
	}
	public void setReleasingCount(int releasingCount) {
		this.releasingCount = releasingCount;
	}
	public int getUnresolvedCount() {
		return unresolvedCount;
	}
	public void setUnresolvedCount(int unresolvedCount) {
		this.unresolvedCount = unresolvedCount;
	}
	public int getProcessingCount() {
		return processingCount;
	}
	public void setProcessingCount(int processingCount) {
		this.processingCount = processingCount;
	}
	public int getUndueCount() {
		return undueCount;
	}
	public void setUndueCount(int undueCount) {
		this.undueCount = undueCount;
	}
	public int getOverdueCount() {
		return overdueCount;
	}
	public void setOverdueCount(int overdueCount) {
		this.overdueCount = overdueCount;
	}
	public int getInteroperatingCount() {
		return interoperatingCount;
	}
	public void setInteroperatingCount(int interoperatingCount) {
		this.interoperatingCount = interoperatingCount;
	}
	public int getWaitingCount() {
		return waitingCount;
	}
	public void setWaitingCount(int waitingCount) {
		this.waitingCount = waitingCount;
	}
	public int getOntimePercentage() {
		return ontimePercentage;
	}
	public void setOntimePercentage(int ontimePercentage) {
		this.ontimePercentage = ontimePercentage;
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
	public boolean isReporting() {
		return reporting;
	}
	public void setReporting(boolean reporting) {
		this.reporting = reporting;
	}
	private int month;
	  private int year;
	  private int totalCount;
	  private int deniedCount;
	  private int cancelledCount;
	  private int processCount;
	  private int remainingCount;
	  private int receivedCount;
	  private int onlineCount;
	  private int releaseCount;
	  private int betimesCount;
	  private int ontimeCount;
	  private int overtimeCount;
	  private int overtimeInside;
	  private int overtimeOutside;
	  private int doneCount;
	  private int releasingCount;
	  private int unresolvedCount;
	  private int processingCount;
	  private int undueCount;
	  private int overdueCount;
	  private int interoperatingCount;
	  private int waitingCount;
	  private int ontimePercentage;
	  private String govAgencyCode;
	  private String govAgencyName; 
	  private String domainCode;
	  private String domainName;
	  private boolean reporting;
}
