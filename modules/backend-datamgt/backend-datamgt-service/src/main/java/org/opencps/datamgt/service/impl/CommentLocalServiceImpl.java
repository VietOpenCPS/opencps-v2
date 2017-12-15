/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.datamgt.service.impl;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.datamgt.constants.CommentTerm;
import org.opencps.datamgt.exception.NoSuchCommentException;
import org.opencps.datamgt.model.Comment;
import org.opencps.datamgt.service.base.CommentLocalServiceBaseImpl;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;
import backend.utils.DLFolderUtil;

/**
 * The implementation of the comment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.CommentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see CommentLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.CommentLocalServiceUtil
 */
@ProviderType
public class CommentLocalServiceImpl extends CommentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.CommentLocalServiceUtil} to access
	 * the comment local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Comment addComment(long userId, long groupId, String className, String classPK, String fullname,
			String email, long parent, String content, long fileSize, InputStream inputStream, String fileName,
			String fileType, int upvoteCount, String pings, ServiceContext serviceContext) throws Exception {

		// // authen
		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }
		//
		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.EDIT_DATA);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }
		Date now = new Date();

		long commentId = counterLocalService.increment(Comment.class.getName());

		Comment comment = commentPersistence.create(commentId);

		if (userId > 0) {
			User user = userPersistence.findByPrimaryKey(userId);
			fullname = user.getFullName();
			email = user.getEmailAddress();
		}

		// Group instance
		comment.setGroupId(groupId);

		// Audit fields
		comment.setUuid(serviceContext.getUuid());
		comment.setCompanyId(serviceContext.getCompanyId());
		comment.setUserId(userId);
		comment.setUserName(fullname);
		comment.setCreateDate(serviceContext.getCreateDate(now));
		comment.setModifiedDate(serviceContext.getCreateDate(now));

		// Add attachment
		if (inputStream != null && fileSize > 0 && Validator.isNotNull(fileName)) {

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			String destination = "Comments/";

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(now);

			destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
			destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
			destination += calendar.get(Calendar.DAY_OF_MONTH);

			DLFolder dlFolder = DLFolderUtil.getTargetFolder(userId, groupId, groupId, false, 0, destination,
					"Comment attactment", false, serviceContext);

			User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);

			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, groupId, dlFolder.getFolderId(), fileName,
					fileType, fileName + StringPool.DASH + System.currentTimeMillis(), "Comment attachment", StringPool.BLANK, inputStream, fileSize, serviceContext);

//			DLFolderUtil.setFilePermissions(fileEntry);

			comment.setFileEntryId(fileEntry.getFileEntryId());

		}

		comment.setClassName(className);
		comment.setClassPK(classPK);
		comment.setFullname(fullname);
		comment.setEmail(email);
		comment.setParent(parent);
		comment.setContent(content);
		comment.setUpvoteCount(upvoteCount);
		comment.setUserHasUpvoted(StringPool.BLANK);
		comment.setPings(pings);
		comment.setExpandoBridgeAttributes(serviceContext);

		commentPersistence.update(comment);

		return comment;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Comment deleteComment(long commentId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, PortalException {

		// // authen
		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }
		//
		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.EDIT_DATA);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		Comment comment = null;

		try {

			comment = commentLocalService.getComment(commentId);

			if (comment.getFileEntryId() > 0) {

				DLAppLocalServiceUtil.deleteFileEntry(comment.getFileEntryId());
			}

			comment = commentPersistence.remove(commentId);

		} catch (NoSuchCommentException e) {
			// TODO Auto-generated catch block
			throw new NotFoundException();
		} catch (PortalException e) {
			throw new PortalException();
		}

		return comment;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Comment updateComment(long commentId, String className, String classPK, String email, int upvoteCount,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {

		// // authen
		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }
		//
		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.EDIT_DATA);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		Date now = new Date();

		Comment comment = commentPersistence.fetchByPrimaryKey(commentId);

		if (Validator.isNull(comment)) {
			throw new NotFoundException();
		}

		comment.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		int counter = comment.getUpvoteCount();

		String userHasUpvoted = Validator.isNotNull(comment.getUserHasUpvoted()) ? comment.getUserHasUpvoted()
				: StringPool.BLANK;

		if ((!StringUtil.contains(userHasUpvoted, email) || Validator.isNull(comment.getUserHasUpvoted()))
				&& upvoteCount >= 0) {

			userHasUpvoted += Validator.isNotNull(userHasUpvoted) ? StringPool.COMMA + email : email;

			String[] userVoteds = StringUtil.split(userHasUpvoted);

			counter = userVoteds.length;

		} else if (StringUtil.contains(userHasUpvoted, email) && upvoteCount < 0) {

			String[] emails = StringUtil.split(userHasUpvoted);
			emails = ArrayUtil.remove(emails, email);
			userHasUpvoted = StringUtil.merge(emails);

			counter = emails.length;

		}

		comment.setUserHasUpvoted(userHasUpvoted);
		// comment.setClassName(className);
		// comment.setClassPK(classPK);
		comment.setUpvoteCount(counter);
		comment.setExpandoBridgeAttributes(serviceContext);

		commentPersistence.update(comment);

		return comment;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Comment updateComment(long userId, long commentId, String className, String classPK, String fullname,
			String email, long parent, String content, String pings, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {

		// // authen
		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }
		//
		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.EDIT_DATA);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Comment comment = commentPersistence.fetchByPrimaryKey(commentId);

		if (Validator.isNull(comment)) {
			throw new NotFoundException();
		}

		// Audit fields
		comment.setUserId(user.getUserId());
		comment.setUserName(user.getFullName());
		comment.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields

		// comment.setClassName(className);
		// comment.setClassPK(classPK);
		comment.setFullname(fullname);
		comment.setEmail(email);
		comment.setParent(parent);
		comment.setContent(content);
		comment.setPings(pings);
		comment.setExpandoBridgeAttributes(serviceContext);

		commentPersistence.update(comment);

		return comment;
	}

	public List<Comment> findByF_groupId(long groupId, int start, int end) {
		return commentPersistence.findByF_groupId(groupId, start, end);
	}

	public Hits luceneSearchEngine(Map<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		// TODO Auto-generated method stub

		Indexer<Comment> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Comment.class);

		searchContext.addFullQueryEntryClassName(Comment.class.getName());
		searchContext.setEntryClassNames(new String[] { Comment.class.getName() });

		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		// searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.

		String classPK = GetterUtil.getString(params.get("classPK"));

		String keywords = GetterUtil.getString(params.get("keywords"));

		String groupId = GetterUtil.getString(params.get("groupId"));

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(CommentTerm.EMAIL);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(CommentTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {

			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(CommentTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Comment.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(Map<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		// TODO Auto-generated method stub

		Indexer<Comment> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Comment.class);

		searchContext.addFullQueryEntryClassName(Comment.class.getName());
		searchContext.setEntryClassNames(new String[] { Comment.class.getName() });

		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		// searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.

		String classPK = GetterUtil.getString(params.get("classPK"));

		String keywords = GetterUtil.getString(params.get("keywords"));

		String groupId = GetterUtil.getString(params.get("groupId"));

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(CommentTerm.EMAIL);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(CommentTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {

			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(CommentTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Comment.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}
}