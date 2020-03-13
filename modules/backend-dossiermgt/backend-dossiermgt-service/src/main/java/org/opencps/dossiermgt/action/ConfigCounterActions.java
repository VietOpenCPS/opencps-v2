package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.dossiermgt.model.ConfigCounter;

public interface ConfigCounterActions {

	public ConfigCounter updateConfigCounter(long groupId, long userId, long configCounterId, String counterCode,
			String patternCode, int startCounter, ServiceContext serviceContext);

	public JSONObject getConfigCounterList(long groupId, int start, int end, ServiceContext serviceContext);

}
