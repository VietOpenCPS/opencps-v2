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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.SavePickField;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the save pick field service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.SavePickFieldPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see SavePickFieldPersistence
 * @see org.opencps.usermgt.service.persistence.impl.SavePickFieldPersistenceImpl
 * @generated
 */
@ProviderType
public class SavePickFieldUtil {
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
	public static void clearCache(SavePickField savePickField) {
		getPersistence().clearCache(savePickField);
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
	public static List<SavePickField> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SavePickField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SavePickField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SavePickField> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SavePickField update(SavePickField savePickField) {
		return getPersistence().update(savePickField);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SavePickField update(SavePickField savePickField,
		ServiceContext serviceContext) {
		return getPersistence().update(savePickField, serviceContext);
	}

	/**
	* Returns all the save pick fields where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching save pick fields
	*/
	public static List<SavePickField> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<SavePickField> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<SavePickField> findByUuid(String uuid, int start,
		int end, OrderByComparator<SavePickField> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<SavePickField> findByUuid(String uuid, int start,
		int end, OrderByComparator<SavePickField> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public static SavePickField findByUuid_First(String uuid,
		OrderByComparator<SavePickField> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public static SavePickField fetchByUuid_First(String uuid,
		OrderByComparator<SavePickField> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public static SavePickField findByUuid_Last(String uuid,
		OrderByComparator<SavePickField> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last save pick field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public static SavePickField fetchByUuid_Last(String uuid,
		OrderByComparator<SavePickField> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the save pick fields before and after the current save pick field in the ordered set where uuid = &#63;.
	*
	* @param fieldPickId the primary key of the current save pick field
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next save pick field
	* @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	*/
	public static SavePickField[] findByUuid_PrevAndNext(long fieldPickId,
		String uuid, OrderByComparator<SavePickField> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence()
				   .findByUuid_PrevAndNext(fieldPickId, uuid, orderByComparator);
	}

	/**
	* Removes all the save pick fields where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of save pick fields where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching save pick fields
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the save pick field where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public static SavePickField findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the save pick field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public static SavePickField fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the save pick field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public static SavePickField fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the save pick field where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the save pick field that was removed
	*/
	public static SavePickField removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of save pick fields where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching save pick fields
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the matching save pick field
	* @throws NoSuchSavePickFieldException if a matching save pick field could not be found
	*/
	public static SavePickField findByG_U_ClassPK(long groupId, long userId,
		String classPK)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().findByG_U_ClassPK(groupId, userId, classPK);
	}

	/**
	* Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public static SavePickField fetchByG_U_ClassPK(long groupId, long userId,
		String classPK) {
		return getPersistence().fetchByG_U_ClassPK(groupId, userId, classPK);
	}

	/**
	* Returns the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	public static SavePickField fetchByG_U_ClassPK(long groupId, long userId,
		String classPK, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_U_ClassPK(groupId, userId, classPK,
			retrieveFromCache);
	}

	/**
	* Removes the save pick field where groupId = &#63; and userId = &#63; and classPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the save pick field that was removed
	*/
	public static SavePickField removeByG_U_ClassPK(long groupId, long userId,
		String classPK)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().removeByG_U_ClassPK(groupId, userId, classPK);
	}

	/**
	* Returns the number of save pick fields where groupId = &#63; and userId = &#63; and classPK = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classPK the class pk
	* @return the number of matching save pick fields
	*/
	public static int countByG_U_ClassPK(long groupId, long userId,
		String classPK) {
		return getPersistence().countByG_U_ClassPK(groupId, userId, classPK);
	}

	/**
	* Caches the save pick field in the entity cache if it is enabled.
	*
	* @param savePickField the save pick field
	*/
	public static void cacheResult(SavePickField savePickField) {
		getPersistence().cacheResult(savePickField);
	}

	/**
	* Caches the save pick fields in the entity cache if it is enabled.
	*
	* @param savePickFields the save pick fields
	*/
	public static void cacheResult(List<SavePickField> savePickFields) {
		getPersistence().cacheResult(savePickFields);
	}

	/**
	* Creates a new save pick field with the primary key. Does not add the save pick field to the database.
	*
	* @param fieldPickId the primary key for the new save pick field
	* @return the new save pick field
	*/
	public static SavePickField create(long fieldPickId) {
		return getPersistence().create(fieldPickId);
	}

	/**
	* Removes the save pick field with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field that was removed
	* @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	*/
	public static SavePickField remove(long fieldPickId)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().remove(fieldPickId);
	}

	public static SavePickField updateImpl(SavePickField savePickField) {
		return getPersistence().updateImpl(savePickField);
	}

	/**
	* Returns the save pick field with the primary key or throws a {@link NoSuchSavePickFieldException} if it could not be found.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field
	* @throws NoSuchSavePickFieldException if a save pick field with the primary key could not be found
	*/
	public static SavePickField findByPrimaryKey(long fieldPickId)
		throws org.opencps.usermgt.exception.NoSuchSavePickFieldException {
		return getPersistence().findByPrimaryKey(fieldPickId);
	}

	/**
	* Returns the save pick field with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field, or <code>null</code> if a save pick field with the primary key could not be found
	*/
	public static SavePickField fetchByPrimaryKey(long fieldPickId) {
		return getPersistence().fetchByPrimaryKey(fieldPickId);
	}

	public static java.util.Map<java.io.Serializable, SavePickField> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the save pick fields.
	*
	* @return the save pick fields
	*/
	public static List<SavePickField> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<SavePickField> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<SavePickField> findAll(int start, int end,
		OrderByComparator<SavePickField> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<SavePickField> findAll(int start, int end,
		OrderByComparator<SavePickField> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the save pick fields from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of save pick fields.
	*
	* @return the number of save pick fields
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SavePickFieldPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SavePickFieldPersistence, SavePickFieldPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SavePickFieldPersistence.class);

		ServiceTracker<SavePickFieldPersistence, SavePickFieldPersistence> serviceTracker =
			new ServiceTracker<SavePickFieldPersistence, SavePickFieldPersistence>(bundle.getBundleContext(),
				SavePickFieldPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}