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

package org.opencps.dossiermgt.service;

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

import org.opencps.dossiermgt.model.ApplicableInfo;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ApplicableInfo. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ApplicableInfoLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ApplicableInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ApplicableInfoLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ApplicableInfoLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicableInfoLocalServiceUtil} to access the applicable info local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ApplicableInfoLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the applicable info to the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfo the applicable info
	* @return the applicable info that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ApplicableInfo addApplicableInfo(ApplicableInfo applicableInfo);

	public ApplicableInfo addApplicableInfo(long groupId,
		long serviceConfigMappingId, String govAgencyCode, String serviceCode,
		int serviceLevel, ServiceContext context);

	/**
	* Creates a new applicable info with the primary key. Does not add the applicable info to the database.
	*
	* @param applicableInfoId the primary key for the new applicable info
	* @return the new applicable info
	*/
	@Transactional(enabled = false)
	public ApplicableInfo createApplicableInfo(long applicableInfoId);

	/**
	* Deletes the applicable info from the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfo the applicable info
	* @return the applicable info that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ApplicableInfo deleteApplicableInfo(ApplicableInfo applicableInfo);

	/**
	* Deletes the applicable info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info that was removed
	* @throws PortalException if a applicable info with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ApplicableInfo deleteApplicableInfo(long applicableInfoId)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ApplicableInfo fetchApplicableInfo(long applicableInfoId);

	/**
	* Returns the applicable info matching the UUID and group.
	*
	* @param uuid the applicable info's UUID
	* @param groupId the primary key of the group
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApplicableInfo fetchApplicableInfoByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApplicableInfo fetchByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the applicable info with the primary key.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info
	* @throws PortalException if a applicable info with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApplicableInfo getApplicableInfo(long applicableInfoId)
		throws PortalException;

	/**
	* Returns the applicable info matching the UUID and group.
	*
	* @param uuid the applicable info's UUID
	* @param groupId the primary key of the group
	* @return the matching applicable info
	* @throws PortalException if a matching applicable info could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApplicableInfo getApplicableInfoByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the applicable infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @return the range of applicable infos
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApplicableInfo> getApplicableInfos(int start, int end);

	/**
	* Returns all the applicable infos matching the UUID and company.
	*
	* @param uuid the UUID of the applicable infos
	* @param companyId the primary key of the company
	* @return the matching applicable infos, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApplicableInfo> getApplicableInfosByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of applicable infos matching the UUID and company.
	*
	* @param uuid the UUID of the applicable infos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching applicable infos, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApplicableInfo> getApplicableInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator);

	/**
	* Returns the number of applicable infos.
	*
	* @return the number of applicable infos
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getApplicableInfosCount();

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
	* Updates the applicable info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicableInfo the applicable info
	* @return the applicable info that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ApplicableInfo updateApplicableInfo(ApplicableInfo applicableInfo);
}