package org.opencps.dossiermgt.action;

import javax.naming.AuthenticationException;

import org.opencps.dossiermgt.model.MenuConfig;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface MenuConfigActions {

	public MenuConfig addMenuConfig(long userId, long groupId, String menuGroup, String menuName, Integer order,
			Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon,
			ServiceContext serviceContext) throws PortalException, AuthenticationException;

	public MenuConfig updateMenuConfig(long actionCodePK, long userId, long groupId, String menuGroup, String menuName,
			Integer order, Integer menuType, String queryParams, String tableConfig, String buttonConfig, String icon,
			ServiceContext serviceContext) throws PortalException, AuthenticationException;

	public void deleteMenuConfig(Long menuConfigId, ServiceContext serviceContext)
			throws PortalException, AuthenticationException;

	public long updateMenuConfigDB(long userId, long groupId, String menuGroup, String menuName, Integer order, Integer menuType,
			String queryParams, String tableConfig, String buttonConfig, String icon) throws PortalException;

	public boolean deleteAllMenuConfig(long groupId, long userId, ServiceContext serviceContext);

	public void updateMenuRoles(long groupId, long menuConfigId, String roles);
}
