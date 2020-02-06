package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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

import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.HolidayManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.HolidayUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.holiday.model.DataSearchModel;
import org.opencps.api.holiday.model.HolidayInputModel;
import org.opencps.api.holiday.model.HolidayModel;
import org.opencps.api.holiday.model.HolidayResults;
import org.opencps.datamgt.action.HolidayInterface;
import org.opencps.datamgt.action.impl.HolidayActions;
import org.opencps.datamgt.model.Holiday;

import backend.auth.api.exception.BusinessExceptionImpl;

public class HolidayManagementImpl implements HolidayManagement {

//	private static final Log _log = LogFactoryUtil.getLog(HolidayManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getHolidays(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		HolidayInterface actions = new HolidayActions();
		HolidayResults result = new HolidayResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			if (Validator.isNotNull(query.getYear())) {
				params.put(ConstantUtils.CERT_YEAR, query.getYear());				
			}
			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getHolidays(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getHolidayModel().addAll(HolidayUtils.mapperHolidayList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String day) {
		HolidayInterface actions = new HolidayActions();
		
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		Holiday holiday = actions.read(user.getUserId(), groupId, company.getCompanyId(), day, serviceContext);
				
		if (Validator.isNotNull(holiday)) {

			HolidayModel holidayModel = HolidayUtils.mapperHolidayModel(holiday);

			return Response.status(HttpURLConnection.HTTP_OK).entity(holidayModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));
			error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, HolidayInputModel input) {
		HolidayInterface actions = new HolidayActions();
		HolidayModel holidayModel = new HolidayModel();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String description = HtmlUtil.escape(input.getDescription());
			
			Holiday holiday = actions.create(user.getUserId(), groupId, input.getHolidayDate(), description,
					serviceContext);

			holidayModel = HolidayUtils.mapperHolidayModel(holiday);

			return Response.status(HttpURLConnection.HTTP_OK).entity(holidayModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String day, HolidayInputModel input) {
		HolidayInterface actions = new HolidayActions();
		HolidayModel holidayModel = new HolidayModel();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String description = HtmlUtil.escape(input.getDescription());
			Holiday holiday = actions.update(user.getUserId(), groupId, day, input.getHolidayDate(), description,
					serviceContext);

			holidayModel = HolidayUtils.mapperHolidayModel(holiday);

			return Response.status(HttpURLConnection.HTTP_OK).entity(holidayModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String day) {
		
		HolidayInterface actions = new HolidayActions();
		
		try {
			
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			
			boolean flag = actions.delete(user.getUserId(), groupId, company.getCompanyId(), day, serviceContext);
			
			if(flag){
				
				return Response.status(HttpURLConnection.HTTP_OK).build();
				
			}else {
				
				ErrorMsg error = new ErrorMsg();

				error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();
				
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
