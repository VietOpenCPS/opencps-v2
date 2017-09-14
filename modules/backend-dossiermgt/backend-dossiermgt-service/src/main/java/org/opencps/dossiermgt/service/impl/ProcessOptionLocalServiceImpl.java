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

import aQute.bnd.annotation.ProviderType;

import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.service.base.ProcessOptionLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;

/**
 * The implementation of the process option local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ProcessOptionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ProcessOptionLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil
 */
@ProviderType
public class ProcessOptionLocalServiceImpl extends ProcessOptionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil} to access
	 * the process option local service.
	 */
	@Indexable(type = IndexableType.DELETE)
	public ProcessOption removeProcessOption(long processOptionId) throws PortalException {

		validateRemove(processOptionId);
		
		ProcessOption option = processOptionPersistence.fetchByPrimaryKey(processOptionId);
		
		processOptionPersistence.remove(option);
		
		return option;
	}

	private void validateRemove(long processOptionId) throws PortalException {
		// TODO add more business login here
	}
}