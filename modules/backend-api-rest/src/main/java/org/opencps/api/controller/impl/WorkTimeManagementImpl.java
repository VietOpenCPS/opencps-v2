package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.WorkTimeManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.WorkTimeUtils;
import org.opencps.api.worktime.model.DataSearchModel;
import org.opencps.api.worktime.model.WorkTimeInputModel;
import org.opencps.api.worktime.model.WorkTimeModel;
import org.opencps.api.worktime.model.WorkTimeResults;
import org.opencps.datamgt.action.WorkTimeInterface;
import org.opencps.datamgt.action.impl.WorkTimeActions;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;

import backend.auth.api.exception.BusinessExceptionImpl;

public class WorkTimeManagementImpl implements WorkTimeManagement {

//	private static final Log _log = LogFactoryUtil.getLog(WorkTimeManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getWorktimes(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		WorkTimeInterface actions = new WorkTimeActions();
		WorkTimeResults result = new WorkTimeResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ConstantUtils.SEARCH_KEYWORD, query.getKeywords());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getWorkTimes(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getWorkTimeModel().addAll(WorkTimeUtils.mapperWorkTimeList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, int n) {
		WorkTimeInterface actions = new WorkTimeActions();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		WorkTime workTime = actions.read(user.getUserId(), groupId, company.getCompanyId(), n, serviceContext);

		if (Validator.isNotNull(workTime)) {

			WorkTimeModel workTimeModel = WorkTimeUtils.mapperWorkTimeModel(workTime);

			return Response.status(200).entity(workTimeModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_INTERNAL_SERVER));
			error.setCode(404);
			error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_INTERNAL_SERVER));

			return Response.status(404).entity(error).build();

		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, WorkTimeInputModel input) {
		WorkTimeInterface actions = new WorkTimeActions();
		WorkTimeModel workTimeModel = new WorkTimeModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String hours = HtmlUtil.escape(String.valueOf(input.getHours()));
			
			WorkTime workTime = actions.create(user.getUserId(), groupId, input.getDay(), hours,
					serviceContext);

			workTimeModel = WorkTimeUtils.mapperWorkTimeModel(workTime);

			return Response.status(200).entity(workTimeModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, int n, WorkTimeInputModel input) {
		WorkTimeInterface actions = new WorkTimeActions();
		WorkTimeModel workTimeModel = new WorkTimeModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String hours = HtmlUtil.escape(String.valueOf(input.getHours()));
			
			WorkTime workTime = actions.update(user.getUserId(), groupId, n, hours, serviceContext);

			workTimeModel = WorkTimeUtils.mapperWorkTimeModel(workTime);

			return Response.status(200).entity(workTimeModel).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, int n) {

		WorkTimeInterface actions = new WorkTimeActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			boolean flag = actions.delete(user.getUserId(), groupId, company.getCompanyId(), n, serviceContext);

			if (flag) {

				return Response.status(200).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_INTERNAL_SERVER));
				error.setCode(500);
				error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_INTERNAL_SERVER));

				return Response.status(500).entity(error).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
