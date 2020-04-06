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

package org.opencps.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.datamgt.exception.NoSuchDictItemMappingException;
import org.opencps.datamgt.model.DictItemMapping;

/**
 * The persistence interface for the dict item mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.datamgt.service.persistence.impl.DictItemMappingPersistenceImpl
 * @see DictItemMappingUtil
 * @generated
 */
@ProviderType
public interface DictItemMappingPersistence extends BasePersistence<DictItemMapping> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemMappingUtil} to access the dict item mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public DictItemMapping findByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId) throws NoSuchDictItemMappingException;

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public DictItemMapping fetchByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId);

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public DictItemMapping fetchByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId, boolean retrieveFromCache);

	/**
	* Removes the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the dict item mapping that was removed
	*/
	public DictItemMapping removeByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId) throws NoSuchDictItemMappingException;

	/**
	* Returns the number of dict item mappings where groupId = &#63; and itemCode = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the number of matching dict item mappings
	*/
	public int countByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId);

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public DictItemMapping findByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId)
		throws NoSuchDictItemMappingException;

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId);

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId, boolean retrieveFromCache);

	/**
	* Removes the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the dict item mapping that was removed
	*/
	public DictItemMapping removeByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId)
		throws NoSuchDictItemMappingException;

	/**
	* Returns the number of dict item mappings where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the number of matching dict item mappings
	*/
	public int countByF_GID_ICDVCQG_CID(long groupId, String itemCodeDVCQG,
		long collectionId);

	/**
	* Returns all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @return the matching dict item mappings
	*/
	public java.util.List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId);

	/**
	* Returns a range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @return the range of matching dict item mappings
	*/
	public java.util.List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end);

	/**
	* Returns an ordered range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item mappings
	*/
	public java.util.List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator);

	/**
	* Returns an ordered range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item mappings
	*/
	public java.util.List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public DictItemMapping findByF_GID_CID_First(long groupId,
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator)
		throws NoSuchDictItemMappingException;

	/**
	* Returns the first dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public DictItemMapping fetchByF_GID_CID_First(long groupId,
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator);

	/**
	* Returns the last dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public DictItemMapping findByF_GID_CID_Last(long groupId,
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator)
		throws NoSuchDictItemMappingException;

	/**
	* Returns the last dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public DictItemMapping fetchByF_GID_CID_Last(long groupId,
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator);

	/**
	* Returns the dict item mappings before and after the current dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param mappingId the primary key of the current dict item mapping
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item mapping
	* @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	*/
	public DictItemMapping[] findByF_GID_CID_PrevAndNext(long mappingId,
		long groupId, long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator)
		throws NoSuchDictItemMappingException;

	/**
	* Removes all the dict item mappings where groupId = &#63; and collectionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	*/
	public void removeByF_GID_CID(long groupId, long collectionId);

	/**
	* Returns the number of dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @return the number of matching dict item mappings
	*/
	public int countByF_GID_CID(long groupId, long collectionId);

	/**
	* Caches the dict item mapping in the entity cache if it is enabled.
	*
	* @param dictItemMapping the dict item mapping
	*/
	public void cacheResult(DictItemMapping dictItemMapping);

	/**
	* Caches the dict item mappings in the entity cache if it is enabled.
	*
	* @param dictItemMappings the dict item mappings
	*/
	public void cacheResult(java.util.List<DictItemMapping> dictItemMappings);

	/**
	* Creates a new dict item mapping with the primary key. Does not add the dict item mapping to the database.
	*
	* @param mappingId the primary key for the new dict item mapping
	* @return the new dict item mapping
	*/
	public DictItemMapping create(long mappingId);

	/**
	* Removes the dict item mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping that was removed
	* @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	*/
	public DictItemMapping remove(long mappingId)
		throws NoSuchDictItemMappingException;

	public DictItemMapping updateImpl(DictItemMapping dictItemMapping);

	/**
	* Returns the dict item mapping with the primary key or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping
	* @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	*/
	public DictItemMapping findByPrimaryKey(long mappingId)
		throws NoSuchDictItemMappingException;

	/**
	* Returns the dict item mapping with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping, or <code>null</code> if a dict item mapping with the primary key could not be found
	*/
	public DictItemMapping fetchByPrimaryKey(long mappingId);

	@Override
	public java.util.Map<java.io.Serializable, DictItemMapping> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict item mappings.
	*
	* @return the dict item mappings
	*/
	public java.util.List<DictItemMapping> findAll();

	/**
	* Returns a range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @return the range of dict item mappings
	*/
	public java.util.List<DictItemMapping> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict item mappings
	*/
	public java.util.List<DictItemMapping> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator);

	/**
	* Returns an ordered range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict item mappings
	*/
	public java.util.List<DictItemMapping> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemMapping> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict item mappings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict item mappings.
	*
	* @return the number of dict item mappings
	*/
	public int countAll();
}