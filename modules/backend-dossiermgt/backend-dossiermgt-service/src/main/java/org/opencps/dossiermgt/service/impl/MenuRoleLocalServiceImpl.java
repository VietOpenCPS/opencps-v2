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

import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.base.MenuRoleLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.MenuRolePK;

import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the menu role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.MenuRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
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
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil} to access the menu role local service.
	 */

	public MenuRole updateMenuRoleDB(long menuConfigId, long roleId) {
		MenuRolePK pk = new MenuRolePK(menuConfigId, roleId);
		MenuRole menuRole = menuRolePersistence.fetchByPrimaryKey(pk);
		if (Validator.isNull(menuRole)) {
			menuRole = menuRolePersistence.create(pk);
		}

		return menuRolePersistence.update(menuRole);
	}
}