package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.ResourceRoleManagement;
import org.opencps.api.controller.util.ResourceRoleUtils;
import org.opencps.api.controller.util.ResourceUserUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.resourcerole.model.DataSearchModel;
import org.opencps.api.resourcerole.model.ResourceRoleInputModel;
import org.opencps.api.resourcerole.model.ResourceRoleModel;
import org.opencps.api.resourcerole.model.ResourceRoleResults;
import org.opencps.api.resourceuser.model.ResourceUserInputModel;
import org.opencps.api.resourceuser.model.ResourceUserResults;
import org.opencps.usermgt.action.ResourceRoleInterface;
import org.opencps.usermgt.action.ResourceUserInterface;
import org.opencps.usermgt.action.impl.ResourceRoleActions;
import org.opencps.usermgt.action.impl.ResourceUserActions;
import org.opencps.usermgt.constants.ResourceRoleTerm;
import org.opencps.usermgt.constants.ResourceUserTerm;
import org.opencps.usermgt.model.ResourceRole;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public class ResourceRoleManagementImpl implements ResourceRoleManagement {

	private static final Log _log = LogFactoryUtil.getLog(ResourceRoleManagementImpl.class);

	@Override
	public Response getResourceRoles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String className, String classPK, DataSearchModel query) {
		ResourceRoleInterface actions = new ResourceRoleActions();
		ResourceRoleResults result = new ResourceRoleResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put(ResourceRoleTerm.CLASS_NAME, String.valueOf(className));
			params.put(ResourceRoleTerm.CLASS_PK, String.valueOf(classPK));

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getResourceRoles(className, classPK, user.getUserId(), company.getCompanyId(),
					groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext, query.isFull());

			result.setTotal(jsonData.getLong("total"));
			result.getResourceRoleModel()
					.addAll(ResourceRoleUtils.mapperResourceRoleList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, ResourceRoleInputModel input) {
		ResourceRoleInterface actions = new ResourceRoleActions();
		ResourceRoleModel ResourceRoleModel = new ResourceRoleModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			ResourceRole ResourceRole = actions.create(user.getUserId(), groupId, input.getClassName(),
					input.getClassPK(), input.getRoleId(), serviceContext);

			ResourceRoleModel = ResourceRoleUtils.mapperResourceRoleModel(ResourceRole);

			return Response.status(200).entity(ResourceRoleModel).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof NotFoundException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("notfound!");
				error.setCode(404);
				error.setDescription("notfound!");

				return Response.status(404).entity(error).build();

			}
			
			return Response.status(500).build();
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String className, String classPK, long workspaceId) {

		ResourceRoleInterface actions = new ResourceRoleActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = actions.delete(user.getUserId(), groupId, className, classPK, workspaceId, serviceContext);

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
			_log.error("@DELETE: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@DELETE: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@DELETE: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@DELETE: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response createResourceRolePatch(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ResourceUserInputModel input, String roles) {
		ResourceRoleInterface actions = new ResourceRoleActions();
		ResourceRoleResults result = new ResourceRoleResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			actions.createResourceRolePatch(input.getClassName(), input.getClassPK(), user.getUserId(),
					company.getCompanyId(), groupId, roles, serviceContext);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(ResourceUserTerm.CLASS_NAME, input.getClassName());
			params.put(ResourceUserTerm.CLASS_PK, input.getClassPK());

			JSONObject jsonData = actions.getResourceRoles(input.getClassName(), input.getClassPK(), user.getUserId(),
					company.getCompanyId(), groupId, params, new Sort[] {}, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					serviceContext, false);

			result.setTotal(jsonData.getLong("total"));
			result.getResourceRoleModel()
					.addAll(ResourceRoleUtils.mapperResourceRoleList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}
			
			if (e instanceof NotFoundException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("notfound!");
				error.setCode(404);
				error.setDescription("notfound!");

				return Response.status(404).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response clone(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String className, String classPK, String sourcePK) {
		ResourceRoleInterface actions = new ResourceRoleActions();
		ResourceRoleResults result = new ResourceRoleResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			actions.clone(className, classPK, user.getUserId(), company.getCompanyId(), groupId, sourcePK,
					serviceContext);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(ResourceUserTerm.CLASS_NAME, className);
			params.put(ResourceUserTerm.CLASS_PK, sourcePK);

			JSONObject jsonData = actions.getResourceRoles(className, sourcePK, user.getUserId(), company.getCompanyId(),
					groupId, params, new Sort[] {}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext, false);

			result.setTotal(jsonData.getLong("total"));
			result.getResourceRoleModel()
					.addAll(ResourceRoleUtils.mapperResourceRoleList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

}
