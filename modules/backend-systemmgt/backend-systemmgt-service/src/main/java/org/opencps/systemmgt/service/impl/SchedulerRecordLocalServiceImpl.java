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

package org.opencps.systemmgt.service.impl;

import java.util.Date;

import org.opencps.systemmgt.model.SchedulerRecord;
import org.opencps.systemmgt.service.base.SchedulerRecordLocalServiceBaseImpl;

/**
 * The implementation of the scheduler record local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.systemmgt.service.SchedulerRecordLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerRecordLocalServiceBaseImpl
 * @see org.opencps.systemmgt.service.SchedulerRecordLocalServiceUtil
 */
public class SchedulerRecordLocalServiceImpl
	extends SchedulerRecordLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.systemmgt.service.SchedulerRecordLocalServiceUtil} to access the scheduler record local service.
	 */
	
	public SchedulerRecord updateSchedulerRecord(long schedulerId, String schedulerType, Date onTime, Date nextTime
			, Date expiredTime, long minDuration, long maxDuration) {
		SchedulerRecord scheduler = null;
		if (schedulerId == 0) {
			schedulerId = counterLocalService.increment(SchedulerRecord.class.getName());
			scheduler = schedulerRecordPersistence.create(schedulerId);
			
			scheduler.setSchedulerType(schedulerType);
			scheduler.setOnTime(onTime);
			scheduler.setNextTime(nextTime);
			scheduler.setExpiredTime(expiredTime);
			scheduler.setMinDuration(minDuration);
			scheduler.setMaxDuration(maxDuration);
		}
		else {
			scheduler = schedulerRecordPersistence.fetchByPrimaryKey(schedulerId);
			
			scheduler.setSchedulerType(schedulerType);
			scheduler.setOnTime(onTime);
			scheduler.setNextTime(nextTime);
			scheduler.setExpiredTime(expiredTime);
			scheduler.setMinDuration(minDuration);
			scheduler.setMaxDuration(maxDuration);			
		}
		
		return schedulerRecordPersistence.update(scheduler);
	}
	
	public SchedulerRecord fetchByST(String schedulerType) {
		return schedulerRecordPersistence.fetchByST(schedulerType);
	}
}