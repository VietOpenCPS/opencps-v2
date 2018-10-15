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

import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.service.persistence.DossierSyncPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author huymq
 * @generated
 */
public class DossierSyncFinderBaseImpl extends BasePersistenceImpl<DossierSync> {
	public DossierSyncFinderBaseImpl() {
		setModelClass(DossierSync.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("state", "state_");

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
		return getDossierSyncPersistence().getBadColumnNames();
	}

	/**
	 * Returns the dossier sync persistence.
	 *
	 * @return the dossier sync persistence
	 */
	public DossierSyncPersistence getDossierSyncPersistence() {
		return dossierSyncPersistence;
	}

	/**
	 * Sets the dossier sync persistence.
	 *
	 * @param dossierSyncPersistence the dossier sync persistence
	 */
	public void setDossierSyncPersistence(
		DossierSyncPersistence dossierSyncPersistence) {
		this.dossierSyncPersistence = dossierSyncPersistence;
	}

	@BeanReference(type = DossierSyncPersistence.class)
	protected DossierSyncPersistence dossierSyncPersistence;
	private static final Log _log = LogFactoryUtil.getLog(DossierSyncFinderBaseImpl.class);
}