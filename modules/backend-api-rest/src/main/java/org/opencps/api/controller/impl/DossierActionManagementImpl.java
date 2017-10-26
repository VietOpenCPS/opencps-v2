package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DossierActionManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierActionUtils;
import org.opencps.api.dossier.model.*;
import org.opencps.api.dossier.model.ActionSearchModel;
import org.opencps.api.dossier.model.ExecuteOneAction;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.usermgt.model.Applicant;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class DossierActionManagementImpl implements DossierActionManagement {

	Log _log = LogFactoryUtil.getLog(DataManagementImpl.class);

	@Override
	public Response getListActions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, ActionSearchModel query) {
		// TODO Auto-generated method stub

		DossierActions actions = new DossierActionsImpl();
		ActionResultModel result = new ActionResultModel();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			String referenceUid = null;
			if (dossierId == 0) {
				referenceUid = id;
			}

			JSONObject jsonData = (JSONObject) actions.getNextActions(groupId, dossierId, referenceUid);
			result.setTotal(jsonData.getInt("total"));
			result.getData()
					.addAll(DossierActionUtils.mappingToDoListActions((List<ProcessAction>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getListActionsExecuted(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub

		DossierActions actions = new DossierActionsImpl();
		ReadActionExecuted result = new ReadActionExecuted();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			String referenceUid = null;
			if (dossierId == 0) {
				referenceUid = id;
			}

			JSONObject jsonData = (JSONObject) actions.getDossierActions(groupId, dossierId, referenceUid);
			result.setTotal(jsonData.getInt("total"));
			result.getData().addAll(
					DossierActionUtils.mappingToDoListReadActionExecuted((List<DossierAction>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addExecuteOneAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, ExecuteOneAction input) {
		// TODO Auto-generated method stub

		return null;

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
			_log.error("@GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
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
