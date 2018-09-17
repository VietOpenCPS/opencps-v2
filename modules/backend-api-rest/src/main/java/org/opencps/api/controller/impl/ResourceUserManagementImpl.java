package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.ResourceUserManagement;
import org.opencps.api.controller.util.ResourceUserUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.resourceuser.model.DataSearchModel;
import org.opencps.api.resourceuser.model.ResourceUserInputModel;
import org.opencps.api.resourceuser.model.ResourceUserModel;
import org.opencps.api.resourceuser.model.ResourceUserResults;
import org.opencps.usermgt.action.ResourceUserInterface;
import org.opencps.usermgt.action.impl.ResourceUserActions;
import org.opencps.usermgt.constants.ResourceUserTerm;
import org.opencps.usermgt.model.ResourceUser;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ResourceUserManagementImpl implements ResourceUserManagement {

//	private static final Log _log = LogFactoryUtil.getLog(ResourceUserManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getResourceUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String className, String classPK, DataSearchModel query) {
		ResourceUserInterface actions = new ResourceUserActions();
		ResourceUserResults result = new ResourceUserResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put(ResourceUserTerm.CLASS_NAME, String.valueOf(className));
			params.put(ResourceUserTerm.CLASS_PK, String.valueOf(classPK));

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getResourceUsers(className, classPK, user.getUserId(), company.getCompanyId(),
					groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext, query.isFull());

			result.setTotal(jsonData.getLong("total"));
			result.getResourceUserModel()
					.addAll(ResourceUserUtils.mapperResourceUserList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, ResourceUserInputModel input) {
		ResourceUserInterface actions = new ResourceUserActions();
		ResourceUserModel ResourceUserModel = new ResourceUserModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			ResourceUser ResourceUser = actions.create(user.getUserId(), groupId, input.getClassName(),
					input.getClassPK(), input.getUserId(), input.getFullName(), input.getEmail(), Boolean.valueOf(input.getReadonly()), serviceContext);

			ResourceUserModel = ResourceUserUtils.mapperResourceUserModel(ResourceUser);

			return Response.status(200).entity(ResourceUserModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String className, String classPK, String email) {

		ResourceUserInterface actions = new ResourceUserActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = actions.delete(user.getUserId(), groupId, company.getCompanyId(), className, classPK, email,
					serviceContext);

			if (flag) {

				return Response.status(200).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response createResourceUserPatch(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ResourceUserInputModel input, String users) {
		ResourceUserInterface actions = new ResourceUserActions();
		ResourceUserResults result = new ResourceUserResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			actions.createResourceUserPatch(input.getClassName(), input.getClassPK(), user.getUserId(),
					company.getCompanyId(), groupId, users, serviceContext);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(ResourceUserTerm.CLASS_NAME, input.getClassName());
			params.put(ResourceUserTerm.CLASS_PK, input.getClassPK());

			JSONObject jsonData = actions.getResourceUsers(input.getClassName(), input.getClassPK(), user.getUserId(),
					company.getCompanyId(), groupId, params, new Sort[] {}, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					serviceContext, false);

			result.setTotal(jsonData.getLong("total"));
			result.getResourceUserModel()
					.addAll(ResourceUserUtils.mapperResourceUserList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response clone(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String className, String classPK, String sourcePK) {
		ResourceUserInterface actions = new ResourceUserActions();
		ResourceUserResults result = new ResourceUserResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			actions.clone(className, classPK, user.getUserId(), company.getCompanyId(), groupId, sourcePK,
					serviceContext);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(ResourceUserTerm.CLASS_NAME, className);
			params.put(ResourceUserTerm.CLASS_PK, sourcePK);

			JSONObject jsonData = actions.getResourceUsers(className, sourcePK, user.getUserId(),
					company.getCompanyId(), groupId, params, new Sort[] {}, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					serviceContext, false);

			result.setTotal(jsonData.getLong("total"));
			result.getResourceUserModel()
					.addAll(ResourceUserUtils.mapperResourceUserList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
