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

package org.opencps.reportland.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.reportland.model.ReportLandTax;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ReportLandTax. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReportLandTaxLocalServiceUtil
 * @see org.opencps.reportland.service.base.ReportLandTaxLocalServiceBaseImpl
 * @see org.opencps.reportland.service.impl.ReportLandTaxLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ReportLandTaxLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReportLandTaxLocalServiceUtil} to access the report land tax local service. Add custom service methods to {@link org.opencps.reportland.service.impl.ReportLandTaxLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	
	public ReportLandTax addReportLandTax(long groupId, String dossierNo,
			String request, String response, ServiceContext serviceContext)
			throws PortalException, SystemException;

	/**
	* Adds the report land tax to the database. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ReportLandTax addReportLandTax(ReportLandTax reportLandTax);

	/**
	* Creates a new report land tax with the primary key. Does not add the report land tax to the database.
	*
	* @param reportId the primary key for the new report land tax
	* @return the new report land tax
	*/
	@Transactional(enabled = false)
	public ReportLandTax createReportLandTax(long reportId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the report land tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax that was removed
	* @throws PortalException if a report land tax with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ReportLandTax deleteReportLandTax(long reportId)
		throws PortalException;

	/**
	* Deletes the report land tax from the database. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ReportLandTax deleteReportLandTax(ReportLandTax reportLandTax);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.reportland.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.reportland.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReportLandTax fetchReportLandTax(long reportId);

	/**
	* Returns the report land tax matching the UUID and group.
	*
	* @param uuid the report land tax's UUID
	* @param groupId the primary key of the group
	* @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReportLandTax fetchReportLandTaxByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the report land tax with the primary key.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax
	* @throws PortalException if a report land tax with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReportLandTax getReportLandTax(long reportId)
		throws PortalException;

	/**
	* Returns the report land tax matching the UUID and group.
	*
	* @param uuid the report land tax's UUID
	* @param groupId the primary key of the group
	* @return the matching report land tax
	* @throws PortalException if a matching report land tax could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReportLandTax getReportLandTaxByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.reportland.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of report land taxs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReportLandTax> getReportLandTaxs(int start, int end);

	/**
	* Returns all the report land taxs matching the UUID and company.
	*
	* @param uuid the UUID of the report land taxs
	* @param companyId the primary key of the company
	* @return the matching report land taxs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReportLandTax> getReportLandTaxsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of report land taxs matching the UUID and company.
	*
	* @param uuid the UUID of the report land taxs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching report land taxs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReportLandTax> getReportLandTaxsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns the number of report land taxs.
	*
	* @return the number of report land taxs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReportLandTaxsCount();

	/**
	* Updates the report land tax in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ReportLandTax updateReportLandTax(ReportLandTax reportLandTax);
}