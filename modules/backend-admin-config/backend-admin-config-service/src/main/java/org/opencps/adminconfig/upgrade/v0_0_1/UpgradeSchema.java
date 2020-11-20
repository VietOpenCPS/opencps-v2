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

package org.opencps.adminconfig.upgrade.v0_0_1;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.opencps.adminconfig.upgrade.util.ReportRoleTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema.class.getName());

	@Override
	protected void doUpgrade() throws Exception {

		upgrade(new UpgradeMVCCVersion());

		if (!hasTable(ReportRoleTable.TABLE_NAME)) {
			
			runSQL(ReportRoleTable.TABLE_SQL_CREATE);

		}else {
			
			upgradeTable(ReportRoleTable.TABLE_NAME, 
					ReportRoleTable.TABLE_COLUMNS, 
					ReportRoleTable.TABLE_SQL_CREATE,
					ReportRoleTable.TABLE_SQL_ADD_INDEXES);
		}

	}

}