package org.opencps.backend.statisticmgt.processimpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.process.QueryProcessFactory;
import org.opencps.backend.statisticmgt.util.ActionUtil;
import org.opencps.backend.statisticmgt.util.DatetimeUtil;
import org.opencps.backend.statisticmgt.util.LinkedHashMapUtil;
import org.opencps.backend.statisticmgt.util.ParamUtil;
import org.opencps.backend.statisticmgt.util.QueryUtil;
import org.opencps.backend.statisticmgt.util.QueryUtil.QueryType;

import io.swagger.v3.oas.annotations.links.Link;

/**
 * @author trungnt
 *
 */
public class QueryProcessFactoryImpl implements QueryProcessFactory {

	private Log _log = LogFactoryUtil.getLog(QueryProcessFactoryImpl.class);

	/*
	 * @see statistic.dossier.receiving_offline.total_count
	 */
	@Override
	public JSONObject getDossierCount1(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_OFFLINE_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 1: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount2(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_OFFLINE_DOMAIN_COUNT.getType());
		int total = 0;
		LinkedHashMap<String, Class<?>> columns = new LinkedHashMap<String, Class<?>>();
		columns.put("domainName", String.class);
		columns.put("domainCode", String.class);
		columns.put("count", Integer.class);
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 2: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);
			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			
			for(int i=0; i<data.length(); i++)
			{
				JSONObject each = data.getJSONObject(i);
				total += each.getInt("count");
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount3(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_ONLINE_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 3: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount4(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_ONLINE_DOMAIN_COUNT.getType());
		int total = 0;
		LinkedHashMap<String, Class<?>> columns = new LinkedHashMap<String, Class<?>>();
		columns.put("domainName", String.class);
		columns.put("domainCode", String.class);
		columns.put("count", Integer.class);
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 4: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);
			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			
			for(int i=0; i<data.length(); i++)
			{
				JSONObject each = data.getJSONObject(i);
				total += each.getInt("count");
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount5(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 5: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount6(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_BETIMES_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 6: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount7(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_ONTIME_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate 7: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount8(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_OVERTIME_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate 8: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount9(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate 9: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount10(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_BETIMES_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 10: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount11(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {


		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_ONTIME_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount12(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_OVERTIME_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 12: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount13(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate 13: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}
			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount14(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_ONTIME_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate 14: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}
			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount15(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer day) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_COUNT.getType());
		
		try {
			
			System.out.println("sqlTemplate 15: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}
			if(day == null || day < 0) {
				sqlTemplate = sqlTemplate.replace("{day}", String.valueOf(2));
			} else {
				sqlTemplate = sqlTemplate.replace("{day}", String.valueOf(day));
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}
			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount16(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_OVERTIME_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate 16: " + sqlTemplate);

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}
			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount17(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_REJECT_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 17: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount18(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_TAKEBACK_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 18: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCount19(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PEDING_TOTAL_COUNT.getType());
		
		try {

			System.out.println("sqlTemplate: " + sqlTemplate);

			_log.info("sqlTemplate 19: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}

			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);

			int count = QueryUtil.getCount(sqlTemplate);

			result.put(Constants.TOTAL, count);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierList1(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		Integer size_list = 0;
		if (start == null || end == null) {
			start = 0;
			size_list = 30;
		} else {
			size_list = end - start;
		}
		
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_OFFLINE_LIST_DOSSIER.getType());
		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMap(sqlTemplate);
		try {
			System.out.println("sqlTemplate: " + sqlTemplate);
			
			_log.info("sqlTemplate 20: " + sqlTemplate);
			sqlTemplate = sqlTemplate.replace("[String]", "");
			sqlTemplate = sqlTemplate.replace("[Integer]", "");
			sqlTemplate = sqlTemplate.replace("[Date]", "");
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size_list));
			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}
			
			if (Validator.isNotNull(fromDate)) {
				sqlTemplate = sqlTemplate.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(toDate)) {
				sqlTemplate = sqlTemplate.replace("{toDate}", ParamUtil.generalTextParam(toDate));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
			}

			if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
				String params = ParamUtil.generalTextParam(dossierStatus);
				sqlTemplate = sqlTemplate.replace("{dossierStatus}", params);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
			}

			if (originalities != null && originalities.length > 0) {
				sqlTemplate = sqlTemplate.replace("{originality}", String.valueOf(originalities[0]));
			} else {
				sqlTemplate = sqlTemplate.replace("AND t1.originality = {originality}", StringPool.BLANK);
			}
			
			_log.info("sql: " + sqlTemplate);

			System.out.println("sql: " + sqlTemplate);
			
			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			for(int i=0; i<data.length(); i++) {
				JSONObject obj = data.getJSONObject(i);
				obj.get("receiveDate").toString();
			}
			int indexFrom = sqlTemplate.indexOf("FROM");
		    sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
		    int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}
	
	

}
