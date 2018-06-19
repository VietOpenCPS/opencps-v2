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

import java.util.Date;

import org.opencps.dossiermgt.exception.DuplicateActionCodeException;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.service.base.MenuConfigLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the menu config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.MenuConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see MenuConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil
 */
public class MenuConfigLocalServiceImpl extends MenuConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil} to access the
	 * menu config local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig addMenuConfig(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig) throws PortalException {

		validate(menuGroup, 0);
		
		User user = userLocalService.getUser(userId);

		Date now = new Date();

		long menuConfigId = counterLocalService.increment(MenuConfig.class.getName());

		MenuConfig object = menuConfigPersistence.create(menuConfigId);

		object.setGroupId(groupId);
		object.setCompanyId(user.getCompanyId());
		object.setUserId(user.getUserId());
		object.setCreateDate(now);
		object.setModifiedDate(now);

		object.setMenuGroup(menuGroup);
		object.setMenuName(menuName);
		object.setOrder(Validator.isNotNull(order) ? order : 0);
		object.setMenuType(Validator.isNotNull(menuType) ? menuType : 0);
		object.setQueryParams(queryParams);
		object.setMenuGroup(menuGroup);
		object.setTableConfig(tableConfig);
		object.setButtonConfig(buttonConfig);

		menuConfigPersistence.update(object);

		return object;

	}

	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig updateMenuConfig(long menuConfigId, long userId, long groupId, String menuGroup, String menuName,
			Integer order, Integer menuType, String queryParams, String tableConfig, String buttonConfig)
			throws PortalException {

		validate(menuGroup, menuConfigId);
		
		User user = userLocalService.getUser(userId);

		Date now = new Date();

		MenuConfig object = menuConfigPersistence.findByPrimaryKey(menuConfigId);

		object.setUserId(user.getUserId());
		object.setModifiedDate(now);

		if (Validator.isNotNull(menuGroup)) {
			object.setMenuGroup(menuGroup);
		}
		if (Validator.isNotNull(menuName)) {
			object.setMenuName(menuName);
		}
		if (Validator.isNotNull(order)) {
			object.setOrder(order);
		}
		if (Validator.isNotNull(menuType)) {
			object.setMenuType(menuType);
		}
		if (Validator.isNotNull(queryParams)) {
			object.setQueryParams(queryParams);
		}
		if (Validator.isNotNull(tableConfig)) {
			object.setTableConfig(tableConfig);
		}
		if (Validator.isNotNull(buttonConfig)) {
			object.setButtonConfig(buttonConfig);
		}

		menuConfigPersistence.update(object);

		return object;

	}

	@Indexable(type = IndexableType.DELETE)
	public MenuConfig removeMenuConfig(long menuConfigId) throws PortalException {
		MenuConfig object = menuConfigPersistence.findByPrimaryKey(menuConfigId);

		object = menuConfigPersistence.remove(object);

		return object;
	}

	public MenuConfig getByCode(String menuGroup) {

		return menuConfigPersistence.fetchByF_BY_menuGroup(menuGroup);

	}

	private void validate(String menuGroup, long menuConfigId) throws PortalException {

		MenuConfig menuConfig = menuConfigPersistence.fetchByF_BY_menuGroup(menuGroup);

		if (Validator.isNull(menuGroup)) {
			throw new DuplicateActionCodeException("DuplicateStepCodeException");
		}

		if (Validator.isNotNull(menuConfig) && menuConfigId == 0) {
			throw new DuplicateActionCodeException("DuplicateStepCodeException");
		}
		
		if (Validator.isNotNull(menuConfig) && menuConfig.getMenuConfigId() != menuConfigId) {
			throw new DuplicateActionCodeException("DuplicateStepCodeException");
		}

	}
}