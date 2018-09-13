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

import java.util.Date;
import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.service.base.OpencpsDossierStatisticLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

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
public class OpencpsDossierStatisticLocalServiceImpl extends OpencpsDossierStatisticLocalServiceBaseImpl {
	public OpencpsDossierStatistic checkExsit(long groupId, int month, int year, String govAgency, String domain,
			boolean reporting) {
		return opencpsDossierStatisticFinder.checkContains(groupId, month, year, domain, govAgency, reporting);
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

		int ontimePercent = 0;
		if (releaseCount > 0) {
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
		}

		dossierStatistic.setOntimePercentage(ontimePercent);
		dossierStatistic = opencpsDossierStatisticLocalService.updateOpencpsDossierStatistic(dossierStatistic);
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
			String govAgencyCode, String groupAgenvyCode, boolean reporting, int start, int end)
			throws PortalException, SystemException {
		return opencpsDossierStatisticFinder.searchDossierStatistic(groupId, year, domain, govAgencyCode,
				groupAgenvyCode, reporting, start, end);
	}

	public List<OpencpsDossierStatistic> searchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String groupAgenvyCode, boolean reporting, int start, int end)
			throws PortalException, SystemException {
		
		return opencpsDossierStatisticFinder.searchByDomainGovAgencyGroupAndReporting(groupId, month, year, domain,
				govAgencyCode, groupAgenvyCode, reporting, start, end);
	}
	
	private Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticLocalServiceImpl.class);

}