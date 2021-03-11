package org.opencps.backend.statisticmgt.processimpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import java.sql.SQLException;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.process.QueryProcessFactory;
import org.opencps.backend.statisticmgt.util.ActionUtil;
import org.opencps.backend.statisticmgt.util.ParamUtil;
import org.opencps.backend.statisticmgt.util.QueryUtil;
import org.opencps.backend.statisticmgt.util.QueryUtil.QueryType;

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

			_log.info("sqlTemplate: " + sqlTemplate);

			sqlTemplate = sqlTemplate.replace("{groupId}", String.valueOf(groupId));

			if (domainCodes != null && domainCodes.length > 0) {
				sqlTemplate = sqlTemplate.replace("{domainCode}", domainCodes[0]);
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

		return null;
	}

	@Override
	public JSONObject getDossierCount3(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount4(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount5(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount6(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount7(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount8(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount9(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount10(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount11(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount12(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount13(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount14(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount15(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount16(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount17(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount18(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getDossierCount19(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
