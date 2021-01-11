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

package org.opencps.communication.upgrade.v1_0_1;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.opencps.communication.upgrade.v1_0_1.util.NotificationQueueTable;
import org.opencps.communication.upgrade.v1_0_1.util.NotificationTemplateTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema1_0_2 extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema1_0_2.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());

		if(!hasColumn(NotificationTemplateTable.TABLE_NAME, "priority")) {
			alter(NotificationQueueTable.class, new AlterTableAddColumn("priority INTEGER"));
		}
		
		

	}

}