
package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
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
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
			String captchaId = request.getSession().getId();
	        try {
	        	boolean isResponseCorrect = instance.validateResponseForID(captchaId,
	        			jCaptchaResponse);
	        	if (!isResponseCorrect) {
	        		ErrorMsgModel error = new ErrorMsgModel();
	        		error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));
	    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
	    			error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));

	    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
	        	}
	        } catch (CaptchaServiceException e) {
	        	_log.debug(e);
        		ErrorMsgModel error = new ErrorMsgModel();
        		error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));
    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
    			error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));

    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
	        }

			Question question = QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(), groupId, 0l,
					input.getFullname(), input.getEmail(), input.getContent(), input.getPublish(),
					input.getGovAgencyCode(), input.getGovAgencyName(), input.getQuestionType(), input.getSubDomainCode(), input.getSubDomainName());
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
				result.setSubDomainCode(question.getSubDomainCode());
				result.setSubDomainName(question.getSubDomainName());
				
				return Response.status(200).entity(result)
						.build();
			}
			else {
				throw new Exception(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getQuestions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, Integer start, Integer end, Integer publish, String govAgencyCode, String keyword, String questionType,
			String answer, String subDomainCode,
			ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.debug("IN GET QUESTION: " + groupId);
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
					publish, questionType, filterAnswer, subDomainCode, start, end);
			
			QuestionResultsModel result = new QuestionResultsModel();
			result.setTotal(QuestionLocalServiceUtil.countByQuerySearch(groupId, keyword, govAgencyCode, publish, questionType, filterAnswer, subDomainCode));
			
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
					model.setQuestionType(q.getQuestionType());
					model.setSubDomainCode(q.getSubDomainCode());
					model.setSubDomainName(q.getSubDomainName());
					
					int count = AnswerLocalServiceUtil.countByG_Q_PL(groupId, q.getQuestionId(), new int[] { 0, 1 } );
					model.setAnswered(false);
					
					if (count > 0) {
						model.setAnswered(true);
					}
					lstModels.add(model);
				}
				result.getData().addAll(lstModels);
			}

			return Response.status(200).entity(result)
					.build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addAnswer(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long questionId, AnswerInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
				return Response.status(200).entity(result)
						.build();
			}
			else {
				throw new Exception(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));
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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
			
			return Response.status(200).entity(result)
					.build();			
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response updateQuestion(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, QuestionInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long questionId = GetterUtil.getLong(id);
		try {
			Question question = QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(), groupId,
					questionId, input.getFullname(), input.getEmail(), input.getContent(), input.getPublish(),
					input.getGovAgencyCode(), input.getGovAgencyName(), input.getQuestionType(), input.getSubDomainCode(), input.getSubDomainName());
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
				result.setSubDomainCode(question.getSubDomainCode());
				result.setSubDomainName(input.getSubDomainName());
				
				return Response.status(200).entity(result)
						.build();
			}
			else {
				throw new Exception(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));
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

			return Response.status(200).entity(ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE))
					.build();
		} catch (PortalException e) {
			_log.debug(e);
			return Response.status(500).entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION)).build();
		}
	}

	@Override
	public Response updateAnswers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long questionId, long answerId, AnswerInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
				return Response.status(200).entity(result)
					.build();
			}
			else {
				throw new Exception(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_CAPTCHA_INCORRECT));
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

			return Response.status(200).entity(ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE))
				.build();
		} catch (PortalException e) {
			_log.debug(e);
			return Response.status(500).entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION)).build();
		}
	}

	@Override
	public Response optionQuestions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, Integer start, Integer end, Integer publish, String govAgencyCode, String keyword,
			String questionType, String answer, String subDomainCode, ServiceContext serviceContext) {
		return Response.status(200).entity(StringPool.BLANK)
				.build();
	}

	@Override
	public Response detailQuestion(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		return Response.status(200).entity(StringPool.BLANK)
				.build();
	}

	@Override
	public Response optionAnswers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, long questionId, Integer start, Integer end, Integer publish, ServiceContext serviceContext) {
		return Response.status(200).entity(StringPool.BLANK)
				.build();
	}

	@Override
	public Response optionsDetailAnswers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long questionId, long answerId) {
		return Response.status(200).entity(StringPool.BLANK)
				.build();
	}

}
