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

package org.opencps.usermgt.upgrade.v0_0_1;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.opencps.usermgt.upgrade.v0_0_1.util.EmployeeJobPosTable;
import org.opencps.usermgt.upgrade.v0_0_1.util.EmployeeTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {

		upgrade(new UpgradeMVCCVersion());

		if (!hasTable(EmployeeTable.TABLE_NAME)) {
			runSQL(EmployeeTable.TABLE_SQL_CREATE);
		} else {
			upgradeTable(EmployeeTable.TABLE_NAME, 
					EmployeeTable.TABLE_COLUMNS, 
					EmployeeTable.TABLE_SQL_CREATE,
					EmployeeTable.TABLE_SQL_ADD_INDEXES);

		}
		
		if(!hasColumn(EmployeeTable.TABLE_NAME, "jobPosTitle")) {
			alter(EmployeeTable.class, new AlterTableAddColumn("jobPosTitle VARCHAR(75) null"));
		}
		
		if (!hasTable(EmployeeJobPosTable.TABLE_NAME)) {
			runSQL(EmployeeJobPosTable.TABLE_SQL_CREATE);
		} else {
			upgradeTable(EmployeeJobPosTable.TABLE_NAME, 
					EmployeeJobPosTable.TABLE_COLUMNS, 
					EmployeeJobPosTable.TABLE_SQL_CREATE,
					EmployeeJobPosTable.TABLE_SQL_ADD_INDEXES);

		}

	}

}