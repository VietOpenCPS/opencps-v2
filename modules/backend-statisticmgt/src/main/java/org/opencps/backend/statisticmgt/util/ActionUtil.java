package org.opencps.backend.statisticmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
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

		_log.info("Type: " + type);
		JSONObject obj = getDossierStatistic(groupId, fromDate, toDate, originalities, domainCode, govAgencyCode,
				serviceCode, dossierStatus, day, groupBy, start, end, type, subType);
		String strFromDate = DatetimeUtil.convertTimestampToStringDatetime(fromDate, DatetimeUtil._DD_MM_YYYY);
		String strToDate = DatetimeUtil.convertTimestampToStringDatetime(toDate, DatetimeUtil._DD_MM_YYYY);
		String reportName = StringPool.BLANK;

		switch (type) {
		case 2:
			reportName = "HỒ SƠ TIẾP NHẬN TRỰC TIẾP";
			break;
		case 4:
			reportName = "HỒ SƠ TIẾP NHẬN TRỰC TUYẾN";
			break;
		case 5:
			reportName = "HỒ SƠ ĐÃ TRẢ KẾT QUẢ";
			break;
		case 9:
			reportName = "HỒ SƠ HOÀN THÀNH GIẢI QUYẾT";
			break;
		case 10:
			reportName = "HỒ SƠ HOÀN THÀNH SỚM HẠN";
			break;
		case 11:
			reportName = "HỒ SƠ HOÀN THÀNH ĐÚNG HẠN";
			break;
		case 12:
			reportName = "HỒ SƠ HOÀN THÀNH TRỄ HẠN";
			break;
		case 17:
			reportName = "HỒ SƠ TỪ CHỐI GIẢI QUYẾT";
			break;
		case 18:
			reportName = "HỒ SƠ RÚT KHÔNG GIẢI QUYẾT";
			break;
		case 13:
			reportName = "HỒ SƠ ĐANG THỤ LÝ GIẢI QUYẾT";
			break;
		case 14:
			reportName = "HỒ SƠ ĐANG GIẢI QUYẾT CÒN HẠN";
			break;
		case 15:
			reportName = "HỒ SƠ ĐANG GIẢI QUYẾT SẮP ĐẾN HẠN";
			break;
		case 16:
			reportName = "HỒ SƠ ĐANG GIẢI QUYẾT QUÁ HẠN";
			break;
		case 19:
			reportName = "HỒ SƠ TẠM DỪNG BỔ SUNG ĐIỀU KIỆN";
			break;
		default:
			break;
		}

		HashMap<String, Object> dataMap = new HashMap<String, Object>();

		int currentDay = DatetimeUtil.getCurrentDay();
		int currentMonth = DatetimeUtil.getCurrentMonth();
		dataMap.put("currentDay", currentDay >= 10 ? String.valueOf(currentDay) : "0" + currentDay);
		dataMap.put("currentMonth",
				currentMonth >= 10 ? String.valueOf(DatetimeUtil.getCurrentMonth()) : "0" + currentMonth);
		dataMap.put("currentYear", String.valueOf(DatetimeUtil.getCurrentYear()));
		dataMap.put("fromDate", strFromDate);
		dataMap.put("toDate", strToDate);
		dataMap.put("reportName", reportName);
		String govAgencyName = StringPool.BLANK;
		String domainName = StringPool.BLANK;

		String keyVotting = "votting";
		if (subType.equals(Constants.LIST)) {

			int totalCount = 0;

			List<Object[]> dataList = new ArrayList<Object[]>();

			if (obj != null && obj.has(Constants.DATA)) {
				JSONArray data = obj.getJSONArray(Constants.DATA);
				if (data != null && data.length() > 0) {
					JSONObject firstRow = data.getJSONObject(0);
					totalCount = data.length();

					govAgencyName = firstRow.getString("govAgencyName");
					domainName = firstRow.getString("domainName");
					JSONObject objA = getDossierStatistic(groupId, fromDate, toDate, originalities, domainCode, govAgencyCode,
							serviceCode, dossierStatus, day, "serviceCode", start, end, type, Constants.GROUP_COUNT);
					_log.info("objA: " + JSONFactoryUtil.looseSerialize(objA));

					dataMap.put("govAgencyName", govAgencyName.toUpperCase());
					dataMap.put("domainName", domainName);
					dataMap.put("total", String.valueOf(totalCount));

					Object[] objects = null;

					JSONObject dataRow = null;

					if("votting".equals(keyVotting)){
						for (int i = 0; i < data.length(); i++) {
							objects = new Object[firstRow.length()];
							dataRow = data.getJSONObject(i);
							objects[0] = String.valueOf(i + 1);
							objects[1] = dataRow.getString("dossierNo");
							objects[2] = dataRow.getString("vote1");
							objects[3] = dataRow.getString("vote2");
							objects[4] = dataRow.getString("vote3");
							objects[5] = dataRow.getString("vote4");
							objects[6] = dataRow.getString("vote5");
							objects[7] = dataRow.getString("vote6");
							objects[8] = dataRow.getString("vote7");
							objects[9] = dataRow.getString("vote8");
							objects[10] = dataRow.getString("vote9");

							dataList.add(objects);
						}

						dataMap.put("data", dataList);
						try {
							result = ClassLoaderFactoryUtil.exportFileByExcelTemplate(
									StatisticUtil.getTemplateFilePath(PropValues.TEMPLATES_REPORTS_FILENAME_4), dataMap);
						} catch (Exception e) {
							_log.error(e);
						}

					}else {
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
						try {
							result = ClassLoaderFactoryUtil.exportFileByExcelTemplate(
									StatisticUtil.getTemplateFilePath(PropValues.TEMPLATES_REPORTS_FILENAME_1), dataMap);
						} catch (Exception e) {
							_log.error(e);
						}
					}
				}
			}



		} else if (subType.equals(Constants.GROUP_COUNT)) {

			Group group = GroupLocalServiceUtil.fetchGroup(groupId);

			if (Validator.isNotNull(group)) {

				govAgencyName = group.getGroupKey();

			}

			String[] total = new String[11];

			List<Object[]> dataList = new ArrayList<Object[]>();

			if (obj != null && obj.has(Constants.DATA)) {
				JSONArray data = obj.getJSONArray(Constants.DATA);
				if (data != null && data.length() > 0) {

					JSONObject lastRow = data.getJSONObject(data.length() - 1);

					int receivedCount = lastRow.getInt("receivedCount");// 5
					int remainingCount = lastRow.getInt("remainingCount");// 4
					int betimesCount = lastRow.getInt("betimesCount");// 7
					int ontimeCount = lastRow.getInt("ontimeCount");// 8
					int overtimeCount = lastRow.getInt("overtimeCount");// 9
					int undueCount = lastRow.getInt("undueCount");// 11
					int overdueCount = lastRow.getInt("overdueCount");// 12

					total[0] = String.valueOf((remainingCount + receivedCount));// 4+5
					total[1] = String.valueOf(remainingCount);// 4
					total[2] = String.valueOf(receivedCount);// 5
					total[3] = String.valueOf(betimesCount + ontimeCount + overtimeCount);// 7 +8 +9
					total[4] = String.valueOf(betimesCount);// 7
					total[5] = String.valueOf(ontimeCount);// 8
					total[6] = String.valueOf(overtimeCount);// 9
					total[7] = String.valueOf(undueCount + overdueCount);// 11+12
					total[8] = String.valueOf(undueCount);// 11
					total[9] = String.valueOf(overdueCount);// 12
					total[10] = String.valueOf(overtimeCount);// 9
					// govAgencyName = firstRow.getString("govAgencyName");

					dataMap.put("govAgencyName", govAgencyName.toUpperCase());

					dataMap.put("total", total);

					Object[] objects = null;

					JSONObject dataRow = null;

					for (int i = 0; i < data.length() - 1; i++) {
						objects = new Object[13];
						dataRow = data.getJSONObject(i);

						receivedCount = dataRow.getInt("receivedCount");// 5
						remainingCount = dataRow.getInt("remainingCount");// 4
						betimesCount = dataRow.getInt("betimesCount");// 7
						ontimeCount = dataRow.getInt("ontimeCount");// 8
						overtimeCount = dataRow.getInt("overtimeCount");// 9
						undueCount = dataRow.getInt("undueCount");// 11
						overdueCount = dataRow.getInt("overdueCount");// 12

						objects[0] = String.valueOf(i + 1);
						if (groupBy.equalsIgnoreCase("serviceCode")) {
							objects[1] = dataRow.getString("serviceName");
						} else {
							objects[1] = dataRow.getString("domainName");
						}

						objects[2] = String.valueOf((remainingCount + receivedCount));// 4+5
						objects[3] = String.valueOf(remainingCount);// 4
						objects[4] = String.valueOf(receivedCount);// 5
						objects[5] = String.valueOf(betimesCount + ontimeCount + overtimeCount);// 7 +8 +9
						objects[6] = String.valueOf(betimesCount);// 7

						objects[7] = String.valueOf(ontimeCount);// 8

						objects[8] = String.valueOf(overtimeCount);// 9
						objects[9] = String.valueOf(undueCount + overdueCount);// 11+12
						objects[10] = String.valueOf(undueCount);// 11
						objects[11] = String.valueOf(overdueCount);// 12
						objects[12] = String.valueOf(overtimeCount);// 9

						dataList.add(objects);
					}

					dataMap.put("data", dataList);
				}
			}

			try {
				if (groupBy.equalsIgnoreCase("serviceCode")) {
					result = ClassLoaderFactoryUtil.exportFileByExcelTemplate(
							StatisticUtil.getTemplateFilePath(PropValues.TEMPLATES_REPORTS_FILENAME_3), dataMap);
				} else {
					result = ClassLoaderFactoryUtil.exportFileByExcelTemplate(
							StatisticUtil.getTemplateFilePath(PropValues.TEMPLATES_REPORTS_FILENAME_2), dataMap);
				}

			} catch (Exception e) {
				_log.error(e);
			}

		}

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
		_log.info("SQL Template : " + sqlTemplate);
		if (Validator.isNull(sqlTemplate)) {
			return StatisticUtil.createResponseSchema(groupId, strFromDate, strToDate,
					ParamUtil.getArrayParams(originalities, 0), ParamUtil.getArrayParams(domainCode),
					ParamUtil.getArrayParams(govAgencyCode), ParamUtil.getArrayParams(serviceCode),
					ParamUtil.getArrayParams(dossierStatus), type, subType);
		}

		_log.info("TYpe: " + type);

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
			}else if (type == 21){
				// set default
				if (Validator.isNull(originalities)) {
					originalities = "2,3";
				}
				return factory.getDossierStatistic21(groupId, strFromDate, strToDate,
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
