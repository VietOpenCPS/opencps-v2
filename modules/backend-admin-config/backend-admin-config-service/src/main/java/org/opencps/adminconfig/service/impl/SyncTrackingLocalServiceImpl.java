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

package org.opencps.adminconfig.service.impl;

import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.adminconfig.exception.NoSuchSyncTrackingException;
import org.opencps.adminconfig.model.SyncTracking;
import org.opencps.adminconfig.service.base.SyncTrackingLocalServiceBaseImpl;


/**
 * The implementation of the sync tracking local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.adminconfig.service.SyncTrackingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see SyncTrackingLocalServiceBaseImpl
 * @see org.opencps.adminconfig.service.SyncTrackingLocalServiceUtil
 */
public class SyncTrackingLocalServiceImpl
	extends SyncTrackingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.adminconfig.service.SyncTrackingLocalServiceUtil} to access the sync tracking local service.
	 */
	
	public SyncTracking updateSyncTracking(
			long userId, long groupId, long trackingId, String dossierNo,
			String referenceUid, String serviceCode, int stateSync, String api,
			String bodyRequest, String bodyResponse) {
			SyncTracking syncTracking = null;
			Date now = new Date();
			
			if (trackingId == 0) {
				trackingId = counterLocalService.increment(SyncTracking.class.getName());
				syncTracking = syncTrackingPersistence.create(trackingId);
				
				syncTracking.setCreateDate(now);
				syncTracking.setModifiedDate(now);
				syncTracking.setGroupId(groupId);
				syncTracking.setUserId(userId);
				if(Validator.isNotNull(dossierNo)) {
					syncTracking.setDossierNo(dossierNo);
				}

				if(Validator.isNotNull(referenceUid)) {
					syncTracking.setReferenceUid(referenceUid);
				}

				if(Validator.isNotNull(serviceCode)) {
					syncTracking.setServiceCode(serviceCode);
				}

				if(Validator.isNotNull(stateSync)) {
					syncTracking.setStateSync(stateSync);
				}

				if(Validator.isNotNull(api)) {
					syncTracking.setApi(api);
				}


				if(Validator.isNotNull(bodyRequest)) {
					syncTracking.setBodyRequest(bodyRequest);
				}

				if(Validator.isNotNull(bodyResponse)) {
					syncTracking.setResponse(bodyResponse);
				}
			}
			return syncTrackingPersistence.update(syncTracking);
		}
	

	public List<SyncTracking> getByDate(Date fromDate, Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_CREATED_BETWEEN(fromDate, toDate, start, end);
	}

	public List<SyncTracking> getByDossierNoAndDate(String dossierNo, Date fromDate,
															  Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_DOSSIERNO_CREATED_BETWEEN(dossierNo, fromDate,
				toDate, start, end);
	}

	public List<SyncTracking> getByServiceCodeAndDate(String serviceCode,
																Date fromDate, Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_SERVICECODE_CREATED_BETWEEN(serviceCode, fromDate,
				toDate, start, end);
	}

	public List<SyncTracking> getByDossierNoAndServiceCodeAndDate(String dossierNo, String serviceCode, Date fromDate,
																		Date toDate, int start, int end) {
		return syncTrackingPersistence.findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(serviceCode,
				dossierNo, fromDate, toDate, start, end);
	}

	public List<SyncTracking> getByReferenceUidAndDate(String referenceUid, Date fromDate, Date toDate
			, int start, int end ) {
		return syncTrackingPersistence.findByF_REFERENCE_UID_CREATED_BETWEEN(referenceUid,
				fromDate, toDate, start, end);
	}

	public SyncTracking getByDossierNo(String dossierNo) {
		try {
			return syncTrackingPersistence.findByF_DossierNo(dossierNo);
		} catch (NoSuchSyncTrackingException e) {
			return null;
		}
	}

	public SyncTracking getByReferenceUid(String referenceUid) {
		try {
			return syncTrackingPersistence.findByF_ReferenceUid(referenceUid);
		} catch (NoSuchSyncTrackingException e) {
			return null;
		}
	}

	
	public List<SyncTracking> getByApiAndServiceCodeAndDossierNoAndDate(String api,
			String dossierNo, String serviceCode, Date fromDate,
			Date toDate, int start, int end) {
		
		return syncTrackingPersistence.findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
				api, serviceCode, dossierNo, fromDate, toDate, start, end);
	}
	
	public List<SyncTracking> getByApiAndServiceCodeAndDate(String api,
			String serviceCode, Date fromDate,
			Date toDate, int start, int end) {
		
		return syncTrackingPersistence.findByF_API_SERVICECODE_CREATED_BETWEEN(
				api, serviceCode, fromDate, toDate, start, end);
	}
	
	public List<SyncTracking> getByApiAndDossierNoAndDate(String api,
			String dossierNo, Date fromDate,
			Date toDate, int start, int end) {
		
		return syncTrackingPersistence.findByF_API_DOSSIERNO_CREATED_BETWEEN(
				api, dossierNo, fromDate, toDate, start, end);
	}
	
	public List<SyncTracking> getByApiAndDate(String api, Date fromDate,
			Date toDate, int start, int end) {
		try {
			return syncTrackingPersistence.findByF_API_CREATED_BETWEEN(
					api, fromDate, toDate, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}