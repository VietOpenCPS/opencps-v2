package backend.api.rest.application.v21.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.ActionConfigActions;
import org.opencps.dossiermgt.action.impl.ActionConfigActionsImpl;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.rest.application.api.ActionConfigApi;
import org.opencps.rest.application.model.ActionConfigItem;
import org.opencps.rest.application.model.ActionConfigItemResult;

import backend.api.rest.application.v21.parser.OpenCPSAPIParsing;

public class ActionConfigApiImpl implements ActionConfigApi {
	private static Log _log = LogFactoryUtil.getLog(ActionConfigApiImpl.class);
	@Context
	private User user;

	@Context
	private HttpHeaders header;

	@Context
	HttpServletResponse response;

	ServiceContext serviceContext = new ServiceContext();
	
	private ActionConfigActions action = new ActionConfigActionsImpl();
	private OpenCPSAPIParsing parsing = new OpenCPSAPIParsing();

	@Override
	public ActionConfigItem addActionConfig(ActionConfigItem body) {
		_log.info("==== START Add Query==== ");
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			
			serviceContext.setUserId(userId);
			
			ActionConfig ett = action.addActionConfig(userId, groupId, body.getActionCode(),
					body.getActionName(), body.getExtraForm(), body.getFormScript(),
					body.getSampleData(), body.getInsideProcess(),
					body.getUserNote(), body.getSyncType(), body.getPending(),
					body.getRollbackable(), body.getNotificationType(),
					body.getDocumentType(), body.getMappingAction(), serviceContext);

			body = parsing.getModel(ett);
			_log.info("==== END Add Query==== ");
		} catch (PortalException e) {
			_log.debug(e);
			_log.info("====PortalException==== ");
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			_log.debug(e);
			_log.info("====AuthenticationException==== ");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public void deleteActionConfig(String actionConfigId) {
		_log.info("==== START DELETE ACTIONCONFIG==== ");
		try {

			long userId = user.getUserId();
			
			serviceContext.setUserId(userId);
			
			action.deleteActionConfig(Long.valueOf(actionConfigId), serviceContext);
			_log.info("==== END DELETE ACTIONCONFIG==== ");
		} catch (PortalException e) {
			_log.debug(e);
			_log.info("====PortalException==== ");
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			_log.debug(e);
			_log.info("====AuthenticationException==== ");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public ActionConfigItem updateActionConfig(String id, ActionConfigItem body) {
		_log.info("==== START UPDATE ACTIONCONFIG==== ");
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			serviceContext.setUserId(userId);
			
			ActionConfig ett = action.updateActionConfig(Long.valueOf(id), userId, groupId, body.getActionCode(),
					body.getActionName(), body.getExtraForm(), body.getFormScript(),
					body.getSampleData(), body.getInsideProcess(),
					body.getUserNote(), body.getSyncType(), body.getPending(),
					body.getRollbackable(), body.getNotificationType(),
					body.getDocumentType(), body.getMappingAction(), serviceContext);

			body = parsing.getModel(ett);
			_log.info("==== END DELETE ACTIONCONFIG==== ");
		} catch (PortalException e) {
			_log.debug(e);
			_log.info("====PortalException==== ");
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			_log.debug(e);
			_log.info("====AuthenticationException==== ");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public ActionConfigItem getActionConfigByCode(String actionCode) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		ActionConfig ett = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);

		if (Validator.isNull(ett)) {
			ett = ActionConfigLocalServiceUtil.fetchActionConfig(Long.valueOf(actionCode));
		}
		return parsing.getModel(ett);
	}

	@Override
	public ActionConfigItemResult getActionConfigsElasticsearch(String q) {
		// TODO Auto-generated method stub
		System.out.println("ActionConfigApiImpl.getActionConfigsElasticsearch()");
		return null;
	}

}
