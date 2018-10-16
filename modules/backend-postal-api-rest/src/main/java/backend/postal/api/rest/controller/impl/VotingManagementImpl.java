
package backend.postal.api.rest.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.api.controller.util.VotingUtils;
import org.opencps.api.voting.model.VotingInputModel;
import org.opencps.api.voting.model.VotingModel;
import org.opencps.api.voting.model.VotingResultInputModel;
import org.opencps.api.voting.model.VotingResultModel;
import org.opencps.api.voting.model.VotingResultResults;
import org.opencps.api.voting.model.VotingResults;
import org.opencps.api.voting.model.VotingSearchModel;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.feedback.action.VotingActions;
import backend.feedback.action.impl.VotingActionsImpl;
import backend.feedback.constants.VotingTerm;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;
import backend.postal.api.rest.controller.VotingManagement;

public class VotingManagementImpl implements VotingManagement {

	Log _log = LogFactoryUtil.getLog(VotingManagementImpl.class);

	@SuppressWarnings({ "unchecked"})
	@Override
	public Response getVotingList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String className, String classPK, VotingSearchModel query) {

		VotingResults result = new VotingResults();

		VotingActions action = new VotingActionsImpl();

		try {

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			_log.info("groupId: "+groupId);
			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put(VotingTerm.CLASS_NAME, className);
			params.put(VotingTerm.CLASS_PK, classPK);

			// Sort[] sorts = new Sort[] {
			// SortFactoryUtil.create(VotingTerm.CREATE_DATE_SORTABLE,
			// Sort.STRING_TYPE, false) };
//			Sort[] sorts = new Sort[] {};

//			JSONObject jsonData = action.getVotingList(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
//					query.getStart(), query.getEnd(), serviceContext);

			JSONObject jsonData = action.getVotingList(user.getUserId(), company.getCompanyId(), groupId, className, classPK,
					query.getStart(), query.getEnd(), serviceContext);


			result.setTotal(jsonData.getLong("total"));
			result.getData()
					.addAll(VotingUtils.mappingVotingDataList((List<Voting>) jsonData.get("data"), serviceContext));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addVoting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, VotingInputModel input) {

		VotingActions actions = new VotingActionsImpl();

		VotingModel result = new VotingModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String className = StringEscapeUtils.escapeHtml4(input.getClassName());
			String classPK = StringEscapeUtils.escapeHtml4(input.getClassPK());
			String subject = StringEscapeUtils.escapeHtml4(input.getSubject());
			String choices = StringEscapeUtils.escapeHtml4(input.getChoices());
			String templateNo = StringEscapeUtils.escapeHtml4(input.getTemplateNo());
			String commentable = StringEscapeUtils.escapeHtml4(String.valueOf(input.getCommentable()));
			
			Voting voting = actions.addVote(user.getUserId(), company.getCompanyId(), groupId, className,
					classPK, subject, templateNo, choices,
					Boolean.valueOf(commentable), serviceContext);

			result = VotingUtils.mapperVotingModel(voting, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateVoting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VotingInputModel input, long votingId) {

		VotingActions actions = new VotingActionsImpl();

		VotingModel result = new VotingModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			String className = StringEscapeUtils.escapeHtml4(input.getClassName());
			String classPK = StringEscapeUtils.escapeHtml4(input.getClassPK());
			String subject = StringEscapeUtils.escapeHtml4(input.getSubject());
			String choices = StringEscapeUtils.escapeHtml4(input.getChoices());
			String templateNo = StringEscapeUtils.escapeHtml4(input.getTemplateNo());
			String commentable = StringEscapeUtils.escapeHtml4(String.valueOf(input.getCommentable()));

			Voting voting = actions.updateVoting(user.getUserId(), company.getCompanyId(), groupId, votingId,
					className, classPK, subject, templateNo,
					choices, Boolean.valueOf(commentable), serviceContext);

			result = VotingUtils.mapperVotingModel(voting, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteVoting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long votingId) {

		VotingActions actions = new VotingActionsImpl();
		try {

			actions.deleteVoting(votingId, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getVotingResults(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long votingId) {

		VotingActions actions = new VotingActionsImpl();

		VotingResultResults result = new VotingResultResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("votingId", String.valueOf(votingId));

			Sort[] sorts = new Sort[] { SortFactoryUtil.create("treeIndex_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = actions.getVotingResults(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getData().addAll(
					VotingUtils.mappingVotingResultDataList((List<Document>) jsonData.get("data"), serviceContext));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addVotingResult(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long votingId, VotingResultInputModel input) {

		VotingActions actions = new VotingActionsImpl();

		VotingResultModel result = new VotingResultModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String comment = StringEscapeUtils.escapeHtml4(input.getComment());
			String selected = StringEscapeUtils.escapeHtml4(input.getSelected());
			String email = StringEscapeUtils.escapeHtml4(input.getEmail());

			VotingResult votingResult = actions.addVotingResult(user.getUserId(), company.getCompanyId(), groupId,
					votingId, email, comment, selected, serviceContext);

			result = VotingUtils.mapperVotingResultModel(votingResult);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeVotingResult(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long votingId, long votingResultId) {

		VotingActions actions = new VotingActionsImpl();

		try {

			actions.removeVotingResult(votingId, votingResultId, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
