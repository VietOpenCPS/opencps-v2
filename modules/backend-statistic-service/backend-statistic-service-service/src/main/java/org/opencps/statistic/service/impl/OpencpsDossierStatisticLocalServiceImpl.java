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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.statistic.dto.DossierStatisticData;
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

	public OpencpsDossierStatistic updateStatistic(long dossierStatisticId, long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, boolean reporting, int onegateCount, int outsideCount,
			int insideCount) throws PortalException, SystemException {
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
			String domainCode, boolean reporting) {
		return opencpsDossierStatisticPersistence.fetchByM_Y_DM_G(groupId, govAgencyCode, month, year, domainCode,
				reporting);
	}

	public List<OpencpsDossierStatistic> fetchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {
		return opencpsDossierStatisticFinder.searchDossierStatistic(groupId, year, domain, govAgencyCode,
				groupAgenvyCode, start, end);
	}

	public List<OpencpsDossierStatistic> searchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String groupAgenvyCode, int start, int end)
			throws PortalException, SystemException {

		return opencpsDossierStatisticFinder.searchByDomainGovAgencyGroup(groupId, month, year, domain,
				govAgencyCode, groupAgenvyCode, start, end);
	}
	
//	public void removeDossierStatisticByD_M_Y(long groupId, String domainCode, int month, int year) throws NoSuchOpencpsDossierStatisticException {
//		opencpsDossierStatisticPersistence.removeByG_D_M_Y(groupId, domainCode, month, year);
//	}
//	
//	public void removeDossierStatisticByMonthYear(long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
//		opencpsDossierStatisticPersistence.removeByG_M_Y(groupId, month, year);
//	}
//
//	public void removeDossierStatisticByYear(long companyId, long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
//		opencpsDossierStatisticPersistence.removeByCID_GID_Y(companyId, groupId, month, year);
//	}

	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYear(long groupId, int month, int year) {
		return opencpsDossierStatisticPersistence.findByG_M_Y(groupId, month, year);
	}

	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYearAndReport(long groupId, int month, int year, boolean reporting) {
		return opencpsDossierStatisticPersistence.findByGID_M_Y_RP(groupId, month, year, reporting);
	}

//	public OpencpsDossierStatistic removeByG_M_Y_G_D(long groupId, int month, int year, String govAgencyCode,
//			String domainCode) throws NoSuchOpencpsDossierStatisticException {
//		return opencpsDossierStatisticPersistence.removeByG_M_Y_G_D(groupId, month, year, govAgencyCode, domainCode);
//	}

	public List<OpencpsDossierStatistic> getDossierStatisticByYear(long companyId, long groupId, int month, int year) {
		return opencpsDossierStatisticPersistence.findByCID_GID_Y(companyId, groupId, month, year);
	}

	public List<OpencpsDossierStatistic> getDossierStatisticByMonthsYearAndReport(long groupId, int[] month, int year, boolean reporting) {
		return opencpsDossierStatisticPersistence.findByGID_MS_Y_RP(groupId, month, year, reporting);
	}

	public OpencpsDossierStatistic createOrUpdateStatistic(long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, boolean reporting, int onegateCount, int outsideCount,
			int insideCount) throws PortalException, SystemException {
		OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsit(
				groupId, month, year, govAgencyCode,
				domainCode);
		Date now = new Date();
		long dossierStatisticId = 0l;
		_log.debug(dossierStatisticId);
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
		} else {
			if (!dossierStatistic.isReporting()) {
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
				dossierStatistic.setReporting(reporting);
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

			byte pausingCount = 0;

			createOrUpdateStatistic(payload.getCompanyId(),
						payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
						payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
						payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
						payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
						payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
						payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
						payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
						payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
						payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
						payload.getDomainName(), payload.isReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
						payload.getInsideCount());
		}
	}

	public OpencpsDossierStatistic createOnlyStatistic(long companyId, long groupId, long userId,
			String userName, int month, int year, int totalCount, int deniedCount, int cancelledCount, int processCount,
			int remainingCount, int receivedCount, int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
			int overtimeCount, int doneCount, int releasingCount, int unresolvedCount, int processingCount,
			int undueCount, int overdueCount, int pausingCount, int ontimePercentage, int overtimeInside,
			int overtimeOutside, int interoperatingCount, int waitingCount, String govAgencyCode, String govAgencyName,
			String domainCode, String domainName, boolean reporting, int onegateCount, int outsideCount,
			int insideCount) throws PortalException, SystemException {
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
			String domainCode, String domainName, boolean reporting, int onegateCount, int outsideCount,
			int insideCount) throws PortalException, SystemException {
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
	
	private Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticLocalServiceImpl.class);

}