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

package org.opencps.adminconfig.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.adminconfig.model.ApiManager;
import org.opencps.adminconfig.service.ApiManagerLocalService;
import org.opencps.adminconfig.service.persistence.AdminConfigPersistence;
import org.opencps.adminconfig.service.persistence.ApiManagerPersistence;
import org.opencps.adminconfig.service.persistence.ApiRolePersistence;
import org.opencps.adminconfig.service.persistence.DynamicReportPersistence;
import org.opencps.adminconfig.service.persistence.ReportRolePersistence;
import org.opencps.adminconfig.service.persistence.SyncTrackingPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the api manager local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.opencps.adminconfig.service.impl.ApiManagerLocalServiceImpl}.
 * </p>
 *
 * @author binhth
 * @see org.opencps.adminconfig.service.impl.ApiManagerLocalServiceImpl
 * @see org.opencps.adminconfig.service.ApiManagerLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ApiManagerLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ApiManagerLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.opencps.adminconfig.service.ApiManagerLocalServiceUtil} to access the api manager local service.
	 */

	/**
	 * Adds the api manager to the database. Also notifies the appropriate model listeners.
	 *
	 * @param apiManager the api manager
	 * @return the api manager that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ApiManager addApiManager(ApiManager apiManager) {
		apiManager.setNew(true);

		return apiManagerPersistence.update(apiManager);
	}

	/**
	 * Creates a new api manager with the primary key. Does not add the api manager to the database.
	 *
	 * @param apiManagerId the primary key for the new api manager
	 * @return the new api manager
	 */
	@Override
	@Transactional(enabled = false)
	public ApiManager createApiManager(long apiManagerId) {
		return apiManagerPersistence.create(apiManagerId);
	}

	/**
	 * Deletes the api manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param apiManagerId the primary key of the api manager
	 * @return the api manager that was removed
	 * @throws PortalException if a api manager with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ApiManager deleteApiManager(long apiManagerId)
		throws PortalException {
		return apiManagerPersistence.remove(apiManagerId);
	}

	/**
	 * Deletes the api manager from the database. Also notifies the appropriate model listeners.
	 *
	 * @param apiManager the api manager
	 * @return the api manager that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ApiManager deleteApiManager(ApiManager apiManager) {
		return apiManagerPersistence.remove(apiManager);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ApiManager.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return apiManagerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return apiManagerPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return apiManagerPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return apiManagerPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return apiManagerPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ApiManager fetchApiManager(long apiManagerId) {
		return apiManagerPersistence.fetchByPrimaryKey(apiManagerId);
	}

	/**
	 * Returns the api manager with the primary key.
	 *
	 * @param apiManagerId the primary key of the api manager
	 * @return the api manager
	 * @throws PortalException if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager getApiManager(long apiManagerId)
		throws PortalException {
		return apiManagerPersistence.findByPrimaryKey(apiManagerId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(apiManagerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ApiManager.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("apiManagerId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(apiManagerLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ApiManager.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"apiManagerId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(apiManagerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ApiManager.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("apiManagerId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return apiManagerLocalService.deleteApiManager((ApiManager)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return apiManagerPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the api managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of api managers
	 * @param end the upper bound of the range of api managers (not inclusive)
	 * @return the range of api managers
	 */
	@Override
	public List<ApiManager> getApiManagers(int start, int end) {
		return apiManagerPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of api managers.
	 *
	 * @return the number of api managers
	 */
	@Override
	public int getApiManagersCount() {
		return apiManagerPersistence.countAll();
	}

	/**
	 * Updates the api manager in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param apiManager the api manager
	 * @return the api manager that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ApiManager updateApiManager(ApiManager apiManager) {
		return apiManagerPersistence.update(apiManager);
	}

	/**
	 * Returns the admin config local service.
	 *
	 * @return the admin config local service
	 */
	public org.opencps.adminconfig.service.AdminConfigLocalService getAdminConfigLocalService() {
		return adminConfigLocalService;
	}

	/**
	 * Sets the admin config local service.
	 *
	 * @param adminConfigLocalService the admin config local service
	 */
	public void setAdminConfigLocalService(
		org.opencps.adminconfig.service.AdminConfigLocalService adminConfigLocalService) {
		this.adminConfigLocalService = adminConfigLocalService;
	}

	/**
	 * Returns the admin config persistence.
	 *
	 * @return the admin config persistence
	 */
	public AdminConfigPersistence getAdminConfigPersistence() {
		return adminConfigPersistence;
	}

	/**
	 * Sets the admin config persistence.
	 *
	 * @param adminConfigPersistence the admin config persistence
	 */
	public void setAdminConfigPersistence(
		AdminConfigPersistence adminConfigPersistence) {
		this.adminConfigPersistence = adminConfigPersistence;
	}

	/**
	 * Returns the api manager local service.
	 *
	 * @return the api manager local service
	 */
	public ApiManagerLocalService getApiManagerLocalService() {
		return apiManagerLocalService;
	}

	/**
	 * Sets the api manager local service.
	 *
	 * @param apiManagerLocalService the api manager local service
	 */
	public void setApiManagerLocalService(
		ApiManagerLocalService apiManagerLocalService) {
		this.apiManagerLocalService = apiManagerLocalService;
	}

	/**
	 * Returns the api manager persistence.
	 *
	 * @return the api manager persistence
	 */
	public ApiManagerPersistence getApiManagerPersistence() {
		return apiManagerPersistence;
	}

	/**
	 * Sets the api manager persistence.
	 *
	 * @param apiManagerPersistence the api manager persistence
	 */
	public void setApiManagerPersistence(
		ApiManagerPersistence apiManagerPersistence) {
		this.apiManagerPersistence = apiManagerPersistence;
	}

	/**
	 * Returns the api role local service.
	 *
	 * @return the api role local service
	 */
	public org.opencps.adminconfig.service.ApiRoleLocalService getApiRoleLocalService() {
		return apiRoleLocalService;
	}

	/**
	 * Sets the api role local service.
	 *
	 * @param apiRoleLocalService the api role local service
	 */
	public void setApiRoleLocalService(
		org.opencps.adminconfig.service.ApiRoleLocalService apiRoleLocalService) {
		this.apiRoleLocalService = apiRoleLocalService;
	}

	/**
	 * Returns the api role persistence.
	 *
	 * @return the api role persistence
	 */
	public ApiRolePersistence getApiRolePersistence() {
		return apiRolePersistence;
	}

	/**
	 * Sets the api role persistence.
	 *
	 * @param apiRolePersistence the api role persistence
	 */
	public void setApiRolePersistence(ApiRolePersistence apiRolePersistence) {
		this.apiRolePersistence = apiRolePersistence;
	}

	/**
	 * Returns the dynamic report local service.
	 *
	 * @return the dynamic report local service
	 */
	public org.opencps.adminconfig.service.DynamicReportLocalService getDynamicReportLocalService() {
		return dynamicReportLocalService;
	}

	/**
	 * Sets the dynamic report local service.
	 *
	 * @param dynamicReportLocalService the dynamic report local service
	 */
	public void setDynamicReportLocalService(
		org.opencps.adminconfig.service.DynamicReportLocalService dynamicReportLocalService) {
		this.dynamicReportLocalService = dynamicReportLocalService;
	}

	/**
	 * Returns the dynamic report persistence.
	 *
	 * @return the dynamic report persistence
	 */
	public DynamicReportPersistence getDynamicReportPersistence() {
		return dynamicReportPersistence;
	}

	/**
	 * Sets the dynamic report persistence.
	 *
	 * @param dynamicReportPersistence the dynamic report persistence
	 */
	public void setDynamicReportPersistence(
		DynamicReportPersistence dynamicReportPersistence) {
		this.dynamicReportPersistence = dynamicReportPersistence;
	}

	/**
	 * Returns the report role local service.
	 *
	 * @return the report role local service
	 */
	public org.opencps.adminconfig.service.ReportRoleLocalService getReportRoleLocalService() {
		return reportRoleLocalService;
	}

	/**
	 * Sets the report role local service.
	 *
	 * @param reportRoleLocalService the report role local service
	 */
	public void setReportRoleLocalService(
		org.opencps.adminconfig.service.ReportRoleLocalService reportRoleLocalService) {
		this.reportRoleLocalService = reportRoleLocalService;
	}

	/**
	 * Returns the report role persistence.
	 *
	 * @return the report role persistence
	 */
	public ReportRolePersistence getReportRolePersistence() {
		return reportRolePersistence;
	}

	/**
	 * Sets the report role persistence.
	 *
	 * @param reportRolePersistence the report role persistence
	 */
	public void setReportRolePersistence(
		ReportRolePersistence reportRolePersistence) {
		this.reportRolePersistence = reportRolePersistence;
	}

	/**
	 * Returns the sync tracking local service.
	 *
	 * @return the sync tracking local service
	 */
	public org.opencps.adminconfig.service.SyncTrackingLocalService getSyncTrackingLocalService() {
		return syncTrackingLocalService;
	}

	/**
	 * Sets the sync tracking local service.
	 *
	 * @param syncTrackingLocalService the sync tracking local service
	 */
	public void setSyncTrackingLocalService(
		org.opencps.adminconfig.service.SyncTrackingLocalService syncTrackingLocalService) {
		this.syncTrackingLocalService = syncTrackingLocalService;
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
		persistedModelLocalServiceRegistry.register("org.opencps.adminconfig.model.ApiManager",
			apiManagerLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"org.opencps.adminconfig.model.ApiManager");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ApiManagerLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ApiManager.class;
	}

	protected String getModelClassName() {
		return ApiManager.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = apiManagerPersistence.getDataSource();

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

	@BeanReference(type = org.opencps.adminconfig.service.AdminConfigLocalService.class)
	protected org.opencps.adminconfig.service.AdminConfigLocalService adminConfigLocalService;
	@BeanReference(type = AdminConfigPersistence.class)
	protected AdminConfigPersistence adminConfigPersistence;
	@BeanReference(type = ApiManagerLocalService.class)
	protected ApiManagerLocalService apiManagerLocalService;
	@BeanReference(type = ApiManagerPersistence.class)
	protected ApiManagerPersistence apiManagerPersistence;
	@BeanReference(type = org.opencps.adminconfig.service.ApiRoleLocalService.class)
	protected org.opencps.adminconfig.service.ApiRoleLocalService apiRoleLocalService;
	@BeanReference(type = ApiRolePersistence.class)
	protected ApiRolePersistence apiRolePersistence;
	@BeanReference(type = org.opencps.adminconfig.service.DynamicReportLocalService.class)
	protected org.opencps.adminconfig.service.DynamicReportLocalService dynamicReportLocalService;
	@BeanReference(type = DynamicReportPersistence.class)
	protected DynamicReportPersistence dynamicReportPersistence;
	@BeanReference(type = org.opencps.adminconfig.service.ReportRoleLocalService.class)
	protected org.opencps.adminconfig.service.ReportRoleLocalService reportRoleLocalService;
	@BeanReference(type = ReportRolePersistence.class)
	protected ReportRolePersistence reportRolePersistence;
	@BeanReference(type = org.opencps.adminconfig.service.SyncTrackingLocalService.class)
	protected org.opencps.adminconfig.service.SyncTrackingLocalService syncTrackingLocalService;
	@BeanReference(type = SyncTrackingPersistence.class)
	protected SyncTrackingPersistence syncTrackingPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}