package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierActionUserManagement;
import org.opencps.api.dossieractionuser.model.DossierActionUserModel;
import org.opencps.api.dossieractionuser.model.DossierActionUserResultModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.dossiermgt.service.persistence.DossierUserPK;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DossierActionUserManagementImpl implements DossierActionUserManagement {

	@Override
	public Response addDossierActionUser(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierActionUserModel input, String id) {
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Indexer<Dossier> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Dossier.class);
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, "admin")) {
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity("User not permission process!").build();
			}
			Dossier dossier = null;
			try {
				long dossierId = Integer.parseInt(id);
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			}
			catch (NumberFormatException nfe) {
				
			}
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}
			String stepCode = input.getStepCode();
		
			if (dossier != null) {
				long dossierActionId = dossier.getDossierActionId();
				DossierAction da = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
				
				if (Validator.isNull(stepCode) && da != null) {
					stepCode = da.getStepCode();
				}
				long userId = 0;
				
				ProcessStep ps = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, da != null? da.getServiceProcessId() : 0);
				if (!dossier.getDossierStatus().equals(ps.getDossierStatus())
						|| !dossier.getDossierSubStatus().equals(ps.getDossierSubStatus())) {
					dossier.setDossierStatus(ps.getDossierStatus());
					dossier.setDossierSubStatus(ps.getDossierSubStatus());
				}
				DossierUserPK duPK = new DossierUserPK();
				if (Validator.isNotNull(input.getUserId())) {
					duPK.setUserId(input.getUserId());
					userId = input.getUserId();
				}
				else if (Validator.isNotNull(input.getEmailAddress())) {
					User u = UserLocalServiceUtil.fetchUserByEmailAddress(dossier.getCompanyId(), input.getEmailAddress());
					if (u != null) {
						userId = u.getUserId();
					}
				}
				duPK.setDossierId(dossier.getDossierId());
				DossierUser du = DossierUserLocalServiceUtil.fetchDossierUser(duPK);
				if (du == null) {
					DossierUserLocalServiceUtil.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), userId, 1, true);
				}
				else {
					du.setModerator(1);
					DossierUserLocalServiceUtil.updateDossierUser(du);
				}
				DossierActionUserPK dauPK = new DossierActionUserPK();
				dauPK.setDossierActionId(dossierActionId);
				dauPK.setUserId(userId);
				DossierActionUser oldDau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(dauPK);
				if (oldDau == null) {
					DossierActionUser dau = DossierActionUserLocalServiceUtil.addDossierActionUser(userId, groupId, dossierActionId, dossier.getDossierId(), stepCode, input.getModerator(), input.getAssigned(), input.isVisited());
					DossierActionUserResultModel result = new DossierActionUserResultModel();
					result.setDossierActionId(dossierActionId);
					result.setAssigned(dau.getAssigned());
					result.setDossierId(dau.getDossierId());
					result.setModerator(dau.getModerator());
					result.setStepCode(dau.getStepCode());
					result.setUserId(dau.getUserId());
					result.setVisited(dau.getVisited());
					
					indexer.reindex(dossier);
					
					return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
				}
				else {
					oldDau.setModerator(1);
					DossierActionUserLocalServiceUtil.updateDossierActionUser(oldDau);
					
					indexer.reindex(dossier);
					
					return Response.status(HttpURLConnection.HTTP_CONFLICT).entity("Dossier action user already exists!").build();									
				}
			}
			else {
				return Response.status(HttpURLConnection.HTTP_CONFLICT).entity("Dossier not exists!").build();				
			}
			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
