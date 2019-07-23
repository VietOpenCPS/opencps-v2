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

import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.base.DeliverableTypeLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

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
			String codePattern, long counter, String formScript, String formReport, String mappingData,
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
			String deliverableType_, String codePattern, long counter, String formScript, String formReport,
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

	// LamTV_ Process output DeliverableType to DB
	public DeliverableType updateDeliverableTypeDB(long userId, long groupId, String typeCode, String typeName,
			String codePattern, Integer docSync, String mappingData, String govAgencies, String formReport,
			String formScript, String dataConfig, String tableConfig, long reportFileEntryId, long scriptFileEntryId) {

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
			object.setDataConfig(dataConfig);
			object.setTableConfig(tableConfig);
			object.setCodePattern(codePattern);
			object.setDocSync(docSync);
			object.setGovAgencies(govAgencies);
			object.setFormReportFileId(reportFileEntryId);
			object.setFormScriptFileId(scriptFileEntryId);

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
			if (Validator.isNotNull(govAgencies)) {
				object.setGovAgencies(govAgencies);
			}
			if (Validator.isNotNull(dataConfig)) {
				object.setDataConfig(dataConfig);
			}
			if (Validator.isNotNull(tableConfig)) {
				object.setTableConfig(tableConfig);
			}
			if (reportFileEntryId > 0)
				object.setFormReportFileId(reportFileEntryId);
			if (scriptFileEntryId > 0)
				object.setFormScriptFileId(scriptFileEntryId);
		}

		return deliverableTypePersistence.update(object);

	}

	private void validateRemoveDeliverableType(long groupId, String deliverableTypeId) {
		// TODO Auto-generated method stub

	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DeliverableType adminProcessDelete(Long id) {

		DeliverableType object = deliverableTypePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			deliverableTypePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DeliverableType adminProcessData(JSONObject objectData) {

		DeliverableType object = null;

		if (objectData.getLong("deliverableTypeId") > 0) {

			object = deliverableTypePersistence.fetchByPrimaryKey(objectData.getLong("deliverableTypeId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DeliverableType.class.getName());

			object = deliverableTypePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setTypeCode(objectData.getString("typeCode"));
		object.setTypeName(objectData.getString("typeName"));
		object.setFormScript(objectData.getString("formScript"));
		object.setFormReport(objectData.getString("formReport"));
		object.setCodePattern(objectData.getString("codePattern"));
		object.setCounter(objectData.getInt("counter"));
		object.setMappingData(objectData.getString("mappingData"));
		object.setDocSync(objectData.getInt("docSync"));
		object.setGovAgencies(objectData.getString("govAgencies"));

		deliverableTypePersistence.update(object);

		return object;
	}
	
	public List<DeliverableType> findByG(long groupId) {
		return deliverableTypePersistence.findByG(groupId);
	}
	
	public DeliverableType getByTypeCode(String typeCode, long groupId) {
		return deliverableTypePersistence.fetchByG_DLT(groupId, typeCode);
	}
	
	public List<DeliverableType> getDeliverableTypes(long groupId, int start, int end) {
		return deliverableTypePersistence.findByG(groupId, start, end);
	}
	
	class ModelKeys {
		public static final String DELIVERABLETYPEID = "deliverableTypeId";
		public static final String GROUPID = "groupId";
		public static final String COMPANYID = "companyId";
		public static final String USERID = "userId";
		public static final String USERNAME = "userName";
		public static final String CREATEDATE = "createDate";
		public static final String MODIFIEDDATE = "modifiedDate";
		public static final String TYPECODE = "typeCode";
		public static final String TYPENAME = "typeName";
		public static final String FORMSCRIPTFILEID = "formScriptFileId";
		public static final String FORMREPORTFILEID = "formReportFileId";
		public static final String CODEPATTERN = "codePattern";
		public static final String COUNTER = "counter";
		public static final String MAPPINGDATA = "mappingData";
		public static final String DATACONFIG = "dataConfig";
		public static final String TABLECONFIG = "tableConfig";
		public static final String DOCSYNC = "docSync";
		public static final String GOVAGENCIES = "govAgencies";
	}
}