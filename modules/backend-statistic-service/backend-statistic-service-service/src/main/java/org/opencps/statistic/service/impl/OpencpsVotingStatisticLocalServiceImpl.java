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

import java.util.Date;
import java.util.List;

import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.service.base.OpencpsVotingStatisticLocalServiceBaseImpl;

/**
 * The implementation of the opencps voting statistic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.statistic.service.OpencpsVotingStatisticLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see OpencpsVotingStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil
 */
public class OpencpsVotingStatisticLocalServiceImpl
	extends OpencpsVotingStatisticLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil} to access the opencps voting statistic local service.
	 */

	public OpencpsVotingStatistic checkExsit(long groupId, int month, int year, String govAgency, String domain,
			String votingCode) {
		return opencpsVotingStatisticFinder.checkContains(groupId, month, year, govAgency, domain, votingCode);
	}

	public OpencpsVotingStatistic updateVotingStatistic(long votingStatisticId, long companyId, long groupId,
			long userId, String userName, int month, int year, int totalVoted, int percentVeryGood, int percentGood,
			int percentBad, String govAgencyCode, String govAgencyName, String domainCode, String domainName,
			String votingCode, int totalCount) {

		OpencpsVotingStatistic votingStatistic = null;
		Date now = new Date();
		if (votingStatisticId == 0L) {
			votingStatisticId = counterLocalService.increment(OpencpsVotingStatistic.class.getName());
			votingStatistic = opencpsVotingStatisticPersistence.create(votingStatisticId);
			votingStatistic.setCreateDate(now);
			votingStatistic.setModifiedDate(now);
			votingStatistic.setCompanyId(companyId);
			votingStatistic.setGroupId(groupId);
			votingStatistic.setUserId(userId);
			votingStatistic.setUserName(userName);
			votingStatistic.setMonth(month);
			votingStatistic.setYear(year);
			votingStatistic.setGovAgencyCode(govAgencyCode);
			votingStatistic.setGovAgencyName(govAgencyName);
			votingStatistic.setDomainCode(domainCode);
			votingStatistic.setDomainName(domainName);
			votingStatistic.setVotingCode(votingCode);
			votingStatistic.setTotalVoted(totalVoted);
			votingStatistic.setPercentVeryGood(percentVeryGood);
			votingStatistic.setPercentGood(percentGood);
			votingStatistic.setPercentBad(percentBad);
			votingStatistic.setTotalCount(totalCount);
		} else {
			votingStatistic = opencpsVotingStatisticPersistence.fetchByPrimaryKey(votingStatisticId);
			votingStatistic.setModifiedDate(now);
			votingStatistic.setMonth(month);
			votingStatistic.setYear(year);
			votingStatistic.setGovAgencyCode(govAgencyCode);
			votingStatistic.setGovAgencyName(govAgencyName);
			votingStatistic.setDomainCode(domainCode);
			votingStatistic.setDomainName(domainName);
			votingStatistic.setVotingCode(votingCode);
			votingStatistic.setTotalVoted(totalVoted);
			votingStatistic.setPercentVeryGood(percentVeryGood);
			votingStatistic.setPercentGood(percentGood);
			votingStatistic.setPercentBad(percentBad);
			votingStatistic.setTotalCount(totalCount);
		}

		return opencpsVotingStatisticLocalService.updateOpencpsVotingStatistic(votingStatistic);
	}

	public List<OpencpsVotingStatistic> fetchVotingStatistic(long groupId, int month, int year, String votingCode,
			String domain, String govAgencyCode, int start, int end) throws PortalException, SystemException {

		return opencpsVotingStatisticFinder.searchVotingStatistic(groupId, year, votingCode, domain,
				govAgencyCode, start, end);
	}

	public List<OpencpsVotingStatistic> searchVotingStatistic(long groupId, int month, int year, String votingCode,
			String domain, String govAgencyCode, int start, int end) throws PortalException, SystemException {

		return opencpsVotingStatisticFinder.searchByVotingServiceGovAgencyGroup(groupId, month, year, votingCode,
				domain, govAgencyCode, start, end);
	}

}