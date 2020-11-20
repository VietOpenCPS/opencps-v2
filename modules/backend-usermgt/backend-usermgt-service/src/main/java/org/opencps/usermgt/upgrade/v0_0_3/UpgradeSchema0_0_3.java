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

package org.opencps.usermgt.upgrade.v0_0_3;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.opencps.usermgt.upgrade.v0_0_1.util.ApplicantDataTable;
import org.opencps.usermgt.upgrade.v0_0_1.util.FileItemTable;
import org.opencps.usermgt.upgrade.v0_0_1.util.SavePickFieldTable;
import org.opencps.usermgt.upgrade.v0_0_1.util.SyncSchedulerTable;
import org.opencps.usermgt.upgrade.v0_0_1.util.TrackClientStatisticTable;
import org.opencps.usermgt.upgrade.v0_0_1.util.TrackClientTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema0_0_3 extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema0_0_3.class.getName());

	@Override
	protected void doUpgrade() throws Exception {

		upgrade(new UpgradeMVCCVersion());

		if (!hasTable(FileItemTable.TABLE_NAME)) {

			runSQL(FileItemTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(FileItemTable.TABLE_NAME, FileItemTable.TABLE_COLUMNS, FileItemTable.TABLE_SQL_CREATE,
					FileItemTable.TABLE_SQL_ADD_INDEXES);
		}

		if (!hasTable(ApplicantDataTable.TABLE_NAME)) {

			runSQL(ApplicantDataTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(ApplicantDataTable.TABLE_NAME, ApplicantDataTable.TABLE_COLUMNS, ApplicantDataTable.TABLE_SQL_CREATE,
					ApplicantDataTable.TABLE_SQL_ADD_INDEXES);
		}

		if (!hasTable(SavePickFieldTable.TABLE_NAME)) {

			runSQL(SavePickFieldTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(SavePickFieldTable.TABLE_NAME, SavePickFieldTable.TABLE_COLUMNS, SavePickFieldTable.TABLE_SQL_CREATE,
					SavePickFieldTable.TABLE_SQL_ADD_INDEXES);
		}

		if (!hasTable(SyncSchedulerTable.TABLE_NAME)) {

			runSQL(SyncSchedulerTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(SyncSchedulerTable.TABLE_NAME, SyncSchedulerTable.TABLE_COLUMNS, SyncSchedulerTable.TABLE_SQL_CREATE,
					SyncSchedulerTable.TABLE_SQL_ADD_INDEXES);
		}

		if (!hasTable(TrackClientStatisticTable.TABLE_NAME)) {

			runSQL(TrackClientStatisticTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(TrackClientStatisticTable.TABLE_NAME, TrackClientStatisticTable.TABLE_COLUMNS, TrackClientStatisticTable.TABLE_SQL_CREATE,
					TrackClientStatisticTable.TABLE_SQL_ADD_INDEXES);
		}

		if (!hasTable(TrackClientTable.TABLE_NAME)) {

			runSQL(TrackClientTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(TrackClientTable.TABLE_NAME, TrackClientTable.TABLE_COLUMNS, TrackClientTable.TABLE_SQL_CREATE,
					TrackClientTable.TABLE_SQL_ADD_INDEXES);
		}

		

	}

}