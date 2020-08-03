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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.statistic.dto.DossierStatisticData;
import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.statistic.service.base.OpencpsDossierStatisticLocalServiceBaseImpl;

/**
 * The implementation of the opencps dossier statistic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.statistic.service.OpencpsDossierStatisticLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor = {
		PortalException.class, SystemException.class
})
public class OpencpsDossierStatisticLocalServiceImpl extends OpencpsDossierStatisticLocalServiceBaseImpl {
	public OpencpsDossierStatistic checkExsit(long groupId, int month, int year, String govAgency, String domain) {
		return opencpsDossierStatisticFinder.checkContains(groupId, month, year, domain, govAgency);
	}

	public OpencpsDossierStatistic checkExsitSystem(long groupId, int month, int year, String govAgency, String domain, String system, String groupGovAgencyCode) {
//		if (lstStatistics == null) {
//			lstStatistics = new ArrayList<OpencpsDossierStatistic>();
//			lstStatistics.addAll(opencpsDossierStatisticPersistence.findAll());
//		}
//		for (OpencpsDossierStatistic statistic : lstStatistics) {
//			boolean checkGroup = false;
//			if (groupId == statistic.getGroupId()) {
//				checkGroup = true;
//			}
//			boolean checkDomain = false;
//			if (Validator.isNull(domain) || domain.contentEquals(statistic.getDomainCode())) {
//				checkDomain = true;
//			}
//			boolean checkGov = false;
//			if (Validator.isNull(govAgency) || govAgency.contentEquals(statistic.getGovAgencyCode())) {
//				checkGov = true;
//			}
//			boolean checkSystem = false;
//			if (Validator.isNull(system) || system.contentEquals(statistic.getSystem())) {
//				checkSystem = true;
//			}
//			boolean checkGroupGov = false;
//			if (Validator.isNull(groupGovAgencyCode) || groupGovAgencyCode.contentEquals(statistic.getGroupAgencyCode())) {
//				checkGroupGov = true;
//			}
//			boolean checkMonth = false;
//			if (month == statistic.getMonth()) {
//				checkMonth = true;
//			}
//			boolean checkYear = false;
//			if (year == statistic.getYear()) {
//				checkYear = true;
//			}
//			if (checkDomain && checkGov && checkGroup && checkGroupGov && checkMonth && checkYear && checkSystem) return statistic;			
//		}
//		return null;
		return opencpsDossierStatisticFinder.checkContainsSystem(groupId, month, year, domain, govAgency, system, groupGovAgencyCode);
	}

	public OpencpsDossierStatistic updateStatistic(long dossierStatisticId, long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int fromViaPostalCount) throws PortalException, SystemException {
		OpencpsDossierStatistic dossierStatistic = null;
		Date now = new Date();
		if (dossierStatisticId == 0L) {
			dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
			dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
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
			dossierStatistic = opencpsDossierStatisticPersistence.findByPrimaryKey(dossierStatisticId);
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
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
		}

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}

	public OpencpsDossierStatistic updateStatistic(long dossierStatisticId, long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, int viaPostalCount,
			int saturdayCount, int dossierOnline3Count, int dossierOnline4Count, int receiveDossierSatCount, int releaseDossierSatCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int fromViaPostalCount) throws PortalException, SystemException {
		OpencpsDossierStatistic dossierStatistic = null;
		Date now = new Date();
		if (dossierStatisticId == 0L) {
			dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
			dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
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
		} else {
			dossierStatistic = opencpsDossierStatisticPersistence.findByPrimaryKey(dossierStatisticId);
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
		}

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}
	
	public OpencpsDossierStatistic getByGovMonthYear(long groupId, String govAgencyCode, int month, int year)
			throws PortalException, SystemException {
		return opencpsDossierStatisticPersistence.fetchByM_Y_G(groupId, govAgencyCode, month, year);
	}

	public OpencpsDossierStatistic checkNotDuplicate(long groupId, String govAgencyCode, int month, int year,
			String domainCode) {
		return opencpsDossierStatisticPersistence.fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
				domainCode);
	}

	public OpencpsDossierStatistic getByGovMonthYearDomain(long groupId, String govAgencyCode, int month, int year,
			String domainCode, int reporting) {
		return opencpsDossierStatisticPersistence.fetchByM_Y_DM_G(groupId, govAgencyCode, month, year, domainCode,
				reporting);
	}

	public List<OpencpsDossierStatistic> fetchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {
		return opencpsDossierStatisticFinder.searchDossierStatistic(groupId, year, domain, govAgencyCode,
				groupAgenvyCode, start, end);
	}

	public List<OpencpsDossierStatistic> fetchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String system, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {
		if (month == 0 && year == -1) {
			//_log.info("START month all and year all: ");
			return opencpsDossierStatisticFinder.searchYearDossierStatistic(groupId, month, domain, govAgencyCode, system,
					groupAgenvyCode, start, end);
		} else {
			return opencpsDossierStatisticFinder.searchDossierStatistic(groupId, year, domain, govAgencyCode, system,
					groupAgenvyCode, start, end);
		}
		
	}

//	public List<OpencpsDossierStatistic> searchDossierStatistic(long groupId, int month, int year, String domain,
//			String govAgencyCode, String groupAgenvyCode, int start, int end)
//			throws PortalException, SystemException {
//
//		return opencpsDossierStatisticFinder.searchByDomainGovAgencyGroup(groupId, month, year, domain,
//				govAgencyCode, groupAgenvyCode, start, end);
//	}

	public List<OpencpsDossierStatistic> searchDossierStatisticSystem(long groupId, int month, int year, String domain,
			String govAgencyCode, String system, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {

		return opencpsDossierStatisticFinder.searchByDomainAgencySystem(groupId, month, year, domain,
				govAgencyCode, system, groupAgenvyCode, start, end);
	}
	
	public void removeDossierStatisticByD_M_Y(long groupId, String domainCode, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		opencpsDossierStatisticPersistence.removeByG_D_M_Y(groupId, domainCode, month, year);
	}
	
	public void removeDossierStatisticByMonthYear(long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		opencpsDossierStatisticPersistence.removeByG_M_Y(groupId, month, year);
	}

	public void removeDossierStatisticByYear(long companyId, long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		opencpsDossierStatisticPersistence.removeByCID_GID_Y(companyId, groupId, month, year);
	}

	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYear(long groupId, int month, int year) {
		return opencpsDossierStatisticPersistence.findByG_M_Y(groupId, month, year);
	}

	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYearAndReport(long groupId, int month, int year, int reporting) {
		return opencpsDossierStatisticPersistence.findByGID_M_Y_RP(groupId, month, year, reporting);
	}

	public OpencpsDossierStatistic removeByG_M_Y_G_D(long groupId, int month, int year, String govAgencyCode,
			String domainCode) throws NoSuchOpencpsDossierStatisticException {
		return opencpsDossierStatisticPersistence.removeByG_M_Y_G_D(groupId, month, year, govAgencyCode, domainCode);
	}

	public List<OpencpsDossierStatistic> getDossierStatisticByYear(long companyId, long groupId, int month, int year) {
		return opencpsDossierStatisticPersistence.findByCID_GID_Y(companyId, groupId, month, year);
	}

	public List<OpencpsDossierStatistic> getDossierStatisticByMonthsYearAndReport(long groupId, int[] month, int year, int reporting) {
		return opencpsDossierStatisticPersistence.findByGID_MS_Y_RP(groupId, month, year, reporting);
	}

	public OpencpsDossierStatistic createOrUpdateStatistic(long companyId, long groupId, long userId,
			String userName, int month, int year, String system, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int fromViaPostalCount) throws PortalException, SystemException {
		OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsitSystem(groupId,
				month, year, govAgencyCode, domainCode, system, StringPool.BLANK);

		Date now = new Date();
//		long dossierStatisticId = 0l;
		long dossierStatisticId;
		// _log.debug(dossierStatisticId);
		if (dossierStatistic == null) {
			dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
			dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
			dossierStatistic.setCreateDate(now);
			dossierStatistic.setModifiedDate(now);
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setUserId(userId);
			dossierStatistic.setUserName(userName);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setSystem(system);
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
			if (dossierStatistic.getReporting() == 0) {
				dossierStatistic.setModifiedDate(now);
				dossierStatistic.setMonth(month);
				dossierStatistic.setYear(year);
				dossierStatistic.setSystem(system);
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
				dossierStatistic.setReporting(reporting);
				dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			}
		}

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}

	public OpencpsDossierStatistic createOrUpdateStatistic(long companyId, long groupId, long userId,
			String userName, int month, int year, String system, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, 
			String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int viaPostalCount, int saturdayCount, int dossierOnline3Count, int dossierOnline4Count, int receiveDossierSatCount, int releaseDossierSatCount,
			int fromViaPostalCount) throws PortalException, SystemException {
		OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsitSystem(groupId,
				month, year, govAgencyCode, domainCode, system, StringPool.BLANK);

		Date now = new Date();
//		long dossierStatisticId = 0l;
		long dossierStatisticId;
		// _log.debug(dossierStatisticId);
		if (dossierStatistic == null) {
			dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
			dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
			dossierStatistic.setCreateDate(now);
			dossierStatistic.setModifiedDate(now);
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setUserId(userId);
			dossierStatistic.setUserName(userName);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setSystem(system);
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
		} else {
			if (dossierStatistic.getReporting() == 0) {
				dossierStatistic.setModifiedDate(now);
				dossierStatistic.setMonth(month);
				dossierStatistic.setYear(year);
				dossierStatistic.setSystem(system);
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
				dossierStatistic.setReporting(reporting);
				dossierStatistic.setViaPostalCount(viaPostalCount);
				dossierStatistic.setSaturdayCount(saturdayCount);
				dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
				dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
				dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
				dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);				
				dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			}
		}

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public void updateStatisticData(Map<String, DossierStatisticData> statisticData) throws SystemException, PortalException {
		for (Map.Entry<String, DossierStatisticData> me : statisticData.entrySet()) {
			DossierStatisticData payload = (DossierStatisticData) me.getValue();
			
			if (Validator.isNull(payload.getDomainCode())) {
				payload.setDomainCode((String) null);
			}

			if (Validator.isNull(payload.getGovAgencyCode())) {
				payload.setGovAgencyCode((String) null);
			}
			
			if (payload.getSystem() == null) {
				payload.setSystem((String) null);
			}

			byte pausingCount = 0;

			createOrUpdateStatistic(payload.getCompanyId(), payload.getGroupId(), -1L, "ADM", payload.getMonth(),
					payload.getYear(), payload.getSystem(), payload.getTotalCount(), payload.getDeniedCount(),
					payload.getCancelledCount(), payload.getProcessCount(), payload.getRemainingCount(),
					payload.getReceivedCount(), payload.getOnlineCount(), payload.getReleaseCount(),
					payload.getBetimesCount(), payload.getOntimeCount(), payload.getOvertimeCount(),
					payload.getDoneCount(), payload.getReleasingCount(), payload.getUnresolvedCount(),
					payload.getProcessingCount(), payload.getUndueCount(), payload.getOverdueCount(), pausingCount,
					payload.getOntimePercentage(), payload.getOvertimeInside(), payload.getOvertimeOutside(),
					payload.getInteroperatingCount(), payload.getWaitingCount(), payload.getGovAgencyCode(),
					payload.getGovAgencyName(), payload.getDomainCode(), payload.getDomainName(), payload.getReporting(),
					payload.getOnegateCount(), payload.getOutsideCount(), payload.getInsideCount(), payload.getViaPostalCount(), payload.getSaturdayCount(), payload.getDossierOnline3Count(), payload.getDossierOnline4Count(), payload.getReceiveDossierSatCount(), payload.getReleaseDossierSatCount(),
					payload.getFromViaPostalCount());
		}
	}

	public OpencpsDossierStatistic createOnlyStatistic(long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int fromViaPostalCount) throws PortalException, SystemException {
		Date now = new Date();
		long dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
		OpencpsDossierStatistic dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
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

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}	

	public OpencpsDossierStatistic createOnlyStatistic(long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int viaPostalCount, int saturdayCount, int dossierOnline3Count, int dossierOnline4Count, int receiveDossierSatCount, int releaseDossierSatCount,
			int fromViaPostalCount) throws PortalException, SystemException {
		Date now = new Date();
		long dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
		OpencpsDossierStatistic dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
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

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}	
	
	public OpencpsDossierStatistic updateOnlyStatistic(OpencpsDossierStatistic dossierStatistic, long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int fromViaPostalCount) throws PortalException, SystemException {
		Date now = new Date();
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

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}	

	public OpencpsDossierStatistic updateOnlyStatistic(OpencpsDossierStatistic dossierStatistic, long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, int reporting, int onegateCount, int outsideCount,
			int insideCount, int viaPostalCount, int saturdayCount, int dossierOnline3Count, int dossierOnline4Count, int receiveDossierSatCount, int releaseDossierSatCount,
			int fromViaPostalCount) throws PortalException, SystemException {
		Date now = new Date();
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

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}	
	
	public List<OpencpsDossierStatistic> findByG(long groupId) {
		return opencpsDossierStatisticPersistence.findByG(groupId);
	}
	
	public OpencpsDossierStatistic fetchByG_M_Y_G_D(long groupId, int month, int year, String govAgencyCode, String domainCode) {
		return opencpsDossierStatisticPersistence.fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode, domainCode);
	}
	private static List<OpencpsDossierStatistic> lstStatistics = null;
	
	public OpencpsDossierStatistic createOrUpdateStatistic(long companyId, long groupId, long userId,
			String userName, int month, int year, String system, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, 
			String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, String groupGovAgencyCode, int reporting, int onegateCount, int outsideCount,
			int insideCount, int viaPostalCount, int saturdayCount, int dossierOnline3Count, int dossierOnline4Count, int receiveDossierSatCount,
			int releaseDossierSatCount, int fromViaPostalCount) throws PortalException, SystemException {
		long startTime = System.currentTimeMillis();
		OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsitSystem(groupId,
				month, year, govAgencyCode, domainCode, system, groupGovAgencyCode);
		long endTime = System.currentTimeMillis();
		_log.debug("CHECK DOSSIER STATISTIC: " + (endTime - startTime) / 1000.0);
		Date now = new Date();
//		long dossierStatisticId = 0l;
		long dossierStatisticId;
//		if (Validator.isNotNull(groupGovAgencyCode)) {
//			 _log.info("SAVE GROUP CREATE UPDATE: " + groupGovAgencyCode);
//			 _log.info("Found statistic: " + dossierStatistic);
//		}
		if (dossierStatistic == null) {
			dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
			dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
			dossierStatistic.setCreateDate(now);
			dossierStatistic.setModifiedDate(now);
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setUserId(userId);
			dossierStatistic.setUserName(userName);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setSystem(system);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
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
			dossierStatistic.setGroupAgencyCode(groupGovAgencyCode);
			
//			lstStatistics.add(dossierStatistic);
		} else {
			if (dossierStatistic.getReporting() == 0) {
				dossierStatistic.setModifiedDate(now);
				dossierStatistic.setMonth(month);
				dossierStatistic.setYear(year);
				dossierStatistic.setSystem(system);
				dossierStatistic.setTotalCount(totalCount);
				dossierStatistic.setDeniedCount(deniedCount);
				dossierStatistic.setCancelledCount(cancelledCount);
				dossierStatistic.setProcessCount(processCount);
				dossierStatistic.setRemainingCount(remainingCount);
				dossierStatistic.setReceivedCount(receivedCount);
				dossierStatistic.setOnlineCount(onlineCount);
				dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
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
				dossierStatistic.setReporting(reporting);
				dossierStatistic.setViaPostalCount(viaPostalCount);
				dossierStatistic.setSaturdayCount(saturdayCount);
				dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
				dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
				dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
				dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);		
				dossierStatistic.setGroupAgencyCode(groupGovAgencyCode);
			}
		}

		int ontimePercent = 100;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
		return dossierStatistic;
	}
	
	public void updateBatchStatistic(List<JSONObject> dossierDataObjs) throws PortalException, SystemException {
		long startTime = System.currentTimeMillis();
		for (JSONObject dossierObj : dossierDataObjs) {
			int pausingCount = 0;
			long companyId = dossierObj.has("companyId") ? dossierObj.getLong("companyId") : 0l;
			long groupId = dossierObj.has("groupId") ? dossierObj.getLong("groupId") : 0l;
			long userId = -1;
			String userName = "ADM";
			
			int month = dossierObj.has("month") ? dossierObj.getInt("month") : 0;
			int year = dossierObj.has("year") ? dossierObj.getInt("year") : 0;
			String system = dossierObj.has("system") ? dossierObj.getString("system") : StringPool.BLANK;
			int totalCount = dossierObj.has("totalCount") ? dossierObj.getInt("totalCount") : 0;
			int deniedCount = dossierObj.has("deniedCount") ? dossierObj.getInt("deniedCount") : 0;
			int cancelledCount = dossierObj.has("cancelledCount") ? dossierObj.getInt("cancelledCount") : 0;
			int processCount = dossierObj.has("processCount") ? dossierObj.getInt("processCount") : 0;
			int remainingCount = dossierObj.has("remainingCount") ? dossierObj.getInt("remainingCount") : 0;
			int receivedCount = dossierObj.has("receivedCount") ? dossierObj.getInt("receivedCount") : 0;
			int onlineCount = dossierObj.has("onlineCount") ? dossierObj.getInt("onlineCount") : 0;
			int releaseCount = dossierObj.has("releaseCount") ? dossierObj.getInt("releaseCount") : 0;
			int betimesCount = dossierObj.has("betimesCount") ? dossierObj.getInt("betimesCount") : 0;
			int ontimeCount = dossierObj.has("ontimeCount") ? dossierObj.getInt("ontimeCount") : 0;
			int overtimeCount = dossierObj.has("overtimeCount") ? dossierObj.getInt("overtimeCount") : 0;
			int doneCount = dossierObj.has("doneCount") ? dossierObj.getInt("doneCount") : 0;
			int releasingCount = dossierObj.has("releasingCount") ? dossierObj.getInt("releasingCount") : 0;
			int unresolvedCount = dossierObj.has("unresolvedCount") ? dossierObj.getInt("unresolvedCount") : 0;
			int processingCount = dossierObj.has("processingCount") ? dossierObj.getInt("processingCount") : 0;
			int undueCount = dossierObj.has("undueCount") ? dossierObj.getInt("undueCount") : 0;
			int overdueCount = dossierObj.has("overdueCount") ? dossierObj.getInt("overdueCount") : 0;
			int ontimePercentage = dossierObj.has("ontimePercentage") ? dossierObj.getInt("ontimePercentage") : 0;
			int overtimeInside = dossierObj.has("overtimeInside") ? dossierObj.getInt("overtimeInside") : 0;
			int overtimeOutside = dossierObj.has("overtimeOutside") ? dossierObj.getInt("overtimeOutside") : 0;
			int interoperatingCount = dossierObj.has("interoperatingCount") ? dossierObj.getInt("interoperatingCount") : 0;
			int waitingCount = dossierObj.has("waitingCount") ? dossierObj.getInt("waitingCount") : 0;
			String govAgencyCode = dossierObj.has("govAgencyCode") ? dossierObj.getString("govAgencyCode") : StringPool.BLANK;
			String govAgencyName = dossierObj.has("govAgencyName") ? dossierObj.getString("govAgencyName") : StringPool.BLANK;
			String domainCode = dossierObj.has("domainCode") ? dossierObj.getString("domainCode") : StringPool.BLANK;
			String domainName = dossierObj.has("domainName") ? dossierObj.getString("domainName") : StringPool.BLANK;
			String groupGovAgencyCode = dossierObj.has("groupAgencyCode") ? dossierObj.getString("groupAgencyCode") : StringPool.BLANK;
			int reporting = dossierObj.has("reporting") ? dossierObj.getInt("reporting") : 0;
			int onegateCount = dossierObj.has("onegateCount") ? dossierObj.getInt("onegateCount") : 0;
			int outsideCount = dossierObj.has("outsideCount") ? dossierObj.getInt("outsideCount") : 0;
			int insideCount = dossierObj.has("insideCount") ? dossierObj.getInt("insideCount") : 0;
			int viaPostalCount = dossierObj.has("viaPostalCount") ? dossierObj.getInt("viaPostalCount") : 0;
			int saturdayCount = dossierObj.has("saturdayCount") ? dossierObj.getInt("saturdayCount") : 0;
			int dossierOnline3Count = dossierObj.has("dossierOnline3Count") ? dossierObj.getInt("dossierOnline3Count") : 0;
			int dossierOnline4Count = dossierObj.has("dossierOnline4Count") ? dossierObj.getInt("dossierOnline4Count") : 0;
			int receiveDossierSatCount = dossierObj.has("receiveDossierSatCount") ? dossierObj.getInt("receiveDossierSatCount") : 0;
			int releaseDossierSatCount = dossierObj.has("releaseDossierSatCount") ? dossierObj.getInt("releaseDossierSatCount") : 0;
			int fromViaPostalCount = dossierObj.has("fromViaPostalCount") ? dossierObj.getInt("fromViaPostalCount") : 0;
			//Check record exit
			OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsitSystem(groupId,
					month, year, govAgencyCode, domainCode, system, groupGovAgencyCode);
			Date now = new Date();
			long dossierStatisticId;
			if (dossierStatistic == null) {
				dossierStatisticId = counterLocalService.increment(OpencpsDossierStatistic.class.getName());
				dossierStatistic = opencpsDossierStatisticPersistence.create(dossierStatisticId);
				dossierStatistic.setCreateDate(now);
				dossierStatistic.setModifiedDate(now);
				dossierStatistic.setCompanyId(companyId);
				dossierStatistic.setGroupId(groupId);
				dossierStatistic.setUserId(userId);
				dossierStatistic.setUserName(userName);
				dossierStatistic.setMonth(month);
				dossierStatistic.setYear(year);
				dossierStatistic.setSystem(system);
				dossierStatistic.setTotalCount(totalCount);
				dossierStatistic.setDeniedCount(deniedCount);
				dossierStatistic.setCancelledCount(cancelledCount);
				dossierStatistic.setProcessCount(processCount);
				dossierStatistic.setRemainingCount(remainingCount);
				dossierStatistic.setReceivedCount(receivedCount);
				dossierStatistic.setOnlineCount(onlineCount);
				dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
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
				dossierStatistic.setGroupAgencyCode(groupGovAgencyCode);
				
//				lstStatistics.add(dossierStatistic);
			} else {
				if (dossierStatistic.getReporting() == 0) {
					dossierStatistic.setModifiedDate(now);
					dossierStatistic.setMonth(month);
					dossierStatistic.setYear(year);
					dossierStatistic.setSystem(system);
					dossierStatistic.setTotalCount(totalCount);
					dossierStatistic.setDeniedCount(deniedCount);
					dossierStatistic.setCancelledCount(cancelledCount);
					dossierStatistic.setProcessCount(processCount);
					dossierStatistic.setRemainingCount(remainingCount);
					dossierStatistic.setReceivedCount(receivedCount);
					dossierStatistic.setOnlineCount(onlineCount);
					dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
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
					dossierStatistic.setReporting(reporting);
					dossierStatistic.setViaPostalCount(viaPostalCount);
					dossierStatistic.setSaturdayCount(saturdayCount);
					dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
					dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
					dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
					dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);		
					dossierStatistic.setGroupAgencyCode(groupGovAgencyCode);
				}
			}

			int ontimePercent = 100;
			if (releaseCount > 0) {
				ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
			}

			dossierStatistic.setOntimePercentage(ontimePercent);
			dossierStatistic = opencpsDossierStatisticPersistence.update(dossierStatistic);
			
		}
		long endTime = System.currentTimeMillis();
		_log.debug("UPDATE BATCH DOSSIER STATISTIC: " + (endTime - startTime) / 1000.0);		
	}
	
	public List<OpencpsDossierStatistic> findByG_NM_Y(long groupId, int notMonth, int year) {
		return opencpsDossierStatisticPersistence.findByG_NM_Y(groupId, notMonth, year);
	}
	
	private Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticLocalServiceImpl.class);

}