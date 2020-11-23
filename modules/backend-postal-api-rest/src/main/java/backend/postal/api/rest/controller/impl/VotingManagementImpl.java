
package backend.postal.api.rest.controller.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.util.PostalConstantUtils;
import org.opencps.api.controller.util.VotingUtils;
import org.opencps.api.voting.model.VotingInputModel;
import org.opencps.api.voting.model.VotingModel;
import org.opencps.api.voting.model.VotingResultInputModel;
import org.opencps.api.voting.model.VotingResultModel;
import org.opencps.api.voting.model.VotingResultResults;
import org.opencps.api.voting.model.VotingResultSearchModel;
import org.opencps.api.voting.model.VotingResults;
import org.opencps.api.voting.model.VotingSearchModel;
import org.opencps.api.voting.model.VotingStatisticsResults;
import org.opencps.auth.utils.APIDateTimeUtils;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.feedback.action.VotingActions;
import backend.feedback.action.impl.VotingActionsImpl;
import backend.feedback.constants.VotingResultTerm;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;
import backend.feedback.service.VotingLocalServiceUtil;
import backend.postal.api.rest.controller.VotingManagement;

public class VotingManagementImpl implements VotingManagement {

	Log _log = LogFactoryUtil.getLog(VotingManagementImpl.class);
	public static final String VOTING_ID = "votingId";
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	public static final String COMPANY_ID = "companyId";
	public static final String CREATE_DATE = "createDate";
	public static final String GROUP_ID = "groupId";
	
	public static final String EMAIL = "email";
	public static final String CLASS_NAME = "className";
	public static final String CLASS_PK = "classPK";
	public static final String SUBJECT = "subject";
	public static final String CHOICES = "choices";
	public static final String TEMPLATE_NO = "templateNo";
	public static final String COMMENTABLE = "commentable";
	public static final String VOTING_CODE = "votingCode";
	public static final String VOTING_SUBJECT = "votingSubject";
	public static final String DOMAIN_CODE = "domainCode";
	public static final String DOMAIN_NAME = "domainName";
	public static final String GOV_AGENCY_CODE = "govAgencyCode";
	public static final String GOV_AGENCY_NAME = "govAgencyName";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	public static final String TREE_INDEX_SORTABLE = "treeIndex_sortable";

	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String FROM_VOTING_DATE = "fromVotingDate";
	public static final String TO_VOTING_DATE = "toVotingDate";
	public static final String KEYWORDS = "keywords";
	public static final String PARAMS = "params";
	public static final String ITEM_LV = "itemLv";
	public static final String PAGINATION_TYPE = "paginationType";
	public static final String EXPANDO_ATTRIBUTES = "expandoAttributes";

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
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			//_log.info("groupId: "+groupId);
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(KEYWORDS, query.getKeywords());
			params.put(CLASS_NAME, className);
			params.put(CLASS_PK, classPK);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(CREATE_DATE_SORTABLE, Sort.STRING_TYPE, false) };
//			Sort[] sorts = new Sort[] {};

//			JSONObject jsonData = action.getVotingList(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
//					query.getStart(), query.getEnd(), serviceContext);
			String fromVotingDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getFromVotingDate());
			String toVotingDate = APIDateTimeUtils.convertNormalDateToLuceneDate(query.getToVotingDate());
			//
			params.put(FROM_VOTING_DATE, fromVotingDate);
			params.put(TO_VOTING_DATE, toVotingDate);

			JSONObject jsonData = action.getVotingList(user.getUserId(), company.getCompanyId(), groupId, sorts, className, classPK,
						params, query.getStart(), query.getEnd(), serviceContext);

			if (jsonData != null) {
				result.setVotingCount(VotingUtils.getVotingCount((List<Document>) jsonData.get(PostalConstantUtils.DATA)));
				result.setTotal(jsonData.getLong(PostalConstantUtils.TOTAL));
				result.getData()
						.addAll(VotingUtils.mappingVotingDocList((List<Document>) jsonData.get(PostalConstantUtils.DATA), query.getFromVotingDate(), query.getToVotingDate(), serviceContext));
			} else {
				result.setTotal(0l);
			}

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String className = HtmlUtil.escape(input.getClassName());
			String classPK = HtmlUtil.escape(input.getClassPK());
			String subject = HtmlUtil.escape(input.getSubject());
			String choices = HtmlUtil.escape(input.getChoices());
			String templateNo = HtmlUtil.escape(input.getTemplateNo());
			String commentable = HtmlUtil.escape(String.valueOf(input.getCommentable()));
			String votingCode = input.getVotingCode();
			
			Voting voting = actions.addVote(user.getUserId(), company.getCompanyId(), groupId, className,
					classPK, subject, templateNo, choices,
					Boolean.valueOf(commentable), serviceContext);
			if (voting != null) {
				voting.setVotingCode(votingCode);
				VotingLocalServiceUtil.updateVoting(voting);
			}

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String className = HtmlUtil.escape(input.getClassName());
			String classPK = HtmlUtil.escape(input.getClassPK());
			String subject = HtmlUtil.escape(input.getSubject());
			String choices = HtmlUtil.escape(input.getChoices());
			String templateNo = HtmlUtil.escape(input.getTemplateNo());
			String commentable = HtmlUtil.escape(String.valueOf(input.getCommentable()));
			String votingCode = input.getVotingCode();

			Voting voting = actions.updateVoting(user.getUserId(), company.getCompanyId(), groupId, votingId,
					className, classPK, subject, templateNo,
					choices, Boolean.valueOf(commentable), serviceContext);
			if (voting != null) {
				voting.setVotingCode(votingCode);
				VotingLocalServiceUtil.updateVoting(voting);
			}

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(VOTING_ID, String.valueOf(votingId));

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(TREE_INDEX_SORTABLE, Sort.STRING_TYPE, false) };

			JSONObject jsonData = actions.getVotingResults(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			result.setTotal(jsonData.getLong(PostalConstantUtils.TOTAL));
			result.getData().addAll(
					VotingUtils.mappingVotingResultDataList((List<Document>) jsonData.get(PostalConstantUtils.DATA), serviceContext));

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String comment = HtmlUtil.escape(input.getComment());
			String selected = HtmlUtil.escape(input.getSelected());
			String email = HtmlUtil.escape(input.getEmail());
			_log.debug("ADD VOTING: " + votingId + "," + comment);
			VotingResult votingResult = actions.addVotingResult(user.getUserId(), company.getCompanyId(), groupId,
					votingId, email, comment, selected, serviceContext);

			result = VotingUtils.mapperVotingResultModel(votingResult);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.debug(e);
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

	@SuppressWarnings("unchecked")
	@Override
	public Response getVotingResultStatistic(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VotingResultSearchModel search) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		VotingActions actions = new VotingActionsImpl();
		VotingStatisticsResults result = new VotingStatisticsResults();

		try {

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(MONTH, String.valueOf(search.getMonth()));
			params.put(YEAR, String.valueOf(search.getYear()));
			params.put(CLASS_NAME, search.getClassName());
			params.put(GOV_AGENCY_CODE, search.getAgency());
			String fromVotingDate = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getFromVotingDate());
			String toVotingDate = APIDateTimeUtils.convertNormalDateToLuceneDate(search.getToVotingDate());
			params.put(VotingResultTerm.FROM_VOTING_DATE, fromVotingDate);
			params.put(VotingResultTerm.TO_VOTING_DATE, toVotingDate);
			
			//params.put("votingId", String.valueOf(votingId));

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(TREE_INDEX_SORTABLE, Sort.STRING_TYPE, false) };

			JSONObject jsonData = actions.getVotingResultStatistic(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			result.setTotal(jsonData.getLong(PostalConstantUtils.TOTAL));
			result.getData().addAll(
					VotingUtils.mappingVotingStatisticsModelList((List<Document>) jsonData.get(PostalConstantUtils.DATA), serviceContext));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response resolveConflictVotings(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// long userId = user.getUserId();
		VotingActions actions = new VotingActionsImpl();
		Indexer<Voting> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Voting.class);

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

		// get JSON data deliverable
		Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(CREATE_DATE_SORTABLE, Sort.STRING_TYPE, false) };
		JSONObject jsonData = actions.getVotingList(user.getUserId(), company.getCompanyId(), groupId, sorts, StringPool.BLANK, StringPool.BLANK,
				params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

		long total = jsonData.getLong(PostalConstantUtils.TOTAL);
		// JSONArray dossierArr = JSONFactoryUtil.createJSONArray();

		if (total > 0) {
			List<Document> lstDocuments = (List<Document>) jsonData.get(PostalConstantUtils.DATA);
			for (Document document : lstDocuments) {
				long votingId = GetterUtil.getLong(document.get(VOTING_ID));
				long companyId = GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				Voting oldVoting = VotingLocalServiceUtil.fetchVoting(votingId);
				if (oldVoting == null) {
					try {
						indexer.delete(companyId, uid);
					} catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}

		return Response.status(200).entity("{}").build();
	}

}
