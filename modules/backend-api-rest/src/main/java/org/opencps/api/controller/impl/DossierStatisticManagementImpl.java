package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DossierStatisticManagement;
import org.opencps.api.controller.util.DossierStatisticUtils;
import org.opencps.api.dossierstatistic.model.DossierStatisticDetailModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticInputModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticResultsModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticSearchModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticYearDataModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticYearResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DossierStatisticAction;
import org.opencps.dossiermgt.action.impl.DossierStatisticActionImpl;
import org.opencps.dossiermgt.constants.DossierStatisticTerm;
import org.opencps.dossiermgt.model.DossierStatistic;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

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
	public Response getYears(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, int year) {

		DossierStatisticAction actions = new DossierStatisticActionImpl();

		DossierStatisticYearResultsModel results = new DossierStatisticYearResultsModel();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		List<DossierStatisticYearDataModel> lstDossierStatisticYearDataModel = new ArrayList<DossierStatisticYearDataModel>();

		List<Employee> lstEmployee = EmployeeLocalServiceUtil.getLstEmployee(groupId, user.getUserId());

		for (Employee employee : lstEmployee) {
			
			DossierStatisticYearDataModel dossierStatisticYearDataModel = new DossierStatisticYearDataModel();

			List<DossierStatistic> lstDossierStatistic = actions.getDossierStatisticbyYear(user.getUserId(), groupId, year);

			EmployeeJobPos employeeJobPos = EmployeeJobPosLocalServiceUtil.getEmployeeJobPosbyGidEmpId(groupId, employee.getEmployeeId());

			WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.getWorkingUnitbyGidandWid(groupId, employeeJobPos.getWorkingUnitId());

			dossierStatisticYearDataModel.setUserId(user.getUserId());
			dossierStatisticYearDataModel.setUserName(user.getFullName());
			dossierStatisticYearDataModel.setWorkingRole(employeeJobPos.getJobPostId());
			dossierStatisticYearDataModel.setWorkingUnitId(employeeJobPos.getWorkingUnitId());
			dossierStatisticYearDataModel.setWorkingUnitName(workingUnit.getName());

			dossierStatisticYearDataModel.getMonths().addAll(DossierStatisticUtils.mappingDossierStatisticYearModel(lstDossierStatistic));
			
			lstDossierStatisticYearDataModel.add(dossierStatisticYearDataModel);
		}
		
		results.setTotal(lstDossierStatisticYearDataModel.size());
		results.getData().addAll(lstDossierStatisticYearDataModel);

		return Response.status(200).entity(results).build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierStatistic(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierStatisticSearchModel search) {
		DossierStatisticAction actions = new DossierStatisticActionImpl();

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
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
		}
	}

}
