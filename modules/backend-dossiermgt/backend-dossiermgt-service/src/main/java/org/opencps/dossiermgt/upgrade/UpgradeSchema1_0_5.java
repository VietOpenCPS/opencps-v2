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

package org.opencps.dossiermgt.upgrade;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.opencps.dossiermgt.upgrade.table.ApplicableInfoTable;
import org.opencps.dossiermgt.upgrade.table.PaymentFeeInfoTable;
import org.opencps.dossiermgt.upgrade.table.ServiceConfigMappingTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema1_0_5 extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema1_0_5.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());
		

		if (!hasTable(PaymentFeeInfoTable.TABLE_NAME)) {

			runSQL(PaymentFeeInfoTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(PaymentFeeInfoTable.TABLE_NAME, PaymentFeeInfoTable.TABLE_COLUMNS, PaymentFeeInfoTable.TABLE_SQL_CREATE,
					PaymentFeeInfoTable.TABLE_SQL_ADD_INDEXES);
		}
		
		if (!hasTable(ServiceConfigMappingTable.TABLE_NAME)) {

			runSQL(ServiceConfigMappingTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(ServiceConfigMappingTable.TABLE_NAME, ServiceConfigMappingTable.TABLE_COLUMNS, ServiceConfigMappingTable.TABLE_SQL_CREATE,
					ServiceConfigMappingTable.TABLE_SQL_ADD_INDEXES);
		}
		
		if (!hasTable(ApplicableInfoTable.TABLE_NAME)) {

			runSQL(ApplicableInfoTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(ApplicableInfoTable.TABLE_NAME, ApplicableInfoTable.TABLE_COLUMNS, ApplicableInfoTable.TABLE_SQL_CREATE,
					ApplicableInfoTable.TABLE_SQL_ADD_INDEXES);
		}

	}

}