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

import org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException;
import org.opencps.dossiermgt.model.CsdlDcServiceInfo;

/**
 * The persistence interface for the csdl dc service info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.CsdlDcServiceInfoPersistenceImpl
 * @see CsdlDcServiceInfoUtil
 * @generated
 */
@ProviderType
public interface CsdlDcServiceInfoPersistence extends BasePersistence<CsdlDcServiceInfo> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CsdlDcServiceInfoUtil} to access the csdl dc service info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the csdl dc service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid(String uuid);

	/**
	* Returns a range of all the csdl dc service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator);

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator);

	/**
	* Returns the csdl dc service infos before and after the current csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param idDcService the primary key of the current csdl dc service info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public CsdlDcServiceInfo[] findByUuid_PrevAndNext(long idDcService,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Removes all the csdl dc service infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of csdl dc service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching csdl dc service infos
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the csdl dc service info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the csdl dc service info that was removed
	*/
	public CsdlDcServiceInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the number of csdl dc service infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching csdl dc service infos
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator);

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator);

	/**
	* Returns the csdl dc service infos before and after the current csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param idDcService the primary key of the current csdl dc service info
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public CsdlDcServiceInfo[] findByUuid_C_PrevAndNext(long idDcService,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Removes all the csdl dc service infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching csdl dc service infos
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo findByF_SERVICECODE_STATUS(String serviceCode,
		int status) throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByF_SERVICECODE_STATUS(String serviceCode,
		int status);

	/**
	* Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceCode the service code
	* @param status the status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public CsdlDcServiceInfo fetchByF_SERVICECODE_STATUS(String serviceCode,
		int status, boolean retrieveFromCache);

	/**
	* Removes the csdl dc service info where serviceCode = &#63; and status = &#63; from the database.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the csdl dc service info that was removed
	*/
	public CsdlDcServiceInfo removeByF_SERVICECODE_STATUS(String serviceCode,
		int status) throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the number of csdl dc service infos where serviceCode = &#63; and status = &#63;.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the number of matching csdl dc service infos
	*/
	public int countByF_SERVICECODE_STATUS(String serviceCode, int status);

	/**
	* Caches the csdl dc service info in the entity cache if it is enabled.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	*/
	public void cacheResult(CsdlDcServiceInfo csdlDcServiceInfo);

	/**
	* Caches the csdl dc service infos in the entity cache if it is enabled.
	*
	* @param csdlDcServiceInfos the csdl dc service infos
	*/
	public void cacheResult(
		java.util.List<CsdlDcServiceInfo> csdlDcServiceInfos);

	/**
	* Creates a new csdl dc service info with the primary key. Does not add the csdl dc service info to the database.
	*
	* @param idDcService the primary key for the new csdl dc service info
	* @return the new csdl dc service info
	*/
	public CsdlDcServiceInfo create(long idDcService);

	/**
	* Removes the csdl dc service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info that was removed
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public CsdlDcServiceInfo remove(long idDcService)
		throws NoSuchCsdlDcServiceInfoException;

	public CsdlDcServiceInfo updateImpl(CsdlDcServiceInfo csdlDcServiceInfo);

	/**
	* Returns the csdl dc service info with the primary key or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public CsdlDcServiceInfo findByPrimaryKey(long idDcService)
		throws NoSuchCsdlDcServiceInfoException;

	/**
	* Returns the csdl dc service info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info, or <code>null</code> if a csdl dc service info with the primary key could not be found
	*/
	public CsdlDcServiceInfo fetchByPrimaryKey(long idDcService);

	@Override
	public java.util.Map<java.io.Serializable, CsdlDcServiceInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the csdl dc service infos.
	*
	* @return the csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findAll();

	/**
	* Returns a range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findAll(int start, int end);

	/**
	* Returns an ordered range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of csdl dc service infos
	*/
	public java.util.List<CsdlDcServiceInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the csdl dc service infos from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of csdl dc service infos.
	*
	* @return the number of csdl dc service infos
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}