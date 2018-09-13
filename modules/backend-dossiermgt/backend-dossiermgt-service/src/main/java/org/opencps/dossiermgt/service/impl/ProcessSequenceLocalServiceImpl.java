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

package org.opencps.dossiermgt.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.exception.NoSuchProcessSequenceException;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.service.base.ProcessSequenceLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * The implementation of the process sequence local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.ProcessSequenceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ProcessSequenceLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil
 */
public class ProcessSequenceLocalServiceImpl
	extends ProcessSequenceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil} to access the process sequence local service.
	 */
	
	public List<ProcessSequence> findByG_SN(long groupId, String sequenceNo) {
		return processSequencePersistence.findByG_SN(groupId, sequenceNo);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public ProcessSequence addProcessSequence(long userId, long groupId, 
			long serviceProcessId,
			String sequenceNo, String sequenceName, double durationCount) throws PortalException {
		User user = userLocalService.getUser(userId);
		
		long processSequenceId = counterLocalService.increment();
		ProcessSequence processSequence = processSequencePersistence.create(processSequenceId);
		
		Date now = new Date();
		
		processSequence.setServiceProcessId(serviceProcessId);
		processSequence.setCreateDate(now);
		processSequence.setModifiedDate(now);
		
		processSequence.setUserId(user.getUserId());
		processSequence.setGroupId(groupId);
		processSequence.setSequenceNo(sequenceNo);
		processSequence.setSequenceName(sequenceName);
		processSequence.setDurationCount(durationCount);
		
		return processSequencePersistence.update(processSequence);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public ProcessSequence updateProcessSequence(long userId, long groupId, 
			long processSequenceId,
			long serviceProcessId,
			String sequenceNo, String sequenceName, double durationCount) throws PortalException {
		User user = userLocalService.getUser(userId);
		
		ProcessSequence processSequence = processSequencePersistence.findByPrimaryKey(processSequenceId);
		
		Date now = new Date();
		
		processSequence.setCreateDate(now);
		processSequence.setModifiedDate(now);
		
		processSequence.setServiceProcessId(serviceProcessId);
		processSequence.setUserId(user.getUserId());
		processSequence.setGroupId(groupId);
		processSequence.setSequenceNo(sequenceNo);
		processSequence.setSequenceName(sequenceName);
		processSequence.setDurationCount(durationCount);
		
		return processSequencePersistence.update(processSequence);
	}	
	
	@Indexable(type = IndexableType.REINDEX)
	public ProcessSequence deleteProcessSequence(long processSequenceId) throws PortalException {
		ProcessSequence processSequence = processSequencePersistence.fetchByPrimaryKey(processSequenceId);
		if (processSequence != null) {
			return processSequencePersistence.remove(processSequence);
		}
		return null;
	}

	@Indexable(type = IndexableType.REINDEX)
	public void updateProcessSequenceDB(long userId, long groupId, long serviceProcessId, String sequenceNo,
			String sequenceName, String sequenceRole, Integer durationCount, ServiceContext serviceContext)
			throws PortalException {
		User user = userLocalService.getUser(userId);
		
		long processSequenceId = counterLocalService.increment();
		ProcessSequence processSequence = processSequencePersistence.create(processSequenceId);
		
		Date now = new Date();
		
		processSequence.setCreateDate(now);
		processSequence.setModifiedDate(now);
		
		processSequence.setUserId(user.getUserId());
		processSequence.setGroupId(groupId);
		processSequence.setServiceProcessId(serviceProcessId);
		processSequence.setSequenceNo(sequenceNo);
		processSequence.setSequenceName(sequenceName);
		processSequence.setSequenceRole(sequenceRole);
		processSequence.setDurationCount(durationCount);
		
		processSequencePersistence.update(processSequence);
	}

	public List<ProcessSequence> getByServiceProcess(long groupId, long serviceProcessId) {
		return processSequencePersistence.findByF_GID_SID(groupId, serviceProcessId);
	}

	public ProcessSequence findBySID_SNO(long groupId, long serviceProcessId, String sequenceNo) {
		return processSequencePersistence.fetchByF_GID_SID_SNO(groupId, serviceProcessId, sequenceNo);
	}
}