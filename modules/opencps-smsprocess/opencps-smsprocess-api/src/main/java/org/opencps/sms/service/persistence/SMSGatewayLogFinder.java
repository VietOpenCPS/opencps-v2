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

package org.opencps.sms.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author khoa
 * @generated
 */
@ProviderType
public interface SMSGatewayLogFinder {
	public java.util.List<org.opencps.sms.model.SMSGatewayLog> searchSMSGateway(
		int smsType, java.util.Date reqFrom, java.util.Date reqTo,
		java.util.Date replyFrom, java.util.Date replyTo, String src,
		int status, int start, int end);
}