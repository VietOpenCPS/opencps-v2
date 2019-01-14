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

import java.util.List;

import org.opencps.dossiermgt.model.OpencpsVotingStatistic;
import org.opencps.dossiermgt.service.base.OpencpsVotingStatisticLocalServiceBaseImpl;

/**
 * The implementation of the opencps voting statistic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.OpencpsVotingStatisticLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see OpencpsVotingStatisticLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.OpencpsVotingStatisticLocalServiceUtil
 */
public class OpencpsVotingStatisticLocalServiceImpl
	extends OpencpsVotingStatisticLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.OpencpsVotingStatisticLocalServiceUtil} to access the opencps voting statistic local service.
	 */
	public OpencpsVotingStatistic fetchByG_M_Y_G_D_VC(long groupId, int month, int year, String govAgencyCode, String domainCode, String votingCode) {
		return opencpsVotingStatisticPersistence.fetchByM_Y_DM_G_VC(groupId, month, year, govAgencyCode, domainCode, votingCode);
	}
	public List<OpencpsVotingStatistic> fetchByG_M_Y_G_D(long groupId, int month, int year, String govAgencyCode, String domainCode) {
		return opencpsVotingStatisticPersistence.findByM_Y_DM_G(groupId, month, year,  govAgencyCode, domainCode);
	}	
}