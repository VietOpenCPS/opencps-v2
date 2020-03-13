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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.action.impl.ConfigCounterActionsImpl;
import org.opencps.dossiermgt.model.ConfigCounter;
import org.opencps.dossiermgt.service.base.ConfigCounterLocalServiceBaseImpl;

/**
 * The implementation of the config counter local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.ConfigCounterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ConfigCounterLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ConfigCounterLocalServiceUtil
 */
public class ConfigCounterLocalServiceImpl
	extends ConfigCounterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.ConfigCounterLocalServiceUtil} to access the config counter local service.
	 */

	private static final Log _log = LogFactoryUtil.getLog(ConfigCounterLocalServiceImpl.class);

	public ConfigCounter updateConfigCounter(long groupId, long userId, long configCounterId, String counterCode, String patternCode,
			int startCounter, ServiceContext serviceContext) {
		
		Date now = new Date();

		if (configCounterId > 0) {
			ConfigCounter config = configCounterPersistence.fetchByPrimaryKey(configCounterId);
			//
			config.setModifiedDate(now);
			if (Validator.isNotNull(counterCode))
				config.setCounterCode(counterCode);
			if (Validator.isNotNull(patternCode))
				config.setPatternCode(patternCode);
			if (Validator.isNotNull(startCounter))
				config.setStartCounter(startCounter);
			//
			return configCounterPersistence.update(config);
		} else {
			configCounterId = counterLocalService.increment(ConfigCounter.class.getName());
			ConfigCounter config = configCounterPersistence.create(configCounterId);
			//
			config.setCreateDate(now);
			config.setModifiedDate(now);
			config.setCompanyId(serviceContext.getCompanyId());
			config.setGroupId(groupId);
			config.setUserId(userId);
			//
			config.setCounterCode(counterCode);
			config.setPatternCode(patternCode);
			config.setStartCounter(startCounter);

			return configCounterPersistence.update(config);
		}
	}

	
	public ConfigCounter fetchByCountrCode(long groupId, String counterCode) {
		return configCounterPersistence.fetchByGID_CODE(groupId, counterCode);
	}

	public List<ConfigCounter> getByGroupId(long groupId, int start, int end) {
		try {
			return configCounterPersistence.findByG_ID(groupId, start, end);
		} catch (Exception e) {
			_log.debug(e);
		}
		return null;
	}

	public long countByGroupId(long groupId) {
		return configCounterPersistence.countByG_ID(groupId);
	}

}