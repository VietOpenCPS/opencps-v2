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

package org.graphql.api.controller.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnect;
import com.liferay.portal.security.sso.openid.connect.configuration.OpenIdConnectConfiguration;
import com.liferay.portal.security.sso.openid.connect.constants.OpenIdConnectConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author nhanhlt
 */
@Component(immediate = true, service = OpenIdConnect.class)
public class OpenIdConnectUtils implements OpenIdConnect {

	@Override
	public boolean isEnabled(long companyId) {
		try {
			OpenIdConnectConfiguration openIdConfiguration =
					ConfigurationProviderUtil.getConfiguration(
					OpenIdConnectConfiguration.class,
					new CompanyServiceSettingsLocator(
						companyId, OpenIdConnectConstants.SERVICE_NAME));

			return openIdConfiguration.enabled();
		}
		catch (ConfigurationException ce) {
			_log.error("Unable to get OpenId configuration", ce);
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OpenIdConnectUtils.class);


}