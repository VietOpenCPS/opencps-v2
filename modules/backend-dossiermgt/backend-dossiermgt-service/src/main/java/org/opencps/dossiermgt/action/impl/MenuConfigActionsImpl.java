package org.opencps.dossiermgt.action.impl;

import java.util.List;

import javax.naming.AuthenticationException;

import org.opencps.dossiermgt.action.MenuConfigActions;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

import backend.auth.api.BackendAuthImpl;

public class MenuConfigActionsImpl implements MenuConfigActions {

	Log _log = LogFactoryUtil.getLog(MenuConfigActionsImpl.class);

	@Override
	public MenuConfig addMenuConfig(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig, ServiceContext serviceContext)
			throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			MenuConfig object = null;

			object = MenuConfigLocalServiceUtil.addMenuConfig(userId, groupId, menuGroup, menuName, order, menuType,
					queryParams, tableConfig, buttonConfig);

			return object;
		} else {
			throw new AuthenticationException();
		}

	}

	@Override
	public MenuConfig updateMenuConfig(long actionCodePK, long userId, long groupId, String menuGroup, String menuName,
			Integer order, Integer menuType, String queryParams, String tableConfig, String buttonConfig,
			ServiceContext serviceContext) throws PortalException, AuthenticationException {

		BackendAuthImpl authImpl = new BackendAuthImpl();

		if (authImpl.hasResource(serviceContext, StringPool.BLANK, StringPool.BLANK)) {
			MenuConfig object = null;

			object = MenuConfigLocalServiceUtil.updateMenuConfig(actionCodePK, userId, groupId, menuGroup, menuName,
					order, menuType, queryParams, tableConfig, buttonConfig);

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
	public MenuConfig updateMenuConfigDB(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig) {

		return MenuConfigLocalServiceUtil.updateMenuConfigDB(userId, groupId, menuGroup, menuName, order,
				menuType, queryParams, tableConfig, buttonConfig);
	}

	//LamTV_ Process all list MenuConfig
	@Override
	public boolean deleteAllMenuConfig(long groupId, long userId, ServiceContext serviceContext) {
		boolean flag = false;
		List<MenuConfig> menuList = MenuConfigLocalServiceUtil.getMenuConfigs(-1, -1);
		if (menuList != null && menuList.size() > 0) {
			for (MenuConfig menuConfig : menuList) {
				MenuConfigLocalServiceUtil.deleteMenuConfig(menuConfig);
				flag = true;
			}
		}
		return flag;
	}

}
