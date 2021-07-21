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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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

import org.opencps.dossiermgt.model.CsdlDcUser;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CsdlDcUser. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see CsdlDcUserLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.CsdlDcUserLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.CsdlDcUserLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CsdlDcUserLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CsdlDcUserLocalServiceUtil} to access the csdl dc user local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.CsdlDcUserLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the csdl dc user to the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcUser the csdl dc user
	* @return the csdl dc user that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CsdlDcUser addCsdlDcUser(CsdlDcUser csdlDcUser);

	/**
	* Creates a new csdl dc user with the primary key. Does not add the csdl dc user to the database.
	*
	* @param idDcUser the primary key for the new csdl dc user
	* @return the new csdl dc user
	*/
	@Transactional(enabled = false)
	public CsdlDcUser createCsdlDcUser(long idDcUser);

	/**
	* Deletes the csdl dc user from the database. Also notifies the appropriate model listeners.
	*
	* @param csdlDcUser the csdl dc user
	* @return the csdl dc user that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CsdlDcUser deleteCsdlDcUser(CsdlDcUser csdlDcUser);

	/**
	* Deletes the csdl dc user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user that was removed
	* @throws PortalException if a csdl dc user with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CsdlDcUser deleteCsdlDcUser(long idDcUser) throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public CsdlDcUser fetchCsdlDcUser(long idDcUser);

	/**
	* Returns the csdl dc user matching the UUID and group.
	*
	* @param uuid the csdl dc user's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CsdlDcUser fetchCsdlDcUserByUuidAndGroupId(String uuid, long groupId);

	public CsdlDcUser findByGovAndEmailAndStatus(String govAgencyCode,
		String email, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the csdl dc user with the primary key.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user
	* @throws PortalException if a csdl dc user with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CsdlDcUser getCsdlDcUser(long idDcUser) throws PortalException;

	/**
	* Returns the csdl dc user matching the UUID and group.
	*
	* @param uuid the csdl dc user's UUID
	* @param groupId the primary key of the group
	* @return the matching csdl dc user
	* @throws PortalException if a matching csdl dc user could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CsdlDcUser getCsdlDcUserByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the csdl dc users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @return the range of csdl dc users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CsdlDcUser> getCsdlDcUsers(int start, int end);

	/**
	* Returns all the csdl dc users matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc users
	* @param companyId the primary key of the company
	* @return the matching csdl dc users, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CsdlDcUser> getCsdlDcUsersByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of csdl dc users matching the UUID and company.
	*
	* @param uuid the UUID of the csdl dc users
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching csdl dc users, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CsdlDcUser> getCsdlDcUsersByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns the number of csdl dc users.
	*
	* @return the number of csdl dc users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCsdlDcUsersCount();

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
	* Updates the csdl dc user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param csdlDcUser the csdl dc user
	* @return the csdl dc user that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CsdlDcUser updateCsdlDcUser(CsdlDcUser csdlDcUser);
}