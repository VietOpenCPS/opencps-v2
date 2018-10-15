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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchActionConfigException;
import org.opencps.dossiermgt.model.ActionConfig;

/**
 * The persistence interface for the action config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ActionConfigPersistenceImpl
 * @see ActionConfigUtil
 * @generated
 */
@ProviderType
public interface ActionConfigPersistence extends BasePersistence<ActionConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActionConfigUtil} to access the action config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the action configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid(String uuid);

	/**
	* Returns a range of all the action configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the action configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns an ordered range of all the action configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Returns the first action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns the last action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Returns the last action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns the action configs before and after the current action config in the ordered set where uuid = &#63;.
	*
	* @param actionConfigId the primary key of the current action config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public ActionConfig[] findByUuid_PrevAndNext(long actionConfigId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Removes all the action configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of action configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching action configs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the action config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActionConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchActionConfigException;

	/**
	* Returns the action config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the action config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the action config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the action config that was removed
	*/
	public ActionConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchActionConfigException;

	/**
	* Returns the number of action configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching action configs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns an ordered range of all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching action configs
	*/
	public java.util.List<ActionConfig> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Returns the first action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns the last action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Returns the last action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns the action configs before and after the current action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param actionConfigId the primary key of the current action config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public ActionConfig[] findByUuid_C_PrevAndNext(long actionConfigId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Removes all the action configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of action configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching action configs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the action configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching action configs
	*/
	public java.util.List<ActionConfig> findByF_BY_GID(long groupId);

	/**
	* Returns a range of all the action configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of matching action configs
	*/
	public java.util.List<ActionConfig> findByF_BY_GID(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the action configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action configs
	*/
	public java.util.List<ActionConfig> findByF_BY_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns an ordered range of all the action configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching action configs
	*/
	public java.util.List<ActionConfig> findByF_BY_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByF_BY_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Returns the first action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByF_BY_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns the last action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByF_BY_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Returns the last action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByF_BY_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns the action configs before and after the current action config in the ordered set where groupId = &#63;.
	*
	* @param actionConfigId the primary key of the current action config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public ActionConfig[] findByF_BY_GID_PrevAndNext(long actionConfigId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator)
		throws NoSuchActionConfigException;

	/**
	* Removes all the action configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_BY_GID(long groupId);

	/**
	* Returns the number of action configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching action configs
	*/
	public int countByF_BY_GID(long groupId);

	/**
	* Returns the action config where groupId = &#63; and actionCode = &#63; or throws a {@link NoSuchActionConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public ActionConfig findByF_BY_ActionCode(long groupId, String actionCode)
		throws NoSuchActionConfigException;

	/**
	* Returns the action config where groupId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByF_BY_ActionCode(long groupId, String actionCode);

	/**
	* Returns the action config where groupId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public ActionConfig fetchByF_BY_ActionCode(long groupId, String actionCode,
		boolean retrieveFromCache);

	/**
	* Removes the action config where groupId = &#63; and actionCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the action config that was removed
	*/
	public ActionConfig removeByF_BY_ActionCode(long groupId, String actionCode)
		throws NoSuchActionConfigException;

	/**
	* Returns the number of action configs where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the number of matching action configs
	*/
	public int countByF_BY_ActionCode(long groupId, String actionCode);

	/**
	* Caches the action config in the entity cache if it is enabled.
	*
	* @param actionConfig the action config
	*/
	public void cacheResult(ActionConfig actionConfig);

	/**
	* Caches the action configs in the entity cache if it is enabled.
	*
	* @param actionConfigs the action configs
	*/
	public void cacheResult(java.util.List<ActionConfig> actionConfigs);

	/**
	* Creates a new action config with the primary key. Does not add the action config to the database.
	*
	* @param actionConfigId the primary key for the new action config
	* @return the new action config
	*/
	public ActionConfig create(long actionConfigId);

	/**
	* Removes the action config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config that was removed
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public ActionConfig remove(long actionConfigId)
		throws NoSuchActionConfigException;

	public ActionConfig updateImpl(ActionConfig actionConfig);

	/**
	* Returns the action config with the primary key or throws a {@link NoSuchActionConfigException} if it could not be found.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public ActionConfig findByPrimaryKey(long actionConfigId)
		throws NoSuchActionConfigException;

	/**
	* Returns the action config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config, or <code>null</code> if a action config with the primary key could not be found
	*/
	public ActionConfig fetchByPrimaryKey(long actionConfigId);

	@Override
	public java.util.Map<java.io.Serializable, ActionConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the action configs.
	*
	* @return the action configs
	*/
	public java.util.List<ActionConfig> findAll();

	/**
	* Returns a range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of action configs
	*/
	public java.util.List<ActionConfig> findAll(int start, int end);

	/**
	* Returns an ordered range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of action configs
	*/
	public java.util.List<ActionConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns an ordered range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of action configs
	*/
	public java.util.List<ActionConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the action configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of action configs.
	*
	* @return the number of action configs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}