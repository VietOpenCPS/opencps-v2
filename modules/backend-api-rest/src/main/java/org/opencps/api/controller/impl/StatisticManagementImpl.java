package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.StatisticManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.StatisticUtils;
import org.opencps.api.statistic.model.StatisticDossierResults;
import org.opencps.api.statistic.model.StatisticDossierSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.constants.DossierTerm;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class StatisticManagementImpl implements StatisticManagement {

	private static final Log _log = LogFactoryUtil.getLog(StatisticManagementImpl.class);

//	@Override
//	public Response getDossierTodo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
//			User user, ServiceContext serviceContext, StatisticDossierSearchModel query) {
//
//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//		BackendAuth auth = new BackendAuthImpl();
//		DossierActions actions = new DossierActionsImpl();
//
//		try {
//
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
//
//			params.put(Field.GROUP_ID, String.valueOf(groupId));
//			params.put(DossierTerm.STATUS, query.getDossierStatus());
//			params.put(DossierTerm.SUBSTATUS, query.getDossierSubStatus());
//			params.put(Field.USER_ID, String.valueOf(user.getUserId()));
//			params.put(DossierTerm.FOLLOW, String.valueOf(true));
//
//			// params.put("LEVEL", query.getLevel());
//
///*			JSONObject jsonData = actions.getDossierTodo(user.getUserId(), company.getCompanyId(), groupId, params,
//					null, serviceContext);
//*/
//			JSONObject jsonData = actions.getDossierTodoPermission(user.getUserId(), company.getCompanyId(), groupId, params,
//					null, serviceContext);
//
//			StatisticDossierResults results = new StatisticDossierResults();
//
//			results.setTotal(jsonData.getInt("total"));
//
//			results.getStatisticDossierModel()
//					.addAll(StatisticUtils.mapperStatisticDossierList(jsonData.getJSONArray("data")));
//
//			return Response.status(200).entity(results).build();
//
//		} catch (Exception e) {
//			_log.error(e);
//
//			ErrorMsg error = new ErrorMsg();
//
//			if (e instanceof UnauthenticationException) {
//				error.setMessage("Non-Authoritative Information.");
//				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//				error.setDescription("Non-Authoritative Information.");
//
//				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
//			} else {
//				if (e instanceof UnauthorizationException) {
//					error.setMessage("Unauthorized.");
//					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//					error.setDescription("Unauthorized.");
//
//					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();
//
//				} else {
//
//					error.setMessage("Internal Server Error");
//					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
//					error.setDescription(e.getMessage());
//
//					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
//
//				}
//			}
//		}
//	}

	@Override
	public Response getDossierTodo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, StatisticDossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.STATUS, query.getDossierStatus());
			params.put(DossierTerm.SUBSTATUS, query.getDossierSubStatus());
			params.put(Field.USER_ID, String.valueOf(user.getUserId()));
			params.put(DossierTerm.FOLLOW, String.valueOf(false));

			JSONObject jsonData = actions.getDossierTodoPermission(user.getUserId(), company.getCompanyId(), groupId, params,
					null, serviceContext);

			StatisticDossierResults results = new StatisticDossierResults();

			results.setTotal(jsonData.getInt("total"));

			results.getStatisticDossierModel()
					.addAll(StatisticUtils.mapperStatisticDossierList(jsonData.getJSONArray("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);

			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}
}
