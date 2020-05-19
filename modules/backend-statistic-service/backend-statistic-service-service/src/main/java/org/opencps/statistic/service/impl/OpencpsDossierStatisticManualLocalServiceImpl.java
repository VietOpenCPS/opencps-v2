/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.statistic.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;
import java.util.List;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticManualException;
import org.opencps.statistic.model.OpencpsDossierStatisticManual;
import org.opencps.statistic.service.base.OpencpsDossierStatisticManualLocalServiceBaseImpl;

/**
 * The implementation of the opencps dossier statistic manual local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.statistic.service.OpencpsDossierStatisticManualLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticManualLocalServiceBaseImpl
 * @see org.opencps.statistic.service.OpencpsDossierStatisticManualLocalServiceUtil
 */
public class OpencpsDossierStatisticManualLocalServiceImpl
	extends OpencpsDossierStatisticManualLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.statistic.service.OpencpsDossierStatisticManualLocalServiceUtil} to access the opencps dossier statistic manual local service.
	 */

	public OpencpsDossierStatisticManual updateStatisticManual(long dossierStatisticId, long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, boolean reporting, int onegateCount, int outsideCount,
			int insideCount, int fromViaPostalCount) throws PortalException, SystemException {
		OpencpsDossierStatisticManual dossierStatistic = null;
		Date now = new Date();
		if (dossierStatisticId == 0L) {
			dossierStatisticId = counterLocalService.increment(OpencpsDossierStatisticManual.class.getName());
			dossierStatistic = opencpsDossierStatisticManualPersistence.create(dossierStatisticId);
			dossierStatistic.setCreateDate(now);
			dossierStatistic.setModifiedDate(now);
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setUserId(userId);
			dossierStatistic.setUserName(userName);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setReleaseCount(releaseCount);
			dossierStatistic.setBetimesCount(betimesCount);
			dossierStatistic.setOntimeCount(ontimeCount);
			dossierStatistic.setOvertimeCount(overtimeCount);
			dossierStatistic.setDoneCount(doneCount);
			dossierStatistic.setReleasingCount(releasingCount);
			dossierStatistic.setUnresolvedCount(unresolvedCount);
			dossierStatistic.setProcessingCount(processingCount);
			dossierStatistic.setUndueCount(undueCount);
			dossierStatistic.setOverdueCount(overdueCount);
			dossierStatistic.setPausingCount(pausingCount);
			dossierStatistic.setOntimePercentage(ontimePercentage);
			dossierStatistic.setOvertimeInside(overtimeInside);
			dossierStatistic.setOvertimeOutside(overtimeOutside);
			dossierStatistic.setInteroperatingCount(interoperatingCount);
			dossierStatistic.setWaitingCount(waitingCount);
			dossierStatistic.setGovAgencyCode(govAgencyCode);
			dossierStatistic.setGovAgencyName(govAgencyName);
			dossierStatistic.setDomainCode(domainCode);
			dossierStatistic.setDomainName(domainName);
			dossierStatistic.setReporting(reporting);
			dossierStatistic.setOnegateCount(onegateCount);
			dossierStatistic.setOutsideCount(outsideCount);
			dossierStatistic.setInsideCount(insideCount);
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
		} else {
			dossierStatistic = opencpsDossierStatisticManualPersistence.findByPrimaryKey(dossierStatisticId);
			dossierStatistic.setModifiedDate(now);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setReleaseCount(releaseCount);
			dossierStatistic.setBetimesCount(betimesCount);
			dossierStatistic.setOntimeCount(ontimeCount);
			dossierStatistic.setOvertimeCount(overtimeCount);
			dossierStatistic.setDoneCount(doneCount);
			dossierStatistic.setReleasingCount(releasingCount);
			dossierStatistic.setUnresolvedCount(unresolvedCount);
			dossierStatistic.setProcessingCount(processingCount);
			dossierStatistic.setUndueCount(undueCount);
			dossierStatistic.setOverdueCount(overdueCount);
			dossierStatistic.setPausingCount(pausingCount);
			dossierStatistic.setOntimePercentage(ontimePercentage);
			dossierStatistic.setOvertimeInside(overtimeInside);
			dossierStatistic.setOvertimeOutside(overtimeOutside);
			dossierStatistic.setInteroperatingCount(interoperatingCount);
			dossierStatistic.setWaitingCount(waitingCount);
			dossierStatistic.setOnegateCount(onegateCount);
			dossierStatistic.setOutsideCount(outsideCount);
			dossierStatistic.setInsideCount(insideCount);
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			
			dossierStatistic.setGovAgencyCode(govAgencyCode);
			dossierStatistic.setGovAgencyName(govAgencyName);
			dossierStatistic.setDomainCode(domainCode);
			dossierStatistic.setDomainName(domainName);

		}

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticManualPersistence.update(dossierStatistic);
		return dossierStatistic;
	}

	public OpencpsDossierStatisticManual updateStatisticManual(long dossierStatisticId, long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, int viaPostalCount,
			int saturdayCount, int dossierOnline3Count, int dossierOnline4Count, int receiveDossierSatCount, int releaseDossierSatCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, boolean reporting, int onegateCount, int outsideCount,
			int insideCount, int fromViaPostalCount) throws PortalException, SystemException {
		OpencpsDossierStatisticManual dossierStatistic = null;
		Date now = new Date();
		if (dossierStatisticId == 0L) {
			dossierStatisticId = counterLocalService.increment(OpencpsDossierStatisticManual.class.getName());
			dossierStatistic = opencpsDossierStatisticManualPersistence.create(dossierStatisticId);
			dossierStatistic.setCreateDate(now);
			dossierStatistic.setModifiedDate(now);
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setUserId(userId);
			dossierStatistic.setUserName(userName);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setReleaseCount(releaseCount);
			dossierStatistic.setBetimesCount(betimesCount);
			dossierStatistic.setOntimeCount(ontimeCount);
			dossierStatistic.setOvertimeCount(overtimeCount);
			dossierStatistic.setDoneCount(doneCount);
			dossierStatistic.setReleasingCount(releasingCount);
			dossierStatistic.setUnresolvedCount(unresolvedCount);
			dossierStatistic.setProcessingCount(processingCount);
			dossierStatistic.setUndueCount(undueCount);
			dossierStatistic.setOverdueCount(overdueCount);
			dossierStatistic.setPausingCount(pausingCount);
			dossierStatistic.setOntimePercentage(ontimePercentage);
			dossierStatistic.setOvertimeInside(overtimeInside);
			dossierStatistic.setOvertimeOutside(overtimeOutside);
			dossierStatistic.setInteroperatingCount(interoperatingCount);
			dossierStatistic.setWaitingCount(waitingCount);
			dossierStatistic.setGovAgencyCode(govAgencyCode);
			dossierStatistic.setGovAgencyName(govAgencyName);
			dossierStatistic.setDomainCode(domainCode);
			dossierStatistic.setDomainName(domainName);
			dossierStatistic.setReporting(reporting);
			dossierStatistic.setOnegateCount(onegateCount);
			dossierStatistic.setOutsideCount(outsideCount);
			dossierStatistic.setInsideCount(insideCount);
			dossierStatistic.setViaPostalCount(viaPostalCount);
			dossierStatistic.setSaturdayCount(saturdayCount);
			dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
			dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
			dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
			dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			dossierStatistic.setSystem("1");
		} else {
			dossierStatistic = opencpsDossierStatisticManualPersistence.findByPrimaryKey(dossierStatisticId);
			dossierStatistic.setModifiedDate(now);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setReleaseCount(releaseCount);
			dossierStatistic.setBetimesCount(betimesCount);
			dossierStatistic.setOntimeCount(ontimeCount);
			dossierStatistic.setOvertimeCount(overtimeCount);
			dossierStatistic.setDoneCount(doneCount);
			dossierStatistic.setReleasingCount(releasingCount);
			dossierStatistic.setUnresolvedCount(unresolvedCount);
			dossierStatistic.setProcessingCount(processingCount);
			dossierStatistic.setUndueCount(undueCount);
			dossierStatistic.setOverdueCount(overdueCount);
			dossierStatistic.setPausingCount(pausingCount);
			dossierStatistic.setOntimePercentage(ontimePercentage);
			dossierStatistic.setOvertimeInside(overtimeInside);
			dossierStatistic.setOvertimeOutside(overtimeOutside);
			dossierStatistic.setInteroperatingCount(interoperatingCount);
			dossierStatistic.setWaitingCount(waitingCount);
			dossierStatistic.setOnegateCount(onegateCount);
			dossierStatistic.setOutsideCount(outsideCount);
			dossierStatistic.setInsideCount(insideCount);
			
			dossierStatistic.setGovAgencyCode(govAgencyCode);
			dossierStatistic.setGovAgencyName(govAgencyName);
			dossierStatistic.setDomainCode(domainCode);
			dossierStatistic.setDomainName(domainName);
			dossierStatistic.setViaPostalCount(viaPostalCount);
			dossierStatistic.setSaturdayCount(saturdayCount);
			dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
			dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
			dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
			dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);	
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			dossierStatistic.setSystem("1");
		}

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticManualPersistence.update(dossierStatistic);
		return dossierStatistic;
	}
	
	public OpencpsDossierStatisticManual getByGovMonthYear(long groupId, String govAgencyCode, int month, int year)
			throws PortalException, SystemException {
		return opencpsDossierStatisticManualPersistence.fetchByM_Y_G(groupId, govAgencyCode, month, year);
	}

	public OpencpsDossierStatisticManual checkNotDuplicate(long groupId, String govAgencyCode, int month, int year,
			String domainCode) {
		return opencpsDossierStatisticManualPersistence.fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
				domainCode);
	}

	public OpencpsDossierStatisticManual getByGovMonthYearDomain(long groupId, String govAgencyCode, int month, int year,
			String domainCode, boolean reporting) {
		return opencpsDossierStatisticManualPersistence.fetchByM_Y_DM_G(groupId, govAgencyCode, month, year, domainCode,
				reporting);
	}
	
	public void removeDossierStatisticManualByD_M_Y(long groupId, String domainCode, int month, int year) throws NoSuchOpencpsDossierStatisticManualException {
		opencpsDossierStatisticManualPersistence.removeByG_D_M_Y(groupId, domainCode, month, year);
	}
	
	public void removeDossierStatisticManualByMonthYear(long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticManualException {
		opencpsDossierStatisticPersistence.removeByG_M_Y(groupId, month, year);
	}

	public void removeDossierStatisticManualByYear(long companyId, long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticManualException {
		opencpsDossierStatisticManualPersistence.removeByCID_GID_Y(companyId, groupId, month, year);
	}

	public List<OpencpsDossierStatisticManual> getDossierStatisticManualByMonthYear(long groupId, int month, int year) {
		return opencpsDossierStatisticManualPersistence.findByG_M_Y(groupId, month, year);
	}

	public List<OpencpsDossierStatisticManual> getDossierStatisticManualByMonthYearAndReport(long groupId, int month, int year, boolean reporting) {
		return opencpsDossierStatisticManualPersistence.findByGID_M_Y_RP(groupId, month, year, reporting);
	}

	public OpencpsDossierStatisticManual removeByG_M_Y_G_D(long groupId, int month, int year, String govAgencyCode,
			String domainCode) throws NoSuchOpencpsDossierStatisticManualException {
		return opencpsDossierStatisticManualPersistence.removeByG_M_Y_G_D(groupId, month, year, govAgencyCode, domainCode);
	}

	public List<OpencpsDossierStatisticManual> getDossierStatisticManualByYear(long companyId, long groupId, int month, int year) {
		return opencpsDossierStatisticManualPersistence.findByCID_GID_Y(companyId, groupId, month, year);
	}

	public List<OpencpsDossierStatisticManual> getDossierStatisticByMonthsYearAndReport(long groupId, int[] month, int year, boolean reporting) {
		return opencpsDossierStatisticManualPersistence.findByGID_MS_Y_RP(groupId, month, year, reporting);
	}
	
	public List<OpencpsDossierStatisticManual> findByG(long groupId) {
		return opencpsDossierStatisticManualPersistence.findByG(groupId);
	}
	
	public OpencpsDossierStatisticManual fetchByG_M_Y_G_D(long groupId, int month, int year, String govAgencyCode, String domainCode) {
		return opencpsDossierStatisticManualPersistence.fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode, domainCode);
	}
	
	public List<OpencpsDossierStatisticManual> fetchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {
		return opencpsDossierStatisticManualFinder.searchDossierStatistic(groupId, year, domain, govAgencyCode,
				groupAgenvyCode, start, end);
	}

	public List<OpencpsDossierStatisticManual> fetchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String system, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {
		if (month == 0 && year == -1) {
			//_log.info("START month all and year all: ");
			return opencpsDossierStatisticManualFinder.searchYearDossierStatistic(groupId, month, domain, govAgencyCode, system,
					groupAgenvyCode, start, end);
		} else {
			return opencpsDossierStatisticManualFinder.searchDossierStatistic(groupId, year, domain, govAgencyCode, system,
					groupAgenvyCode, start, end);
		}
		
	}
	
	public List<OpencpsDossierStatisticManual> searchDossierStatisticSystem(long groupId, int month, int year, String domain,
			String govAgencyCode, String system, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {

		return opencpsDossierStatisticManualFinder.searchByDomainAgencySystem(groupId, month, year, domain,
				govAgencyCode, system, groupAgenvyCode, start, end);
	}

	public void removeAll() {
		opencpsDossierStatisticManualPersistence.removeAll();
	}
	
	private Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticManualLocalServiceImpl.class);	
}