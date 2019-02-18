package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.exception.NoSuchUserException;

import org.opencps.dossiermgt.action.DynamicReportActions;
import org.opencps.dossiermgt.service.DynamicReportLocalServiceUtil;

public class DynamicReportActionsImpl implements DynamicReportActions{

	@Override
	public void updateDynamicReportDB(long userId, long groupId, String reportCode, String reportName, Integer sharing,
			String filterConfig, String tableConfig, String userConfig, String reportType) throws NoSuchUserException {

		DynamicReportLocalServiceUtil.updateDynamicReportDB(userId, groupId, reportCode, reportName, sharing,
				filterConfig, tableConfig, userConfig, reportType);
		
	}

}
