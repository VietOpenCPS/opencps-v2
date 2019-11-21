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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.ProcessOptionTerm;
import org.opencps.dossiermgt.model.ProcessPlugin;
import org.opencps.dossiermgt.service.base.ProcessPluginLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the process plugin local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ProcessPluginLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ProcessPluginLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ProcessPluginLocalServiceUtil
 */
@ProviderType
public class ProcessPluginLocalServiceImpl extends ProcessPluginLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ProcessPluginLocalServiceUtil} to access the
	 * process plugin local service.
	 */
	public List<ProcessPlugin> getProcessPlugins(long serviceProcessId, String stepCode) {
		return processPluginPersistence.findBySC_SPID(stepCode, serviceProcessId);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ProcessPlugin adminProcessDelete(Long id) {

		ProcessPlugin object = processPluginPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			processPluginPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProcessPlugin adminProcessData(JSONObject objectData) {

		ProcessPlugin object = null;

		if (objectData.getLong(ProcessOptionTerm.PROCESS_PLUGIN_ID) > 0) {

			object = processPluginPersistence.fetchByPrimaryKey(objectData.getLong(ProcessOptionTerm.PROCESS_PLUGIN_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ProcessPlugin.class.getName());

			object = processPluginPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(Field.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(Field.USER_ID));
		object.setUserName(objectData.getString(Field.USER_NAME));

		object.setStepCode(objectData.getString(DossierActionTerm.STEP_CODE));
		object.setServiceProcessId(objectData.getLong(DossierActionTerm.SERVICE_PROCESS_ID));
		object.setPluginName(objectData.getString(ProcessOptionTerm.PLUGIN_NAME));
		object.setPluginType(objectData.getInt(ProcessOptionTerm.PLUGIN_TYPE));
		object.setSequenceNo(objectData.getString(ProcessOptionTerm.SEQUENCE_NO));
		object.setPluginForm(objectData.getString(ProcessOptionTerm.PLUGIN_FORM));
		object.setSampleData(objectData.getString(ProcessOptionTerm.SAMPLE_DATA));
		object.setAutoRun(objectData.getBoolean(ProcessOptionTerm.AUTO_RUN));

		processPluginPersistence.update(object);

		return object;
	}
}