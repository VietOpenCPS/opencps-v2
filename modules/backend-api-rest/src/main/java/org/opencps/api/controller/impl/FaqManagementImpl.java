package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.FaqManagement;
import org.opencps.api.controller.util.CaptchaServiceSingleton;
import org.opencps.api.faq.model.AnswerDetailModel;
import org.opencps.api.faq.model.AnswerInputModel;
import org.opencps.api.faq.model.AnswerModel;
import org.opencps.api.faq.model.AnswerResultsModel;
import org.opencps.api.faq.model.QuestionDetailModel;
import org.opencps.api.faq.model.QuestionInputModel;
import org.opencps.api.faq.model.QuestionModel;
import org.opencps.api.faq.model.QuestionResultsModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.usermgt.model.Answer;
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.AnswerLocalServiceUtil;
import org.opencps.usermgt.service.QuestionLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;

public class FaqManagementImpl implements FaqManagement {

	private static final Log _log = LogFactoryUtil.getLog(FaqManagementImpl.class);
	@Override
	public Response addQuestion(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, QuestionInputModel input, String jCaptchaResponse) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
			String captchaId = request.getSession().getId();
	        try {
	        	boolean isResponseCorrect = instance.validateResponseForID(captchaId,
	        			jCaptchaResponse);
	        	if (!isResponseCorrect) {
	        		ErrorMsgModel error = new ErrorMsgModel();
	        		error.setMessage("Captcha incorrect");
	    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
	    			error.setDescription("Captcha incorrect");

	    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
	        	}
	        } catch (CaptchaServiceException e) {
	        	_log.debug(e);
        		ErrorMsgModel error = new ErrorMsgModel();
        		error.setMessage("Captcha incorrect");
    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
    			error.setDescription("Captcha incorrect");

    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
	        }

			Question question = QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(), groupId, 0l,
					input.getFullname(), input.getEmail(), input.getContent(), input.getPublish(),
					input.getGovAgencyCode(), input.getGovAgencyName());
			if (question != null) {
				QuestionDetailModel result = new QuestionDetailModel();
				result.setContent(question.getContent());
				result.setCreateDate(APIDateTimeUtils.convertDateToString(question.getCreateDate()));
				result.setEmail(question.getEmail());
				result.setFullname(question.getFullname());
				result.setPublish(question.getPublish());
				result.setQuestionId(question.getQuestionId());
				result.setGovAgencyCode(question.getGovAgencyCode());
				result.setGovAgencyName(question.getGovAgencyName());
				
				return Response.status(200).entity(result).build();
			}
			else {
				throw new Exception("Error process database");
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getQuestions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, Integer start, Integer end, Integer publish, String govAgencyCode, String keyword, String questionType,
			String answer,
			ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			if (Validator.isNull(start)) {
				start = QueryUtil.ALL_POS;
				end = QueryUtil.ALL_POS;
			}
			//List<Question> lstQuestions = null;
//			if (publish == null) {
//				lstQuestions = QuestionLocalServiceUtil.findByG_PL(groupId, new int[] { 0, 1 }, start, end);
//			}
//			else {
//				lstQuestions = QuestionLocalServiceUtil.findByG_PL(groupId, new int[] { publish }, start, end);
//			}
			
			Boolean filterAnswer = null;
			if (Validator.isNotNull(answer)) {
				filterAnswer = Boolean.parseBoolean(answer);
			}
			List<Question> lstQuestions = QuestionLocalServiceUtil.findByQuerySearch(groupId, keyword, govAgencyCode,
					publish, questionType, filterAnswer, start, end);
			
			QuestionResultsModel result = new QuestionResultsModel();
			result.setTotal(QuestionLocalServiceUtil.countByQuerySearch(groupId, keyword, govAgencyCode, publish, questionType, filterAnswer));
			
			List<QuestionModel> lstModels = new ArrayList<>();
			if (lstQuestions != null && lstQuestions.size() > 0) {
				for (Question q : lstQuestions) {
					QuestionModel model = new QuestionModel();
					model.setContent(q.getContent());
					model.setCreateDate(APIDateTimeUtils.convertDateToString(q.getCreateDate()));
					model.setEmail(q.getEmail());
					model.setFullname(q.getFullname());
					model.setPublish(q.getPublish());
					model.setQuestionId(q.getQuestionId());
					model.setGovAgencyCode(q.getGovAgencyCode());
					model.setGovAgencyName(q.getGovAgencyName());
					
					int count = AnswerLocalServiceUtil.countByG_Q_PL(groupId, q.getQuestionId(), new int[] { 0, 1 } );
					model.setAnswered(false);
					
					if (count > 0) {
						model.setAnswered(true);
					}
					lstModels.add(model);
				}
				result.getData().addAll(lstModels);
			}

			return Response.status(200).entity(result).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addAnswer(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long questionId, AnswerInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			Answer answer = AnswerLocalServiceUtil.updateAnswer(user.getUserId(), groupId, 0l, questionId, input.getContent(), input.getPublish());
			
			if (answer != null) {
				AnswerDetailModel result = new AnswerDetailModel();
				result.setAnswerId(answer.getAnswerId());
				result.setContent(answer.getContent());
				result.setCreateDate(APIDateTimeUtils.convertDateToString(answer.getCreateDate()));
				result.setModifiedDate(APIDateTimeUtils.convertDateToString(answer.getModifiedDate()));
				result.setPublish(answer.getPublish());
				result.setQuestionId(answer.getQuestionId());
				result.setUserName(answer.getUserName());
				return Response.status(200).entity(result).build();
			}
			else {
				throw new Exception("Error process database");
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response getAnswers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, long questionId, 
			Integer start,
			Integer end,
			Integer publish,
			ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			if (Validator.isNull(start)) {
				start = QueryUtil.ALL_POS;
				end = QueryUtil.ALL_POS;
			}
			List<Answer> lstAnswers = null; 
			if (publish == null) {
				lstAnswers = AnswerLocalServiceUtil.findByG_Q_PL(groupId, questionId, new int[] { 0, 1 }, start, end);
			}
			else {
				lstAnswers = AnswerLocalServiceUtil.findByG_Q_PL(groupId, questionId, new int[] { publish }, start, end);
			}
			AnswerResultsModel result = new AnswerResultsModel();
			
			if (publish == null) {
				result.setTotal(AnswerLocalServiceUtil.countByG_Q_PL(groupId, questionId, new int[] { 0, 1 }));
			}
			else {
				result.setTotal(AnswerLocalServiceUtil.countByG_Q_PL(groupId, questionId, new int[] { publish }));
			}
			
			List<AnswerModel> lstModels = new ArrayList<>();
			for (Answer q : lstAnswers) {
				AnswerModel model = new AnswerModel();
				model.setAnswerId(q.getAnswerId());
				model.setContent(q.getContent());
				model.setCreateDate(APIDateTimeUtils.convertDateToString(q.getCreateDate()));
				model.setPublish(q.getPublish());
				model.setQuestionId(q.getQuestionId());
				
				lstModels.add(model);
			}
			result.getData().addAll(lstModels);
			
			return Response.status(200).entity(result).build();			
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	}

	@Override
	public Response updateQuestion(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, QuestionInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long questionId = GetterUtil.getLong(id);
		try {
			Question question = QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(), groupId,
					questionId, input.getFullname(), input.getEmail(), input.getContent(), input.getPublish(),
					input.getGovAgencyCode(), input.getGovAgencyName());
			if (question != null) {
				QuestionDetailModel result = new QuestionDetailModel();
				result.setContent(question.getContent());
				result.setCreateDate(APIDateTimeUtils.convertDateToString(question.getCreateDate()));
				result.setEmail(question.getEmail());
				result.setFullname(question.getFullname());
				result.setPublish(question.getPublish());
				result.setQuestionId(question.getQuestionId());
				result.setGovAgencyCode(question.getGovAgencyCode());
				result.setGovAgencyName(input.getGovAgencyName());
				
				return Response.status(200).entity(result).build();
			}
			else {
				throw new Exception("Error process database");
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteQuestion(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		long questionId = GetterUtil.getLong(id);
		
		try {
			QuestionLocalServiceUtil.deleteQuestion(questionId);

			return Response.status(200).entity("Delete question is success!").build();
		} catch (PortalException e) {
			_log.debug(e);
			return Response.status(500).entity("Delete question is false!").build();
		}
	}

	@Override
	public Response updateAnswers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long questionId, long answerId, AnswerInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			Answer answer = AnswerLocalServiceUtil.updateAnswer(user.getUserId(), groupId, answerId, questionId, input.getContent(), input.getPublish());

			if (answer != null) {
				AnswerDetailModel result = new AnswerDetailModel();
				result.setAnswerId(answer.getAnswerId());
				result.setContent(answer.getContent());
				result.setCreateDate(APIDateTimeUtils.convertDateToString(answer.getCreateDate()));
				result.setModifiedDate(APIDateTimeUtils.convertDateToString(answer.getModifiedDate()));
				result.setPublish(answer.getPublish());
				result.setQuestionId(answer.getQuestionId());
				result.setUserName(answer.getUserName());
				return Response.status(200).entity(result).build();
			}
			else {
				throw new Exception("Error process database");
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteAnswers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long questionId, long answerId) {

		try {
			AnswerLocalServiceUtil.deleteAnswer(answerId);

			return Response.status(200).entity("Delete Answer is success!").build();
		} catch (PortalException e) {
			_log.debug(e);
			return Response.status(500).entity("Delete Answer is false!").build();
		}
	}

}
