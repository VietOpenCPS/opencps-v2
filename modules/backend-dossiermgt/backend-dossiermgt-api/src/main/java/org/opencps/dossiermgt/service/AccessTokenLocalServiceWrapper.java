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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccessTokenLocalService}.
 *
 * @author huymq
 * @see AccessTokenLocalService
 * @generated
 */
@ProviderType
public class AccessTokenLocalServiceWrapper implements AccessTokenLocalService,
	ServiceWrapper<AccessTokenLocalService> {
	public AccessTokenLocalServiceWrapper(
		AccessTokenLocalService accessTokenLocalService) {
		_accessTokenLocalService = accessTokenLocalService;
	}

	/**
	* Adds the access token to the database. Also notifies the appropriate model listeners.
	*
	* @param accessToken the access token
	* @return the access token that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.AccessToken addAccessToken(
		org.opencps.dossiermgt.model.AccessToken accessToken) {
		return _accessTokenLocalService.addAccessToken(accessToken);
	}

	@Override
	public org.opencps.dossiermgt.model.AccessToken addAccessToken(
		long companyId, long groupId, String token, String className) {
		return _accessTokenLocalService.addAccessToken(companyId, groupId,
			token, className);
	}

	/**
	* Creates a new access token with the primary key. Does not add the access token to the database.
	*
	* @param accessTokenId the primary key for the new access token
	* @return the new access token
	*/
	@Override
	public org.opencps.dossiermgt.model.AccessToken createAccessToken(
		long accessTokenId) {
		return _accessTokenLocalService.createAccessToken(accessTokenId);
	}

	/**
	* Deletes the access token from the database. Also notifies the appropriate model listeners.
	*
	* @param accessToken the access token
	* @return the access token that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.AccessToken deleteAccessToken(
		org.opencps.dossiermgt.model.AccessToken accessToken) {
		return _accessTokenLocalService.deleteAccessToken(accessToken);
	}

	/**
	* Deletes the access token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token that was removed
	* @throws PortalException if a access token with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.AccessToken deleteAccessToken(
		long accessTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accessTokenLocalService.deleteAccessToken(accessTokenId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accessTokenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accessTokenLocalService.dynamicQuery();
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
		return _accessTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accessTokenLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accessTokenLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _accessTokenLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accessTokenLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.AccessToken fetchAccessToken(
		long accessTokenId) {
		return _accessTokenLocalService.fetchAccessToken(accessTokenId);
	}

	/**
	* Returns the access token with the primary key.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token
	* @throws PortalException if a access token with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.AccessToken getAccessToken(
		long accessTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accessTokenLocalService.getAccessToken(accessTokenId);
	}

	@Override
	public org.opencps.dossiermgt.model.AccessToken getAccessToken(
		long groupId, String className) {
		return _accessTokenLocalService.getAccessToken(groupId, className);
	}

	/**
	* Returns a range of all the access tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @return the range of access tokens
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.AccessToken> getAccessTokens(
		int start, int end) {
		return _accessTokenLocalService.getAccessTokens(start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.AccessToken> getAccessTokens(
		long groupId, String className) {
		return _accessTokenLocalService.getAccessTokens(groupId, className);
	}

	/**
	* Returns the number of access tokens.
	*
	* @return the number of access tokens
	*/
	@Override
	public int getAccessTokensCount() {
		return _accessTokenLocalService.getAccessTokensCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accessTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accessTokenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _accessTokenLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accessTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the access token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accessToken the access token
	* @return the access token that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.AccessToken updateAccessToken(
		org.opencps.dossiermgt.model.AccessToken accessToken) {
		return _accessTokenLocalService.updateAccessToken(accessToken);
	}

	@Override
	public AccessTokenLocalService getWrappedService() {
		return _accessTokenLocalService;
	}

	@Override
	public void setWrappedService(
		AccessTokenLocalService accessTokenLocalService) {
		_accessTokenLocalService = accessTokenLocalService;
	}

	private AccessTokenLocalService _accessTokenLocalService;
}