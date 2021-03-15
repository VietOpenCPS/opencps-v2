package org.opencps.backend.statisticmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.opencps.backend.statisticmgt.constant.PropKeys;

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

		STATISTIC_DOSSIER_RECEIVING_OFFLINE_LIST_DOSSIER(20),
		
		STATISTIC_DOSSIER_RECEIVING_ONLINE_LIST_DOSSIER(21),
		
		STATISTIC_DOSSIER_RELEASED_TOTAL_LIST(22),
		
		STATISTIC_DOSSIER_RELEASED_BETIMES_LIST(23),
		
		STATISTIC_DOSSIER_RELEASED_ONTIME_LIST(24),
		
		STATISTIC_DOSSIER_RELEASED_OVERTIME_LIST(25),
		
		STATISTIC_DOSSIER_DONE_TOTAL_LIST(26),
		
		STATISTIC_DOSSIER_DONE_BETIMES_LIST(27),
		
		STATISTIC_DOSSIER_DONE_ONTIME_LIST(28),
		
		STATISTIC_DOSSIER_DONE_OVERTIME_LIST(29),
		
		STATISTIC_DOSSIER_PROCESSING_TOTAL_LIST(30),
		
		STATISTIC_DOSSIER_PROCESSING_ONTIME_LIST(31),
		
		STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_LIST(32),
		
		STATISTIC_DOSSIER_PROCESSING_OVERTIME_LIST(33),
		
		STATISTIC_DOSSIER_REJECT_TOTAL_LIST(34),
		
		STATISTIC_DOSSIER_TAKEBACK_TOTAL_LIST(35),
		
		STATISTIC_DOSSIER_PEDING_TOTAL_LIST(36),
		
		STATISTIC_DOSSIER_DASHBROAD_TOTAL_COUNT(37);
		
		private QueryType(int type) {
			this.type = type;

			switch (type) {
			case 1:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RECEIVING_OFFLINE_TOTAL_COUNT);
				break;

			case 2:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RECEIVING_OFFLINE_DOMAIN_COUNT);
				break;

			case 3:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RECEIVING_ONLINE_TOTAL_COUNT);
				break;

			case 4:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RECEIVING_ONLINE_DOMAIN_COUNT);
				break;

			case 5:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_TOTAL_COUNT);
				break;

			case 6:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_BETIMES_COUNT);
				break;
			case 7:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_ONTIME_COUNT);
				break;
			case 8:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_OVERTIME_COUNT);
				break;
			case 9:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_TOTAL_COUNT);
				break;
			case 10:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_BETIMES_COUNT);
				break;
			case 11:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_ONTIME_COUNT);
				break;
			case 12:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_OVERTIME_COUNT);
				break;
			case 13:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_TOTAL_COUNT);
				break;
			case 14:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_ONTIME_COUNT);
				break;
			case 15:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_COUNT);
				break;
			case 16:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_OVERTIME_COUNT);
				break;
			case 17:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_REJECT_TOTAL_COUNT);
				break;
			case 18:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_TAKEBACK_TOTAL_COUNT);
				break;
			case 19:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PEDING_TOTAL_COUNT);
				break;
			case 20:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RECEIVING_OFFLINE_LIST_DOSSIER);
				break;
			case 21:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RECEIVING_ONLINE_LIST_DOSSIER);
				break;
			case 22:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_TOTAL_LIST);
				break;
			case 23:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_BETIMES_LIST);
				break;
			case 24:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_ONTIME_LIST);
				break;
			case 25:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_RELEASED_OVERTIME_LIST);
				break;
			case 26:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_TOTAL_LIST);
				break;
			case 27:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_BETIMES_LIST);
				break;
			case 28:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_ONTIME_LIST);
				break;
			case 29:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DONE_OVERTIME_LIST);
				break;
			case 30:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_TOTAL_LIST);
				break;
			case 31:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_ONTIME_LIST);
				break;
			case 32:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_LIST);
				break;
			case 33:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PROCESSING_OVERTIME_LIST);
				break;
			case 34:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_REJECT_TOTAL_LIST);
				break;
			case 35:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_TAKEBACK_TOTAL_LIST);
				break;
			case 36:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_PEDING_TOTAL_LIST);
				break;
			case 37:
				this.sqlTemplate = PropUtil.getProperty(PropKeys.STATISTIC_DOSSIER_DASHBROAD_TOTAL_COUNT);
				break;
			default:
				this.sqlTemplate = StringPool.BLANK;
				break;
			}

		}

		public int getType() {
			return type;
		}

		public static String getSQLQueryTemplate(int type) {
			for (QueryType e : values()) {
				if (e.type == type) {
					return e.sqlTemplate;
				}
			}
			return StringPool.BLANK;
		}

		String sqlTemplate;

		int type;
	}

	public static int getCount(String sql) {

		int count = 0;

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
		}

//		ConnectionUtil.closeConnection();

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
							dataRow.put(key, DatetimeUtil.convertDatetoDateString(rs.getDate(key)));
						} else {
							dataRow.put(key, rs.getString(key));
						}
					}
					data.put(dataRow);
				}
			} catch (SQLException sqle) {
				_log.warn(sqle.getMessage(), sqle);
			}
		} catch (SQLException sqle) {
			_log.warn(sqle.getMessage(), sqle);
		}

//		ConnectionUtil.closeConnection();

		return data;
	}

	private static Log _log = LogFactoryUtil.getLog(QueryUtil.class);
}
