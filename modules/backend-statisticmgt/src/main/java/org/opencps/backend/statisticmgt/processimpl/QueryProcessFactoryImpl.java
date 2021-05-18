package org.opencps.backend.statisticmgt.processimpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.model.QuerySchema;
import org.opencps.backend.statisticmgt.process.QueryProcessFactory;
import org.opencps.backend.statisticmgt.util.ParamUtil;
import org.opencps.backend.statisticmgt.util.QueryUtil;
import org.opencps.backend.statisticmgt.util.StatisticUtil;

/**
 * @author trungnt
 *
 */
public class QueryProcessFactoryImpl implements QueryProcessFactory {

	private Log _log = LogFactoryUtil.getLog(QueryProcessFactoryImpl.class);

	public QuerySchema getQuerySchema2(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			sql = sql.replace("{originality}", String.valueOf(originalities[0]));
		} else {
			sql = sql.replace("AND t1.originality = {originality}", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema2: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic2(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {
		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema2(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema2(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema4(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			sql = sql.replace("{originality}", String.valueOf(originalities[0]));
		} else {
			sql = sql.replace("AND t1.originality = {originality}", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema4: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic4(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {
		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema4(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema4(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema5(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus[0]);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus = {dossierStatus}", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema5: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic5(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema5(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema5(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema6(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus[0]);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus = {dossierStatus}", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema6: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic6(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema6(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema6(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema7(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus[0]);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus = {dossierStatus}", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema7: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic7(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema7(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema7(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema8(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus[0]);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus = {dossierStatus}", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}

		_log.debug("generateQuerySchema8: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic8(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema8(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema8(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema9(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}

		_log.debug("generateQuerySchema9: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic9(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema9(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema9(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema10(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema10: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic10(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema10(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema10(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema11(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}

		_log.debug("generateQuerySchema11: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic11(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema11(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema11(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema12(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}
		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}

		_log.debug("generateQuerySchema12: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic12(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema12(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema12(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema13(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}

		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}

		_log.debug("generateQuerySchema13: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic13(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema13(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema13(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema14(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}

		_log.debug("generateQuerySchema14: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic14(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema14(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema14(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema15(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus, Integer day,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}

		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		if (day == null || day < 0) {

			sql = sql.replace("{day}", String.valueOf(2));
		} else {

			sql = sql.replace("{day}", String.valueOf(day));
		}
		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}

		_log.debug("generateQuerySchema15: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic15(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus, Integer day,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema15(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, day, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema15(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, day, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema16(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema16: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic16(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema16(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema16(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema17(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}

		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema17: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic17(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema17(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema17(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema18(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}

		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema18: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic18(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema18(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema18(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema19(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("t2.domainName[String]", "t1.govAgencyName[String]");
				sql = sql.replace("t2.domainCode[String]", "t1.govAgencyCode[String]");
			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("t2.domainName[String]", "t2.serviceName[String]");
				sql = sql.replace("t2.domainCode[String]", "t2.serviceCode[String]");
			} else {
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("{groupBy}", "t2.domainCode");
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		if (originalities != null && originalities.length > 0) {
			String paramsOriginalities = ParamUtil.generalTextParam(originalities);
			sql = sql.replace("{originality}", paramsOriginalities);
		} else {
			sql = sql.replace("AND t1.originality IN ({originality})", StringPool.BLANK);
		}
		_log.debug("generateQuerySchema19: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic19(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema19(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema19(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	public QuerySchema getQuerySchema20(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subtype) {

		int[] pageAndSize = QueryUtil.getPageAndSize(start, end);

		start = pageAndSize[0];

		int size = pageAndSize[1];

		String sql = sqlTemplate;

		if (Validator.isNotNull(groupBy)) {

			if (groupBy.equalsIgnoreCase("govAgencyCode")) {
				sql = sql.replace("{groupBy}", "t1.govAgencyCode");
				sql = sql.replace("AND t2.groupId = {groupId}", StringPool.BLANK);
				sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
				sql = sql.replace("INNER JOIN opencps_serviceinfo t2 ON t1.serviceCode = t2.serviceCode",
						StringPool.BLANK);

			} else if (groupBy.equalsIgnoreCase("serviceCode")) {
				sql = sql.replace("{groupBy}", "t1.serviceCode");
				sql = sql.replace("AND t2.groupId = {groupId}", StringPool.BLANK);
				sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
				sql = sql.replace("INNER JOIN opencps_serviceinfo t2 ON t1.serviceCode = t2.serviceCode",
						StringPool.BLANK);
				sql = sql.replace("t1.govAgencyCode[String]", "t1.serviceCode[String]");
				sql = sql.replace("t1.govAgencyName[String]", "t1.serviceName[String]");
			} else {
				sql = sql.replace("t1.govAgencyCode[String]", "t2.domainCode[String]");
				sql = sql.replace("t1.govAgencyName[String]", "t2.domainName[String]");
				sql = sql.replace("{groupBy}", "t2.domainCode");
			}

		} else {
			sql = sql.replace("INNER JOIN opencps_serviceinfo t2 ON t1.serviceCode = t2.serviceCode", StringPool.BLANK);
			sql = sql.replace("GROUP BY {groupBy}", StringPool.BLANK);
		}

		LinkedHashMap<String, Class<?>> columns = QueryUtil.getDataColumnMap(sql);

		Pattern pattern = Pattern.compile("(\\[([a-z]+|[A-Z]+)\\])", Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(sql);

		String dataType = StringPool.BLANK;

		while (matcher.find()) {
			dataType = matcher.group();
			sql = sql.replace(dataType, StringPool.BLANK);
		}
		sql = sql.replace("{groupId}", String.valueOf(groupId));
		sql = sql.replace("{start}", String.valueOf(start));
		sql = sql.replace("{size}", String.valueOf(size));

		if (Validator.isNotNull(fromDate)) {
			sql = sql.replace("{fromDate}", ParamUtil.generalTextParam(fromDate));
		} else {
			sql = sql.replace("AND t1.receiveDate >= {fromDate}", StringPool.BLANK);
		}

		if (Validator.isNotNull(toDate)) {
			sql = sql.replace("{toDate}", ParamUtil.generalTextParam(toDate));
		} else {
			sql = sql.replace("AND t1.receiveDate < {toDate}", StringPool.BLANK);
		}
		
		if (domainCodes != null && domainCodes.length > 0) {
			String paramsDomainCodes = ParamUtil.generalTextParam(domainCodes);
			sql = sql.replace("{domainCode}", paramsDomainCodes);
		} else {
			sql = sql.replace("AND t2.domainCode IN ({domainCode})", StringPool.BLANK);
		}
		if (govAgencyCodes != null && govAgencyCodes.length > 0) {
			String paramsGovAgencyCodes = ParamUtil.generalTextParam(govAgencyCodes);
			sql = sql.replace("{govAgencyCode}", paramsGovAgencyCodes);
		} else {
			sql = sql.replace("AND t1.govAgencyCode IN ({govAgencyCode})", StringPool.BLANK);
		}
		if (serviceCodes != null && serviceCodes.length > 0) {
			String paramsServiceCodes = ParamUtil.generalTextParam(serviceCodes);
			sql = sql.replace("{serviceCode}", paramsServiceCodes);
		} else {
			sql = sql.replace("AND t1.serviceCode IN ({serviceCode})", StringPool.BLANK);
		}
		if (Validator.isNotNull(dossierStatus) && dossierStatus.length > 0) {
			String params = ParamUtil.generalTextParam(dossierStatus);
			sql = sql.replace("{dossierStatus}", params);
		} else {
			sql = sql.replace("AND t1.dossierStatus IN ({dossierStatus})", StringPool.BLANK);
		}
		
		if (originalities != null && originalities.length > 0) {
			sql = sql.replace("{originality}", String.valueOf(originalities[0]));
		} else {
			sql = sql.replace("AND t1.originality = {originality}", StringPool.BLANK);
		}
		
		_log.debug("generateQuerySchema20: " + sql);

		return new QuerySchema(sql, sqlTemplate, type, subtype, columns);
	}

	@Override
	public JSONObject getDossierStatistic20(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus,
			String groupBy, int start, int end, String sqlTemplate, int type, String subType) {

		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema20(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);

		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());

			long total = 0;
			if (data != null) {
				JSONObject totalObj = JSONFactoryUtil.createJSONObject();
				for (int i = 0; i < data.length(); i++) {
					JSONObject tmp = data.getJSONObject(i);
					Iterator<String> keys = tmp.keys();
					while (keys.hasNext()) {
						String key = keys.next();
						if (key.equalsIgnoreCase("govAgencyCode") || key.equalsIgnoreCase("domainCode")
								|| key.equalsIgnoreCase("serviceCode")) {

							totalObj.put(key, StringPool.BLANK);
						} else {

							int value = 0;
							if (totalObj.has(key)) {
								value = totalObj.getInt(key);
							}
							value += tmp.getInt(key);

							totalObj.put(key, value);
						}
					}

					total += tmp.getInt(Constants.TOTAL_COUNT);
				}
				data.put(totalObj);

			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema20(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		}

		return result;
	}

	@Override
	public JSONObject getDossierStatistic21(long groupId, String fromDate, String toDate, int[] originalities,
											String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes,
											String[] dossierStatus, String groupBy, int start, int end,
											String sqlTemplate, int type, String subType) {
		JSONObject result = StatisticUtil.createResponseSchema(groupId, fromDate, toDate, originalities, domainCodes,
				govAgencyCodes, serviceCodes, dossierStatus, type, subType);
		QuerySchema schema = getQuerySchema2(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
				serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, subType);
		_log.debug("schema: " + schema.getSql());
		_log.debug("subType: " + subType);
		if (schema == null || Validator.isNull(schema.getSql())) {
			return result;
		}

		if (subType.equals(Constants.COUNT)) {
			int total = QueryUtil.getCount(schema.getSql());
			result.put(Constants.TOTAL, total);
		} else if (subType.equals(Constants.GROUP_COUNT)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			long total = 0;
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					total += data.getJSONObject(i).getInt(Constants.COUNT);
				}
			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.LIST)) {

			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());
			sqlTemplate = QueryUtil.getSQLQueryTemplate(type, Constants.COUNT);
			schema = getQuerySchema2(groupId, fromDate, toDate, originalities, domainCodes, govAgencyCodes,
					serviceCodes, dossierStatus, groupBy, start, end, sqlTemplate, type, Constants.COUNT);
			int total = QueryUtil.getCount(schema.getSql());

			result.put(Constants.TOTAL, total);

			result.put(Constants.DATA, data);
		} else if (subType.equals(Constants.ROW_TOTAL)) {
			JSONArray data = QueryUtil.getData(schema.getSql(), schema.getColumnMap());

			long total = 0;
			if (data != null) {
				JSONObject totalObj = JSONFactoryUtil.createJSONObject();
				for (int i = 0; i < data.length(); i++) {
					JSONObject tmp = data.getJSONObject(i);
					Iterator<String> keys = tmp.keys();
					while (keys.hasNext()) {
						String key = keys.next();
						if (key.equalsIgnoreCase("govAgencyCode") || key.equalsIgnoreCase("domainCode")
								|| key.equalsIgnoreCase("serviceCode")) {

							totalObj.put(key, StringPool.BLANK);
						} else {

							int value = 0;
							if (totalObj.has(key)) {
								value = totalObj.getInt(key);
							}
							value += tmp.getInt(key);

							totalObj.put(key, value);
						}
					}

					total += tmp.getInt(Constants.TOTAL_COUNT);
				}
				data.put(totalObj);

			}
			result.put(Constants.TOTAL, total);
			result.put(Constants.DATA, data);
		}

		return result;
	}

}
