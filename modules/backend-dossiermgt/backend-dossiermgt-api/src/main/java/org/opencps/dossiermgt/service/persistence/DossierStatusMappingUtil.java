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

import org.opencps.dossiermgt.model.DossierStatusMapping;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier status mapping service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierStatusMappingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierStatusMappingPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierStatusMappingPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierStatusMappingUtil {
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
	public static void clearCache(DossierStatusMapping dossierStatusMapping) {
		getPersistence().clearCache(dossierStatusMapping);
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
	public static List<DossierStatusMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierStatusMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierStatusMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierStatusMapping update(
		DossierStatusMapping dossierStatusMapping) {
		return getPersistence().update(dossierStatusMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierStatusMapping update(
		DossierStatusMapping dossierStatusMapping, ServiceContext serviceContext) {
		return getPersistence().update(dossierStatusMapping, serviceContext);
	}

	/**
	* Returns all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @return the matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode) {
		return getPersistence().findByF_GID_SC(groupId, statusCode);
	}

	/**
	* Returns a range of all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @return the range of matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode, int start, int end) {
		return getPersistence().findByF_GID_SC(groupId, statusCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence()
				   .findByF_GID_SC(groupId, statusCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SC(long groupId,
		String statusCode, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_SC(groupId, statusCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping findByF_GID_SC_First(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence()
				   .findByF_GID_SC_First(groupId, statusCode, orderByComparator);
	}

	/**
	* Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping fetchByF_GID_SC_First(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SC_First(groupId, statusCode, orderByComparator);
	}

	/**
	* Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping findByF_GID_SC_Last(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence()
				   .findByF_GID_SC_Last(groupId, statusCode, orderByComparator);
	}

	/**
	* Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping fetchByF_GID_SC_Last(long groupId,
		String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SC_Last(groupId, statusCode, orderByComparator);
	}

	/**
	* Returns the dossier status mappings before and after the current dossier status mapping in the ordered set where groupId = &#63; and statusCode = &#63;.
	*
	* @param dossierStatusMappingId the primary key of the current dossier status mapping
	* @param groupId the group ID
	* @param statusCode the status code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	*/
	public static DossierStatusMapping[] findByF_GID_SC_PrevAndNext(
		long dossierStatusMappingId, long groupId, String statusCode,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence()
				   .findByF_GID_SC_PrevAndNext(dossierStatusMappingId, groupId,
			statusCode, orderByComparator);
	}

	/**
	* Removes all the dossier status mappings where groupId = &#63; and statusCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param statusCode the status code
	*/
	public static void removeByF_GID_SC(long groupId, String statusCode) {
		getPersistence().removeByF_GID_SC(groupId, statusCode);
	}

	/**
	* Returns the number of dossier status mappings where groupId = &#63; and statusCode = &#63;.
	*
	* @param groupId the group ID
	* @param statusCode the status code
	* @return the number of matching dossier status mappings
	*/
	public static int countByF_GID_SC(long groupId, String statusCode) {
		return getPersistence().countByF_GID_SC(groupId, statusCode);
	}

	/**
	* Returns all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @return the matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG) {
		return getPersistence().findByF_GID_SCDVCQG(groupId, statusCodeDVCQG);
	}

	/**
	* Returns a range of all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @return the range of matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG, int start, int end) {
		return getPersistence()
				   .findByF_GID_SCDVCQG(groupId, statusCodeDVCQG, start, end);
	}

	/**
	* Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence()
				   .findByF_GID_SCDVCQG(groupId, statusCodeDVCQG, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier status mappings
	*/
	public static List<DossierStatusMapping> findByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG, int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_SCDVCQG(groupId, statusCodeDVCQG, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping findByF_GID_SCDVCQG_First(long groupId,
		String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence()
				   .findByF_GID_SCDVCQG_First(groupId, statusCodeDVCQG,
			orderByComparator);
	}

	/**
	* Returns the first dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping fetchByF_GID_SCDVCQG_First(
		long groupId, String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SCDVCQG_First(groupId, statusCodeDVCQG,
			orderByComparator);
	}

	/**
	* Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping findByF_GID_SCDVCQG_Last(long groupId,
		String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence()
				   .findByF_GID_SCDVCQG_Last(groupId, statusCodeDVCQG,
			orderByComparator);
	}

	/**
	* Returns the last dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping fetchByF_GID_SCDVCQG_Last(long groupId,
		String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SCDVCQG_Last(groupId, statusCodeDVCQG,
			orderByComparator);
	}

	/**
	* Returns the dossier status mappings before and after the current dossier status mapping in the ordered set where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* @param dossierStatusMappingId the primary key of the current dossier status mapping
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	*/
	public static DossierStatusMapping[] findByF_GID_SCDVCQG_PrevAndNext(
		long dossierStatusMappingId, long groupId, String statusCodeDVCQG,
		OrderByComparator<DossierStatusMapping> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence()
				   .findByF_GID_SCDVCQG_PrevAndNext(dossierStatusMappingId,
			groupId, statusCodeDVCQG, orderByComparator);
	}

	/**
	* Removes all the dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63; from the database.
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	*/
	public static void removeByF_GID_SCDVCQG(long groupId,
		String statusCodeDVCQG) {
		getPersistence().removeByF_GID_SCDVCQG(groupId, statusCodeDVCQG);
	}

	/**
	* Returns the number of dossier status mappings where groupId = &#63; and statusCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param statusCodeDVCQG the status code dvcqg
	* @return the number of matching dossier status mappings
	*/
	public static int countByF_GID_SCDVCQG(long groupId, String statusCodeDVCQG) {
		return getPersistence().countByF_GID_SCDVCQG(groupId, statusCodeDVCQG);
	}

	/**
	* Returns the dossier status mapping where groupId = &#63; and subStatusCode = &#63; or throws a {@link NoSuchDossierStatusMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param subStatusCode the sub status code
	* @return the matching dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping findByF_GID_SUBSC(long groupId,
		String subStatusCode)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence().findByF_GID_SUBSC(groupId, subStatusCode);
	}

	/**
	* Returns the dossier status mapping where groupId = &#63; and subStatusCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param subStatusCode the sub status code
	* @return the matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping fetchByF_GID_SUBSC(long groupId,
		String subStatusCode) {
		return getPersistence().fetchByF_GID_SUBSC(groupId, subStatusCode);
	}

	/**
	* Returns the dossier status mapping where groupId = &#63; and subStatusCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param subStatusCode the sub status code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier status mapping, or <code>null</code> if a matching dossier status mapping could not be found
	*/
	public static DossierStatusMapping fetchByF_GID_SUBSC(long groupId,
		String subStatusCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_SUBSC(groupId, subStatusCode, retrieveFromCache);
	}

	/**
	* Removes the dossier status mapping where groupId = &#63; and subStatusCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param subStatusCode the sub status code
	* @return the dossier status mapping that was removed
	*/
	public static DossierStatusMapping removeByF_GID_SUBSC(long groupId,
		String subStatusCode)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence().removeByF_GID_SUBSC(groupId, subStatusCode);
	}

	/**
	* Returns the number of dossier status mappings where groupId = &#63; and subStatusCode = &#63;.
	*
	* @param groupId the group ID
	* @param subStatusCode the sub status code
	* @return the number of matching dossier status mappings
	*/
	public static int countByF_GID_SUBSC(long groupId, String subStatusCode) {
		return getPersistence().countByF_GID_SUBSC(groupId, subStatusCode);
	}

	/**
	* Caches the dossier status mapping in the entity cache if it is enabled.
	*
	* @param dossierStatusMapping the dossier status mapping
	*/
	public static void cacheResult(DossierStatusMapping dossierStatusMapping) {
		getPersistence().cacheResult(dossierStatusMapping);
	}

	/**
	* Caches the dossier status mappings in the entity cache if it is enabled.
	*
	* @param dossierStatusMappings the dossier status mappings
	*/
	public static void cacheResult(
		List<DossierStatusMapping> dossierStatusMappings) {
		getPersistence().cacheResult(dossierStatusMappings);
	}

	/**
	* Creates a new dossier status mapping with the primary key. Does not add the dossier status mapping to the database.
	*
	* @param dossierStatusMappingId the primary key for the new dossier status mapping
	* @return the new dossier status mapping
	*/
	public static DossierStatusMapping create(long dossierStatusMappingId) {
		return getPersistence().create(dossierStatusMappingId);
	}

	/**
	* Removes the dossier status mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatusMappingId the primary key of the dossier status mapping
	* @return the dossier status mapping that was removed
	* @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	*/
	public static DossierStatusMapping remove(long dossierStatusMappingId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence().remove(dossierStatusMappingId);
	}

	public static DossierStatusMapping updateImpl(
		DossierStatusMapping dossierStatusMapping) {
		return getPersistence().updateImpl(dossierStatusMapping);
	}

	/**
	* Returns the dossier status mapping with the primary key or throws a {@link NoSuchDossierStatusMappingException} if it could not be found.
	*
	* @param dossierStatusMappingId the primary key of the dossier status mapping
	* @return the dossier status mapping
	* @throws NoSuchDossierStatusMappingException if a dossier status mapping with the primary key could not be found
	*/
	public static DossierStatusMapping findByPrimaryKey(
		long dossierStatusMappingId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierStatusMappingException {
		return getPersistence().findByPrimaryKey(dossierStatusMappingId);
	}

	/**
	* Returns the dossier status mapping with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierStatusMappingId the primary key of the dossier status mapping
	* @return the dossier status mapping, or <code>null</code> if a dossier status mapping with the primary key could not be found
	*/
	public static DossierStatusMapping fetchByPrimaryKey(
		long dossierStatusMappingId) {
		return getPersistence().fetchByPrimaryKey(dossierStatusMappingId);
	}

	public static java.util.Map<java.io.Serializable, DossierStatusMapping> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier status mappings.
	*
	* @return the dossier status mappings
	*/
	public static List<DossierStatusMapping> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier status mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @return the range of dossier status mappings
	*/
	public static List<DossierStatusMapping> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier status mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier status mappings
	*/
	public static List<DossierStatusMapping> findAll(int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier status mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier status mappings
	*/
	public static List<DossierStatusMapping> findAll(int start, int end,
		OrderByComparator<DossierStatusMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier status mappings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier status mappings.
	*
	* @return the number of dossier status mappings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DossierStatusMappingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierStatusMappingPersistence, DossierStatusMappingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierStatusMappingPersistence.class);

		ServiceTracker<DossierStatusMappingPersistence, DossierStatusMappingPersistence> serviceTracker =
			new ServiceTracker<DossierStatusMappingPersistence, DossierStatusMappingPersistence>(bundle.getBundleContext(),
				DossierStatusMappingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}