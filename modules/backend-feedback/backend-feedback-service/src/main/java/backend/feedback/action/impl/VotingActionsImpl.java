package backend.feedback.action.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.NotFoundException;

import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.feedback.action.VotingActions;
import backend.feedback.exception.NoSuchVotingException;
import backend.feedback.exception.NoSuchVotingResultException;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;
import backend.feedback.service.VotingLocalServiceUtil;
import backend.feedback.service.VotingResultLocalServiceUtil;

public class VotingActionsImpl implements VotingActions {

	public static final Locale locale = new Locale("vi", "VN");

	private static final Log _log = LogFactoryUtil.getLog(VotingActionsImpl.class);

	@Override
	public JSONObject getVotingList(long userId, long companyId, long groupId, String className,
			String classPK, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			if (!"0".equals(classPK)) {
				long count = VotingLocalServiceUtil.countVotingByClass_Name_PK(className, classPK);
				if (count == 0) {
					// Add new voting with classPK
					List<Voting> votingList = VotingLocalServiceUtil.getVotingByClass_Name_PK(className, "0");
					if (votingList != null) {
						String subject;
						String choices;
						String templateNo;
						Boolean commentable;
						String votingCode;
						for (Voting voting : votingList) {
							subject = voting.getSubject();
							choices = voting.getChoices();
							templateNo = voting.getTemplateNo();
							commentable = voting.getCommentable();
							votingCode = voting.getVotingCode();
							//voting.setClassPK(classPK);
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

			List<Voting> votingList = VotingLocalServiceUtil.getVotingByClass_Name_PK(className, classPK);
			result.put("data", votingList);
			// Hits hits = VotingLocalServiceUtil.luceneSearchEngine(params, sorts, start,
			// end, searchContext);
			// _log.info("VotingActions.getVotingList(): "+hits.getLength());
			// result.put("data", hits.toList());
			// long total = VotingLocalServiceUtil.countLuceneSearchEngine(params,
			// searchContext);
			long total = VotingLocalServiceUtil.countVotingByClass_Name_PK(className, classPK);
			result.put("total", total);
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

		VotingLocalServiceUtil.deleteVote(votingId, serviceContext);
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

			result.put("data", hits.toList());

			long total = VotingResultLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

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
					votingResult =
						VotingResultLocalServiceUtil.fetchByF_votingId_userId(
							userId, voting.getVotingId());
					if (Validator.isNotNull(votingResult)) {

						votingResult = VotingResultLocalServiceUtil.updateVoteResult(userId,
								votingResult.getVotingResultId(), votingId, employee.getFullName(), employee.getEmail(),
								comment, selected, serviceContext);
					} else {
						votingResult = VotingResultLocalServiceUtil.addVotingResult(userId, groupId,
								voting.getVotingId(), employee.getFullName(), employee.getEmail(), comment, selected,
								serviceContext);

					}
				} else {
					Applicant applicant = ApplicantLocalServiceUtil.fetchByEmail(email);
					if (applicant != null) {
						votingResult = VotingResultLocalServiceUtil.fetchByF_votingId_userId(userId,
								voting.getVotingId());
						if (Validator.isNotNull(votingResult)) {
							votingResult = VotingResultLocalServiceUtil.updateVoteResult(userId,
									votingResult.getVotingResultId(), votingId, applicant.getApplicantName(),
									applicant.getContactEmail(), comment, selected, serviceContext);
						} else {
							votingResult = VotingResultLocalServiceUtil.addVotingResult(userId, groupId,
									voting.getVotingId(), applicant.getApplicantName(), applicant.getContactEmail(), comment,
									selected, serviceContext);

						}
					}
				}
			}
			else {
//				ResourceUser resourceUser =
//					ResourceUserBusinessFactoryUtil.getResourceUserByEmail(
//						groupId, voting.getClassName(), voting.getClassPK(), email);
//				ResourceUser resourceUser = ResourceUserLocalServiceUtil.fetchByF_className_classPK_email(groupId,
//						voting.getClassName(), voting.getClassPK(), email);
//				if (resourceUser == null) {
//					throw new UnauthenticationException();
//				}

				votingResult = VotingResultLocalServiceUtil.addVotingResult(userId, groupId, voting.getVotingId(), "",
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
			result.put("data", hits.toList());

			long total = VotingResultLocalServiceUtil.countLuceneSearchEngine(params, searchContext);
			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

}
