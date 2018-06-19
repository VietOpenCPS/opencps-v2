package backend.api.rest.application.v21.impl;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.MenuConfigActions;
import org.opencps.dossiermgt.action.impl.MenuConfigActionsImpl;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.api.rest.application.v21.parser.OpenCPSAPIParsing;
import io.swagger.api.MenuConfigApi;
import io.swagger.model.MenuConfigCountItemResults;
import io.swagger.model.MenuConfigItem;
import io.swagger.model.MenuConfigItemResult;

public class MenuConfigApiImpl implements MenuConfigApi {

	@Context
	private User user;

	@Context
	private HttpHeaders header;

	@Context
	HttpServletResponse response;

	ServiceContext serviceContext = new ServiceContext();

	private MenuConfigActions action = new MenuConfigActionsImpl();
	private OpenCPSAPIParsing parsing = new OpenCPSAPIParsing();

	@Override
	public MenuConfigItem addMenuConfig(MenuConfigItem body) {
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		System.out.println("MenuConfigApiImpl.addMenuConfig()" + body);
		try {

			serviceContext.setUserId(userId);
			
			MenuConfig ett = action.addMenuConfig(userId, groupId, body.getMenuGroup(), body.getMenuName(),
					body.getOrder(), body.getMenuType(), body.getQueryParams(), body.getTableConfig(),
					body.getButtonConfig(), serviceContext);

			body = parsing.getModel(ett);

		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public void deleteMenuConfig(String id) {
		try {

			long userId = user.getUserId();
			
			serviceContext.setUserId(userId);
			
			action.deleteMenuConfig(Long.valueOf(id), serviceContext);

		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public MenuConfigItem getMenuConfigByCode(String id) {

		MenuConfig ett = MenuConfigLocalServiceUtil.getByCode(id);

		if (Validator.isNull(ett)) {
			ett = MenuConfigLocalServiceUtil.fetchMenuConfig(Long.valueOf(id));
		}

		return parsing.getModel(ett);
	}

	@Override
	public MenuConfigItem updateMenuConfig(String id, MenuConfigItem body) {
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			serviceContext.setUserId(userId);
			
			MenuConfig ett = action.updateMenuConfig(Long.valueOf(id), userId, groupId, body.getMenuGroup(), body.getMenuName(),
					body.getOrder(), body.getMenuType(), body.getQueryParams(), body.getTableConfig(),
					body.getButtonConfig(), serviceContext);

			body = parsing.getModel(ett);

		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public MenuConfigCountItemResults getMenuConfigsCount(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuConfigItemResult getMenuConfigsElasticsearch(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuConfigItemResult getMenuConfigsTodo(String q) {
		// TODO Auto-generated method stub
		return null;
	}

}
