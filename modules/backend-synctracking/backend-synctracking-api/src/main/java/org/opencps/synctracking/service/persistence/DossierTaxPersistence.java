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

package org.opencps.synctracking.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.synctracking.exception.NoSuchDossierTaxException;
import org.opencps.synctracking.model.DossierTax;

/**
 * The persistence interface for the dossier tax service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duongnt
 * @see org.opencps.synctracking.service.persistence.impl.DossierTaxPersistenceImpl
 * @see DossierTaxUtil
 * @generated
 */
@ProviderType
public interface DossierTaxPersistence extends BasePersistence<DossierTax> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierTaxUtil} to access the dossier tax persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where uuid = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax[] findByUuid_PrevAndNext(long taxId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Removes all the dossier taxs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier taxs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier tax where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierTaxException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierTaxException;

	/**
	* Returns the dossier tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier tax where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier tax that was removed
	*/
	public DossierTax removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierTaxException;

	/**
	* Returns the number of dossier taxs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier taxs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax[] findByUuid_C_PrevAndNext(long taxId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Removes all the dossier taxs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier taxs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or throws a {@link NoSuchDossierTaxException} if it could not be found.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) throws NoSuchDossierTaxException;

	/**
	* Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh);

	/**
	* Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh, boolean retrieveFromCache);

	/**
	* Removes the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the dossier tax that was removed
	*/
	public DossierTax removeByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) throws NoSuchDossierTaxException;

	/**
	* Returns the number of dossier taxs where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63;.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the number of matching dossier taxs
	*/
	public int countByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh);

	/**
	* Returns all the dossier taxs where statusTBT = &#63;.
	*
	* @param statusTBT the status tbt
	* @return the matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int statusTBT);

	/**
	* Returns a range of all the dossier taxs where statusTBT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBT the status tbt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int statusTBT,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs where statusTBT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBT the status tbt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int statusTBT,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs where statusTBT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBT the status tbt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int statusTBT,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier tax in the ordered set where statusTBT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_STATUS_TBT_First(int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the first dossier tax in the ordered set where statusTBT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_STATUS_TBT_First(int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the last dossier tax in the ordered set where statusTBT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_STATUS_TBT_Last(int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the last dossier tax in the ordered set where statusTBT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_STATUS_TBT_Last(int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where statusTBT = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax[] findByF_STATUS_TBT_PrevAndNext(long taxId,
		int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns all the dossier taxs where statusTBT = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBTs the status tbts
	* @return the matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int[] statusTBTs);

	/**
	* Returns a range of all the dossier taxs where statusTBT = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBTs the status tbts
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int[] statusTBTs,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs where statusTBT = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBTs the status tbts
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int[] statusTBTs,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs where statusTBT = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBT the status tbt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_TBT(int[] statusTBTs,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier taxs where statusTBT = &#63; from the database.
	*
	* @param statusTBT the status tbt
	*/
	public void removeByF_STATUS_TBT(int statusTBT);

	/**
	* Returns the number of dossier taxs where statusTBT = &#63;.
	*
	* @param statusTBT the status tbt
	* @return the number of matching dossier taxs
	*/
	public int countByF_STATUS_TBT(int statusTBT);

	/**
	* Returns the number of dossier taxs where statusTBT = any &#63;.
	*
	* @param statusTBTs the status tbts
	* @return the number of matching dossier taxs
	*/
	public int countByF_STATUS_TBT(int[] statusTBTs);

	/**
	* Returns all the dossier taxs where statusTBT = &#63; and statusCTT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @return the matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_CTT(int statusTBT,
		int statusCTT);

	/**
	* Returns a range of all the dossier taxs where statusTBT = &#63; and statusCTT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_CTT(int statusTBT,
		int statusCTT, int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs where statusTBT = &#63; and statusCTT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_CTT(int statusTBT,
		int statusCTT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs where statusTBT = &#63; and statusCTT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_STATUS_CTT(int statusTBT,
		int statusCTT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier tax in the ordered set where statusTBT = &#63; and statusCTT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_STATUS_CTT_First(int statusTBT, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the first dossier tax in the ordered set where statusTBT = &#63; and statusCTT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_STATUS_CTT_First(int statusTBT, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the last dossier tax in the ordered set where statusTBT = &#63; and statusCTT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_STATUS_CTT_Last(int statusTBT, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the last dossier tax in the ordered set where statusTBT = &#63; and statusCTT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_STATUS_CTT_Last(int statusTBT, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where statusTBT = &#63; and statusCTT = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax[] findByF_STATUS_CTT_PrevAndNext(long taxId,
		int statusTBT, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Removes all the dossier taxs where statusTBT = &#63; and statusCTT = &#63; from the database.
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	*/
	public void removeByF_STATUS_CTT(int statusTBT, int statusCTT);

	/**
	* Returns the number of dossier taxs where statusTBT = &#63; and statusCTT = &#63;.
	*
	* @param statusTBT the status tbt
	* @param statusCTT the status ctt
	* @return the number of matching dossier taxs
	*/
	public int countByF_STATUS_CTT(int statusTBT, int statusCTT);

	/**
	* Returns all the dossier taxs where dossierId = &#63; and statusTBT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @return the matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_TBT(long dossierId,
		int statusTBT);

	/**
	* Returns a range of all the dossier taxs where dossierId = &#63; and statusTBT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_TBT(long dossierId,
		int statusTBT, int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs where dossierId = &#63; and statusTBT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_TBT(long dossierId,
		int statusTBT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs where dossierId = &#63; and statusTBT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_TBT(long dossierId,
		int statusTBT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier tax in the ordered set where dossierId = &#63; and statusTBT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_DOSSIER_TBT_First(long dossierId, int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the first dossier tax in the ordered set where dossierId = &#63; and statusTBT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_DOSSIER_TBT_First(long dossierId, int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the last dossier tax in the ordered set where dossierId = &#63; and statusTBT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_DOSSIER_TBT_Last(long dossierId, int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the last dossier tax in the ordered set where dossierId = &#63; and statusTBT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_DOSSIER_TBT_Last(long dossierId, int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where dossierId = &#63; and statusTBT = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax[] findByF_DOSSIER_TBT_PrevAndNext(long taxId,
		long dossierId, int statusTBT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Removes all the dossier taxs where dossierId = &#63; and statusTBT = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	*/
	public void removeByF_DOSSIER_TBT(long dossierId, int statusTBT);

	/**
	* Returns the number of dossier taxs where dossierId = &#63; and statusTBT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusTBT the status tbt
	* @return the number of matching dossier taxs
	*/
	public int countByF_DOSSIER_TBT(long dossierId, int statusTBT);

	/**
	* Returns all the dossier taxs where dossierId = &#63; and statusCTT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @return the matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_CTT(long dossierId,
		int statusCTT);

	/**
	* Returns a range of all the dossier taxs where dossierId = &#63; and statusCTT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_CTT(long dossierId,
		int statusCTT, int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs where dossierId = &#63; and statusCTT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_CTT(long dossierId,
		int statusCTT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs where dossierId = &#63; and statusCTT = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public java.util.List<DossierTax> findByF_DOSSIER_CTT(long dossierId,
		int statusCTT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier tax in the ordered set where dossierId = &#63; and statusCTT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_DOSSIER_CTT_First(long dossierId, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the first dossier tax in the ordered set where dossierId = &#63; and statusCTT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_DOSSIER_CTT_First(long dossierId, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the last dossier tax in the ordered set where dossierId = &#63; and statusCTT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public DossierTax findByF_DOSSIER_CTT_Last(long dossierId, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Returns the last dossier tax in the ordered set where dossierId = &#63; and statusCTT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public DossierTax fetchByF_DOSSIER_CTT_Last(long dossierId, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where dossierId = &#63; and statusCTT = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax[] findByF_DOSSIER_CTT_PrevAndNext(long taxId,
		long dossierId, int statusCTT,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator)
		throws NoSuchDossierTaxException;

	/**
	* Removes all the dossier taxs where dossierId = &#63; and statusCTT = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	*/
	public void removeByF_DOSSIER_CTT(long dossierId, int statusCTT);

	/**
	* Returns the number of dossier taxs where dossierId = &#63; and statusCTT = &#63;.
	*
	* @param dossierId the dossier ID
	* @param statusCTT the status ctt
	* @return the number of matching dossier taxs
	*/
	public int countByF_DOSSIER_CTT(long dossierId, int statusCTT);

	/**
	* Caches the dossier tax in the entity cache if it is enabled.
	*
	* @param dossierTax the dossier tax
	*/
	public void cacheResult(DossierTax dossierTax);

	/**
	* Caches the dossier taxs in the entity cache if it is enabled.
	*
	* @param dossierTaxs the dossier taxs
	*/
	public void cacheResult(java.util.List<DossierTax> dossierTaxs);

	/**
	* Creates a new dossier tax with the primary key. Does not add the dossier tax to the database.
	*
	* @param taxId the primary key for the new dossier tax
	* @return the new dossier tax
	*/
	public DossierTax create(long taxId);

	/**
	* Removes the dossier tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax that was removed
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax remove(long taxId) throws NoSuchDossierTaxException;

	public DossierTax updateImpl(DossierTax dossierTax);

	/**
	* Returns the dossier tax with the primary key or throws a {@link NoSuchDossierTaxException} if it could not be found.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public DossierTax findByPrimaryKey(long taxId)
		throws NoSuchDossierTaxException;

	/**
	* Returns the dossier tax with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax, or <code>null</code> if a dossier tax with the primary key could not be found
	*/
	public DossierTax fetchByPrimaryKey(long taxId);

	@Override
	public java.util.Map<java.io.Serializable, DossierTax> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier taxs.
	*
	* @return the dossier taxs
	*/
	public java.util.List<DossierTax> findAll();

	/**
	* Returns a range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of dossier taxs
	*/
	public java.util.List<DossierTax> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier taxs
	*/
	public java.util.List<DossierTax> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns an ordered range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier taxs
	*/
	public java.util.List<DossierTax> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier taxs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier taxs.
	*
	* @return the number of dossier taxs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}