package org.opencps.backend.statisticmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.constant.PropValues;
import org.opencps.backend.statisticmgt.processimpl.QueryProcessFactoryImpl;
import org.opencps.bundlemgt.classloader.util.ClassLoaderFactoryUtil;

/**
 * @author trungnt
 *
 */
public class ActionUtil {

	private static Log _log = LogFactoryUtil.getLog(ActionUtil.class);

	public static String exportDossierStatistic(long groupId, long fromDate, long toDate, String originalities,
			String domainCode, String govAgencyCode, String serviceCode, String dossierStatus, Integer day,
			String groupBy, Integer start, Integer end, int type, String subType) {

		String result = StringPool.BLANK;

		JSONObject obj = getDossierStatistic(groupId, fromDate, toDate, originalities, domainCode, govAgencyCode,
				serviceCode, dossierStatus, day, groupBy, start, end, type, subType);
		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._YYYY_MM_DD);

		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._YYYY_MM_DD);
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("currentDay", String.valueOf(DatetimeUtil.getCurrentDay()));
		dataMap.put("currentMonth", String.valueOf(DatetimeUtil.getCurrentMonth()));
		dataMap.put("currentYear", String.valueOf(DatetimeUtil.getCurrentYear()));
		dataMap.put("fromDate", strFromDate);
		dataMap.put("toDate", strToDate);
		String govAgencyName = StringPool.BLANK;
		String domainName = StringPool.BLANK;

		int total = 0;

		List<Object[]> dataList = new ArrayList<Object[]>();

		if (obj != null && obj.has(Constants.DATA)) {
			JSONArray data = obj.getJSONArray(Constants.DATA);
			if (data != null && data.length() > 0) {
				JSONObject firstRow = data.getJSONObject(0);
				total = data.length();
				govAgencyName = firstRow.getString("govAgencyName");
				domainName = firstRow.getString("domainName");
				dataMap.put("govAgencyName", govAgencyName.toUpperCase());
				dataMap.put("domainName", domainName);
				dataMap.put("total", String.valueOf(total));

				Object[] objects = null;

				JSONObject dataRow = null;
				for (int i = 0; i < data.length(); i++) {
					objects = new Object[firstRow.length()];
					dataRow = data.getJSONObject(i);
					objects[0] = String.valueOf(i + 1);
					objects[1] = dataRow.getString("dossierNo");
					objects[2] = dataRow.getString("dossierName");
					objects[3] = dataRow.getString("applicantName");
					objects[4] = dataRow.getString("receiveDate");
					objects[5] = dataRow.getString("dueDate");
					objects[6] = dataRow.getString("dossierStatusText");

					dataList.add(objects);
				}

				dataMap.put("data", dataList);
			}
		}

		try {
			result = ClassLoaderFactoryUtil.exportFileByExcelTemplate(
					StatisticUtil.getTemplateFilePath(PropValues.TEMPLATES_REPORTS_FILENAME_1), dataMap);
		} catch (Exception e) {
			_log.error(e);
		}

		_log.info("=========> Result " + result);
		
		return result;
	}

	public static JSONObject getDossierStatistic(long groupId, long fromDate, long toDate, String originalities,
			String domainCode, String govAgencyCode, String serviceCode, String dossierStatus, Integer day,
			String groupBy, Integer start, Integer end, int type, String subType) {

		if (start == null) {
			start = 0;
		}

		if (end == null) {
			end = PropValues.CONFIG_DOSSIER_STATISTIC_SIZE_LIST;
		}

		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._YYYY_MM_DD);

		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._YYYY_MM_DD);

		String sqlTemplate = QueryUtil.getSQLQueryTemplate(type, subType);

		if (Validator.isNull(sqlTemplate)) {
			return StatisticUtil.createResponseSchema(groupId, strFromDate, strToDate,
					ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
					ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
					ParamUtil.getArrayParams(dossierStatus), type, subType);
		}

		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();

		try {

			if (type == 1) {
				return StatisticUtil.createResponseSchema(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), type, subType);
			} else if (type == 2) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = String.valueOf(3);
				}

				/*
				 * if (Validator.isNull(dossierStatus)) { dossierStatus =
				 * "processing, planning"; }
				 */
				return factory.getDossierStatistic2(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 3) {
				return StatisticUtil.createResponseSchema(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), type, subType);
			} else if (type == 4) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = String.valueOf(2);
				}
				/*
				 * if (Validator.isNull(dossierStatus)) { dossierStatus =
				 * "processing, planning"; }
				 */
				return factory.getDossierStatistic4(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 5) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}
				/*
				 * if (Validator.isNull(dossierStatus)) { dossierStatus = "done"; }
				 */
				return factory.getDossierStatistic5(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 6) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}
				/*
				 * if (Validator.isNull(dossierStatus)) { dossierStatus = "done"; }
				 */
				return factory.getDossierStatistic6(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 7) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}
				/*
				 * if (Validator.isNull(dossierStatus)) { dossierStatus = "done"; }
				 */
				return factory.getDossierStatistic7(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 8) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}
				/*
				 * if (Validator.isNull(dossierStatus)) { dossierStatus = "done"; }
				 */
				return factory.getDossierStatistic8(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 9) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "releasing,posting,done";
				}
				return factory.getDossierStatistic9(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 10) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "releasing,posting,done";
				}
				return factory.getDossierStatistic10(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 11) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "releasing,posting,done";
				}
				return factory.getDossierStatistic11(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 12) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "releasing,posting,done";
				}
				return factory.getDossierStatistic12(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 13) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "processing,interoperating,planning";
				}

				return factory.getDossierStatistic13(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 14) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "processing,interoperating,planning";
				}
				return factory.getDossierStatistic14(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 15) {
				// day
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "processing,interoperating,planning";
				}
				return factory.getDossierStatistic15(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), day, groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 16) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "processing,interoperating,planning";
				}
				return factory.getDossierStatistic16(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 17) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "unresolved";
				}
				return factory.getDossierStatistic17(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 18) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "cancelled";
				}
				return factory.getDossierStatistic18(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 19) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}

				if (Validator.isNull(dossierStatus)) {
					dossierStatus = "waiting,receiving";
				}

				return factory.getDossierStatistic19(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else if (type == 20) {
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}
				return factory.getDossierStatistic20(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), groupBy, start, end, sqlTemplate, type, subType);
			} else {
				return StatisticUtil.createResponseSchema(groupId, strFromDate, strToDate,
						ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
						ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
						ParamUtil.getArrayParams(dossierStatus), type, subType);
			}
		} catch (Exception e) {
			_log.error(e);

			return StatisticUtil.createResponseSchema(groupId, strFromDate, strToDate,
					ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
					ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
					ParamUtil.getArrayParams(dossierStatus), type, subType, new String[] { e.getMessage() });
		}

	}

}
