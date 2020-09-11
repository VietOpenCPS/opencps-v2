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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPSDossierBusinessLocalService}.
 *
 * @author huymq
 * @see CPSDossierBusinessLocalService
 * @generated
 */
@ProviderType
public class CPSDossierBusinessLocalServiceWrapper
	implements CPSDossierBusinessLocalService,
		ServiceWrapper<CPSDossierBusinessLocalService> {
	public CPSDossierBusinessLocalServiceWrapper(
		CPSDossierBusinessLocalService cpsDossierBusinessLocalService) {
		_cpsDossierBusinessLocalService = cpsDossierBusinessLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier addDossier(long groupId,
		com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.addDossier(groupId, company,
			user, serviceContext, input);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile addDossierFileByDossierId(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.apache.cxf.jaxrs.ext.multipart.Attachment file, String id,
		String referenceUid, String dossierTemplateNo, String dossierPartNo,
		String fileTemplateNo, String displayName, String fileType,
		String isSync, String formData, String removed, String eForm,
		Long modifiedDate)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.addDossierFileByDossierId(groupId,
			company, user, serviceContext, file, id, referenceUid,
			dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName,
			fileType, isSync, formData, removed, eForm, modifiedDate);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile addDossierFileFrequency(
		long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.io.InputStream inputStream, String referenceUid,
		org.opencps.dossiermgt.model.Dossier dossier, String displayName,
		String fileType, String isSync, String formData, String removed,
		String eForm) throws Exception {
		return _cpsDossierBusinessLocalService.addDossierFileFrequency(groupId,
			serviceContext, inputStream, referenceUid, dossier, displayName,
			fileType, isSync, formData, removed, eForm);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier addDossierPublish(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierPublishModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.addDossierPublish(groupId,
			company, user, serviceContext, input);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier addFullDossier(long groupId,
		com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.addFullDossier(groupId, company,
			user, serviceContext, input);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier addFullDossier(long groupId,
		com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierMultipleInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.addFullDossier(groupId, company,
			user, serviceContext, input);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier addMultipleDossier(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierMultipleInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.addMultipleDossier(groupId,
			company, user, serviceContext, input);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier createDossierFrequency(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.ProfileInModel input)
		throws Exception {
		return _cpsDossierBusinessLocalService.createDossierFrequency(groupId,
			company, user, serviceContext, input);
	}

	@Override
	public org.opencps.dossiermgt.model.PaymentFile createPaymentFileByDossierId(
		long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		String id,
		org.opencps.dossiermgt.input.model.PaymentFileInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.createPaymentFileByDossierId(groupId,
			serviceContext, id, input);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierAction doAction(long groupId,
		long userId, org.opencps.dossiermgt.model.Dossier dossier,
		org.opencps.dossiermgt.model.ProcessOption option,
		org.opencps.dossiermgt.model.ProcessAction proAction,
		String actionCode, String actionUser, String actionNote,
		String payload, String assignUsers, String payment, int syncType,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException, Exception {
		return _cpsDossierBusinessLocalService.doAction(groupId, userId,
			dossier, option, proAction, actionCode, actionUser, actionNote,
			payload, assignUsers, payment, syncType, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier eparPublish(long groupId,
		com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long id, org.opencps.dossiermgt.input.model.DossierPublishModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.eparPublish(groupId, company,
			user, serviceContext, id, input);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpsDossierBusinessLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void initDossierActionUser(
		org.opencps.dossiermgt.model.ProcessAction processAction,
		org.opencps.dossiermgt.model.Dossier dossier, int allowAssignUser,
		org.opencps.dossiermgt.model.DossierAction dossierAction, long userId,
		long groupId, long assignUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_cpsDossierBusinessLocalService.initDossierActionUser(processAction,
			dossier, allowAssignUser, dossierAction, userId, groupId,
			assignUserId);
	}

	@Override
	public void initDossierActionUser(String stepCode, long serviceProcessId,
		org.opencps.dossiermgt.model.Dossier dossier,
		org.opencps.dossiermgt.model.ProcessAction processAction,
		int allowAssignUser,
		org.opencps.dossiermgt.model.DossierAction dossierAction, long userId,
		long groupId, long assignUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_cpsDossierBusinessLocalService.initDossierActionUser(stepCode,
			serviceProcessId, dossier, processAction, allowAssignUser,
			dossierAction, userId, groupId, assignUserId);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile resetformdataDossierFileFormData(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long id, String referenceUid, String formdata)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.resetformdataDossierFileFormData(groupId,
			company, serviceContext, id, referenceUid, formdata);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile updateDossierFile(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long id, String referenceUid,
		org.apache.cxf.jaxrs.ext.multipart.Attachment file)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.updateDossierFile(groupId,
			company, serviceContext, id, referenceUid, file);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierFile updateDossierFileFormData(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long id, String referenceUid, String formdata)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return _cpsDossierBusinessLocalService.updateDossierFileFormData(groupId,
			company, serviceContext, id, referenceUid, formdata);
	}

	@Override
	public void updateDossierFrequencyAction(long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.model.Dossier dossier,
		org.opencps.dossiermgt.input.model.ProfileInModel input,
		String actionCode) throws Exception {
		_cpsDossierBusinessLocalService.updateDossierFrequencyAction(groupId,
			serviceContext, dossier, input, actionCode);
	}

	@Override
	public CPSDossierBusinessLocalService getWrappedService() {
		return _cpsDossierBusinessLocalService;
	}

	@Override
	public void setWrappedService(
		CPSDossierBusinessLocalService cpsDossierBusinessLocalService) {
		_cpsDossierBusinessLocalService = cpsDossierBusinessLocalService;
	}

	private CPSDossierBusinessLocalService _cpsDossierBusinessLocalService;
}