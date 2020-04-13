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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AccessToken. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.AccessTokenLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see AccessTokenLocalService
 * @see org.opencps.dossiermgt.service.base.AccessTokenLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.AccessTokenLocalServiceImpl
 * @generated
 */
@ProviderType
public class AccessTokenLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.AccessTokenLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the access token to the database. Also notifies the appropriate model listeners.
	*
	* @param accessToken the access token
	* @return the access token that was added
	*/
	public static org.opencps.dossiermgt.model.AccessToken addAccessToken(
		org.opencps.dossiermgt.model.AccessToken accessToken) {
		return getService().addAccessToken(accessToken);
	}

	public static org.opencps.dossiermgt.model.AccessToken addAccessToken(
		long companyId, long groupId, String token, String className) {
		return getService().addAccessToken(companyId, groupId, token, className);
	}

	/**
	* Creates a new access token with the primary key. Does not add the access token to the database.
	*
	* @param accessTokenId the primary key for the new access token
	* @return the new access token
	*/
	public static org.opencps.dossiermgt.model.AccessToken createAccessToken(
		long accessTokenId) {
		return getService().createAccessToken(accessTokenId);
	}

	/**
	* Deletes the access token from the database. Also notifies the appropriate model listeners.
	*
	* @param accessToken the access token
	* @return the access token that was removed
	*/
	public static org.opencps.dossiermgt.model.AccessToken deleteAccessToken(
		org.opencps.dossiermgt.model.AccessToken accessToken) {
		return getService().deleteAccessToken(accessToken);
	}

	/**
	* Deletes the access token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token that was removed
	* @throws PortalException if a access token with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.AccessToken deleteAccessToken(
		long accessTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccessToken(accessTokenId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.dossiermgt.model.AccessToken fetchAccessToken(
		long accessTokenId) {
		return getService().fetchAccessToken(accessTokenId);
	}

	/**
	* Returns the access token with the primary key.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token
	* @throws PortalException if a access token with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.AccessToken getAccessToken(
		long accessTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccessToken(accessTokenId);
	}

	public static org.opencps.dossiermgt.model.AccessToken getAccessToken(
		long groupId, String className) {
		return getService().getAccessToken(groupId, className);
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
	public static java.util.List<org.opencps.dossiermgt.model.AccessToken> getAccessTokens(
		int start, int end) {
		return getService().getAccessTokens(start, end);
	}

	public static java.util.List<org.opencps.dossiermgt.model.AccessToken> getAccessTokens(
		long groupId, String className) {
		return getService().getAccessTokens(groupId, className);
	}

	/**
	* Returns the number of access tokens.
	*
	* @return the number of access tokens
	*/
	public static int getAccessTokensCount() {
		return getService().getAccessTokensCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the access token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accessToken the access token
	* @return the access token that was updated
	*/
	public static org.opencps.dossiermgt.model.AccessToken updateAccessToken(
		org.opencps.dossiermgt.model.AccessToken accessToken) {
		return getService().updateAccessToken(accessToken);
	}

	public static AccessTokenLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AccessTokenLocalService, AccessTokenLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AccessTokenLocalService.class);

		ServiceTracker<AccessTokenLocalService, AccessTokenLocalService> serviceTracker =
			new ServiceTracker<AccessTokenLocalService, AccessTokenLocalService>(bundle.getBundleContext(),
				AccessTokenLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}