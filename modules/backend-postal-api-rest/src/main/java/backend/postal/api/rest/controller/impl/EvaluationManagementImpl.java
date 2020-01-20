package backend.postal.api.rest.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.EvaluationUtils;
import org.opencps.api.evaluation.model.EvaluationInputModel;
import org.opencps.api.evaluation.model.EvaluationModels;
import org.opencps.api.evaluation.model.EvaluationResultDetailModel;
import org.opencps.api.evaluation.model.EvaluationResultsModel;
import org.opencps.api.service.util.ConfigConstants;
import org.opencps.api.service.util.ConfigProps;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.feedback.model.Evaluation;
import backend.feedback.service.EvaluationLocalServiceUtil;
import backend.postal.api.rest.controller.EvaluationManagement;

public class EvaluationManagementImpl implements EvaluationManagement {
	private static Log _log = LogFactoryUtil.getLog(EvaluationManagementImpl.class);

	@Override
	public Response getEvaluation(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long employeeId, String score) {

//		BackendAuth auth = new BackendAuthImpl();
		List<Evaluation> lstEvaluation = new ArrayList<Evaluation>();
		try {
			EvaluationResultsModel result = new EvaluationResultsModel();
			
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			if(StringPool.BLANK.equals(score)){
				score = VnPostTerm.EVALUATION_SCORE_DEFAULT;
			}
			int number = Integer.parseInt(score);
			
			if (number == 0) {
				lstEvaluation = EvaluationLocalServiceUtil.getEvaluationbyEmployeeId(employeeId);
			} else {
				lstEvaluation = EvaluationLocalServiceUtil.getEvaluationbyEmployeeIdScore(employeeId, number);
			}
			
			List<EvaluationModels> lstEvaluationModels = EvaluationUtils.mappinglstEvaluation(lstEvaluation);
			
			result.setTotal(lstEvaluationModels.size());
			result.getData().addAll(lstEvaluationModels);
			
			return Response.status(200).entity(result).build();
			
		} catch (Exception e) {
			return processException(e);
		}
	}
	
	public Response getEvaluationScore(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long employeeId, int score) {

//		BackendAuth auth = new BackendAuthImpl();
		List<Evaluation> lstEvaluation = new ArrayList<Evaluation>();
		try {
			EvaluationResultsModel result = new EvaluationResultsModel();
			
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			
			if (score == 0) {
				lstEvaluation = EvaluationLocalServiceUtil.getEvaluationbyEmployeeId(employeeId);
			} else {
				lstEvaluation = EvaluationLocalServiceUtil.getEvaluationbyEmployeeIdScore(employeeId, score);
			}
			
			List<EvaluationModels> lstEvaluationModels = EvaluationUtils.mappinglstEvaluation(lstEvaluation);
			
			result.setTotal(lstEvaluationModels.size());
			result.getData().addAll(lstEvaluationModels);
			
			return Response.status(200).entity(result).build();
			
		} catch (Exception e) {
			return processException(e);
		}
	}
	
	@Override
	public Response addEvaluation(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, EvaluationInputModel model, long employeeId) {
//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			Evaluation evaluation = EvaluationLocalServiceUtil.addEvaluation(groupId, employeeId,
					model.getEmployeeName(), model.getScore(), serviceContext);

			EvaluationResultDetailModel result = EvaluationUtils.mappingEvaluationResultDetail(evaluation);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	private Response processException(Exception e) {
		ErrorMsg error = new ErrorMsg();

		if (e instanceof UnauthenticationException) {
			error.setMessage(ConfigProps.get(ConfigConstants.EVALUATION_UNAUTHEN_EX));
			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
			error.setDescription(ConfigProps.get(ConfigConstants.EVALUATION_UNAUTHEN_EX));

			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		} else {
			if (e instanceof UnauthorizationException) {
				error.setMessage(ConfigProps.get(ConfigConstants.EVALUATION_UNAUTHOR_EX));
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription(ConfigProps.get(ConfigConstants.EVALUATION_UNAUTHOR_EX));

				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

			} else {
				_log.error(e);

				error.setMessage(ConfigProps.get(ConfigConstants.EVALUATION_NO_CONTENT_EX));
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription(ConfigProps.get(ConfigConstants.EVALUATION_NO_CONTENT_EX));

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}
}
