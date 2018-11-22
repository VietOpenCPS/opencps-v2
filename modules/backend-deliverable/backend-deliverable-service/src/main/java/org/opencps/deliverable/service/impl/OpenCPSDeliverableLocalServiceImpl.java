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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.utils.DictCollectionUtils;
import org.opencps.deliverable.model.OpenCPSDeliverable;
import org.opencps.deliverable.service.base.OpenCPSDeliverableLocalServiceBaseImpl;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

/**
 * The implementation of the open cps deliverable local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.deliverable.service.OpenCPSDeliverableLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableLocalServiceBaseImpl
 * @see org.opencps.deliverable.service.OpenCPSDeliverableLocalServiceUtil
 */
public class OpenCPSDeliverableLocalServiceImpl extends OpenCPSDeliverableLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.deliverable.service.OpenCPSDeliverableLocalServiceUtil} to access
	 * the open cps deliverable local service.
	 */

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public OpenCPSDeliverable adminProcessDelete(Long id) {

		OpenCPSDeliverable object = openCPSDeliverablePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			openCPSDeliverablePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
		public OpenCPSDeliverable adminProcessData(JSONObject objectData) {

			OpenCPSDeliverable object = null;

			if (objectData.getLong(ModelKeys.DELIVERABLEID) > 0) {

				object = openCPSDeliverablePersistence.fetchByPrimaryKey(objectData.getLong(ModelKeys.DELIVERABLEID));

				object.setModifiedDate(new Date());

			} else {

				long id = CounterLocalServiceUtil.increment(OpenCPSDeliverable.class.getName());

				object = openCPSDeliverablePersistence.create(id);

				object.setGroupId(objectData.getLong(ModelKeys.GROUPID));
				object.setCompanyId(objectData.getLong(ModelKeys.COMPANYID));
				object.setCreateDate(new Date());

			}

			object.setUserId(objectData.getLong(ModelKeys.USERID));

			object.setDeliverableCode(objectData.getString(ModelKeys.DELIVERABLECODE));
			object.setDeliverableName(objectData.getString(ModelKeys.DELIVERABLENAME));
			object.setDeliverableType(objectData.getString(ModelKeys.DELIVERABLETYPE));
			object.setGovAgencyCode(objectData.getString(ModelKeys.GOVAGENCYCODE));
			
			DictItem govAgencyName = DictCollectionUtils.getDictItemByCode(DataMGTConstants.GOVERNMENT_AGENCY,
					objectData.getString(ModelKeys.GOVAGENCYCODE), objectData.getLong(ModelKeys.GROUPID));

			if (Validator.isNotNull(govAgencyName)) {
				object.setGovAgencyName(govAgencyName.getItemName());
			}
			
			object.setApplicantIdNo(objectData.getString(ModelKeys.APPLICANTIDNO));
			
			Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(objectData.getLong(ModelKeys.GROUPID), objectData.getString(ModelKeys.APPLICANTIDNO));
			
			if (Validator.isNotNull(applicant)) {
				object.setApplicantName(applicant.getApplicantName());
			} else {
				object.setApplicantName(StringPool.BLANK);
			}
			
			object.setSubject(objectData.getString(ModelKeys.SUBJECT));
			object.setFormData(objectData.getString(ModelKeys.FORMDATA));
			object.setFormScriptFileId(objectData.getLong(ModelKeys.FORMSCRIPTFILEID));
			object.setFormReportFileId(objectData.getLong(ModelKeys.FORMREPORTFILEID));
			if (Validator.isNotNull(objectData.getString(ModelKeys.ISSUEDATE))) {
				object.setIssueDate(new Date(objectData.getLong(ModelKeys.ISSUEDATE)));
			}
			if (Validator.isNotNull(objectData.getString(ModelKeys.EXPIREDATE))) {
				object.setExpireDate(new Date(objectData.getLong(ModelKeys.EXPIREDATE)));
			}
			if (Validator.isNotNull(objectData.getString(ModelKeys.REVALIDATE))) {
				object.setRevalidate(new Date(objectData.getLong(ModelKeys.REVALIDATE)));
			}
			
			object.setDeliverableState(objectData.getInt(ModelKeys.DELIVERABLESTATE));
			object.setFileEntryId(objectData.getLong(ModelKeys.FILEENTRYID));
			object.setDocSync(objectData.getInt(ModelKeys.DOCSYNC));
			object.setDossierId(objectData.getLong(ModelKeys.DOSSIERID));
			
			openCPSDeliverablePersistence.update(object);

			return object;
		}

	class ModelKeys {
		public static final String DELIVERABLEID = "deliverableId";
		public static final String GROUPID = "groupId";
		public static final String COMPANYID = "companyId";
		public static final String USERID = "userId";
		public static final String USERNAME = "userName";
		public static final String CREATEDATE = "createDate";
		public static final String MODIFIEDDATE = "modifiedDate";
		public static final String DELIVERABLECODE = "deliverableCode";
		public static final String DELIVERABLENAME = "deliverableName";
		public static final String DELIVERABLETYPE = "deliverableType";
		public static final String GOVAGENCYCODE = "govAgencyCode";
		public static final String GOVAGENCYNAME = "govAgencyName";
		public static final String APPLICANTIDNO = "applicantIdNo";
		public static final String APPLICANTNAME = "applicantName";
		public static final String SUBJECT = "subject";
		public static final String FORMDATA = "formData";
		public static final String FORMSCRIPTFILEID = "formScriptFileId";
		public static final String FORMREPORTFILEID = "formReportFileId";
		public static final String ISSUEDATE = "issueDate";
		public static final String EXPIREDATE = "expireDate";
		public static final String REVALIDATE = "revalidate";
		public static final String DELIVERABLESTATE = "deliverableState";
		public static final String FILEENTRYID = "fileEntryId";
		public static final String DOCSYNC = "docSync";
		public static final String DOSSIERID = "dossierId";
	}
}