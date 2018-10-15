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

import org.opencps.dossiermgt.exception.NoSuchStepConfigException;
import org.opencps.dossiermgt.model.StepConfig;

/**
 * The persistence interface for the step config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.StepConfigPersistenceImpl
 * @see StepConfigUtil
 * @generated
 */
@ProviderType
public interface StepConfigPersistence extends BasePersistence<StepConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StepConfigUtil} to access the step config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the step configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching step configs
	*/
	public java.util.List<StepConfig> findByUuid(String uuid);

	/**
	* Returns a range of all the step configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public java.util.List<StepConfig> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the step configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns an ordered range of all the step configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the first step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the last step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the last step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the step configs before and after the current step config in the ordered set where uuid = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public StepConfig[] findByUuid_PrevAndNext(long stepConfigId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Removes all the step configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of step configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching step configs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the step config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchStepConfigException;

	/**
	* Returns the step config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the step config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the step config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the step config that was removed
	*/
	public StepConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchStepConfigException;

	/**
	* Returns the number of step configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching step configs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching step configs
	*/
	public java.util.List<StepConfig> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public java.util.List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns an ordered range of all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the first step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the last step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the last step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the step configs before and after the current step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public StepConfig[] findByUuid_C_PrevAndNext(long stepConfigId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Removes all the step configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of step configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching step configs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByF_BY_stepCode(long groupId, String stepCode)
		throws NoSuchStepConfigException;

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_BY_stepCode(long groupId, String stepCode);

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_BY_stepCode(long groupId, String stepCode,
		boolean retrieveFromCache);

	/**
	* Removes the step config where groupId = &#63; and stepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the step config that was removed
	*/
	public StepConfig removeByF_BY_stepCode(long groupId, String stepCode)
		throws NoSuchStepConfigException;

	/**
	* Returns the number of step configs where groupId = &#63; and stepCode = &#63;.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the number of matching step configs
	*/
	public int countByF_BY_stepCode(long groupId, String stepCode);

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByF_GT_STEP(long groupId, String stepCode)
		throws NoSuchStepConfigException;

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_GT_STEP(long groupId, String stepCode);

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_GT_STEP(long groupId, String stepCode,
		boolean retrieveFromCache);

	/**
	* Removes the step config where groupId = &#63; and stepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the step config that was removed
	*/
	public StepConfig removeByF_GT_STEP(long groupId, String stepCode)
		throws NoSuchStepConfigException;

	/**
	* Returns the number of step configs where groupId = &#63; and stepCode = &#63;.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the number of matching step configs
	*/
	public int countByF_GT_STEP(long groupId, String stepCode);

	/**
	* Returns all the step configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching step configs
	*/
	public java.util.List<StepConfig> findByF_GID(long groupId);

	/**
	* Returns a range of all the step configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public java.util.List<StepConfig> findByF_GID(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the step configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns an ordered range of all the step configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the first step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the last step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the last step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the step configs before and after the current step config in the ordered set where groupId = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public StepConfig[] findByF_GID_PrevAndNext(long stepConfigId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Removes all the step configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_GID(long groupId);

	/**
	* Returns the number of step configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching step configs
	*/
	public int countByF_GID(long groupId);

	/**
	* Returns all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @return the matching step configs
	*/
	public java.util.List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus);

	/**
	* Returns a range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public java.util.List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus, int start, int end);

	/**
	* Returns an ordered range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns an ordered range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public java.util.List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByF_MS_SS_First(long groupId, String dossierStatus,
		String dossierSubStatus,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the first step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_MS_SS_First(long groupId, String dossierStatus,
		String dossierSubStatus,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the last step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public StepConfig findByF_MS_SS_Last(long groupId, String dossierStatus,
		String dossierSubStatus,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Returns the last step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public StepConfig fetchByF_MS_SS_Last(long groupId, String dossierStatus,
		String dossierSubStatus,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the step configs before and after the current step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public StepConfig[] findByF_MS_SS_PrevAndNext(long stepConfigId,
		long groupId, String dossierStatus, String dossierSubStatus,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator)
		throws NoSuchStepConfigException;

	/**
	* Removes all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	*/
	public void removeByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus);

	/**
	* Returns the number of step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @return the number of matching step configs
	*/
	public int countByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus);

	/**
	* Caches the step config in the entity cache if it is enabled.
	*
	* @param stepConfig the step config
	*/
	public void cacheResult(StepConfig stepConfig);

	/**
	* Caches the step configs in the entity cache if it is enabled.
	*
	* @param stepConfigs the step configs
	*/
	public void cacheResult(java.util.List<StepConfig> stepConfigs);

	/**
	* Creates a new step config with the primary key. Does not add the step config to the database.
	*
	* @param stepConfigId the primary key for the new step config
	* @return the new step config
	*/
	public StepConfig create(long stepConfigId);

	/**
	* Removes the step config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config that was removed
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public StepConfig remove(long stepConfigId)
		throws NoSuchStepConfigException;

	public StepConfig updateImpl(StepConfig stepConfig);

	/**
	* Returns the step config with the primary key or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public StepConfig findByPrimaryKey(long stepConfigId)
		throws NoSuchStepConfigException;

	/**
	* Returns the step config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config, or <code>null</code> if a step config with the primary key could not be found
	*/
	public StepConfig fetchByPrimaryKey(long stepConfigId);

	@Override
	public java.util.Map<java.io.Serializable, StepConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the step configs.
	*
	* @return the step configs
	*/
	public java.util.List<StepConfig> findAll();

	/**
	* Returns a range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of step configs
	*/
	public java.util.List<StepConfig> findAll(int start, int end);

	/**
	* Returns an ordered range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of step configs
	*/
	public java.util.List<StepConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns an ordered range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of step configs
	*/
	public java.util.List<StepConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the step configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of step configs.
	*
	* @return the number of step configs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}