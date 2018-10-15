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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchPreferencesException;
import org.opencps.usermgt.model.Preferences;

/**
 * The persistence interface for the preferences service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.PreferencesPersistenceImpl
 * @see PreferencesUtil
 * @generated
 */
@ProviderType
public interface PreferencesPersistence extends BasePersistence<Preferences> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PreferencesUtil} to access the preferences persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the preferenceses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid(String uuid);

	/**
	* Returns a range of all the preferenceses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @return the range of matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator);

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public Preferences findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException;

	/**
	* Returns the first preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator);

	/**
	* Returns the last preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public Preferences findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException;

	/**
	* Returns the last preferences in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator);

	/**
	* Returns the preferenceses before and after the current preferences in the ordered set where uuid = &#63;.
	*
	* @param preferencesId the primary key of the current preferences
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next preferences
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public Preferences[] findByUuid_PrevAndNext(long preferencesId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException;

	/**
	* Removes all the preferenceses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of preferenceses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching preferenceses
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the preferences where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPreferencesException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public Preferences findByUUID_G(String uuid, long groupId)
		throws NoSuchPreferencesException;

	/**
	* Returns the preferences where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the preferences where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the preferences where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the preferences that was removed
	*/
	public Preferences removeByUUID_G(String uuid, long groupId)
		throws NoSuchPreferencesException;

	/**
	* Returns the number of preferenceses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching preferenceses
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @return the range of matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator);

	/**
	* Returns an ordered range of all the preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching preferenceses
	*/
	public java.util.List<Preferences> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public Preferences findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException;

	/**
	* Returns the first preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator);

	/**
	* Returns the last preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public Preferences findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException;

	/**
	* Returns the last preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator);

	/**
	* Returns the preferenceses before and after the current preferences in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param preferencesId the primary key of the current preferences
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next preferences
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public Preferences[] findByUuid_C_PrevAndNext(long preferencesId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator)
		throws NoSuchPreferencesException;

	/**
	* Removes all the preferenceses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of preferenceses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching preferenceses
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the preferences where groupId = &#63; and userId = &#63; or throws a {@link NoSuchPreferencesException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching preferences
	* @throws NoSuchPreferencesException if a matching preferences could not be found
	*/
	public Preferences findByF_userId(long groupId, long userId)
		throws NoSuchPreferencesException;

	/**
	* Returns the preferences where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByF_userId(long groupId, long userId);

	/**
	* Returns the preferences where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public Preferences fetchByF_userId(long groupId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the preferences where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the preferences that was removed
	*/
	public Preferences removeByF_userId(long groupId, long userId)
		throws NoSuchPreferencesException;

	/**
	* Returns the number of preferenceses where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching preferenceses
	*/
	public int countByF_userId(long groupId, long userId);

	/**
	* Caches the preferences in the entity cache if it is enabled.
	*
	* @param preferences the preferences
	*/
	public void cacheResult(Preferences preferences);

	/**
	* Caches the preferenceses in the entity cache if it is enabled.
	*
	* @param preferenceses the preferenceses
	*/
	public void cacheResult(java.util.List<Preferences> preferenceses);

	/**
	* Creates a new preferences with the primary key. Does not add the preferences to the database.
	*
	* @param preferencesId the primary key for the new preferences
	* @return the new preferences
	*/
	public Preferences create(long preferencesId);

	/**
	* Removes the preferences with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences that was removed
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public Preferences remove(long preferencesId)
		throws NoSuchPreferencesException;

	public Preferences updateImpl(Preferences preferences);

	/**
	* Returns the preferences with the primary key or throws a {@link NoSuchPreferencesException} if it could not be found.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences
	* @throws NoSuchPreferencesException if a preferences with the primary key could not be found
	*/
	public Preferences findByPrimaryKey(long preferencesId)
		throws NoSuchPreferencesException;

	/**
	* Returns the preferences with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences, or <code>null</code> if a preferences with the primary key could not be found
	*/
	public Preferences fetchByPrimaryKey(long preferencesId);

	@Override
	public java.util.Map<java.io.Serializable, Preferences> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the preferenceses.
	*
	* @return the preferenceses
	*/
	public java.util.List<Preferences> findAll();

	/**
	* Returns a range of all the preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @return the range of preferenceses
	*/
	public java.util.List<Preferences> findAll(int start, int end);

	/**
	* Returns an ordered range of all the preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of preferenceses
	*/
	public java.util.List<Preferences> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator);

	/**
	* Returns an ordered range of all the preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of preferenceses
	*/
	public java.util.List<Preferences> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Preferences> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the preferenceses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of preferenceses.
	*
	* @return the number of preferenceses
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}