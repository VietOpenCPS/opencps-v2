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

package org.opencps.dossiermgt.service.base;

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

import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.PaymentFileLocalService;
import org.opencps.dossiermgt.service.persistence.AccessTokenPersistence;
import org.opencps.dossiermgt.service.persistence.ActionConfigPersistence;
import org.opencps.dossiermgt.service.persistence.ApplicableInfoPersistence;
import org.opencps.dossiermgt.service.persistence.BookingFinder;
import org.opencps.dossiermgt.service.persistence.BookingPersistence;
import org.opencps.dossiermgt.service.persistence.ConfigCounterPersistence;
import org.opencps.dossiermgt.service.persistence.DeliverableFinder;
import org.opencps.dossiermgt.service.persistence.DeliverableLogPersistence;
import org.opencps.dossiermgt.service.persistence.DeliverablePersistence;
import org.opencps.dossiermgt.service.persistence.DeliverableTypePersistence;
import org.opencps.dossiermgt.service.persistence.DeliverableTypeRolePersistence;
import org.opencps.dossiermgt.service.persistence.DocumentTypePersistence;
import org.opencps.dossiermgt.service.persistence.DossierActionFinder;
import org.opencps.dossiermgt.service.persistence.DossierActionPersistence;
import org.opencps.dossiermgt.service.persistence.DossierActionSyncPersistence;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPersistence;
import org.opencps.dossiermgt.service.persistence.DossierDocumentPersistence;
import org.opencps.dossiermgt.service.persistence.DossierFilePersistence;
import org.opencps.dossiermgt.service.persistence.DossierLogPersistence;
import org.opencps.dossiermgt.service.persistence.DossierMarkPersistence;
import org.opencps.dossiermgt.service.persistence.DossierPartPersistence;
import org.opencps.dossiermgt.service.persistence.DossierPersistence;
import org.opencps.dossiermgt.service.persistence.DossierRequestUDPersistence;
import org.opencps.dossiermgt.service.persistence.DossierStatisticPersistence;
import org.opencps.dossiermgt.service.persistence.DossierStatusMappingPersistence;
import org.opencps.dossiermgt.service.persistence.DossierSyncFinder;
import org.opencps.dossiermgt.service.persistence.DossierSyncPersistence;
import org.opencps.dossiermgt.service.persistence.DossierTemplatePersistence;
import org.opencps.dossiermgt.service.persistence.DossierUserPersistence;
import org.opencps.dossiermgt.service.persistence.DynamicReportPersistence;
import org.opencps.dossiermgt.service.persistence.EFormPersistence;
import org.opencps.dossiermgt.service.persistence.MenuConfigPersistence;
import org.opencps.dossiermgt.service.persistence.MenuRolePersistence;
import org.opencps.dossiermgt.service.persistence.PaymentConfigPersistence;
import org.opencps.dossiermgt.service.persistence.PaymentFeeInfoPersistence;
import org.opencps.dossiermgt.service.persistence.PaymentFilePersistence;
import org.opencps.dossiermgt.service.persistence.ProcessActionPersistence;
import org.opencps.dossiermgt.service.persistence.ProcessOptionPersistence;
import org.opencps.dossiermgt.service.persistence.ProcessPluginPersistence;
import org.opencps.dossiermgt.service.persistence.ProcessSequencePersistence;
import org.opencps.dossiermgt.service.persistence.ProcessStepPersistence;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePersistence;
import org.opencps.dossiermgt.service.persistence.PublishQueuePersistence;
import org.opencps.dossiermgt.service.persistence.RegistrationFormPersistence;
import org.opencps.dossiermgt.service.persistence.RegistrationLogPersistence;
import org.opencps.dossiermgt.service.persistence.RegistrationPersistence;
import org.opencps.dossiermgt.service.persistence.RegistrationTemplatesPersistence;
import org.opencps.dossiermgt.service.persistence.ServiceConfigMappingPersistence;
import org.opencps.dossiermgt.service.persistence.ServiceConfigPersistence;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePersistence;
import org.opencps.dossiermgt.service.persistence.ServiceInfoMappingPersistence;
import org.opencps.dossiermgt.service.persistence.ServiceInfoPersistence;
import org.opencps.dossiermgt.service.persistence.ServiceProcessPersistence;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePersistence;
import org.opencps.dossiermgt.service.persistence.StepConfigFinder;
import org.opencps.dossiermgt.service.persistence.StepConfigPersistence;
import org.opencps.dossiermgt.service.persistence.UserInfoLogPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the payment file local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.opencps.dossiermgt.service.impl.PaymentFileLocalServiceImpl}.
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.impl.PaymentFileLocalServiceImpl
 * @see org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class PaymentFileLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements PaymentFileLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil} to access the payment file local service.
	 */

	/**
	 * Adds the payment file to the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentFile the payment file
	 * @return the payment file that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PaymentFile addPaymentFile(PaymentFile paymentFile) {
		paymentFile.setNew(true);

		return paymentFilePersistence.update(paymentFile);
	}

	/**
	 * Creates a new payment file with the primary key. Does not add the payment file to the database.
	 *
	 * @param paymentFileId the primary key for the new payment file
	 * @return the new payment file
	 */
	@Override
	@Transactional(enabled = false)
	public PaymentFile createPaymentFile(long paymentFileId) {
		return paymentFilePersistence.create(paymentFileId);
	}

	/**
	 * Deletes the payment file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentFileId the primary key of the payment file
	 * @return the payment file that was removed
	 * @throws PortalException if a payment file with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PaymentFile deletePaymentFile(long paymentFileId)
		throws PortalException {
		return paymentFilePersistence.remove(paymentFileId);
	}

	/**
	 * Deletes the payment file from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentFile the payment file
	 * @return the payment file that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PaymentFile deletePaymentFile(PaymentFile paymentFile) {
		return paymentFilePersistence.remove(paymentFile);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(PaymentFile.class,
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
		return paymentFilePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return paymentFilePersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return paymentFilePersistence.findWithDynamicQuery(dynamicQuery, start,
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
		return paymentFilePersistence.countWithDynamicQuery(dynamicQuery);
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
		return paymentFilePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public PaymentFile fetchPaymentFile(long paymentFileId) {
		return paymentFilePersistence.fetchByPrimaryKey(paymentFileId);
	}

	/**
	 * Returns the payment file matching the UUID and group.
	 *
	 * @param uuid the payment file's UUID
	 * @param groupId the primary key of the group
	 * @return the matching payment file, or <code>null</code> if a matching payment file could not be found
	 */
	@Override
	public PaymentFile fetchPaymentFileByUuidAndGroupId(String uuid,
		long groupId) {
		return paymentFilePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the payment file with the primary key.
	 *
	 * @param paymentFileId the primary key of the payment file
	 * @return the payment file
	 * @throws PortalException if a payment file with the primary key could not be found
	 */
	@Override
	public PaymentFile getPaymentFile(long paymentFileId)
		throws PortalException {
		return paymentFilePersistence.findByPrimaryKey(paymentFileId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(paymentFileLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PaymentFile.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("paymentFileId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(paymentFileLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PaymentFile.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"paymentFileId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(paymentFileLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PaymentFile.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("paymentFileId");
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

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PaymentFile>() {
				@Override
				public void performAction(PaymentFile paymentFile)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						paymentFile);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(PaymentFile.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return paymentFileLocalService.deletePaymentFile((PaymentFile)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return paymentFilePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the payment files matching the UUID and company.
	 *
	 * @param uuid the UUID of the payment files
	 * @param companyId the primary key of the company
	 * @return the matching payment files, or an empty list if no matches were found
	 */
	@Override
	public List<PaymentFile> getPaymentFilesByUuidAndCompanyId(String uuid,
		long companyId) {
		return paymentFilePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of payment files matching the UUID and company.
	 *
	 * @param uuid the UUID of the payment files
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching payment files, or an empty list if no matches were found
	 */
	@Override
	public List<PaymentFile> getPaymentFilesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<PaymentFile> orderByComparator) {
		return paymentFilePersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the payment file matching the UUID and group.
	 *
	 * @param uuid the payment file's UUID
	 * @param groupId the primary key of the group
	 * @return the matching payment file
	 * @throws PortalException if a matching payment file could not be found
	 */
	@Override
	public PaymentFile getPaymentFileByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return paymentFilePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the payment files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PaymentFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment files
	 * @param end the upper bound of the range of payment files (not inclusive)
	 * @return the range of payment files
	 */
	@Override
	public List<PaymentFile> getPaymentFiles(int start, int end) {
		return paymentFilePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of payment files.
	 *
	 * @return the number of payment files
	 */
	@Override
	public int getPaymentFilesCount() {
		return paymentFilePersistence.countAll();
	}

	/**
	 * Updates the payment file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param paymentFile the payment file
	 * @return the payment file that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PaymentFile updatePaymentFile(PaymentFile paymentFile) {
		return paymentFilePersistence.update(paymentFile);
	}

	/**
	 * Returns the access token local service.
	 *
	 * @return the access token local service
	 */
	public org.opencps.dossiermgt.service.AccessTokenLocalService getAccessTokenLocalService() {
		return accessTokenLocalService;
	}

	/**
	 * Sets the access token local service.
	 *
	 * @param accessTokenLocalService the access token local service
	 */
	public void setAccessTokenLocalService(
		org.opencps.dossiermgt.service.AccessTokenLocalService accessTokenLocalService) {
		this.accessTokenLocalService = accessTokenLocalService;
	}

	/**
	 * Returns the access token persistence.
	 *
	 * @return the access token persistence
	 */
	public AccessTokenPersistence getAccessTokenPersistence() {
		return accessTokenPersistence;
	}

	/**
	 * Sets the access token persistence.
	 *
	 * @param accessTokenPersistence the access token persistence
	 */
	public void setAccessTokenPersistence(
		AccessTokenPersistence accessTokenPersistence) {
		this.accessTokenPersistence = accessTokenPersistence;
	}

	/**
	 * Returns the action config local service.
	 *
	 * @return the action config local service
	 */
	public org.opencps.dossiermgt.service.ActionConfigLocalService getActionConfigLocalService() {
		return actionConfigLocalService;
	}

	/**
	 * Sets the action config local service.
	 *
	 * @param actionConfigLocalService the action config local service
	 */
	public void setActionConfigLocalService(
		org.opencps.dossiermgt.service.ActionConfigLocalService actionConfigLocalService) {
		this.actionConfigLocalService = actionConfigLocalService;
	}

	/**
	 * Returns the action config persistence.
	 *
	 * @return the action config persistence
	 */
	public ActionConfigPersistence getActionConfigPersistence() {
		return actionConfigPersistence;
	}

	/**
	 * Sets the action config persistence.
	 *
	 * @param actionConfigPersistence the action config persistence
	 */
	public void setActionConfigPersistence(
		ActionConfigPersistence actionConfigPersistence) {
		this.actionConfigPersistence = actionConfigPersistence;
	}

	/**
	 * Returns the applicable info local service.
	 *
	 * @return the applicable info local service
	 */
	public org.opencps.dossiermgt.service.ApplicableInfoLocalService getApplicableInfoLocalService() {
		return applicableInfoLocalService;
	}

	/**
	 * Sets the applicable info local service.
	 *
	 * @param applicableInfoLocalService the applicable info local service
	 */
	public void setApplicableInfoLocalService(
		org.opencps.dossiermgt.service.ApplicableInfoLocalService applicableInfoLocalService) {
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
	 * Returns the booking local service.
	 *
	 * @return the booking local service
	 */
	public org.opencps.dossiermgt.service.BookingLocalService getBookingLocalService() {
		return bookingLocalService;
	}

	/**
	 * Sets the booking local service.
	 *
	 * @param bookingLocalService the booking local service
	 */
	public void setBookingLocalService(
		org.opencps.dossiermgt.service.BookingLocalService bookingLocalService) {
		this.bookingLocalService = bookingLocalService;
	}

	/**
	 * Returns the booking persistence.
	 *
	 * @return the booking persistence
	 */
	public BookingPersistence getBookingPersistence() {
		return bookingPersistence;
	}

	/**
	 * Sets the booking persistence.
	 *
	 * @param bookingPersistence the booking persistence
	 */
	public void setBookingPersistence(BookingPersistence bookingPersistence) {
		this.bookingPersistence = bookingPersistence;
	}

	/**
	 * Returns the booking finder.
	 *
	 * @return the booking finder
	 */
	public BookingFinder getBookingFinder() {
		return bookingFinder;
	}

	/**
	 * Sets the booking finder.
	 *
	 * @param bookingFinder the booking finder
	 */
	public void setBookingFinder(BookingFinder bookingFinder) {
		this.bookingFinder = bookingFinder;
	}

	/**
	 * Returns the config counter local service.
	 *
	 * @return the config counter local service
	 */
	public org.opencps.dossiermgt.service.ConfigCounterLocalService getConfigCounterLocalService() {
		return configCounterLocalService;
	}

	/**
	 * Sets the config counter local service.
	 *
	 * @param configCounterLocalService the config counter local service
	 */
	public void setConfigCounterLocalService(
		org.opencps.dossiermgt.service.ConfigCounterLocalService configCounterLocalService) {
		this.configCounterLocalService = configCounterLocalService;
	}

	/**
	 * Returns the config counter persistence.
	 *
	 * @return the config counter persistence
	 */
	public ConfigCounterPersistence getConfigCounterPersistence() {
		return configCounterPersistence;
	}

	/**
	 * Sets the config counter persistence.
	 *
	 * @param configCounterPersistence the config counter persistence
	 */
	public void setConfigCounterPersistence(
		ConfigCounterPersistence configCounterPersistence) {
		this.configCounterPersistence = configCounterPersistence;
	}

	/**
	 * Returns the cps dossier business local service.
	 *
	 * @return the cps dossier business local service
	 */
	public org.opencps.dossiermgt.service.CPSDossierBusinessLocalService getCPSDossierBusinessLocalService() {
		return cpsDossierBusinessLocalService;
	}

	/**
	 * Sets the cps dossier business local service.
	 *
	 * @param cpsDossierBusinessLocalService the cps dossier business local service
	 */
	public void setCPSDossierBusinessLocalService(
		org.opencps.dossiermgt.service.CPSDossierBusinessLocalService cpsDossierBusinessLocalService) {
		this.cpsDossierBusinessLocalService = cpsDossierBusinessLocalService;
	}

	/**
	 * Returns the deliverable local service.
	 *
	 * @return the deliverable local service
	 */
	public org.opencps.dossiermgt.service.DeliverableLocalService getDeliverableLocalService() {
		return deliverableLocalService;
	}

	/**
	 * Sets the deliverable local service.
	 *
	 * @param deliverableLocalService the deliverable local service
	 */
	public void setDeliverableLocalService(
		org.opencps.dossiermgt.service.DeliverableLocalService deliverableLocalService) {
		this.deliverableLocalService = deliverableLocalService;
	}

	/**
	 * Returns the deliverable persistence.
	 *
	 * @return the deliverable persistence
	 */
	public DeliverablePersistence getDeliverablePersistence() {
		return deliverablePersistence;
	}

	/**
	 * Sets the deliverable persistence.
	 *
	 * @param deliverablePersistence the deliverable persistence
	 */
	public void setDeliverablePersistence(
		DeliverablePersistence deliverablePersistence) {
		this.deliverablePersistence = deliverablePersistence;
	}

	/**
	 * Returns the deliverable finder.
	 *
	 * @return the deliverable finder
	 */
	public DeliverableFinder getDeliverableFinder() {
		return deliverableFinder;
	}

	/**
	 * Sets the deliverable finder.
	 *
	 * @param deliverableFinder the deliverable finder
	 */
	public void setDeliverableFinder(DeliverableFinder deliverableFinder) {
		this.deliverableFinder = deliverableFinder;
	}

	/**
	 * Returns the deliverable log local service.
	 *
	 * @return the deliverable log local service
	 */
	public org.opencps.dossiermgt.service.DeliverableLogLocalService getDeliverableLogLocalService() {
		return deliverableLogLocalService;
	}

	/**
	 * Sets the deliverable log local service.
	 *
	 * @param deliverableLogLocalService the deliverable log local service
	 */
	public void setDeliverableLogLocalService(
		org.opencps.dossiermgt.service.DeliverableLogLocalService deliverableLogLocalService) {
		this.deliverableLogLocalService = deliverableLogLocalService;
	}

	/**
	 * Returns the deliverable log persistence.
	 *
	 * @return the deliverable log persistence
	 */
	public DeliverableLogPersistence getDeliverableLogPersistence() {
		return deliverableLogPersistence;
	}

	/**
	 * Sets the deliverable log persistence.
	 *
	 * @param deliverableLogPersistence the deliverable log persistence
	 */
	public void setDeliverableLogPersistence(
		DeliverableLogPersistence deliverableLogPersistence) {
		this.deliverableLogPersistence = deliverableLogPersistence;
	}

	/**
	 * Returns the deliverable type local service.
	 *
	 * @return the deliverable type local service
	 */
	public org.opencps.dossiermgt.service.DeliverableTypeLocalService getDeliverableTypeLocalService() {
		return deliverableTypeLocalService;
	}

	/**
	 * Sets the deliverable type local service.
	 *
	 * @param deliverableTypeLocalService the deliverable type local service
	 */
	public void setDeliverableTypeLocalService(
		org.opencps.dossiermgt.service.DeliverableTypeLocalService deliverableTypeLocalService) {
		this.deliverableTypeLocalService = deliverableTypeLocalService;
	}

	/**
	 * Returns the deliverable type persistence.
	 *
	 * @return the deliverable type persistence
	 */
	public DeliverableTypePersistence getDeliverableTypePersistence() {
		return deliverableTypePersistence;
	}

	/**
	 * Sets the deliverable type persistence.
	 *
	 * @param deliverableTypePersistence the deliverable type persistence
	 */
	public void setDeliverableTypePersistence(
		DeliverableTypePersistence deliverableTypePersistence) {
		this.deliverableTypePersistence = deliverableTypePersistence;
	}

	/**
	 * Returns the deliverable type role local service.
	 *
	 * @return the deliverable type role local service
	 */
	public org.opencps.dossiermgt.service.DeliverableTypeRoleLocalService getDeliverableTypeRoleLocalService() {
		return deliverableTypeRoleLocalService;
	}

	/**
	 * Sets the deliverable type role local service.
	 *
	 * @param deliverableTypeRoleLocalService the deliverable type role local service
	 */
	public void setDeliverableTypeRoleLocalService(
		org.opencps.dossiermgt.service.DeliverableTypeRoleLocalService deliverableTypeRoleLocalService) {
		this.deliverableTypeRoleLocalService = deliverableTypeRoleLocalService;
	}

	/**
	 * Returns the deliverable type role persistence.
	 *
	 * @return the deliverable type role persistence
	 */
	public DeliverableTypeRolePersistence getDeliverableTypeRolePersistence() {
		return deliverableTypeRolePersistence;
	}

	/**
	 * Sets the deliverable type role persistence.
	 *
	 * @param deliverableTypeRolePersistence the deliverable type role persistence
	 */
	public void setDeliverableTypeRolePersistence(
		DeliverableTypeRolePersistence deliverableTypeRolePersistence) {
		this.deliverableTypeRolePersistence = deliverableTypeRolePersistence;
	}

	/**
	 * Returns the document type local service.
	 *
	 * @return the document type local service
	 */
	public org.opencps.dossiermgt.service.DocumentTypeLocalService getDocumentTypeLocalService() {
		return documentTypeLocalService;
	}

	/**
	 * Sets the document type local service.
	 *
	 * @param documentTypeLocalService the document type local service
	 */
	public void setDocumentTypeLocalService(
		org.opencps.dossiermgt.service.DocumentTypeLocalService documentTypeLocalService) {
		this.documentTypeLocalService = documentTypeLocalService;
	}

	/**
	 * Returns the document type persistence.
	 *
	 * @return the document type persistence
	 */
	public DocumentTypePersistence getDocumentTypePersistence() {
		return documentTypePersistence;
	}

	/**
	 * Sets the document type persistence.
	 *
	 * @param documentTypePersistence the document type persistence
	 */
	public void setDocumentTypePersistence(
		DocumentTypePersistence documentTypePersistence) {
		this.documentTypePersistence = documentTypePersistence;
	}

	/**
	 * Returns the dossier local service.
	 *
	 * @return the dossier local service
	 */
	public org.opencps.dossiermgt.service.DossierLocalService getDossierLocalService() {
		return dossierLocalService;
	}

	/**
	 * Sets the dossier local service.
	 *
	 * @param dossierLocalService the dossier local service
	 */
	public void setDossierLocalService(
		org.opencps.dossiermgt.service.DossierLocalService dossierLocalService) {
		this.dossierLocalService = dossierLocalService;
	}

	/**
	 * Returns the dossier persistence.
	 *
	 * @return the dossier persistence
	 */
	public DossierPersistence getDossierPersistence() {
		return dossierPersistence;
	}

	/**
	 * Sets the dossier persistence.
	 *
	 * @param dossierPersistence the dossier persistence
	 */
	public void setDossierPersistence(DossierPersistence dossierPersistence) {
		this.dossierPersistence = dossierPersistence;
	}

	/**
	 * Returns the dossier action local service.
	 *
	 * @return the dossier action local service
	 */
	public org.opencps.dossiermgt.service.DossierActionLocalService getDossierActionLocalService() {
		return dossierActionLocalService;
	}

	/**
	 * Sets the dossier action local service.
	 *
	 * @param dossierActionLocalService the dossier action local service
	 */
	public void setDossierActionLocalService(
		org.opencps.dossiermgt.service.DossierActionLocalService dossierActionLocalService) {
		this.dossierActionLocalService = dossierActionLocalService;
	}

	/**
	 * Returns the dossier action persistence.
	 *
	 * @return the dossier action persistence
	 */
	public DossierActionPersistence getDossierActionPersistence() {
		return dossierActionPersistence;
	}

	/**
	 * Sets the dossier action persistence.
	 *
	 * @param dossierActionPersistence the dossier action persistence
	 */
	public void setDossierActionPersistence(
		DossierActionPersistence dossierActionPersistence) {
		this.dossierActionPersistence = dossierActionPersistence;
	}

	/**
	 * Returns the dossier action finder.
	 *
	 * @return the dossier action finder
	 */
	public DossierActionFinder getDossierActionFinder() {
		return dossierActionFinder;
	}

	/**
	 * Sets the dossier action finder.
	 *
	 * @param dossierActionFinder the dossier action finder
	 */
	public void setDossierActionFinder(DossierActionFinder dossierActionFinder) {
		this.dossierActionFinder = dossierActionFinder;
	}

	/**
	 * Returns the dossier action sync local service.
	 *
	 * @return the dossier action sync local service
	 */
	public org.opencps.dossiermgt.service.DossierActionSyncLocalService getDossierActionSyncLocalService() {
		return dossierActionSyncLocalService;
	}

	/**
	 * Sets the dossier action sync local service.
	 *
	 * @param dossierActionSyncLocalService the dossier action sync local service
	 */
	public void setDossierActionSyncLocalService(
		org.opencps.dossiermgt.service.DossierActionSyncLocalService dossierActionSyncLocalService) {
		this.dossierActionSyncLocalService = dossierActionSyncLocalService;
	}

	/**
	 * Returns the dossier action sync persistence.
	 *
	 * @return the dossier action sync persistence
	 */
	public DossierActionSyncPersistence getDossierActionSyncPersistence() {
		return dossierActionSyncPersistence;
	}

	/**
	 * Sets the dossier action sync persistence.
	 *
	 * @param dossierActionSyncPersistence the dossier action sync persistence
	 */
	public void setDossierActionSyncPersistence(
		DossierActionSyncPersistence dossierActionSyncPersistence) {
		this.dossierActionSyncPersistence = dossierActionSyncPersistence;
	}

	/**
	 * Returns the dossier action user local service.
	 *
	 * @return the dossier action user local service
	 */
	public org.opencps.dossiermgt.service.DossierActionUserLocalService getDossierActionUserLocalService() {
		return dossierActionUserLocalService;
	}

	/**
	 * Sets the dossier action user local service.
	 *
	 * @param dossierActionUserLocalService the dossier action user local service
	 */
	public void setDossierActionUserLocalService(
		org.opencps.dossiermgt.service.DossierActionUserLocalService dossierActionUserLocalService) {
		this.dossierActionUserLocalService = dossierActionUserLocalService;
	}

	/**
	 * Returns the dossier action user persistence.
	 *
	 * @return the dossier action user persistence
	 */
	public DossierActionUserPersistence getDossierActionUserPersistence() {
		return dossierActionUserPersistence;
	}

	/**
	 * Sets the dossier action user persistence.
	 *
	 * @param dossierActionUserPersistence the dossier action user persistence
	 */
	public void setDossierActionUserPersistence(
		DossierActionUserPersistence dossierActionUserPersistence) {
		this.dossierActionUserPersistence = dossierActionUserPersistence;
	}

	/**
	 * Returns the dossier document local service.
	 *
	 * @return the dossier document local service
	 */
	public org.opencps.dossiermgt.service.DossierDocumentLocalService getDossierDocumentLocalService() {
		return dossierDocumentLocalService;
	}

	/**
	 * Sets the dossier document local service.
	 *
	 * @param dossierDocumentLocalService the dossier document local service
	 */
	public void setDossierDocumentLocalService(
		org.opencps.dossiermgt.service.DossierDocumentLocalService dossierDocumentLocalService) {
		this.dossierDocumentLocalService = dossierDocumentLocalService;
	}

	/**
	 * Returns the dossier document persistence.
	 *
	 * @return the dossier document persistence
	 */
	public DossierDocumentPersistence getDossierDocumentPersistence() {
		return dossierDocumentPersistence;
	}

	/**
	 * Sets the dossier document persistence.
	 *
	 * @param dossierDocumentPersistence the dossier document persistence
	 */
	public void setDossierDocumentPersistence(
		DossierDocumentPersistence dossierDocumentPersistence) {
		this.dossierDocumentPersistence = dossierDocumentPersistence;
	}

	/**
	 * Returns the dossier file local service.
	 *
	 * @return the dossier file local service
	 */
	public org.opencps.dossiermgt.service.DossierFileLocalService getDossierFileLocalService() {
		return dossierFileLocalService;
	}

	/**
	 * Sets the dossier file local service.
	 *
	 * @param dossierFileLocalService the dossier file local service
	 */
	public void setDossierFileLocalService(
		org.opencps.dossiermgt.service.DossierFileLocalService dossierFileLocalService) {
		this.dossierFileLocalService = dossierFileLocalService;
	}

	/**
	 * Returns the dossier file persistence.
	 *
	 * @return the dossier file persistence
	 */
	public DossierFilePersistence getDossierFilePersistence() {
		return dossierFilePersistence;
	}

	/**
	 * Sets the dossier file persistence.
	 *
	 * @param dossierFilePersistence the dossier file persistence
	 */
	public void setDossierFilePersistence(
		DossierFilePersistence dossierFilePersistence) {
		this.dossierFilePersistence = dossierFilePersistence;
	}

	/**
	 * Returns the dossier log local service.
	 *
	 * @return the dossier log local service
	 */
	public org.opencps.dossiermgt.service.DossierLogLocalService getDossierLogLocalService() {
		return dossierLogLocalService;
	}

	/**
	 * Sets the dossier log local service.
	 *
	 * @param dossierLogLocalService the dossier log local service
	 */
	public void setDossierLogLocalService(
		org.opencps.dossiermgt.service.DossierLogLocalService dossierLogLocalService) {
		this.dossierLogLocalService = dossierLogLocalService;
	}

	/**
	 * Returns the dossier log persistence.
	 *
	 * @return the dossier log persistence
	 */
	public DossierLogPersistence getDossierLogPersistence() {
		return dossierLogPersistence;
	}

	/**
	 * Sets the dossier log persistence.
	 *
	 * @param dossierLogPersistence the dossier log persistence
	 */
	public void setDossierLogPersistence(
		DossierLogPersistence dossierLogPersistence) {
		this.dossierLogPersistence = dossierLogPersistence;
	}

	/**
	 * Returns the dossier mark local service.
	 *
	 * @return the dossier mark local service
	 */
	public org.opencps.dossiermgt.service.DossierMarkLocalService getDossierMarkLocalService() {
		return dossierMarkLocalService;
	}

	/**
	 * Sets the dossier mark local service.
	 *
	 * @param dossierMarkLocalService the dossier mark local service
	 */
	public void setDossierMarkLocalService(
		org.opencps.dossiermgt.service.DossierMarkLocalService dossierMarkLocalService) {
		this.dossierMarkLocalService = dossierMarkLocalService;
	}

	/**
	 * Returns the dossier mark persistence.
	 *
	 * @return the dossier mark persistence
	 */
	public DossierMarkPersistence getDossierMarkPersistence() {
		return dossierMarkPersistence;
	}

	/**
	 * Sets the dossier mark persistence.
	 *
	 * @param dossierMarkPersistence the dossier mark persistence
	 */
	public void setDossierMarkPersistence(
		DossierMarkPersistence dossierMarkPersistence) {
		this.dossierMarkPersistence = dossierMarkPersistence;
	}

	/**
	 * Returns the dossier part local service.
	 *
	 * @return the dossier part local service
	 */
	public org.opencps.dossiermgt.service.DossierPartLocalService getDossierPartLocalService() {
		return dossierPartLocalService;
	}

	/**
	 * Sets the dossier part local service.
	 *
	 * @param dossierPartLocalService the dossier part local service
	 */
	public void setDossierPartLocalService(
		org.opencps.dossiermgt.service.DossierPartLocalService dossierPartLocalService) {
		this.dossierPartLocalService = dossierPartLocalService;
	}

	/**
	 * Returns the dossier part persistence.
	 *
	 * @return the dossier part persistence
	 */
	public DossierPartPersistence getDossierPartPersistence() {
		return dossierPartPersistence;
	}

	/**
	 * Sets the dossier part persistence.
	 *
	 * @param dossierPartPersistence the dossier part persistence
	 */
	public void setDossierPartPersistence(
		DossierPartPersistence dossierPartPersistence) {
		this.dossierPartPersistence = dossierPartPersistence;
	}

	/**
	 * Returns the dossier request ud local service.
	 *
	 * @return the dossier request ud local service
	 */
	public org.opencps.dossiermgt.service.DossierRequestUDLocalService getDossierRequestUDLocalService() {
		return dossierRequestUDLocalService;
	}

	/**
	 * Sets the dossier request ud local service.
	 *
	 * @param dossierRequestUDLocalService the dossier request ud local service
	 */
	public void setDossierRequestUDLocalService(
		org.opencps.dossiermgt.service.DossierRequestUDLocalService dossierRequestUDLocalService) {
		this.dossierRequestUDLocalService = dossierRequestUDLocalService;
	}

	/**
	 * Returns the dossier request ud persistence.
	 *
	 * @return the dossier request ud persistence
	 */
	public DossierRequestUDPersistence getDossierRequestUDPersistence() {
		return dossierRequestUDPersistence;
	}

	/**
	 * Sets the dossier request ud persistence.
	 *
	 * @param dossierRequestUDPersistence the dossier request ud persistence
	 */
	public void setDossierRequestUDPersistence(
		DossierRequestUDPersistence dossierRequestUDPersistence) {
		this.dossierRequestUDPersistence = dossierRequestUDPersistence;
	}

	/**
	 * Returns the dossier statistic local service.
	 *
	 * @return the dossier statistic local service
	 */
	public org.opencps.dossiermgt.service.DossierStatisticLocalService getDossierStatisticLocalService() {
		return dossierStatisticLocalService;
	}

	/**
	 * Sets the dossier statistic local service.
	 *
	 * @param dossierStatisticLocalService the dossier statistic local service
	 */
	public void setDossierStatisticLocalService(
		org.opencps.dossiermgt.service.DossierStatisticLocalService dossierStatisticLocalService) {
		this.dossierStatisticLocalService = dossierStatisticLocalService;
	}

	/**
	 * Returns the dossier statistic persistence.
	 *
	 * @return the dossier statistic persistence
	 */
	public DossierStatisticPersistence getDossierStatisticPersistence() {
		return dossierStatisticPersistence;
	}

	/**
	 * Sets the dossier statistic persistence.
	 *
	 * @param dossierStatisticPersistence the dossier statistic persistence
	 */
	public void setDossierStatisticPersistence(
		DossierStatisticPersistence dossierStatisticPersistence) {
		this.dossierStatisticPersistence = dossierStatisticPersistence;
	}

	/**
	 * Returns the dossier status mapping local service.
	 *
	 * @return the dossier status mapping local service
	 */
	public org.opencps.dossiermgt.service.DossierStatusMappingLocalService getDossierStatusMappingLocalService() {
		return dossierStatusMappingLocalService;
	}

	/**
	 * Sets the dossier status mapping local service.
	 *
	 * @param dossierStatusMappingLocalService the dossier status mapping local service
	 */
	public void setDossierStatusMappingLocalService(
		org.opencps.dossiermgt.service.DossierStatusMappingLocalService dossierStatusMappingLocalService) {
		this.dossierStatusMappingLocalService = dossierStatusMappingLocalService;
	}

	/**
	 * Returns the dossier status mapping persistence.
	 *
	 * @return the dossier status mapping persistence
	 */
	public DossierStatusMappingPersistence getDossierStatusMappingPersistence() {
		return dossierStatusMappingPersistence;
	}

	/**
	 * Sets the dossier status mapping persistence.
	 *
	 * @param dossierStatusMappingPersistence the dossier status mapping persistence
	 */
	public void setDossierStatusMappingPersistence(
		DossierStatusMappingPersistence dossierStatusMappingPersistence) {
		this.dossierStatusMappingPersistence = dossierStatusMappingPersistence;
	}

	/**
	 * Returns the dossier sync local service.
	 *
	 * @return the dossier sync local service
	 */
	public org.opencps.dossiermgt.service.DossierSyncLocalService getDossierSyncLocalService() {
		return dossierSyncLocalService;
	}

	/**
	 * Sets the dossier sync local service.
	 *
	 * @param dossierSyncLocalService the dossier sync local service
	 */
	public void setDossierSyncLocalService(
		org.opencps.dossiermgt.service.DossierSyncLocalService dossierSyncLocalService) {
		this.dossierSyncLocalService = dossierSyncLocalService;
	}

	/**
	 * Returns the dossier sync persistence.
	 *
	 * @return the dossier sync persistence
	 */
	public DossierSyncPersistence getDossierSyncPersistence() {
		return dossierSyncPersistence;
	}

	/**
	 * Sets the dossier sync persistence.
	 *
	 * @param dossierSyncPersistence the dossier sync persistence
	 */
	public void setDossierSyncPersistence(
		DossierSyncPersistence dossierSyncPersistence) {
		this.dossierSyncPersistence = dossierSyncPersistence;
	}

	/**
	 * Returns the dossier sync finder.
	 *
	 * @return the dossier sync finder
	 */
	public DossierSyncFinder getDossierSyncFinder() {
		return dossierSyncFinder;
	}

	/**
	 * Sets the dossier sync finder.
	 *
	 * @param dossierSyncFinder the dossier sync finder
	 */
	public void setDossierSyncFinder(DossierSyncFinder dossierSyncFinder) {
		this.dossierSyncFinder = dossierSyncFinder;
	}

	/**
	 * Returns the dossier template local service.
	 *
	 * @return the dossier template local service
	 */
	public org.opencps.dossiermgt.service.DossierTemplateLocalService getDossierTemplateLocalService() {
		return dossierTemplateLocalService;
	}

	/**
	 * Sets the dossier template local service.
	 *
	 * @param dossierTemplateLocalService the dossier template local service
	 */
	public void setDossierTemplateLocalService(
		org.opencps.dossiermgt.service.DossierTemplateLocalService dossierTemplateLocalService) {
		this.dossierTemplateLocalService = dossierTemplateLocalService;
	}

	/**
	 * Returns the dossier template persistence.
	 *
	 * @return the dossier template persistence
	 */
	public DossierTemplatePersistence getDossierTemplatePersistence() {
		return dossierTemplatePersistence;
	}

	/**
	 * Sets the dossier template persistence.
	 *
	 * @param dossierTemplatePersistence the dossier template persistence
	 */
	public void setDossierTemplatePersistence(
		DossierTemplatePersistence dossierTemplatePersistence) {
		this.dossierTemplatePersistence = dossierTemplatePersistence;
	}

	/**
	 * Returns the dossier user local service.
	 *
	 * @return the dossier user local service
	 */
	public org.opencps.dossiermgt.service.DossierUserLocalService getDossierUserLocalService() {
		return dossierUserLocalService;
	}

	/**
	 * Sets the dossier user local service.
	 *
	 * @param dossierUserLocalService the dossier user local service
	 */
	public void setDossierUserLocalService(
		org.opencps.dossiermgt.service.DossierUserLocalService dossierUserLocalService) {
		this.dossierUserLocalService = dossierUserLocalService;
	}

	/**
	 * Returns the dossier user persistence.
	 *
	 * @return the dossier user persistence
	 */
	public DossierUserPersistence getDossierUserPersistence() {
		return dossierUserPersistence;
	}

	/**
	 * Sets the dossier user persistence.
	 *
	 * @param dossierUserPersistence the dossier user persistence
	 */
	public void setDossierUserPersistence(
		DossierUserPersistence dossierUserPersistence) {
		this.dossierUserPersistence = dossierUserPersistence;
	}

	/**
	 * Returns the dynamic report local service.
	 *
	 * @return the dynamic report local service
	 */
	public org.opencps.dossiermgt.service.DynamicReportLocalService getDynamicReportLocalService() {
		return dynamicReportLocalService;
	}

	/**
	 * Sets the dynamic report local service.
	 *
	 * @param dynamicReportLocalService the dynamic report local service
	 */
	public void setDynamicReportLocalService(
		org.opencps.dossiermgt.service.DynamicReportLocalService dynamicReportLocalService) {
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
	 * Returns the e form local service.
	 *
	 * @return the e form local service
	 */
	public org.opencps.dossiermgt.service.EFormLocalService getEFormLocalService() {
		return eFormLocalService;
	}

	/**
	 * Sets the e form local service.
	 *
	 * @param eFormLocalService the e form local service
	 */
	public void setEFormLocalService(
		org.opencps.dossiermgt.service.EFormLocalService eFormLocalService) {
		this.eFormLocalService = eFormLocalService;
	}

	/**
	 * Returns the e form persistence.
	 *
	 * @return the e form persistence
	 */
	public EFormPersistence getEFormPersistence() {
		return eFormPersistence;
	}

	/**
	 * Sets the e form persistence.
	 *
	 * @param eFormPersistence the e form persistence
	 */
	public void setEFormPersistence(EFormPersistence eFormPersistence) {
		this.eFormPersistence = eFormPersistence;
	}

	/**
	 * Returns the menu config local service.
	 *
	 * @return the menu config local service
	 */
	public org.opencps.dossiermgt.service.MenuConfigLocalService getMenuConfigLocalService() {
		return menuConfigLocalService;
	}

	/**
	 * Sets the menu config local service.
	 *
	 * @param menuConfigLocalService the menu config local service
	 */
	public void setMenuConfigLocalService(
		org.opencps.dossiermgt.service.MenuConfigLocalService menuConfigLocalService) {
		this.menuConfigLocalService = menuConfigLocalService;
	}

	/**
	 * Returns the menu config persistence.
	 *
	 * @return the menu config persistence
	 */
	public MenuConfigPersistence getMenuConfigPersistence() {
		return menuConfigPersistence;
	}

	/**
	 * Sets the menu config persistence.
	 *
	 * @param menuConfigPersistence the menu config persistence
	 */
	public void setMenuConfigPersistence(
		MenuConfigPersistence menuConfigPersistence) {
		this.menuConfigPersistence = menuConfigPersistence;
	}

	/**
	 * Returns the menu role local service.
	 *
	 * @return the menu role local service
	 */
	public org.opencps.dossiermgt.service.MenuRoleLocalService getMenuRoleLocalService() {
		return menuRoleLocalService;
	}

	/**
	 * Sets the menu role local service.
	 *
	 * @param menuRoleLocalService the menu role local service
	 */
	public void setMenuRoleLocalService(
		org.opencps.dossiermgt.service.MenuRoleLocalService menuRoleLocalService) {
		this.menuRoleLocalService = menuRoleLocalService;
	}

	/**
	 * Returns the menu role persistence.
	 *
	 * @return the menu role persistence
	 */
	public MenuRolePersistence getMenuRolePersistence() {
		return menuRolePersistence;
	}

	/**
	 * Sets the menu role persistence.
	 *
	 * @param menuRolePersistence the menu role persistence
	 */
	public void setMenuRolePersistence(MenuRolePersistence menuRolePersistence) {
		this.menuRolePersistence = menuRolePersistence;
	}

	/**
	 * Returns the payment config local service.
	 *
	 * @return the payment config local service
	 */
	public org.opencps.dossiermgt.service.PaymentConfigLocalService getPaymentConfigLocalService() {
		return paymentConfigLocalService;
	}

	/**
	 * Sets the payment config local service.
	 *
	 * @param paymentConfigLocalService the payment config local service
	 */
	public void setPaymentConfigLocalService(
		org.opencps.dossiermgt.service.PaymentConfigLocalService paymentConfigLocalService) {
		this.paymentConfigLocalService = paymentConfigLocalService;
	}

	/**
	 * Returns the payment config persistence.
	 *
	 * @return the payment config persistence
	 */
	public PaymentConfigPersistence getPaymentConfigPersistence() {
		return paymentConfigPersistence;
	}

	/**
	 * Sets the payment config persistence.
	 *
	 * @param paymentConfigPersistence the payment config persistence
	 */
	public void setPaymentConfigPersistence(
		PaymentConfigPersistence paymentConfigPersistence) {
		this.paymentConfigPersistence = paymentConfigPersistence;
	}

	/**
	 * Returns the payment fee info local service.
	 *
	 * @return the payment fee info local service
	 */
	public org.opencps.dossiermgt.service.PaymentFeeInfoLocalService getPaymentFeeInfoLocalService() {
		return paymentFeeInfoLocalService;
	}

	/**
	 * Sets the payment fee info local service.
	 *
	 * @param paymentFeeInfoLocalService the payment fee info local service
	 */
	public void setPaymentFeeInfoLocalService(
		org.opencps.dossiermgt.service.PaymentFeeInfoLocalService paymentFeeInfoLocalService) {
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
	 * Returns the payment file local service.
	 *
	 * @return the payment file local service
	 */
	public PaymentFileLocalService getPaymentFileLocalService() {
		return paymentFileLocalService;
	}

	/**
	 * Sets the payment file local service.
	 *
	 * @param paymentFileLocalService the payment file local service
	 */
	public void setPaymentFileLocalService(
		PaymentFileLocalService paymentFileLocalService) {
		this.paymentFileLocalService = paymentFileLocalService;
	}

	/**
	 * Returns the payment file persistence.
	 *
	 * @return the payment file persistence
	 */
	public PaymentFilePersistence getPaymentFilePersistence() {
		return paymentFilePersistence;
	}

	/**
	 * Sets the payment file persistence.
	 *
	 * @param paymentFilePersistence the payment file persistence
	 */
	public void setPaymentFilePersistence(
		PaymentFilePersistence paymentFilePersistence) {
		this.paymentFilePersistence = paymentFilePersistence;
	}

	/**
	 * Returns the process action local service.
	 *
	 * @return the process action local service
	 */
	public org.opencps.dossiermgt.service.ProcessActionLocalService getProcessActionLocalService() {
		return processActionLocalService;
	}

	/**
	 * Sets the process action local service.
	 *
	 * @param processActionLocalService the process action local service
	 */
	public void setProcessActionLocalService(
		org.opencps.dossiermgt.service.ProcessActionLocalService processActionLocalService) {
		this.processActionLocalService = processActionLocalService;
	}

	/**
	 * Returns the process action persistence.
	 *
	 * @return the process action persistence
	 */
	public ProcessActionPersistence getProcessActionPersistence() {
		return processActionPersistence;
	}

	/**
	 * Sets the process action persistence.
	 *
	 * @param processActionPersistence the process action persistence
	 */
	public void setProcessActionPersistence(
		ProcessActionPersistence processActionPersistence) {
		this.processActionPersistence = processActionPersistence;
	}

	/**
	 * Returns the process option local service.
	 *
	 * @return the process option local service
	 */
	public org.opencps.dossiermgt.service.ProcessOptionLocalService getProcessOptionLocalService() {
		return processOptionLocalService;
	}

	/**
	 * Sets the process option local service.
	 *
	 * @param processOptionLocalService the process option local service
	 */
	public void setProcessOptionLocalService(
		org.opencps.dossiermgt.service.ProcessOptionLocalService processOptionLocalService) {
		this.processOptionLocalService = processOptionLocalService;
	}

	/**
	 * Returns the process option persistence.
	 *
	 * @return the process option persistence
	 */
	public ProcessOptionPersistence getProcessOptionPersistence() {
		return processOptionPersistence;
	}

	/**
	 * Sets the process option persistence.
	 *
	 * @param processOptionPersistence the process option persistence
	 */
	public void setProcessOptionPersistence(
		ProcessOptionPersistence processOptionPersistence) {
		this.processOptionPersistence = processOptionPersistence;
	}

	/**
	 * Returns the process plugin local service.
	 *
	 * @return the process plugin local service
	 */
	public org.opencps.dossiermgt.service.ProcessPluginLocalService getProcessPluginLocalService() {
		return processPluginLocalService;
	}

	/**
	 * Sets the process plugin local service.
	 *
	 * @param processPluginLocalService the process plugin local service
	 */
	public void setProcessPluginLocalService(
		org.opencps.dossiermgt.service.ProcessPluginLocalService processPluginLocalService) {
		this.processPluginLocalService = processPluginLocalService;
	}

	/**
	 * Returns the process plugin persistence.
	 *
	 * @return the process plugin persistence
	 */
	public ProcessPluginPersistence getProcessPluginPersistence() {
		return processPluginPersistence;
	}

	/**
	 * Sets the process plugin persistence.
	 *
	 * @param processPluginPersistence the process plugin persistence
	 */
	public void setProcessPluginPersistence(
		ProcessPluginPersistence processPluginPersistence) {
		this.processPluginPersistence = processPluginPersistence;
	}

	/**
	 * Returns the process sequence local service.
	 *
	 * @return the process sequence local service
	 */
	public org.opencps.dossiermgt.service.ProcessSequenceLocalService getProcessSequenceLocalService() {
		return processSequenceLocalService;
	}

	/**
	 * Sets the process sequence local service.
	 *
	 * @param processSequenceLocalService the process sequence local service
	 */
	public void setProcessSequenceLocalService(
		org.opencps.dossiermgt.service.ProcessSequenceLocalService processSequenceLocalService) {
		this.processSequenceLocalService = processSequenceLocalService;
	}

	/**
	 * Returns the process sequence persistence.
	 *
	 * @return the process sequence persistence
	 */
	public ProcessSequencePersistence getProcessSequencePersistence() {
		return processSequencePersistence;
	}

	/**
	 * Sets the process sequence persistence.
	 *
	 * @param processSequencePersistence the process sequence persistence
	 */
	public void setProcessSequencePersistence(
		ProcessSequencePersistence processSequencePersistence) {
		this.processSequencePersistence = processSequencePersistence;
	}

	/**
	 * Returns the process step local service.
	 *
	 * @return the process step local service
	 */
	public org.opencps.dossiermgt.service.ProcessStepLocalService getProcessStepLocalService() {
		return processStepLocalService;
	}

	/**
	 * Sets the process step local service.
	 *
	 * @param processStepLocalService the process step local service
	 */
	public void setProcessStepLocalService(
		org.opencps.dossiermgt.service.ProcessStepLocalService processStepLocalService) {
		this.processStepLocalService = processStepLocalService;
	}

	/**
	 * Returns the process step persistence.
	 *
	 * @return the process step persistence
	 */
	public ProcessStepPersistence getProcessStepPersistence() {
		return processStepPersistence;
	}

	/**
	 * Sets the process step persistence.
	 *
	 * @param processStepPersistence the process step persistence
	 */
	public void setProcessStepPersistence(
		ProcessStepPersistence processStepPersistence) {
		this.processStepPersistence = processStepPersistence;
	}

	/**
	 * Returns the process step role local service.
	 *
	 * @return the process step role local service
	 */
	public org.opencps.dossiermgt.service.ProcessStepRoleLocalService getProcessStepRoleLocalService() {
		return processStepRoleLocalService;
	}

	/**
	 * Sets the process step role local service.
	 *
	 * @param processStepRoleLocalService the process step role local service
	 */
	public void setProcessStepRoleLocalService(
		org.opencps.dossiermgt.service.ProcessStepRoleLocalService processStepRoleLocalService) {
		this.processStepRoleLocalService = processStepRoleLocalService;
	}

	/**
	 * Returns the process step role persistence.
	 *
	 * @return the process step role persistence
	 */
	public ProcessStepRolePersistence getProcessStepRolePersistence() {
		return processStepRolePersistence;
	}

	/**
	 * Sets the process step role persistence.
	 *
	 * @param processStepRolePersistence the process step role persistence
	 */
	public void setProcessStepRolePersistence(
		ProcessStepRolePersistence processStepRolePersistence) {
		this.processStepRolePersistence = processStepRolePersistence;
	}

	/**
	 * Returns the publish queue local service.
	 *
	 * @return the publish queue local service
	 */
	public org.opencps.dossiermgt.service.PublishQueueLocalService getPublishQueueLocalService() {
		return publishQueueLocalService;
	}

	/**
	 * Sets the publish queue local service.
	 *
	 * @param publishQueueLocalService the publish queue local service
	 */
	public void setPublishQueueLocalService(
		org.opencps.dossiermgt.service.PublishQueueLocalService publishQueueLocalService) {
		this.publishQueueLocalService = publishQueueLocalService;
	}

	/**
	 * Returns the publish queue persistence.
	 *
	 * @return the publish queue persistence
	 */
	public PublishQueuePersistence getPublishQueuePersistence() {
		return publishQueuePersistence;
	}

	/**
	 * Sets the publish queue persistence.
	 *
	 * @param publishQueuePersistence the publish queue persistence
	 */
	public void setPublishQueuePersistence(
		PublishQueuePersistence publishQueuePersistence) {
		this.publishQueuePersistence = publishQueuePersistence;
	}

	/**
	 * Returns the registration local service.
	 *
	 * @return the registration local service
	 */
	public org.opencps.dossiermgt.service.RegistrationLocalService getRegistrationLocalService() {
		return registrationLocalService;
	}

	/**
	 * Sets the registration local service.
	 *
	 * @param registrationLocalService the registration local service
	 */
	public void setRegistrationLocalService(
		org.opencps.dossiermgt.service.RegistrationLocalService registrationLocalService) {
		this.registrationLocalService = registrationLocalService;
	}

	/**
	 * Returns the registration persistence.
	 *
	 * @return the registration persistence
	 */
	public RegistrationPersistence getRegistrationPersistence() {
		return registrationPersistence;
	}

	/**
	 * Sets the registration persistence.
	 *
	 * @param registrationPersistence the registration persistence
	 */
	public void setRegistrationPersistence(
		RegistrationPersistence registrationPersistence) {
		this.registrationPersistence = registrationPersistence;
	}

	/**
	 * Returns the registration form local service.
	 *
	 * @return the registration form local service
	 */
	public org.opencps.dossiermgt.service.RegistrationFormLocalService getRegistrationFormLocalService() {
		return registrationFormLocalService;
	}

	/**
	 * Sets the registration form local service.
	 *
	 * @param registrationFormLocalService the registration form local service
	 */
	public void setRegistrationFormLocalService(
		org.opencps.dossiermgt.service.RegistrationFormLocalService registrationFormLocalService) {
		this.registrationFormLocalService = registrationFormLocalService;
	}

	/**
	 * Returns the registration form persistence.
	 *
	 * @return the registration form persistence
	 */
	public RegistrationFormPersistence getRegistrationFormPersistence() {
		return registrationFormPersistence;
	}

	/**
	 * Sets the registration form persistence.
	 *
	 * @param registrationFormPersistence the registration form persistence
	 */
	public void setRegistrationFormPersistence(
		RegistrationFormPersistence registrationFormPersistence) {
		this.registrationFormPersistence = registrationFormPersistence;
	}

	/**
	 * Returns the registration log local service.
	 *
	 * @return the registration log local service
	 */
	public org.opencps.dossiermgt.service.RegistrationLogLocalService getRegistrationLogLocalService() {
		return registrationLogLocalService;
	}

	/**
	 * Sets the registration log local service.
	 *
	 * @param registrationLogLocalService the registration log local service
	 */
	public void setRegistrationLogLocalService(
		org.opencps.dossiermgt.service.RegistrationLogLocalService registrationLogLocalService) {
		this.registrationLogLocalService = registrationLogLocalService;
	}

	/**
	 * Returns the registration log persistence.
	 *
	 * @return the registration log persistence
	 */
	public RegistrationLogPersistence getRegistrationLogPersistence() {
		return registrationLogPersistence;
	}

	/**
	 * Sets the registration log persistence.
	 *
	 * @param registrationLogPersistence the registration log persistence
	 */
	public void setRegistrationLogPersistence(
		RegistrationLogPersistence registrationLogPersistence) {
		this.registrationLogPersistence = registrationLogPersistence;
	}

	/**
	 * Returns the registration templates local service.
	 *
	 * @return the registration templates local service
	 */
	public org.opencps.dossiermgt.service.RegistrationTemplatesLocalService getRegistrationTemplatesLocalService() {
		return registrationTemplatesLocalService;
	}

	/**
	 * Sets the registration templates local service.
	 *
	 * @param registrationTemplatesLocalService the registration templates local service
	 */
	public void setRegistrationTemplatesLocalService(
		org.opencps.dossiermgt.service.RegistrationTemplatesLocalService registrationTemplatesLocalService) {
		this.registrationTemplatesLocalService = registrationTemplatesLocalService;
	}

	/**
	 * Returns the registration templates persistence.
	 *
	 * @return the registration templates persistence
	 */
	public RegistrationTemplatesPersistence getRegistrationTemplatesPersistence() {
		return registrationTemplatesPersistence;
	}

	/**
	 * Sets the registration templates persistence.
	 *
	 * @param registrationTemplatesPersistence the registration templates persistence
	 */
	public void setRegistrationTemplatesPersistence(
		RegistrationTemplatesPersistence registrationTemplatesPersistence) {
		this.registrationTemplatesPersistence = registrationTemplatesPersistence;
	}

	/**
	 * Returns the service config local service.
	 *
	 * @return the service config local service
	 */
	public org.opencps.dossiermgt.service.ServiceConfigLocalService getServiceConfigLocalService() {
		return serviceConfigLocalService;
	}

	/**
	 * Sets the service config local service.
	 *
	 * @param serviceConfigLocalService the service config local service
	 */
	public void setServiceConfigLocalService(
		org.opencps.dossiermgt.service.ServiceConfigLocalService serviceConfigLocalService) {
		this.serviceConfigLocalService = serviceConfigLocalService;
	}

	/**
	 * Returns the service config persistence.
	 *
	 * @return the service config persistence
	 */
	public ServiceConfigPersistence getServiceConfigPersistence() {
		return serviceConfigPersistence;
	}

	/**
	 * Sets the service config persistence.
	 *
	 * @param serviceConfigPersistence the service config persistence
	 */
	public void setServiceConfigPersistence(
		ServiceConfigPersistence serviceConfigPersistence) {
		this.serviceConfigPersistence = serviceConfigPersistence;
	}

	/**
	 * Returns the service config mapping local service.
	 *
	 * @return the service config mapping local service
	 */
	public org.opencps.dossiermgt.service.ServiceConfigMappingLocalService getServiceConfigMappingLocalService() {
		return serviceConfigMappingLocalService;
	}

	/**
	 * Sets the service config mapping local service.
	 *
	 * @param serviceConfigMappingLocalService the service config mapping local service
	 */
	public void setServiceConfigMappingLocalService(
		org.opencps.dossiermgt.service.ServiceConfigMappingLocalService serviceConfigMappingLocalService) {
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
	 * Returns the service file template local service.
	 *
	 * @return the service file template local service
	 */
	public org.opencps.dossiermgt.service.ServiceFileTemplateLocalService getServiceFileTemplateLocalService() {
		return serviceFileTemplateLocalService;
	}

	/**
	 * Sets the service file template local service.
	 *
	 * @param serviceFileTemplateLocalService the service file template local service
	 */
	public void setServiceFileTemplateLocalService(
		org.opencps.dossiermgt.service.ServiceFileTemplateLocalService serviceFileTemplateLocalService) {
		this.serviceFileTemplateLocalService = serviceFileTemplateLocalService;
	}

	/**
	 * Returns the service file template persistence.
	 *
	 * @return the service file template persistence
	 */
	public ServiceFileTemplatePersistence getServiceFileTemplatePersistence() {
		return serviceFileTemplatePersistence;
	}

	/**
	 * Sets the service file template persistence.
	 *
	 * @param serviceFileTemplatePersistence the service file template persistence
	 */
	public void setServiceFileTemplatePersistence(
		ServiceFileTemplatePersistence serviceFileTemplatePersistence) {
		this.serviceFileTemplatePersistence = serviceFileTemplatePersistence;
	}

	/**
	 * Returns the service info local service.
	 *
	 * @return the service info local service
	 */
	public org.opencps.dossiermgt.service.ServiceInfoLocalService getServiceInfoLocalService() {
		return serviceInfoLocalService;
	}

	/**
	 * Sets the service info local service.
	 *
	 * @param serviceInfoLocalService the service info local service
	 */
	public void setServiceInfoLocalService(
		org.opencps.dossiermgt.service.ServiceInfoLocalService serviceInfoLocalService) {
		this.serviceInfoLocalService = serviceInfoLocalService;
	}

	/**
	 * Returns the service info persistence.
	 *
	 * @return the service info persistence
	 */
	public ServiceInfoPersistence getServiceInfoPersistence() {
		return serviceInfoPersistence;
	}

	/**
	 * Sets the service info persistence.
	 *
	 * @param serviceInfoPersistence the service info persistence
	 */
	public void setServiceInfoPersistence(
		ServiceInfoPersistence serviceInfoPersistence) {
		this.serviceInfoPersistence = serviceInfoPersistence;
	}

	/**
	 * Returns the service info mapping local service.
	 *
	 * @return the service info mapping local service
	 */
	public org.opencps.dossiermgt.service.ServiceInfoMappingLocalService getServiceInfoMappingLocalService() {
		return serviceInfoMappingLocalService;
	}

	/**
	 * Sets the service info mapping local service.
	 *
	 * @param serviceInfoMappingLocalService the service info mapping local service
	 */
	public void setServiceInfoMappingLocalService(
		org.opencps.dossiermgt.service.ServiceInfoMappingLocalService serviceInfoMappingLocalService) {
		this.serviceInfoMappingLocalService = serviceInfoMappingLocalService;
	}

	/**
	 * Returns the service info mapping persistence.
	 *
	 * @return the service info mapping persistence
	 */
	public ServiceInfoMappingPersistence getServiceInfoMappingPersistence() {
		return serviceInfoMappingPersistence;
	}

	/**
	 * Sets the service info mapping persistence.
	 *
	 * @param serviceInfoMappingPersistence the service info mapping persistence
	 */
	public void setServiceInfoMappingPersistence(
		ServiceInfoMappingPersistence serviceInfoMappingPersistence) {
		this.serviceInfoMappingPersistence = serviceInfoMappingPersistence;
	}

	/**
	 * Returns the service process local service.
	 *
	 * @return the service process local service
	 */
	public org.opencps.dossiermgt.service.ServiceProcessLocalService getServiceProcessLocalService() {
		return serviceProcessLocalService;
	}

	/**
	 * Sets the service process local service.
	 *
	 * @param serviceProcessLocalService the service process local service
	 */
	public void setServiceProcessLocalService(
		org.opencps.dossiermgt.service.ServiceProcessLocalService serviceProcessLocalService) {
		this.serviceProcessLocalService = serviceProcessLocalService;
	}

	/**
	 * Returns the service process persistence.
	 *
	 * @return the service process persistence
	 */
	public ServiceProcessPersistence getServiceProcessPersistence() {
		return serviceProcessPersistence;
	}

	/**
	 * Sets the service process persistence.
	 *
	 * @param serviceProcessPersistence the service process persistence
	 */
	public void setServiceProcessPersistence(
		ServiceProcessPersistence serviceProcessPersistence) {
		this.serviceProcessPersistence = serviceProcessPersistence;
	}

	/**
	 * Returns the service process role local service.
	 *
	 * @return the service process role local service
	 */
	public org.opencps.dossiermgt.service.ServiceProcessRoleLocalService getServiceProcessRoleLocalService() {
		return serviceProcessRoleLocalService;
	}

	/**
	 * Sets the service process role local service.
	 *
	 * @param serviceProcessRoleLocalService the service process role local service
	 */
	public void setServiceProcessRoleLocalService(
		org.opencps.dossiermgt.service.ServiceProcessRoleLocalService serviceProcessRoleLocalService) {
		this.serviceProcessRoleLocalService = serviceProcessRoleLocalService;
	}

	/**
	 * Returns the service process role persistence.
	 *
	 * @return the service process role persistence
	 */
	public ServiceProcessRolePersistence getServiceProcessRolePersistence() {
		return serviceProcessRolePersistence;
	}

	/**
	 * Sets the service process role persistence.
	 *
	 * @param serviceProcessRolePersistence the service process role persistence
	 */
	public void setServiceProcessRolePersistence(
		ServiceProcessRolePersistence serviceProcessRolePersistence) {
		this.serviceProcessRolePersistence = serviceProcessRolePersistence;
	}

	/**
	 * Returns the step config local service.
	 *
	 * @return the step config local service
	 */
	public org.opencps.dossiermgt.service.StepConfigLocalService getStepConfigLocalService() {
		return stepConfigLocalService;
	}

	/**
	 * Sets the step config local service.
	 *
	 * @param stepConfigLocalService the step config local service
	 */
	public void setStepConfigLocalService(
		org.opencps.dossiermgt.service.StepConfigLocalService stepConfigLocalService) {
		this.stepConfigLocalService = stepConfigLocalService;
	}

	/**
	 * Returns the step config persistence.
	 *
	 * @return the step config persistence
	 */
	public StepConfigPersistence getStepConfigPersistence() {
		return stepConfigPersistence;
	}

	/**
	 * Sets the step config persistence.
	 *
	 * @param stepConfigPersistence the step config persistence
	 */
	public void setStepConfigPersistence(
		StepConfigPersistence stepConfigPersistence) {
		this.stepConfigPersistence = stepConfigPersistence;
	}

	/**
	 * Returns the step config finder.
	 *
	 * @return the step config finder
	 */
	public StepConfigFinder getStepConfigFinder() {
		return stepConfigFinder;
	}

	/**
	 * Sets the step config finder.
	 *
	 * @param stepConfigFinder the step config finder
	 */
	public void setStepConfigFinder(StepConfigFinder stepConfigFinder) {
		this.stepConfigFinder = stepConfigFinder;
	}

	/**
	 * Returns the user info log local service.
	 *
	 * @return the user info log local service
	 */
	public org.opencps.dossiermgt.service.UserInfoLogLocalService getUserInfoLogLocalService() {
		return userInfoLogLocalService;
	}

	/**
	 * Sets the user info log local service.
	 *
	 * @param userInfoLogLocalService the user info log local service
	 */
	public void setUserInfoLogLocalService(
		org.opencps.dossiermgt.service.UserInfoLogLocalService userInfoLogLocalService) {
		this.userInfoLogLocalService = userInfoLogLocalService;
	}

	/**
	 * Returns the user info log persistence.
	 *
	 * @return the user info log persistence
	 */
	public UserInfoLogPersistence getUserInfoLogPersistence() {
		return userInfoLogPersistence;
	}

	/**
	 * Sets the user info log persistence.
	 *
	 * @param userInfoLogPersistence the user info log persistence
	 */
	public void setUserInfoLogPersistence(
		UserInfoLogPersistence userInfoLogPersistence) {
		this.userInfoLogPersistence = userInfoLogPersistence;
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
		persistedModelLocalServiceRegistry.register("org.opencps.dossiermgt.model.PaymentFile",
			paymentFileLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"org.opencps.dossiermgt.model.PaymentFile");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PaymentFileLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PaymentFile.class;
	}

	protected String getModelClassName() {
		return PaymentFile.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = paymentFilePersistence.getDataSource();

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

	@BeanReference(type = org.opencps.dossiermgt.service.AccessTokenLocalService.class)
	protected org.opencps.dossiermgt.service.AccessTokenLocalService accessTokenLocalService;
	@BeanReference(type = AccessTokenPersistence.class)
	protected AccessTokenPersistence accessTokenPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ActionConfigLocalService.class)
	protected org.opencps.dossiermgt.service.ActionConfigLocalService actionConfigLocalService;
	@BeanReference(type = ActionConfigPersistence.class)
	protected ActionConfigPersistence actionConfigPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ApplicableInfoLocalService.class)
	protected org.opencps.dossiermgt.service.ApplicableInfoLocalService applicableInfoLocalService;
	@BeanReference(type = ApplicableInfoPersistence.class)
	protected ApplicableInfoPersistence applicableInfoPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.BookingLocalService.class)
	protected org.opencps.dossiermgt.service.BookingLocalService bookingLocalService;
	@BeanReference(type = BookingPersistence.class)
	protected BookingPersistence bookingPersistence;
	@BeanReference(type = BookingFinder.class)
	protected BookingFinder bookingFinder;
	@BeanReference(type = org.opencps.dossiermgt.service.ConfigCounterLocalService.class)
	protected org.opencps.dossiermgt.service.ConfigCounterLocalService configCounterLocalService;
	@BeanReference(type = ConfigCounterPersistence.class)
	protected ConfigCounterPersistence configCounterPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.CPSDossierBusinessLocalService.class)
	protected org.opencps.dossiermgt.service.CPSDossierBusinessLocalService cpsDossierBusinessLocalService;
	@BeanReference(type = org.opencps.dossiermgt.service.DeliverableLocalService.class)
	protected org.opencps.dossiermgt.service.DeliverableLocalService deliverableLocalService;
	@BeanReference(type = DeliverablePersistence.class)
	protected DeliverablePersistence deliverablePersistence;
	@BeanReference(type = DeliverableFinder.class)
	protected DeliverableFinder deliverableFinder;
	@BeanReference(type = org.opencps.dossiermgt.service.DeliverableLogLocalService.class)
	protected org.opencps.dossiermgt.service.DeliverableLogLocalService deliverableLogLocalService;
	@BeanReference(type = DeliverableLogPersistence.class)
	protected DeliverableLogPersistence deliverableLogPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DeliverableTypeLocalService.class)
	protected org.opencps.dossiermgt.service.DeliverableTypeLocalService deliverableTypeLocalService;
	@BeanReference(type = DeliverableTypePersistence.class)
	protected DeliverableTypePersistence deliverableTypePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DeliverableTypeRoleLocalService.class)
	protected org.opencps.dossiermgt.service.DeliverableTypeRoleLocalService deliverableTypeRoleLocalService;
	@BeanReference(type = DeliverableTypeRolePersistence.class)
	protected DeliverableTypeRolePersistence deliverableTypeRolePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DocumentTypeLocalService.class)
	protected org.opencps.dossiermgt.service.DocumentTypeLocalService documentTypeLocalService;
	@BeanReference(type = DocumentTypePersistence.class)
	protected DocumentTypePersistence documentTypePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierLocalService.class)
	protected org.opencps.dossiermgt.service.DossierLocalService dossierLocalService;
	@BeanReference(type = DossierPersistence.class)
	protected DossierPersistence dossierPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierActionLocalService.class)
	protected org.opencps.dossiermgt.service.DossierActionLocalService dossierActionLocalService;
	@BeanReference(type = DossierActionPersistence.class)
	protected DossierActionPersistence dossierActionPersistence;
	@BeanReference(type = DossierActionFinder.class)
	protected DossierActionFinder dossierActionFinder;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierActionSyncLocalService.class)
	protected org.opencps.dossiermgt.service.DossierActionSyncLocalService dossierActionSyncLocalService;
	@BeanReference(type = DossierActionSyncPersistence.class)
	protected DossierActionSyncPersistence dossierActionSyncPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierActionUserLocalService.class)
	protected org.opencps.dossiermgt.service.DossierActionUserLocalService dossierActionUserLocalService;
	@BeanReference(type = DossierActionUserPersistence.class)
	protected DossierActionUserPersistence dossierActionUserPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierDocumentLocalService.class)
	protected org.opencps.dossiermgt.service.DossierDocumentLocalService dossierDocumentLocalService;
	@BeanReference(type = DossierDocumentPersistence.class)
	protected DossierDocumentPersistence dossierDocumentPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierFileLocalService.class)
	protected org.opencps.dossiermgt.service.DossierFileLocalService dossierFileLocalService;
	@BeanReference(type = DossierFilePersistence.class)
	protected DossierFilePersistence dossierFilePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierLogLocalService.class)
	protected org.opencps.dossiermgt.service.DossierLogLocalService dossierLogLocalService;
	@BeanReference(type = DossierLogPersistence.class)
	protected DossierLogPersistence dossierLogPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierMarkLocalService.class)
	protected org.opencps.dossiermgt.service.DossierMarkLocalService dossierMarkLocalService;
	@BeanReference(type = DossierMarkPersistence.class)
	protected DossierMarkPersistence dossierMarkPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierPartLocalService.class)
	protected org.opencps.dossiermgt.service.DossierPartLocalService dossierPartLocalService;
	@BeanReference(type = DossierPartPersistence.class)
	protected DossierPartPersistence dossierPartPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierRequestUDLocalService.class)
	protected org.opencps.dossiermgt.service.DossierRequestUDLocalService dossierRequestUDLocalService;
	@BeanReference(type = DossierRequestUDPersistence.class)
	protected DossierRequestUDPersistence dossierRequestUDPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierStatisticLocalService.class)
	protected org.opencps.dossiermgt.service.DossierStatisticLocalService dossierStatisticLocalService;
	@BeanReference(type = DossierStatisticPersistence.class)
	protected DossierStatisticPersistence dossierStatisticPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierStatusMappingLocalService.class)
	protected org.opencps.dossiermgt.service.DossierStatusMappingLocalService dossierStatusMappingLocalService;
	@BeanReference(type = DossierStatusMappingPersistence.class)
	protected DossierStatusMappingPersistence dossierStatusMappingPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierSyncLocalService.class)
	protected org.opencps.dossiermgt.service.DossierSyncLocalService dossierSyncLocalService;
	@BeanReference(type = DossierSyncPersistence.class)
	protected DossierSyncPersistence dossierSyncPersistence;
	@BeanReference(type = DossierSyncFinder.class)
	protected DossierSyncFinder dossierSyncFinder;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierTemplateLocalService.class)
	protected org.opencps.dossiermgt.service.DossierTemplateLocalService dossierTemplateLocalService;
	@BeanReference(type = DossierTemplatePersistence.class)
	protected DossierTemplatePersistence dossierTemplatePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DossierUserLocalService.class)
	protected org.opencps.dossiermgt.service.DossierUserLocalService dossierUserLocalService;
	@BeanReference(type = DossierUserPersistence.class)
	protected DossierUserPersistence dossierUserPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.DynamicReportLocalService.class)
	protected org.opencps.dossiermgt.service.DynamicReportLocalService dynamicReportLocalService;
	@BeanReference(type = DynamicReportPersistence.class)
	protected DynamicReportPersistence dynamicReportPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.EFormLocalService.class)
	protected org.opencps.dossiermgt.service.EFormLocalService eFormLocalService;
	@BeanReference(type = EFormPersistence.class)
	protected EFormPersistence eFormPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.MenuConfigLocalService.class)
	protected org.opencps.dossiermgt.service.MenuConfigLocalService menuConfigLocalService;
	@BeanReference(type = MenuConfigPersistence.class)
	protected MenuConfigPersistence menuConfigPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.MenuRoleLocalService.class)
	protected org.opencps.dossiermgt.service.MenuRoleLocalService menuRoleLocalService;
	@BeanReference(type = MenuRolePersistence.class)
	protected MenuRolePersistence menuRolePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.PaymentConfigLocalService.class)
	protected org.opencps.dossiermgt.service.PaymentConfigLocalService paymentConfigLocalService;
	@BeanReference(type = PaymentConfigPersistence.class)
	protected PaymentConfigPersistence paymentConfigPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.PaymentFeeInfoLocalService.class)
	protected org.opencps.dossiermgt.service.PaymentFeeInfoLocalService paymentFeeInfoLocalService;
	@BeanReference(type = PaymentFeeInfoPersistence.class)
	protected PaymentFeeInfoPersistence paymentFeeInfoPersistence;
	@BeanReference(type = PaymentFileLocalService.class)
	protected PaymentFileLocalService paymentFileLocalService;
	@BeanReference(type = PaymentFilePersistence.class)
	protected PaymentFilePersistence paymentFilePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ProcessActionLocalService.class)
	protected org.opencps.dossiermgt.service.ProcessActionLocalService processActionLocalService;
	@BeanReference(type = ProcessActionPersistence.class)
	protected ProcessActionPersistence processActionPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ProcessOptionLocalService.class)
	protected org.opencps.dossiermgt.service.ProcessOptionLocalService processOptionLocalService;
	@BeanReference(type = ProcessOptionPersistence.class)
	protected ProcessOptionPersistence processOptionPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ProcessPluginLocalService.class)
	protected org.opencps.dossiermgt.service.ProcessPluginLocalService processPluginLocalService;
	@BeanReference(type = ProcessPluginPersistence.class)
	protected ProcessPluginPersistence processPluginPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ProcessSequenceLocalService.class)
	protected org.opencps.dossiermgt.service.ProcessSequenceLocalService processSequenceLocalService;
	@BeanReference(type = ProcessSequencePersistence.class)
	protected ProcessSequencePersistence processSequencePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ProcessStepLocalService.class)
	protected org.opencps.dossiermgt.service.ProcessStepLocalService processStepLocalService;
	@BeanReference(type = ProcessStepPersistence.class)
	protected ProcessStepPersistence processStepPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ProcessStepRoleLocalService.class)
	protected org.opencps.dossiermgt.service.ProcessStepRoleLocalService processStepRoleLocalService;
	@BeanReference(type = ProcessStepRolePersistence.class)
	protected ProcessStepRolePersistence processStepRolePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.PublishQueueLocalService.class)
	protected org.opencps.dossiermgt.service.PublishQueueLocalService publishQueueLocalService;
	@BeanReference(type = PublishQueuePersistence.class)
	protected PublishQueuePersistence publishQueuePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.RegistrationLocalService.class)
	protected org.opencps.dossiermgt.service.RegistrationLocalService registrationLocalService;
	@BeanReference(type = RegistrationPersistence.class)
	protected RegistrationPersistence registrationPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.RegistrationFormLocalService.class)
	protected org.opencps.dossiermgt.service.RegistrationFormLocalService registrationFormLocalService;
	@BeanReference(type = RegistrationFormPersistence.class)
	protected RegistrationFormPersistence registrationFormPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.RegistrationLogLocalService.class)
	protected org.opencps.dossiermgt.service.RegistrationLogLocalService registrationLogLocalService;
	@BeanReference(type = RegistrationLogPersistence.class)
	protected RegistrationLogPersistence registrationLogPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.RegistrationTemplatesLocalService.class)
	protected org.opencps.dossiermgt.service.RegistrationTemplatesLocalService registrationTemplatesLocalService;
	@BeanReference(type = RegistrationTemplatesPersistence.class)
	protected RegistrationTemplatesPersistence registrationTemplatesPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ServiceConfigLocalService.class)
	protected org.opencps.dossiermgt.service.ServiceConfigLocalService serviceConfigLocalService;
	@BeanReference(type = ServiceConfigPersistence.class)
	protected ServiceConfigPersistence serviceConfigPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ServiceConfigMappingLocalService.class)
	protected org.opencps.dossiermgt.service.ServiceConfigMappingLocalService serviceConfigMappingLocalService;
	@BeanReference(type = ServiceConfigMappingPersistence.class)
	protected ServiceConfigMappingPersistence serviceConfigMappingPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ServiceFileTemplateLocalService.class)
	protected org.opencps.dossiermgt.service.ServiceFileTemplateLocalService serviceFileTemplateLocalService;
	@BeanReference(type = ServiceFileTemplatePersistence.class)
	protected ServiceFileTemplatePersistence serviceFileTemplatePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ServiceInfoLocalService.class)
	protected org.opencps.dossiermgt.service.ServiceInfoLocalService serviceInfoLocalService;
	@BeanReference(type = ServiceInfoPersistence.class)
	protected ServiceInfoPersistence serviceInfoPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ServiceInfoMappingLocalService.class)
	protected org.opencps.dossiermgt.service.ServiceInfoMappingLocalService serviceInfoMappingLocalService;
	@BeanReference(type = ServiceInfoMappingPersistence.class)
	protected ServiceInfoMappingPersistence serviceInfoMappingPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ServiceProcessLocalService.class)
	protected org.opencps.dossiermgt.service.ServiceProcessLocalService serviceProcessLocalService;
	@BeanReference(type = ServiceProcessPersistence.class)
	protected ServiceProcessPersistence serviceProcessPersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.ServiceProcessRoleLocalService.class)
	protected org.opencps.dossiermgt.service.ServiceProcessRoleLocalService serviceProcessRoleLocalService;
	@BeanReference(type = ServiceProcessRolePersistence.class)
	protected ServiceProcessRolePersistence serviceProcessRolePersistence;
	@BeanReference(type = org.opencps.dossiermgt.service.StepConfigLocalService.class)
	protected org.opencps.dossiermgt.service.StepConfigLocalService stepConfigLocalService;
	@BeanReference(type = StepConfigPersistence.class)
	protected StepConfigPersistence stepConfigPersistence;
	@BeanReference(type = StepConfigFinder.class)
	protected StepConfigFinder stepConfigFinder;
	@BeanReference(type = org.opencps.dossiermgt.service.UserInfoLogLocalService.class)
	protected org.opencps.dossiermgt.service.UserInfoLogLocalService userInfoLogLocalService;
	@BeanReference(type = UserInfoLogPersistence.class)
	protected UserInfoLogPersistence userInfoLogPersistence;
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