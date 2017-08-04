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

package org.mobilink.backend.usermgt.service.impl;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;

import org.mobilink.backend.usermgt.constants.PartnerFileTerm;
import org.mobilink.backend.usermgt.model.PartnerFile;
import org.mobilink.backend.usermgt.service.base.PartnerFileLocalServiceBaseImpl;
import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.datamgt.utils.DocumentUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
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
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the partner file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.PartnerFileLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see PartnerFileLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.PartnerFileLocalServiceUtil
 */
@ProviderType
public class PartnerFileLocalServiceImpl extends PartnerFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.PartnerFileLocalServiceUtil} to
	 * access the partner file local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PartnerFile addPartnerFile(long userId, long groupId, long partnerId, File file, String documentName,
			String title, String mimeType, ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long PartnerFileId = counterLocalService.increment(PartnerFile.class.getName());

		PartnerFile partnerFile = partnerFilePersistence.create(PartnerFileId);

		// Group instance
		partnerFile.setGroupId(groupId);

		// Audit fields
		partnerFile.setUuid(serviceContext.getUuid());
		partnerFile.setCompanyId(user.getCompanyId());
		partnerFile.setUserId(user.getUserId());
		partnerFile.setUserName(user.getFullName());
		partnerFile.setCreateDate(serviceContext.getCreateDate(now));
		partnerFile.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		partnerFile.setPartnerId(partnerId);
		// upload File

		String typeFolderName = "PARTNER";

		Folder folderType = DocumentUtils.createFolder(groupId, 0, typeFolderName, typeFolderName, serviceContext);

		String rootFolderName = DateTimeUtils.convertDateToString(now, DateTimeUtils._VN_DATE_FORMAT);

		String[] folderNameSplit = rootFolderName.split("/");

		Folder folderYear = DocumentUtils.createFolder(groupId, folderType.getFolderId(), folderNameSplit[2],
				typeFolderName, serviceContext);

		Folder folderMonth = DocumentUtils.createFolder(groupId, folderYear.getFolderId(), folderNameSplit[1],
				typeFolderName, serviceContext);

		Folder folderDay = DocumentUtils.createFolder(groupId, folderMonth.getFolderId(), folderNameSplit[0],
				typeFolderName, serviceContext);

		long fileEntryId = DocumentUtils.fileUpload(file, documentName, title, mimeType, typeFolderName, groupId,
				folderMonth.getFolderId(), folderNameSplit[0], serviceContext);

		partnerFile.setFileEntryId(fileEntryId);

		partnerFile.setExpandoBridgeAttributes(serviceContext);

		partnerFilePersistence.update(partnerFile);

		return partnerFile;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public PartnerFile deletePartnerFile(long PartnerFileId, ServiceContext serviceContext) throws Exception {

		PartnerFile PartnerFile = partnerFilePersistence.remove(PartnerFileId);

		return PartnerFile;

	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
		String partnerId = (String) params.get(PartnerFileTerm.PARTNER_ID);
		String groupId = (String) params.get(PartnerFileTerm.GROUP_ID);
		Indexer<PartnerFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(PartnerFile.class);

		searchContext.addFullQueryEntryClassName(PartnerFile.class.getName());
		searchContext.setEntryClassNames(new String[] { PartnerFile.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = indexer.getFullQuery(searchContext);

		if (Validator.isNotNull(partnerId)) {
			MultiMatchQuery query = new MultiMatchQuery(partnerId);

			query.addFields(PartnerFileTerm.PARTNER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(PartnerFileTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, PartnerFile.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}
}