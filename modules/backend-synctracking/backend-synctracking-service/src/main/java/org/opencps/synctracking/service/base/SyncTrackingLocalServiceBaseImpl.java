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

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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

import org.opencps.synctracking.model.SyncTracking;
import org.opencps.synctracking.service.SyncTrackingLocalService;
import org.opencps.synctracking.service.persistence.DossierTaxPersistence;
import org.opencps.synctracking.service.persistence.SyncTrackingPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the sync tracking local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.opencps.synctracking.service.impl.SyncTrackingLocalServiceImpl}.
 * </p>
 *
 * @author duongnt
 * @see org.opencps.synctracking.service.impl.SyncTrackingLocalServiceImpl
 * @see org.opencps.synctracking.service.SyncTrackingLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class SyncTrackingLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements SyncTrackingLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.opencps.synctracking.service.SyncTrackingLocalServiceUtil} to access the sync tracking local service.
	 */

	/**
	 * Adds the sync tracking to the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncTracking the sync tracking
	 * @return the sync tracking that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SyncTracking addSyncTracking(SyncTracking syncTracking) {
		syncTracking.setNew(true);

		return syncTrackingPersistence.update(syncTracking);
	}

	/**
	 * Creates a new sync tracking with the primary key. Does not add the sync tracking to the database.
	 *
	 * @param trackingId the primary key for the new sync tracking
	 * @return the new sync tracking
	 */
	@Override
	@Transactional(enabled = false)
	public SyncTracking createSyncTracking(long trackingId) {
		return syncTrackingPersistence.create(trackingId);
	}

	/**
	 * Deletes the sync tracking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackingId the primary key of the sync tracking
	 * @return the sync tracking that was removed
	 * @throws PortalException if a sync tracking with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SyncTracking deleteSyncTracking(long trackingId)
		throws PortalException {
		return syncTrackingPersistence.remove(trackingId);
	}

	/**
	 * Deletes the sync tracking from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncTracking the sync tracking
	 * @return the sync tracking that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SyncTracking deleteSyncTracking(SyncTracking syncTracking) {
		return syncTrackingPersistence.remove(syncTracking);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(SyncTracking.class,
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
		return syncTrackingPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return syncTrackingPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return syncTrackingPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return syncTrackingPersistence.countWithDynamicQuery(dynamicQuery);
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
		return syncTrackingPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public SyncTracking fetchSyncTracking(long trackingId) {
		return syncTrackingPersistence.fetchByPrimaryKey(trackingId);
	}

	/**
	 * Returns the sync tracking matching the UUID and group.
	 *
	 * @param uuid the sync tracking's UUID
	 * @param groupId the primary key of the group
	 * @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchSyncTrackingByUuidAndGroupId(String uuid,
		long groupId) {
		return syncTrackingPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the sync tracking with the primary key.
	 *
	 * @param trackingId the primary key of the sync tracking
	 * @return the sync tracking
	 * @throws PortalException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking getSyncTracking(long trackingId)
		throws PortalException {
		return syncTrackingPersistence.findByPrimaryKey(trackingId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(syncTrackingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SyncTracking.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("trackingId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(syncTrackingLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SyncTracking.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("trackingId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(syncTrackingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SyncTracking.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("trackingId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SyncTracking>() {
				@Override
				public void performAction(SyncTracking syncTracking)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						syncTracking);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(SyncTracking.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return syncTrackingLocalService.deleteSyncTracking((SyncTracking)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return syncTrackingPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the sync trackings matching the UUID and company.
	 *
	 * @param uuid the UUID of the sync trackings
	 * @param companyId the primary key of the company
	 * @return the matching sync trackings, or an empty list if no matches were found
	 */
	@Override
	public List<SyncTracking> getSyncTrackingsByUuidAndCompanyId(String uuid,
		long companyId) {
		return syncTrackingPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of sync trackings matching the UUID and company.
	 *
	 * @param uuid the UUID of the sync trackings
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching sync trackings, or an empty list if no matches were found
	 */
	@Override
	public List<SyncTracking> getSyncTrackingsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return syncTrackingPersistence.findByUuid_C(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	 * Returns the sync tracking matching the UUID and group.
	 *
	 * @param uuid the sync tracking's UUID
	 * @param groupId the primary key of the group
	 * @return the matching sync tracking
	 * @throws PortalException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking getSyncTrackingByUuidAndGroupId(String uuid,
		long groupId) throws PortalException {
		return syncTrackingPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the sync trackings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of sync trackings
	 */
	@Override
	public List<SyncTracking> getSyncTrackings(int start, int end) {
		return syncTrackingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of sync trackings.
	 *
	 * @return the number of sync trackings
	 */
	@Override
	public int getSyncTrackingsCount() {
		return syncTrackingPersistence.countAll();
	}

	/**
	 * Updates the sync tracking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param syncTracking the sync tracking
	 * @return the sync tracking that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SyncTracking updateSyncTracking(SyncTracking syncTracking) {
		return syncTrackingPersistence.update(syncTracking);
	}

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
	public SyncTrackingLocalService getSyncTrackingLocalService() {
		return syncTrackingLocalService;
	}

	/**
	 * Sets the sync tracking local service.
	 *
	 * @param syncTrackingLocalService the sync tracking local service
	 */
	public void setSyncTrackingLocalService(
		SyncTrackingLocalService syncTrackingLocalService) {
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
		persistedModelLocalServiceRegistry.register("org.opencps.synctracking.model.SyncTracking",
			syncTrackingLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"org.opencps.synctracking.model.SyncTracking");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SyncTrackingLocalService.class.getName();
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
	@BeanReference(type = DossierTaxPersistence.class)
	protected DossierTaxPersistence dossierTaxPersistence;
	@BeanReference(type = SyncTrackingLocalService.class)
	protected SyncTrackingLocalService syncTrackingLocalService;
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