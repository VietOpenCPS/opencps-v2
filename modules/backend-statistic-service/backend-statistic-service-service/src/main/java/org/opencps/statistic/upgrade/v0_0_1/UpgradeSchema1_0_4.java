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

package org.opencps.statistic.upgrade.v0_0_1;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import org.opencps.statistic.upgrade.v0_0_1.util.StatisticTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema1_0_4 extends UpgradeProcess {
	
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema1_0_4.class.getName());
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());
		
		if(!hasColumn(StatisticTable.TABLE_NAME, "processingInAPeriodCount")) {
			alter(StatisticTable.class,new AlterTableAddColumn("processingInAPeriodCount INTEGER"));
			
		}

		if(!hasColumn(StatisticTable.TABLE_NAME, "releaseInAPeriodCount")) {
			alter(StatisticTable.class,new AlterTableAddColumn("releaseInAPeriodCount INTEGER"));
			
		}
		
		
		
		runSQL("create index IX_45A15230 on opencps_statistic (groupId, govAgencyCode, month, year, system, domainCode)");
		runSQL("create index IX_68F959EF on opencps_statistic (groupId, month, year, domainCode)");
		runSQL("create index IX_89A15952 on opencps_statistic (groupId, month, year, system, domainCode)");

		
		
		
	}

}