package org.opencps.usermgt.action.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.action.ResourceUserInterface;
import org.opencps.usermgt.constants.ResourceUserTerm;
import org.opencps.usermgt.model.ResourceUser;
import org.opencps.usermgt.service.ResourceUserLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public class ResourceUserActions implements ResourceUserInterface {

	public Log _log = LogFactoryUtil.getLog(ResourceUserActions.class);

	public boolean delete(long userId, long groupId, long companyId, String className, String classPK, String email,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		boolean flag = false;

		// getUserId from email
		User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);

		long toUserId = Validator.isNotNull(user) ? user.getUserId() : 0;

		ResourceUser resourceUser = ResourceUserLocalServiceUtil.fetchByF_className_classPK_toUserId(groupId, className,
				classPK, toUserId);

		if (Validator.isNotNull(resourceUser)) {

			ResourceUserLocalServiceUtil.deleteResourceUser(resourceUser.getResourceUserId(), serviceContext);

			flag = true;
		}

		return flag;
	}

	@Override
	public JSONObject getResourceUsers(String className, String classPK, long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext,
			boolean full) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			if (full) {

				Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

				searchContext.addFullQueryEntryClassName(User.class.getName());
				searchContext.setEntryClassNames(new String[] { User.class.getName() });
				searchContext.setAttribute("paginationType", "regular");
				searchContext.setLike(true);
				searchContext.setStart(QueryUtil.ALL_POS);
				searchContext.setEnd(QueryUtil.ALL_POS);
				searchContext.setAndSearch(true);
				searchContext.setSorts(new Sort[] {});

				BooleanQuery booleanQuery = indexer.getFullQuery(searchContext);
				MultiMatchQuery query = null;

				query = new MultiMatchQuery(String.valueOf(groupId));

				query.addFields("groupId");

				booleanQuery.add(query, BooleanClauseOccur.MUST);

				booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, User.class.getName());

				hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);

				List<Document> list = hits.toList();

				for (Document document : list) {

					ResourceUser ResourceUser = ResourceUserLocalServiceUtil.fetchByF_className_classPK_toUserId(
							groupId, className, classPK, Long.valueOf(document.get("entryClassPK")));

					String selected = Boolean.FALSE.toString();

					if (Validator.isNotNull(ResourceUser)) {

						selected = Boolean.TRUE.toString();

					}
					document.addTextSortable(ResourceUserTerm.TO_USERID, document.get("entryClassPK"));
					document.addTextSortable(ResourceUserTerm.TO_USERNAME, document.get("fullName"));
					document.addTextSortable("selected", selected);

				}

				result.put("data", list);

				long total = list.size();

				result.put("total", total);

			} else {

				hits = ResourceUserLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

				result.put("data", hits.toList());

				long total = ResourceUserLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

				result.put("total", total);

			}

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	public ResourceUser create(long userId, long groupId, String className, String classPK, Long toUserId,
			String fullname, String email, boolean readonly, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException, NotFoundException {
		ResourceUser ett = null;

		ett = ResourceUserLocalServiceUtil.addResourceUser(userId, groupId, className, classPK, toUserId, fullname,
				email, readonly, serviceContext);

		return ett;
	}

	@Override
	public void createResourceUserPatch(String className, String classPK, long userId, long companyId, long groupId,
			String users, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchUserException {
		try {

			List<ResourceUser> resourceUsers = new ArrayList<>(
					ResourceUserLocalServiceUtil.findByF_className_classPK(groupId, className, classPK));

			JSONArray jUser = JSONFactoryUtil.createJSONArray(users);

			// jUser to resource user
			ResourceUser resourceUser = null;
			for (int n = 0; n < jUser.length(); n++) {
				JSONObject user = jUser.getJSONObject(n);

				resourceUser = ResourceUserLocalServiceUtil.fetchByF_className_classPK_toUserId(groupId, className,
						classPK, user.getLong("userId"));

				if (Validator.isNotNull(resourceUser)) {

					resourceUsers.remove(resourceUser);

				} else {

					ResourceUserLocalServiceUtil.addResourceUser(userId, groupId, className, classPK,
							user.getLong("userId"), user.getString("fullname"), user.getString("email"),
							user.getBoolean("readonly"), serviceContext);

				}

			}

			for (ResourceUser ett : resourceUsers) {

				ResourceUserLocalServiceUtil.deleteResourceUser(ett.getResourceUserId(), serviceContext);

			}

		} catch (JSONException e) {
			_log.error(e);
		}

	}

	@Override
	public void clone(String className, String classPK, long userId, long companyId, long groupId, String sourcePK,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchUserException {
		List<ResourceUser> resourceUsers = new ArrayList<>(
				ResourceUserLocalServiceUtil.findByF_className_classPK(groupId, className, classPK));

		if (Validator.isNotNull(resourceUsers) && resourceUsers.size() > 0) {
			// Nothing to do here
		} else {

			List<ResourceUser> resourceUsersSourcePK = new ArrayList<>(
					ResourceUserLocalServiceUtil.findByF_className_classPK(groupId, className, sourcePK));

			for (ResourceUser resourceUser : resourceUsersSourcePK) {

				ResourceUserLocalServiceUtil.addResourceUser(userId, groupId, className, classPK,
						resourceUser.getToUserId(), resourceUser.getFullname(), resourceUser.getEmail(),
						resourceUser.getReadonly(), serviceContext);
			}

		}

	}

}
