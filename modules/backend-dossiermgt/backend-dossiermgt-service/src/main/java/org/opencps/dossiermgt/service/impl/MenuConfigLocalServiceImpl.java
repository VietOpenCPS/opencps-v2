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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.constants.MenuConfigTerm;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.service.base.MenuConfigLocalServiceBaseImpl;

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
	 * org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil} to access the menu
	 * config local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig addMenuConfig(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon) throws PortalException {

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
		object.setIcon(icon);

		menuConfigPersistence.update(object);

		return object;

	}

	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig updateMenuConfig(long menuConfigId, long userId, long groupId, String menuGroup, String menuName,
			Integer order, Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon)
			throws PortalException {

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		MenuConfig object = menuConfigPersistence.findByPrimaryKey(menuConfigId);

		object.setUserId(user.getUserId());
		object.setModifiedDate(now);

		if (menuGroup != null) {
			object.setMenuGroup(menuGroup);
		}
		if (menuName != null) {
			object.setMenuName(menuName);
		}
		if (order != null) {
			object.setOrder(order);
		}
		if (menuType != null) {
			object.setMenuType(menuType);
		}
		if (queryParams != null) {
			object.setQueryParams(queryParams);
		}
		if (tableConfig != null) {
			object.setTableConfig(tableConfig);
		}
		if (buttonConfig != null) {
			object.setButtonConfig(buttonConfig);
		}
		if (icon != null) {
			object.setIcon(icon);
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

	// LamTV_ Process ouput MenuConfig to DB
	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig updateMenuConfigDB(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon)
			throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		MenuConfig object = menuConfigPersistence.fetchByF_BY_G_MENU(groupId, menuGroup);

		if (object == null) {
			long menuConfigId = counterLocalService.increment(MenuConfig.class.getName());
			object = menuConfigPersistence.create(menuConfigId);

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
			object.setIcon(icon);
		} else {
			object.setUserId(user.getUserId());
			object.setModifiedDate(new Date());
			object.setMenuName(menuName);
			object.setOrder(order);
			object.setMenuType(menuType);
			object.setQueryParams(queryParams);
			object.setTableConfig(tableConfig);
			object.setButtonConfig(buttonConfig);
			object.setIcon(icon);
		}

		return menuConfigPersistence.update(object);
	}

	public List<MenuConfig> getByGroupId(long groupId) {
		return menuConfigPersistence.findByF_BY_GID(groupId);
	}

	public List<MenuConfig> getByMenus(long[] menuConfigIds) {
		return menuConfigPersistence.findByF_A_MID(menuConfigIds);
	}

	public MenuConfig getByG_MENU(long groupId, String menuGroup) {
		return menuConfigPersistence.fetchByF_BY_G_MENU(groupId, menuGroup);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public MenuConfig adminProcessDelete(Long id) {

		MenuConfig object = menuConfigPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			menuConfigPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig adminProcessData(JSONObject objectData) {

		MenuConfig object = null;

		if (objectData.getLong(MenuConfigTerm.MENU_CONFIG_ID) > 0) {

			object = menuConfigPersistence.fetchByPrimaryKey(objectData.getLong(MenuConfigTerm.MENU_CONFIG_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(MenuConfig.class.getName());

			object = menuConfigPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(Field.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(Field.USER_ID));

		object.setMenuGroup(objectData.getString(MenuConfigTerm.MENU_GROUP));
		object.setMenuName(objectData.getString(MenuConfigTerm.MENU_NAME));
		object.setOrder(objectData.getInt(MenuConfigTerm.ORDER));
		object.setMenuType(objectData.getInt(MenuConfigTerm.MENU_TYPE));
		object.setQueryParams(objectData.getString(MenuConfigTerm.QUERY_PARAMS));
		object.setTableConfig(objectData.getString(MenuConfigTerm.TABLE_CONFIG));
		object.setButtonConfig(objectData.getString(MenuConfigTerm.BUTTON_CONFIG));
		object.setIcon(objectData.getString(MenuConfigTerm.ICON));
		object.setViewScript(objectData.getString(MenuConfigTerm.VIEW_SCRIPT));

		menuConfigPersistence.update(object);

		return object;
	}
}