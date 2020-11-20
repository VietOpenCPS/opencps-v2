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

package org.opencps.datamgt.upgrade;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.opencps.datamgt.upgrade.v0_0_1.UpgradeSchema;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author nhanhoang
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class DatamgtServiceUpgrade implements UpgradeStepRegistrator {
	
	

	@Override
	public void register(Registry registry) {
		
		_log.info("===DatamgtServiceUpgrade===");
		_log.info("===DatamgtServiceUpgrade===");
		
		
		
		registry.register("1.0.0", "1.0.1", new DummyUpgradeStep());
		registry.register("1.0.1", "1.0.2", new UpgradeSchema());
		// See LPS-82746
		
	}

	@Reference(unbind = "-")
	protected void setSettingsFactory(SettingsFactory settingsFactory) {
		_settingsFactory = settingsFactory;
	}

	@Reference
	private SettingsFactory _settingsFactory;
	
	
	
	private static final Log _log = LogFactoryUtil.getLog(
			DatamgtServiceUpgrade.class);

}