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

import aQute.bnd.annotation.ProviderType;

import java.util.Date;

import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.base.DeliverableTypeLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the deliverable type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DeliverableTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DeliverableTypeLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil
 */
@ProviderType
public class DeliverableTypeLocalServiceImpl extends DeliverableTypeLocalServiceBaseImpl {
	
	
	public DeliverableType getByCode(long groupId, String typeCode) {
		return deliverableTypePersistence.fetchByG_DLT(groupId, typeCode);
	}

	public DeliverableType addDeliverableType(long groupId, String deliverableName, String deliverableType_,
			String codePattern, String counter, String formScript, String formReport, String mappingData,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Add DeliverableType
		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		long deliverableTypeId = counterLocalService.increment(DeliverableType.class.getName());

		DeliverableType object = deliverableTypePersistence.create(deliverableTypeId);

		/// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		// Add other fields
		object.setDeliverableTypeId(deliverableTypeId);
		object.setTypeCode(deliverableType_);
		object.setTypeName(deliverableName);
		object.setFormScript(formScript);
		object.setFormReport(formReport);
		object.setMappingData(mappingData);
		object.setCodePattern(codePattern);
		object.setCounter(counter);

		return deliverableTypePersistence.update(object);
	}

	public DeliverableType updateDeliverableType(long groupId, long deliverableTypeId, String deliverableName,
			String deliverableType_, String codePattern, String counter, String formScript, String formReport,
			String mappingData, ServiceContext serviceContext) throws PortalException {
		// TODO Update DeliverableType
		Date now = new Date();

		long userId = serviceContext.getUserId();

		User userAction = userPersistence.fetchByPrimaryKey(userId);

		DeliverableType deliverableTypeObj = null;

		if (deliverableTypeId == 0) {
			deliverableTypeId = counterLocalService.increment(DeliverableType.class.getName());

			deliverableTypeObj = deliverableTypePersistence.create(deliverableTypeId);

			/// Add audit fields
			deliverableTypeObj.setCompanyId(serviceContext.getCompanyId());
			deliverableTypeObj.setGroupId(groupId);
			deliverableTypeObj.setCreateDate(now);
			deliverableTypeObj.setModifiedDate(now);
			deliverableTypeObj.setUserId(userAction.getUserId());
			deliverableTypeObj.setUserName(userAction.getFullName());

			// Add other fields
			deliverableTypeObj.setDeliverableTypeId(deliverableTypeId);
			deliverableTypeObj.setTypeCode(deliverableType_);
			deliverableTypeObj.setTypeName(deliverableName);
			deliverableTypeObj.setFormScript(formScript);
			deliverableTypeObj.setFormReport(formReport);
			deliverableTypeObj.setMappingData(mappingData);
			deliverableTypeObj.setCodePattern(codePattern);
			deliverableTypeObj.setCounter(counter);

		} else {
			deliverableTypeObj = deliverableTypePersistence.fetchByPrimaryKey(deliverableTypeId);

			deliverableTypeObj.setModifiedDate(now);

			deliverableTypeObj.setDeliverableTypeId(deliverableTypeId);
			deliverableTypeObj.setTypeCode(deliverableType_);
			deliverableTypeObj.setTypeName(deliverableName);
			deliverableTypeObj.setFormScript(formScript);
			deliverableTypeObj.setFormReport(formReport);
			deliverableTypeObj.setMappingData(mappingData);
			deliverableTypeObj.setCodePattern(codePattern);
			deliverableTypeObj.setCounter(counter);

		}

		deliverableTypePersistence.update(deliverableTypeObj);

		return deliverableTypeObj;

	}

	public DeliverableType removeDeliverableType(long groupId, String deliverableTypeId) throws PortalException {
		// TODO remove DeliverableType

		validateRemoveDeliverableType(groupId, deliverableTypeId);

		DeliverableType deliverableTypeObj = null;
		long id = Long.valueOf(deliverableTypeId);
		deliverableTypeObj = deliverableTypePersistence.fetchByPrimaryKey(id);
		if (Validator.isNull(deliverableTypeObj)) {
			deliverableTypeObj = deliverableTypePersistence.fetchByG_DLT(groupId, deliverableTypeId);
		}
		return deliverableTypePersistence.remove(deliverableTypeObj);
	}

	public DeliverableType updateFormScript(long groupId, long deliverableTypeId, String formScript,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Update FormScript of DeliverableType

		Date now = new Date();

		DeliverableType deliverableType = deliverableTypePersistence.findByPrimaryKey(deliverableTypeId);

		deliverableType.setFormScript(formScript);
		deliverableType.setModifiedDate(now);

		return deliverableTypePersistence.update(deliverableType);
	}

	public DeliverableType updateFormReport(long groupId, long deliverableTypeId, String formReport,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Update FormReport of DeliverableType

		Date now = new Date();

		DeliverableType deliverableType = deliverableTypePersistence.findByPrimaryKey(deliverableTypeId);

		deliverableType.setFormReport(formReport);
		deliverableType.setModifiedDate(now);

		return deliverableTypePersistence.update(deliverableType);
	}

	public DeliverableType updateMappingData(long groupId, long deliverableTypeId, String mappingData,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Update FormReport of DeliverableType

		Date now = new Date();

		DeliverableType deliverableType = deliverableTypePersistence.findByPrimaryKey(deliverableTypeId);

		deliverableType.setMappingData(mappingData);
		deliverableType.setModifiedDate(now);

		return deliverableTypePersistence.update(deliverableType);
	}

	public DeliverableType getDeliverableTypebyId(long groupId, String deliverableTypeId) throws PortalException {
		// TODO remove DeliverableType

		// validateRemoveDeliverableType(groupId, deliverableTypeId,
		// deliverableType_);

		DeliverableType deliverableTypeObj = null;

		long id = Long.valueOf(deliverableTypeId);
		deliverableTypeObj = deliverableTypePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(deliverableTypeObj)) {
			deliverableTypeObj = deliverableTypePersistence.fetchByG_DLT(groupId, deliverableTypeId);
		}

		return deliverableTypeObj;

	}

	//LamTV_ Process output DeliverableType to DB
	public DeliverableType updateDeliverableTypeDB(long userId, long groupId, String typeCode, String typeName, String codePattern,
			Integer docSync, String mappingData, String govAgencies, String formReport, String formScript) {

		Date now = new Date();
		User userAction = userPersistence.fetchByPrimaryKey(userId);

		DeliverableType object = deliverableTypePersistence.fetchByG_DLT(groupId, typeCode);

		if (object == null) {
			long deliverableTypeId = counterLocalService.increment(DeliverableType.class.getName());

			object = deliverableTypePersistence.create(deliverableTypeId);

			/// Add audit fields
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields
			object.setDeliverableTypeId(deliverableTypeId);
			object.setTypeCode(typeCode);
			object.setTypeName(typeName);
			object.setFormScript(formScript);
			object.setFormReport(formReport);
			object.setMappingData(mappingData);
			object.setCodePattern(codePattern);
			object.setDocSync(docSync);
			object.setGovAgencies(govAgencies);

		} else {
			object.setModifiedDate(now);

			if (Validator.isNotNull(typeCode)) {
				object.setTypeCode(typeCode);
			}
			if (Validator.isNotNull(typeName)) {
				object.setTypeName(typeName);
			}
			if (Validator.isNotNull(formScript)) {
				object.setFormScript(formScript);
			}
			if (Validator.isNotNull(formReport)) {
				object.setFormReport(formReport);
			}
			if (Validator.isNotNull(mappingData)) {
				object.setMappingData(mappingData);
			}
			if (Validator.isNotNull(codePattern)) {
				object.setCodePattern(codePattern);
			}
			if (Validator.isNotNull(docSync)) {
				object.setDocSync(docSync);
			}
//			if (Validator.isNotNull(fieldConfigs)) {
//				object.setFieldConfigs(fieldConfigs);
//			}
		}

		return deliverableTypePersistence.update(object);

	}

	private void validateRemoveDeliverableType(long groupId, String deliverableTypeId) {
		// TODO Auto-generated method stub

	}
}