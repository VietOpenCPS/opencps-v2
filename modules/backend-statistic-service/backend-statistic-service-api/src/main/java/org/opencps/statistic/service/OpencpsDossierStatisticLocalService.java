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

package org.opencps.statistic.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.statistic.dto.DossierStatisticData;
import org.opencps.statistic.model.OpencpsDossierStatistic;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for OpencpsDossierStatistic. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see OpencpsDossierStatisticLocalServiceUtil
 * @see org.opencps.statistic.service.base.OpencpsDossierStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsDossierStatisticLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OpencpsDossierStatisticLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsDossierStatisticLocalServiceUtil} to access the opencps dossier statistic local service. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsDossierStatisticLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the opencps dossier statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OpencpsDossierStatistic addOpencpsDossierStatistic(
		OpencpsDossierStatistic opencpsDossierStatistic);

	public OpencpsDossierStatistic checkExsit(long groupId, int month,
		int year, String govAgency, String domain);

	public OpencpsDossierStatistic checkNotDuplicate(long groupId,
		String govAgencyCode, int month, int year, String domainCode);

	public OpencpsDossierStatistic createOnlyStatistic(long companyId,
		long groupId, long userId, String userName, int month, int year,
		int totalCount, int deniedCount, int cancelledCount, int processCount,
		int remainingCount, int receivedCount, int onlineCount,
		int releaseCount, int betimesCount, int ontimeCount, int overtimeCount,
		int doneCount, int releasingCount, int unresolvedCount,
		int processingCount, int undueCount, int overdueCount,
		int pausingCount, int ontimePercentage, int overtimeInside,
		int overtimeOutside, int interoperatingCount, int waitingCount,
		String govAgencyCode, String govAgencyName, String domainCode,
		String domainName, boolean reporting, int onegateCount,
		int outsideCount, int insideCount)
		throws PortalException, SystemException;

	/**
	* Creates a new opencps dossier statistic with the primary key. Does not add the opencps dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new opencps dossier statistic
	* @return the new opencps dossier statistic
	*/
	@Transactional(enabled = false)
	public OpencpsDossierStatistic createOpencpsDossierStatistic(
		long dossierStatisticId);

	public OpencpsDossierStatistic createOrUpdateStatistic(long companyId,
		long groupId, long userId, String userName, int month, int year,
		int totalCount, int deniedCount, int cancelledCount, int processCount,
		int remainingCount, int receivedCount, int onlineCount,
		int releaseCount, int betimesCount, int ontimeCount, int overtimeCount,
		int doneCount, int releasingCount, int unresolvedCount,
		int processingCount, int undueCount, int overdueCount,
		int pausingCount, int ontimePercentage, int overtimeInside,
		int overtimeOutside, int interoperatingCount, int waitingCount,
		String govAgencyCode, String govAgencyName, String domainCode,
		String domainName, boolean reporting, int onegateCount,
		int outsideCount, int insideCount)
		throws PortalException, SystemException;

	/**
	* Deletes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public OpencpsDossierStatistic deleteOpencpsDossierStatistic(
		long dossierStatisticId) throws PortalException;

	/**
	* Deletes the opencps dossier statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public OpencpsDossierStatistic deleteOpencpsDossierStatistic(
		OpencpsDossierStatistic opencpsDossierStatistic);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> fetchDossierStatistic(long groupId,
		int month, int year, String domain, String govAgencyCode,
		String groupAgenvyCode, int start, int end)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossierStatistic fetchOpencpsDossierStatistic(
		long dossierStatisticId);

	/**
	* Returns the opencps dossier statistic matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossierStatistic fetchOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId);

	public List<OpencpsDossierStatistic> findByG(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossierStatistic getByGovMonthYear(long groupId,
		String govAgencyCode, int month, int year)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossierStatistic getByGovMonthYearDomain(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> getDossierStatisticByMonthsYearAndReport(
		long groupId, int[] month, int year, boolean reporting);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYear(
		long groupId, int month, int year);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYearAndReport(
		long groupId, int month, int year, boolean reporting);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> getDossierStatisticByYear(
		long companyId, long groupId, int month, int year);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the opencps dossier statistic with the primary key.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic
	* @throws PortalException if a opencps dossier statistic with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossierStatistic getOpencpsDossierStatistic(
		long dossierStatisticId) throws PortalException;

	/**
	* Returns the opencps dossier statistic matching the UUID and group.
	*
	* @param uuid the opencps dossier statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier statistic
	* @throws PortalException if a matching opencps dossier statistic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossierStatistic getOpencpsDossierStatisticByUuidAndGroupId(
		String uuid, long groupId) throws PortalException;

	/**
	* Returns a range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of opencps dossier statistics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> getOpencpsDossierStatistics(
		int start, int end);

	/**
	* Returns all the opencps dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistics
	* @param companyId the primary key of the company
	* @return the matching opencps dossier statistics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of opencps dossier statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossier statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps dossier statistics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> getOpencpsDossierStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns the number of opencps dossier statistics.
	*
	* @return the number of opencps dossier statistics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOpencpsDossierStatisticsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossierStatistic> searchDossierStatistic(long groupId,
		int month, int year, String domain, String govAgencyCode,
		String groupAgenvyCode, int start, int end)
		throws PortalException, SystemException;

	public OpencpsDossierStatistic updateOnlyStatistic(
		OpencpsDossierStatistic dossierStatistic, long companyId, long groupId,
		long userId, String userName, int month, int year, int totalCount,
		int deniedCount, int cancelledCount, int processCount,
		int remainingCount, int receivedCount, int onlineCount,
		int releaseCount, int betimesCount, int ontimeCount, int overtimeCount,
		int doneCount, int releasingCount, int unresolvedCount,
		int processingCount, int undueCount, int overdueCount,
		int pausingCount, int ontimePercentage, int overtimeInside,
		int overtimeOutside, int interoperatingCount, int waitingCount,
		String govAgencyCode, String govAgencyName, String domainCode,
		String domainName, boolean reporting, int onegateCount,
		int outsideCount, int insideCount)
		throws PortalException, SystemException;

	/**
	* Updates the opencps dossier statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	* @return the opencps dossier statistic that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OpencpsDossierStatistic updateOpencpsDossierStatistic(
		OpencpsDossierStatistic opencpsDossierStatistic);

	public OpencpsDossierStatistic updateStatistic(long dossierStatisticId,
		long companyId, long groupId, long userId, String userName, int month,
		int year, int totalCount, int deniedCount, int cancelledCount,
		int processCount, int remainingCount, int receivedCount,
		int onlineCount, int releaseCount, int betimesCount, int ontimeCount,
		int overtimeCount, int doneCount, int releasingCount,
		int unresolvedCount, int processingCount, int undueCount,
		int overdueCount, int pausingCount, int ontimePercentage,
		int overtimeInside, int overtimeOutside, int interoperatingCount,
		int waitingCount, String govAgencyCode, String govAgencyName,
		String domainCode, String domainName, boolean reporting,
		int onegateCount, int outsideCount, int insideCount)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor =  {
		SystemException.class, PortalException.class, Exception.class}
	)
	public void updateStatisticData(
		Map<String, DossierStatisticData> statisticData)
		throws SystemException, PortalException;
}