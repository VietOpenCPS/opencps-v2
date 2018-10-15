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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DossierUser. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.DossierUserLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see DossierUserLocalService
 * @see org.opencps.dossiermgt.service.base.DossierUserLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierUserLocalServiceImpl
 * @generated
 */
@ProviderType
public class DossierUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dossier user to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUser the dossier user
	* @return the dossier user that was added
	*/
	public static org.opencps.dossiermgt.model.DossierUser addDossierUser(
		org.opencps.dossiermgt.model.DossierUser dossierUser) {
		return getService().addDossierUser(dossierUser);
	}

	public static org.opencps.dossiermgt.model.DossierUser addDossierUser(
		long groupId, long dossierId, long userId, int moderator,
		boolean visited) {
		return getService()
				   .addDossierUser(groupId, dossierId, userId, moderator,
			visited);
	}

	/**
	* Creates a new dossier user with the primary key. Does not add the dossier user to the database.
	*
	* @param dossierUserPK the primary key for the new dossier user
	* @return the new dossier user
	*/
	public static org.opencps.dossiermgt.model.DossierUser createDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK) {
		return getService().createDossierUser(dossierUserPK);
	}

	/**
	* Deletes the dossier user from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUser the dossier user
	* @return the dossier user that was removed
	*/
	public static org.opencps.dossiermgt.model.DossierUser deleteDossierUser(
		org.opencps.dossiermgt.model.DossierUser dossierUser) {
		return getService().deleteDossierUser(dossierUser);
	}

	/**
	* Deletes the dossier user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user that was removed
	* @throws PortalException if a dossier user with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierUser deleteDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDossierUser(dossierUserPK);
	}

	public static org.opencps.dossiermgt.model.DossierUser deleteDossierUser(
		long dossierId, long userId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getService().deleteDossierUser(dossierId, userId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.dossiermgt.model.DossierUser fetchDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK) {
		return getService().fetchDossierUser(dossierUserPK);
	}

	public static java.util.List<org.opencps.dossiermgt.model.DossierUser> findByDID(
		long dossierId) {
		return getService().findByDID(dossierId);
	}

	public static org.opencps.dossiermgt.model.DossierUser findByDID_UD(
		long dossierId, long userId) {
		return getService().findByDID_UD(dossierId, userId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.DossierUser getByDossierUser(
		long dossierId, long userId) {
		return getService().getByDossierUser(dossierId, userId);
	}

	/**
	* Returns the dossier user with the primary key.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user
	* @throws PortalException if a dossier user with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierUser getDossierUser(
		org.opencps.dossiermgt.service.persistence.DossierUserPK dossierUserPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierUser(dossierUserPK);
	}

	/**
	* Returns a range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of dossier users
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierUser> getDossierUsers(
		int start, int end) {
		return getService().getDossierUsers(start, end);
	}

	/**
	* Returns the number of dossier users.
	*
	* @return the number of dossier users
	*/
	public static int getDossierUsersCount() {
		return getService().getDossierUsersCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the dossier user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierUser the dossier user
	* @return the dossier user that was updated
	*/
	public static org.opencps.dossiermgt.model.DossierUser updateDossierUser(
		org.opencps.dossiermgt.model.DossierUser dossierUser) {
		return getService().updateDossierUser(dossierUser);
	}

	public static org.opencps.dossiermgt.model.DossierUser updateDossierUser(
		long dossierId, long userId, int moderator, boolean visited)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getService()
				   .updateDossierUser(dossierId, userId, moderator, visited);
	}

	public static DossierUserLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierUserLocalService, DossierUserLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierUserLocalService.class);

		ServiceTracker<DossierUserLocalService, DossierUserLocalService> serviceTracker =
			new ServiceTracker<DossierUserLocalService, DossierUserLocalService>(bundle.getBundleContext(),
				DossierUserLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}