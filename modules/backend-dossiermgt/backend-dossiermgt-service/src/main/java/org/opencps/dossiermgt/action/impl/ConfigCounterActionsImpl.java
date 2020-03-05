package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.opencps.dossiermgt.action.ConfigCounterActions;
import org.opencps.dossiermgt.model.ConfigCounter;
import org.opencps.dossiermgt.service.BookingLocalServiceUtil;
import org.opencps.dossiermgt.service.ConfigCounterLocalServiceUtil;

public class ConfigCounterActionsImpl implements ConfigCounterActions{

	private static final Log _log = LogFactoryUtil.getLog(ConfigCounterActionsImpl.class);

	@Override
	public ConfigCounter updateConfigCounter(long groupId, long userId, long counterConfigId, String counterCode,
			String patternCode, int startCounter, ServiceContext serviceContext) {

		return ConfigCounterLocalServiceUtil.updateConfigCounter(groupId, userId, counterConfigId, counterCode, patternCode,
				startCounter, serviceContext);
	}

	@Override
	public JSONObject getConfigCounterList(long groupId, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {

			List<ConfigCounter> configList = ConfigCounterLocalServiceUtil.getByGroupId(groupId, start, end);
			if (configList != null) {
				result.put("data", configList);

				long total = ConfigCounterLocalServiceUtil.countByGroupId(groupId);

				result.put("total", total);
			} else {
				result.put("total", 0l);
			}

		} catch (Exception e) {
			_log.debug(e);
		}

		return result;
	}

}
