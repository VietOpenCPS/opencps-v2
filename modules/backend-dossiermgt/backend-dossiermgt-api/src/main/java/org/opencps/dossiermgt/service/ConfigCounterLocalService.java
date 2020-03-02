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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ConfigCounter;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ConfigCounter. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ConfigCounterLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ConfigCounterLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ConfigCounterLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ConfigCounterLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ConfigCounterLocalServiceUtil} to access the config counter local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ConfigCounterLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the config counter to the database. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ConfigCounter addConfigCounter(ConfigCounter configCounter);

	/**
	* Creates a new config counter with the primary key. Does not add the config counter to the database.
	*
	* @param configCounterId the primary key for the new config counter
	* @return the new config counter
	*/
	@Transactional(enabled = false)
	public ConfigCounter createConfigCounter(long configCounterId);

	/**
	* Deletes the config counter from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ConfigCounter deleteConfigCounter(ConfigCounter configCounter);

	/**
	* Deletes the config counter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter that was removed
	* @throws PortalException if a config counter with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ConfigCounter deleteConfigCounter(long configCounterId)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ConfigCounter fetchByCountrCode(long groupId, String counterCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ConfigCounter fetchConfigCounter(long configCounterId);

	/**
	* Returns the config counter matching the UUID and group.
	*
	* @param uuid the config counter's UUID
	* @param groupId the primary key of the group
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ConfigCounter fetchConfigCounterByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the config counter with the primary key.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter
	* @throws PortalException if a config counter with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ConfigCounter getConfigCounter(long configCounterId)
		throws PortalException;

	/**
	* Returns the config counter matching the UUID and group.
	*
	* @param uuid the config counter's UUID
	* @param groupId the primary key of the group
	* @return the matching config counter
	* @throws PortalException if a matching config counter could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ConfigCounter getConfigCounterByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of config counters
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ConfigCounter> getConfigCounters(int start, int end);

	/**
	* Returns all the config counters matching the UUID and company.
	*
	* @param uuid the UUID of the config counters
	* @param companyId the primary key of the company
	* @return the matching config counters, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ConfigCounter> getConfigCountersByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of config counters matching the UUID and company.
	*
	* @param uuid the UUID of the config counters
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching config counters, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ConfigCounter> getConfigCountersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns the number of config counters.
	*
	* @return the number of config counters
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getConfigCountersCount();

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
	* Updates the config counter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ConfigCounter updateConfigCounter(ConfigCounter configCounter);
}