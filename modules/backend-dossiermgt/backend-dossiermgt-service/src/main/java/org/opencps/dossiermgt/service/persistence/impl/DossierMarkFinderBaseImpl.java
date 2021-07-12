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

import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.service.persistence.DossierMarkPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author huymq
 * @generated
 */
public class DossierMarkFinderBaseImpl extends BasePersistenceImpl<DossierMark> {
	public DossierMarkFinderBaseImpl() {
		setModelClass(DossierMark.class);

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
		return getDossierMarkPersistence().getBadColumnNames();
	}

	/**
	 * Returns the dossier mark persistence.
	 *
	 * @return the dossier mark persistence
	 */
	public DossierMarkPersistence getDossierMarkPersistence() {
		return dossierMarkPersistence;
	}

	/**
	 * Sets the dossier mark persistence.
	 *
	 * @param dossierMarkPersistence the dossier mark persistence
	 */
	public void setDossierMarkPersistence(
		DossierMarkPersistence dossierMarkPersistence) {
		this.dossierMarkPersistence = dossierMarkPersistence;
	}

	@BeanReference(type = DossierMarkPersistence.class)
	protected DossierMarkPersistence dossierMarkPersistence;
	private static final Log _log = LogFactoryUtil.getLog(DossierMarkFinderBaseImpl.class);
}