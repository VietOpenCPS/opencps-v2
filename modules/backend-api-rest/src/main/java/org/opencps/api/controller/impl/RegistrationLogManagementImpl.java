package org.opencps.api.controller.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.RegistrationLogManagement;
import org.opencps.api.controller.util.RegistrationLogUtils;
import org.opencps.api.registrationlog.model.RegistrationLogModel;
import org.opencps.api.registrationlog.model.RegistrationLogResultsModel;
import org.opencps.api.registrationlog.model.RegistrationLogSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.RegistrationLogActions;
import org.opencps.dossiermgt.action.impl.RegistrationLogActionsImpl;
import org.opencps.dossiermgt.model.RegistrationLog;

import backend.auth.api.exception.BusinessExceptionImpl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class RegistrationLogManagementImpl implements RegistrationLogManagement {
	Log _log = LogFactoryUtil.getLog(RegistrationLogManagementImpl.class);

	@Override
	public Response getRegistrationLogsbyRegId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long registrationId) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			RegistrationLogActions action = new RegistrationLogActionsImpl();

			RegistrationLogResultsModel results = new RegistrationLogResultsModel();

			List<RegistrationLog> lstRegistrationLog = action.getRegistrationLogbyId(groupId, registrationId);

			results.setTotal(lstRegistrationLog.size());
			results.getData().addAll(RegistrationLogUtils.mappingToRegistrationLoggData(lstRegistrationLog));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	
	//search Lucene
	@SuppressWarnings("unchecked")
	@Override
	public Response getRegistrationLogs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long registrationId, RegistrationLogSearchModel query) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			RegistrationLogActions action = new RegistrationLogActionsImpl();

			RegistrationLogResultsModel results = new RegistrationLogResultsModel();

			JSONObject registrationLog =  action.getRegistrationLog(groupId, registrationId, query.getStart(), query.getEnd(), query.getSort(), query.getOrder(), serviceContext);

			List<Document> documents = (List<Document>) registrationLog.get("data");
			results.setTotal(registrationLog.getInt("total"));
			results.getData().addAll(RegistrationLogUtils.mappingToRegistrationLoggDataListDocument(documents));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	

	@Override
	public Response addRegistrationByRegistrationId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long registrationId, String author, String payload,
			String content) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			if(!auth.isAuth(serviceContext)){
				throw new UnauthenticationException();
			}
			RegistrationLogActions action = new RegistrationLogActionsImpl();
			RegistrationLog registrationLog = action.addRegistrationLogById(groupId, registrationId, author, content, payload, serviceContext);
			
			RegistrationLogModel result = RegistrationLogUtils.mappingToRegistrationLogModel(registrationLog);
			
			return Response.status(200).entity(result).build();
			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
