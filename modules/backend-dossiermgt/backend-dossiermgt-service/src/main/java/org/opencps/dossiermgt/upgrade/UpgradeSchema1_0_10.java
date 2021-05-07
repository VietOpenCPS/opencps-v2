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

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import org.opencps.dossiermgt.upgrade.table.BookingTable;
import org.opencps.dossiermgt.upgrade.table.DossierTable;
import org.opencps.dossiermgt.upgrade.table.EformTable;
import org.opencps.dossiermgt.upgrade.table.PaymentFileTable;
import org.opencps.dossiermgt.upgrade.table.ProcessOptionTable;

/**
 * @author nhanhoang
 */
public class UpgradeSchema1_0_10 extends UpgradeProcess {
	private Log _log = LogFactoryUtil.getLog(UpgradeSchema1_0_10.class.getName());
	
	
	@Override
	protected void doUpgrade() throws Exception {
		
		upgrade(new UpgradeMVCCVersion());
		
		if(!hasColumn(ProcessOptionTable.TABLE_NAME, "forCitizen")){
			
			alter(ProcessOptionTable.class, new AlterTableAddColumn("forCitizen BOOLEAN"));
			updateColumn(ProcessOptionTable.TABLE_NAME, "forCitizen", "BOOLEAN", "1");
					
		}
		if(!hasColumn(ProcessOptionTable.TABLE_NAME, "forBusiness")){
			alter(ProcessOptionTable.class, new AlterTableAddColumn("forBusiness BOOLEAN"));
			updateColumn(ProcessOptionTable.TABLE_NAME, "forBusiness", "BOOLEAN", "1");
					
		}
		if(!hasColumn(EformTable.TABLE_NAME, "govAgencyCode")){
			alter(EformTable.class, new AlterTableAddColumn("govAgencyCode VARCHAR(75) null"));
					
		}
		if(!hasColumn(BookingTable.TABLE_NAME, "govAgencyCode")){
			alter(BookingTable.class, new AlterTableAddColumn("govAgencyCode VARCHAR(75) null"));
					
		}

	}
	
	protected void updateColumn(
			String tableName, String columnName, String dataType, String data)
		throws Exception {

//		if (hasColumn(tableName, columnName)) {
//			return;
//		}
//
//		String dataTypeUpperCase = StringUtil.toUpperCase(dataType);

		StringBundler sb = new StringBundler(6);

//		sb.append("alter table ");
//		sb.append(tableName);
//		sb.append(" add ");
//		sb.append(columnName);
//		sb.append(StringPool.SPACE);
//		sb.append(dataTypeUpperCase);
//
//		String sql = sb.toString();
//
//		if (dataTypeUpperCase.equals("DATE") || dataType.equals("STRING")) {
//			sql = sql.concat(" null");
//		}
//
//		runSQL(sql);

		sb.setIndex(0);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");
		sb.append(columnName);
		sb.append(" = ");
		sb.append(data);

		runSQL(sb.toString());
	}


}