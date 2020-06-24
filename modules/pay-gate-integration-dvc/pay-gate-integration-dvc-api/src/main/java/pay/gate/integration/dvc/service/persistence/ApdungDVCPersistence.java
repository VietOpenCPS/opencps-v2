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

import pay.gate.integration.dvc.exception.NoSuchApdungDVCException;
import pay.gate.integration.dvc.model.ApdungDVC;

/**
 * The persistence interface for the apdung dvc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see pay.gate.integration.dvc.service.persistence.impl.ApdungDVCPersistenceImpl
 * @see ApdungDVCUtil
 * @generated
 */
@ProviderType
public interface ApdungDVCPersistence extends BasePersistence<ApdungDVC> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApdungDVCUtil} to access the apdung dvc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the apdung dvcs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid(String uuid);

	/**
	* Returns a range of all the apdung dvcs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public ApdungDVC findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException;

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public ApdungDVC findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException;

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns the apdung dvcs before and after the current apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param apdungDVCId the primary key of the current apdung dvc
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next apdung dvc
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public ApdungDVC[] findByUuid_PrevAndNext(long apdungDVCId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException;

	/**
	* Removes all the apdung dvcs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of apdung dvcs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching apdung dvcs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the apdung dvc where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApdungDVCException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public ApdungDVC findByUUID_G(String uuid, long groupId)
		throws NoSuchApdungDVCException;

	/**
	* Returns the apdung dvc where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the apdung dvc where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the apdung dvc where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the apdung dvc that was removed
	*/
	public ApdungDVC removeByUUID_G(String uuid, long groupId)
		throws NoSuchApdungDVCException;

	/**
	* Returns the number of apdung dvcs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching apdung dvcs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching apdung dvcs
	*/
	public java.util.List<ApdungDVC> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public ApdungDVC findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException;

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public ApdungDVC findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException;

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns the apdung dvcs before and after the current apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param apdungDVCId the primary key of the current apdung dvc
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next apdung dvc
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public ApdungDVC[] findByUuid_C_PrevAndNext(long apdungDVCId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator)
		throws NoSuchApdungDVCException;

	/**
	* Removes all the apdung dvcs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching apdung dvcs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or throws a {@link NoSuchApdungDVCException} if it could not be found.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public ApdungDVC findByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo) throws NoSuchApdungDVCException;

	/**
	* Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo);

	/**
	* Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public ApdungDVC fetchByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo, boolean retrieveFromCache);

	/**
	* Removes the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; from the database.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the apdung dvc that was removed
	*/
	public ApdungDVC removeByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo) throws NoSuchApdungDVCException;

	/**
	* Returns the number of apdung dvcs where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63;.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the number of matching apdung dvcs
	*/
	public int countByF_TTHC_CQTH_MD(String maTTHC, String maCQTH, int mucdo);

	/**
	* Caches the apdung dvc in the entity cache if it is enabled.
	*
	* @param apdungDVC the apdung dvc
	*/
	public void cacheResult(ApdungDVC apdungDVC);

	/**
	* Caches the apdung dvcs in the entity cache if it is enabled.
	*
	* @param apdungDVCs the apdung dvcs
	*/
	public void cacheResult(java.util.List<ApdungDVC> apdungDVCs);

	/**
	* Creates a new apdung dvc with the primary key. Does not add the apdung dvc to the database.
	*
	* @param apdungDVCId the primary key for the new apdung dvc
	* @return the new apdung dvc
	*/
	public ApdungDVC create(long apdungDVCId);

	/**
	* Removes the apdung dvc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc that was removed
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public ApdungDVC remove(long apdungDVCId) throws NoSuchApdungDVCException;

	public ApdungDVC updateImpl(ApdungDVC apdungDVC);

	/**
	* Returns the apdung dvc with the primary key or throws a {@link NoSuchApdungDVCException} if it could not be found.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public ApdungDVC findByPrimaryKey(long apdungDVCId)
		throws NoSuchApdungDVCException;

	/**
	* Returns the apdung dvc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc, or <code>null</code> if a apdung dvc with the primary key could not be found
	*/
	public ApdungDVC fetchByPrimaryKey(long apdungDVCId);

	@Override
	public java.util.Map<java.io.Serializable, ApdungDVC> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the apdung dvcs.
	*
	* @return the apdung dvcs
	*/
	public java.util.List<ApdungDVC> findAll();

	/**
	* Returns a range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of apdung dvcs
	*/
	public java.util.List<ApdungDVC> findAll(int start, int end);

	/**
	* Returns an ordered range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of apdung dvcs
	*/
	public java.util.List<ApdungDVC> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns an ordered range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of apdung dvcs
	*/
	public java.util.List<ApdungDVC> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApdungDVC> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the apdung dvcs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of apdung dvcs.
	*
	* @return the number of apdung dvcs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}