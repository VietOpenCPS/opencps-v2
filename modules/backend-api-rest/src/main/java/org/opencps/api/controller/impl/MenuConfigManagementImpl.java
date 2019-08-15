package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.MenuConfigManagement;
import org.opencps.dossiermgt.constants.MenuConfigTerm;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class MenuConfigManagementImpl implements MenuConfigManagement {
	private static final Log _log = LogFactoryUtil.getLog(MenuConfigManagementImpl.class);

	@Override
	public Response getMenuConfigDetail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, String menuGroup, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			MenuConfig menuConfig = MenuConfigLocalServiceUtil.getByG_MENU(groupId, menuGroup);
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			if (menuConfig != null) {
				jsonObj.put(MenuConfigTerm.MENU_GROUP, menuConfig.getMenuGroup());
				jsonObj.put(MenuConfigTerm.BUTTON_CONFIG, menuConfig.getButtonConfig());
				jsonObj.put(MenuConfigTerm.MENU_NAME, menuConfig.getMenuName());
				jsonObj.put(MenuConfigTerm.MENU_TYPE, menuConfig.getMenuType());
				jsonObj.put(MenuConfigTerm.ORDER, menuConfig.getOrder());
				jsonObj.put(MenuConfigTerm.QUERY_PARAMS, menuConfig.getQueryParams());
				jsonObj.put(MenuConfigTerm.TABLE_CONFIG, menuConfig.getTableConfig());
				jsonObj.put(MenuConfigTerm.VIEW_SCRIPT, menuConfig.getViewScript());
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObj.toJSONString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
