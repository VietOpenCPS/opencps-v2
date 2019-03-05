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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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

import backend.deliverable.service.util.ModelKeysDeliverable;

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

		if (objectData.getLong(ModelKeysDeliverable.DELIVERABLEID) > 0) {

			object = openCPSDeliverablePersistence
					.fetchByPrimaryKey(objectData.getLong(ModelKeysDeliverable.DELIVERABLEID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(OpenCPSDeliverable.class.getName());

			object = openCPSDeliverablePersistence.create(id);

			object.setGroupId(objectData.getLong(ModelKeysDeliverable.GROUPID));
			object.setCompanyId(objectData.getLong(ModelKeysDeliverable.COMPANYID));
			object.setCreateDate(new Date());

		}

		long userId = objectData.getLong(ModelKeysDeliverable.USERID);
		
		object.setUserId(objectData.getLong(ModelKeysDeliverable.USERID));

		if (userId > 0) {
			User user = UserLocalServiceUtil.fetchUser(userId);
			object.setUserName(user.getFullName());
		}
		
		object.setDeliverableCode(objectData.getString(ModelKeysDeliverable.DELIVERABLECODE));
		object.setDeliverableName(objectData.getString(ModelKeysDeliverable.DELIVERABLENAME));
		object.setDeliverableType(objectData.getString(ModelKeysDeliverable.DELIVERABLETYPE));
		object.setGovAgencyCode(objectData.getString(ModelKeysDeliverable.GOVAGENCYCODE));

		DictItem govAgencyName = DictCollectionUtils.getDictItemByCode(DataMGTConstants.GOVERNMENT_AGENCY,
				objectData.getString(ModelKeysDeliverable.GOVAGENCYCODE),
				objectData.getLong(ModelKeysDeliverable.GROUPID));

		if (Validator.isNotNull(govAgencyName)) {
			object.setGovAgencyName(govAgencyName.getItemName());
		}

		object.setApplicantIdNo(objectData.getString(ModelKeysDeliverable.APPLICANTIDNO));
		object.setApplicantName(objectData.getString(ModelKeysDeliverable.APPLICANTNAME));

//		Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(
//				objectData.getLong(ModelKeysDeliverable.GROUPID),
//				objectData.getString(ModelKeysDeliverable.APPLICANTIDNO));
//
//		if (Validator.isNotNull(applicant)) {
//			object.setApplicantName(applicant.getApplicantName());
//		} else {
//			object.setApplicantName(StringPool.BLANK);
//		}

		object.setSubject(objectData.getString(ModelKeysDeliverable.SUBJECT));
		object.setFormData(objectData.getString(ModelKeysDeliverable.FORMDATA));
		object.setFormScriptFileId(objectData.getLong(ModelKeysDeliverable.FORMSCRIPTFILEID));
		object.setFormReportFileId(objectData.getLong(ModelKeysDeliverable.FORMREPORTFILEID));
		if (Validator.isNotNull(objectData.getString(ModelKeysDeliverable.ISSUEDATE))) {
			object.setIssueDate(new Date(objectData.getLong(ModelKeysDeliverable.ISSUEDATE)));
		}
		if (Validator.isNotNull(objectData.getString(ModelKeysDeliverable.EXPIREDATE))) {
			object.setExpireDate(new Date(objectData.getLong(ModelKeysDeliverable.EXPIREDATE)));
		}
		if (Validator.isNotNull(objectData.getString(ModelKeysDeliverable.REVALIDATE))) {
			object.setRevalidate(new Date(objectData.getLong(ModelKeysDeliverable.REVALIDATE)));
		}
		//System.out.println("OpenCPSDeliverableLocalServiceImpl.adminProcessData()" + objectData);
		
		//System.out.println("OpenCPSDeliverableLocalServiceImpl.adminProcessData()" + objectData.getInt(ModelKeysDeliverable.DELIVERABLESTATE));
		object.setDeliverableState(objectData.getInt(ModelKeysDeliverable.DELIVERABLESTATE));
		object.setFileEntryId(objectData.getLong(ModelKeysDeliverable.FILEENTRYID));
		object.setDocSync(objectData.getInt(ModelKeysDeliverable.DOCSYNC));
		object.setDossierId(objectData.getLong(ModelKeysDeliverable.DOSSIERID));

		openCPSDeliverablePersistence.update(object);

		return object;
	}

}