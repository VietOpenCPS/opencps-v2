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

package opencps.dvcqg.extend.sync.service.base;

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

import opencps.dvcqg.extend.sync.model.ApplicableInfo;
import opencps.dvcqg.extend.sync.service.ApplicableInfoLocalService;
import opencps.dvcqg.extend.sync.service.persistence.ApplicableInfoPersistence;
import opencps.dvcqg.extend.sync.service.persistence.PaymentFeeInfoPersistence;
import opencps.dvcqg.extend.sync.service.persistence.ServiceConfigMappingPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the applicable info local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link opencps.dvcqg.extend.sync.service.impl.ApplicableInfoLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see opencps.dvcqg.extend.sync.service.impl.ApplicableInfoLocalServiceImpl
 * @see opencps.dvcqg.extend.sync.service.ApplicableInfoLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ApplicableInfoLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ApplicableInfoLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link opencps.dvcqg.extend.sync.service.ApplicableInfoLocalServiceUtil} to access the applicable info local service.
	 */

	/**
	 * Adds the applicable info to the database. Also notifies the appropriate model listeners.
	 *
	 * @param applicableInfo the applicable info
	 * @return the applicable info that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ApplicableInfo addApplicableInfo(ApplicableInfo applicableInfo) {
		applicableInfo.setNew(true);

		return applicableInfoPersistence.update(applicableInfo);
	}

	/**
	 * Creates a new applicable info with the primary key. Does not add the applicable info to the database.
	 *
	 * @param applicableInfoId the primary key for the new applicable info
	 * @return the new applicable info
	 */
	@Override
	@Transactional(enabled = false)
	public ApplicableInfo createApplicableInfo(long applicableInfoId) {
		return applicableInfoPersistence.create(applicableInfoId);
	}

	/**
	 * Deletes the applicable info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param applicableInfoId the primary key of the applicable info
	 * @return the applicable info that was removed
	 * @throws PortalException if a applicable info with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ApplicableInfo deleteApplicableInfo(long applicableInfoId)
		throws PortalException {
		return applicableInfoPersistence.remove(applicableInfoId);
	}

	/**
	 * Deletes the applicable info from the database. Also notifies the appropriate model listeners.
	 *
	 * @param applicableInfo the applicable info
	 * @return the applicable info that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ApplicableInfo deleteApplicableInfo(ApplicableInfo applicableInfo) {
		return applicableInfoPersistence.remove(applicableInfo);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ApplicableInfo.class,
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
		return applicableInfoPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link opencps.dvcqg.extend.sync.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return applicableInfoPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link opencps.dvcqg.extend.sync.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return applicableInfoPersistence.findWithDynamicQuery(dynamicQuery,
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
		return applicableInfoPersistence.countWithDynamicQuery(dynamicQuery);
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
		return applicableInfoPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ApplicableInfo fetchApplicableInfo(long applicableInfoId) {
		return applicableInfoPersistence.fetchByPrimaryKey(applicableInfoId);
	}

	/**
	 * Returns the applicable info matching the UUID and group.
	 *
	 * @param uuid the applicable info's UUID
	 * @param groupId the primary key of the group
	 * @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo fetchApplicableInfoByUuidAndGroupId(String uuid,
		long groupId) {
		return applicableInfoPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the applicable info with the primary key.
	 *
	 * @param applicableInfoId the primary key of the applicable info
	 * @return the applicable info
	 * @throws PortalException if a applicable info with the primary key could not be found
	 */
	@Override
	public ApplicableInfo getApplicableInfo(long applicableInfoId)
		throws PortalException {
		return applicableInfoPersistence.findByPrimaryKey(applicableInfoId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(applicableInfoLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ApplicableInfo.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("applicableInfoId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(applicableInfoLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ApplicableInfo.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"applicableInfoId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(applicableInfoLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ApplicableInfo.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("applicableInfoId");
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

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ApplicableInfo>() {
				@Override
				public void performAction(ApplicableInfo applicableInfo)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						applicableInfo);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(ApplicableInfo.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return applicableInfoLocalService.deleteApplicableInfo((ApplicableInfo)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return applicableInfoPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the applicable infos matching the UUID and company.
	 *
	 * @param uuid the UUID of the applicable infos
	 * @param companyId the primary key of the company
	 * @return the matching applicable infos, or an empty list if no matches were found
	 */
	@Override
	public List<ApplicableInfo> getApplicableInfosByUuidAndCompanyId(
		String uuid, long companyId) {
		return applicableInfoPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of applicable infos matching the UUID and company.
	 *
	 * @param uuid the UUID of the applicable infos
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching applicable infos, or an empty list if no matches were found
	 */
	@Override
	public List<ApplicableInfo> getApplicableInfosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return applicableInfoPersistence.findByUuid_C(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	 * Returns the applicable info matching the UUID and group.
	 *
	 * @param uuid the applicable info's UUID
	 * @param groupId the primary key of the group
	 * @return the matching applicable info
	 * @throws PortalException if a matching applicable info could not be found
	 */
	@Override
	public ApplicableInfo getApplicableInfoByUuidAndGroupId(String uuid,
		long groupId) throws PortalException {
		return applicableInfoPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the applicable infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link opencps.dvcqg.extend.sync.model.impl.ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of applicable infos
	 * @param end the upper bound of the range of applicable infos (not inclusive)
	 * @return the range of applicable infos
	 */
	@Override
	public List<ApplicableInfo> getApplicableInfos(int start, int end) {
		return applicableInfoPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of applicable infos.
	 *
	 * @return the number of applicable infos
	 */
	@Override
	public int getApplicableInfosCount() {
		return applicableInfoPersistence.countAll();
	}

	/**
	 * Updates the applicable info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param applicableInfo the applicable info
	 * @return the applicable info that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ApplicableInfo updateApplicableInfo(ApplicableInfo applicableInfo) {
		return applicableInfoPersistence.update(applicableInfo);
	}

	/**
	 * Returns the applicable info local service.
	 *
	 * @return the applicable info local service
	 */
	public ApplicableInfoLocalService getApplicableInfoLocalService() {
		return applicableInfoLocalService;
	}

	/**
	 * Sets the applicable info local service.
	 *
	 * @param applicableInfoLocalService the applicable info local service
	 */
	public void setApplicableInfoLocalService(
		ApplicableInfoLocalService applicableInfoLocalService) {
		this.applicableInfoLocalService = applicableInfoLocalService;
	}

	/**
	 * Returns the applicable info persistence.
	 *
	 * @return the applicable info persistence
	 */
	public ApplicableInfoPersistence getApplicableInfoPersistence() {
		return applicableInfoPersistence;
	}

	/**
	 * Sets the applicable info persistence.
	 *
	 * @param applicableInfoPersistence the applicable info persistence
	 */
	public void setApplicableInfoPersistence(
		ApplicableInfoPersistence applicableInfoPersistence) {
		this.applicableInfoPersistence = applicableInfoPersistence;
	}

	/**
	 * Returns the payment fee info local service.
	 *
	 * @return the payment fee info local service
	 */
	public opencps.dvcqg.extend.sync.service.PaymentFeeInfoLocalService getPaymentFeeInfoLocalService() {
		return paymentFeeInfoLocalService;
	}

	/**
	 * Sets the payment fee info local service.
	 *
	 * @param paymentFeeInfoLocalService the payment fee info local service
	 */
	public void setPaymentFeeInfoLocalService(
		opencps.dvcqg.extend.sync.service.PaymentFeeInfoLocalService paymentFeeInfoLocalService) {
		this.paymentFeeInfoLocalService = paymentFeeInfoLocalService;
	}

	/**
	 * Returns the payment fee info persistence.
	 *
	 * @return the payment fee info persistence
	 */
	public PaymentFeeInfoPersistence getPaymentFeeInfoPersistence() {
		return paymentFeeInfoPersistence;
	}

	/**
	 * Sets the payment fee info persistence.
	 *
	 * @param paymentFeeInfoPersistence the payment fee info persistence
	 */
	public void setPaymentFeeInfoPersistence(
		PaymentFeeInfoPersistence paymentFeeInfoPersistence) {
		this.paymentFeeInfoPersistence = paymentFeeInfoPersistence;
	}

	/**
	 * Returns the service config mapping local service.
	 *
	 * @return the service config mapping local service
	 */
	public opencps.dvcqg.extend.sync.service.ServiceConfigMappingLocalService getServiceConfigMappingLocalService() {
		return serviceConfigMappingLocalService;
	}

	/**
	 * Sets the service config mapping local service.
	 *
	 * @param serviceConfigMappingLocalService the service config mapping local service
	 */
	public void setServiceConfigMappingLocalService(
		opencps.dvcqg.extend.sync.service.ServiceConfigMappingLocalService serviceConfigMappingLocalService) {
		this.serviceConfigMappingLocalService = serviceConfigMappingLocalService;
	}

	/**
	 * Returns the service config mapping persistence.
	 *
	 * @return the service config mapping persistence
	 */
	public ServiceConfigMappingPersistence getServiceConfigMappingPersistence() {
		return serviceConfigMappingPersistence;
	}

	/**
	 * Sets the service config mapping persistence.
	 *
	 * @param serviceConfigMappingPersistence the service config mapping persistence
	 */
	public void setServiceConfigMappingPersistence(
		ServiceConfigMappingPersistence serviceConfigMappingPersistence) {
		this.serviceConfigMappingPersistence = serviceConfigMappingPersistence;
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
		persistedModelLocalServiceRegistry.register("opencps.dvcqg.extend.sync.model.ApplicableInfo",
			applicableInfoLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"opencps.dvcqg.extend.sync.model.ApplicableInfo");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ApplicableInfoLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ApplicableInfo.class;
	}

	protected String getModelClassName() {
		return ApplicableInfo.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = applicableInfoPersistence.getDataSource();

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

	@BeanReference(type = ApplicableInfoLocalService.class)
	protected ApplicableInfoLocalService applicableInfoLocalService;
	@BeanReference(type = ApplicableInfoPersistence.class)
	protected ApplicableInfoPersistence applicableInfoPersistence;
	@BeanReference(type = opencps.dvcqg.extend.sync.service.PaymentFeeInfoLocalService.class)
	protected opencps.dvcqg.extend.sync.service.PaymentFeeInfoLocalService paymentFeeInfoLocalService;
	@BeanReference(type = PaymentFeeInfoPersistence.class)
	protected PaymentFeeInfoPersistence paymentFeeInfoPersistence;
	@BeanReference(type = opencps.dvcqg.extend.sync.service.ServiceConfigMappingLocalService.class)
	protected opencps.dvcqg.extend.sync.service.ServiceConfigMappingLocalService serviceConfigMappingLocalService;
	@BeanReference(type = ServiceConfigMappingPersistence.class)
	protected ServiceConfigMappingPersistence serviceConfigMappingPersistence;
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