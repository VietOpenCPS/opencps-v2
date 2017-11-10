package org.opencps.api.controller.impl;

import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.DossierLogManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierLogUtils;
import org.opencps.api.dossierlog.model.DossierLogModel;
import org.opencps.api.dossierlog.model.DossierLogResultsModel;
import org.opencps.api.dossierlog.model.DossierLogSearchIdResultsModel;
import org.opencps.api.dossierlog.model.DossierLogSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierLogActions;
import org.opencps.dossiermgt.action.impl.DossierLogActionsImpl;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class DossierLogManagementImpl implements DossierLogManagement{
	
	@Override
	public Response addDossierLogByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String notificationType, String author,
			String payload, String content) {

		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			DossierLogActions action = new DossierLogActionsImpl();
			
			DossierLog dossierLog = action.addDossierLog(groupId, id,  author,
					content, notificationType, payload, serviceContext);
			
			DossierLogModel result = DossierLogUtils.mappingToDossierLogModel(dossierLog);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}
	
	@Override
	public Response getDossierLogs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierLogSearchModel query) {
		
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		 
		try {
			
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			
			DossierLogResultsModel results = new DossierLogResultsModel();
			
			DossierLogActions action = new DossierLogActionsImpl();
			
			JSONObject dossierLogJsonObject = action.getDossierLogs(groupId, query.getNotificationType(),  query.isOwner(),  query.getStart(),
					query.getEnd(), query.getSort(), query.getOrder(), serviceContext);
			
			List<Document> documents = (List<Document>) dossierLogJsonObject.get("data");
//			
			results.setTotal(dossierLogJsonObject.getInt("total"));
			results.getData().addAll(DossierLogUtils.mappingToDossierLogResultsModel(documents));
			
			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}
	
	@Override
	public Response getDossierLogById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext,DossierLogSearchModel query, long dossierId, String password ) {
		
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			
			DossierLogSearchIdResultsModel results = new DossierLogSearchIdResultsModel();
			
			DossierLogActions action = new DossierLogActionsImpl();
			
			JSONObject dossierLogJsonObject = action.getDossierLogById(groupId, dossierId, password, query.isOwner(),  query.getStart(),
					query.getEnd(), query.getSort(), query.getOrder(), serviceContext);
			List<Document> documents = (List<Document>) dossierLogJsonObject.get("data");
//			
			results.setTotal(dossierLogJsonObject.getInt("total"));
			results.getData().addAll(DossierLogUtils.mappingToDossierLogSearchByIdResultsModel(documents));
			
//			results.setTotal(dossierLogs.size());
//			results.getData().addAll(DossierLogUtils.mappingToDossierLogData(dossierLogs));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}
	private Response processException(Exception e) {
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

				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("No Content.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}
}
