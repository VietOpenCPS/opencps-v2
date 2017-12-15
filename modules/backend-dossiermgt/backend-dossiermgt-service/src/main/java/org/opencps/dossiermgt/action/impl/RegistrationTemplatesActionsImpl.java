package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.RegistrationTemplatesActions;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class RegistrationTemplatesActionsImpl implements RegistrationTemplatesActions {

	private static final Log _log = LogFactoryUtil.getLog(RegistrationTemplatesActionsImpl.class);

	@Override
	public JSONObject getRegistrationTemplates(long groupId, int start, int end) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			if (start == 0) {
				start = -1;
				end = -1;
			}

			List<RegistrationTemplates> lstRegistrationTemplates = RegistrationTemplatesLocalServiceUtil
					.getRegistrationTemplateses(start, end);

			int total = RegistrationTemplatesLocalServiceUtil.getRegistrationTemplatesesCount();

			result.put("total", total);
			result.put("lstRegistrationTemplate", lstRegistrationTemplates);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public RegistrationTemplates addRegistrationTemplate(long groupId, String govAgencyCode, String govAgencyName,
			String formNo, String formName, boolean multiple, String formScript, String formReport, String sampleData,
			ServiceContext serviceContext) throws PortalException, SystemException {

		return RegistrationTemplatesLocalServiceUtil.addRegistrationTemplates(groupId, govAgencyCode, govAgencyName,
				formNo, formName, multiple, formScript, formReport, sampleData, serviceContext);
	}

	@Override
	public RegistrationTemplates updateRegistrationTemplates(long groupId, long registrationTemplateId,
			String govAgencyCode, String govAgencyName, String formNo, String formName, boolean multiple,
			String formScript, String formReport, String sampleData, ServiceContext serviceContext)
			throws SystemException, PortalException {

		return RegistrationTemplatesLocalServiceUtil.updateRegistrationTemplates(groupId, registrationTemplateId,
				govAgencyCode, govAgencyName, formNo, formName, multiple, formScript, formReport, sampleData,
				serviceContext);
	}

	@Override
	public RegistrationTemplates removeRegistrationTemplate(long groupId, String registrationTemplateId)
			throws PortalException {

		return RegistrationTemplatesLocalServiceUtil.removeRegistrationTemplate(groupId, registrationTemplateId);
	}

	@Override
	public RegistrationTemplates updateFormScript(long groupId, long registrationTemplateId, String formScript,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return RegistrationTemplatesLocalServiceUtil.updateFormScript(groupId, registrationTemplateId, formScript,
				serviceContext);
	}

	@Override
	public RegistrationTemplates updateFormReport(long groupId, long registrationTemplatesId, String formReport,
			ServiceContext serviceContext) throws PortalException, SystemException {

		return RegistrationTemplatesLocalServiceUtil.updateFormReport(groupId, registrationTemplatesId, formReport,
				serviceContext);
	}

	@Override
	public RegistrationTemplates updateSampledata(long groupId, long registrationTemplatesId, String sampleData,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return RegistrationTemplatesLocalServiceUtil.updateSampledata(groupId, registrationTemplatesId, sampleData,
				serviceContext);
	}
}
