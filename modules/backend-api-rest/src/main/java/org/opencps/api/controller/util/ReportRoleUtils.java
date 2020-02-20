package org.opencps.api.controller.util;

import org.opencps.adminconfig.model.ReportRole;
import org.opencps.api.reportrole.model.ReportRoleModel;

public class ReportRoleUtils {
	public static ReportRoleModel mappingReportRole(ReportRole rr) throws Exception {
		ReportRoleModel result = new ReportRoleModel();
		result.setDynamicReportId(rr.getDynamicReportId());
		result.setRoleId(rr.getRoleId());
		
		return result;
	}
}
