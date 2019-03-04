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