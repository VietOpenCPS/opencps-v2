package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DossierStatisticManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierStatisticUtils;
import org.opencps.api.controller.util.ServiceInfoUtils;
import org.opencps.api.dossierstatistic.model.DossierStatisticResultsModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticSearchModel;
import org.opencps.api.serviceinfo.model.ServiceInfoResultsModel;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.impl.ServiceInfoActionsImpl;
import org.opencps.dossiermgt.constants.DossierStatisticTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;

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

public class DossierStatisticManagementImpl implements DossierStatisticManagement {

	Log _log = LogFactoryUtil.getLog(DossierStatisticManagementImpl.class);

	@Override
	public Response getYears(long year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response postAgency(long agencyCd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDossierStatistic(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierStatisticSearchModel search) {
		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		DossierStatisticResultsModel results = new DossierStatisticResultsModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierStatisticTerm.YEAR, search.getYear());
			params.put(DossierStatisticTerm.MONTH, search.getMonth());
			params.put(DossierStatisticTerm.DOMAIN_CODE, search.getDomainCode());
			params.put(DossierStatisticTerm.GOV_AGENCY_CODE, search.getGovAgencyCode());
			params.put(DossierStatisticTerm.ADMINISTRATION_LEVEL, search.getAdministrationLevel());

			Sort[] sorts = new Sort[] {};

			JSONObject jsonData = actions.getServiceInfos(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, -1, 1, serviceContext);

			results.setTotal(jsonData.getInt("total"));
			results.getData().addAll(
					DossierStatisticUtils.mappingToDossierStatistictModel((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

}
