package org.opencps.backend.statisticmgt.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.processimpl.QueryProcessFactoryImpl;
import org.opencps.backend.statisticmgt.util.QueryUtil.QueryType;

/**
 * @author trungnt
 *
 */
public class ActionUtil {

	private static Log _log = LogFactoryUtil.getLog(ActionUtil.class);

	public static JSONObject createResponseSchema(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, int type, String... msg) {
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

		if (msg != null) {
			result.put(Constants.MESSAGES, StringUtil.merge(msg));
		}

		return result;
	}

	public static JSONObject getCountDossier(long groupId, long fromDate, long toDate, String originalities,
			String domainCode, String dossierStatus, int type, Integer day) {

		String sqlCountTemplate = QueryType.getSQLCountQueryTemplate(type);

		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();

		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._YYYY_MM_DD);

		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._YYYY_MM_DD);

		try {
			if (Validator.isNull(sqlCountTemplate)) {
				_log.info("Can't get sqltemplate width type = " + type);
				return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
						ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type);
			}

			if (type == 1) {
				return factory.getDossierCount1(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 2) {
				return factory.getDossierCount2(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 3) {
				return factory.getDossierCount3(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 4) {
				return factory.getDossierCount4(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 5) {
				return factory.getDossierCount5(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 6) {
				return factory.getDossierCount6(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 7) {
				return factory.getDossierCount7(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 8) {
				return factory.getDossierCount8(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 9) {
				return factory.getDossierCount9(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 10) {
				return factory.getDossierCount10(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 11) {
				return factory.getDossierCount11(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 12) {
				return factory.getDossierCount12(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 13) {
				return factory.getDossierCount13(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 14) {
				return factory.getDossierCount14(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 15) {
				return factory.getDossierCount15(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate, day);
			} else if (type == 16) {
				return factory.getDossierCount16(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 17) {
				return factory.getDossierCount17(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 18) {
				return factory.getDossierCount18(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else if (type == 19) {
				return factory.getDossierCount19(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlCountTemplate);
			} else {
				return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
						ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type);
			}
		} catch (Exception e) {
			_log.error(e);

			return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
					ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type,
					new String[] { e.getMessage() });
		}

	}

	public static JSONObject getListDossier(long groupId, long fromDate, long toDate, String originalities,
			String domainCode, String dossierStatus, int type, Integer day, Integer start, Integer end) {

		String sqlSearchTemplate = QueryType.getSQLSearchQueryTemplate(type);

		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();

		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._YYYY_MM_DD);

		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._YYYY_MM_DD);
		try {
			if (Validator.isNull(sqlSearchTemplate)) {

				_log.info("Can't get sqltemplate width type = " + type);

				return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
						ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type);
			}

			JSONObject totalObj = null;

			if (type == 1) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;

				return factory.getDossierList1(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 2) {

				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus,
						QueryType.STATISTIC_DOSSIER_RECEIVING_OFFLINE_TOTAL_COUNT.getType(), day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;

				return factory.getDossierList2(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 3) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList3(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 4) {

				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus,
						QueryType.STATISTIC_DOSSIER_RECEIVING_ONLINE_TOTAL_COUNT.getType(), day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;

				return factory.getDossierList4(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 5) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList5(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 6) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList6(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 7) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList7(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 8) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList8(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 9) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList9(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 10) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList10(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 11) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList11(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 12) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList12(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 13) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList13(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 14) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList14(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 15) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList15(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end, day)
						.put(Constants.TOTAL, total);
			} else if (type == 16) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList16(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 17) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList17(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 18) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList18(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 19) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList19(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else if (type == 20) {
				totalObj = getCountDossier(groupId, fromDate, toDate, originalities, domainCode, dossierStatus, type,
						day);

				int total = (totalObj != null && totalObj.has(Constants.TOTAL)) ? totalObj.getInt(Constants.TOTAL) : 0;
				return factory.getDossierList20(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlSearchTemplate, start, end)
						.put(Constants.TOTAL, total);
			} else {
				return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
						ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type);
			}
		} catch (Exception e) {
			_log.error(e);

			return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
					ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type,
					new String[] { e.getMessage() });
		}
	}

}
