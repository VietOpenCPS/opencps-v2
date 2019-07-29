package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.action.EFormActions;
import org.opencps.dossiermgt.action.util.EFormNumberGenerator;
import org.opencps.dossiermgt.model.EForm;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.EFormLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;

public class EFormActionsImpl implements EFormActions{

	private static final Log _log = LogFactoryUtil.getLog(EFormActionsImpl.class);
	@Override
	public JSONObject getEFormList(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = EFormLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = EFormLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public EForm updateEForm(long userId, long groupId, long eFormId, String eFormNo, long serviceInfoId,
			String fileTemplateNo, String eFormName, Long formScriptFileId, Long formReportFileId, String eFormData,
			String email, String secret, ServiceContext serviceContext) {
		
		try {
			String eFormNoPattern = StringPool.BLANK;
			EForm eform = null;
			if (Validator.isNotNull(eFormNo)) {
				eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);
			}
			eFormId = eform != null ? eform.getEFormId() : 0;
			secret = eform != null ? eform.getSecret() : StringPool.BLANK;

			ServiceInfo service = ServiceInfoLocalServiceUtil.fetchServiceInfo(serviceInfoId);
			if (service != null) {
				ServiceFileTemplate fileTemplate = ServiceFileTemplateLocalServiceUtil
						.fetchByF_serviceInfoId_fileTemplateNo(service.getServiceInfoId(), fileTemplateNo);
				
				if (fileTemplate != null) {
					formScriptFileId = fileTemplate.getFormScriptFileId();
					formReportFileId = fileTemplate.getFormReportFileId();
					eFormNoPattern = fileTemplate.getEFormNoPattern();
				}
			}

			if (Validator.isNull(eFormNo)) {
				_log.info("START GENERATE EFORM");
				eFormNo = EFormNumberGenerator.generateServiceFileNumber(groupId, serviceContext.getCompanyId(),
						service != null ? service.getServiceCode() : StringPool.BLANK, eFormNoPattern);
			}
			if (Validator.isNull(secret)) {
				secret = PwdGenerator.getPinNumber();
			}

			return EFormLocalServiceUtil.updateEForm(userId, groupId, eFormId, eFormNo,
					service != null ? service.getServiceCode() : StringPool.BLANK, fileTemplateNo, eFormName,
					formScriptFileId, formReportFileId, eFormData, email, secret, serviceContext);
		} catch (Exception e) {
			_log.error(e);
		}

		return null;
	}

	@Override
	public EForm updateDataByEFormNo(long eFormId, String eFormData, ServiceContext serviceContext) {

		try {
			return EFormLocalServiceUtil.updateDataByEFormNo(eFormId, eFormData, serviceContext);
		} catch (Exception e) {
			_log.error(e);
			return null;
		}
	}

	@Override
	public EForm deleteEFormById(long eFormId, ServiceContext serviceContext) {

		try {
			return EFormLocalServiceUtil.deleteEForm(eFormId);
		} catch (Exception e) {
			_log.error(e);
			return null;
		}
	}

}
