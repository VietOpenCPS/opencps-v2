package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
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

import org.opencps.api.controller.JobposManagement;
import org.opencps.api.controller.util.JobposUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.jobpos.model.DataSearchModel;
import org.opencps.api.jobpos.model.JobposInputModel;
import org.opencps.api.jobpos.model.JobposModel;
import org.opencps.api.jobpos.model.JobposPermissionModel;
import org.opencps.api.jobpos.model.JobposPermissionResults;
import org.opencps.api.jobpos.model.JobposResults;
import org.opencps.usermgt.action.JobposInterface;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class JobposManagementImpl implements JobposManagement {

//	private static final Log _log = LogFactoryUtil.getLog(JobposManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getJobposs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {

		JobposInterface actions = new JobposActions();
		JobposResults result = new JobposResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getJobpos(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getJobposModel().addAll(JobposUtils.mapperJobposList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {

		JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(id);

		if (Validator.isNotNull(jobPos)) {

			JobposModel jobposModel = JobposUtils.mapperJobposModel(jobPos);

			return Response.status(200).entity(jobposModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();

		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, JobposInputModel input) {
		JobposInterface actions = new JobposActions();
		JobposModel jobposModel = new JobposModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String title = HtmlUtil.escape(input.getTitle());
			String description = HtmlUtil.escape(input.getDescription());
			
			JobPos jobPos = actions.create(user.getUserId(), groupId, title, description,
					input.getLeader(), serviceContext);

			jobposModel = JobposUtils.mapperJobposModel(jobPos);

			return Response.status(200).entity(jobposModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, JobposInputModel input) {
		JobposInterface actions = new JobposActions();
		JobposModel jobposModel = new JobposModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String title = HtmlUtil.escape(input.getTitle());
			String description = HtmlUtil.escape(input.getDescription());

			JobPos jobPos = actions.update(user.getUserId(), groupId, id, title, description,
					input.getLeader(), serviceContext);

			jobposModel = JobposUtils.mapperJobposModel(jobPos);

			return Response.status(200).entity(jobposModel).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		try {

			JobPosLocalServiceUtil.deleteJobPos(id, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response readPermissions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		JobposInterface actions = new JobposActions();
		JobposPermissionResults result = new JobposPermissionResults();
		try {

			JSONObject jsonData = actions.getJobposPermissions();

			result.setTotal(jsonData.getLong("total"));
			result.getJobposPermissionModel().addAll(JobposUtils
					.mapperJobposPermissionsList((String[]) jsonData.get("data"), user.getUserId(), id, serviceContext));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response createPermissions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, JobposInputModel input) {
		JobposInterface actions = new JobposActions();
		JobposPermissionModel result = new JobposPermissionModel();
		try {

			String actionId = actions.createPermissions(company.getCompanyId(), id, input.getActionId(),
					serviceContext);

			result = JobposUtils.mapperJobposPermissionModel(actionId);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deletePermissionByKey(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String actionId) {
		JobposInterface actions = new JobposActions();
		try {

			actions.deletePermissionByKey(company.getCompanyId(), id, actionId, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}


	@Override
	public Response createPermissionsPatch(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String permissions) {
		JobposInterface actions = new JobposActions();
		JobposPermissionResults result = new JobposPermissionResults();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			actions.createPermissionsPatch(user.getUserId(), company.getCompanyId(), groupId, id, permissions,
					serviceContext);

			JSONObject jsonData = actions.getJobposPermissions();

			result.setTotal(jsonData.getLong("total"));
			result.getJobposPermissionModel().addAll(JobposUtils
					.mapperJobposPermissionsList((String[]) jsonData.get("data"), user.getUserId(), id, serviceContext));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
