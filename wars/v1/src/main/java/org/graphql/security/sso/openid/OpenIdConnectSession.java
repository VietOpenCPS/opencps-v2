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

package org.graphql.security.sso.openid;

import com.liferay.portal.security.sso.openid.connect.OpenIdConnectFlowState;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Jesse Rao
 */
@ProviderType
public interface OpenIdConnectSession {

	public String getAccessTokenValue();

	public long getLoginTime();

	public long getLoginUserId();

	public String getNonceValue();

	public OpenIdConnectFlowState getOpenIdConnectFlowState();

	public String getOpenIdProviderName();

	public String getRefreshTokenValue();

	public String getStateValue();

	public void setOpenIdConnectFlowState(
		OpenIdConnectFlowState openIdConnectFlowState);

}