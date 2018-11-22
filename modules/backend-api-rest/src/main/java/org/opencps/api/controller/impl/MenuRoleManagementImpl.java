package org.opencps.api.controller.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.MenuRoleManagement;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class MenuRoleManagementImpl implements MenuRoleManagement {

	@Override
	public Response autoGenerateId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			
			List<MenuRole> lstMenuRoles = MenuRoleLocalServiceUtil.getMenuRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			for (MenuRole mr : lstMenuRoles) {
				long menuRoleId = CounterLocalServiceUtil.increment(MenuRole.class.getName());
				mr.setMenuRoleId(menuRoleId);
				
				MenuRoleLocalServiceUtil.updateMenuRole(mr);
			}
			
			return Response.status(200).entity(StringPool.BLANK).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getMenuRoles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		return Response.status(200).entity(StringPool.BLANK).build();
	}

}
