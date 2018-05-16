
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.evaluation.model.EvaluationModels;
import org.opencps.api.evaluation.model.EvaluationResultDetailModel;
import org.opencps.auth.utils.APIDateTimeUtils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import backend.cmc.model.Evaluation;

public class EvaluationUtils {

	private static final Log _log = LogFactoryUtil.getLog(EvaluationUtils.class);

	public static EvaluationResultDetailModel mappingEvaluationResultDetail(Evaluation evaluation) {
		if (evaluation == null) {
			return null;
		}

		EvaluationResultDetailModel model = new EvaluationResultDetailModel();
		try {
			
			model.setCompanyId(evaluation.getCompanyId());
			model.setCreateDate(APIDateTimeUtils.convertDateToString(evaluation.getCreateDate(), "yyyyMMddHHmmss"));
			model.setModifiedDate(APIDateTimeUtils.convertDateToString(evaluation.getModifiedDate(), "yyyyMMddHHmmss"));
			model.setEmployeeId(evaluation.getEmployeeId());
			model.setEmployeeName(evaluation.getEmployeeName());
			model.setScore(evaluation.getScore());
		} catch (Exception e) {
			_log.error(e);
		}
		return model;
	}

	public static List<EvaluationModels> mappinglstEvaluation(List<Evaluation> lstEvaluation){
		List<EvaluationModels> result = new ArrayList<EvaluationModels>();
		try {
			
			for(Evaluation evaluation : lstEvaluation){
				EvaluationModels model = new EvaluationModels();
				
				model.setEmployeeId(evaluation.getEmployeeId());
				model.setEmployeeName(evaluation.getEmployeeName());
				model.setScore(evaluation.getScore());
				
				result.add(model);
			}
			return result;
		} catch (Exception e) {
			_log.error(e);
		}
		return result;
	}
}
