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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author khoavu
 * @generated
 */
@ProviderType
public interface TrackClientStatisticFinder {
	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> findPeriod(
		String startDay, String endDay);

	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> findAllYear();

	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> findURLAllYear();

	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> findAccessURLAllYear(
		String url);

	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> findURLPeriod(
		String startDay, String endDay);

	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> findAccessURLPeriod(
		String startDay, String endDay, String url);

	public java.util.List<org.opencps.usermgt.model.TrackClientStatistic> findAccessPeriodDesktopMobileTablet(
		String startDay, String endDay, boolean desktop, boolean mobile,
		boolean tablet);
}