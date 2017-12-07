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

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.service.base.DeliverableLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the deliverable local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DeliverableLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DeliverableLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DeliverableLocalServiceUtil
 */
@ProviderType
public class DeliverableLocalServiceImpl extends DeliverableLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DeliverableLocalServiceUtil} to access the
	 * deliverable local service.
	 */
	public List<Deliverable> getListDeliverable(String deliverableState, String govAgencyCode, String deliverableType,
			String applicant) {
		List<Deliverable> listDeliverable = deliverablePersistence.findByG_ID(deliverableState, govAgencyCode,
				deliverableType, applicant);
		return listDeliverable;
	}

	public void insert(Deliverable model) {
		model.setDeliverableId(counterLocalService.increment(Deliverable.class.getName()));
		addDeliverable(model);
	}

	public Deliverable getListDeliverableDetai(Long id) throws NoSuchDeliverableException {
		Deliverable deliverable = deliverablePersistence.findByDID(id);
		return deliverable;
	}

	public void update(Deliverable model) {
		updateDeliverable(model);
	}

}