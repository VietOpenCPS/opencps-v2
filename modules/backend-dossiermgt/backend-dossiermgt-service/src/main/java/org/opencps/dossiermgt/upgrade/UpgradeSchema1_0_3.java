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
import com.liferay.portal.kernel.upgrade.UpgradeProcess.AlterTableAddColumn;

import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.upgrade.table.BookingTable;
import org.opencps.dossiermgt.upgrade.table.DossierPartTable;
import org.opencps.dossiermgt.upgrade.table.DossierTable;
import org.opencps.dossiermgt.upgrade.table.DossierTemplateTable;
import org.opencps.dossiermgt.upgrade.table.ServiceInfoTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema1_0_3 extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema1_0_3.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());
		
		if(!hasColumn(DossierPartTable.TABLE_NAME, "partNameTitle")){
			
			alter(DossierPartTable.class, new AlterTableAddColumn("partNameTitle VARCHAR(500) null"));
					
		}

	}

}