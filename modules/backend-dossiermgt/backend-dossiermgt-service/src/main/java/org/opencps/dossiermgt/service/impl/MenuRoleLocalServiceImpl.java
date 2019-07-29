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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.service.base.MenuRoleLocalServiceBaseImpl;

/**
 * The implementation of the menu role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.MenuRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see MenuRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil
 */
public class MenuRoleLocalServiceImpl extends MenuRoleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil} to access the menu
	 * role local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(MenuRoleLocalServiceImpl.class);

	public List<MenuRole> getByRoles(long[] roleIds) {
		return menuRolePersistence.findByF_RID(roleIds);
	}

	public List<MenuRole> getByRoleId(long roleId) {
		return menuRolePersistence.findByF_ROLE(roleId);
	}

	public List<MenuRole> getByMenuConfig(long menuConfigId) {
		return menuRolePersistence.findByF_CONFIG_ID(menuConfigId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public MenuRole updateMenuRoleDB(long menuConfigId, long roleId) {

		long menuRoleId = CounterLocalServiceUtil.increment(MenuRole.class.getName());

		// MenuRolePK pk = new MenuRolePK(menuConfigId, roleId);

		MenuRole object = menuRolePersistence.create(menuRoleId);

		// object.setMenuRoleId(menuRoleId);
		object.setMenuConfigId(menuConfigId);
		object.setRoleId(roleId);

		return menuRolePersistence.update(object);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public MenuRole adminProcessDelete(Long id) {

		MenuRole object = null;
		try {
			object = menuRolePersistence.fetchByPrimaryKey(id);

			if (Validator.isNull(object)) {
				return null;
			} else {
				object = menuRolePersistence.remove(object);
			}
		} catch (Exception e) {
			_log.debug(e);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public MenuRole adminProcessData(JSONObject objectData) {

		MenuRole object = null;

		if (objectData.getLong("menuRoleId") > 0) {

			object = menuRolePersistence.fetchByPrimaryKey(objectData.getLong("menuRoleId"));

		} else {

			long id = CounterLocalServiceUtil.increment(MenuRole.class.getName());

			object = menuRolePersistence.create(id);

		}

		object.setMenuConfigId(objectData.getLong("menuConfigId"));
		object.setRoleId(objectData.getLong("roleId"));

		menuRolePersistence.update(object);

		return object;
	}

	public void deleteAll() {
		menuRolePersistence.removeAll();
	}
}