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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
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
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.api.keys.ModelNameKeys;
import org.opencps.datamgt.constants.FileAttachTerm;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.base.FileAttachLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the file attach local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.FileAttachLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see FileAttachLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.FileAttachLocalServiceUtil
 */
@ProviderType
public class FileAttachLocalServiceImpl extends FileAttachLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use {@link
	 * org.mobilink.backend.document.service.FileAttachLocalServiceUtil} to access
	 * the file attach local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FileAttach addFileAttach(long userId, long groupId, String className, String classPK, String fullName,
			String email, long fileEntryId, String source, String sourceUrl, long docFileId, String fileName,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NoSuchUserException {

//		// authen
//		BackendAuthImpl authImpl = new BackendAuthImpl();
//
//		boolean isAuth = authImpl.isAuth(serviceContext);
//
//		if (!isAuth) {
//			throw new UnauthenticationException();
//		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long fileAttachId = counterLocalService.increment(FileAttach.class.getName());

		FileAttach fileAttach = fileAttachPersistence.create(fileAttachId);

		// Group instance
		fileAttach.setGroupId(groupId);

		// Audit fields
		fileAttach.setCompanyId(user.getCompanyId());
		fileAttach.setUserId(user.getUserId());
		fileAttach.setUserName(user.getFullName());
		fileAttach.setCreateDate(serviceContext.getCreateDate(now));
		fileAttach.setModifiedDate(serviceContext.getCreateDate(now));

		fileAttach.setClassName(className);
		fileAttach.setClassPK(classPK);
		fileAttach.setFullName(fullName);
		fileAttach.setEmail(email);
		fileAttach.setFileEntryId(fileEntryId);
		fileAttach.setSource(source);
		fileAttach.setSourceUrl(sourceUrl);
		fileAttach.setDocFileId(docFileId);
		fileAttach.setFileName(fileName);

		fileAttach.setExpandoBridgeAttributes(serviceContext);

		fileAttachPersistence.update(fileAttach);

		return fileAttach;
	}

	@Indexable(type = IndexableType.REINDEX)
	// @Override
	public FileAttach copyFileAttach(long userId, long groupId, long docFileId, FileAttach fileAttach,
			ServiceContext serviceContext) throws IOException, PortalException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long fileEntryId = fileAttach.getFileAttachId();

		long fileAttachId = counterLocalService.increment(FileAttach.class.getName());

		FileAttach cloneFileAttach = fileAttachPersistence.create(fileAttachId);

		// Group instance
		cloneFileAttach.setGroupId(groupId);

		// Audit fields
		cloneFileAttach.setCompanyId(user.getCompanyId());
		cloneFileAttach.setUserId(user.getUserId());
		cloneFileAttach.setUserName(user.getFullName());
		cloneFileAttach.setCreateDate(serviceContext.getCreateDate(now));
		cloneFileAttach.setModifiedDate(serviceContext.getCreateDate(now));

		cloneFileAttach.setClassName(fileAttach.getClassName());
		cloneFileAttach.setClassPK(fileAttach.getClassPK());
		cloneFileAttach.setFullName(fileAttach.getFullName());
		cloneFileAttach.setEmail(fileAttach.getEmail());
		cloneFileAttach.setFileEntryId(fileEntryId);
		cloneFileAttach.setSource("link");
		cloneFileAttach.setSourceUrl(fileAttach.getSourceUrl());
		cloneFileAttach.setDocFileId(docFileId);
		cloneFileAttach.setFileName(fileAttach.getFileName());

		cloneFileAttach.setExpandoBridgeAttributes(serviceContext);

		cloneFileAttach = fileAttachPersistence.update(cloneFileAttach);

		return cloneFileAttach;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FileAttach deleteFileAttach(long fileAttachId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		FileAttach fileAttach = fileAttachPersistence.fetchByPrimaryKey(fileAttachId);

		// try {
		if (fileAttach != null) {
			fileAttach = fileAttachPersistence.remove(fileAttach);
		}

		// fileAttach = fileAttachPersistence.remove(fileAttachId);
		// } catch (NoSuchFileAttachException e) {
		// throw new NotFoundException();
		// }

		return fileAttach;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FileAttach updateFileAttach(long userId, long fileAttachId, String className, String classPK,
			String fullName, String email, long fileEntryId, String source, String sourceUrl, long docFileId,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		FileAttach fileAttach = fileAttachPersistence.fetchByPrimaryKey(fileAttachId);

		if (Validator.isNull(fileAttach)) {
			throw new NotFoundException();
		}

		// Audit fields
		fileAttach.setUserId(user.getUserId());
		fileAttach.setUserName(user.getFullName());
		fileAttach.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		fileAttach.setClassName(className);
		fileAttach.setClassPK(classPK);
		fileAttach.setFullName(fullName);
		fileAttach.setEmail(email);
		fileAttach.setFileEntryId(fileEntryId);
		fileAttach.setSource(source);
		fileAttach.setSourceUrl(sourceUrl);
		fileAttach.setDocFileId(docFileId);

		fileAttach.setExpandoBridgeAttributes(serviceContext);

		fileAttachPersistence.update(fileAttach);

		return fileAttach;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FileAttach updateFileAttach(long userId, long fileAttachId, String className, String classPK,
			String fullName, String email, long fileEntryId, String source, String sourceUrl, long docFileId,
			String fileName, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		FileAttach fileAttach = fileAttachPersistence.fetchByPrimaryKey(fileAttachId);

		if (Validator.isNull(fileAttach)) {
			throw new NotFoundException();
		}

		// Audit fields
		fileAttach.setUserId(user.getUserId());
		fileAttach.setUserName(user.getFullName());
		fileAttach.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		fileAttach.setClassName(className);
		fileAttach.setClassPK(classPK);
		fileAttach.setFullName(fullName);
		fileAttach.setEmail(email);
		fileAttach.setFileEntryId(fileEntryId);
		fileAttach.setSource(source);
		fileAttach.setSourceUrl(sourceUrl);
		fileAttach.setDocFileId(docFileId);

		fileAttach.setFileName(fileName);

		fileAttach.setExpandoBridgeAttributes(serviceContext);

		fileAttachPersistence.update(fileAttach);

		return fileAttach;
	}

	public List<FileAttach> findByF_docFileId(long groupId, String className, String classPK, long docFileId) {

		return fileAttachPersistence.findByF_docFileId(groupId, className, classPK, docFileId);
	}

	public List<FileAttach> findByF_className_classPK(long groupId, String className, String classPK) {

		return fileAttachPersistence.findByF_className_classPK(groupId, className, classPK);
	}
	
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<FileAttach> indexer = IndexerRegistryUtil.nullSafeGetIndexer(FileAttach.class);

		searchContext.addFullQueryEntryClassName(FileAttach.class.getName());
		searchContext.setEntryClassNames(new String[] { FileAttach.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String className = (String) params.get(FileAttachTerm.CLASS_NAME);
		String classPK = (String) params.get(FileAttachTerm.CLASS_PK);
		String docFileId = (String) params.get(FileAttachTerm.DOCFILE_ID);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(FileAttachTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(FileAttachTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(FileAttachTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(FileAttachTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(docFileId)) {
			String[] keyword = docFileId.split(",");

			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);

			for (String string : keyword) {

				TermQuery catQuery1 = new TermQueryImpl(FileAttachTerm.DOCFILE_ID, string);

				categoryQuery.add(catQuery1, BooleanClauseOccur.SHOULD);

			}

			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, FileAttach.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<FileAttach> indexer = IndexerRegistryUtil.nullSafeGetIndexer(FileAttach.class);

		searchContext.addFullQueryEntryClassName(FileAttach.class.getName());
		searchContext.setEntryClassNames(new String[] { FileAttach.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String className = (String) params.get(FileAttachTerm.CLASS_NAME);
		String classPK = (String) params.get(FileAttachTerm.CLASS_PK);
		String docFileId = (String) params.get(FileAttachTerm.DOCFILE_ID);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(FileAttachTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(FileAttachTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(FileAttachTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(FileAttachTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(docFileId)) {
			String[] keyword = docFileId.split(",");

			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);

			for (String string : keyword) {

				TermQuery catQuery1 = new TermQueryImpl(FileAttachTerm.DOCFILE_ID, string);

				categoryQuery.add(catQuery1, BooleanClauseOccur.SHOULD);

			}

			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, FileAttach.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public FileAttach adminProcessDelete(Long id) {

		FileAttach object = fileAttachPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			fileAttachPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public FileAttach adminProcessData(JSONObject objectData) {

		FileAttach object = null;

		if (objectData.getLong("fileAttachId") > 0) {

			object = fileAttachPersistence.fetchByPrimaryKey(objectData.getLong("fileAttachId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(FileAttach.class.getName());

			object = fileAttachPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setClassName(objectData.getString("className"));
		object.setClassPK(objectData.getString("classPK"));
		object.setFullName(objectData.getString("fullName"));
		object.setEmail(objectData.getString("email"));
		// object.setFileEntryId(objectData.getString("actionCode")fileEntryId);
		object.setSource(objectData.getString("source"));
		object.setSourceUrl(objectData.getString("sourceUrl"));
		object.setDocFileId(objectData.getLong("docFileId"));
		object.setFileName(objectData.getString("fileName"));

		fileAttachPersistence.update(object);

		return object;
	}

	private static final Log _log = LogFactoryUtil.getLog(FileAttachLocalServiceImpl.class);
}
