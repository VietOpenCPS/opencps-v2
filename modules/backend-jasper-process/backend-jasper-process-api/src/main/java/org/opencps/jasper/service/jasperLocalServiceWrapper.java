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

package org.opencps.jasper.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link jasperLocalService}.
 *
 * @author Binhth
 * @see jasperLocalService
 * @generated
 */
@ProviderType
public class jasperLocalServiceWrapper implements jasperLocalService,
	ServiceWrapper<jasperLocalService> {
	public jasperLocalServiceWrapper(jasperLocalService jasperLocalService) {
		_jasperLocalService = jasperLocalService;
	}

	/**
	* Adds the jasper to the database. Also notifies the appropriate model listeners.
	*
	* @param jasper the jasper
	* @return the jasper that was added
	*/
	@Override
	public org.opencps.jasper.model.jasper addjasper(
		org.opencps.jasper.model.jasper jasper) {
		return _jasperLocalService.addjasper(jasper);
	}

	/**
	* Creates a new jasper with the primary key. Does not add the jasper to the database.
	*
	* @param jasperId the primary key for the new jasper
	* @return the new jasper
	*/
	@Override
	public org.opencps.jasper.model.jasper createjasper(long jasperId) {
		return _jasperLocalService.createjasper(jasperId);
	}

	/**
	* Deletes the jasper from the database. Also notifies the appropriate model listeners.
	*
	* @param jasper the jasper
	* @return the jasper that was removed
	*/
	@Override
	public org.opencps.jasper.model.jasper deletejasper(
		org.opencps.jasper.model.jasper jasper) {
		return _jasperLocalService.deletejasper(jasper);
	}

	/**
	* Deletes the jasper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper that was removed
	* @throws PortalException if a jasper with the primary key could not be found
	*/
	@Override
	public org.opencps.jasper.model.jasper deletejasper(long jasperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jasperLocalService.deletejasper(jasperId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jasperLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jasperLocalService.dynamicQuery();
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
		return _jasperLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.jasper.model.impl.jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jasperLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.jasper.model.impl.jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jasperLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _jasperLocalService.dynamicQueryCount(dynamicQuery);
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
		return _jasperLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.jasper.model.jasper fetchjasper(long jasperId) {
		return _jasperLocalService.fetchjasper(jasperId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _jasperLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _jasperLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the jasper with the primary key.
	*
	* @param jasperId the primary key of the jasper
	* @return the jasper
	* @throws PortalException if a jasper with the primary key could not be found
	*/
	@Override
	public org.opencps.jasper.model.jasper getjasper(long jasperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jasperLocalService.getjasper(jasperId);
	}

	/**
	* Returns a range of all the jaspers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.jasper.model.impl.jasperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jaspers
	* @param end the upper bound of the range of jaspers (not inclusive)
	* @return the range of jaspers
	*/
	@Override
	public java.util.List<org.opencps.jasper.model.jasper> getjaspers(
		int start, int end) {
		return _jasperLocalService.getjaspers(start, end);
	}

	/**
	* Returns the number of jaspers.
	*
	* @return the number of jaspers
	*/
	@Override
	public int getjaspersCount() {
		return _jasperLocalService.getjaspersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _jasperLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jasperLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the jasper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jasper the jasper
	* @return the jasper that was updated
	*/
	@Override
	public org.opencps.jasper.model.jasper updatejasper(
		org.opencps.jasper.model.jasper jasper) {
		return _jasperLocalService.updatejasper(jasper);
	}

	@Override
	public jasperLocalService getWrappedService() {
		return _jasperLocalService;
	}

	@Override
	public void setWrappedService(jasperLocalService jasperLocalService) {
		_jasperLocalService = jasperLocalService;
	}

	private jasperLocalService _jasperLocalService;
}