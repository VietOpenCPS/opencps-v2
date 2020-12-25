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

package org.opencps.synctracking.service.impl;

import com.liferay.portal.kernel.util.Validator;
import org.opencps.synctracking.model.SyncTracking;
import org.opencps.synctracking.model.SyncTrackingQuery;
import org.opencps.synctracking.service.base.SyncTrackingLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the sync tracking local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synctracking.service.SyncTrackingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author giaonn
 * @see SyncTrackingLocalServiceBaseImpl
 * @see org.opencps.synctracking.service.SyncTrackingLocalServiceUtil
 */
public class SyncTrackingLocalServiceImpl
	extends SyncTrackingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synctracking.service.SyncTrackingLocalServiceUtil} to access the sync tracking local service.
	 */
	public List<SyncTracking> getByGroupId(long groupId, int start, int end) {
		return syncTrackingPersistence.findByGroupId(groupId, start, end);
	}

	public List<SyncTracking> getByGroupIdAndDate(long groupId, Date fromDate, Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_GID_CREATED_BETWEEN(groupId, fromDate, toDate, start, end);
	}

	public List<SyncTracking> getByGroupIdAndDossierNoAndDate(long groupId, String dossierNo, Date fromDate,
															  Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_GID_DOSSIERNO_CREATED_BETWEEN(groupId, dossierNo, fromDate,
				toDate, start, end);
	}

	public List<SyncTracking> getByGroupIdAndServiceCodeAndDate(long groupId, String serviceCode,
																Date fromDate, Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_GID_SERVICECODE_CREATED_BETWEEN(groupId, serviceCode, fromDate,
				toDate, start, end);
	}

	public List<SyncTracking> getByGroupIdAndDossierNoAndServiceCodeAndDate(long groupId, String dossierNo,
																			String serviceCode, Date fromDate,
																			Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(groupId, serviceCode,
				dossierNo, fromDate, toDate, start, end);
	}

	public SyncTracking createSyncTrackingManual(SyncTrackingQuery syncTrackingQuery) {
		long syncTrackingId = counterLocalService.increment(SyncTracking.class.getName());
		Date now = new Date();

		SyncTracking syncTracking = syncTrackingLocalService.createSyncTracking(syncTrackingId);
		syncTracking.setCreateDate(now);
		syncTracking.setModifiedDate(now);
		syncTracking.setGroupId(syncTrackingQuery.groupId);
		if(Validator.isNotNull(syncTrackingQuery.dossierNo)) {
			syncTracking.setDossierNo(syncTrackingQuery.dossierNo);
		}

		if(Validator.isNotNull(syncTrackingQuery.serviceCode)) {
			syncTracking.setServiceCode(syncTrackingQuery.serviceCode);
		}

		if(Validator.isNotNull(syncTrackingQuery.stateSync)) {
			syncTracking.setStateSync(syncTrackingQuery.stateSync);
		}

		if(Validator.isNotNull(syncTrackingQuery.api)) {
			syncTracking.setApi(syncTrackingQuery.api);
		}

		if(Validator.isNotNull(syncTrackingQuery.fromUnit)) {
			syncTracking.setFromUnit(syncTrackingQuery.fromUnit);
		}

		if(Validator.isNotNull(syncTrackingQuery.toUnitSingle)) {
			syncTracking.setToUnit(syncTrackingQuery.toUnitSingle);
		}

		if(Validator.isNotNull(syncTrackingQuery.bodyRequest)) {
			syncTracking.setBodyRequest(syncTrackingQuery.bodyRequest);
		}

		if(Validator.isNotNull(syncTrackingQuery.bodyResponse)) {
			syncTracking.setResponse(syncTrackingQuery.bodyResponse);
		}

		syncTrackingPersistence.update(syncTracking);

		return syncTracking;
	}
}