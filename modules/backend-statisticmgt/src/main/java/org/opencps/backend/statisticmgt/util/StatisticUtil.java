package org.opencps.backend.statisticmgt.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

import org.opencps.backend.statisticmgt.constant.Constants;

/**
 * @author trungnt
 *
 */
public class StatisticUtil {
	public static JSONObject createResponseSchema(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus, int type,
			String subType, String... msg) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONObject condition = JSONFactoryUtil.createJSONObject();

		condition.put(Constants.GROUPID, groupId);
		condition.put(Constants.FROM_DATE, fromDate);
		condition.put(Constants.TO_DATE, toDate);
		condition.put(Constants.ORIGINALITIES, StringUtil.merge(originalities));
		condition.put(Constants.DOMAIN_CODES, StringUtil.merge(domainCodes));
		condition.put(Constants.DOSSIER_STATUS, StringUtil.merge(dossierStatus));

		result.put(Constants.CONDITIONS, condition);
		result.put(Constants.TOTAL, 0);
		result.put(Constants.DATA, JSONFactoryUtil.createJSONArray());
		result.put(Constants.TYPE, type);
		result.put(Constants.SUBTYPE, subType);

		if (msg != null) {
			result.put(Constants.MESSAGES, StringUtil.merge(msg));
		}

		return result;
	}
}
