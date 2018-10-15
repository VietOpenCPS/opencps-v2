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

package org.opencps.usermgt.service;

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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.Visibility;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Visibility. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see VisibilityLocalServiceUtil
 * @see org.opencps.usermgt.service.base.VisibilityLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.VisibilityLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface VisibilityLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VisibilityLocalServiceUtil} to access the visibility local service. Add custom service methods to {@link org.opencps.usermgt.service.impl.VisibilityLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the visibility to the database. Also notifies the appropriate model listeners.
	*
	* @param visibility the visibility
	* @return the visibility that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Visibility addVisibility(Visibility visibility);

	/**
	* Creates a new visibility with the primary key. Does not add the visibility to the database.
	*
	* @param visibilityId the primary key for the new visibility
	* @return the new visibility
	*/
	@Transactional(enabled = false)
	public Visibility createVisibility(long visibilityId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the visibility with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility that was removed
	* @throws PortalException if a visibility with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Visibility deleteVisibility(long visibilityId)
		throws PortalException;

	/**
	* Deletes the visibility from the database. Also notifies the appropriate model listeners.
	*
	* @param visibility the visibility
	* @return the visibility that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Visibility deleteVisibility(Visibility visibility);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Visibility fetchVisibility(long visibilityId);

	/**
	* Returns the visibility matching the UUID and group.
	*
	* @param uuid the visibility's UUID
	* @param groupId the primary key of the group
	* @return the matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Visibility fetchVisibilityByUuidAndGroupId(String uuid, long groupId);

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
	* Returns a range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of visibilities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Visibility> getVisibilities(int start, int end);

	/**
	* Returns all the visibilities matching the UUID and company.
	*
	* @param uuid the UUID of the visibilities
	* @param companyId the primary key of the company
	* @return the matching visibilities, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Visibility> getVisibilitiesByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of visibilities matching the UUID and company.
	*
	* @param uuid the UUID of the visibilities
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching visibilities, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Visibility> getVisibilitiesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns the number of visibilities.
	*
	* @return the number of visibilities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getVisibilitiesCount();

	/**
	* Returns the visibility with the primary key.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility
	* @throws PortalException if a visibility with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Visibility getVisibility(long visibilityId)
		throws PortalException;

	/**
	* Returns the visibility matching the UUID and group.
	*
	* @param uuid the visibility's UUID
	* @param groupId the primary key of the group
	* @return the matching visibility
	* @throws PortalException if a matching visibility could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Visibility getVisibilityByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Updates the visibility in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param visibility the visibility
	* @return the visibility that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Visibility updateVisibility(Visibility visibility);
}