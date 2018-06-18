package backend.api.rest.application.v21.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.ActionConfigActions;
import org.opencps.dossiermgt.action.impl.ActionConfigActionsImpl;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.api.rest.application.v21.parser.ActionConfigParsing;
import io.swagger.api.ActionConfigApi;
import io.swagger.model.ActionConfigItem;

public class ActionConfigApiImpl implements ActionConfigApi {

	@Context
	private User user;

	@Context
	private HttpHeaders header;

	@Context
	HttpServletResponse response;

	private ActionConfigActions action = new ActionConfigActionsImpl();
	private ActionConfigParsing parsing = new ActionConfigParsing();

	@Override
	public ActionConfigItem addActionConfig(ActionConfigItem actionConfigItem) {

		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		System.out.println("ActionConfigApiImpl.addActionConfig()" + actionConfigItem);
		try {
			ActionConfig ett = action.addActionConfig(userId, groupId, actionConfigItem.getActionCode(), actionConfigItem.getActionName(),
					actionConfigItem.getExtraForm(), actionConfigItem.getFormScript(), actionConfigItem.getSampleData(),
					actionConfigItem.getInsideProcess(), actionConfigItem.getSyncType(), actionConfigItem.getPending(),
					actionConfigItem.getNotificationType(), actionConfigItem.getCreateDocument(),
					actionConfigItem.getDocumentName(), actionConfigItem.getDocumentScript(),
					actionConfigItem.getDocumentCode(), actionConfigItem.getSendDocument());
			
			actionConfigItem = parsing.getModel(ett);
			
		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}

		return actionConfigItem;
	}

	@Override
	public void deleteActionConfig(String actionCode) {
		try {
			
			ActionConfig object = ActionConfigLocalServiceUtil.getByCode(actionCode);
			
			action.deleteActionConfig(object.getActionConfigId());
			
		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	public List<ActionConfigItem> getActionConfigs(String q) {
		// TODO Auto-generated method stub
		List<ActionConfigItem> results = new ArrayList<>();

		ActionConfigItem object = new ActionConfigItem();
		
		List<ActionConfig> actionConfigs = ActionConfigLocalServiceUtil.getActionConfigs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		for (ActionConfig ett : actionConfigs) {
			results.add(parsing.getModel(ett));
		}

		return results;
	}

	@Override
	public ActionConfigItem updateActionConfig(String actionCode, ActionConfigItem actionConfigItem) {
		
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			
			ActionConfig ett = action.updateActionConfig(actionCode, userId, groupId, actionConfigItem.getActionCode(), actionConfigItem.getActionName(),
					actionConfigItem.getExtraForm(), actionConfigItem.getFormScript(), actionConfigItem.getSampleData(),
					actionConfigItem.getInsideProcess(), actionConfigItem.getSyncType(), actionConfigItem.getPending(),
					actionConfigItem.getNotificationType(), actionConfigItem.getCreateDocument(),
					actionConfigItem.getDocumentName(), actionConfigItem.getDocumentScript(),
					actionConfigItem.getDocumentCode(), actionConfigItem.getSendDocument());
			
			actionConfigItem = parsing.getModel(ett);
			
		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		
		return actionConfigItem;
	}

	@Override
	public ActionConfigItem getActionConfigByCode(String actionCode) {
		
		ActionConfig ett = ActionConfigLocalServiceUtil.getByCode(actionCode);
		
		return parsing.getModel(ett);
	}

}
