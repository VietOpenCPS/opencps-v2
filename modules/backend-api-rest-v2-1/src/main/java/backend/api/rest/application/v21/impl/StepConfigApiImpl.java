package backend.api.rest.application.v21.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.StepConfigActions;
import org.opencps.dossiermgt.action.impl.StepConfigActionsImpl;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.rest.application.api.StepConfigApi;
import org.opencps.rest.application.model.StepConfigItem;
import org.opencps.rest.application.model.StepConfigItemResult;

import backend.api.rest.application.v21.parser.OpenCPSAPIParsing;

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
	private static Log _log = LogFactoryUtil.getLog(StepConfigApiImpl.class);
	@Override
	public StepConfigItem addStepConfig(StepConfigItem body) {
		_log.debug("====START ADD STEP CONFIG==== ");
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			
			serviceContext.setUserId(userId);
			
			StepConfig ett = action.addStepConfig(userId, groupId, body.getStepCode(), body.getStepName(),
					body.getStepType(), body.getDossierStatus(), body.getDossierSubStatus(), body.getMenuGroup(),
					body.getMenuStepName(), body.getButtonConfig(), serviceContext);

			body = parsing.getModel(ett);
			_log.debug("====START ADD STEP CONFIG==== ");
		} catch (PortalException e) {
			_log.debug(e);
			_log.error("====ADD STEP CONFIG - PortalException==== ");
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			_log.debug(e);
			_log.error("====ADD STEP CONFIG - AuthenticationException==== ");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public void deleteStepConfig(String id) {
		_log.info("====START DELETE STEP CONFIG==== ");
		try {
			
			long userId = user.getUserId();
			serviceContext.setUserId(userId);
			action.deleteStepConfig(Long.valueOf(id), serviceContext);

			_log.info("====END DELETE STEP CONFIG==== ");
		} catch (PortalException e) {
			_log.debug(e);
			_log.error("====ADD MENU CONFIG - PortalException==== ");
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			_log.debug(e);
			_log.error("====ADD MENU CONFIG - AuthenticationException==== ");
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
		_log.info("====START UPDATE STEP CONFIG==== ");
		long userId = user.getUserId();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			
			serviceContext.setUserId(userId);

			StepConfig ett = action.updateStepConfig(Long.valueOf(id), userId, groupId, body.getStepCode(),
					body.getStepName(), body.getStepType(), body.getDossierStatus(), body.getDossierSubStatus(),
					body.getMenuGroup(), body.getMenuStepName(), body.getButtonConfig(), serviceContext);

			body = parsing.getModel(ett);
			_log.info("====END UPDATE STEP CONFIG==== ");
		} catch (PortalException e) {
			_log.debug(e);
			_log.error("====UPDATE MENU CONFIG - PortalException==== ");
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (AuthenticationException e) {
			_log.debug(e);
			_log.error("====UPDATE MENU CONFIG - AuthenticationException==== ");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return body;
	}

	@Override
	public StepConfigItemResult getStepConfigsElasticsearch(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StepConfigItemResult getStepConfigByMainStatusAndSubStatus(String mainStatus, String subStatus) {
		_log.debug("====START GET LIST STEP CONFIG==== ");
		StepConfigItemResult result = new StepConfigItemResult();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		List<StepConfig> lstStepConfigs = StepConfigLocalServiceUtil.getStepByMainStatusAndSubStatus(groupId, mainStatus, subStatus);
		
		if (lstStepConfigs.size() > 0) {
			List<StepConfigItem> lstItems = new ArrayList<>();
			
			result.setTotal((long)lstStepConfigs.size());
			
			for (StepConfig st : lstStepConfigs) {
				StepConfigItem item = new StepConfigItem();
				item.setButtonConfig(st.getButtonConfig());
				item.setCreateDate(String.valueOf(st.getCreateDate().getTime()));
				item.setDossierStatus(st.getDossierStatus());
				item.setDossierSubStatus(st.getDossierSubStatus());
				item.setMenuGroup(st.getMenuGroup());
				item.setMenuStepName(st.getMenuStepName());
				item.setStepCode(st.getStepCode());
				item.setStepName(st.getStepName());
				item.setStepConfigId(st.getStepConfigId());
				item.setStepType(st.getStepType());
				
				lstItems.add(item);
			}
			
			result.getData().addAll(lstItems);
		}
		_log.debug("====END GET LIST STEP CONFIG==== ");
		return result;
	}

}
