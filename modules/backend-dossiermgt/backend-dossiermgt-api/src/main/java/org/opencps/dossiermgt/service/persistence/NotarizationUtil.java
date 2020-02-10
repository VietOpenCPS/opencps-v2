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

import org.opencps.dossiermgt.model.Notarization;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the notarization service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.NotarizationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see NotarizationPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.NotarizationPersistenceImpl
 * @generated
 */
@ProviderType
public class NotarizationUtil {
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
	public static void clearCache(Notarization notarization) {
		getPersistence().clearCache(notarization);
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
	public static List<Notarization> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Notarization> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Notarization> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Notarization> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Notarization update(Notarization notarization) {
		return getPersistence().update(notarization);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Notarization update(Notarization notarization,
		ServiceContext serviceContext) {
		return getPersistence().update(notarization, serviceContext);
	}

	/**
	* Returns all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching notarizations
	*/
	public static List<Notarization> findByF_G_DID(long groupId, long dossierId) {
		return getPersistence().findByF_G_DID(groupId, dossierId);
	}

	/**
	* Returns a range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @return the range of matching notarizations
	*/
	public static List<Notarization> findByF_G_DID(long groupId,
		long dossierId, int start, int end) {
		return getPersistence().findByF_G_DID(groupId, dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notarizations
	*/
	public static List<Notarization> findByF_G_DID(long groupId,
		long dossierId, int start, int end,
		OrderByComparator<Notarization> orderByComparator) {
		return getPersistence()
				   .findByF_G_DID(groupId, dossierId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notarizations
	*/
	public static List<Notarization> findByF_G_DID(long groupId,
		long dossierId, int start, int end,
		OrderByComparator<Notarization> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_G_DID(groupId, dossierId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notarization
	* @throws NoSuchNotarizationException if a matching notarization could not be found
	*/
	public static Notarization findByF_G_DID_First(long groupId,
		long dossierId, OrderByComparator<Notarization> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNotarizationException {
		return getPersistence()
				   .findByF_G_DID_First(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the first notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notarization, or <code>null</code> if a matching notarization could not be found
	*/
	public static Notarization fetchByF_G_DID_First(long groupId,
		long dossierId, OrderByComparator<Notarization> orderByComparator) {
		return getPersistence()
				   .fetchByF_G_DID_First(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the last notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notarization
	* @throws NoSuchNotarizationException if a matching notarization could not be found
	*/
	public static Notarization findByF_G_DID_Last(long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNotarizationException {
		return getPersistence()
				   .findByF_G_DID_Last(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the last notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notarization, or <code>null</code> if a matching notarization could not be found
	*/
	public static Notarization fetchByF_G_DID_Last(long groupId,
		long dossierId, OrderByComparator<Notarization> orderByComparator) {
		return getPersistence()
				   .fetchByF_G_DID_Last(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the notarizations before and after the current notarization in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param notarizationId the primary key of the current notarization
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notarization
	* @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	*/
	public static Notarization[] findByF_G_DID_PrevAndNext(
		long notarizationId, long groupId, long dossierId,
		OrderByComparator<Notarization> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNotarizationException {
		return getPersistence()
				   .findByF_G_DID_PrevAndNext(notarizationId, groupId,
			dossierId, orderByComparator);
	}

	/**
	* Removes all the notarizations where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public static void removeByF_G_DID(long groupId, long dossierId) {
		getPersistence().removeByF_G_DID(groupId, dossierId);
	}

	/**
	* Returns the number of notarizations where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching notarizations
	*/
	public static int countByF_G_DID(long groupId, long dossierId) {
		return getPersistence().countByF_G_DID(groupId, dossierId);
	}

	/**
	* Caches the notarization in the entity cache if it is enabled.
	*
	* @param notarization the notarization
	*/
	public static void cacheResult(Notarization notarization) {
		getPersistence().cacheResult(notarization);
	}

	/**
	* Caches the notarizations in the entity cache if it is enabled.
	*
	* @param notarizations the notarizations
	*/
	public static void cacheResult(List<Notarization> notarizations) {
		getPersistence().cacheResult(notarizations);
	}

	/**
	* Creates a new notarization with the primary key. Does not add the notarization to the database.
	*
	* @param notarizationId the primary key for the new notarization
	* @return the new notarization
	*/
	public static Notarization create(long notarizationId) {
		return getPersistence().create(notarizationId);
	}

	/**
	* Removes the notarization with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization that was removed
	* @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	*/
	public static Notarization remove(long notarizationId)
		throws org.opencps.dossiermgt.exception.NoSuchNotarizationException {
		return getPersistence().remove(notarizationId);
	}

	public static Notarization updateImpl(Notarization notarization) {
		return getPersistence().updateImpl(notarization);
	}

	/**
	* Returns the notarization with the primary key or throws a {@link NoSuchNotarizationException} if it could not be found.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization
	* @throws NoSuchNotarizationException if a notarization with the primary key could not be found
	*/
	public static Notarization findByPrimaryKey(long notarizationId)
		throws org.opencps.dossiermgt.exception.NoSuchNotarizationException {
		return getPersistence().findByPrimaryKey(notarizationId);
	}

	/**
	* Returns the notarization with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization, or <code>null</code> if a notarization with the primary key could not be found
	*/
	public static Notarization fetchByPrimaryKey(long notarizationId) {
		return getPersistence().fetchByPrimaryKey(notarizationId);
	}

	public static java.util.Map<java.io.Serializable, Notarization> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the notarizations.
	*
	* @return the notarizations
	*/
	public static List<Notarization> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @return the range of notarizations
	*/
	public static List<Notarization> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of notarizations
	*/
	public static List<Notarization> findAll(int start, int end,
		OrderByComparator<Notarization> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of notarizations
	*/
	public static List<Notarization> findAll(int start, int end,
		OrderByComparator<Notarization> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the notarizations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of notarizations.
	*
	* @return the number of notarizations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NotarizationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NotarizationPersistence, NotarizationPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NotarizationPersistence.class);

		ServiceTracker<NotarizationPersistence, NotarizationPersistence> serviceTracker =
			new ServiceTracker<NotarizationPersistence, NotarizationPersistence>(bundle.getBundleContext(),
				NotarizationPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}