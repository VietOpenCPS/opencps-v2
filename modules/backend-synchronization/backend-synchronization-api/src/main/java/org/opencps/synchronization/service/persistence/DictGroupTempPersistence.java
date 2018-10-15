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

package org.opencps.synchronization.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.synchronization.exception.NoSuchDictGroupTempException;
import org.opencps.synchronization.model.DictGroupTemp;

import java.util.Date;

/**
 * The persistence interface for the dict group temp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see org.opencps.synchronization.service.persistence.impl.DictGroupTempPersistenceImpl
 * @see DictGroupTempUtil
 * @generated
 */
@ProviderType
public interface DictGroupTempPersistence extends BasePersistence<DictGroupTemp> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictGroupTempUtil} to access the dict group temp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dict group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid(String uuid);

	/**
	* Returns a range of all the dict group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where uuid = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public DictGroupTemp[] findByUuid_PrevAndNext(long dictGroupId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Removes all the dict group temps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dict group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict group temps
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dict group temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByUUID_G(String uuid, long groupId)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the dict group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dict group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict group temp where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict group temp that was removed
	*/
	public DictGroupTemp removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the number of dict group temps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public DictGroupTemp[] findByUuid_C_PrevAndNext(long dictGroupId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Removes all the dict group temps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict group temps
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByF_groupCode(String groupCode, long groupId)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByF_groupCode(String groupCode, long groupId);

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByF_groupCode(String groupCode, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict group temp where groupCode = &#63; and groupId = &#63; from the database.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the dict group temp that was removed
	*/
	public DictGroupTemp removeByF_groupCode(String groupCode, long groupId)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the number of dict group temps where groupCode = &#63; and groupId = &#63;.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public int countByF_groupCode(String groupCode, long groupId);

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) throws NoSuchDictGroupTempException;

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId);

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId, boolean retrieveFromCache);

	/**
	* Removes the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; from the database.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the dict group temp that was removed
	*/
	public DictGroupTemp removeByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) throws NoSuchDictGroupTempException;

	/**
	* Returns the number of dict group temps where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63;.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the number of matching dict group temps
	*/
	public int countByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId);

	/**
	* Returns all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId);

	/**
	* Returns a range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByGID_DC_First(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the first dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByGID_DC_First(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the last dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByGID_DC_Last(long dictCollectionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the last dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByGID_DC_Last(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public DictGroupTemp[] findByGID_DC_PrevAndNext(long dictGroupId,
		long dictCollectionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Removes all the dict group temps where dictCollectionId = &#63; and groupId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	*/
	public void removeByGID_DC(long dictCollectionId, long groupId);

	/**
	* Returns the number of dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public int countByGID_DC(long dictCollectionId, long groupId);

	/**
	* Returns all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId);

	/**
	* Returns a range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public java.util.List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByF_dictGroupNewerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the first dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByF_dictGroupNewerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the last dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public DictGroupTemp findByF_dictGroupNewerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the last dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public DictGroupTemp fetchByF_dictGroupNewerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public DictGroupTemp[] findByF_dictGroupNewerThan_PrevAndNext(
		long dictGroupId, Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator)
		throws NoSuchDictGroupTempException;

	/**
	* Removes all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public void removeByF_dictGroupNewerThan(Date modifiedDate, long groupId);

	/**
	* Returns the number of dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public int countByF_dictGroupNewerThan(Date modifiedDate, long groupId);

	/**
	* Caches the dict group temp in the entity cache if it is enabled.
	*
	* @param dictGroupTemp the dict group temp
	*/
	public void cacheResult(DictGroupTemp dictGroupTemp);

	/**
	* Caches the dict group temps in the entity cache if it is enabled.
	*
	* @param dictGroupTemps the dict group temps
	*/
	public void cacheResult(java.util.List<DictGroupTemp> dictGroupTemps);

	/**
	* Creates a new dict group temp with the primary key. Does not add the dict group temp to the database.
	*
	* @param dictGroupId the primary key for the new dict group temp
	* @return the new dict group temp
	*/
	public DictGroupTemp create(long dictGroupId);

	/**
	* Removes the dict group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp that was removed
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public DictGroupTemp remove(long dictGroupId)
		throws NoSuchDictGroupTempException;

	public DictGroupTemp updateImpl(DictGroupTemp dictGroupTemp);

	/**
	* Returns the dict group temp with the primary key or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public DictGroupTemp findByPrimaryKey(long dictGroupId)
		throws NoSuchDictGroupTempException;

	/**
	* Returns the dict group temp with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp, or <code>null</code> if a dict group temp with the primary key could not be found
	*/
	public DictGroupTemp fetchByPrimaryKey(long dictGroupId);

	@Override
	public java.util.Map<java.io.Serializable, DictGroupTemp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict group temps.
	*
	* @return the dict group temps
	*/
	public java.util.List<DictGroupTemp> findAll();

	/**
	* Returns a range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of dict group temps
	*/
	public java.util.List<DictGroupTemp> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict group temps
	*/
	public java.util.List<DictGroupTemp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict group temps
	*/
	public java.util.List<DictGroupTemp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict group temps from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict group temps.
	*
	* @return the number of dict group temps
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}