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

package org.opencps.kyso.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link kysoLocalService}.
 *
 * @author Binhth
 * @see kysoLocalService
 * @generated
 */
@ProviderType
public class kysoLocalServiceWrapper implements kysoLocalService,
	ServiceWrapper<kysoLocalService> {
	public kysoLocalServiceWrapper(kysoLocalService kysoLocalService) {
		_kysoLocalService = kysoLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _kysoLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kysoLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _kysoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kysoLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kysoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of kysos.
	*
	* @return the number of kysos
	*/
	@Override
	public int getkysosCount() {
		return _kysoLocalService.getkysosCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _kysoLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _kysoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.kyso.model.impl.kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _kysoLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.kyso.model.impl.kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _kysoLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the kysos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.kyso.model.impl.kysoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kysos
	* @param end the upper bound of the range of kysos (not inclusive)
	* @return the range of kysos
	*/
	@Override
	public java.util.List<org.opencps.kyso.model.kyso> getkysos(int start,
		int end) {
		return _kysoLocalService.getkysos(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _kysoLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _kysoLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Adds the kyso to the database. Also notifies the appropriate model listeners.
	*
	* @param kyso the kyso
	* @return the kyso that was added
	*/
	@Override
	public org.opencps.kyso.model.kyso addkyso(org.opencps.kyso.model.kyso kyso) {
		return _kysoLocalService.addkyso(kyso);
	}

	/**
	* Creates a new kyso with the primary key. Does not add the kyso to the database.
	*
	* @param jasperId the primary key for the new kyso
	* @return the new kyso
	*/
	@Override
	public org.opencps.kyso.model.kyso createkyso(long jasperId) {
		return _kysoLocalService.createkyso(jasperId);
	}

	/**
	* Deletes the kyso with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso that was removed
	* @throws PortalException if a kyso with the primary key could not be found
	*/
	@Override
	public org.opencps.kyso.model.kyso deletekyso(long jasperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kysoLocalService.deletekyso(jasperId);
	}

	/**
	* Deletes the kyso from the database. Also notifies the appropriate model listeners.
	*
	* @param kyso the kyso
	* @return the kyso that was removed
	*/
	@Override
	public org.opencps.kyso.model.kyso deletekyso(
		org.opencps.kyso.model.kyso kyso) {
		return _kysoLocalService.deletekyso(kyso);
	}

	@Override
	public org.opencps.kyso.model.kyso fetchkyso(long jasperId) {
		return _kysoLocalService.fetchkyso(jasperId);
	}

	/**
	* Returns the kyso with the primary key.
	*
	* @param jasperId the primary key of the kyso
	* @return the kyso
	* @throws PortalException if a kyso with the primary key could not be found
	*/
	@Override
	public org.opencps.kyso.model.kyso getkyso(long jasperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kysoLocalService.getkyso(jasperId);
	}

	/**
	* Updates the kyso in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kyso the kyso
	* @return the kyso that was updated
	*/
	@Override
	public org.opencps.kyso.model.kyso updatekyso(
		org.opencps.kyso.model.kyso kyso) {
		return _kysoLocalService.updatekyso(kyso);
	}

	@Override
	public kysoLocalService getWrappedService() {
		return _kysoLocalService;
	}

	@Override
	public void setWrappedService(kysoLocalService kysoLocalService) {
		_kysoLocalService = kysoLocalService;
	}

	private kysoLocalService _kysoLocalService;
}