package org.opencps.backend.statisticmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.constant.PropKeys;
import org.opencps.backend.statisticmgt.constant.PropValues;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

/**
 * @author trungnt
 *
 */
public class QueryUtil {
	public static enum QueryType {
		STATISTIC_DOSSIER_RECEIVING_OFFLINE_TOTAL_COUNT(1),

		STATISTIC_DOSSIER_RECEIVING_OFFLINE_DOMAIN_COUNT(2),

		STATISTIC_DOSSIER_RECEIVING_ONLINE_TOTAL_COUNT(3),

		STATISTIC_DOSSIER_RECEIVING_ONLINE_DOMAIN_COUNT(4),

		STATISTIC_DOSSIER_RELEASED_TOTAL_COUNT(5),

		STATISTIC_DOSSIER_RELEASED_BETIMES_COUNT(6),

		STATISTIC_DOSSIER_RELEASED_ONTIME_COUNT(7),

		STATISTIC_DOSSIER_RELEASED_OVERTIME_COUNT(8),

		STATISTIC_DOSSIER_DONE_TOTAL_COUNT(9),

		STATISTIC_DOSSIER_DONE_BETIMES_COUNT(10),

		STATISTIC_DOSSIER_DONE_ONTIME_COUNT(11),

		STATISTIC_DOSSIER_DONE_OVERTIME_COUNT(12),

		STATISTIC_DOSSIER_PROCESSING_TOTAL_COUNT(13),

		STATISTIC_DOSSIER_PROCESSING_ONTIME_COUNT(14),

		STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_COUNT(15),

		STATISTIC_DOSSIER_PROCESSING_OVERTIME_COUNT(16),

		STATISTIC_DOSSIER_REJECT_TOTAL_COUNT(17),

		STATISTIC_DOSSIER_TAKEBACK_TOTAL_COUNT(18),

		STATISTIC_DOSSIER_PEDING_TOTAL_COUNT(19),

		STATISTIC_DOSSIER_RECEIVING_OFFLINE_LIST_DOSSIER(2),

		STATISTIC_DOSSIER_RECEIVING_ONLINE_LIST_DOSSIER(4),

		STATISTIC_DOSSIER_RELEASED_TOTAL_LIST(5),

		STATISTIC_DOSSIER_RELEASED_BETIMES_LIST(6),

		STATISTIC_DOSSIER_RELEASED_ONTIME_LIST(7),

		STATISTIC_DOSSIER_RELEASED_OVERTIME_LIST(8),

		STATISTIC_DOSSIER_DONE_TOTAL_LIST(9),

		STATISTIC_DOSSIER_DONE_BETIMES_LIST(10),

		STATISTIC_DOSSIER_DONE_ONTIME_LIST(11),

		STATISTIC_DOSSIER_DONE_OVERTIME_LIST(12),

		STATISTIC_DOSSIER_PROCESSING_TOTAL_LIST(13),

		STATISTIC_DOSSIER_PROCESSING_ONTIME_LIST(14),

		STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_LIST(15),

		STATISTIC_DOSSIER_PROCESSING_OVERTIME_LIST(16),

		STATISTIC_DOSSIER_REJECT_TOTAL_LIST(17),

		STATISTIC_DOSSIER_TAKEBACK_TOTAL_LIST(18),

		STATISTIC_DOSSIER_PEDING_TOTAL_LIST(19),

		STATISTIC_DOSSIER_DASHBROAD_TOTAL_COUNT(20),

		STATISTIC_DOSSIER_VOTING_TOTAL_LIST(21);

		private QueryType(int type) {
			this.type = type;

			switch (type) {
			case 1:
				this.sqlCountTemplate = StringPool.BLANK;
				this.sqlGroupTemplate = StringPool.BLANK;
				this.sqlSearchTemplate = StringPool.BLANK;

			case 2:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_RECEIVING_OFFLINE_TOTAL_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_RECEIVING_OFFLINE_GROUP_TOTAL_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_RECEIVING_OFFLINE_LIST_DOSSIER;

				break;

			case 3:
				this.sqlCountTemplate = StringPool.BLANK;
				this.sqlGroupTemplate = StringPool.BLANK;
				this.sqlSearchTemplate = StringPool.BLANK;
				break;

			case 4:
				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_RECEIVING_ONLINE_TOTAL_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_RECEIVING_ONLINE_GROUP_TOTAL_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_RECEIVING_ONLINE_LIST_DOSSIER;
				break;

			case 5:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_TOTAL_COUNT;
				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_GROUP_TOTAL_COUNT;
				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_TOTAL_LIST;

				break;

			case 6:
				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_BETIMES_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_GROUP_BETIMES_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_BETIMES_LIST;

				break;
			case 7:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_ONTIME_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_GROUP_ONTIME_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_ONTIME_LIST;

				break;
			case 8:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_OVERTIME_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_GROUP_OVERTIME_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_RELEASED_OVERTIME_LIST;

				break;
			case 9:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_DONE_TOTAL_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_DONE_GROUP_TOTAL_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_DONE_TOTAL_LIST;

				break;
			case 10:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_DONE_BETIMES_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_DONE_GROUP_BETIMES_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_DONE_BETIMES_LIST;

				break;
			case 11:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_DONE_ONTIME_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_DONE_GROUP_ONTIME_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_DONE_ONTIME_LIST;

				break;
			case 12:
				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_DONE_OVERTIME_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_DONE_GROUP_OVERTIME_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_DONE_OVERTIME_LIST;

				break;
			case 13:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_TOTAL_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_GROUP_TOTAL_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_TOTAL_LIST;

				break;
			case 14:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_ONTIME_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_GROUP_ONTIME_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_ONTIME_LIST;

				break;
			case 15:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_GROUP_NEAREXPIRED_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_LIST;

				break;
			case 16:
				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_OVERTIME_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_GROUP_OVERTIME_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_PROCESSING_OVERTIME_LIST;

				break;
			case 17:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_REJECT_TOTAL_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_REJECT_GROUP_TOTAL_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_REJECT_TOTAL_LIST;

				break;
			case 18:

				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_TAKEBACK_TOTAL_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_TAKEBACK_GROUP_TOTAL_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_TAKEBACK_TOTAL_LIST;

				break;
			case 19:
				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_PEDING_TOTAL_COUNT;

				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_PEDING_GROUP_TOTAL_COUNT;

				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_PEDING_TOTAL_LIST;

				break;
			case 20:
				this.sqlCountTemplate = StringPool.BLANK;
				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_DASHBROAD_GROUP_TOTAL_COUNT;
				this.sqlSearchTemplate = StringPool.BLANK;
				break;

			case 21:
				this.sqlCountTemplate = PropValues.STATISTIC_DOSSIER_VOTING_TOTAL_COUNT;
				this.sqlGroupTemplate = PropValues.STATISTIC_DOSSIER_VOTING_GROUP_TOTAL_COUNT;
				this.sqlRowTemplate = PropValues.STATISTIC_DOSSIER_VOTING_ROW_TOTAL_COUNT;
				this.sqlSearchTemplate = PropValues.STATISTIC_DOSSIER_VOTING_TOTAL_LIST;
				break;


			default:
				this.sqlCountTemplate = StringPool.BLANK;
				this.sqlGroupTemplate = StringPool.BLANK;
				this.sqlSearchTemplate = StringPool.BLANK;

				break;
			}

		}

		public int getType() {
			return type;
		}

		public static String getSQLCountQueryTemplate(int type) {
			for (QueryType e : values()) {
				if (e.type == type) {
					return e.sqlCountTemplate;
				}
			}
			return StringPool.BLANK;
		}

		public static String getSQLSearchQueryTemplate(int type) {
			for (QueryType e : values()) {
				if (e.type == type) {
					return e.sqlSearchTemplate;
				}
			}
			return StringPool.BLANK;
		}

		public static String getSQLRowTotalQueryTemplate(int type) {
			for (QueryType e : values()) {
				if (e.type == type) {
					return e.sqlRowTemplate;
				}
			}
			return StringPool.BLANK;
		}

		public static String getSQLGroupQueryTemplate(int type) {
			for (QueryType e : values()) {
				if (e.type == type) {
					return e.sqlGroupTemplate;
				}
			}
			return StringPool.BLANK;
		}

		String sqlCountTemplate;

		String sqlSearchTemplate;

		String sqlGroupTemplate;

		String sqlRowTemplate;

		int type;
	}

	public static int getCount(String sql, long userId, long groupId) {

		int count = 0;
		_log.debug("sqlTemplate: " + sql);
		if (userId > 0) {
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
			if (Validator.isNotNull(employee) && Validator.isNotNull(employee.getScope())) {
				String name = StringPool.BLANK;
				String[] scope = employee.getScope().split(StringPool.COMMA);
				for (String key : scope) {
					if (Validator.isNotNull(name)) {
						name += "," + key;
					} else {
						name = key;
					}
				}
				sql = sql.replace("{scopeEmpl}", StringPool.APOSTROPHE + name + StringPool.APOSTROPHE);
			} else {
				sql = sql.replace("{scopeEmpl}", "''");
			}
		}

		try (PreparedStatement pst = ConnectionUtil._getConnection().prepareStatement(sql)) {

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException sqle) {
				_log.warn(sqle.getMessage(), sqle);
			}
		} catch (SQLException sqle) {
			_log.warn(sqle.getMessage(), sqle);
			ConnectionUtil._getConnection();
		}

		return count;
	}

	public static JSONArray getData(String sql, LinkedHashMap<String, Class<?>> columns) {

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try (PreparedStatement pst = ConnectionUtil._getConnection().prepareStatement(sql)) {

			try (ResultSet rs = pst.executeQuery()) {

				JSONObject dataRow = null;

				while (rs.next()) {

					dataRow = JSONFactoryUtil.createJSONObject();

					for (Map.Entry<String, Class<?>> entry : columns.entrySet()) {
						String key = entry.getKey();
						Class<?> dataType = entry.getValue();
						String dataTypeName = dataType.getName();

						if (dataTypeName.equals(String.class.getName())) {
							dataRow.put(key, rs.getString(key));
						} else if (dataTypeName.equals(Long.class.getName())) {
							dataRow.put(key, rs.getLong(key));
						} else if (dataTypeName.equals(long.class.getName())) {
							dataRow.put(key, rs.getLong(key));
						} else if (dataTypeName.equals(Integer.class.getName())) {
							dataRow.put(key, rs.getInt(key));
						} else if (dataTypeName.equals(int.class.getName())) {
							dataRow.put(key, rs.getInt(key));
						} else if (dataTypeName.equals(Double.class.getName())) {
							dataRow.put(key, rs.getDouble(key));
						} else if (dataTypeName.equals(double.class.getName())) {
							dataRow.put(key, rs.getDouble(key));
						} else if (dataTypeName.equals(Float.class.getName())) {
							dataRow.put(key, rs.getFloat(key));
						} else if (dataTypeName.equals(float.class.getName())) {
							dataRow.put(key, rs.getFloat(key));
						} else if (dataTypeName.equals(Short.class.getName())) {
							dataRow.put(key, rs.getShort(key));
						} else if (dataTypeName.equals(short.class.getName())) {
							dataRow.put(key, rs.getShort(key));
						} else if (dataTypeName.equals(Boolean.class.getName())) {
							dataRow.put(key, rs.getBoolean(key));
						} else if (dataTypeName.equals(boolean.class.getName())) {
							dataRow.put(key, rs.getBoolean(key));
						} else if (dataTypeName.equals(Date.class.getName())) {
							dataRow.put(key,
									DatetimeUtil.convertDateToString(rs.getDate(key), DatetimeUtil._DD_MM_YYYY));
						} else {
							dataRow.put(key, rs.getString(key));
						}
					}
					data.put(dataRow);
				}
			} catch (SQLException sqle) {
				_log.error(sqle.getMessage() + "! - Configurate Debug log org.opencps.backend.statisticmgt.util.QueryUtil for more details!");
				_log.debug(sqle);
			}
		} catch (SQLException sqle) {
			_log.error(sqle.getMessage() + "! - Configurate Debug log org.opencps.backend.statisticmgt.util.QueryUtil for more details!");
			_log.debug(sqle);
			ConnectionUtil._getConnection();
		}

		return data;
	}

	public static LinkedHashMap<String, Class<?>> getDataColumnMap(String sqlTemplate) {
		LinkedHashMap<String, Class<?>> columns = new LinkedHashMap<String, Class<?>>();

		Pattern pattern = Pattern.compile("^select[ ](.*)[ ]from", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sqlTemplate);

		String select = StringPool.BLANK;

		while (matcher.find()) {
			select = matcher.group();
		}

		if (Validator.isNull(select)) {
			return columns;
		}

		pattern = Pattern.compile("([a-z0-9_]+|[A-Z0-9_]+|[0-9]+)(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		matcher = pattern.matcher(select);

		String group = StringPool.BLANK;

		while (matcher.find()) {
			group = matcher.group();

			String dataType = group.substring(group.lastIndexOf(StringPool.OPEN_BRACKET) + 1,
					group.lastIndexOf(StringPool.CLOSE_BRACKET));
			String columnName = group.substring(0, group.lastIndexOf(StringPool.OPEN_BRACKET));

			if (dataType.equalsIgnoreCase("string")) {
				columns.put(columnName, String.class);
			} else if (dataType.equalsIgnoreCase("long")) {
				columns.put(columnName, Long.class);
			} else if (dataType.equalsIgnoreCase("integer")) {
				columns.put(columnName, Integer.class);
			} else if (dataType.equalsIgnoreCase("int")) {
				columns.put(columnName, Integer.class);
			} else if (dataType.equalsIgnoreCase("double")) {
				columns.put(columnName, Double.class);
			} else if (dataType.equalsIgnoreCase("float")) {
				columns.put(columnName, Float.class);
			} else if (dataType.equalsIgnoreCase("short")) {
				columns.put(columnName, Short.class);
			} else if (dataType.equalsIgnoreCase("boolean")) {
				columns.put(columnName, Boolean.class);
			} else if (dataType.equalsIgnoreCase("date")) {
				columns.put(columnName, Date.class);
			} else {

			}
		}
		return columns;
	}

	public static int[] getPageAndSize(Integer start, Integer end) {

		int size = 0;

		if (start == null || end == null) {
			start = 0;
			size = PropValues.CONFIG_DOSSIER_STATISTIC_SIZE_LIST;
		} else {
			start = start < 0 ? 0 : start;
			size = (end - start) <= 0 ? size : (end - start);
		}

		return new int[] { start, size };
	}

	public static String getSQLQueryTemplate(int type, String subType) {
		String sqlQueryTemplate = StringPool.BLANK;

		if (Validator.isNull(subType)) {
			return sqlQueryTemplate;
		} else if (subType.equals(Constants.COUNT)) {
			sqlQueryTemplate = QueryType.getSQLCountQueryTemplate(type);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			sqlQueryTemplate = QueryType.getSQLGroupQueryTemplate(type);
		} else if (subType.equals(Constants.LIST)) {
			sqlQueryTemplate = QueryType.getSQLSearchQueryTemplate(type);
		} else if (subType.equals(Constants.ROW_TOTAL)) {
			sqlQueryTemplate = QueryType.getSQLRowTotalQueryTemplate(type);
		}
		//System.out.println("5 " + Thread.currentThread().getId() + "|" + Thread.currentThread().getName());
		return sqlQueryTemplate;
	}

	private static Log _log = LogFactoryUtil.getLog(QueryUtil.class);
}
