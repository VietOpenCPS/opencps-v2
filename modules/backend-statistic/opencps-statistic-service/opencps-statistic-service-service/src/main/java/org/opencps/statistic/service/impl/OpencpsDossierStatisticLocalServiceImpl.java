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

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.service.base.OpencpsDossierStatisticLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the opencps dossier statistic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.statistic.service.OpencpsDossierStatisticLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil
 */
public class OpencpsDossierStatisticLocalServiceImpl extends OpencpsDossierStatisticLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil} to
	 * access the opencps dossier statistic local service.
	 */

	public List<OpencpsDossierStatistic> searchDossierStatistic(long groupId, int month, int year, String domain,
			String govAgencyCode, String groupAgenvyCode, boolean reporting, int start, int end)
			throws PortalException, SystemException {
		return opencpsDossierStatisticFinder.searchByDomainGovAgencyGroupAndReporting(groupId, month, year, domain,
				govAgencyCode, groupAgenvyCode, reporting, start, end);
	}
}