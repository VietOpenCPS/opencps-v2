package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.VotingManagement;
import org.opencps.dossiermgt.action.DVCQGIntegrationAction;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.model.OpencpsVotingStatisticModel;
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;
import org.opencps.statistic.service.persistence.OpencpsVotingStatisticUtil;

public class VotingManagementImpl implements VotingManagement {
	private Log _log = LogFactoryUtil.getLog(VotingManagementImpl.class);

	@Override
	public Response checkVotePermission(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantIdNo, String dossierNo) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		List<Dossier> lstDossiers = DossierLocalServiceUtil.findByDN_AN(dossierNo, applicantIdNo);
		if (lstDossiers.size() > 0) {
			result.put("hasPermission", true);
		}
		else {
			result.put("hasPermission", false);
		}
		
		return Response.status(200).entity(result.toJSONString()).build();
	}

	@Override
	public Response getStatisticDVCQG(HttpServletRequest request, HttpHeaders header, Company company,
									  Locale locale, User user, ServiceContext serviceContext) {
		try {
			DVCQGIntegrationActionImpl integrationAction = new DVCQGIntegrationActionImpl();
			integrationAction.syncSummaryVote();
			return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
		}catch (Exception e) {
			_log.error(e.getMessage());
		}

		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
	}

	@Override
	public Response syncStatisticVoteToDVC(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		try {
			//Remove old data
			List<OpencpsVotingStatistic> lists = OpencpsVotingStatisticLocalServiceUtil.getOpencpsVotingStatistics(0, 100);
			for(OpencpsVotingStatistic oneStatistic: lists){
				OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByMonthYear(oneStatistic.getGroupId(),
						oneStatistic.getMonth(), oneStatistic.getYear());
			}

			//Sync new data from MCDT
			JSONArray votes = JSONFactoryUtil.createJSONArray(body);
			JSONObject item;

			for (int i = 0; i < votes.length(); i++) {
				item = votes.getJSONObject(i);
				OpencpsVotingStatisticLocalServiceUtil.updateVotingStatistic(0L,
						item.getLong("companyId", 0L), item.getLong("groupId", 0L),
						item.getLong("userId", 0L), item.getString("userName", ""),
						item.getInt("month", 0), item.getInt("year", 0),
						item.getString("votingSubject", ""), item.getInt("totalVoted", 0),
						item.getInt("veryGoodCount", 0), item.getInt("goodCount", 0),
						item.getInt("badCount", 0), item.getInt("percentVeryGood", 0),
						item.getInt("percentGood", 0), item.getInt("percentBad", 0),
						item.getString("govAgencyCode", ""), item.getString("govAgencyName", ""),
						item.getString("domainCode", ""), item.getString("domainName", ""),
						item.getString("votingCode", ""), item.getInt("totalCount", 0));
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
		}catch (Exception e) {
			_log.error(e.getMessage());
		}
		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
	}

}
