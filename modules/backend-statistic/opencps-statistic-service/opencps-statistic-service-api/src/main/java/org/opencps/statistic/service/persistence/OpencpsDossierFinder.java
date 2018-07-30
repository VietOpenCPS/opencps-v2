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

package org.opencps.statistic.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author khoavu
 * @generated
 */
@ProviderType
public interface OpencpsDossierFinder {
	public java.util.List<org.opencps.statistic.model.OpencpsDossier> searchDossiers(
		long groupId, java.lang.String keyword,
		java.lang.String registerBookCode, java.lang.String processNo,
		java.lang.String serviceCode, java.lang.String govAgencyCode,
		java.lang.String applicantIdType, java.lang.String applicantIdNo,
		java.lang.String cityCode, java.lang.String districtCode,
		java.lang.String wardCode, java.lang.String contactTelNo,
		java.lang.String contactEmail, java.lang.String delegateIdNo,
		java.lang.String delegateTelNo, java.lang.String dossierStatus,
		java.lang.String dossierSubStatus, long dossierActionId, int viaPostal,
		boolean online, int originality, java.lang.String serverNo,
		long originDossierId, boolean order, java.lang.String orderBy,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}