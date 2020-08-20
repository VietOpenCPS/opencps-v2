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

package org.opencps.dossiermgt.upgrade.v0_0_1;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.opencps.dossiermgt.upgrade.v0_0_1.util.BookingTable;
import org.opencps.dossiermgt.upgrade.v0_0_1.util.DossierTable;
import org.opencps.dossiermgt.upgrade.v0_0_1.util.DossierTemplateTable;
import org.opencps.dossiermgt.upgrade.v0_0_1.util.ServiceInfoTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());
		alter(
				DossierTable.class, 
				new AlterTableAddColumn("jobPosTitle VARCHAR(75) null"),
				new AlterTableAddColumn("vnpostalStatus INTEGER"),
				new AlterTableAddColumn("vnpostalProfile TEXT null"),
				new AlterTableAddColumn("fromViaPostal INTEGER"),
				new AlterTableAddColumn("multipleCheck VARCHAR(75) null"),
				new AlterTableAddColumn("postalCodeSend VARCHAR(75) null"),
				new AlterTableAddColumn("postalCodeReceived VARCHAR(75) null"),
				new AlterColumnType("groupDossierId","VARCHAR(75) null")
		);
		
		alter(
				ServiceInfoTable.class, 
				new AlterTableAddColumn("serviceNameTitle VARCHAR(500) null"),
				new AlterTableAddColumn("isNotarization BOOLEAN"));
		alter(DossierTemplateTable.class, new AlterTableAddColumn("formMeta TEXT null"));
		alter(
				BookingTable.class, 
				new AlterTableAddColumn("online_ BOOLEAN"),
				new AlterTableAddColumn("bookingInTime VARCHAR(255) null"),
				new AlterTableAddColumn("telNo VARCHAR(128) null"));

	}

}