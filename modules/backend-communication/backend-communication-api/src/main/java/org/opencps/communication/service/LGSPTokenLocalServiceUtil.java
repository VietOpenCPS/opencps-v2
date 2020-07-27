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

package org.opencps.communication.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LGSPToken. This utility wraps
 * {@link org.opencps.communication.service.impl.LGSPTokenLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavd
 * @see LGSPTokenLocalService
 * @see org.opencps.communication.service.base.LGSPTokenLocalServiceBaseImpl
 * @see org.opencps.communication.service.impl.LGSPTokenLocalServiceImpl
 * @generated
 */
@ProviderType
public class LGSPTokenLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.communication.service.impl.LGSPTokenLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the lgsp token to the database. Also notifies the appropriate model listeners.
	*
	* @param lgspToken the lgsp token
	* @return the lgsp token that was added
	*/
	public static org.opencps.communication.model.LGSPToken addLGSPToken(
		org.opencps.communication.model.LGSPToken lgspToken) {
		return getService().addLGSPToken(lgspToken);
	}

	/**
	* Creates a new lgsp token with the primary key. Does not add the lgsp token to the database.
	*
	* @param tokenId the primary key for the new lgsp token
	* @return the new lgsp token
	*/
	public static org.opencps.communication.model.LGSPToken createLGSPToken(
		long tokenId) {
		return getService().createLGSPToken(tokenId);
	}

	/**
	* Deletes the lgsp token from the database. Also notifies the appropriate model listeners.
	*
	* @param lgspToken the lgsp token
	* @return the lgsp token that was removed
	*/
	public static org.opencps.communication.model.LGSPToken deleteLGSPToken(
		org.opencps.communication.model.LGSPToken lgspToken) {
		return getService().deleteLGSPToken(lgspToken);
	}

	/**
	* Deletes the lgsp token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tokenId the primary key of the lgsp token
	* @return the lgsp token that was removed
	* @throws PortalException if a lgsp token with the primary key could not be found
	*/
	public static org.opencps.communication.model.LGSPToken deleteLGSPToken(
		long tokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLGSPToken(tokenId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.communication.model.LGSPToken fetchLGSPToken(
		long tokenId) {
		return getService().fetchLGSPToken(tokenId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.communication.model.LGSPToken> getByTokenType(
		String tokenType) {
		return getService().getByTokenType(tokenType);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the lgsp token with the primary key.
	*
	* @param tokenId the primary key of the lgsp token
	* @return the lgsp token
	* @throws PortalException if a lgsp token with the primary key could not be found
	*/
	public static org.opencps.communication.model.LGSPToken getLGSPToken(
		long tokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLGSPToken(tokenId);
	}

	/**
	* Returns a range of all the lgsp tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @return the range of lgsp tokens
	*/
	public static java.util.List<org.opencps.communication.model.LGSPToken> getLGSPTokens(
		int start, int end) {
		return getService().getLGSPTokens(start, end);
	}

	/**
	* Returns the number of lgsp tokens.
	*
	* @return the number of lgsp tokens
	*/
	public static int getLGSPTokensCount() {
		return getService().getLGSPTokensCount();
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
	* Updates the lgsp token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lgspToken the lgsp token
	* @return the lgsp token that was updated
	*/
	public static org.opencps.communication.model.LGSPToken updateLGSPToken(
		org.opencps.communication.model.LGSPToken lgspToken) {
		return getService().updateLGSPToken(lgspToken);
	}

	public static org.opencps.communication.model.LGSPToken updateLGSPToken(
		String token, String tokenType, String refreshToken,
		java.util.Date expiryDate) {
		return getService()
				   .updateLGSPToken(token, tokenType, refreshToken, expiryDate);
	}

	public static LGSPTokenLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LGSPTokenLocalService, LGSPTokenLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LGSPTokenLocalService.class);

		ServiceTracker<LGSPTokenLocalService, LGSPTokenLocalService> serviceTracker =
			new ServiceTracker<LGSPTokenLocalService, LGSPTokenLocalService>(bundle.getBundleContext(),
				LGSPTokenLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}