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
public interface OpencpsDossierStatisticManualFinder {
	public org.opencps.statistic.model.OpencpsDossierStatisticManual checkContains(
		long groupId, int month, int year, String domain, String govAgency);

	public org.opencps.statistic.model.OpencpsDossierStatisticManual checkContainsSystem(
		long groupId, int month, int year, String domain, String govAgency,
		String system);

	public java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> searchDossierStatistic(
		long groupId, int year, String domain, String govAgency,
		String groupAgencyCode, int start, int end);

	public java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> searchDossierStatistic(
		long groupId, int year, String domain, String govAgency, String system,
		String groupAgencyCode, int start, int end);

	public java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> searchYearDossierStatistic(
		long groupId, int month, String domain, String govAgency,
		String system, String groupAgencyCode, int start, int end);

	public java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> searchByDomainGovAgencyGroup(
		long groupId, int month, int year, String domain, String govAgency,
		String groupAgencyCode, int start, int end);

	public java.util.List<org.opencps.statistic.model.OpencpsDossierStatisticManual> searchByDomainAgencySystem(
		long groupId, int month, int year, String domain, String govAgency,
		String system, String groupAgencyCode, int start, int end);
}