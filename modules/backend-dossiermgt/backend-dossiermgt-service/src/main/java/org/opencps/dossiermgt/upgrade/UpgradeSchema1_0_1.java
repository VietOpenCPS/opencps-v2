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

import org.opencps.dossiermgt.upgrade.table.BookingTable;
import org.opencps.dossiermgt.upgrade.table.DossierTable;
import org.opencps.dossiermgt.upgrade.table.DossierTemplateTable;
import org.opencps.dossiermgt.upgrade.table.ServiceInfoTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema1_0_1 extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema1_0_1.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());
		alter(
				DossierTable.class,
				new AlterColumnType("groupDossierId","VARCHAR(75) null")
		);
		
		if(!hasColumn(DossierTable.TABLE_NAME, "jobPosTitle")){
					
			alter(DossierTable.class,new AlterTableAddColumn("jobPosTitle VARCHAR(75) null"));
					
		}
		
		if(!hasColumn(DossierTable.TABLE_NAME, "vnpostalStatus")){
			
			alter(DossierTable.class,new AlterTableAddColumn("vnpostalStatus INTEGER"));
			
		}
		
		if(!hasColumn(DossierTable.TABLE_NAME, "vnpostalProfile")){
			
			alter(DossierTable.class,new AlterTableAddColumn("vnpostalProfile TEXT nul"));
			
		}
		
		if(!hasColumn(DossierTable.TABLE_NAME, "multipleCheck")){
			
			alter(DossierTable.class,new AlterTableAddColumn("multipleCheck VARCHAR(75) null"));
			
		}
		
		if(!hasColumn(DossierTable.TABLE_NAME, "postalCodeSend")){
			
			alter(DossierTable.class,new AlterTableAddColumn("postalCodeSend VARCHAR(75) null"));
			
		}
		
		if(!hasColumn(DossierTable.TABLE_NAME, "postalCodeReceived")){
			
			alter(DossierTable.class,new AlterTableAddColumn("postalCodeReceived VARCHAR(75) null"));
			
		}
		
		if(!hasColumn(DossierTable.TABLE_NAME, "serviceNameTitle")){
			
			alter(DossierTable.class,new AlterTableAddColumn("serviceNameTitle VARCHAR(500) null"));
			
		}
		
		if(!hasColumn(ServiceInfoTable.TABLE_NAME, "serviceNameTitle")){
			
			alter(ServiceInfoTable.class,new AlterTableAddColumn("serviceNameTitle VARCHAR(500) null"));
			
		}
		
		if(!hasColumn(ServiceInfoTable.TABLE_NAME, "isNotarization")){
			
			alter(ServiceInfoTable.class,new AlterTableAddColumn("isNotarization BOOLEAN"));
			
		}
		
		if(!hasColumn(DossierTemplateTable.TABLE_NAME, "formMeta")){
					
			alter(DossierTemplateTable.class, new AlterTableAddColumn("formMeta TEXT null"));
					
		}
		
		if(!hasColumn(BookingTable.TABLE_NAME, "online_")){
			
			alter(BookingTable.class,new AlterTableAddColumn("online_ BOOLEAN"));
			
		}
		
		if(!hasColumn(BookingTable.TABLE_NAME, "bookingInTime")){
			
			alter(BookingTable.class,new AlterTableAddColumn("bookingInTime VARCHAR(255) null"));
			
		}
		
		if(!hasColumn(BookingTable.TABLE_NAME, "telNo")){
			
			alter(BookingTable.class,new AlterTableAddColumn("telNo VARCHAR(128) null"));
			
		}
		

	}

}