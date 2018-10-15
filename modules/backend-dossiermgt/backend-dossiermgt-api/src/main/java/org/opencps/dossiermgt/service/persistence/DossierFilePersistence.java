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

import org.opencps.dossiermgt.exception.NoSuchDossierFileException;
import org.opencps.dossiermgt.model.DossierFile;

/**
 * The persistence interface for the dossier file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierFilePersistenceImpl
 * @see DossierFileUtil
 * @generated
 */
@ProviderType
public interface DossierFilePersistence extends BasePersistence<DossierFile> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierFileUtil} to access the dossier file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where uuid = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByUuid_PrevAndNext(long dossierFileId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier files
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierFileException;

	/**
	* Returns the dossier file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier file where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier file that was removed
	*/
	public DossierFile removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierFileException;

	/**
	* Returns the number of dossier files where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier files
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the dossier files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByUuid_C_PrevAndNext(long dossierFileId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dossier files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier files
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTN_First(long dossierId,
		String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTN_First(long dossierId,
		String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTN_Last(long dossierId,
		String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTN_Last(long dossierId,
		String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDID_FTN_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	*/
	public void removeByDID_FTN(long dossierId, String fileTemplateNo);

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @return the number of matching dossier files
	*/
	public int countByDID_FTN(long dossierId, String fileTemplateNo);

	/**
	* Returns all the dossier files where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_(long dossierId);

	/**
	* Returns a range of all the dossier files where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_(long dossierId, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_(long dossierId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_(long dossierId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID__First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID__First(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID__Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID__Last(long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDID__PrevAndNext(long dossierFileId,
		long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public void removeByDID_(long dossierId);

	/**
	* Returns the number of dossier files where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier files
	*/
	public int countByDID_(long dossierId);

	/**
	* Returns all the dossier files where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDossierId(long dossierId,
		boolean removed);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDossierId(long dossierId,
		boolean removed, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDossierId(long dossierId,
		boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDossierId(long dossierId,
		boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDossierId_First(long dossierId, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDossierId_First(long dossierId, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDossierId_Last(long dossierId, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDossierId_Last(long dossierId, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDossierId_PrevAndNext(long dossierFileId,
		long dossierId, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	*/
	public void removeByDossierId(long dossierId, boolean removed);

	/**
	* Returns the number of dossier files where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByDossierId(long dossierId, boolean removed);

	/**
	* Returns all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByD_DPT_First(long dossierId, int dossierPartType,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByD_DPT_First(long dossierId, int dossierPartType,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByD_DPT_Last(long dossierId, int dossierPartType,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByD_DPT_Last(long dossierId, int dossierPartType,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByD_DPT_PrevAndNext(long dossierFileId,
		long dossierId, int dossierPartType, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	*/
	public void removeByD_DPT(long dossierId, int dossierPartType,
		boolean removed);

	/**
	* Returns the number of dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByD_DPT(long dossierId, int dossierPartType, boolean removed);

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByD_RUID(long dossierId, String referenceUid,
		boolean removed) throws NoSuchDossierFileException;

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByD_RUID(long dossierId, String referenceUid,
		boolean removed);

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByD_RUID(long dossierId, String referenceUid,
		boolean removed, boolean retrieveFromCache);

	/**
	* Removes the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the dossier file that was removed
	*/
	public DossierFile removeByD_RUID(long dossierId, String referenceUid,
		boolean removed) throws NoSuchDossierFileException;

	/**
	* Returns the number of dossier files where dossierId = &#63; and referenceUid = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByD_RUID(long dossierId, String referenceUid,
		boolean removed);

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_REF(long dossierId, String referenceUid)
		throws NoSuchDossierFileException;

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_REF(long dossierId, String referenceUid);

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_REF(long dossierId, String referenceUid,
		boolean retrieveFromCache);

	/**
	* Removes the dossier file where dossierId = &#63; and referenceUid = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the dossier file that was removed
	*/
	public DossierFile removeByDID_REF(long dossierId, String referenceUid)
		throws NoSuchDossierFileException;

	/**
	* Returns the number of dossier files where dossierId = &#63; and referenceUid = &#63;.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the number of matching dossier files
	*/
	public int countByDID_REF(long dossierId, String referenceUid);

	/**
	* Returns all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_ISN_First(long dossierId, boolean isNew,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_ISN_First(long dossierId, boolean isNew,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_ISN_Last(long dossierId, boolean isNew,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_ISN_Last(long dossierId, boolean isNew,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDID_ISN_PrevAndNext(long dossierFileId,
		long dossierId, boolean isNew, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	*/
	public void removeByDID_ISN(long dossierId, boolean isNew, boolean removed);

	/**
	* Returns the number of dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByDID_ISN(long dossierId, boolean isNew, boolean removed);

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTNO_First(long dossierId,
		String fileTemplateNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTNO_First(long dossierId,
		String fileTemplateNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTNO_Last(long dossierId,
		String fileTemplateNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTNO_Last(long dossierId,
		String fileTemplateNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDID_FTNO_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	*/
	public void removeByDID_FTNO(long dossierId, String fileTemplateNo,
		boolean removed);

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByDID_FTNO(long dossierId, String fileTemplateNo,
		boolean removed);

	/**
	* Returns all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_DPNO_First(long dossierId,
		String dossierPartNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_DPNO_First(long dossierId,
		String dossierPartNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_DPNO_Last(long dossierId,
		String dossierPartNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_DPNO_Last(long dossierId,
		String dossierPartNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDID_DPNO_PrevAndNext(long dossierFileId,
		long dossierId, String dossierPartNo, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	*/
	public void removeByDID_DPNO(long dossierId, String dossierPartNo,
		boolean removed);

	/**
	* Returns the number of dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByDID_DPNO(long dossierId, String dossierPartNo,
		boolean removed);

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTNO_DPT_First(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTNO_DPT_First(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTNO_DPT_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTNO_DPT_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDID_FTNO_DPT_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo, int dossierPartType,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	*/
	public void removeByDID_FTNO_DPT(long dossierId, String fileTemplateNo,
		int dossierPartType, boolean removed);

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByDID_FTNO_DPT(long dossierId, String fileTemplateNo,
		int dossierPartType, boolean removed);

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTNO_DPT_NOT_NULL_FID_First(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTNO_DPT_NOT_NULL_FID_First(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDID_FTNO_DPT_NOT_NULL_FID_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDID_FTNO_DPT_NOT_NULL_FID_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByDID_FTNO_DPT_NOT_NULL_FID_PrevAndNext(
		long dossierFileId, long dossierId, String fileTemplateNo,
		int dossierPartType, long fileEntryId, boolean removed,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	*/
	public void removeByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed);

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public int countByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed);

	/**
	* Returns the dossier file where deliverableCode = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param deliverableCode the deliverable code
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByDE_CODE(String deliverableCode)
		throws NoSuchDossierFileException;

	/**
	* Returns the dossier file where deliverableCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDE_CODE(String deliverableCode);

	/**
	* Returns the dossier file where deliverableCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByDE_CODE(String deliverableCode,
		boolean retrieveFromCache);

	/**
	* Removes the dossier file where deliverableCode = &#63; from the database.
	*
	* @param deliverableCode the deliverable code
	* @return the dossier file that was removed
	*/
	public DossierFile removeByDE_CODE(String deliverableCode)
		throws NoSuchDossierFileException;

	/**
	* Returns the number of dossier files where deliverableCode = &#63;.
	*
	* @param deliverableCode the deliverable code
	* @return the number of matching dossier files
	*/
	public int countByDE_CODE(String deliverableCode);

	/**
	* Returns the dossier file where groupId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByGID_REF(long groupId, String referenceUid)
		throws NoSuchDossierFileException;

	/**
	* Returns the dossier file where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByGID_REF(long groupId, String referenceUid);

	/**
	* Returns the dossier file where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByGID_REF(long groupId, String referenceUid,
		boolean retrieveFromCache);

	/**
	* Removes the dossier file where groupId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the dossier file that was removed
	*/
	public DossierFile removeByGID_REF(long groupId, String referenceUid)
		throws NoSuchDossierFileException;

	/**
	* Returns the number of dossier files where groupId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the number of matching dossier files
	*/
	public int countByGID_REF(long groupId, String referenceUid);

	/**
	* Returns the dossier file where fileEntryId = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param fileEntryId the file entry ID
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByFILE_ID(long fileEntryId)
		throws NoSuchDossierFileException;

	/**
	* Returns the dossier file where fileEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByFILE_ID(long fileEntryId);

	/**
	* Returns the dossier file where fileEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByFILE_ID(long fileEntryId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier file where fileEntryId = &#63; from the database.
	*
	* @param fileEntryId the file entry ID
	* @return the dossier file that was removed
	*/
	public DossierFile removeByFILE_ID(long fileEntryId)
		throws NoSuchDossierFileException;

	/**
	* Returns the number of dossier files where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @return the number of matching dossier files
	*/
	public int countByFILE_ID(long fileEntryId);

	/**
	* Returns all the dossier files where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByREF_UID(String referenceUid);

	/**
	* Returns a range of all the dossier files where referenceUid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByREF_UID(String referenceUid,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier files where referenceUid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByREF_UID(String referenceUid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where referenceUid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByREF_UID(String referenceUid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByREF_UID_First(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByREF_UID_First(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByREF_UID_Last(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByREF_UID_Last(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByREF_UID_PrevAndNext(long dossierFileId,
		String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where referenceUid = &#63; from the database.
	*
	* @param referenceUid the reference uid
	*/
	public void removeByREF_UID(String referenceUid);

	/**
	* Returns the number of dossier files where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the number of matching dossier files
	*/
	public int countByREF_UID(String referenceUid);

	/**
	* Returns all the dossier files where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByG(long groupId);

	/**
	* Returns a range of all the dossier files where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByG(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByG(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByG(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where groupId = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByG_PrevAndNext(long dossierFileId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG(long groupId);

	/**
	* Returns the number of dossier files where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dossier files
	*/
	public int countByG(long groupId);

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossier files
	*/
	public java.util.List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo);

	/**
	* Returns a range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of matching dossier files
	*/
	public java.util.List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier files
	*/
	public java.util.List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByD_FTN_DTN_First(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByD_FTN_DTN_First(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public DossierFile findByD_FTN_DTN_Last(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public DossierFile fetchByD_FTN_DTN_Last(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile[] findByD_FTN_DTN_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator)
		throws NoSuchDossierFileException;

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	*/
	public void removeByD_FTN_DTN(long dossierId, String fileTemplateNo,
		String dossierTemplateNo);

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossier files
	*/
	public int countByD_FTN_DTN(long dossierId, String fileTemplateNo,
		String dossierTemplateNo);

	/**
	* Caches the dossier file in the entity cache if it is enabled.
	*
	* @param dossierFile the dossier file
	*/
	public void cacheResult(DossierFile dossierFile);

	/**
	* Caches the dossier files in the entity cache if it is enabled.
	*
	* @param dossierFiles the dossier files
	*/
	public void cacheResult(java.util.List<DossierFile> dossierFiles);

	/**
	* Creates a new dossier file with the primary key. Does not add the dossier file to the database.
	*
	* @param dossierFileId the primary key for the new dossier file
	* @return the new dossier file
	*/
	public DossierFile create(long dossierFileId);

	/**
	* Removes the dossier file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file that was removed
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile remove(long dossierFileId)
		throws NoSuchDossierFileException;

	public DossierFile updateImpl(DossierFile dossierFile);

	/**
	* Returns the dossier file with the primary key or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public DossierFile findByPrimaryKey(long dossierFileId)
		throws NoSuchDossierFileException;

	/**
	* Returns the dossier file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file, or <code>null</code> if a dossier file with the primary key could not be found
	*/
	public DossierFile fetchByPrimaryKey(long dossierFileId);

	@Override
	public java.util.Map<java.io.Serializable, DossierFile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier files.
	*
	* @return the dossier files
	*/
	public java.util.List<DossierFile> findAll();

	/**
	* Returns a range of all the dossier files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of dossier files
	*/
	public java.util.List<DossierFile> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier files
	*/
	public java.util.List<DossierFile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator);

	/**
	* Returns an ordered range of all the dossier files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier files
	*/
	public java.util.List<DossierFile> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier files from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier files.
	*
	* @return the number of dossier files
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}