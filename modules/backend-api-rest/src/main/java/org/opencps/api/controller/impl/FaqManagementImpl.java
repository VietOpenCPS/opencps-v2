
package org.opencps.api.controller.impl;

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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.FaqManagement;
import org.opencps.api.controller.util.CaptchaServiceSingleton;
import org.opencps.api.controller.util.MessageUtil;
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
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.kernel.prop.PropValues;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.model.Answer;
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.AnswerLocalServiceUtil;
import org.opencps.usermgt.service.QuestionLocalServiceUtil;

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

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;

public class FaqManagementImpl implements FaqManagement {

	private static final Log _log =
		LogFactoryUtil.getLog(FaqManagementImpl.class);
	private final String ACCESS_CONTROL_ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin";
	private final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	private final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	private final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
	private final String ORIGIN_HEADER = "Origin";
	private final String METHOD_POST = "POST";
	private final String METHOD_GET = "GET";
	private final String METHOD_PUT = "PUT";
	private final String METHOD_DELETE = "DELETE";
	private final String ALLOW_HEADERS = "origin, content-type, accept, authorization, groupid, token";
	
	private void buildCrossOriginHeader(ResponseBuilder builder, HttpServletRequest request, String method) {
		builder.header(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, request.getHeader(ORIGIN_HEADER));
		builder.header(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		builder.header(ACCESS_CONTROL_ALLOW_HEADERS, ALLOW_HEADERS);
		builder.header(ACCESS_CONTROL_ALLOW_METHODS, method);
	}
	
	@Override
	public Response addQuestion(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		QuestionInputModel input, String jCaptchaResponse) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String captchaType = PropValues.CAPTCHA_TYPE;
		
		try {
			if (Validator.isNotNull(captchaType) && captchaType.equals("jcaptcha")) {
				ImageCaptchaService instance =
						CaptchaServiceSingleton.getInstance();
					String captchaId = request.getSession().getId();
					try {
						boolean isResponseCorrect =
							instance.validateResponseForID(captchaId, jCaptchaResponse);
						if (!isResponseCorrect) {
							ErrorMsgModel error = new ErrorMsgModel();
							error.setMessage("Captcha incorrect");
							error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
							error.setDescription("Captcha incorrect");

							return Response.status(
								HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(
									error).build();
						}
					}
					catch (CaptchaServiceException e) {
						_log.debug(e);
						ErrorMsgModel error = new ErrorMsgModel();
						error.setMessage("Captcha incorrect");
						error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
						error.setDescription("Captcha incorrect");

						return Response.status(
							HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(
								error).build();
					}
			} else {
				ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();
				boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user,
						serviceContext, jCaptchaResponse);
				
				if (!isValid) {
					ErrorMsgModel error = new ErrorMsgModel();
					error.setMessage("Captcha incorrect");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Captcha incorrect");

					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
				}
			}

			Question question = QuestionLocalServiceUtil.updateQuestion(
				serviceContext.getCompanyId(), groupId, 0l, input.getFullname(),
				input.getEmail(), input.getContent(), input.getPublish(),
				input.getDomainCode(), input.getDomainName(),
				input.getGovAgencyCode(), input.getGovAgencyName(),
				input.getQuestionType(), input.getSubDomainCode(),
				input.getSubDomainName(), input.getPhone(), input.getAddress());
			if (question != null) {
				QuestionDetailModel result = new QuestionDetailModel();
				result.setContent(question.getContent());
				result.setCreateDate(
					APIDateTimeUtils.convertDateToString(
						question.getCreateDate()));
				result.setEmail(question.getEmail());
				result.setFullname(question.getFullname());
				result.setPublish(question.getPublish());
				result.setQuestionId(question.getQuestionId());
				result.setDomainCode(question.getDomainCode());
				result.setDomainName(question.getDomainName());
				result.setGovAgencyCode(question.getGovAgencyCode());
				result.setGovAgencyName(question.getGovAgencyName());
				result.setSubDomainCode(question.getSubDomainCode());
				result.setSubDomainName(question.getSubDomainName());
				
				ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(result);
				buildCrossOriginHeader(builder, request, METHOD_POST);
				
				return builder.build();
			}
			else {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_ERROR_PROCESS_DATABASE));
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getQuestions(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, Integer start, Integer end, Integer publish,
		String domainCode, String govAgencyCode, String keyword,
		String questionType, String answer, String subDomainCode,
		ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.debug("IN GET QUESTION: " + groupId);
		try {
			if (Validator.isNull(start)) {
				start = QueryUtil.ALL_POS;
				end = QueryUtil.ALL_POS;
			}
			// List<Question> lstQuestions = null;
			// if (publish == null) {
			// lstQuestions = QuestionLocalServiceUtil.findByG_PL(groupId, new
			// int[] { 0, 1 }, start, end);
			// }
			// else {
			// lstQuestions = QuestionLocalServiceUtil.findByG_PL(groupId, new
			// int[] { publish }, start, end);
			// }

			Boolean filterAnswer = null;
			if (Validator.isNotNull(answer)) {
				filterAnswer = Boolean.parseBoolean(answer);
			}
			List<Question> lstQuestions =
				QuestionLocalServiceUtil.findByQuerySearch(
					groupId, keyword, domainCode, govAgencyCode, publish,
					questionType, filterAnswer, subDomainCode, start, end);

			QuestionResultsModel result = new QuestionResultsModel();
			result.setTotal(
				QuestionLocalServiceUtil.countByQuerySearch(
					groupId, keyword, domainCode, govAgencyCode, publish,
					questionType, filterAnswer, subDomainCode));

			List<QuestionModel> lstModels = new ArrayList<>();
			if (lstQuestions != null && lstQuestions.size() > 0) {
				for (Question q : lstQuestions) {
					QuestionModel model = new QuestionModel();
					model.setContent(q.getContent());
					model.setCreateDate(
						APIDateTimeUtils.convertDateToString(
							q.getCreateDate()));
					model.setEmail(q.getEmail());
					model.setFullname(q.getFullname());
					model.setPublish(q.getPublish());
					model.setQuestionId(q.getQuestionId());
					model.setDomainCode(q.getDomainCode());
					model.setDomainName(q.getDomainName());
					model.setGovAgencyCode(q.getGovAgencyCode());
					model.setGovAgencyName(q.getGovAgencyName());
					model.setQuestionType(q.getQuestionType());
					model.setSubDomainCode(q.getSubDomainCode());
					model.setSubDomainName(q.getSubDomainName());

					int count = AnswerLocalServiceUtil.countByG_Q_PL(
						groupId, q.getQuestionId(), new int[] {
							0, 1
						});
					model.setAnswered(false);

					if (count > 0) {
						model.setAnswered(true);
					}
					lstModels.add(model);
				}
				result.getData().addAll(lstModels);
			}

			ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(result);
			buildCrossOriginHeader(builder, request, METHOD_GET);
			
			return builder.build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addAnswer(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long questionId, AnswerInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			Answer answer = AnswerLocalServiceUtil.updateAnswer(
				user.getUserId(), groupId, 0l, questionId, input.getContent(),
				input.getPublish());

			if (answer != null) {
				AnswerDetailModel result = new AnswerDetailModel();
				result.setAnswerId(answer.getAnswerId());
				result.setContent(answer.getContent());
				result.setCreateDate(
					APIDateTimeUtils.convertDateToString(
						answer.getCreateDate()));
				result.setModifiedDate(
					APIDateTimeUtils.convertDateToString(
						answer.getModifiedDate()));
				result.setPublish(answer.getPublish());
				result.setQuestionId(answer.getQuestionId());
				result.setUserName(answer.getUserName());
				ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(result);
				buildCrossOriginHeader(builder, request, METHOD_POST);
				return builder.build();
			}
			else {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.API_JSON_MESSAGE_PROCESSDBERROR));
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getAnswers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, long questionId, Integer start, Integer end,
		Integer publish, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			if (Validator.isNull(start)) {
				start = QueryUtil.ALL_POS;
				end = QueryUtil.ALL_POS;
			}
			List<Answer> lstAnswers = null;
			if (publish == null) {
				lstAnswers = AnswerLocalServiceUtil.findByG_Q_PL(
					groupId, questionId, new int[] {
						0, 1
					}, start, end);
			}
			else {
				lstAnswers = AnswerLocalServiceUtil.findByG_Q_PL(
					groupId, questionId, new int[] {
						publish
					}, start, end);
			}
			AnswerResultsModel result = new AnswerResultsModel();

			if (publish == null) {
				result.setTotal(
					AnswerLocalServiceUtil.countByG_Q_PL(
						groupId, questionId, new int[] {
							0, 1
						}));
			}
			else {
				result.setTotal(
					AnswerLocalServiceUtil.countByG_Q_PL(
						groupId, questionId, new int[] {
							publish
						}));
			}

			List<AnswerModel> lstModels = new ArrayList<>();
			for (Answer q : lstAnswers) {
				AnswerModel model = new AnswerModel();
				model.setAnswerId(q.getAnswerId());
				model.setContent(q.getContent());
				model.setCreateDate(
					APIDateTimeUtils.convertDateToString(q.getCreateDate()));
				model.setPublish(q.getPublish());
				model.setQuestionId(q.getQuestionId());

				lstModels.add(model);
			}
			result.getData().addAll(lstModels);

			ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(result);
			buildCrossOriginHeader(builder, request, METHOD_GET);
			
			return builder.build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateQuestion(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		QuestionInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long questionId = GetterUtil.getLong(id);
		try {
			Question question = QuestionLocalServiceUtil.updateQuestion(
				serviceContext.getCompanyId(), groupId, questionId,
				input.getFullname(), input.getEmail(), input.getContent(),
				input.getPublish(), input.getDomainCode(),
				input.getDomainName(), input.getGovAgencyCode(),
				input.getGovAgencyName(), input.getQuestionType(),
				input.getSubDomainCode(), input.getSubDomainName(), input.getPhone(), input.getAddress());
			if (question != null) {
				QuestionDetailModel result = new QuestionDetailModel();
				result.setContent(question.getContent());
				result.setCreateDate(
					APIDateTimeUtils.convertDateToString(
						question.getCreateDate()));
				result.setEmail(question.getEmail());
				result.setFullname(question.getFullname());
				result.setPublish(question.getPublish());
				result.setQuestionId(question.getQuestionId());
				result.setDomainCode(question.getDomainCode());
				result.setDomainName(input.getDomainName());
				result.setGovAgencyCode(question.getGovAgencyCode());
				result.setGovAgencyName(input.getGovAgencyName());
				result.setSubDomainCode(question.getSubDomainCode());
				result.setSubDomainName(input.getSubDomainName());

				ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(result);
				buildCrossOriginHeader(builder, request, METHOD_PUT);
				
				return builder.build();
			}
			else {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.API_JSON_MESSAGE_PROCESSDBERROR));
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteQuestion(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long questionId = GetterUtil.getLong(id);

		try {
			QuestionLocalServiceUtil.deleteQuestion(questionId);

			ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_DELETESUCCESS));
			buildCrossOriginHeader(builder, request, METHOD_DELETE);
			
			return builder.build();
		}
		catch (PortalException e) {
			_log.debug(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(
				MessageUtil.getMessage(ConstantUtils.API_MESSAGE_DELETEFAILURE)).build();
		}
	}

	@Override
	public Response updateAnswers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long questionId, long answerId, AnswerInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			Answer answer = AnswerLocalServiceUtil.updateAnswer(
				user.getUserId(), groupId, answerId, questionId,
				input.getContent(), input.getPublish());

			if (answer != null) {
				AnswerDetailModel result = new AnswerDetailModel();
				result.setAnswerId(answer.getAnswerId());
				result.setContent(answer.getContent());
				result.setCreateDate(
					APIDateTimeUtils.convertDateToString(
						answer.getCreateDate()));
				result.setModifiedDate(
					APIDateTimeUtils.convertDateToString(
						answer.getModifiedDate()));
				result.setPublish(answer.getPublish());
				result.setQuestionId(answer.getQuestionId());
				result.setUserName(answer.getUserName());
				ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(result);
				buildCrossOriginHeader(builder, request, METHOD_PUT);
				
				return builder.build();
			}
			else {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_ERROR_PROCESS_DATABASE));
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteAnswers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long questionId, long answerId) {

		try {
			AnswerLocalServiceUtil.deleteAnswer(answerId);
			
			ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_DELETESUCCESS));
			buildCrossOriginHeader(builder, request, METHOD_DELETE);
			
			return builder.build();
		}
		catch (PortalException e) {
			_log.debug(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(
				MessageUtil.getMessage(ConstantUtils.API_MESSAGE_DELETEFAILURE)).build();
		}
	}

	@Override
	public Response optionQuestions(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, Integer start, Integer end, Integer publish,
		String govAgencyCode, String keyword, String questionType,
		String answer, String subDomainCode, ServiceContext serviceContext) {

		ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK);
		buildCrossOriginHeader(builder, request, METHOD_GET + StringPool.COMMA + METHOD_POST);
		
		return builder.build();
	}

	@Override
	public Response detailQuestion(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK);
		buildCrossOriginHeader(builder, request, METHOD_GET + StringPool.COMMA + METHOD_PUT + StringPool.COMMA + METHOD_DELETE);
		return builder.build();
	}

	@Override
	public Response optionAnswers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, long questionId, Integer start, Integer end,
		Integer publish, ServiceContext serviceContext) {

		ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK);
		buildCrossOriginHeader(builder, request, METHOD_GET + StringPool.COMMA + METHOD_POST);
		return builder.build();
	}

	@Override
	public Response optionsDetailAnswers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long questionId, long answerId) {

		ResponseBuilder builder = Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK);
		buildCrossOriginHeader(builder, request, METHOD_GET + StringPool.COMMA + METHOD_PUT + StringPool.COMMA + METHOD_DELETE);
		return builder.build();		
	}
	private final String SERVER_DVC = "SERVER_DVC";
	
	@Override
	public Response proxyQuestion(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String url,
		String method, String data) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			ServerConfig sc =
				ServerConfigLocalServiceUtil.getByCode(groupId, SERVER_DVC);
			if (sc != null) {
				JSONObject configObj =
					JSONFactoryUtil.createJSONObject(sc.getConfigs());
				String serverUrl = StringPool.BLANK;
				String authStrEnc = StringPool.BLANK;

				String apiUrl;

				StringBuilder sb = new StringBuilder();
				try {
					URL urlVal = null;
					String groupIdRequest = StringPool.BLANK;
					StringBuilder postData = new StringBuilder();
					JSONObject dataObj = JSONFactoryUtil.createJSONObject(data);
					Iterator<?> keys = dataObj.keys();
					while (keys.hasNext()) {
						String key = (String) keys.next();
						if (!StringPool.BLANK.equals(postData.toString())) {
							postData.append(StringPool.AMPERSAND);
						}
						postData.append(key);
						postData.append("=");
						postData.append(dataObj.get(key));
					}

					if (configObj.has(SyncServerTerm.SERVER_USERNAME) &&
						configObj.has(SyncServerTerm.SERVER_SECRET) &&
						configObj.has(SyncServerTerm.SERVER_URL) &&
						configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
						authStrEnc = Base64.getEncoder().encodeToString(
							(configObj.getString(
								SyncServerTerm.SERVER_USERNAME) + StringPool.COLON +
								configObj.getString(
									SyncServerTerm.SERVER_SECRET)).getBytes());

						serverUrl =
							configObj.getString(SyncServerTerm.SERVER_URL);
						groupIdRequest =
							configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
					}

					apiUrl = serverUrl + url;
					if (METHOD_GET.equals(method)) {
						urlVal = new URL(apiUrl + StringPool.QUESTION + postData.toString());
					}
					else {
						urlVal = new URL(apiUrl);
					}

					java.net.HttpURLConnection conn =
						(java.net.HttpURLConnection) urlVal.openConnection();
					conn.setRequestProperty(Field.GROUP_ID, groupIdRequest);
					conn.setRequestMethod(method);
					conn.setRequestProperty(HttpHeaders.ACCEPT, ConstantUtils.CONTENT_TYPE_JSON);
					String authorization = String.format(MessageUtil.getMessage(ConstantUtils.HTTP_HEADER_BASICAUTH), authStrEnc);
					
					conn.setRequestProperty(
						HttpHeaders.AUTHORIZATION, authorization);

					if (METHOD_POST.equals(method) || METHOD_PUT.equals(method)) {
						conn.setRequestProperty(
							HttpHeaders.CONTENT_TYPE,
							ConstantUtils.CONTENT_TYPE_XXX_FORM_URLENCODED);
						conn.setRequestProperty(
							ConstantUtils.CONTENT_LENGTH, StringPool.BLANK + Integer.toString(
								postData.toString().getBytes().length));

						conn.setUseCaches(false);
						conn.setDoInput(true);
						conn.setDoOutput(true);

						OutputStream os = conn.getOutputStream();
						os.write(postData.toString().getBytes());
						os.close();
					}

					BufferedReader brf = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));

					int cp;
					while ((cp = brf.read()) != -1) {
						sb.append((char) cp);
					}

					return Response.status(HttpURLConnection.HTTP_OK).entity(
						sb.toString()).build();
				}
				catch (IOException e) {
					_log.debug(e);
					_log.debug(
						"Something went wrong while reading/writing in stream!!");
				}
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(
					StringPool.BLANK).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(
					StringPool.BLANK).build();
			}
		}
		catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}
}
