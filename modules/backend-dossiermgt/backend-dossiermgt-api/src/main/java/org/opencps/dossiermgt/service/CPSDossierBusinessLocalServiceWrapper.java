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