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

package org.opencps.dossiermgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.input.model.DossierMarkBatchModel;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;
import org.opencps.dossiermgt.service.base.DossierMarkLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier mark local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DossierMarkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierMarkLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil
 */
@ProviderType
public class DossierMarkLocalServiceImpl extends DossierMarkLocalServiceBaseImpl {
	public DossierMark addDossierMark(long groupId, long dossierId, String dossierPartNo, Integer fileMark,
			Integer fileCheck, String fileComment, String recordCount, ServiceContext serviceContext)
			throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);
		DossierMark object = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId, dossierId,
				dossierPartNo);
		if (object != null) {
			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());

			// Add other fields
			if (Validator.isNotNull(fileCheck)) {
				object.setFileCheck(fileCheck);
			}
			if (Validator.isNotNull(fileMark)) {
				object.setFileMark(fileMark);
			}
			object.setFileComment(fileComment);
			if (Validator.isNotNull(recordCount)) {
				object.setRecordCount(recordCount);
			}
		} else {
			long dossierMarkId = counterLocalService.increment(DossierMark.class.getName());
			object = dossierMarkPersistence.create(dossierMarkId);

			/// Add audit fields
			object.setCompanyId(serviceContext.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());

			// Add other fields
			object.setDossierId(dossierId);
			object.setDossierPartNo(dossierPartNo);
			if (Validator.isNotNull(fileCheck)) {
				object.setFileCheck(fileCheck);
			} else {
				object.setFileCheck(0);
			}
			if (Validator.isNotNull(fileMark)) {
				object.setFileMark(fileMark);
			} else {
				object.setFileMark(0);
			}
			
			object.setFileComment(fileComment);
			object.setRecordCount(recordCount);
		}

		return dossierMarkPersistence.update(object);
	}

	public DossierMark getDossierMarkbyDossierId(long groupId, long dossierId, String dossierPartNo) {

		return dossierMarkPersistence.fetchByG_DID_PN(groupId, dossierId, dossierPartNo);
	}

	public List<DossierMark> getDossierMarks(long groupId, long dossierId) {

		return dossierMarkPersistence.findByG_DID(groupId, dossierId);
	}

	public List<DossierMark> getDossierMarksByFileMark(long groupId, long dossierId, int fileMark) {
		return dossierMarkPersistence.findByG_DID_MARK(groupId, dossierId, fileMark);
	}


	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DossierMark adminProcessDelete(Long id) {

		DossierMark object = dossierMarkPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dossierMarkPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierMark adminProcessData(JSONObject objectData) {

		DossierMark object = null;

		if (objectData.getLong("dossierMarkId") > 0) {

			object = dossierMarkPersistence.fetchByPrimaryKey(objectData.getLong("dossierMarkId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DossierMark.class.getName());

			object = dossierMarkPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setDossierId(objectData.getLong("dossierId"));
		object.setDossierPartNo(objectData.getString("dossierPartNo"));
		object.setFileCheck(objectData.getInt("fileCheck"));
		object.setFileMark(objectData.getInt("fileMark"));
		object.setFileComment(objectData.getString("fileComment"));
		object.setRecordCount(objectData.getString("recordCount"));

		dossierMarkPersistence.update(object);

		return object;
	}
	
	public void addBatchDossierMark(long groupId, DossierMarkBatchModel[] marks, Map<String, DossierMark> mapMarks, ServiceContext serviceContext)
			throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);
		for (int i = 0; i < marks.length; i++) {
//			DossierMark object = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId, marks[i].getDossierId(),
//					marks[i].getDossierPartNo());
			DossierMark object = mapMarks.get(marks[i].getDossierPartNo());
			if (object != null) {
				// Add audit fields
				object.setModifiedDate(now);
				object.setUserId(userAction.getUserId());

				// Add other fields
				if (Validator.isNotNull(marks[i].getFileCheck())) {
					object.setFileCheck(marks[i].getFileCheck());
				}
				if (Validator.isNotNull(marks[i].getFileMark())) {
					object.setFileMark(marks[i].getFileMark());
				}
				object.setFileComment(marks[i].getFileComment());
				if (Validator.isNotNull(marks[i].getRecordCount())) {
					object.setRecordCount(marks[i].getRecordCount());
				}
			} else {
				long dossierMarkId = counterLocalService.increment(DossierMark.class.getName());
				object = dossierMarkPersistence.create(dossierMarkId);

				/// Add audit fields
				object.setCompanyId(serviceContext.getCompanyId());
				object.setGroupId(groupId);
				object.setCreateDate(now);
				object.setModifiedDate(now);
				object.setUserId(userAction.getUserId());

				// Add other fields
				object.setDossierId(marks[i].getDossierId());
				object.setDossierPartNo(marks[i].getDossierPartNo());
				if (Validator.isNotNull(marks[i].getFileCheck())) {
					object.setFileCheck(marks[i].getFileCheck());
				}
				if (Validator.isNotNull(marks[i].getFileMark())) {
					object.setFileMark(marks[i].getFileMark());
				}
				object.setFileComment(marks[i].getFileComment());
				object.setRecordCount(marks[i].getRecordCount());
			}
			
			dossierMarkPersistence.update(object);
		}
	}
 
}