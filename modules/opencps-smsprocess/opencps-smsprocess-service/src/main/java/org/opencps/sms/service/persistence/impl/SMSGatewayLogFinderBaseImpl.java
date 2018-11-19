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

package org.opencps.sms.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import org.opencps.sms.model.SMSGatewayLog;
import org.opencps.sms.service.persistence.SMSGatewayLogPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author khoa
 * @generated
 */
public class SMSGatewayLogFinderBaseImpl extends BasePersistenceImpl<SMSGatewayLog> {
	public SMSGatewayLogFinderBaseImpl() {
		setModelClass(SMSGatewayLog.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getSMSGatewayLogPersistence().getBadColumnNames();
	}

	/**
	 * Returns the sms gateway log persistence.
	 *
	 * @return the sms gateway log persistence
	 */
	public SMSGatewayLogPersistence getSMSGatewayLogPersistence() {
		return smsGatewayLogPersistence;
	}

	/**
	 * Sets the sms gateway log persistence.
	 *
	 * @param smsGatewayLogPersistence the sms gateway log persistence
	 */
	public void setSMSGatewayLogPersistence(
		SMSGatewayLogPersistence smsGatewayLogPersistence) {
		this.smsGatewayLogPersistence = smsGatewayLogPersistence;
	}

	@BeanReference(type = SMSGatewayLogPersistence.class)
	protected SMSGatewayLogPersistence smsGatewayLogPersistence;
	private static final Log _log = LogFactoryUtil.getLog(SMSGatewayLogFinderBaseImpl.class);
}