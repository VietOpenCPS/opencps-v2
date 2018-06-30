package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;

import com.liferay.portal.kernel.service.ServiceContext;

public interface StatisticActions {

	public long countTodoTest(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params, Object object,
			ServiceContext serviceContext);

}
