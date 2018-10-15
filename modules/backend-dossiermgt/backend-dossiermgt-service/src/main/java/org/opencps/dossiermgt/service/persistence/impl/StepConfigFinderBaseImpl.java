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

package org.opencps.dossiermgt.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.persistence.StepConfigPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author huymq
 * @generated
 */
public class StepConfigFinderBaseImpl extends BasePersistenceImpl<StepConfig> {
	public StepConfigFinderBaseImpl() {
		setModelClass(StepConfig.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getStepConfigPersistence().getBadColumnNames();
	}

	/**
	 * Returns the step config persistence.
	 *
	 * @return the step config persistence
	 */
	public StepConfigPersistence getStepConfigPersistence() {
		return stepConfigPersistence;
	}

	/**
	 * Sets the step config persistence.
	 *
	 * @param stepConfigPersistence the step config persistence
	 */
	public void setStepConfigPersistence(
		StepConfigPersistence stepConfigPersistence) {
		this.stepConfigPersistence = stepConfigPersistence;
	}

	@BeanReference(type = StepConfigPersistence.class)
	protected StepConfigPersistence stepConfigPersistence;
	private static final Log _log = LogFactoryUtil.getLog(StepConfigFinderBaseImpl.class);
}