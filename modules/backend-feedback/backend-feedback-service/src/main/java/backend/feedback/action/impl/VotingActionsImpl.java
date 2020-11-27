package backend.feedback.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.NotFoundException;

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.feedback.action.VotingActions;
import backend.feedback.constants.VotingTerm;
import backend.feedback.exception.NoSuchVotingException;
import backend.feedback.exception.NoSuchVotingResultException;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;
import backend.feedback.service.VotingLocalServiceUtil;
import backend.feedback.service.VotingResultLocalServiceUtil;
import backend.feedback.service.util.ConfigConstants;
import backend.feedback.service.util.ConfigProps;

public class VotingActionsImpl implements VotingActions {

	public static final Locale locale = new Locale(ConfigProps.get(ConfigConstants.SYS_LOCALE), ConfigProps.get(ConfigConstants.SYS_LOCALE_UPP));

	private static final Log _log = LogFactoryUtil.getLog(VotingActionsImpl.class);

	@Override
	public JSONObject getVotingList(long userId, long companyId, long groupId, Sort[] sorts, String className,
			String classPK, LinkedHashMap<String, Object> params, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			boolean isGetFromDB = true;

			if (!"0".equals(classPK)) {

				long count = VotingLocalServiceUtil.countVotingByClass_Name_PK(className, classPK);
				long countTemplate = VotingLocalServiceUtil.countVotingByClass_Name_PK(className, "0");
				System.out.println("============Count Cur=========" + count);
				System.out.println("============Count Temp=========" + countTemplate);

				if (count < countTemplate) {
					String subject;
					String choices;
					String templateNo;
					Boolean commentable;
					String votingCode;
					// get list voting config
					List<Voting> listVotingConfig = VotingLocalServiceUtil.getVotingByClass_Name_PK(className, ConfigProps.get(ConfigConstants.VOTING_CLASSPK_VALIDATOR));
					List<Voting> listVotingByClassPk = VotingLocalServiceUtil.getVotingByClass_Name_PK(className, classPK);
					String votingCodeConfig;
					boolean isClassPKExists;

					for (Voting votingConfig : listVotingConfig) {
						isClassPKExists = false;
						if(votingConfig.getVotingCode() == null || votingConfig.getVotingCode().isEmpty()){
							continue;
						}
						votingCodeConfig = votingConfig.getVotingCode();
						for(Voting votingClassPk: listVotingByClassPk){
							if(votingCodeConfig.equals(votingClassPk.getVotingCode())){
								isClassPKExists = true;
								break;
							}
						}
						if(!isClassPKExists){
							subject = votingConfig.getSubject();
							choices = votingConfig.getChoices();
							templateNo = votingConfig.getTemplateNo();
							commentable = votingConfig.getCommentable();
							votingCode = votingConfig.getVotingCode();

							Voting votingAdd = VotingLocalServiceUtil.addVoting(userId, groupId, className, classPK, subject, choices,
									templateNo, commentable, serviceContext);

							if (votingAdd != null) {
								votingAdd.setVotingCode(votingCode);
								VotingLocalServiceUtil.updateVoting(votingAdd);
							}
						}
					}
				}
			}

			// Add this case: When list vote renew, elastic search wont able to reload new vote(take some minute)
			// immediately so we will return new list here.
			if(isGetFromDB) {
				List<Voting> votingList;
				votingList = VotingLocalServiceUtil.getVotingByClass_Name_PK(className, classPK);
				List<Document> votingListDocument = new ArrayList<>();
				Document document;

				for(Voting voting : votingList) {
					document = new DocumentImpl();
					document.addText(VotingTerm.CLASS_NAME, className);
					document.addText(VotingTerm.SUBJECT, voting.getSubject());
					document.addText(VotingTerm.VOTING_CODE, voting.getVotingCode());
					document.addText(VotingTerm.COMMENTABLE, String.valueOf(voting.getCommentable()));
					document.addText(VotingTerm.CHOICES, voting.getChoices());
					document.addText(VotingTerm.VOTING_ID, String.valueOf(voting.getVotingId()));
					document.addText(VotingTerm.USER_ID, String.valueOf(voting.getUserId()));
					votingListDocument.add(document);
				}

				result.put(ConstantUtils.DATA, votingListDocument);
				result.put(ConstantUtils.TOTAL, votingList.size());
				return result;
			}

			//return list by elastic search
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(companyId);

			Hits hits = VotingLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);
			_log.info("VotingActions.getVotingList(): "+hits.getLength());
			if (hits.toList() == null || hits.toList().size() == 0) {
				params.put(VotingTerm.CLASS_PK, ConfigProps.get(ConfigConstants.VOTING_CLASSPK_VALIDATOR));
				params.put(VotingTerm.FROM_VOTING_DATE, StringPool.BLANK);
				params.put(VotingTerm.TO_VOTING_DATE, StringPool.BLANK);
				hits = VotingLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);
			}
			result.put(ConstantUtils.DATA, hits.toList());

			long total = VotingLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put(ConstantUtils.TOTAL, total);

			//long total = VotingLocalServiceUtil.countVotingByClass_Name_PK(className, classPK);
//			result.put(ConstantUtils.TOTAL, total);
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public Voting addVote(long userId, long companyId, long groupId, String className, String classPK, String subject,
			String templateNo, String choices, boolean commentable, ServiceContext serviceContext)
			throws PortalException, SystemException {

		return VotingLocalServiceUtil.addVoting(userId, groupId, className, classPK, subject, choices, templateNo,
				commentable, serviceContext);

	}

	public Voting fetchVotingById(long id) throws NotFoundException {

		Voting voting = VotingLocalServiceUtil.fetchVoting(id);
		if (voting == null) {
			throw new NotFoundException();
		}

		return voting;
	}

	@Override
	public Voting updateVoting(long userId, long companyId, long groupId, long votingId, String className,
			String classPK, String subject, String templateNo, String choices, Boolean commentable,
			ServiceContext serviceContext) throws PortalException, SystemException {

		Voting ett = fetchVotingById(votingId);

		if (Validator.isNotNull(subject)) {
			ett.setSubject(subject);
		}

		if (Validator.isNotNull(templateNo)) {
			ett.setTemplateNo(templateNo);
		}

		if (Validator.isNotNull(choices)) {
			ett.setChoices(choices);
		}

		if (Validator.isNotNull(commentable)) {
			ett.setCommentable(commentable);
		}

		ett = VotingLocalServiceUtil.updateVote(userId, votingId, ett.getClassName(), ett.getClassPK(),
				ett.getSubject(), ett.getChoices(), ett.getTemplateNo(), ett.getCommentable(), serviceContext);

		return ett;
	}

//	@Deprecated
//	@Override
//	public Voting getVoting(long votingId, ServiceContext serviceContext) {
//
//		Voting ett = VotingLocalServiceUtil.fetchVoting(votingId);
//
//		return ett;
//	}

	@Override
	public void deleteVoting(long votingId, ServiceContext serviceContext)
			throws NotFoundException, NoSuchVotingException {

		//VotingLocalServiceUtil.deleteVote(votingId, serviceContext);
		VotingLocalServiceUtil.deleteVoteConfig(votingId, serviceContext);

	}

	@Override
	public JSONObject getVotingResults(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = VotingResultLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put(ConstantUtils.DATA, hits.toList());

			long total = VotingResultLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put(ConstantUtils.TOTAL, total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public void removeVotingResult(long votingId, long votingResultId, ServiceContext serviceContext)
			throws NoSuchVotingResultException {

		VotingResultLocalServiceUtil.deleteVoteResult(votingResultId, serviceContext);
	}

//	@Override
//	public VotingResult addVotingResult(long userId, long companyId, long groupId, long votingId, String comment,
//			String selected, ServiceContext serviceContext)
//			throws PortalException, SystemException {
//
//		Voting voting = VotingLocalServiceUtil.fetchVoting(votingId);
//
//		if (Validator.isNull(voting)) {
//			throw new NotFoundException();
//		}
//		User user = UserLocalServiceUtil.fetchUser(userId);
//
//		VotingResult votingResult = VotingResultLocalServiceUtil.fetchByF_votingId_userId(userId, votingId);
//
//		if (user != null) {
//			if (Validator.isNotNull(votingResult)) {
//
//				votingResult = VotingResultLocalServiceUtil.updateVoteResult(userId, votingResult.getVotingResultId(),
//						votingId, user.getFullName(), user.getEmailAddress(), comment, selected, serviceContext);
//
//			} else {
//				// User user = UserLocalServiceUtil.
//				votingResult = VotingResultLocalServiceUtil.addVotingResult(userId, groupId, votingId,
//						user.getFullName(), user.getEmailAddress(), comment, selected, serviceContext);
//			}
//		}
//
//		return votingResult;
//	}

	@Override
	public VotingResult addVotingResult(long userId, long companyId, long groupId, long votingId, String email,
			String comment, String selected, ServiceContext serviceContext) throws PortalException, SystemException {

		Voting voting = VotingLocalServiceUtil.fetchVoting(votingId);
		VotingResult votingResult = null;

//		MBPermissionCheckerFactoryUtil.checkReadPermisson(
//			voting.getClassName(), voting.getClassPK(), serviceContext);

		if (voting != null) {
			User user = UserLocalServiceUtil.fetchUser(userId);
			if (user != null && !user.isDefaultUser()) {
				// Check employee
				Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
				// TODO check customer
				if (employee != null) {
					votingResult = VotingResultLocalServiceUtil.addVotingResult(userId, groupId,
							voting.getVotingId(), employee.getFullName(), employee.getEmail(), comment, selected,
							serviceContext);
				} else {
					Applicant applicant = ApplicantLocalServiceUtil.fetchByEmail(email);
					if (applicant != null) {
						votingResult = VotingResultLocalServiceUtil.addVotingResult(userId, groupId,
								voting.getVotingId(), applicant.getApplicantName(), applicant.getContactEmail(), comment,
								selected, serviceContext);
					}
				}
			}
			else {
				votingResult = VotingResultLocalServiceUtil.addVotingResult(userId, groupId, voting.getVotingId(), StringPool.BLANK,
						email, comment, selected, serviceContext);
			}
		}

		return votingResult;
	}

	@Override
	public JSONObject getVotingResultStatistic(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = VotingResultLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);
			result.put(ConstantUtils.DATA, hits.toList());

			long total = VotingResultLocalServiceUtil.countLuceneSearchEngine(params, searchContext);
			result.put(ConstantUtils.TOTAL, total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

}
