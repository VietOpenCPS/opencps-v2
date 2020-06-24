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

package pay.gate.integration.dvc.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import pay.gate.integration.dvc.exception.NoSuchPhiLePhiException;
import pay.gate.integration.dvc.model.PhiLePhi;

/**
 * The persistence interface for the phi le phi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see pay.gate.integration.dvc.service.persistence.impl.PhiLePhiPersistenceImpl
 * @see PhiLePhiUtil
 * @generated
 */
@ProviderType
public interface PhiLePhiPersistence extends BasePersistence<PhiLePhi> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PhiLePhiUtil} to access the phi le phi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the phi le phis where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid(String uuid);

	/**
	* Returns a range of all the phi le phis where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public PhiLePhi findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public PhiLePhi findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns the phi le phis before and after the current phi le phi in the ordered set where uuid = &#63;.
	*
	* @param phiLePhiId the primary key of the current phi le phi
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public PhiLePhi[] findByUuid_PrevAndNext(long phiLePhiId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Removes all the phi le phis where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of phi le phis where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching phi le phis
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the phi le phi where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPhiLePhiException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public PhiLePhi findByUUID_G(String uuid, long groupId)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the phi le phi where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the phi le phi where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the phi le phi where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the phi le phi that was removed
	*/
	public PhiLePhi removeByUUID_G(String uuid, long groupId)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the number of phi le phis where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching phi le phis
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public PhiLePhi findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public PhiLePhi findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns the phi le phis before and after the current phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param phiLePhiId the primary key of the current phi le phi
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public PhiLePhi[] findByUuid_C_PrevAndNext(long phiLePhiId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Removes all the phi le phis where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching phi le phis
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @return the matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId);

	/**
	* Returns a range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end);

	/**
	* Returns an ordered range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns an ordered range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phi le phis
	*/
	public java.util.List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public PhiLePhi findByG_SCMID_First(long groupId,
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the first phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByG_SCMID_First(long groupId,
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns the last phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public PhiLePhi findByG_SCMID_Last(long groupId,
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the last phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public PhiLePhi fetchByG_SCMID_Last(long groupId,
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns the phi le phis before and after the current phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param phiLePhiId the primary key of the current phi le phi
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public PhiLePhi[] findByG_SCMID_PrevAndNext(long phiLePhiId, long groupId,
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator)
		throws NoSuchPhiLePhiException;

	/**
	* Removes all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	*/
	public void removeByG_SCMID(long groupId, long serviceConfigMappingId);

	/**
	* Returns the number of phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @return the number of matching phi le phis
	*/
	public int countByG_SCMID(long groupId, long serviceConfigMappingId);

	/**
	* Caches the phi le phi in the entity cache if it is enabled.
	*
	* @param phiLePhi the phi le phi
	*/
	public void cacheResult(PhiLePhi phiLePhi);

	/**
	* Caches the phi le phis in the entity cache if it is enabled.
	*
	* @param phiLePhis the phi le phis
	*/
	public void cacheResult(java.util.List<PhiLePhi> phiLePhis);

	/**
	* Creates a new phi le phi with the primary key. Does not add the phi le phi to the database.
	*
	* @param phiLePhiId the primary key for the new phi le phi
	* @return the new phi le phi
	*/
	public PhiLePhi create(long phiLePhiId);

	/**
	* Removes the phi le phi with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi that was removed
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public PhiLePhi remove(long phiLePhiId) throws NoSuchPhiLePhiException;

	public PhiLePhi updateImpl(PhiLePhi phiLePhi);

	/**
	* Returns the phi le phi with the primary key or throws a {@link NoSuchPhiLePhiException} if it could not be found.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public PhiLePhi findByPrimaryKey(long phiLePhiId)
		throws NoSuchPhiLePhiException;

	/**
	* Returns the phi le phi with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi, or <code>null</code> if a phi le phi with the primary key could not be found
	*/
	public PhiLePhi fetchByPrimaryKey(long phiLePhiId);

	@Override
	public java.util.Map<java.io.Serializable, PhiLePhi> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the phi le phis.
	*
	* @return the phi le phis
	*/
	public java.util.List<PhiLePhi> findAll();

	/**
	* Returns a range of all the phi le phis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of phi le phis
	*/
	public java.util.List<PhiLePhi> findAll(int start, int end);

	/**
	* Returns an ordered range of all the phi le phis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of phi le phis
	*/
	public java.util.List<PhiLePhi> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator);

	/**
	* Returns an ordered range of all the phi le phis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of phi le phis
	*/
	public java.util.List<PhiLePhi> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PhiLePhi> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the phi le phis from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of phi le phis.
	*
	* @return the number of phi le phis
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}