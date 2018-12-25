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

import org.opencps.dossiermgt.model.OpencpsDossierStatistic;
import org.opencps.dossiermgt.service.base.OpencpsDossierStatisticLocalServiceBaseImpl;

/**
 * The implementation of the opencps dossier statistic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.OpencpsDossierStatisticLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see OpencpsDossierStatisticLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.OpencpsDossierStatisticLocalServiceUtil
 */
public class OpencpsDossierStatisticLocalServiceImpl
	extends OpencpsDossierStatisticLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.OpencpsDossierStatisticLocalServiceUtil} to access the opencps dossier statistic local service.
	 */
	public OpencpsDossierStatistic fetchByG_M_Y_G_D(long groupId, int month, int year, String govAgencyCode, String domainCode) {
		return opencpsDossierStatisticPersistence.fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode, domainCode);
	}
}