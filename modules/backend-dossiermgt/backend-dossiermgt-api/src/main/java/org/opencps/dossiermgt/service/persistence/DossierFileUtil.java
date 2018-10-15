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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.DossierFile;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier file service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierFilePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierFilePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierFilePersistenceImpl
 * @generated
 */
@ProviderType
public class DossierFileUtil {
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
	public static void clearCache(DossierFile dossierFile) {
		getPersistence().clearCache(dossierFile);
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
	public static List<DossierFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierFile update(DossierFile dossierFile) {
		return getPersistence().update(dossierFile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierFile update(DossierFile dossierFile,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierFile, serviceContext);
	}

	/**
	* Returns all the dossier files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<DossierFile> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<DossierFile> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByUuid_First(String uuid,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByUuid_First(String uuid,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByUuid_Last(String uuid,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByUuid_Last(String uuid,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where uuid = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public static DossierFile[] findByUuid_PrevAndNext(long dossierFileId,
		String uuid, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierFileId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier files where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier files where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier files
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier file where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier file where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier file where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier file that was removed
	*/
	public static DossierFile removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier files where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier files
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<DossierFile> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<DossierFile> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static DossierFile[] findByUuid_C_PrevAndNext(long dossierFileId,
		String uuid, long companyId,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierFileId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossier files where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier files where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier files
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo) {
		return getPersistence().findByDID_FTN(dossierId, fileTemplateNo);
	}

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
	public static List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end) {
		return getPersistence()
				   .findByDID_FTN(dossierId, fileTemplateNo, start, end);
	}

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
	public static List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDID_FTN(dossierId, fileTemplateNo, start, end,
			orderByComparator);
	}

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
	public static List<DossierFile> findByDID_FTN(long dossierId,
		String fileTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_FTN(dossierId, fileTemplateNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDID_FTN_First(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTN_First(dossierId, fileTemplateNo,
			orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_FTN_First(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTN_First(dossierId, fileTemplateNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDID_FTN_Last(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTN_Last(dossierId, fileTemplateNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_FTN_Last(long dossierId,
		String fileTemplateNo, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTN_Last(dossierId, fileTemplateNo,
			orderByComparator);
	}

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
	public static DossierFile[] findByDID_FTN_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTN_PrevAndNext(dossierFileId, dossierId,
			fileTemplateNo, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	*/
	public static void removeByDID_FTN(long dossierId, String fileTemplateNo) {
		getPersistence().removeByDID_FTN(dossierId, fileTemplateNo);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @return the number of matching dossier files
	*/
	public static int countByDID_FTN(long dossierId, String fileTemplateNo) {
		return getPersistence().countByDID_FTN(dossierId, fileTemplateNo);
	}

	/**
	* Returns all the dossier files where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByDID_(long dossierId) {
		return getPersistence().findByDID_(dossierId);
	}

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
	public static List<DossierFile> findByDID_(long dossierId, int start,
		int end) {
		return getPersistence().findByDID_(dossierId, start, end);
	}

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
	public static List<DossierFile> findByDID_(long dossierId, int start,
		int end, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDID_(dossierId, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByDID_(long dossierId, int start,
		int end, OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_(dossierId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDID__First(long dossierId,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByDID__First(dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID__First(long dossierId,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().fetchByDID__First(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDID__Last(long dossierId,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByDID__Last(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID__Last(long dossierId,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().fetchByDID__Last(dossierId, orderByComparator);
	}

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where dossierId = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public static DossierFile[] findByDID__PrevAndNext(long dossierFileId,
		long dossierId, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID__PrevAndNext(dossierFileId, dossierId,
			orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public static void removeByDID_(long dossierId) {
		getPersistence().removeByDID_(dossierId);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier files
	*/
	public static int countByDID_(long dossierId) {
		return getPersistence().countByDID_(dossierId);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByDossierId(long dossierId,
		boolean removed) {
		return getPersistence().findByDossierId(dossierId, removed);
	}

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
	public static List<DossierFile> findByDossierId(long dossierId,
		boolean removed, int start, int end) {
		return getPersistence().findByDossierId(dossierId, removed, start, end);
	}

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
	public static List<DossierFile> findByDossierId(long dossierId,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDossierId(dossierId, removed, start, end,
			orderByComparator);
	}

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
	public static List<DossierFile> findByDossierId(long dossierId,
		boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDossierId(dossierId, removed, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDossierId_First(long dossierId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDossierId_First(dossierId, removed, orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDossierId_First(long dossierId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDossierId_First(dossierId, removed, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDossierId_Last(long dossierId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDossierId_Last(dossierId, removed, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDossierId_Last(long dossierId,
		boolean removed, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDossierId_Last(dossierId, removed, orderByComparator);
	}

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
	public static DossierFile[] findByDossierId_PrevAndNext(
		long dossierFileId, long dossierId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDossierId_PrevAndNext(dossierFileId, dossierId,
			removed, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	*/
	public static void removeByDossierId(long dossierId, boolean removed) {
		getPersistence().removeByDossierId(dossierId, removed);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public static int countByDossierId(long dossierId, boolean removed) {
		return getPersistence().countByDossierId(dossierId, removed);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed) {
		return getPersistence().findByD_DPT(dossierId, dossierPartType, removed);
	}

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
	public static List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed, int start, int end) {
		return getPersistence()
				   .findByD_DPT(dossierId, dossierPartType, removed, start, end);
	}

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
	public static List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByD_DPT(dossierId, dossierPartType, removed, start,
			end, orderByComparator);
	}

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
	public static List<DossierFile> findByD_DPT(long dossierId,
		int dossierPartType, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_DPT(dossierId, dossierPartType, removed, start,
			end, orderByComparator, retrieveFromCache);
	}

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
	public static DossierFile findByD_DPT_First(long dossierId,
		int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByD_DPT_First(dossierId, dossierPartType, removed,
			orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByD_DPT_First(long dossierId,
		int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByD_DPT_First(dossierId, dossierPartType, removed,
			orderByComparator);
	}

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
	public static DossierFile findByD_DPT_Last(long dossierId,
		int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByD_DPT_Last(dossierId, dossierPartType, removed,
			orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByD_DPT_Last(long dossierId,
		int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByD_DPT_Last(dossierId, dossierPartType, removed,
			orderByComparator);
	}

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
	public static DossierFile[] findByD_DPT_PrevAndNext(long dossierFileId,
		long dossierId, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByD_DPT_PrevAndNext(dossierFileId, dossierId,
			dossierPartType, removed, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	*/
	public static void removeByD_DPT(long dossierId, int dossierPartType,
		boolean removed) {
		getPersistence().removeByD_DPT(dossierId, dossierPartType, removed);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public static int countByD_DPT(long dossierId, int dossierPartType,
		boolean removed) {
		return getPersistence().countByD_DPT(dossierId, dossierPartType, removed);
	}

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByD_RUID(long dossierId, String referenceUid,
		boolean removed)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByD_RUID(dossierId, referenceUid, removed);
	}

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByD_RUID(long dossierId,
		String referenceUid, boolean removed) {
		return getPersistence().fetchByD_RUID(dossierId, referenceUid, removed);
	}

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByD_RUID(long dossierId,
		String referenceUid, boolean removed, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByD_RUID(dossierId, referenceUid, removed,
			retrieveFromCache);
	}

	/**
	* Removes the dossier file where dossierId = &#63; and referenceUid = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the dossier file that was removed
	*/
	public static DossierFile removeByD_RUID(long dossierId,
		String referenceUid, boolean removed)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().removeByD_RUID(dossierId, referenceUid, removed);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and referenceUid = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public static int countByD_RUID(long dossierId, String referenceUid,
		boolean removed) {
		return getPersistence().countByD_RUID(dossierId, referenceUid, removed);
	}

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDID_REF(long dossierId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByDID_REF(dossierId, referenceUid);
	}

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_REF(long dossierId, String referenceUid) {
		return getPersistence().fetchByDID_REF(dossierId, referenceUid);
	}

	/**
	* Returns the dossier file where dossierId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_REF(long dossierId,
		String referenceUid, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDID_REF(dossierId, referenceUid, retrieveFromCache);
	}

	/**
	* Removes the dossier file where dossierId = &#63; and referenceUid = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the dossier file that was removed
	*/
	public static DossierFile removeByDID_REF(long dossierId,
		String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().removeByDID_REF(dossierId, referenceUid);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and referenceUid = &#63;.
	*
	* @param dossierId the dossier ID
	* @param referenceUid the reference uid
	* @return the number of matching dossier files
	*/
	public static int countByDID_REF(long dossierId, String referenceUid) {
		return getPersistence().countByDID_REF(dossierId, referenceUid);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed) {
		return getPersistence().findByDID_ISN(dossierId, isNew, removed);
	}

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
	public static List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed, int start, int end) {
		return getPersistence()
				   .findByDID_ISN(dossierId, isNew, removed, start, end);
	}

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
	public static List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDID_ISN(dossierId, isNew, removed, start, end,
			orderByComparator);
	}

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
	public static List<DossierFile> findByDID_ISN(long dossierId,
		boolean isNew, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_ISN(dossierId, isNew, removed, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static DossierFile findByDID_ISN_First(long dossierId,
		boolean isNew, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_ISN_First(dossierId, isNew, removed,
			orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_ISN_First(long dossierId,
		boolean isNew, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_ISN_First(dossierId, isNew, removed,
			orderByComparator);
	}

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
	public static DossierFile findByDID_ISN_Last(long dossierId, boolean isNew,
		boolean removed, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_ISN_Last(dossierId, isNew, removed,
			orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_ISN_Last(long dossierId,
		boolean isNew, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_ISN_Last(dossierId, isNew, removed,
			orderByComparator);
	}

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
	public static DossierFile[] findByDID_ISN_PrevAndNext(long dossierFileId,
		long dossierId, boolean isNew, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_ISN_PrevAndNext(dossierFileId, dossierId, isNew,
			removed, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	*/
	public static void removeByDID_ISN(long dossierId, boolean isNew,
		boolean removed) {
		getPersistence().removeByDID_ISN(dossierId, isNew, removed);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and isNew = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param isNew the is new
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public static int countByDID_ISN(long dossierId, boolean isNew,
		boolean removed) {
		return getPersistence().countByDID_ISN(dossierId, isNew, removed);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed) {
		return getPersistence()
				   .findByDID_FTNO(dossierId, fileTemplateNo, removed);
	}

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
	public static List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end) {
		return getPersistence()
				   .findByDID_FTNO(dossierId, fileTemplateNo, removed, start,
			end);
	}

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
	public static List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDID_FTNO(dossierId, fileTemplateNo, removed, start,
			end, orderByComparator);
	}

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
	public static List<DossierFile> findByDID_FTNO(long dossierId,
		String fileTemplateNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_FTNO(dossierId, fileTemplateNo, removed, start,
			end, orderByComparator, retrieveFromCache);
	}

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
	public static DossierFile findByDID_FTNO_First(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_First(dossierId, fileTemplateNo, removed,
			orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_FTNO_First(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTNO_First(dossierId, fileTemplateNo, removed,
			orderByComparator);
	}

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
	public static DossierFile findByDID_FTNO_Last(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_Last(dossierId, fileTemplateNo, removed,
			orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_FTNO_Last(long dossierId,
		String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTNO_Last(dossierId, fileTemplateNo, removed,
			orderByComparator);
	}

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
	public static DossierFile[] findByDID_FTNO_PrevAndNext(long dossierFileId,
		long dossierId, String fileTemplateNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_PrevAndNext(dossierFileId, dossierId,
			fileTemplateNo, removed, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	*/
	public static void removeByDID_FTNO(long dossierId, String fileTemplateNo,
		boolean removed) {
		getPersistence().removeByDID_FTNO(dossierId, fileTemplateNo, removed);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public static int countByDID_FTNO(long dossierId, String fileTemplateNo,
		boolean removed) {
		return getPersistence()
				   .countByDID_FTNO(dossierId, fileTemplateNo, removed);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed) {
		return getPersistence().findByDID_DPNO(dossierId, dossierPartNo, removed);
	}

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
	public static List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end) {
		return getPersistence()
				   .findByDID_DPNO(dossierId, dossierPartNo, removed, start, end);
	}

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
	public static List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDID_DPNO(dossierId, dossierPartNo, removed, start,
			end, orderByComparator);
	}

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
	public static List<DossierFile> findByDID_DPNO(long dossierId,
		String dossierPartNo, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_DPNO(dossierId, dossierPartNo, removed, start,
			end, orderByComparator, retrieveFromCache);
	}

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
	public static DossierFile findByDID_DPNO_First(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_DPNO_First(dossierId, dossierPartNo, removed,
			orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_DPNO_First(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_DPNO_First(dossierId, dossierPartNo, removed,
			orderByComparator);
	}

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
	public static DossierFile findByDID_DPNO_Last(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_DPNO_Last(dossierId, dossierPartNo, removed,
			orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDID_DPNO_Last(long dossierId,
		String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_DPNO_Last(dossierId, dossierPartNo, removed,
			orderByComparator);
	}

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
	public static DossierFile[] findByDID_DPNO_PrevAndNext(long dossierFileId,
		long dossierId, String dossierPartNo, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_DPNO_PrevAndNext(dossierFileId, dossierId,
			dossierPartNo, removed, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	*/
	public static void removeByDID_DPNO(long dossierId, String dossierPartNo,
		boolean removed) {
		getPersistence().removeByDID_DPNO(dossierId, dossierPartNo, removed);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and dossierPartNo = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param dossierPartNo the dossier part no
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public static int countByDID_DPNO(long dossierId, String dossierPartNo,
		boolean removed) {
		return getPersistence()
				   .countByDID_DPNO(dossierId, dossierPartNo, removed);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed) {
		return getPersistence()
				   .findByDID_FTNO_DPT(dossierId, fileTemplateNo,
			dossierPartType, removed);
	}

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
	public static List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end) {
		return getPersistence()
				   .findByDID_FTNO_DPT(dossierId, fileTemplateNo,
			dossierPartType, removed, start, end);
	}

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
	public static List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDID_FTNO_DPT(dossierId, fileTemplateNo,
			dossierPartType, removed, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed, int start,
		int end, OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_FTNO_DPT(dossierId, fileTemplateNo,
			dossierPartType, removed, start, end, orderByComparator,
			retrieveFromCache);
	}

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
	public static DossierFile findByDID_FTNO_DPT_First(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_DPT_First(dossierId, fileTemplateNo,
			dossierPartType, removed, orderByComparator);
	}

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
	public static DossierFile fetchByDID_FTNO_DPT_First(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTNO_DPT_First(dossierId, fileTemplateNo,
			dossierPartType, removed, orderByComparator);
	}

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
	public static DossierFile findByDID_FTNO_DPT_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_DPT_Last(dossierId, fileTemplateNo,
			dossierPartType, removed, orderByComparator);
	}

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
	public static DossierFile fetchByDID_FTNO_DPT_Last(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTNO_DPT_Last(dossierId, fileTemplateNo,
			dossierPartType, removed, orderByComparator);
	}

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
	public static DossierFile[] findByDID_FTNO_DPT_PrevAndNext(
		long dossierFileId, long dossierId, String fileTemplateNo,
		int dossierPartType, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_DPT_PrevAndNext(dossierFileId, dossierId,
			fileTemplateNo, dossierPartType, removed, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	*/
	public static void removeByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed) {
		getPersistence()
			.removeByDID_FTNO_DPT(dossierId, fileTemplateNo, dossierPartType,
			removed);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and removed = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param removed the removed
	* @return the number of matching dossier files
	*/
	public static int countByDID_FTNO_DPT(long dossierId,
		String fileTemplateNo, int dossierPartType, boolean removed) {
		return getPersistence()
				   .countByDID_FTNO_DPT(dossierId, fileTemplateNo,
			dossierPartType, removed);
	}

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
	public static List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed) {
		return getPersistence()
				   .findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed);
	}

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
	public static List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed, int start, int end) {
		return getPersistence()
				   .findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed, start, end);
	}

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
	public static List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByDID_FTNO_DPT_NOT_NULL_FID(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static DossierFile findByDID_FTNO_DPT_NOT_NULL_FID_First(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_DPT_NOT_NULL_FID_First(dossierId,
			fileTemplateNo, dossierPartType, fileEntryId, removed,
			orderByComparator);
	}

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
	public static DossierFile fetchByDID_FTNO_DPT_NOT_NULL_FID_First(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTNO_DPT_NOT_NULL_FID_First(dossierId,
			fileTemplateNo, dossierPartType, fileEntryId, removed,
			orderByComparator);
	}

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
	public static DossierFile findByDID_FTNO_DPT_NOT_NULL_FID_Last(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_DPT_NOT_NULL_FID_Last(dossierId,
			fileTemplateNo, dossierPartType, fileEntryId, removed,
			orderByComparator);
	}

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
	public static DossierFile fetchByDID_FTNO_DPT_NOT_NULL_FID_Last(
		long dossierId, String fileTemplateNo, int dossierPartType,
		long fileEntryId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByDID_FTNO_DPT_NOT_NULL_FID_Last(dossierId,
			fileTemplateNo, dossierPartType, fileEntryId, removed,
			orderByComparator);
	}

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
	public static DossierFile[] findByDID_FTNO_DPT_NOT_NULL_FID_PrevAndNext(
		long dossierFileId, long dossierId, String fileTemplateNo,
		int dossierPartType, long fileEntryId, boolean removed,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByDID_FTNO_DPT_NOT_NULL_FID_PrevAndNext(dossierFileId,
			dossierId, fileTemplateNo, dossierPartType, fileEntryId, removed,
			orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierPartType = &#63; and fileEntryId &gt; &#63; and removed = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierPartType the dossier part type
	* @param fileEntryId the file entry ID
	* @param removed the removed
	*/
	public static void removeByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed) {
		getPersistence()
			.removeByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed);
	}

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
	public static int countByDID_FTNO_DPT_NOT_NULL_FID(long dossierId,
		String fileTemplateNo, int dossierPartType, long fileEntryId,
		boolean removed) {
		return getPersistence()
				   .countByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo,
			dossierPartType, fileEntryId, removed);
	}

	/**
	* Returns the dossier file where deliverableCode = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param deliverableCode the deliverable code
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByDE_CODE(String deliverableCode)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByDE_CODE(deliverableCode);
	}

	/**
	* Returns the dossier file where deliverableCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDE_CODE(String deliverableCode) {
		return getPersistence().fetchByDE_CODE(deliverableCode);
	}

	/**
	* Returns the dossier file where deliverableCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByDE_CODE(String deliverableCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDE_CODE(deliverableCode, retrieveFromCache);
	}

	/**
	* Removes the dossier file where deliverableCode = &#63; from the database.
	*
	* @param deliverableCode the deliverable code
	* @return the dossier file that was removed
	*/
	public static DossierFile removeByDE_CODE(String deliverableCode)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().removeByDE_CODE(deliverableCode);
	}

	/**
	* Returns the number of dossier files where deliverableCode = &#63;.
	*
	* @param deliverableCode the deliverable code
	* @return the number of matching dossier files
	*/
	public static int countByDE_CODE(String deliverableCode) {
		return getPersistence().countByDE_CODE(deliverableCode);
	}

	/**
	* Returns the dossier file where groupId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByGID_REF(long groupId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByGID_REF(groupId, referenceUid);
	}

	/**
	* Returns the dossier file where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByGID_REF(long groupId, String referenceUid) {
		return getPersistence().fetchByGID_REF(groupId, referenceUid);
	}

	/**
	* Returns the dossier file where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByGID_REF(long groupId, String referenceUid,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_REF(groupId, referenceUid, retrieveFromCache);
	}

	/**
	* Removes the dossier file where groupId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the dossier file that was removed
	*/
	public static DossierFile removeByGID_REF(long groupId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().removeByGID_REF(groupId, referenceUid);
	}

	/**
	* Returns the number of dossier files where groupId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the number of matching dossier files
	*/
	public static int countByGID_REF(long groupId, String referenceUid) {
		return getPersistence().countByGID_REF(groupId, referenceUid);
	}

	/**
	* Returns the dossier file where fileEntryId = &#63; or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param fileEntryId the file entry ID
	* @return the matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByFILE_ID(long fileEntryId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByFILE_ID(fileEntryId);
	}

	/**
	* Returns the dossier file where fileEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByFILE_ID(long fileEntryId) {
		return getPersistence().fetchByFILE_ID(fileEntryId);
	}

	/**
	* Returns the dossier file where fileEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByFILE_ID(long fileEntryId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByFILE_ID(fileEntryId, retrieveFromCache);
	}

	/**
	* Removes the dossier file where fileEntryId = &#63; from the database.
	*
	* @param fileEntryId the file entry ID
	* @return the dossier file that was removed
	*/
	public static DossierFile removeByFILE_ID(long fileEntryId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().removeByFILE_ID(fileEntryId);
	}

	/**
	* Returns the number of dossier files where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @return the number of matching dossier files
	*/
	public static int countByFILE_ID(long fileEntryId) {
		return getPersistence().countByFILE_ID(fileEntryId);
	}

	/**
	* Returns all the dossier files where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByREF_UID(String referenceUid) {
		return getPersistence().findByREF_UID(referenceUid);
	}

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
	public static List<DossierFile> findByREF_UID(String referenceUid,
		int start, int end) {
		return getPersistence().findByREF_UID(referenceUid, start, end);
	}

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
	public static List<DossierFile> findByREF_UID(String referenceUid,
		int start, int end, OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByREF_UID(referenceUid, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByREF_UID(String referenceUid,
		int start, int end, OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByREF_UID(referenceUid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByREF_UID_First(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByREF_UID_First(referenceUid, orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByREF_UID_First(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByREF_UID_First(referenceUid, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByREF_UID_Last(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByREF_UID_Last(referenceUid, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByREF_UID_Last(String referenceUid,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByREF_UID_Last(referenceUid, orderByComparator);
	}

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where referenceUid = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public static DossierFile[] findByREF_UID_PrevAndNext(long dossierFileId,
		String referenceUid, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByREF_UID_PrevAndNext(dossierFileId, referenceUid,
			orderByComparator);
	}

	/**
	* Removes all the dossier files where referenceUid = &#63; from the database.
	*
	* @param referenceUid the reference uid
	*/
	public static void removeByREF_UID(String referenceUid) {
		getPersistence().removeByREF_UID(referenceUid);
	}

	/**
	* Returns the number of dossier files where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the number of matching dossier files
	*/
	public static int countByREF_UID(String referenceUid) {
		return getPersistence().countByREF_UID(referenceUid);
	}

	/**
	* Returns all the dossier files where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByG(long groupId) {
		return getPersistence().findByG(groupId);
	}

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
	public static List<DossierFile> findByG(long groupId, int start, int end) {
		return getPersistence().findByG(groupId, start, end);
	}

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
	public static List<DossierFile> findByG(long groupId, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().findByG(groupId, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByG(long groupId, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByG_First(long groupId,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByG_First(long groupId,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().fetchByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file
	* @throws NoSuchDossierFileException if a matching dossier file could not be found
	*/
	public static DossierFile findByG_Last(long groupId,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByG_Last(long groupId,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().fetchByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the dossier files before and after the current dossier file in the ordered set where groupId = &#63;.
	*
	* @param dossierFileId the primary key of the current dossier file
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public static DossierFile[] findByG_PrevAndNext(long dossierFileId,
		long groupId, OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByG_PrevAndNext(dossierFileId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the dossier files where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG(long groupId) {
		getPersistence().removeByG(groupId);
	}

	/**
	* Returns the number of dossier files where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dossier files
	*/
	public static int countByG(long groupId) {
		return getPersistence().countByG(groupId);
	}

	/**
	* Returns all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossier files
	*/
	public static List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo) {
		return getPersistence()
				   .findByD_FTN_DTN(dossierId, fileTemplateNo, dossierTemplateNo);
	}

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
	public static List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end) {
		return getPersistence()
				   .findByD_FTN_DTN(dossierId, fileTemplateNo,
			dossierTemplateNo, start, end);
	}

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
	public static List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .findByD_FTN_DTN(dossierId, fileTemplateNo,
			dossierTemplateNo, start, end, orderByComparator);
	}

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
	public static List<DossierFile> findByD_FTN_DTN(long dossierId,
		String fileTemplateNo, String dossierTemplateNo, int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_FTN_DTN(dossierId, fileTemplateNo,
			dossierTemplateNo, start, end, orderByComparator, retrieveFromCache);
	}

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
	public static DossierFile findByD_FTN_DTN_First(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByD_FTN_DTN_First(dossierId, fileTemplateNo,
			dossierTemplateNo, orderByComparator);
	}

	/**
	* Returns the first dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByD_FTN_DTN_First(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByD_FTN_DTN_First(dossierId, fileTemplateNo,
			dossierTemplateNo, orderByComparator);
	}

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
	public static DossierFile findByD_FTN_DTN_Last(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByD_FTN_DTN_Last(dossierId, fileTemplateNo,
			dossierTemplateNo, orderByComparator);
	}

	/**
	* Returns the last dossier file in the ordered set where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	public static DossierFile fetchByD_FTN_DTN_Last(long dossierId,
		String fileTemplateNo, String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence()
				   .fetchByD_FTN_DTN_Last(dossierId, fileTemplateNo,
			dossierTemplateNo, orderByComparator);
	}

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
	public static DossierFile[] findByD_FTN_DTN_PrevAndNext(
		long dossierFileId, long dossierId, String fileTemplateNo,
		String dossierTemplateNo,
		OrderByComparator<DossierFile> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence()
				   .findByD_FTN_DTN_PrevAndNext(dossierFileId, dossierId,
			fileTemplateNo, dossierTemplateNo, orderByComparator);
	}

	/**
	* Removes all the dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	*/
	public static void removeByD_FTN_DTN(long dossierId, String fileTemplateNo,
		String dossierTemplateNo) {
		getPersistence()
			.removeByD_FTN_DTN(dossierId, fileTemplateNo, dossierTemplateNo);
	}

	/**
	* Returns the number of dossier files where dossierId = &#63; and fileTemplateNo = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param fileTemplateNo the file template no
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossier files
	*/
	public static int countByD_FTN_DTN(long dossierId, String fileTemplateNo,
		String dossierTemplateNo) {
		return getPersistence()
				   .countByD_FTN_DTN(dossierId, fileTemplateNo,
			dossierTemplateNo);
	}

	/**
	* Caches the dossier file in the entity cache if it is enabled.
	*
	* @param dossierFile the dossier file
	*/
	public static void cacheResult(DossierFile dossierFile) {
		getPersistence().cacheResult(dossierFile);
	}

	/**
	* Caches the dossier files in the entity cache if it is enabled.
	*
	* @param dossierFiles the dossier files
	*/
	public static void cacheResult(List<DossierFile> dossierFiles) {
		getPersistence().cacheResult(dossierFiles);
	}

	/**
	* Creates a new dossier file with the primary key. Does not add the dossier file to the database.
	*
	* @param dossierFileId the primary key for the new dossier file
	* @return the new dossier file
	*/
	public static DossierFile create(long dossierFileId) {
		return getPersistence().create(dossierFileId);
	}

	/**
	* Removes the dossier file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file that was removed
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public static DossierFile remove(long dossierFileId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().remove(dossierFileId);
	}

	public static DossierFile updateImpl(DossierFile dossierFile) {
		return getPersistence().updateImpl(dossierFile);
	}

	/**
	* Returns the dossier file with the primary key or throws a {@link NoSuchDossierFileException} if it could not be found.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file
	* @throws NoSuchDossierFileException if a dossier file with the primary key could not be found
	*/
	public static DossierFile findByPrimaryKey(long dossierFileId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierFileException {
		return getPersistence().findByPrimaryKey(dossierFileId);
	}

	/**
	* Returns the dossier file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file, or <code>null</code> if a dossier file with the primary key could not be found
	*/
	public static DossierFile fetchByPrimaryKey(long dossierFileId) {
		return getPersistence().fetchByPrimaryKey(dossierFileId);
	}

	public static java.util.Map<java.io.Serializable, DossierFile> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier files.
	*
	* @return the dossier files
	*/
	public static List<DossierFile> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<DossierFile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<DossierFile> findAll(int start, int end,
		OrderByComparator<DossierFile> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<DossierFile> findAll(int start, int end,
		OrderByComparator<DossierFile> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier files from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier files.
	*
	* @return the number of dossier files
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierFilePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierFilePersistence, DossierFilePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierFilePersistence.class);

		ServiceTracker<DossierFilePersistence, DossierFilePersistence> serviceTracker =
			new ServiceTracker<DossierFilePersistence, DossierFilePersistence>(bundle.getBundleContext(),
				DossierFilePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}