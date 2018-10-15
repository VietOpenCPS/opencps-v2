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

import org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException;
import org.opencps.dossiermgt.model.DeliverableType;

/**
 * The persistence interface for the deliverable type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverableTypePersistenceImpl
 * @see DeliverableTypeUtil
 * @generated
 */
@ProviderType
public interface DeliverableTypePersistence extends BasePersistence<DeliverableType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeliverableTypeUtil} to access the deliverable type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid(String uuid);

	/**
	* Returns a range of all the deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public DeliverableType findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public DeliverableType findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns the deliverable types before and after the current deliverable type in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeId the primary key of the current deliverable type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public DeliverableType[] findByUuid_PrevAndNext(long deliverableTypeId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException;

	/**
	* Removes all the deliverable types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverable types
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the deliverable type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public DeliverableType findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the deliverable type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable type that was removed
	*/
	public DeliverableType removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the number of deliverable types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverable types
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable types
	*/
	public java.util.List<DeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public DeliverableType findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public DeliverableType findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns the deliverable types before and after the current deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeId the primary key of the current deliverable type
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public DeliverableType[] findByUuid_C_PrevAndNext(long deliverableTypeId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException;

	/**
	* Removes all the deliverable types where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverable types
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the deliverable type where groupId = &#63; and typeCode = &#63; or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public DeliverableType findByG_DLT(long groupId, String typeCode)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the deliverable type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByG_DLT(long groupId, String typeCode);

	/**
	* Returns the deliverable type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public DeliverableType fetchByG_DLT(long groupId, String typeCode,
		boolean retrieveFromCache);

	/**
	* Removes the deliverable type where groupId = &#63; and typeCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the deliverable type that was removed
	*/
	public DeliverableType removeByG_DLT(long groupId, String typeCode)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the number of deliverable types where groupId = &#63; and typeCode = &#63;.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the number of matching deliverable types
	*/
	public int countByG_DLT(long groupId, String typeCode);

	/**
	* Caches the deliverable type in the entity cache if it is enabled.
	*
	* @param deliverableType the deliverable type
	*/
	public void cacheResult(DeliverableType deliverableType);

	/**
	* Caches the deliverable types in the entity cache if it is enabled.
	*
	* @param deliverableTypes the deliverable types
	*/
	public void cacheResult(java.util.List<DeliverableType> deliverableTypes);

	/**
	* Creates a new deliverable type with the primary key. Does not add the deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new deliverable type
	* @return the new deliverable type
	*/
	public DeliverableType create(long deliverableTypeId);

	/**
	* Removes the deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type that was removed
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public DeliverableType remove(long deliverableTypeId)
		throws NoSuchDeliverableTypeException;

	public DeliverableType updateImpl(DeliverableType deliverableType);

	/**
	* Returns the deliverable type with the primary key or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public DeliverableType findByPrimaryKey(long deliverableTypeId)
		throws NoSuchDeliverableTypeException;

	/**
	* Returns the deliverable type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type, or <code>null</code> if a deliverable type with the primary key could not be found
	*/
	public DeliverableType fetchByPrimaryKey(long deliverableTypeId);

	@Override
	public java.util.Map<java.io.Serializable, DeliverableType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the deliverable types.
	*
	* @return the deliverable types
	*/
	public java.util.List<DeliverableType> findAll();

	/**
	* Returns a range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of deliverable types
	*/
	public java.util.List<DeliverableType> findAll(int start, int end);

	/**
	* Returns an ordered range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deliverable types
	*/
	public java.util.List<DeliverableType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deliverable types
	*/
	public java.util.List<DeliverableType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the deliverable types from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of deliverable types.
	*
	* @return the number of deliverable types
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}