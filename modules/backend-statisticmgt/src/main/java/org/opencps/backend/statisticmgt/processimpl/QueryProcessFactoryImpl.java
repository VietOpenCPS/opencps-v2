package org.opencps.backend.statisticmgt.processimpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.process.QueryProcessFactory;
import org.opencps.backend.statisticmgt.util.ActionUtil;
import org.opencps.backend.statisticmgt.util.ConfigUtil;
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

			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.receiving_offline.domain_count
	 */
	@Override
	public JSONObject getDossierCount2(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_OFFLINE_DOMAIN_COUNT.getType());
		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
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
			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	/*
	 * @see statistic.dossier.receiving_online.total_count
	 */
	@Override
	public JSONObject getDossierCount3(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_ONLINE_TOTAL_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.receiving_online.domain_count
	 */
	@Override
	public JSONObject getDossierCount4(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_ONLINE_DOMAIN_COUNT.getType());
		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.total_count
	 */
	@Override
	public JSONObject getDossierCount5(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_TOTAL_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.betimes_count
	 */
	@Override
	public JSONObject getDossierCount6(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_BETIMES_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.ontime_count
	 */
	@Override
	public JSONObject getDossierCount7(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_ONTIME_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.overtime_count
	 */
	@Override
	public JSONObject getDossierCount8(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_OVERTIME_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.total_count
	 */
	@Override
	public JSONObject getDossierCount9(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_TOTAL_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.betimes_count
	 */
	@Override
	public JSONObject getDossierCount10(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_BETIMES_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.ontime_count
	 */
	@Override
	public JSONObject getDossierCount11(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_ONTIME_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.overtime_count
	 */
	@Override
	public JSONObject getDossierCount12(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_OVERTIME_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.total_count
	 */
	@Override
	public JSONObject getDossierCount13(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_TOTAL_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.ontime_count
	 */
	@Override
	public JSONObject getDossierCount14(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_ONTIME_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.nearexpired_count
	 */
	@Override
	public JSONObject getDossierCount15(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer day) throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_COUNT.getType());

		try {
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}
			if (day == null || day < 0) {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.overtime_count
	 */
	@Override
	public JSONObject getDossierCount16(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_OVERTIME_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.reject.total_count
	 */
	@Override
	public JSONObject getDossierCount17(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_REJECT_TOTAL_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.takeback.total_count
	 */
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.peding.total_count
	 */
	@Override
	public JSONObject getDossierCount19(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PEDING_TOTAL_COUNT.getType());
		try {
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
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	/*
	 * @see statistic.dossier.receiving_offline.list_dossier
	 */
	@Override
	public JSONObject getDossierList1(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_OFFLINE_LIST_DOSSIER.getType());
		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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
			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.receiving_online.list_dossier
	 */
	@Override
	public JSONObject getDossierList2(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RECEIVING_ONLINE_LIST_DOSSIER.getType());
		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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
			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.total_list
	 */
	@Override
	public JSONObject getDossierList3(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_TOTAL_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.betimes_list
	 */
	@Override
	public JSONObject getDossierList4(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_BETIMES_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.ontime_list
	 */
	@Override
	public JSONObject getDossierList5(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {

		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_ONTIME_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.released.overtime_list
	 */
	@Override
	public JSONObject getDossierList6(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_RELEASED_OVERTIME_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.total_list
	 */
	@Override
	public JSONObject getDossierList7(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_TOTAL_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.betimes_list
	 */
	@Override
	public JSONObject getDossierList8(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_BETIMES_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.ontime_list
	 */
	@Override
	public JSONObject getDossierList9(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_ONTIME_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.done.overtime_list
	 */
	@Override
	public JSONObject getDossierList10(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DONE_OVERTIME_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.total_list
	 */
	@Override
	public JSONObject getDossierList11(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_TOTAL_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.ontime_list
	 */
	@Override
	public JSONObject getDossierList12(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_ONTIME_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.nearexpired_list
	 */
	@Override
	public JSONObject getDossierList13(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end, Integer day)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_NEAREXPIRED_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
			if (domainCodes != null && domainCodes.length > 0) {
				String params_domainCodes = ParamUtil.generalTextParam(domainCodes);
				sqlTemplate = sqlTemplate.replace("{domainCode}", params_domainCodes);
			} else {
				sqlTemplate = sqlTemplate.replace("AND t2.domainCode = {domainCode}", StringPool.BLANK);
			}

			if (day == null || day < 0) {
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.processing.overtime_list
	 */
	@Override
	public JSONObject getDossierList14(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PROCESSING_OVERTIME_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.reject.total_list
	 */
	@Override
	public JSONObject getDossierList15(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_REJECT_TOTAL_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.takeback.total_list
	 */
	@Override
	public JSONObject getDossierList16(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {
		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;
		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_TAKEBACK_TOTAL_LIST.getType());
		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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
			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	/*
	 * @see statistic.dossier.peding.total_list
	 */
	@Override
	public JSONObject getDossierList17(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {

		int sizeDefault = Integer.parseInt(ConfigUtil.readKey("statistic.dossier.size_list"));
		int size = 0;
		start = (start == null) ? 0 : start;
		start = (start <= 0) ? 0 : start;
		end = (end == null) ? start + sizeDefault : end;
		end = (end < 0) ? start + sizeDefault : end;
		size = (end > start) ? end - start : start + sizeDefault;

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_PEDING_TOTAL_LIST.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
			}
			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));
			sqlTemplate = sqlTemplate.replace("{start}", String.valueOf(start));
			sqlTemplate = sqlTemplate.replace("{size}", String.valueOf(size));
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);
			sqlTemplate = sqlTemplate.toLowerCase();
			int indexFrom = sqlTemplate.indexOf("from");
			sqlTemplate = "SELECT COUNT(*) ".concat(sqlTemplate.substring(indexFrom));
			int count = QueryUtil.getCount(sqlTemplate);
			result.put(Constants.TOTAL, count);
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}

	@Override
	public JSONObject getDossierList18(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer start, Integer end)
			throws SQLException {

		JSONObject result = ActionUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				dossierStatus, QueryType.STATISTIC_DOSSIER_DASHBROAD_TOTAL_COUNT.getType());

		LinkedHashMap<String, Class<?>> columns = LinkedHashMapUtil.getLinkHashMapUsingRegression(sqlTemplate);
		try {
			Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlTemplate);
			String dataType = "";
			while (matcher.find()) {
				dataType = matcher.group();
				sqlTemplate = sqlTemplate.replace(dataType, StringPool.BLANK);
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

			JSONArray data = QueryUtil.getData(sqlTemplate, columns);

			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, data);
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}
}
