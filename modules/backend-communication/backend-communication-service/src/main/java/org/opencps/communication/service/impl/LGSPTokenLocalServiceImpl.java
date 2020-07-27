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

package org.opencps.communication.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.communication.model.LGSPToken;
import org.opencps.communication.service.base.LGSPTokenLocalServiceBaseImpl;

/**
 * The implementation of the lgsp token local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.communication.service.LGSPTokenLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavd
 * @see LGSPTokenLocalServiceBaseImpl
 * @see org.opencps.communication.service.LGSPTokenLocalServiceUtil
 */
public class LGSPTokenLocalServiceImpl extends LGSPTokenLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.communication.service.LGSPTokenLocalServiceUtil} to access the lgsp token local service.
	 */

	public List<LGSPToken> getByTokenType(String tokenType) {
		return lgspTokenPersistence.findByF_TYPE_EXPIRY(tokenType);
	}

	public LGSPToken updateLGSPToken(String token, String tokenType, String refreshToken, Date expiryDate) {

		long tokenId = counterLocalService.increment(LGSPToken.class.getName());
		LGSPToken lgspToken = lgspTokenPersistence.create(tokenId);

		Date now = new Date();
		// Audit fields
		lgspToken.setCreateDate(now);
		lgspToken.setModifiedDate(now);
		lgspToken.setToken(token);
		lgspToken.setTokenType(tokenType);
		lgspToken.setRefreshToken(refreshToken);
		lgspToken.setExpiryDate(expiryDate);

		return lgspTokenPersistence.update(lgspToken);
	}

}