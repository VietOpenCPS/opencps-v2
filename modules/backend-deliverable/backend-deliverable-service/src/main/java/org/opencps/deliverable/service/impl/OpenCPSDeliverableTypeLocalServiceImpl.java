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

package org.opencps.deliverable.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.deliverable.model.OpenCPSDeliverableType;
import org.opencps.deliverable.service.base.OpenCPSDeliverableTypeLocalServiceBaseImpl;

/**
 * The implementation of the open cps deliverable type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.deliverable.service.OpenCPSDeliverableTypeLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeLocalServiceBaseImpl
 * @see org.opencps.deliverable.service.OpenCPSDeliverableTypeLocalServiceUtil
 */
public class OpenCPSDeliverableTypeLocalServiceImpl extends OpenCPSDeliverableTypeLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.deliverable.service.OpenCPSDeliverableTypeLocalServiceUtil} to
	 * access the open cps deliverable type local service.
	 */
	
	// business
	public OpenCPSDeliverableType getByTypeCode(String typeCode) {
		return openCPSDeliverableTypePersistence.fetchByF_typeCode(typeCode);
	}
	
	public List<OpenCPSDeliverableType> getDeliverableTypes(long groupId, int start, int end) {
		return openCPSDeliverableTypePersistence.findByF_groupId(groupId, start, end);
	}
	
	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public OpenCPSDeliverableType adminProcessDelete(Long id) {

		OpenCPSDeliverableType object = openCPSDeliverableTypePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			openCPSDeliverableTypePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public OpenCPSDeliverableType adminProcessData(JSONObject objectData) {

		OpenCPSDeliverableType object = null;

		if (objectData.getLong(ModelKeys.DELIVERABLETYPEID) > 0) {

			object = openCPSDeliverableTypePersistence
					.fetchByPrimaryKey(objectData.getLong(ModelKeys.DELIVERABLETYPEID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(OpenCPSDeliverableType.class.getName());

			object = openCPSDeliverableTypePersistence.create(id);

			object.setGroupId(objectData.getLong(ModelKeys.GROUPID));
			object.setCompanyId(objectData.getLong(ModelKeys.COMPANYID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(ModelKeys.USERID));
		
		
		object.setTypeCode(objectData.getString(ModelKeys.TYPECODE));
		object.setTypeName(objectData.getString(ModelKeys.TYPENAME));
		object.setFormScriptFileId(objectData.getLong(ModelKeys.FORMSCRIPTFILEID));
		object.setFormReportFileId(objectData.getLong(ModelKeys.FORMREPORTFILEID));
		object.setCodePattern(objectData.getString(ModelKeys.CODEPATTERN));
		object.setCounter(objectData.getLong(ModelKeys.COUNTER));
		object.setMappingData(objectData.getString(ModelKeys.MAPPINGDATA));
		object.setDataConfig(objectData.getString(ModelKeys.DATACONFIG));
		object.setTableConfig(objectData.getString(ModelKeys.TABLECONFIG));
		object.setDocSync(objectData.getInt(ModelKeys.DOCSYNC));
		object.setGovAgencies(objectData.getString(ModelKeys.GOVAGENCIES));

		openCPSDeliverableTypePersistence.update(object);

		return object;
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