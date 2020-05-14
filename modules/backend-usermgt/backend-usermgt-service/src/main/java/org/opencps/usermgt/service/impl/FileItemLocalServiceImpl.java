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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.FileItemTerm;
import org.opencps.usermgt.exception.NoSuchFileItemException;
import org.opencps.usermgt.model.FileItem;
import org.opencps.usermgt.service.base.FileItemLocalServiceBaseImpl;

/**
 * The implementation of the file item local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.FileItemLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see FileItemLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.FileItemLocalServiceUtil
 */
public class FileItemLocalServiceImpl extends FileItemLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.FileItemLocalServiceUtil} to access the file item local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public FileItem createFileItem(ServiceContext context, long groupId, 
			String fileTemplateNo,
			String name,
			int status,
			int size,
			String fileType,
			String log) throws PortalException, SystemException {
		FileItem fileItem = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());
		long fileItemId = counterLocalService.increment(FileItem.class.getName());
		
		fileItem = fileItemPersistence.create(fileItemId);
		fileItem.setModifiedDate(now);
		fileItem.setCreateDate(now);
		fileItem.setCompanyId(context.getCompanyId());
		fileItem.setGroupId(groupId);
		fileItem.setUserId(auditUser.getUserId());
		fileItem.setUserName(auditUser.getScreenName());
		fileItem.setFileTemplateNo(fileTemplateNo);
		fileItem.setStatus(status);
		fileItem.setName(name);
		fileItem.setStatus(status);
		fileItem.setSize(size);
		fileItem.setFileType(fileType);
		fileItem.setLog(log);
		
		return fileItem;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public FileItem updateFileItem(ServiceContext context, long groupId, 
			long fileItemId,
			String fileTemplateNo,
			String name,
			int status,
			int size,
			String fileType,
			String log) throws PortalException, SystemException {
		FileItem fileItem = null;

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());
		fileItem = fileItemPersistence.fetchByPrimaryKey(fileItemId);
		
		fileItem.setModifiedDate(now);
		fileItem.setCreateDate(now);
		fileItem.setCompanyId(context.getCompanyId());
		fileItem.setGroupId(groupId);
		fileItem.setUserId(auditUser.getUserId());
		fileItem.setUserName(auditUser.getScreenName());
		fileItem.setStatus(status);
		fileItem.setName(name);
		fileItem.setSize(size);
		fileItem.setFileType(fileType);
		fileItem.setLog(log);
		
		return fileItem;
	}
	
	public FileItem deleteFileItem(long fileItemId) {
		try {
			FileItem fileItem = fileItemPersistence.remove(fileItemId);
			return fileItem;
		}
		catch (NoSuchFileItemException e) {
			_log.debug(e);
		}
		return null;
	}
	
	public FileItem adminProcessDelete(Long id) {

		FileItem object = fileItemPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			fileItemPersistence.remove(object);
		}

		return object;
	}

	public FileItem adminProcessData(JSONObject objectData) {

		FileItem object = null;

		if (objectData.getLong(FileItemTerm.FILEITEM_ID) > 0) {

			object = fileItemPersistence.fetchByPrimaryKey(
				objectData.getLong(FileItemTerm.FILEITEM_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(FileItem.class.getName());

			object = fileItemPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(ApplicantTerm.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(FileItemTerm.USER_ID));
		if (objectData.has(FileItemTerm.FILE_TEMPLATE_NO)) {
			object.setFileTemplateNo(objectData.getString(FileItemTerm.FILE_TEMPLATE_NO));
		}
		if (objectData.has(FileItemTerm.NAME)) {
			object.setName(objectData.getString(FileItemTerm.NAME));
		}
		if (objectData.has(FileItemTerm.STATUS)) {
			object.setStatus(objectData.getInt(FileItemTerm.STATUS));
		}
		if (objectData.has(FileItemTerm.SIZE)) {
			object.setSize(objectData.getInt(FileItemTerm.SIZE));
		}
		if (objectData.has(FileItemTerm.FILE_TYPE)) {
			object.setFileType(objectData.getString(FileItemTerm.FILE_TYPE));
		}
		if (objectData.has(FileItemTerm.LOG)) {
			object.setLog(objectData.getString(FileItemTerm.LOG));
		}
		fileItemPersistence.update(object);

		return object;
	}

	public FileItem findByG_FTN(long groupId, String fileTemplateNo) {
		return fileItemPersistence.fetchByG_FTN(groupId, fileTemplateNo);
	}

	public List<FileItem> findByG_FTNS(long groupId, String[] fileTemplateNos) {
		return fileItemPersistence.findByG_FTNS(groupId, fileTemplateNos);
	}
	
	public List<FileItem> findByG_S(long groupId, int status) {
		return fileItemPersistence.findByG_S(groupId, status);
	}
	
	public List<FileItem> findByG_S(long groupId, int status, int start, int end) {
		return fileItemPersistence.findByG_S(groupId, status, start, end);
	}
	private static Log _log =
			LogFactoryUtil.getLog(FileItemLocalServiceImpl.class);
	public FileItem fetchFileItem(long fileItemId) {
		return fileItemPersistence.fetchByPrimaryKey(fileItemId);
	}	
}