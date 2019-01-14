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

import org.opencps.statistic.model.OpencpsVotingStatistic;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for OpencpsVotingStatistic. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see OpencpsVotingStatisticLocalServiceUtil
 * @see org.opencps.statistic.service.base.OpencpsVotingStatisticLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsVotingStatisticLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OpencpsVotingStatisticLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsVotingStatisticLocalServiceUtil} to access the opencps voting statistic local service. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsVotingStatisticLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the opencps voting statistic to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OpencpsVotingStatistic addOpencpsVotingStatistic(
		OpencpsVotingStatistic opencpsVotingStatistic);

	public OpencpsVotingStatistic checkExsit(long groupId, int month, int year,
		String govAgency, String domain, String votingCode);

	/**
	* Creates a new opencps voting statistic with the primary key. Does not add the opencps voting statistic to the database.
	*
	* @param votingStatisticId the primary key for the new opencps voting statistic
	* @return the new opencps voting statistic
	*/
	@Transactional(enabled = false)
	public OpencpsVotingStatistic createOpencpsVotingStatistic(
		long votingStatisticId);

	/**
	* Deletes the opencps voting statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic that was removed
	* @throws PortalException if a opencps voting statistic with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public OpencpsVotingStatistic deleteOpencpsVotingStatistic(
		long votingStatisticId) throws PortalException;

	/**
	* Deletes the opencps voting statistic from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public OpencpsVotingStatistic deleteOpencpsVotingStatistic(
		OpencpsVotingStatistic opencpsVotingStatistic);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public OpencpsVotingStatistic fetchOpencpsVotingStatistic(
		long votingStatisticId);

	/**
	* Returns the opencps voting statistic matching the UUID and group.
	*
	* @param uuid the opencps voting statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsVotingStatistic fetchOpencpsVotingStatisticByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsVotingStatistic> fetchVotingStatistic(long groupId,
		int month, int year, String votingCode, String domain,
		String govAgencyCode, int start, int end)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the opencps voting statistic with the primary key.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic
	* @throws PortalException if a opencps voting statistic with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsVotingStatistic getOpencpsVotingStatistic(
		long votingStatisticId) throws PortalException;

	/**
	* Returns the opencps voting statistic matching the UUID and group.
	*
	* @param uuid the opencps voting statistic's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps voting statistic
	* @throws PortalException if a matching opencps voting statistic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsVotingStatistic getOpencpsVotingStatisticByUuidAndGroupId(
		String uuid, long groupId) throws PortalException;

	/**
	* Returns a range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of opencps voting statistics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsVotingStatistic> getOpencpsVotingStatistics(int start,
		int end);

	/**
	* Returns all the opencps voting statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps voting statistics
	* @param companyId the primary key of the company
	* @return the matching opencps voting statistics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsVotingStatistic> getOpencpsVotingStatisticsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of opencps voting statistics matching the UUID and company.
	*
	* @param uuid the UUID of the opencps voting statistics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps voting statistics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsVotingStatistic> getOpencpsVotingStatisticsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the number of opencps voting statistics.
	*
	* @return the number of opencps voting statistics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOpencpsVotingStatisticsCount();

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

	public void removeVotingStatisticByD_M_Y(long groupId, String domainCode,
		int month, int year);

	public void removeVotingStatisticByMonthYear(long groupId, int month,
		int year);

	public void removeVotingStatisticByYear(long companyId, long groupId,
		int month, int year);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsVotingStatistic> searchVotingStatistic(long groupId,
		int month, int year, String votingCode, String domain,
		String govAgencyCode, int start, int end)
		throws PortalException, SystemException;

	/**
	* Updates the opencps voting statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	* @return the opencps voting statistic that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OpencpsVotingStatistic updateOpencpsVotingStatistic(
		OpencpsVotingStatistic opencpsVotingStatistic);

	public OpencpsVotingStatistic updateVotingStatistic(
		long votingStatisticId, long companyId, long groupId, long userId,
		String userName, int month, int year, String votingSubject,
		int totalVoted, int veryGoodCount, int goodCount, int badCount,
		int percentVeryGood, int percentGood, int percentBad,
		String govAgencyCode, String govAgencyName, String domainCode,
		String domainName, String votingCode, int totalCount);
}