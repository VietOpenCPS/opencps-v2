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

package org.opencps.systemmgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SchedulerRecordLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerRecordLocalService
 * @generated
 */
@ProviderType
public class SchedulerRecordLocalServiceWrapper
	implements SchedulerRecordLocalService,
		ServiceWrapper<SchedulerRecordLocalService> {
	public SchedulerRecordLocalServiceWrapper(
		SchedulerRecordLocalService schedulerRecordLocalService) {
		_schedulerRecordLocalService = schedulerRecordLocalService;
	}

	/**
	* Adds the scheduler record to the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerRecord the scheduler record
	* @return the scheduler record that was added
	*/
	@Override
	public org.opencps.systemmgt.model.SchedulerRecord addSchedulerRecord(
		org.opencps.systemmgt.model.SchedulerRecord schedulerRecord) {
		return _schedulerRecordLocalService.addSchedulerRecord(schedulerRecord);
	}

	/**
	* Creates a new scheduler record with the primary key. Does not add the scheduler record to the database.
	*
	* @param schedulerId the primary key for the new scheduler record
	* @return the new scheduler record
	*/
	@Override
	public org.opencps.systemmgt.model.SchedulerRecord createSchedulerRecord(
		long schedulerId) {
		return _schedulerRecordLocalService.createSchedulerRecord(schedulerId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _schedulerRecordLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the scheduler record with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record that was removed
	* @throws PortalException if a scheduler record with the primary key could not be found
	*/
	@Override
	public org.opencps.systemmgt.model.SchedulerRecord deleteSchedulerRecord(
		long schedulerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _schedulerRecordLocalService.deleteSchedulerRecord(schedulerId);
	}

	/**
	* Deletes the scheduler record from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerRecord the scheduler record
	* @return the scheduler record that was removed
	*/
	@Override
	public org.opencps.systemmgt.model.SchedulerRecord deleteSchedulerRecord(
		org.opencps.systemmgt.model.SchedulerRecord schedulerRecord) {
		return _schedulerRecordLocalService.deleteSchedulerRecord(schedulerRecord);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _schedulerRecordLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _schedulerRecordLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.systemmgt.model.impl.SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _schedulerRecordLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.systemmgt.model.impl.SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _schedulerRecordLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _schedulerRecordLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _schedulerRecordLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.systemmgt.model.SchedulerRecord fetchByST(
		String schedulerType) {
		return _schedulerRecordLocalService.fetchByST(schedulerType);
	}

	@Override
	public org.opencps.systemmgt.model.SchedulerRecord fetchSchedulerRecord(
		long schedulerId) {
		return _schedulerRecordLocalService.fetchSchedulerRecord(schedulerId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _schedulerRecordLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _schedulerRecordLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _schedulerRecordLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _schedulerRecordLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the scheduler record with the primary key.
	*
	* @param schedulerId the primary key of the scheduler record
	* @return the scheduler record
	* @throws PortalException if a scheduler record with the primary key could not be found
	*/
	@Override
	public org.opencps.systemmgt.model.SchedulerRecord getSchedulerRecord(
		long schedulerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _schedulerRecordLocalService.getSchedulerRecord(schedulerId);
	}

	/**
	* Returns a range of all the scheduler records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.systemmgt.model.impl.SchedulerRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of scheduler records
	* @param end the upper bound of the range of scheduler records (not inclusive)
	* @return the range of scheduler records
	*/
	@Override
	public java.util.List<org.opencps.systemmgt.model.SchedulerRecord> getSchedulerRecords(
		int start, int end) {
		return _schedulerRecordLocalService.getSchedulerRecords(start, end);
	}

	/**
	* Returns the number of scheduler records.
	*
	* @return the number of scheduler records
	*/
	@Override
	public int getSchedulerRecordsCount() {
		return _schedulerRecordLocalService.getSchedulerRecordsCount();
	}

	@Override
	public org.opencps.systemmgt.model.SchedulerRecord updateSchedulerRecord(
		long schedulerId, String schedulerType, java.util.Date onTime,
		java.util.Date nextTime, java.util.Date expiredTime, long minDuration,
		long maxDuration) {
		return _schedulerRecordLocalService.updateSchedulerRecord(schedulerId,
			schedulerType, onTime, nextTime, expiredTime, minDuration,
			maxDuration);
	}

	/**
	* Updates the scheduler record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param schedulerRecord the scheduler record
	* @return the scheduler record that was updated
	*/
	@Override
	public org.opencps.systemmgt.model.SchedulerRecord updateSchedulerRecord(
		org.opencps.systemmgt.model.SchedulerRecord schedulerRecord) {
		return _schedulerRecordLocalService.updateSchedulerRecord(schedulerRecord);
	}

	@Override
	public SchedulerRecordLocalService getWrappedService() {
		return _schedulerRecordLocalService;
	}

	@Override
	public void setWrappedService(
		SchedulerRecordLocalService schedulerRecordLocalService) {
		_schedulerRecordLocalService = schedulerRecordLocalService;
	}

	private SchedulerRecordLocalService _schedulerRecordLocalService;
}