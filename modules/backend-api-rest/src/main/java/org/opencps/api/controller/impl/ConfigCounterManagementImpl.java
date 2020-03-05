package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.configcounter.model.ConfigCounterDetailModel;
import org.opencps.api.configcounter.model.ConfigCounterInputModel;
import org.opencps.api.configcounter.model.ConfigCounterResultsModel;
import org.opencps.api.configcounter.model.ConfigCounterSearchModel;
import org.opencps.api.controller.ConfigCounterManagement;
import org.opencps.api.controller.util.ConfigCounterUtils;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.ConfigCounterActions;
import org.opencps.dossiermgt.action.impl.ConfigCounterActionsImpl;
import org.opencps.dossiermgt.model.ConfigCounter;
import org.opencps.dossiermgt.service.ConfigCounterLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ConfigCounterManagementImpl implements ConfigCounterManagement{

	private static final Log _log = LogFactoryUtil.getLog(ConfigCounterManagementImpl.class);

	@Override
	public Response getConfigCounterList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ConfigCounterSearchModel query) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
//			if (Validator.isNull(search.getEnd()) || search.getEnd() == 0) {
//				search.setStart(-1);
//				search.setEnd(-1);
//			}

			ConfigCounterResultsModel results = new ConfigCounterResultsModel();

			ConfigCounterActions actions = new ConfigCounterActionsImpl();
			JSONObject jsonData = actions.getConfigCounterList(groupId, 1, 1, serviceContext);

			results.setTotal(jsonData.getInt("total"));

			results.getData().addAll(ConfigCounterUtils.mappingToListConfig((List<ConfigCounter>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addConfigCounter(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ConfigCounterInputModel input) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			//
			String counterCode = input.getCounterCode();
			String patternCode = input.getPatternCode();
			
			int startCounter = 0;
			if (Validator.isNotNull(input.getStartCounter())) {
				startCounter = input.getStartCounter();
			}
			//
			ConfigCounterActions actions = new ConfigCounterActionsImpl();
			ConfigCounter config = actions.updateConfigCounter(groupId, userId, 0, counterCode, patternCode, startCounter,serviceContext);

			ConfigCounterDetailModel result =
				ConfigCounterUtils.mappingToConfigCounterModel(config);

			return Response.status(200).entity(result).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getConfigCounterDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		try {

			ConfigCounterDetailModel result = null;
			if (id > 0) {
				
				ConfigCounter config = ConfigCounterLocalServiceUtil.fetchConfigCounter(id);
				if (Validator.isNotNull(config)) {
					result = ConfigCounterUtils.mappingToConfigCounterModel(config);
				}
			}
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateConfigCounter(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ConfigCounterInputModel input) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ConfigCounter configCounter = ConfigCounterLocalServiceUtil.fetchConfigCounter(id);
			if (configCounter != null) {

				String counterCode = input.getCounterCode();
				String patternCode = input.getPatternCode();

				int startCounter = 0;
				if (Validator.isNotNull(input.getStartCounter())) {
					startCounter = input.getStartCounter();
				}
				//
				ConfigCounterActions actions = new ConfigCounterActionsImpl();
				configCounter = actions.updateConfigCounter(groupId, userId, id, counterCode, patternCode, startCounter,
						serviceContext);
			}

			ConfigCounterDetailModel result = ConfigCounterUtils.mappingToConfigCounterModel(configCounter);

			return Response.status(200).entity(result).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeConfigCounter(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ConfigCounter config = ConfigCounterLocalServiceUtil.deleteConfigCounter(id);

			ConfigCounterDetailModel result = ConfigCounterUtils.mappingToConfigCounterModel(config);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

}
