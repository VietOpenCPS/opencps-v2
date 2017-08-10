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

package org.opencps.usermgt.service.impl;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.datamgt.utils.DocumentUtils;
import org.opencps.usermgt.constants.EmployeeFileTerm;
import org.opencps.usermgt.model.EmployeeFile;
import org.opencps.usermgt.service.base.EmployeeFileLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the employee file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.EmployeeFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see EmployeeFileLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.EmployeeFileLocalServiceUtil
 */
@ProviderType
public class EmployeeFileLocalServiceImpl
	extends EmployeeFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.mobilink.backend.usermgt.service.EmployeeFileLocalServiceUtil} to access the employee file local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public EmployeeFile addEmployeeFile(long userId, long groupId, long employeeId,
			File file, String documentName, String title, String mimeType,
			ServiceContext serviceContext)
			throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long employeeFileId = counterLocalService.increment(EmployeeFile.class.getName());

		EmployeeFile EmployeeFile = employeeFilePersistence.create(employeeFileId);

		// Group instance
		EmployeeFile.setGroupId(groupId);

		// Audit fields
		EmployeeFile.setUuid(serviceContext.getUuid());
		EmployeeFile.setCompanyId(user.getCompanyId());
		EmployeeFile.setUserId(user.getUserId());
		EmployeeFile.setUserName(user.getFullName());
		EmployeeFile.setCreateDate(serviceContext.getCreateDate(now));
		EmployeeFile.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		EmployeeFile.setEmployeeId(employeeId);
		//upload File
				
		String typeFolderName = "EMPLOYEE";
				
		Folder folderType = DocumentUtils.createFolder(groupId, 0, typeFolderName, typeFolderName, serviceContext);
				
		String rootFolderName = DateTimeUtils.convertDateToString(now, DateTimeUtils._VN_DATE_FORMAT);
				
		String[] folderNameSplit = rootFolderName.split("/");
				
		Folder folderYear = DocumentUtils.createFolder(groupId, folderType.getFolderId(), folderNameSplit[2], typeFolderName, serviceContext);
				
		Folder folderMonth = DocumentUtils.createFolder(groupId, folderYear.getFolderId(),  folderNameSplit[1], typeFolderName, serviceContext);
				
		Folder folderDay = DocumentUtils.createFolder(groupId, folderMonth.getFolderId(), folderNameSplit[0], typeFolderName, serviceContext);
				
		long fileEntryId = DocumentUtils.fileUpload(file, documentName, title, mimeType, typeFolderName, groupId, folderMonth.getFolderId(), 
						folderNameSplit[0], serviceContext);
		
		EmployeeFile.setFileEntryId(fileEntryId);

		EmployeeFile.setExpandoBridgeAttributes(serviceContext);

		employeeFilePersistence.update(EmployeeFile);

		return EmployeeFile;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public EmployeeFile deleteEmployeeFile(long employeeFileId, ServiceContext serviceContext) throws Exception {

		EmployeeFile employeeFile = employeeFilePersistence.remove(employeeFileId);

		return employeeFile;

	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
		String keywords = (String) params.get("keywords");
		String employeeId = (String) params.get(EmployeeFileTerm.EMPLOYEE_ID);
		String groupId = (String) params.get(EmployeeFileTerm.GROUP_ID);
		Indexer<EmployeeFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(EmployeeFile.class);

		searchContext.addFullQueryEntryClassName(EmployeeFile.class.getName());
		searchContext.setEntryClassNames(new String[] { EmployeeFile.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

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

				query.addFields(EmployeeFileTerm.DOCUMENT_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(employeeId)) {
			MultiMatchQuery query = new MultiMatchQuery(employeeId);

			query.addFields(EmployeeFileTerm.EMPLOYEE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(EmployeeFileTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, EmployeeFile.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}
}