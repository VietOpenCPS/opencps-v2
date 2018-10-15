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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HmacAuthenLocalService}.
 *
 * @author khoavu
 * @see HmacAuthenLocalService
 * @generated
 */
@ProviderType
public class HmacAuthenLocalServiceWrapper implements HmacAuthenLocalService,
	ServiceWrapper<HmacAuthenLocalService> {
	public HmacAuthenLocalServiceWrapper(
		HmacAuthenLocalService hmacAuthenLocalService) {
		_hmacAuthenLocalService = hmacAuthenLocalService;
	}

	/**
	* Adds the hmac authen to the database. Also notifies the appropriate model listeners.
	*
	* @param hmacAuthen the hmac authen
	* @return the hmac authen that was added
	*/
	@Override
	public org.opencps.usermgt.model.HmacAuthen addHmacAuthen(
		org.opencps.usermgt.model.HmacAuthen hmacAuthen) {
		return _hmacAuthenLocalService.addHmacAuthen(hmacAuthen);
	}

	/**
	* Creates a new hmac authen with the primary key. Does not add the hmac authen to the database.
	*
	* @param hmacAuthId the primary key for the new hmac authen
	* @return the new hmac authen
	*/
	@Override
	public org.opencps.usermgt.model.HmacAuthen createHmacAuthen(
		long hmacAuthId) {
		return _hmacAuthenLocalService.createHmacAuthen(hmacAuthId);
	}

	/**
	* Deletes the hmac authen from the database. Also notifies the appropriate model listeners.
	*
	* @param hmacAuthen the hmac authen
	* @return the hmac authen that was removed
	*/
	@Override
	public org.opencps.usermgt.model.HmacAuthen deleteHmacAuthen(
		org.opencps.usermgt.model.HmacAuthen hmacAuthen) {
		return _hmacAuthenLocalService.deleteHmacAuthen(hmacAuthen);
	}

	/**
	* Deletes the hmac authen with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen that was removed
	* @throws PortalException if a hmac authen with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.HmacAuthen deleteHmacAuthen(
		long hmacAuthId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hmacAuthenLocalService.deleteHmacAuthen(hmacAuthId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hmacAuthenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _hmacAuthenLocalService.dynamicQuery();
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
		return _hmacAuthenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hmacAuthenLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hmacAuthenLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _hmacAuthenLocalService.dynamicQueryCount(dynamicQuery);
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
		return _hmacAuthenLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.HmacAuthen fetchHmacAuthen(long hmacAuthId) {
		return _hmacAuthenLocalService.fetchHmacAuthen(hmacAuthId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _hmacAuthenLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the hmac authen with the primary key.
	*
	* @param hmacAuthId the primary key of the hmac authen
	* @return the hmac authen
	* @throws PortalException if a hmac authen with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.HmacAuthen getHmacAuthen(long hmacAuthId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hmacAuthenLocalService.getHmacAuthen(hmacAuthId);
	}

	/**
	* Returns a range of all the hmac authens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.HmacAuthenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hmac authens
	* @param end the upper bound of the range of hmac authens (not inclusive)
	* @return the range of hmac authens
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.HmacAuthen> getHmacAuthens(
		int start, int end) {
		return _hmacAuthenLocalService.getHmacAuthens(start, end);
	}

	/**
	* Returns the number of hmac authens.
	*
	* @return the number of hmac authens
	*/
	@Override
	public int getHmacAuthensCount() {
		return _hmacAuthenLocalService.getHmacAuthensCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _hmacAuthenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _hmacAuthenLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hmacAuthenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hmac authen in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hmacAuthen the hmac authen
	* @return the hmac authen that was updated
	*/
	@Override
	public org.opencps.usermgt.model.HmacAuthen updateHmacAuthen(
		org.opencps.usermgt.model.HmacAuthen hmacAuthen) {
		return _hmacAuthenLocalService.updateHmacAuthen(hmacAuthen);
	}

	@Override
	public HmacAuthenLocalService getWrappedService() {
		return _hmacAuthenLocalService;
	}

	@Override
	public void setWrappedService(HmacAuthenLocalService hmacAuthenLocalService) {
		_hmacAuthenLocalService = hmacAuthenLocalService;
	}

	private HmacAuthenLocalService _hmacAuthenLocalService;
}