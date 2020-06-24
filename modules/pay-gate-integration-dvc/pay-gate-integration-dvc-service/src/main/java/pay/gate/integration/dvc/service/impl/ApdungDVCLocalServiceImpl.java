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

package pay.gate.integration.dvc.service.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

import pay.gate.integration.dvc.model.ApdungDVC;
import pay.gate.integration.dvc.model.ServiceConfigMapping;
import pay.gate.integration.dvc.service.base.ApdungDVCLocalServiceBaseImpl;

/**
 * The implementation of the apdung dvc local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link pay.gate.integration.dvc.service.ApdungDVCLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVCLocalServiceBaseImpl
 * @see pay.gate.integration.dvc.service.ApdungDVCLocalServiceUtil
 */
public class ApdungDVCLocalServiceImpl extends ApdungDVCLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link pay.gate.integration.dvc.service.ApdungDVCLocalServiceUtil} to access the apdung dvc local service.
	 */
	
	public ApdungDVC getApdungDVCByTTHCCQTHMD(String maTTHC, String maCQTH, int mucdo) {
		return apdungDVCPersistence.fetchByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo);
	}

}