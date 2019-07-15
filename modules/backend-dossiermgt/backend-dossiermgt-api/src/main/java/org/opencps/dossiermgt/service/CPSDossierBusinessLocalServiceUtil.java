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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CPSDossierBusiness. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.CPSDossierBusinessLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see CPSDossierBusinessLocalService
 * @see org.opencps.dossiermgt.service.base.CPSDossierBusinessLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.CPSDossierBusinessLocalServiceImpl
 * @generated
 */
@ProviderType
public class CPSDossierBusinessLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.CPSDossierBusinessLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.Dossier addDossier(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .addDossier(groupId, company, user, serviceContext, input);
	}

	public static org.opencps.dossiermgt.model.DossierFile addDossierFileByDossierId(
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
		return getService()
				   .addDossierFileByDossierId(groupId, company, user,
			serviceContext, file, id, referenceUid, dossierTemplateNo,
			dossierPartNo, fileTemplateNo, displayName, fileType, isSync,
			formData, removed, eForm, modifiedDate);
	}

	public static org.opencps.dossiermgt.model.Dossier addDossierPublish(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierPublishModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .addDossierPublish(groupId, company, user, serviceContext,
			input);
	}

	public static org.opencps.dossiermgt.model.Dossier addFullDossier(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .addFullDossier(groupId, company, user, serviceContext, input);
	}

	public static org.opencps.dossiermgt.model.Dossier addFullDossier(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierMultipleInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .addFullDossier(groupId, company, user, serviceContext, input);
	}

	public static org.opencps.dossiermgt.model.Dossier addMultipleDossier(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		org.opencps.dossiermgt.input.model.DossierMultipleInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .addMultipleDossier(groupId, company, user, serviceContext,
			input);
	}

	public static org.opencps.dossiermgt.model.PaymentFile createPaymentFileByDossierId(
		long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		String id,
		org.opencps.dossiermgt.input.model.PaymentFileInputModel input)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .createPaymentFileByDossierId(groupId, serviceContext, id,
			input);
	}

	public static org.opencps.dossiermgt.model.DossierAction doAction(
		long groupId, long userId,
		org.opencps.dossiermgt.model.Dossier dossier,
		org.opencps.dossiermgt.model.ProcessOption option,
		org.opencps.dossiermgt.model.ProcessAction proAction,
		String actionCode, String actionUser, String actionNote,
		String payload, String assignUsers, String payment, int syncType,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException, Exception {
		return getService()
				   .doAction(groupId, userId, dossier, option, proAction,
			actionCode, actionUser, actionNote, payload, assignUsers, payment,
			syncType, context);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void initDossierActionUser(
		org.opencps.dossiermgt.model.ProcessAction processAction,
		org.opencps.dossiermgt.model.Dossier dossier, int allowAssignUser,
		org.opencps.dossiermgt.model.DossierAction dossierAction, long userId,
		long groupId, long assignUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.initDossierActionUser(processAction, dossier, allowAssignUser,
			dossierAction, userId, groupId, assignUserId);
	}

	public static org.opencps.dossiermgt.model.DossierFile resetformdataDossierFileFormData(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long id, String referenceUid, String formdata)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .resetformdataDossierFileFormData(groupId, company,
			serviceContext, id, referenceUid, formdata);
	}

	public static org.opencps.dossiermgt.model.DossierFile updateDossierFile(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long id, String referenceUid,
		org.apache.cxf.jaxrs.ext.multipart.Attachment file)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .updateDossierFile(groupId, company, serviceContext, id,
			referenceUid, file);
	}

	public static org.opencps.dossiermgt.model.DossierFile updateDossierFileFormData(
		long groupId, com.liferay.portal.kernel.model.Company company,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long id, String referenceUid, String formdata)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			com.liferay.portal.kernel.exception.PortalException, Exception {
		return getService()
				   .updateDossierFileFormData(groupId, company, serviceContext,
			id, referenceUid, formdata);
	}

	public static CPSDossierBusinessLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPSDossierBusinessLocalService, CPSDossierBusinessLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPSDossierBusinessLocalService.class);

		ServiceTracker<CPSDossierBusinessLocalService, CPSDossierBusinessLocalService> serviceTracker =
			new ServiceTracker<CPSDossierBusinessLocalService, CPSDossierBusinessLocalService>(bundle.getBundleContext(),
				CPSDossierBusinessLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}