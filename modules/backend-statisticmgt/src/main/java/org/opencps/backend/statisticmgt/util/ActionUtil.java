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

		String sqlTemplate = QueryType.getSQLQueryTemplate(type);

		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();

		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._YYYY_MM_DD);

		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._YYYY_MM_DD);

		try {
			if (Validator.isNull(sqlTemplate)) {
				_log.info("Can't get sqltemplate width type = " + type);
				return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
						ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type);
			}

			if (type == 1) {
				return factory.getDossierCount1(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 2) {
				return factory.getDossierCount2(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 3) {
				return factory.getDossierCount3(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 4) {
				return factory.getDossierCount4(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 5) {
				return factory.getDossierCount5(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 6) {
				return factory.getDossierCount6(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 7) {
				return factory.getDossierCount7(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 8) {
				return factory.getDossierCount8(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 9) {
				return factory.getDossierCount9(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 10) {
				return factory.getDossierCount10(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 11) {
				return factory.getDossierCount11(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 12) {
				return factory.getDossierCount12(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 13) {
				return factory.getDossierCount13(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 14) {
				return factory.getDossierCount14(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 15) {
				return factory.getDossierCount15(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, day);
			} else if (type == 16) {
				return factory.getDossierCount16(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 17) {
				return factory.getDossierCount17(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 18) {
				return factory.getDossierCount18(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
			} else if (type == 19) {
				return factory.getDossierCount19(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate);
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
	
	public static JSONObject getListDossier (long groupId, long fromDate, long toDate, String originalities,
			String domainCode, String dossierStatus, int type, Integer day, Integer start, Integer end) {
		String sqlTemplate = QueryType.getSQLQueryTemplate(type);

		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();

		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._YYYY_MM_DD);

		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._YYYY_MM_DD);
		try {
			if (Validator.isNull(sqlTemplate)) {
				_log.info("Can't get sqltemplate width type = " + type);
				return createResponseSchema(groupId, strFromDate, strToDate, ParamUtil.getArrayParams(originalities, 0),
						ParamUtil.getArrayParams(domainCode), ParamUtil.getArrayParams(dossierStatus), type);
			}
			if (type == 20) {
				return factory.getDossierList1(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 21) {
				return factory.getDossierList2(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 22) {
				return factory.getDossierList3(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 23) {
				return factory.getDossierList4(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 24) {
				return factory.getDossierList5(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 25) {
				return factory.getDossierList6(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 26) {
				return factory.getDossierList7(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 27) {
				return factory.getDossierList8(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 28) {
				return factory.getDossierList9(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 29) {
				return factory.getDossierList10(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 30) {
				return factory.getDossierList11(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 31) {
				return factory.getDossierList12(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 32) {
				return factory.getDossierList13(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end, day);
			} else if (type == 33) {
				return factory.getDossierList14(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 34) {
				return factory.getDossierList15(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 35) {
				return factory.getDossierList16(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 36) {
				return factory.getDossierList17(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
			} else if (type == 37) {
				return factory.getDossierList18(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(dossierStatus), sqlTemplate, start, end);
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
