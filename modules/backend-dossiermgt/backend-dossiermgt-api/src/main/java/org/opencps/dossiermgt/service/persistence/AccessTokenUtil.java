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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.AccessToken;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the access token service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.AccessTokenPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see AccessTokenPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.AccessTokenPersistenceImpl
 * @generated
 */
@ProviderType
public class AccessTokenUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AccessToken accessToken) {
		getPersistence().clearCache(accessToken);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccessToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccessToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccessToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccessToken> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccessToken update(AccessToken accessToken) {
		return getPersistence().update(accessToken);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccessToken update(AccessToken accessToken,
		ServiceContext serviceContext) {
		return getPersistence().update(accessToken, serviceContext);
	}

	/**
	* Returns all the access tokens where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the matching access tokens
	*/
	public static List<AccessToken> findByF_GID_ClassName(long groupId,
		String className) {
		return getPersistence().findByF_GID_ClassName(groupId, className);
	}

	/**
	* Returns a range of all the access tokens where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @return the range of matching access tokens
	*/
	public static List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end) {
		return getPersistence()
				   .findByF_GID_ClassName(groupId, className, start, end);
	}

	/**
	* Returns an ordered range of all the access tokens where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching access tokens
	*/
	public static List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end,
		OrderByComparator<AccessToken> orderByComparator) {
		return getPersistence()
				   .findByF_GID_ClassName(groupId, className, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the access tokens where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching access tokens
	*/
	public static List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end,
		OrderByComparator<AccessToken> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_ClassName(groupId, className, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching access token
	* @throws NoSuchAccessTokenException if a matching access token could not be found
	*/
	public static AccessToken findByF_GID_ClassName_First(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchAccessTokenException {
		return getPersistence()
				   .findByF_GID_ClassName_First(groupId, className,
			orderByComparator);
	}

	/**
	* Returns the first access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching access token, or <code>null</code> if a matching access token could not be found
	*/
	public static AccessToken fetchByF_GID_ClassName_First(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_ClassName_First(groupId, className,
			orderByComparator);
	}

	/**
	* Returns the last access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching access token
	* @throws NoSuchAccessTokenException if a matching access token could not be found
	*/
	public static AccessToken findByF_GID_ClassName_Last(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchAccessTokenException {
		return getPersistence()
				   .findByF_GID_ClassName_Last(groupId, className,
			orderByComparator);
	}

	/**
	* Returns the last access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching access token, or <code>null</code> if a matching access token could not be found
	*/
	public static AccessToken fetchByF_GID_ClassName_Last(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_ClassName_Last(groupId, className,
			orderByComparator);
	}

	/**
	* Returns the access tokens before and after the current access token in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param accessTokenId the primary key of the current access token
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next access token
	* @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	*/
	public static AccessToken[] findByF_GID_ClassName_PrevAndNext(
		long accessTokenId, long groupId, String className,
		OrderByComparator<AccessToken> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchAccessTokenException {
		return getPersistence()
				   .findByF_GID_ClassName_PrevAndNext(accessTokenId, groupId,
			className, orderByComparator);
	}

	/**
	* Removes all the access tokens where groupId = &#63; and className = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	*/
	public static void removeByF_GID_ClassName(long groupId, String className) {
		getPersistence().removeByF_GID_ClassName(groupId, className);
	}

	/**
	* Returns the number of access tokens where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the number of matching access tokens
	*/
	public static int countByF_GID_ClassName(long groupId, String className) {
		return getPersistence().countByF_GID_ClassName(groupId, className);
	}

	/**
	* Caches the access token in the entity cache if it is enabled.
	*
	* @param accessToken the access token
	*/
	public static void cacheResult(AccessToken accessToken) {
		getPersistence().cacheResult(accessToken);
	}

	/**
	* Caches the access tokens in the entity cache if it is enabled.
	*
	* @param accessTokens the access tokens
	*/
	public static void cacheResult(List<AccessToken> accessTokens) {
		getPersistence().cacheResult(accessTokens);
	}

	/**
	* Creates a new access token with the primary key. Does not add the access token to the database.
	*
	* @param accessTokenId the primary key for the new access token
	* @return the new access token
	*/
	public static AccessToken create(long accessTokenId) {
		return getPersistence().create(accessTokenId);
	}

	/**
	* Removes the access token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token that was removed
	* @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	*/
	public static AccessToken remove(long accessTokenId)
		throws org.opencps.dossiermgt.exception.NoSuchAccessTokenException {
		return getPersistence().remove(accessTokenId);
	}

	public static AccessToken updateImpl(AccessToken accessToken) {
		return getPersistence().updateImpl(accessToken);
	}

	/**
	* Returns the access token with the primary key or throws a {@link NoSuchAccessTokenException} if it could not be found.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token
	* @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	*/
	public static AccessToken findByPrimaryKey(long accessTokenId)
		throws org.opencps.dossiermgt.exception.NoSuchAccessTokenException {
		return getPersistence().findByPrimaryKey(accessTokenId);
	}

	/**
	* Returns the access token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accessTokenId the primary key of the access token
	* @return the access token, or <code>null</code> if a access token with the primary key could not be found
	*/
	public static AccessToken fetchByPrimaryKey(long accessTokenId) {
		return getPersistence().fetchByPrimaryKey(accessTokenId);
	}

	public static java.util.Map<java.io.Serializable, AccessToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the access tokens.
	*
	* @return the access tokens
	*/
	public static List<AccessToken> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the access tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @return the range of access tokens
	*/
	public static List<AccessToken> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the access tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of access tokens
	*/
	public static List<AccessToken> findAll(int start, int end,
		OrderByComparator<AccessToken> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the access tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of access tokens
	* @param end the upper bound of the range of access tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of access tokens
	*/
	public static List<AccessToken> findAll(int start, int end,
		OrderByComparator<AccessToken> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the access tokens from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of access tokens.
	*
	* @return the number of access tokens
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccessTokenPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AccessTokenPersistence, AccessTokenPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AccessTokenPersistence.class);

		ServiceTracker<AccessTokenPersistence, AccessTokenPersistence> serviceTracker =
			new ServiceTracker<AccessTokenPersistence, AccessTokenPersistence>(bundle.getBundleContext(),
				AccessTokenPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}