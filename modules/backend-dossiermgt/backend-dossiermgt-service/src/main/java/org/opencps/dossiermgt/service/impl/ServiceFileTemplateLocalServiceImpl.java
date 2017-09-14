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

package org.opencps.dossiermgt.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.service.base.ServiceFileTemplateLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the file template local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.FileTemplateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see FileTemplateLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.FileTemplateLocalServiceUtil
 */
@ProviderType
public class ServiceFileTemplateLocalServiceImpl extends ServiceFileTemplateLocalServiceBaseImpl {

	public int countByServiceInfoId(long serviceInfoId) {
		return serviceFileTemplatePersistence.countByServiceInfoId(serviceInfoId);
	}

	public List<ServiceFileTemplate> getByServiceInfoId(long serviceInfoId) {
		return serviceFileTemplatePersistence.findByServiceInfoId(serviceInfoId);
	}

	public ServiceFileTemplate removeServiceFileTemplate(long serviceInfoId, String fileTemplateNo)
			throws PortalException {
		ServiceFileTemplatePK fileTemplatePK = new ServiceFileTemplatePK(serviceInfoId, fileTemplateNo);

		ServiceFileTemplate fileTemplate = serviceFileTemplatePersistence.fetchByPrimaryKey(fileTemplatePK);

		dlAppLocalService.deleteFileEntry(fileTemplate.getFileEntryId());

		serviceFileTemplatePersistence.remove(fileTemplate);

		return fileTemplate;
	}
	
	public ServiceFileTemplate addServiceFileTemplate(long serviceInfoId, String fileTemplateNo, String templateName, long fileEntryId,
			ServiceContext serviceContext) throws PortalException, IOException {


		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);


		ServiceFileTemplatePK fileTemplatePK = new ServiceFileTemplatePK(serviceInfoId, fileTemplateNo);

		ServiceFileTemplate serviceFileTemplate = serviceFileTemplatePersistence.create(fileTemplatePK);

		serviceFileTemplate.setTemplateName(templateName);
		serviceFileTemplate.setFileEntryId(fileEntryId);

		return serviceFileTemplatePersistence.update(serviceFileTemplate);
	}

	public ServiceFileTemplate addServiceFileTemplate(long userId, long groupId, long folderId, long serviceInfoId,
			String fileTemplateNo, String templateName, String sourceFileName, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, IOException {

		long fileEntryId = 0;

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		if (inputStream != null) {
			String mimeType = MimeTypesUtil.getContentType(sourceFileName);
			int size = FileUtil.getBytes(inputStream).length;

			// FileEntry fileEntry =
			//
			// dlAppLocalService.addFileEntry(userId, groupId, folderId,
			// sourceFileName, mimeType,
			// sourceFileName, sourceFileName, sourceFileName,
			// inputStream, size, serviceContext);
			
			try {
				FileEntry fileEntry = dlAppLocalService.addFileEntry(userId, groupId, folderId, sourceFileName, mimeType,
						sourceFileName, sourceFileName, sourceFileName, inputStream, size, serviceContext);

				fileEntryId = fileEntry.getFileEntryId();
			} catch (Exception e) {
				_log.error(e);
			}
			
			
		}

		ServiceFileTemplatePK fileTemplatePK = new ServiceFileTemplatePK(serviceInfoId, fileTemplateNo);

		ServiceFileTemplate serviceFileTemplate = serviceFileTemplatePersistence.create(fileTemplatePK);

		serviceFileTemplate.setTemplateName(templateName);
		serviceFileTemplate.setFileEntryId(fileEntryId);

		return serviceFileTemplatePersistence.update(serviceFileTemplate);
	}

	public ServiceFileTemplate updateServiceFileTemplate(long userId, long groupId, long folderId, long serviceInfoId,
			String fileTemplateNo, String templateName, String sourceFileName, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, IOException {

		long fileEntryId = 0;

		if (inputStream != null) {
			String mimeType = MimeTypesUtil.getContentType(sourceFileName);
			int size = FileUtil.getBytes(inputStream).length;

			FileEntry fileEntry = dlAppLocalService.addFileEntry(userId, groupId, folderId, sourceFileName, mimeType,
					templateName, templateName, StringPool.BLANK, inputStream, size, serviceContext);

			fileEntryId = fileEntry.getFileEntryId();
		}

		ServiceFileTemplatePK fileTemplatePK = new ServiceFileTemplatePK(serviceInfoId, fileTemplateNo);

		ServiceFileTemplate serviceFileTemplate = serviceFileTemplatePersistence.fetchByPrimaryKey(fileTemplatePK);

		// remove old fileEntry
		if (serviceFileTemplate.getFileEntryId() != 0) {
			dlAppLocalService.deleteFileEntry(serviceFileTemplate.getFileEntryId());
		}

		serviceFileTemplate.setTemplateName(templateName);
		serviceFileTemplate.setFileEntryId(fileEntryId);

		return serviceFileTemplatePersistence.update(serviceFileTemplate);
	}
	
	
	Log _log = LogFactoryUtil.getLog(ServiceFileTemplateLocalServiceImpl.class);
}