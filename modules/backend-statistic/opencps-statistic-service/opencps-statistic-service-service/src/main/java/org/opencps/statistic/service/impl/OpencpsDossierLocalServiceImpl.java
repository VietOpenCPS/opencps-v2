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

package org.opencps.statistic.service.impl;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossier;
import org.opencps.statistic.service.base.OpencpsDossierLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the opencps dossier local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.statistic.service.OpencpsDossierLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierLocalServiceBaseImpl
 * @see org.opencps.statistic.service.OpencpsDossierLocalServiceUtil
 */
public class OpencpsDossierLocalServiceImpl extends OpencpsDossierLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.statistic.service.OpencpsDossierLocalServiceUtil} to access
	 * the opencps dossier local service.
	 */

	public List<OpencpsDossier> searchDossier(long groupId, String keyword, String registerBookCode, String processNo,
			String serviceCode, String govAgencyCode, String applicantIdType, String applicantIdNo, String cityCode,
			String districtCode, String wardCode, String contactTelNo, String contactEmail, String delegateIdNo,
			String delegateTelNo, String dossierStatus, String dossierSubStatus, long dossierActionId, int viaPostal,
			boolean online, int originality, String serverNo, long originDossierId, boolean order, String orderBy,
			int start, int end) throws PortalException, SystemException {
		return opencpsDossierFinder.searchDossiers(groupId, keyword, registerBookCode, processNo, serviceCode,
				govAgencyCode, applicantIdType, applicantIdNo, cityCode, districtCode, wardCode, contactTelNo,
				contactEmail, delegateIdNo, delegateTelNo, dossierStatus, dossierSubStatus, dossierActionId, viaPostal,
				online, originality, serverNo, originDossierId, order, orderBy, start, end);
	}
}