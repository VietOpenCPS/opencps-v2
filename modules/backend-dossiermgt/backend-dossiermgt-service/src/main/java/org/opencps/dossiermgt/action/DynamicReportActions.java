package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.exception.NoSuchUserException;

public interface DynamicReportActions {

	public void updateDynamicReportDB(long userId, long groupId, String reportCode, String reportName, Integer sharing,
			String filterConfig, String tableConfig, String userConfig, String reportType) throws NoSuchUserException;

}
