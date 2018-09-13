
package backend.postal.api.rest.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.VotingUtils;
import org.opencps.api.voting.model.VotingInputModel;
import org.opencps.api.voting.model.VotingModel;
import org.opencps.api.voting.model.VotingResultInputModel;
import org.opencps.api.voting.model.VotingResultModel;
import org.opencps.api.voting.model.VotingResultResults;
import org.opencps.api.voting.model.VotingResults;
import org.opencps.api.voting.model.VotingSearchModel;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

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

import backend.feedback.action.VotingActions;
import backend.feedback.action.impl.VotingActionsImpl;
import backend.feedback.constants.VotingTerm;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;
import backend.feedback.service.VotingLocalServiceUtil;
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
			Sort[] sorts = new Sort[] {};

			JSONObject jsonData = action.getVotingList(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getData()
					.addAll(VotingUtils.mappingVotingDataList((List<Document>) jsonData.get("data"), serviceContext));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response addVoting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, VotingInputModel input) {

		VotingActions actions = new VotingActionsImpl();

		VotingModel result = new VotingModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Voting voting = actions.addVote(user.getUserId(), company.getCompanyId(), groupId, input.getClassName(),
					input.getClassPK(), input.getSubject(), input.getTemplateNo(), input.getChoices(),
					Boolean.valueOf(input.getCommentable()), serviceContext);

			result = VotingUtils.mapperVotingModel(voting, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response updateVoting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VotingInputModel input, long votingId) {

		VotingActions actions = new VotingActionsImpl();

		VotingModel result = new VotingModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Voting voting = actions.updateVoting(user.getUserId(), company.getCompanyId(), groupId, votingId,
					input.getClassName(), input.getClassPK(), input.getSubject(), input.getTemplateNo(),
					input.getChoices(), Boolean.valueOf(input.getCommentable()), serviceContext);

			result = VotingUtils.mapperVotingModel(voting, user.getUserId());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

	// @Override
	// public Response getVoting(HttpServletRequest request, HttpHeaders header,
	// Company company, Locale locale, User user,
	// ServiceContext serviceContext, long votingId) {
	//
	// super.pre(header, serviceContext);
	//
	// serviceContext = MBServiceContextThreadLocal.getServiceContext();
	//
	// VotingInterface actions = new VotingActions();
	//
	// VotingModel result = new VotingModel();
	//
	// Voting voting = actions.getVoting(votingId, serviceContext);
	//
	// if (Validator.isNotNull(voting)) {
	//
	// result = VotingUtils.mapperVotingModel(voting, user.getUserId());
	//
	// return Response.status(200).entity(result).build();
	//
	// } else {
	//
	// return MBResponseBuilderFactoryUtil.response(StringPool.BLANK, new
	// NotFoundException());
	//
	// }
	// }

	@Override
	public Response deleteVoting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long votingId) {

		VotingActions actions = new VotingActionsImpl();
		try {

			actions.deleteVoting(votingId, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
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
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response addVotingResult(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long votingId, VotingResultInputModel input) {

		VotingActions actions = new VotingActionsImpl();

		VotingResultModel result = new VotingResultModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			String comment = input.getComment();
			String selected = input.getSelected();
			String email = input.getEmail();

			VotingResult votingResult = actions.addVotingResult(user.getUserId(), company.getCompanyId(), groupId,
					votingId, email, comment, selected, serviceContext);

			result = VotingUtils.mapperVotingResultModel(votingResult);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
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
			_log.error(e);
			return processException(e);
		}
	}

	// @Override
	// public Response getVotingTops(HttpServletRequest request, HttpHeaders
	// header, Company company, Locale locale,
	// User user, ServiceContext serviceContext, VotingSearchModel query) {
	//
	// super.pre(header, serviceContext);
	//
	// serviceContext = MBServiceContextThreadLocal.getServiceContext();
	//
	// VotingInterface actions = new VotingActions();
	//
	// VotingTopResults result = new VotingTopResults();
	//
	// try {
	//
	// if (query.getEnd() == 0) {
	//
	// query.setStart(-1);
	//
	// query.setEnd(-1);
	//
	// }
	// long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	//
	// LinkedHashMap<String, Object> params = new LinkedHashMap<String,
	// Object>();
	//
	// params.put("groupId", String.valueOf(groupId));
	// params.put("keywords", query.getKeywords());
	//
	// Sort[] sorts = new Sort[] { SortFactoryUtil.create("treeIndex_sortable",
	// Sort.STRING_TYPE, false) };
	//
	// JSONObject jsonData = actions.getVotingResults(user.getUserId(),
	// company.getCompanyId(), groupId, params,
	// sorts, query.getStart(), query.getEnd(), serviceContext);
	//
	// result.setTotal(jsonData.getLong("total"));
	// result.getVotingTopModel()
	// .addAll(VotingUtils.mappingVotingTopList((List<Document>)
	// jsonData.get("data"), serviceContext));
	//
	// return Response.status(200).entity(result).build();
	//
	// } catch (Exception e) {
	// _log.error(e);
	// return MBResponseBuilderFactoryUtil.response(StringPool.BLANK, new
	// NotFoundException());
	// }
	// }
	//
	// @Override
	// public Response getVotingStatics(HttpServletRequest request, HttpHeaders
	// header, Company company, Locale locale,
	// User user, ServiceContext serviceContext) {
	//
	// super.pre(header, serviceContext);
	//
	// serviceContext = MBServiceContextThreadLocal.getServiceContext();
	//
	// VotingInterface actions = new VotingActions();
	//
	// VotingStatisticsResults result = new VotingStatisticsResults();
	//
	// try {
	//
	// long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	//
	// LinkedHashMap<String, Object> params = new LinkedHashMap<String,
	// Object>();
	//
	// params.put("groupId", String.valueOf(groupId));
	//
	// Sort[] sorts = new Sort[] { SortFactoryUtil.create("treeIndex_sortable",
	// Sort.STRING_TYPE, false) };
	//
	// JSONObject jsonData = actions.getVotingStatics(user.getUserId(),
	// company.getCompanyId(), groupId, params,
	// sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);
	//
	// result.setTotal(jsonData.getLong("total"));
	// result.getVotingStatisticsModel().addAll(VotingUtils
	// .mappingVotingStatisticsModelList((List<Document>) jsonData.get("data"),
	// serviceContext));
	//
	// return Response.status(200).entity(result).build();
	//
	// } catch (Exception e) {
	// _log.error(e);
	// return MBResponseBuilderFactoryUtil.response(StringPool.BLANK, new
	// NotFoundException());
	// }
	// }
	private Response processException(Exception e) {
		ErrorMsg error = new ErrorMsg();

		if (e instanceof UnauthenticationException) {
			error.setMessage("Non-Authoritative Information.");
			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
			error.setDescription("Non-Authoritative Information.");

			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		} else {
			if (e instanceof UnauthorizationException) {
				error.setMessage("Unauthorized.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Unauthorized.");

				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

			} else {
				_log.error(e);

				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("No Content.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}
}
