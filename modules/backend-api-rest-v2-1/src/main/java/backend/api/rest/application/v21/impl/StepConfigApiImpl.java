package backend.api.rest.application.v21.impl;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.StepConfigActions;
import org.opencps.dossiermgt.action.impl.StepConfigActionsImpl;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.api.rest.application.v21.parser.OpenCPSAPIParsing;
import io.swagger.api.StepConfigApi;
import io.swagger.model.StepConfigItem;
import io.swagger.model.StepConfigItemResult;

public class StepConfigApiImpl implements StepConfigApi {

	@Context
	private User user;

	@Context
	private HttpHeaders header;

	@Context
	HttpServletResponse response;

	ServiceContext serviceContext = new ServiceContext();

	private StepConfigActions action = new StepConfigActionsImpl();
	private OpenCPSAPIParsing parsing = new OpenCPSAPIParsing();

	@Override
	public StepConfigItem addStepConfig(StepConfigItem body) {
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		System.out.println("StepConfigApiImpl.addStepConfig()" + body);
		try {
			
			serviceContext.setUserId(userId);
			
			StepConfig ett = action.addStepConfig(userId, groupId, body.getStepCode(), body.getStepName(),
					body.getStepType(), body.getDossierStatus(), body.getDossierSubStatus(), body.getMenuGroup(),
					body.getMenuStepName(), body.getButtonConfig(), serviceContext);

			body = parsing.getModel(ett);

		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public void deleteStepConfig(String id) {
		try {
			
			long userId = user.getUserId();
			
			serviceContext.setUserId(userId);
			
			action.deleteStepConfig(Long.valueOf(id), serviceContext);

		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public StepConfigItem getStepConfigByCode(String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		StepConfig ett = StepConfigLocalServiceUtil.getByCode(groupId, id);
		
		if (Validator.isNull(ett)) {
			ett = StepConfigLocalServiceUtil.fetchStepConfig(Long.valueOf(id));
		}

		return parsing.getModel(ett);
	}

	@Override
	public StepConfigItem updateStepConfig(String id, StepConfigItem body) {
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			
			serviceContext.setUserId(userId);

			StepConfig ett = action.updateStepConfig(Long.valueOf(id), userId, groupId, body.getStepCode(),
					body.getStepName(), body.getStepType(), body.getDossierStatus(), body.getDossierSubStatus(),
					body.getMenuGroup(), body.getMenuStepName(), body.getButtonConfig(), serviceContext);

			body = parsing.getModel(ett);

		} catch (PortalException e) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public StepConfigItemResult getStepConfigsElasticsearch(String q) {
		// TODO Auto-generated method stub
		return null;
	}

}
