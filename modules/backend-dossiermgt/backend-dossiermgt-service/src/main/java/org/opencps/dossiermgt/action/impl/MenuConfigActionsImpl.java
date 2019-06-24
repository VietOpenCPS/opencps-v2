package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.naming.AuthenticationException;

import org.opencps.dossiermgt.action.MenuConfigActions;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.auth.api.BackendAuthImpl;

public class MenuConfigActionsImpl implements MenuConfigActions {

	Log _log = LogFactoryUtil.getLog(MenuConfigActionsImpl.class);

	@Override
	public MenuConfig addMenuConfig(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon,
			ServiceContext serviceContext) throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			MenuConfig object = null;

			object = MenuConfigLocalServiceUtil.addMenuConfig(userId, groupId, menuGroup, menuName, order, menuType,
					queryParams, tableConfig, buttonConfig, icon);

			return object;
		} else {
			throw new AuthenticationException();
		}

	}

	@Override
	public MenuConfig updateMenuConfig(long actionCodePK, long userId, long groupId, String menuGroup, String menuName,
			Integer order, Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon,
			ServiceContext serviceContext) throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			MenuConfig object = null;

			object = MenuConfigLocalServiceUtil.updateMenuConfig(actionCodePK, userId, groupId, menuGroup, menuName,
					order, menuType, queryParams, tableConfig, buttonConfig, icon);

			return object;
		} else {
			throw new AuthenticationException();
		}

	}

	@Override
	public void deleteMenuConfig(Long menuConfigId, ServiceContext serviceContext)
			throws PortalException, AuthenticationException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			MenuConfigLocalServiceUtil.removeMenuConfig(menuConfigId);
		} else {
			throw new AuthenticationException();
		}

	}

	@Override
	public long updateMenuConfigDB(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon)
			throws PortalException {

		MenuConfig menuConfig = MenuConfigLocalServiceUtil.updateMenuConfigDB(userId, groupId, menuGroup, menuName, order,
				menuType, queryParams, tableConfig, buttonConfig, icon);
		if (menuConfig != null) {
			return menuConfig.getMenuConfigId();
		}
		return 0;
	}

	//LamTV_ Process all list MenuConfig
	@Override
	public boolean deleteAllMenuConfig(long groupId, long userId, ServiceContext serviceContext) {
		boolean flag = false;
		List<MenuConfig> menuList = MenuConfigLocalServiceUtil.getByGroupId(groupId);
		if (menuList != null && menuList.size() > 0) {
			for (MenuConfig menuConfig : menuList) {
				MenuConfigLocalServiceUtil.deleteMenuConfig(menuConfig);
				flag = true;
			}
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	public void updateMenuRoles(long groupId, long menuConfigId, String roles) {

		if (Validator.isNotNull(roles)) {
			String[] roleArr = StringUtil.split(roles);
			for (String role: roleArr) {
				JobPos job = JobPosLocalServiceUtil.getByJobCode(groupId, role);
				if (job != null) {
					long roleId = job.getMappingRoleId();
					if (roleId > 0) {
						MenuRoleLocalServiceUtil.updateMenuRoleDB(menuConfigId, roleId);
					}
				}
			}
		}
	}

}
