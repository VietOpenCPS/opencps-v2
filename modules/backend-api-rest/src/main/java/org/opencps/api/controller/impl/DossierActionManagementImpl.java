package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierActionManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierActionUtils;
import org.opencps.api.dossier.model.ListContacts;
import org.opencps.api.dossieraction.model.DossierActionNextActionResultsModel;
import org.opencps.api.dossieraction.model.DossierActionResultsModel;
import org.opencps.api.dossieraction.model.DossierActionSearchModel;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierActionUser;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionUserImpl;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

public class DossierActionManagementImpl implements DossierActionManagement {

	Log _log = LogFactoryUtil.getLog(DataManagementImpl.class);

	@Override
	public Response getListActions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierActionSearchModel query, String id) {
		// TODO Auto-generated method stub

		DossierActions actions = new DossierActionsImpl();
		DossierActionNextActionResultsModel result = new DossierActionNextActionResultsModel();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);

			String referenceUid = StringPool.BLANK;

			if (dossierId == 0) {
				referenceUid = id;
			}

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
			params.put(DossierTerm.REFERENCE_UID, String.valueOf(referenceUid));
			params.put(DossierActionTerm.ACTION_CODE, query.getActionCode());
			params.put(DossierActionTerm.AUTO, query.getAuto());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONArray jsonData = actions.getNextActions(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.length());
			result.getData().addAll(DossierActionUtils.mappingToDoListReadNextActions(jsonData));
			// result.getData()
			// .addAll(DossierActionUtils.mappingToDoListActions((List<ProcessAction>)
			// jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

	@Override
	public Response getListActionsExecuted(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DossierActionSearchModel query, String id) {

		DossierActions actions = new DossierActionsImpl();
		DossierActionResultsModel result = new DossierActionResultsModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);

			JSONObject jsonData = null;

			jsonData = (JSONObject) actions.getDossierActions(dossierId, groupId, query.isOwner(), query.getStart(),
					query.getEnd(), query.getSort(), query.getOrder(), serviceContext);
			List<Document> documents = (List<Document>) jsonData.get("data");
			result.setTotal(jsonData.getInt("total"));
			result.getData().addAll(DossierActionUtils.mappingToDoListReadActionExecuted(documents));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

	@Override
	public Response getListContacts(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		// TODO Auto-generated method stub

		DossierActions actions = new DossierActionsImpl();
		List<ListContacts> listContacts = new ArrayList<ListContacts>();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			String referenceUid = null;
			if (dossierId == 0) {
				referenceUid = id + "";
			}

			JSONObject jsonData = (JSONObject) actions.getContacts(groupId, dossierId, referenceUid);

			listContacts = DossierActionUtils.mappingToDoListContacts((List<Dossier>) jsonData.get("ListContacts"));

			return Response.status(200).entity(listContacts).build();

		} catch (Exception e) {
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

	@Override
	public Response getVisited(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getRollback(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
