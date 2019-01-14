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

import org.opencps.statistic.model.OpencpsPersonStatistic;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.service.base.OpencpsPersonStatisticLocalServiceBaseImpl;

/**
 * The implementation of the opencps person statistic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.statistic.service.OpencpsPersonStatisticLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see OpencpsPersonStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.OpencpsPersonStatisticLocalServiceUtil
 */
public class OpencpsPersonStatisticLocalServiceImpl
	extends OpencpsPersonStatisticLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.statistic.service.OpencpsPersonStatisticLocalServiceUtil} to access the opencps person statistic local service.
	 */

	public OpencpsPersonStatistic checkExsit(long groupId, int month, int year, String govAgency, Long employeeId,
			String votingCode) {
		return opencpsPersonStatisticFinder.checkContains(groupId, month, year, govAgency, employeeId, votingCode);
	}

	public OpencpsPersonStatistic updatePersonStatistic(long personStatisticId, long companyId, long groupId,
			long userId, String userName, int month, int year, String votingSubject, int totalVoted, int veryGoodCount,
			int goodCount, int badCount, int percentVeryGood, int percentGood, int percentBad, String govAgencyCode,
			String govAgencyName, long employeeId, String votingCode, int totalCount) {

		OpencpsPersonStatistic personStatistic = null;
		Date now = new Date();
		if (personStatisticId == 0L) {
			personStatisticId = counterLocalService.increment(OpencpsPersonStatistic.class.getName());
			personStatistic = opencpsPersonStatisticPersistence.create(personStatisticId);
			personStatistic.setCreateDate(now);
			personStatistic.setModifiedDate(now);
			personStatistic.setCompanyId(companyId);
			personStatistic.setGroupId(groupId);
			personStatistic.setUserId(userId);
			personStatistic.setUserName(userName);
			personStatistic.setMonth(month);
			personStatistic.setYear(year);
			personStatistic.setVotingSubject(votingSubject);
			personStatistic.setGovAgencyCode(govAgencyCode);
			personStatistic.setGovAgencyName(govAgencyName);
			personStatistic.setEmployeeId(employeeId);
			personStatistic.setVotingCode(votingCode);
			personStatistic.setTotalVoted(totalVoted);
			personStatistic.setVeryGoodCount(veryGoodCount);
			personStatistic.setGoodCount(goodCount);
			personStatistic.setBadCount(badCount);
			personStatistic.setPercentVeryGood(percentVeryGood);
			personStatistic.setPercentGood(percentGood);
			personStatistic.setPercentBad(percentBad);
			personStatistic.setTotalCount(totalCount);
		} else {
			personStatistic = opencpsPersonStatisticPersistence.fetchByPrimaryKey(personStatisticId);
			personStatistic.setModifiedDate(now);
			personStatistic.setMonth(month);
			personStatistic.setYear(year);
			personStatistic.setVotingSubject(votingSubject);
			personStatistic.setGovAgencyCode(govAgencyCode);
			personStatistic.setGovAgencyName(govAgencyName);
			personStatistic.setEmployeeId(employeeId);
			personStatistic.setVotingCode(votingCode);
			personStatistic.setTotalVoted(totalVoted);
			personStatistic.setVeryGoodCount(veryGoodCount);
			personStatistic.setGoodCount(goodCount);
			personStatistic.setBadCount(badCount);
			personStatistic.setPercentVeryGood(percentVeryGood);
			personStatistic.setPercentGood(percentGood);
			personStatistic.setPercentBad(percentBad);
			personStatistic.setTotalCount(totalCount);
		}

		return opencpsPersonStatisticPersistence.update(personStatistic);
	}

	public List<OpencpsPersonStatistic> fetchPersonStatistic(long groupId, int month, int year, String votingCode,
			Long employeeId, String govAgencyCode, int start, int end) throws PortalException, SystemException {

		return opencpsPersonStatisticFinder.searchPersonStatistic(groupId, year, votingCode, employeeId,
				govAgencyCode, start, end);
	}

	public List<OpencpsPersonStatistic> searchPersonStatistic(long groupId, int month, int year, String votingCode,
			Long employeeId, String govAgencyCode, int start, int end) throws PortalException, SystemException {

		return opencpsPersonStatisticFinder.searchByPersonServiceGovAgencyGroup(groupId, month, year, votingCode,
				employeeId, govAgencyCode, start, end);
	}

//	public void removePersonStatisticByD_M_Y(long groupId, String domainCode, int month, int year) {
//		opencpsPersonStatisticPersistence.removeByG_D_M_Y(groupId, domainCode, month, year);
//	}

	public void removePersonStatisticByMonthYear(long groupId, int month, int year) {
		opencpsPersonStatisticPersistence.removeByG_M_Y(groupId, month, year);
	}

	public void removePersonStatisticByYear(long companyId, long groupId, int month, int year) {
		opencpsPersonStatisticPersistence.removeByCID_GID_Y(companyId, groupId, month, year);
	}
}