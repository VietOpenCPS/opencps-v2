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

import java.util.Date;
import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsDossierStatisticMgt;
import org.opencps.statistic.service.OpencpsDossierStatisticMgtLocalServiceUtil;
import org.opencps.statistic.service.base.OpencpsDossierStatisticMgtLocalServiceBaseImpl;

/**
 * The implementation of the opencps dossier statistic mgt local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.statistic.service.OpencpsDossierStatisticMgtLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticMgtLocalServiceBaseImpl
 * @see org.opencps.statistic.service.OpencpsDossierStatisticMgtLocalServiceUtil
 */
public class OpencpsDossierStatisticMgtLocalServiceImpl
	extends OpencpsDossierStatisticMgtLocalServiceBaseImpl {
	
	public OpencpsDossierStatisticMgt checkContains(long groupId, int month, int year, String govAgencyCode, String domainCode) {
		return opencpsDossierStatisticMgtFinder.checkContains(groupId, month, year, govAgencyCode, domainCode);
	}
	
	public OpencpsDossierStatisticMgt checkContainGroupBy(long groupId, int month, int year, String govAgencyCode, String domainCode, int groupBy) {
		return opencpsDossierStatisticMgtFinder.checkContainsGroupBy(groupId, month, year, govAgencyCode, domainCode, groupBy);
	}
	
	public void updateBatchStatisticMgt(List<JSONObject> dossierDataObjs) throws PortalException, SystemException {
		long startTime = System.currentTimeMillis();
		for (JSONObject dossierObj : dossierDataObjs) {
			long groupId = dossierObj.has("groupId") ? dossierObj.getLong("groupId") : 0l;
			long userId = -1;
			String userName = "ADM";
			
			int month = dossierObj.has("month") ? dossierObj.getInt("month") : 0;
			int year = dossierObj.has("year") ? dossierObj.getInt("year") : 0;
			int totalCount = dossierObj.has("totalCount") ? dossierObj.getInt("totalCount") : 0;
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
			int processingCount = dossierObj.has("processingCount") ? dossierObj.getInt("processingCount") : 0;
			int undueCount = dossierObj.has("undueCount") ? dossierObj.getInt("undueCount") : 0;
			int overdueCount = dossierObj.has("overdueCount") ? dossierObj.getInt("overdueCount") : 0;
			int ontimePercentage = dossierObj.has("ontimePercentage") ? dossierObj.getInt("ontimePercentage") : 0;
			int waitingCount = dossierObj.has("waitingCount") ? dossierObj.getInt("waitingCount") : 0;
			String govAgencyCode = dossierObj.has("govAgencyCode") ? dossierObj.getString("govAgencyCode") : StringPool.BLANK;
			String govAgencyName = dossierObj.has("govAgencyName") ? dossierObj.getString("govAgencyName") : StringPool.BLANK;
			String domainCode = dossierObj.has("domainCode") ? dossierObj.getString("domainCode") : StringPool.BLANK;
			String domainName = dossierObj.has("domainName") ? dossierObj.getString("domainName") : StringPool.BLANK;
			String serviceCode = dossierObj.has("serviceCode") ? dossierObj.getString("serviceCode") : StringPool.BLANK;
			String serviceName = dossierObj.has("serrviceName") ? dossierObj.getString("serrviceName") : StringPool.BLANK;
			int onegateCount = dossierObj.has("onegateCount") ? dossierObj.getInt("onegateCount") : 0;
			int groupBy = dossierObj.has("groupBy") ? dossierObj.getInt("groupBy") : 0;
			int cancelledCount = dossierObj.has("cancelledCount") ? dossierObj.getInt("cancelledCount") : 0;
			int unresolvedCount = dossierObj.has("unresolvedCount") ? dossierObj.getInt("unresolvedCount") : 0;
			//Check record exit
			OpencpsDossierStatisticMgt dossierStatisticMgt = OpencpsDossierStatisticMgtLocalServiceUtil.checkContainGroupBy(groupId, month, year, govAgencyCode, domainCode, groupBy);
			Date now = new Date();
			long dossierStatisticMgtId;
			if (dossierStatisticMgt == null) {
				dossierStatisticMgtId = counterLocalService.increment(OpencpsDossierStatisticMgt.class.getName());
				dossierStatisticMgt = opencpsDossierStatisticMgtPersistence.create(dossierStatisticMgtId);
				dossierStatisticMgt.setCreateDate(now);
				dossierStatisticMgt.setModifiedDate(now);
				dossierStatisticMgt.setGroupId(groupId);
				dossierStatisticMgt.setUserId(userId);
				dossierStatisticMgt.setUserName(userName);
				dossierStatisticMgt.setMonth(month);
				dossierStatisticMgt.setYear(year);
				dossierStatisticMgt.setTotalCount(totalCount);
				dossierStatisticMgt.setProcessCount(processCount);
				dossierStatisticMgt.setRemainingCount(remainingCount);
				dossierStatisticMgt.setReceivedCount(receivedCount);
				dossierStatisticMgt.setOnlineCount(onlineCount);
				dossierStatisticMgt.setReleaseCount(releaseCount);
				dossierStatisticMgt.setBetimesCount(betimesCount);
				dossierStatisticMgt.setOntimeCount(ontimeCount);
				dossierStatisticMgt.setOvertimeCount(overtimeCount);
				dossierStatisticMgt.setDoneCount(doneCount);
				dossierStatisticMgt.setReleasingCount(releasingCount);
				dossierStatisticMgt.setProcessingCount(processingCount);
				dossierStatisticMgt.setUndueCount(undueCount);
				dossierStatisticMgt.setOverdueCount(overdueCount);
				dossierStatisticMgt.setOntimePercentage(ontimePercentage);
				dossierStatisticMgt.setWaitingCount(waitingCount);
				dossierStatisticMgt.setGovAgencyCode(govAgencyCode);
				dossierStatisticMgt.setGovAgencyName(govAgencyName);
				dossierStatisticMgt.setDomainCode(domainCode);
				dossierStatisticMgt.setDomainName(domainName);
				dossierStatisticMgt.setServiceCode(serviceCode);
				dossierStatisticMgt.setServiceName(serviceName);
				dossierStatisticMgt.setGroupBy(groupBy);
				dossierStatisticMgt.setUnresolvedCount(unresolvedCount);
				dossierStatisticMgt.setCancelledCount(cancelledCount);
			} else {
				dossierStatisticMgt.setModifiedDate(now);
				dossierStatisticMgt.setMonth(month);
				dossierStatisticMgt.setYear(year);
				dossierStatisticMgt.setTotalCount(totalCount);
				dossierStatisticMgt.setProcessCount(processCount);
				dossierStatisticMgt.setRemainingCount(remainingCount);
				dossierStatisticMgt.setReceivedCount(receivedCount);
				dossierStatisticMgt.setOnlineCount(onlineCount);
				dossierStatisticMgt.setReleaseCount(releaseCount);
				dossierStatisticMgt.setBetimesCount(betimesCount);
				dossierStatisticMgt.setOntimeCount(ontimeCount);
				dossierStatisticMgt.setOvertimeCount(overtimeCount);
				dossierStatisticMgt.setDoneCount(doneCount);
				dossierStatisticMgt.setReleasingCount(releasingCount);
				dossierStatisticMgt.setProcessingCount(processingCount);
				dossierStatisticMgt.setUndueCount(undueCount);
				dossierStatisticMgt.setOverdueCount(overdueCount);
				dossierStatisticMgt.setOntimePercentage(ontimePercentage);
				dossierStatisticMgt.setWaitingCount(waitingCount);
				dossierStatisticMgt.setOnegateCount(onegateCount);					
				dossierStatisticMgt.setGovAgencyCode(govAgencyCode);
				dossierStatisticMgt.setGovAgencyName(govAgencyName);
				dossierStatisticMgt.setDomainCode(domainCode);
				dossierStatisticMgt.setDomainName(domainName);
				dossierStatisticMgt.setServiceCode(serviceCode);
				dossierStatisticMgt.setServiceName(serviceName);
				dossierStatisticMgt.setGroupBy(groupBy);
				dossierStatisticMgt.setUnresolvedCount(unresolvedCount);
				dossierStatisticMgt.setCancelledCount(cancelledCount);
			}

			int ontimePercent = 100;
			if (releaseCount > 0) {
				ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
			}

			dossierStatisticMgt.setOntimePercentage(ontimePercent);
			opencpsDossierStatisticMgtPersistence.update(dossierStatisticMgt);
			
		}
		long endTime = System.currentTimeMillis();
		_log.debug("UPDATE BATCH DOSSIER STATISTIC MGT: " + (endTime - startTime) / 1000.0);			
	}
	
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId, int month, int year) {
		return opencpsDossierStatisticMgtPersistence.findByG_NM_Y(groupId, month, year);
	}
	
	public List<OpencpsDossierStatisticMgt> findByG_Y_ARR(long groupId, int[] yearArr) {
		return opencpsDossierStatisticMgtPersistence.findByG_Y_REPO(groupId, yearArr);
	}
	
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(long groupId, int month, int year, int groupBy) {
		return opencpsDossierStatisticMgtPersistence.findByG_NM_Y_GB(groupId, month, year, groupBy);
	}
	
	public List<OpencpsDossierStatisticMgt> findByG_Y_ARR_GB(long groupId, int[] yearArr, int groupBy) {
		return opencpsDossierStatisticMgtPersistence.findByG_Y_REPO_GB(groupId, yearArr, groupBy);
	}
	
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(long groupId, int month, int year, String govAgencyCode, String domainCode, int groupBy){
		return opencpsDossierStatisticMgtPersistence.findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode, domainCode, groupBy);
	}

	public List<OpencpsDossierStatisticMgt> searchDossierStatistic(long groupId, int month, int year, String domainCode,
			String govAgencyCode, int groupBy, int start, int end){
		try {
			return opencpsDossierStatisticMgtFinder.searchByDomainAgencyGroupBy(groupId, month, year, domainCode, govAgencyCode, groupBy, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticMgtLocalServiceImpl.class);



	
}