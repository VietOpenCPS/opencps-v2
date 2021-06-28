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

package org.opencps.statistic.model.impl;

import aQute.bnd.annotation.ProviderType;

import org.opencps.statistic.model.OpencpsDossierStatisticMgt;
import org.opencps.statistic.service.OpencpsDossierStatisticMgtLocalServiceUtil;

/**
 * The extended model base implementation for the OpencpsDossierStatisticMgt service. Represents a row in the &quot;opencps_statistic_mgt&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OpencpsDossierStatisticMgtImpl}.
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticMgtImpl
 * @see OpencpsDossierStatisticMgt
 * @generated
 */
@ProviderType
public abstract class OpencpsDossierStatisticMgtBaseImpl
	extends OpencpsDossierStatisticMgtModelImpl
	implements OpencpsDossierStatisticMgt {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a opencps dossier statistic mgt model instance should use the {@link OpencpsDossierStatisticMgt} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			OpencpsDossierStatisticMgtLocalServiceUtil.addOpencpsDossierStatisticMgt(this);
		}
		else {
			OpencpsDossierStatisticMgtLocalServiceUtil.updateOpencpsDossierStatisticMgt(this);
		}
	}
}