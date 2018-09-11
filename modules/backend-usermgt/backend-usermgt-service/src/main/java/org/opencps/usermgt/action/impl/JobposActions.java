package org.opencps.usermgt.action.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.action.JobposInterface;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

public class JobposActions implements JobposInterface {

	private static final Log _log = LogFactoryUtil.getLog(JobposActions.class);

	@Override
	public JSONObject getJobpos(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = JobPosLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = JobPosLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JobPos create(long userId, long groupId, String title, String description, int leader,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, PortalException {
		JobPos ett = null;

		ett = JobPosLocalServiceUtil.addJobPos(userId, groupId, title, description, leader, serviceContext);

		return ett;
	}

	@Override
	public JobPos update(long userId, long groupId, long id, String title, String description, int leader,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {

		JobPos mJobPos = JobPosLocalServiceUtil.fetchJobPos(id);

		if (Validator.isNotNull(title)) {

			mJobPos.setTitle(title);

		}

		if (Validator.isNotNull(description)) {

			mJobPos.setDescription(description);

		}

		if (Validator.isNotNull(leader)) {

			mJobPos.setLeader(leader);

		}

		mJobPos = JobPosLocalServiceUtil.updateJobPos(userId, mJobPos.getJobPosId(), mJobPos.getTitle(),
				mJobPos.getDescription(), mJobPos.getLeader(), serviceContext);

		return mJobPos;
	}

	@Override
	public JSONObject getJobposPermissions() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		String[] permissionData = ActionKeys.getListPermissionData();

		result.put("data", permissionData);

		result.put("total", permissionData.length);

		return result;
	}

	@Override
	public String createPermissions(long companyId, long id, String actionId, ServiceContext serviceContext) {

		JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(id);

		Role role = RoleLocalServiceUtil.fetchRole(jobPos.getMappingRoleId());

		List<String> actionIds = new ArrayList<>();

		for (String actionKey : ActionKeys.getListPermissionData()) {

			boolean hasPermission = false;

			try {

				hasPermission = ResourcePermissionLocalServiceUtil.hasResourcePermission(companyId,
						ModelNameKeys.WORKINGUNIT_MGT_CENTER, ResourceConstants.SCOPE_INDIVIDUAL,
						Long.toString(role.getRoleId()), role.getRoleId(), actionKey);

				if (hasPermission) {
					actionIds.add(actionKey);
				}

			} catch (PortalException e) {
				_log.error(e);
			}

		}

		actionIds.remove(actionId);
		actionIds.add(actionId);

		String[] actionKey = actionIds.toArray(new String[actionIds.size()]);

		JobPosLocalServiceUtil.assignPermission(id, actionKey, serviceContext);

		return actionId;
	}

	@Override
	public void deletePermissionByKey(long companyId, long id, String actionId, ServiceContext serviceContext) {

		JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(id);

		Role role = RoleLocalServiceUtil.fetchRole(jobPos.getMappingRoleId());

		List<String> actionIds = new ArrayList<>();

		for (String actionKey : ActionKeys.getListPermissionData()) {

			boolean hasPermission = false;

			try {

				hasPermission = ResourcePermissionLocalServiceUtil.hasResourcePermission(companyId,
						ModelNameKeys.WORKINGUNIT_MGT_CENTER, ResourceConstants.SCOPE_INDIVIDUAL,
						Long.toString(role.getRoleId()), role.getRoleId(), actionKey);

				if (hasPermission) {
					actionIds.add(actionKey);
				}

			} catch (PortalException e) {
				_log.error(e);
			}

		}

		actionIds.remove(actionId);

		String[] actionKey = actionIds.toArray(new String[actionIds.size()]);

		JobPosLocalServiceUtil.assignPermission(id, actionKey, serviceContext);

	}

	@Override
	public void createPermissionsPatch(long userId, long companyId, long groupId, long id, String permissions,
			ServiceContext serviceContext) {
		try {

			JSONArray jPermissions = JSONFactoryUtil.createJSONArray(permissions);

			List<String> actionIds = new ArrayList<>();
			for (int n = 0; n < jPermissions.length(); n++) {
				JSONObject action = jPermissions.getJSONObject(n);

				actionIds.add(action.getString("actionId"));

			}

			String[] actionKey = actionIds.toArray(new String[actionIds.size()]);

			JobPosLocalServiceUtil.assignPermission(id, actionKey, serviceContext);

		} catch (JSONException e) {
			_log.error(e);
		}

	}

	public JobPos getJobPos(long jobPosId) {
		return JobPosLocalServiceUtil.fetchJobPos(jobPosId);
	}

	@Override
	public void updateJobPosDB(long userId, long groupId, String jobCode, String title, String description,
			ServiceContext serviceContext) throws PortalException {

		JobPosLocalServiceUtil.updateJobPosDB(userId, groupId, jobCode, title, description, serviceContext);

	}
}
