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

package org.opencps.synctracking.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.synctracking.model.SyncTracking;
import org.opencps.synctracking.service.SyncTrackingService;
import org.opencps.synctracking.service.persistence.DossierTaxPersistence;
import org.opencps.synctracking.service.persistence.SyncTrackingPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the sync tracking remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.opencps.synctracking.service.impl.SyncTrackingServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.opencps.synctracking.service.impl.SyncTrackingServiceImpl
 * @see org.opencps.synctracking.service.SyncTrackingServiceUtil
 * @generated
 */
public abstract class SyncTrackingServiceBaseImpl extends BaseServiceImpl
	implements SyncTrackingService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.opencps.synctracking.service.SyncTrackingServiceUtil} to access the sync tracking remote service.
	 */

	/**
	 * Returns the dossier tax local service.
	 *
	 * @return the dossier tax local service
	 */
	public org.opencps.synctracking.service.DossierTaxLocalService getDossierTaxLocalService() {
		return dossierTaxLocalService;
	}

	/**
	 * Sets the dossier tax local service.
	 *
	 * @param dossierTaxLocalService the dossier tax local service
	 */
	public void setDossierTaxLocalService(
		org.opencps.synctracking.service.DossierTaxLocalService dossierTaxLocalService) {
		this.dossierTaxLocalService = dossierTaxLocalService;
	}

	/**
	 * Returns the dossier tax remote service.
	 *
	 * @return the dossier tax remote service
	 */
	public org.opencps.synctracking.service.DossierTaxService getDossierTaxService() {
		return dossierTaxService;
	}

	/**
	 * Sets the dossier tax remote service.
	 *
	 * @param dossierTaxService the dossier tax remote service
	 */
	public void setDossierTaxService(
		org.opencps.synctracking.service.DossierTaxService dossierTaxService) {
		this.dossierTaxService = dossierTaxService;
	}

	/**
	 * Returns the dossier tax persistence.
	 *
	 * @return the dossier tax persistence
	 */
	public DossierTaxPersistence getDossierTaxPersistence() {
		return dossierTaxPersistence;
	}

	/**
	 * Sets the dossier tax persistence.
	 *
	 * @param dossierTaxPersistence the dossier tax persistence
	 */
	public void setDossierTaxPersistence(
		DossierTaxPersistence dossierTaxPersistence) {
		this.dossierTaxPersistence = dossierTaxPersistence;
	}

	/**
	 * Returns the sync tracking local service.
	 *
	 * @return the sync tracking local service
	 */
	public org.opencps.synctracking.service.SyncTrackingLocalService getSyncTrackingLocalService() {
		return syncTrackingLocalService;
	}

	/**
	 * Sets the sync tracking local service.
	 *
	 * @param syncTrackingLocalService the sync tracking local service
	 */
	public void setSyncTrackingLocalService(
		org.opencps.synctracking.service.SyncTrackingLocalService syncTrackingLocalService) {
		this.syncTrackingLocalService = syncTrackingLocalService;
	}

	/**
	 * Returns the sync tracking remote service.
	 *
	 * @return the sync tracking remote service
	 */
	public SyncTrackingService getSyncTrackingService() {
		return syncTrackingService;
	}

	/**
	 * Sets the sync tracking remote service.
	 *
	 * @param syncTrackingService the sync tracking remote service
	 */
	public void setSyncTrackingService(SyncTrackingService syncTrackingService) {
		this.syncTrackingService = syncTrackingService;
	}

	/**
	 * Returns the sync tracking persistence.
	 *
	 * @return the sync tracking persistence
	 */
	public SyncTrackingPersistence getSyncTrackingPersistence() {
		return syncTrackingPersistence;
	}

	/**
	 * Sets the sync tracking persistence.
	 *
	 * @param syncTrackingPersistence the sync tracking persistence
	 */
	public void setSyncTrackingPersistence(
		SyncTrackingPersistence syncTrackingPersistence) {
		this.syncTrackingPersistence = syncTrackingPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SyncTrackingService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SyncTracking.class;
	}

	protected String getModelClassName() {
		return SyncTracking.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = syncTrackingPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = org.opencps.synctracking.service.DossierTaxLocalService.class)
	protected org.opencps.synctracking.service.DossierTaxLocalService dossierTaxLocalService;
	@BeanReference(type = org.opencps.synctracking.service.DossierTaxService.class)
	protected org.opencps.synctracking.service.DossierTaxService dossierTaxService;
	@BeanReference(type = DossierTaxPersistence.class)
	protected DossierTaxPersistence dossierTaxPersistence;
	@BeanReference(type = org.opencps.synctracking.service.SyncTrackingLocalService.class)
	protected org.opencps.synctracking.service.SyncTrackingLocalService syncTrackingLocalService;
	@BeanReference(type = SyncTrackingService.class)
	protected SyncTrackingService syncTrackingService;
	@BeanReference(type = SyncTrackingPersistence.class)
	protected SyncTrackingPersistence syncTrackingPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}