package org.opencps.usermgt.action.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.action.ResourceRoleInterface;
import org.opencps.usermgt.constants.ResourceRoleTerm;
import org.opencps.usermgt.model.ResourceRole;
import org.opencps.usermgt.service.ResourceRoleLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public class ResourceRoleActions implements ResourceRoleInterface {

	public Log _log = LogFactoryUtil.getLog(ResourceRoleActions.class);

	public boolean delete(long userId, long groupId, String className, String classPK, long roleId,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		boolean flag = false;

		ResourceRole resourceRole = ResourceRoleLocalServiceUtil.fetchByF_className_classPK_roleId(groupId, className,
				classPK, roleId);

		if (Validator.isNotNull(resourceRole)) {

			ResourceRoleLocalServiceUtil.deleteResourceRole(resourceRole.getResourceRoleId(), serviceContext);

			flag = true;
		}

		return flag;
	}

	@Override
	public JSONObject getResourceRoles(String className, String classPK, long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext,
			boolean full) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			if (full) {

				List<Role> listRole = RoleLocalServiceUtil.getGroupRoles(groupId);

				List<Document> list = new ArrayList<>();

				for (Role role : listRole) {

					ResourceRole ResourceRole = ResourceRoleLocalServiceUtil.fetchByF_className_classPK_roleId(groupId,
							className, classPK, role.getRoleId());

					String selected = Boolean.FALSE.toString();

					if (Validator.isNotNull(ResourceRole)) {

						selected = Boolean.TRUE.toString();

					}
					Document document = new DocumentImpl();

					document.addTextSortable(ResourceRoleTerm.ROLE_ID, String.valueOf(role.getRoleId()));
					document.addTextSortable(ResourceRoleTerm.ROLE_NAME, role.getName());
					document.addTextSortable("selected", selected);

					list.add(document);

				}

				result.put("data", list);

				long total = list.size();

				result.put("total", total);

			} else {

				hits = ResourceRoleLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

				result.put("data", hits.toList());

				long total = ResourceRoleLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

				result.put("total", total);

			}

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	public ResourceRole create(long userId, long groupId, String className, String classPK, Long roleId,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, NotFoundException {
		ResourceRole ett = null;

		ett = ResourceRoleLocalServiceUtil.addResourceRole(userId, groupId, className, classPK, roleId, serviceContext);

		return ett;
	}

	@Override
	public void createResourceRolePatch(String className, String classPK, long userId, long companyId, long groupId,
			String roles, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchUserException {
		try {

			List<ResourceRole> resourceRoles = new ArrayList<>(
					ResourceRoleLocalServiceUtil.findByF_className_classPK(groupId, className, classPK));
			
			JSONArray jRoles = JSONFactoryUtil.createJSONArray(roles);
			
			
			// jUser to resource user
			ResourceRole resourceRole = null;
			for (int n = 0; n < jRoles.length(); n++) {
				JSONObject role = jRoles.getJSONObject(n);
				
				resourceRole = ResourceRoleLocalServiceUtil.fetchByF_className_classPK_roleId(groupId, className,
						classPK, role.getLong("roleId"));

				if (Validator.isNotNull(resourceRole)) {

					resourceRoles.remove(resourceRole);

				} else {

					ResourceRoleLocalServiceUtil.addResourceRole(userId, groupId, className, classPK,
							role.getLong("roleId"), serviceContext);

				}

			}

			for (ResourceRole ett : resourceRoles) {

				ResourceRoleLocalServiceUtil.deleteResourceRole(ett.getResourceRoleId(), serviceContext);

			}

		} catch (JSONException e) {
			_log.error(e);
		}
	}

	@Override
	public void clone(String className, String classPK, long userId, long companyId, long groupId, String sourcePK,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchUserException {
		List<ResourceRole> resourceRoles = new ArrayList<>(
				ResourceRoleLocalServiceUtil.findByF_className_classPK(groupId, className, classPK));
		
		if(Validator.isNotNull(resourceRoles) && resourceRoles.size() > 0){
			//Nothing to do here
		} else {
			
			List<ResourceRole> resourceRolesSourcePK = new ArrayList<>(
					ResourceRoleLocalServiceUtil.findByF_className_classPK(groupId, className, sourcePK));
			
			for (ResourceRole resourceRole : resourceRolesSourcePK) {
			
				ResourceRoleLocalServiceUtil.addResourceRole(userId, groupId, className, classPK, resourceRole.getRoleId(), serviceContext);
			}
			
		}
		
	}

}
