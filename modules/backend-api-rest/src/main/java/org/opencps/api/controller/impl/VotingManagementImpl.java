package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.VotingManagement;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class VotingManagementImpl implements VotingManagement {

	@Override
	public Response checkVotePermission(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantIdNo, String dossierNo) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		List<Dossier> lstDossiers = DossierLocalServiceUtil.findByDN_AN(dossierNo, applicantIdNo);
		if (lstDossiers.size() > 0) {
			result.put("hasPermission", true);
		}
		else {
			result.put("hasPermission", false);
		}
		
		return Response.status(200).entity(result.toJSONString()).build();
	}

}
