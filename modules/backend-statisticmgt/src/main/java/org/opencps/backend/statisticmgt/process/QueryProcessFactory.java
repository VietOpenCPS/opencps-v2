package org.opencps.backend.statisticmgt.process;

import com.liferay.portal.kernel.json.JSONObject;

import java.sql.SQLException;

/**
 * @author trungnt
 *
 */
public interface QueryProcessFactory {
	public JSONObject getDossierCount1(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount2(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount3(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount4(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount5(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount6(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount7(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount8(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount9(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount10(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount11(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount12(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount13(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount14(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount15(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate, Integer day) throws SQLException;
	public JSONObject getDossierCount16(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount17(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount18(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	public JSONObject getDossierCount19(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlTemplate) throws SQLException;
	
	public JSONObject getDossierList1(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList2(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList3(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList4(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList5(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList6(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList7(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList8(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList9(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList10(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList11(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList12(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList13(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList14(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList15(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end, Integer day) throws SQLException;
	public JSONObject getDossierList16(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList17(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList18(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList19(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
	public JSONObject getDossierList20(long groupId, String fromDate, String toDate, int[] originalities, String[] domainCodes, String[] dossierStatus, String sqlSearchTemplate, Integer start, Integer end) throws SQLException;
}
