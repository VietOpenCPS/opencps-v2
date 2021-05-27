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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import org.opencps.dossiermgt.model.ReportLandTax;
import org.opencps.dossiermgt.service.base.ReportLandTaxLocalServiceBaseImpl;

import java.util.Date;

/**
 * The implementation of the report land tax local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.ReportLandTaxLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ReportLandTaxLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ReportLandTaxLocalServiceUtil
 */
public class ReportLandTaxLocalServiceImpl
	extends ReportLandTaxLocalServiceBaseImpl {
	@Override
	public ReportLandTax addReportLandTax(long groupId, String dossierNo, String request, String response, ServiceContext serviceContext) throws PortalException, SystemException {
		long userId = serviceContext.getUserId();
		Date now = new Date();

		long reportId =
				counterLocalService.increment(ReportLandTax.class.getName());

		ReportLandTax object = reportLandTaxPersistence.create(reportId);
		object.setCompanyId(serviceContext.getCompanyId());

		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);
		object.setDossierNo(dossierNo);
		object.setBodyRequest(request);
		return reportLandTaxPersistence.update(object);
	}
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.ReportLandTaxLocalServiceUtil} to access the report land tax local service.
	 */
}