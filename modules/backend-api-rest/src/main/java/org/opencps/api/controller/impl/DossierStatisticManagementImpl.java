package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierStatisticManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierStatisticUtils;
import org.opencps.api.dossierstatistic.model.DossierStatisticDetailModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticInputModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticResultsModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierStatisticAction;
import org.opencps.dossiermgt.action.impl.DossierStatisticActionImpl;
import org.opencps.dossiermgt.constants.DossierStatisticTerm;
import org.opencps.dossiermgt.model.DossierStatistic;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
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
	public Response getDossierStatisticTodo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDossierStatistic(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierStatisticSearchModel search) {
		DossierStatisticActionImpl actions = new DossierStatisticActionImpl();

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

			JSONObject jsonData = actions.getDossierStatistic(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, -1, -1, serviceContext);

			results.setTotal(jsonData.getInt("total"));
			results.getData().addAll(
					DossierStatisticUtils.mappingToDossierStatistictModel((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response postAgency(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierStatisticInputModel input, String code) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			DossierStatisticAction action = new DossierStatisticActionImpl();

			DossierStatistic dossierStatistic = action.insertDossierStatistic(groupId, input.getMonth(),
					input.getYear(), input.getRemainingCount(), input.getReceivedCount(), input.getOnlineCount(),
					input.getUndueCount(), input.getOverdueCount(), input.getOntimeCount(), input.getOvertimeCount(),
					code, input.getGovAgencyName(), input.getDomainCode(), input.getDomainName(),
					input.getAdministrationLevel(), input.isReporting(), serviceContext);

			DossierStatisticDetailModel result = DossierStatisticUtils.mappingToDossierStatisticModel(dossierStatistic);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
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

				error.setMessage("Internal Server Error");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription(e.getMessage());

				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

			}
		}
	}

}
