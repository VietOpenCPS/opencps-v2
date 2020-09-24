package org.opencps.statistic.rest.dto;

public class RealtimeData {
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
	
	public int getOnegateCount() {
		return onegateCount;
	}

	public void setOnegateCount(int onegateCount) {
		this.onegateCount = onegateCount;
	}

	public int getOutsideCount() {
		return outsideCount;
	}

	public void setOutsideCount(int outsideCount) {
		this.outsideCount = outsideCount;
	}

	public int getInsideCount() {
		return insideCount;
	}

	public void setInsideCount(int insideCount) {
		this.insideCount = insideCount;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	private int totalCount = 0;
	private int deniedCount = 0;
	private int cancelledCount = 0;
	private int processCount = 0;
	private int remainingCount = 0;
	private int receivedCount = 0;
	private int onlineCount = 0;

	private int onegateCount = 0;
	private int outsideCount = 0;
	private int insideCount = 0;
	private int releaseCount = 0;
	private int betimesCount = 0;
	private int ontimeCount = 0;
	private int overtimeCount = 0;
	private int overtimeInside = 0;
	private int overtimeOutside = 0;
	private int doneCount = 0;
	private int releasingCount = 0;
	private int unresolvedCount = 0;
	private int processingCount = 0;
	private int undueCount = 0;
	private int overdueCount = 0;
	private int interoperatingCount = 0;
	private int waitingCount = 0;
	private int ontimePercentage = 0;
	private String govAgencyCode;
	private String govAgencyName;
	private String domainCode;
	private String domainName;
	private String system;
	private int viaPostalCount;
	private int saturdayCount;
	private int dossierOnline3Count;
	private int dossierOnline4Count;
	private int receiveDossierSatCount;
	private int releaseDossierSatCount;
	private String groupAgencyCode;
	private int fromViaPostalCount;
	private String serviceCode;
	private int doneOnegateCount;
	public int getDoneOnegateCount() {
		return doneOnegateCount;
	}

	public void setDoneOnegateCount(int doneOnegateCount) {
		this.doneOnegateCount = doneOnegateCount;
	}

	public int getDoneViaPostalCount() {
		return doneViaPostalCount;
	}

	public void setDoneViaPostalCount(int doneViaPostalCount) {
		this.doneViaPostalCount = doneViaPostalCount;
	}

	private int doneViaPostalCount;
	
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

	private String serviceName;
	
	public int getFromViaPostalCount() {
		return fromViaPostalCount;
	}

	public void setFromViaPostalCount(int fromViaPostalCount) {
		this.fromViaPostalCount = fromViaPostalCount;
	}

	public String getGroupAgencyCode() {
		return groupAgencyCode;
	}

	public void setGroupAgencyCode(String groupAgencyCode) {
		this.groupAgencyCode = groupAgencyCode;
	}

	public int getDossierOnline3Count() {
		return dossierOnline3Count;
	}

	public void setDossierOnline3Count(int dossierOnline3Count) {
		this.dossierOnline3Count = dossierOnline3Count;
	}

	public int getDossierOnline4Count() {
		return dossierOnline4Count;
	}

	public void setDossierOnline4Count(int dossierOnline4Count) {
		this.dossierOnline4Count = dossierOnline4Count;
	}

	public int getReceiveDossierSatCount() {
		return receiveDossierSatCount;
	}

	public void setReceiveDossierSatCount(int receiveDossierSatCount) {
		this.receiveDossierSatCount = receiveDossierSatCount;
	}

	public int getReleaseDossierSatCount() {
		return releaseDossierSatCount;
	}

	public void setReleaseDossierSatCount(int releaseDossierSatCount) {
		this.releaseDossierSatCount = releaseDossierSatCount;
	}

	public int getViaPostalCount() {
		return viaPostalCount;
	}

	public void setViaPostalCount(int viaPostalCount) {
		this.viaPostalCount = viaPostalCount;
	}

	public int getSaturdayCount() {
		return saturdayCount;
	}

	public void setSaturdayCount(int saturdayCount) {
		this.saturdayCount = saturdayCount;
	}
	
	private int dossierOnegate3Count;
	private int dossierOnegate4Count;
	private int releaseDossierOnline3Count;
	private int releaseDossierOnline4Count;
	private int releaseDossierOnegate3Count;
	private int releaseDossierOnegate4Count;
	
	public int getDossierOnegate3Count() {
		return dossierOnegate3Count;
	}

	public void setDossierOnegate3Count(int dossierOnegate3Count) {
		this.dossierOnegate3Count = dossierOnegate3Count;
	}

	public int getDossierOnegate4Count() {
		return dossierOnegate4Count;
	}

	public void setDossierOnegate4Count(int dossierOnegate4Count) {
		this.dossierOnegate4Count = dossierOnegate4Count;
	}

	public int getReleaseDossierOnline3Count() {
		return releaseDossierOnline3Count;
	}

	public void setReleaseDossierOnline3Count(int releaseDossierOnline3Count) {
		this.releaseDossierOnline3Count = releaseDossierOnline3Count;
	}

	public int getReleaseDossierOnline4Count() {
		return releaseDossierOnline4Count;
	}

	public void setReleaseDossierOnline4Count(int releaseDossierOnline4Count) {
		this.releaseDossierOnline4Count = releaseDossierOnline4Count;
	}

	public int getReleaseDossierOnegate3Count() {
		return releaseDossierOnegate3Count;
	}

	public void setReleaseDossierOnegate3Count(int releaseDossierOnegate3Count) {
		this.releaseDossierOnegate3Count = releaseDossierOnegate3Count;
	}

	public int getReleaseDossierOnegate4Count() {
		return releaseDossierOnegate4Count;
	}

	public void setReleaseDossierOnegate4Count(int releaseDossierOnegate4Count) {
		this.releaseDossierOnegate4Count = releaseDossierOnegate4Count;
	}
		
}
