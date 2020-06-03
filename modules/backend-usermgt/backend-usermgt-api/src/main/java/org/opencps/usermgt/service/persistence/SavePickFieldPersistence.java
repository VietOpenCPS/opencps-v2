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

import org.opencps.usermgt.exception.NoSuchSavePickFieldException;
import org.opencps.usermgt.model.SavePickField;

/**
 * The persistence interface for the save pick field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.SavePickFieldPersistenceImpl
 * @see SavePickFieldUtil
 * @generated
 */
@ProviderType
public interface SavePickFieldPersistence extends BasePersistence<SavePickField> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SavePickFieldUtil} to access the save pick field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the save pick fields where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching save pick fields
	*/
	public java.util.List<SavePickField> findByUuid(String uuid);

	/**
	* Returns a range of all the save pick fields where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of save pick fields
	* @param end the upper bound of the range of save pick fields (not inclusive)
	* @return the range of matching save pick fields
	*/
	public java.util.List<SavePickField> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the save pick fields where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of save pick fields
	* @param end the upper bound of the range of save pick fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching save pick fields
	*/
	public java.util.List<SavePickField> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator);

	/**
	* Returns an ordered range of all the save pick fields where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of save pick fields
	* @param end the upper bound of the range of save pick fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching save pick fields
	*/
	public java.util.List<SavePickField> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public SavePickField findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator)
		throws NoSuchSavePickFieldException;

	/**
	* Returns the first save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public SavePickField fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator);

	/**
	* Returns the last save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public SavePickField findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator)
		throws NoSuchSavePickFieldException;

	/**
	* Returns the last save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public SavePickField fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator);

	/**
	* Returns the save pick fields before and after the current save pick field in the ordered set where uuid = &#63;.
	*
	* @param fieldPickId the primary key of the current save pick field
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next save pick field
	* @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	*/
	public SavePickField[] findByUuid_PrevAndNext(long fieldPickId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator)
		throws NoSuchSavePickFieldException;

	/**
	* Removes all the save pick fields where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of save pick fields where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching save pick fields
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the save pick field where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public SavePickField findByUUID_G(String uuid, long groupId)
		throws NoSuchSavePickFieldException;

	/**
	* Returns the save pick field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public SavePickField fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the save pick field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public SavePickField fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the save pick field where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the save pick field that was removed
	*/
	public SavePickField removeByUUID_G(String uuid, long groupId)
		throws NoSuchSavePickFieldException;

	/**
	* Returns the number of save pick fields where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching save pick fields
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public SavePickField findByG_U_ClassPK(long groupId, long userId,
		String classPK) throws NoSuchSavePickFieldException;

	/**
	* Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public SavePickField fetchByG_U_ClassPK(long groupId, long userId,
		String classPK);

	/**
	* Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public SavePickField fetchByG_U_ClassPK(long groupId, long userId,
		String classPK, boolean retrieveFromCache);

	/**
	* Removes the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the save pick field that was removed
	*/
	public SavePickField removeByG_U_ClassPK(long groupId, long userId,
		String classPK) throws NoSuchSavePickFieldException;

	/**
	* Returns the number of save pick fields where groupId = &#63; and userId = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the number of matching save pick fields
	*/
	public int countByG_U_ClassPK(long groupId, long userId, String classPK);

	/**
	* Caches the save pick field in the entity cache if it is enabled.
	*
	* @param savePickField the save pick field
	*/
	public void cacheResult(SavePickField savePickField);

	/**
	* Caches the save pick fields in the entity cache if it is enabled.
	*
	* @param savePickFields the save pick fields
	*/
	public void cacheResult(java.util.List<SavePickField> savePickFields);

	/**
	* Creates a new save pick field with the primary key. Does not add the save pick field to the database.
	*
	* @param fieldPickId the primary key for the new save pick field
	* @return the new save pick field
	*/
	public SavePickField create(long fieldPickId);

	/**
	* Removes the save pick field with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field that was removed
	* @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	*/
	public SavePickField remove(long fieldPickId)
		throws NoSuchSavePickFieldException;

	public SavePickField updateImpl(SavePickField savePickField);

	/**
	* Returns the save pick field with the primary key or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field
	* @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	*/
	public SavePickField findByPrimaryKey(long fieldPickId)
		throws NoSuchSavePickFieldException;

	/**
	* Returns the save pick field with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field, or <code>null</code> if a save pick field with the primary key could not be found
	*/
	public SavePickField fetchByPrimaryKey(long fieldPickId);

	@Override
	public java.util.Map<java.io.Serializable, SavePickField> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the save pick fields.
	*
	* @return the save pick fields
	*/
	public java.util.List<SavePickField> findAll();

	/**
	* Returns a range of all the save pick fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of save pick fields
	* @param end the upper bound of the range of save pick fields (not inclusive)
	* @return the range of save pick fields
	*/
	public java.util.List<SavePickField> findAll(int start, int end);

	/**
	* Returns an ordered range of all the save pick fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of save pick fields
	* @param end the upper bound of the range of save pick fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of save pick fields
	*/
	public java.util.List<SavePickField> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator);

	/**
	* Returns an ordered range of all the save pick fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of save pick fields
	* @param end the upper bound of the range of save pick fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of save pick fields
	*/
	public java.util.List<SavePickField> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SavePickField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the save pick fields from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of save pick fields.
	*
	* @return the number of save pick fields
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}