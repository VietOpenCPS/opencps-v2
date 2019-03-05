
package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.opencps.api.voting.model.VotingDataModel;
import org.opencps.api.voting.model.VotingModel;
import org.opencps.api.voting.model.VotingResultDataModel;
import org.opencps.api.voting.model.VotingResultModel;
import org.opencps.api.voting.model.VotingStatisticsModel;
import org.opencps.api.voting.model.VotingTopModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.feedback.constants.VotingResultTerm;
import backend.feedback.constants.VotingTerm;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;
import backend.feedback.service.VotingLocalServiceUtil;
import backend.feedback.service.VotingResultLocalServiceUtil;

public class VotingUtils {

	/**
	 * @author sondt
	 * @param answerString
	 * @return
	 */
	public static List<String> convertAnswers(String answerString) {

		String[] arrayString = StringUtil.splitLines(answerString);

		//_log.info("Array" + ListUtil.toList(arrayString));

		return ListUtil.toList(arrayString);
	}

	/**
	 * @author sondt
	 * @param answerString
	 * @return
	 */
	public static String convertListToStringAnswers(List<String> answerlist) {

//		String stringAnswer = StringPool.BLANK;

		StringBuffer sb = new StringBuffer();

		for (String aswerStr : answerlist) {
			sb.append(aswerStr);
			sb.append(StringPool.SPACE);
			sb.append("\n");
		}

		 String stringAnswer = sb.toString().replaceAll("\n$", StringPool.BLANK);

		return stringAnswer;
	}

	static Log _log = LogFactoryUtil.getLog(VotingUtils.class);

	public static List<VotingModel> mappingVotingList(List<Document> list, ServiceContext serviceContext) {

		List<VotingModel> results = new ArrayList<>();

		VotingModel ett = null;

		for (Document doc : list) {
			ett = new VotingModel();

			ett.setVotingId(Long.valueOf(doc.get(VotingTerm.VOTING_ID)));
			ett.setUserId(Long.valueOf(doc.get(VotingTerm.USER_ID)));
			ett.setUserName(doc.get(VotingTerm.USER_NAME));
			try {

				ett.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(VotingTerm.CREATE_DATE),
						APIDateTimeUtils._TIMESTAMP));

				ett.setModifiedDate(
						APIDateTimeUtils.convertDateToString(doc.getDate("modified"), APIDateTimeUtils._TIMESTAMP));

			} catch (ParseException e) {
//				_log.error(e);
				_log.debug(e);
			}

			ett.setTemplateNo(doc.get(VotingTerm.TEMPLATE_NO));
			ett.setSubject(doc.get(VotingTerm.SUBJECT));
			ett.setCommentable(Boolean.valueOf(doc.get(VotingTerm.COMMENTABLE)));

			List<String> listChoices = convertAnswers(doc.get(VotingTerm.CHOICES));

			ett.getChoices().addAll(listChoices);

			VotingResult votingResult = VotingResultLocalServiceUtil
					.fetchByF_votingId_userId(serviceContext.getUserId(), ett.getVotingId());

			if (Validator.isNotNull(votingResult)) {
				ett.setSelected(GetterUtil.get(votingResult.getSelected(), 0));
			}
//			ett.setSelected(0);

			int votingCount = 0;
			int i = 0;
			int totalCounter = 0;

			List<String> answers = new ArrayList<>();

			for (String string : listChoices) {

				votingCount = VotingResultLocalServiceUtil.countByF_votingId_selected(ett.getVotingId(),
						String.valueOf(i + 1));

				i++;

				totalCounter = totalCounter + votingCount;

				answers.add(String.valueOf(votingCount));

			}

			ett.getAnswers().addAll(answers);
			ett.setAnswersCount(Long.valueOf(totalCounter));

			results.add(ett);
		}

		return results;
	}
	
	public static List<VotingDataModel> mappingVotingDataList(List<Voting> votingList, ServiceContext serviceContext) {

		List<VotingDataModel> results = new ArrayList<>();

		VotingDataModel ett = null;

		if (votingList != null) {
			for (Voting voting : votingList) {
				ett = new VotingDataModel();

//				ett.setVotingId(Long.valueOf(doc.get(VotingTerm.VOTING_ID)));
//				ett.setUserId(Long.valueOf(doc.get(VotingTerm.USER_ID)));
//				ett.setUserName(doc.get(VotingTerm.USER_NAME));
				ett.setVotingId(voting.getVotingId());
				ett.setUserId(voting.getUserId());
				ett.setUserName(voting.getUserName());
				ett.setCreateDate(
						APIDateTimeUtils.convertDateToString(voting.getCreateDate(), APIDateTimeUtils._TIMESTAMP));
				ett.setModifiedDate(
						APIDateTimeUtils.convertDateToString(voting.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));

//				ett.setTemplateNo(doc.get(VotingTerm.TEMPLATE_NO));
//				ett.setSubject(doc.get(VotingTerm.SUBJECT));
//				ett.setCommentable(Boolean.valueOf(doc.get(VotingTerm.COMMENTABLE)));
				ett.setTemplateNo(voting.getTemplateNo());
				ett.setSubject(voting.getSubject());
				ett.setCommentable(voting.getCommentable());

				List<String> listChoices = convertAnswers(voting.getChoices());
				ett.getChoices().addAll(listChoices);

//				VotingResult votingResult = VotingResultLocalServiceUtil
//						.fetchByF_votingId_userId(serviceContext.getUserId(), ett.getVotingId());
	//
//				if (Validator.isNotNull(votingResult)) {
//					ett.setSelected(GetterUtil.get(votingResult.getSelected(), 0));
//				}
				ett.setSelected(0);

				int votingCount = 0;
				int i = 0;
				int totalCounter = 0;

				List<String> answers = new ArrayList<>();

				for (String string : listChoices) {

					votingCount = VotingResultLocalServiceUtil.countByF_votingId_selected(ett.getVotingId(),
							String.valueOf(i + 1));

					i++;

					totalCounter = totalCounter + votingCount;

					answers.add(String.valueOf(votingCount));

				}

				ett.getAnswers().addAll(answers);
				ett.setAnswersCount(Long.valueOf(totalCounter));

				results.add(ett);
			}
		}

		return results;
	}

	public static VotingModel mapperVotingModel(Voting voting, long userId) {

		VotingModel ett = new VotingModel();

		if (Validator.isNotNull(voting)) {

			ett.setVotingId(voting.getVotingId());
			ett.setUserId(voting.getUserId());
			ett.setUserName(voting.getUserName());
			ett.setCreateDate(APIDateTimeUtils.convertDateToString(voting.getCreateDate(), APIDateTimeUtils._TIMESTAMP));

			ett.setModifiedDate(
					APIDateTimeUtils.convertDateToString(voting.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			ett.setTemplateNo(voting.getTemplateNo());
			ett.setSubject(voting.getSubject());
			ett.setCommentable(voting.getCommentable());
			ett.setVotingCode(voting.getVotingCode());

			List<String> listChoices = convertAnswers(voting.getChoices());

			ett.getChoices().addAll(listChoices);

			VotingResult votingResult = VotingResultLocalServiceUtil.fetchByF_votingId_userId(userId,
					ett.getVotingId());

			if (Validator.isNotNull(votingResult)) {
				ett.setSelected(GetterUtil.get(votingResult.getSelected(), 0));
			}

			int votingCount = 0;
			int i = 0;
			int totalCounter = 0;
			List<String> answers = new ArrayList<>();

			for (String string : listChoices) {

				votingCount = VotingResultLocalServiceUtil.countByF_votingId_selected(ett.getVotingId(),
						String.valueOf(i + 1));

				i++;
				totalCounter = totalCounter + votingCount;

				answers.add(String.valueOf(votingCount));

			}

			ett.setAnswersCount(Long.valueOf(totalCounter));

			if (answers.size() <= 0) {
				answers.add(StringPool.BLANK);
			}

			ett.getAnswers().addAll(answers);

		}

		return ett;
	}

	public static List<VotingResultModel> mappingVotingResultList(List<Document> list, ServiceContext serviceContext) {

		List<VotingResultModel> results = new ArrayList<>();

		VotingResultModel ett = null;

		for (Document doc : list) {
			ett = new VotingResultModel();

			ett.setVotingResultId(Long.valueOf(doc.get(VotingResultTerm.VOTING_RESULT_ID)));
			ett.setUserId(Long.valueOf(doc.get(VotingResultTerm.USER_ID)));
			try {

				ett.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(VotingResultTerm.CREATE_DATE),
						APIDateTimeUtils._TIMESTAMP));

				ett.setModifiedDate(
						APIDateTimeUtils.convertDateToString(doc.getDate("modified"), APIDateTimeUtils._TIMESTAMP));

			} catch (ParseException e) {
				_log.error(e);
			}

			ett.setFullName(doc.get(VotingResultTerm.FULLNAME));
			ett.setEmail(doc.get(VotingResultTerm.EMAIL));
			ett.setComment(doc.get(VotingResultTerm.COMMENT));

			ett.setSelected(GetterUtil.get(doc.get(VotingResultTerm.SELECTED), 0));

			boolean isCurrentUser = false;

			if (ett.getUserId() == serviceContext.getUserId()) {
				isCurrentUser = true;
			}

			ett.setCurrentUser(isCurrentUser);

			results.add(ett);
		}

		return results;
	}
	
	public static List<VotingResultDataModel> mappingVotingResultDataList(List<Document> list, ServiceContext serviceContext) {

		List<VotingResultDataModel> results = new ArrayList<>();

		VotingResultDataModel ett = null;

		for (Document doc : list) {
			ett = new VotingResultDataModel();

			ett.setVotingResultId(Long.valueOf(doc.get(VotingResultTerm.VOTING_RESULT_ID)));
			ett.setUserId(Long.valueOf(doc.get(VotingResultTerm.USER_ID)));
			try {

				ett.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(VotingResultTerm.CREATE_DATE),
						APIDateTimeUtils._TIMESTAMP));

				ett.setModifiedDate(
						APIDateTimeUtils.convertDateToString(doc.getDate("modified"), APIDateTimeUtils._TIMESTAMP));

			} catch (ParseException e) {
				_log.error(e);
			}

			ett.setFullName(doc.get(VotingResultTerm.FULLNAME));
			ett.setEmail(doc.get(VotingResultTerm.EMAIL));
			ett.setComment(doc.get(VotingResultTerm.COMMENT));

			ett.setSelected(GetterUtil.get(doc.get(VotingResultTerm.SELECTED), 0));

			boolean isCurrentUser = false;

			if (ett.getUserId() == serviceContext.getUserId()) {
				isCurrentUser = true;
			}

			ett.setCurrentUser(isCurrentUser);

			results.add(ett);
		}

		return results;
	}

	public static List<VotingTopModel> mappingVotingTopList(List<Document> list, ServiceContext serviceContext) {

		List<VotingTopModel> results = new ArrayList<>();

		VotingTopModel ett = null;

		for (Document doc : list) {
			ett = new VotingTopModel();
			try {

				ett.setVotingResultId(String.valueOf(doc.get(VotingResultTerm.VOTING_RESULT_ID)));
				ett.setUserId(Long.valueOf(doc.get(VotingResultTerm.USER_ID)));

				ett.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(VotingResultTerm.CREATE_DATE),
						APIDateTimeUtils._TIMESTAMP));

				ett.setModifiedDate(
						APIDateTimeUtils.convertDateToString(doc.getDate("modified"), APIDateTimeUtils._TIMESTAMP));

				ett.setFullName(doc.get(VotingResultTerm.FULLNAME));
				ett.setEmail(doc.get(VotingResultTerm.EMAIL));
				ett.setComment(doc.get(VotingResultTerm.COMMENT));
				ett.setVotingId(Long.valueOf(doc.get(VotingResultTerm.VOTING_ID)));

				Voting voting = VotingLocalServiceUtil.fetchVoting(ett.getVotingId());

				ett.setClassName(voting.getClassName());
				ett.setClassPK(voting.getClassPK());
				ett.setSubject(voting.getSubject());

				List<String> listChoices = convertAnswers(voting.getChoices());

				int i = 0;
				String choiceValue = StringPool.BLANK;

				for (String string : listChoices) {
					if (i == GetterUtil.get(doc.get(VotingResultTerm.SELECTED), 0)) {
						choiceValue = string;
						break;
					}
					i++;
				}

				ett.setChoice(choiceValue);
			} catch (ParseException e) {
				_log.error(e);
			}
			results.add(ett);
		}

		return results;
	}

	public static VotingResultModel mapperVotingResultModel(VotingResult votingResult) {

		VotingResultModel ett = new VotingResultModel();

		if (Validator.isNotNull(votingResult)) {

			ett.setVotingResultId(votingResult.getVotingResultId());
			ett.setUserId(votingResult.getUserId());
			ett.setFullName(votingResult.getFullname());
			ett.setCreateDate(
					APIDateTimeUtils.convertDateToString(votingResult.getCreateDate(), APIDateTimeUtils._TIMESTAMP));

			ett.setModifiedDate(
					APIDateTimeUtils.convertDateToString(votingResult.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			ett.setEmail(votingResult.getEmail());
			ett.setComment(votingResult.getComment());

			ett.setSelected(GetterUtil.get(votingResult.getSelected(), 0));

//			boolean isCurrentUser = false;
//			if (votingResult.getUserId() == votingResult.getUserId()) {
//				isCurrentUser = true;
//			}
//			ett.setCurrentUser(isCurrentUser);
			ett.setCurrentUser(true);
		}

		return ett;
	}

	public static List<VotingStatisticsModel> mappingVotingStatisticsModelList(List<Document> list,
			ServiceContext serviceContext) {

		List<VotingStatisticsModel> results = new ArrayList<>();
		VotingStatisticsModel ett = null;

		for (Document doc : list) {
			ett = new VotingStatisticsModel();

			ett.setVotingResultId(Long.valueOf(doc.get(VotingResultTerm.VOTING_RESULT_ID)));
			ett.setUserId(Long.valueOf(doc.get(VotingResultTerm.USER_ID)));
			ett.setGroupId(Long.valueOf(doc.get(Field.GROUP_ID)));
			try {
				ett.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(VotingResultTerm.CREATE_DATE),
						APIDateTimeUtils._NORMAL_PARTTERN));
				ett.setModifiedDate(APIDateTimeUtils.convertDateToString(doc.getDate(Field.MODIFIED_DATE),
						APIDateTimeUtils._NORMAL_PARTTERN));
			} catch (Exception e) {
				_log.error(e);
			}
			ett.setFullName(doc.get(VotingResultTerm.FULLNAME));
			ett.setEmail(doc.get(VotingResultTerm.EMAIL));
			ett.setComment(doc.get(VotingResultTerm.COMMENT));
			ett.setSelected(GetterUtil.get(doc.get(VotingResultTerm.SELECTED), 0));
			ett.setClassName(doc.get(VotingTerm.CLASS_NAME));
			ett.setClassPK(doc.get(VotingTerm.CLASS_PK));
			Long employeeId = GetterUtil.getLong(doc.get(VotingTerm.CLASS_PK));
			if (employeeId > 0) {
				Employee employee = EmployeeLocalServiceUtil.fetchEmployee(employeeId);
				if (employee != null) {
					ett.setEmployeeId(employee.getEmployeeId());
					ett.setEmployeeName(employee.getFullName());
				}
			}
			ett.setGovAgencyCode(doc.get(VotingTerm.GOV_AGENCY_CODE));
			ett.setGovAgencyName(doc.get(VotingTerm.GOV_AGENCY_NAME));
			ett.setDomain(doc.get(VotingTerm.DOMAIN_CODE));
			ett.setDomainName(doc.get(VotingTerm.DOMAIN_NAME));
			ett.setVotingCode(doc.get(VotingTerm.VOTING_CODE));
			ett.setVotingSubject(doc.get(VotingTerm.VOTING_SUBJECT));

			results.add(ett);
		}

		return results;
	}

}
