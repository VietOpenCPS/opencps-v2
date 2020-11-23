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

import org.opencps.dossiermgt.upgrade.table.NewsBoardTable;
import org.opencps.dossiermgt.upgrade.table.NotarizationTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema1_0_4 extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema1_0_4.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());

		alter(NotarizationTable.class);
		
		if(!hasTable(NotarizationTable.TABLE_NAME)) {
			runSQL(NotarizationTable.TABLE_SQL_CREATE);
			
		}
		
		if (!hasTable(NotarizationTable.TABLE_NAME)) {

			runSQL(NotarizationTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(NotarizationTable.TABLE_NAME, NotarizationTable.TABLE_COLUMNS, NotarizationTable.TABLE_SQL_CREATE,
					NotarizationTable.TABLE_SQL_ADD_INDEXES);
		}
		
		if (!hasTable(NewsBoardTable.TABLE_NAME)) {

			runSQL(NewsBoardTable.TABLE_SQL_CREATE);

		} else {
			upgradeTable(NewsBoardTable.TABLE_NAME, NewsBoardTable.TABLE_COLUMNS, NewsBoardTable.TABLE_SQL_CREATE,
					NewsBoardTable.TABLE_SQL_ADD_INDEXES);
		}

	}

}