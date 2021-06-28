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

package org.opencps.adminconfig.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.adminconfig.exception.NoSuchApiManagerException;
import org.opencps.adminconfig.model.ApiManager;

/**
 * The persistence interface for the api manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.adminconfig.service.persistence.impl.ApiManagerPersistenceImpl
 * @see ApiManagerUtil
 * @generated
 */
@ProviderType
public interface ApiManagerPersistence extends BasePersistence<ApiManager> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApiManagerUtil} to access the api manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the api manager where apiManagerId = &#63; or throws a {@link NoSuchApiManagerException} if it could not be found.
	*
	* @param apiManagerId the api manager ID
	* @return the matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public ApiManager findByF_ID(long apiManagerId)
		throws NoSuchApiManagerException;

	/**
	* Returns the api manager where apiManagerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param apiManagerId the api manager ID
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public ApiManager fetchByF_ID(long apiManagerId);

	/**
	* Returns the api manager where apiManagerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param apiManagerId the api manager ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public ApiManager fetchByF_ID(long apiManagerId, boolean retrieveFromCache);

	/**
	* Removes the api manager where apiManagerId = &#63; from the database.
	*
	* @param apiManagerId the api manager ID
	* @return the api manager that was removed
	*/
	public ApiManager removeByF_ID(long apiManagerId)
		throws NoSuchApiManagerException;

	/**
	* Returns the number of api managers where apiManagerId = &#63;.
	*
	* @param apiManagerId the api manager ID
	* @return the number of matching api managers
	*/
	public int countByF_ID(long apiManagerId);

	/**
	* Returns the api manager where apiCode = &#63; or throws a {@link NoSuchApiManagerException} if it could not be found.
	*
	* @param apiCode the api code
	* @return the matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public ApiManager findByF_apiCode(String apiCode)
		throws NoSuchApiManagerException;

	/**
	* Returns the api manager where apiCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param apiCode the api code
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public ApiManager fetchByF_apiCode(String apiCode);

	/**
	* Returns the api manager where apiCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param apiCode the api code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public ApiManager fetchByF_apiCode(String apiCode, boolean retrieveFromCache);

	/**
	* Removes the api manager where apiCode = &#63; from the database.
	*
	* @param apiCode the api code
	* @return the api manager that was removed
	*/
	public ApiManager removeByF_apiCode(String apiCode)
		throws NoSuchApiManagerException;

	/**
	* Returns the number of api managers where apiCode = &#63;.
	*
	* @param apiCode the api code
	* @return the number of matching api managers
	*/
	public int countByF_apiCode(String apiCode);

	/**
	* Returns all the api managers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching api managers
	*/
	public java.util.List<ApiManager> findByF_GID(long groupId);

	/**
	* Returns a range of all the api managers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @return the range of matching api managers
	*/
	public java.util.List<ApiManager> findByF_GID(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the api managers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching api managers
	*/
	public java.util.List<ApiManager> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator);

	/**
	* Returns an ordered range of all the api managers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching api managers
	*/
	public java.util.List<ApiManager> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public ApiManager findByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator)
		throws NoSuchApiManagerException;

	/**
	* Returns the first api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public ApiManager fetchByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator);

	/**
	* Returns the last api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching api manager
	* @throws NoSuchApiManagerException if a matching api manager could not be found
	*/
	public ApiManager findByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator)
		throws NoSuchApiManagerException;

	/**
	* Returns the last api manager in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching api manager, or <code>null</code> if a matching api manager could not be found
	*/
	public ApiManager fetchByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator);

	/**
	* Returns the api managers before and after the current api manager in the ordered set where groupId = &#63;.
	*
	* @param apiManagerId the primary key of the current api manager
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next api manager
	* @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	*/
	public ApiManager[] findByF_GID_PrevAndNext(long apiManagerId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator)
		throws NoSuchApiManagerException;

	/**
	* Removes all the api managers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_GID(long groupId);

	/**
	* Returns the number of api managers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching api managers
	*/
	public int countByF_GID(long groupId);

	/**
	* Caches the api manager in the entity cache if it is enabled.
	*
	* @param apiManager the api manager
	*/
	public void cacheResult(ApiManager apiManager);

	/**
	* Caches the api managers in the entity cache if it is enabled.
	*
	* @param apiManagers the api managers
	*/
	public void cacheResult(java.util.List<ApiManager> apiManagers);

	/**
	* Creates a new api manager with the primary key. Does not add the api manager to the database.
	*
	* @param apiManagerId the primary key for the new api manager
	* @return the new api manager
	*/
	public ApiManager create(long apiManagerId);

	/**
	* Removes the api manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager that was removed
	* @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	*/
	public ApiManager remove(long apiManagerId)
		throws NoSuchApiManagerException;

	public ApiManager updateImpl(ApiManager apiManager);

	/**
	* Returns the api manager with the primary key or throws a {@link NoSuchApiManagerException} if it could not be found.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager
	* @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	*/
	public ApiManager findByPrimaryKey(long apiManagerId)
		throws NoSuchApiManagerException;

	/**
	* Returns the api manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param apiManagerId the primary key of the api manager
	* @return the api manager, or <code>null</code> if a api manager with the primary key could not be found
	*/
	public ApiManager fetchByPrimaryKey(long apiManagerId);

	@Override
	public java.util.Map<java.io.Serializable, ApiManager> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the api managers.
	*
	* @return the api managers
	*/
	public java.util.List<ApiManager> findAll();

	/**
	* Returns a range of all the api managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @return the range of api managers
	*/
	public java.util.List<ApiManager> findAll(int start, int end);

	/**
	* Returns an ordered range of all the api managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of api managers
	*/
	public java.util.List<ApiManager> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator);

	/**
	* Returns an ordered range of all the api managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of api managers
	* @param end the upper bound of the range of api managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of api managers
	*/
	public java.util.List<ApiManager> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApiManager> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the api managers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of api managers.
	*
	* @return the number of api managers
	*/
	public int countAll();
}