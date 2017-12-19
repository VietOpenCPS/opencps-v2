package org.opencps.dossiermgt.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLogLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationActionsImpl implements RegistrationActions {

	@Override
	public Registration insert(long groupId, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, ServiceContext serviceContext) throws SystemException, PortalException {

		return RegistrationLocalServiceUtil.insert(groupId, applicantName, applicantIdType, applicantIdNo,
				applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
				contactName, contactTelNo, contactEmail, govAgencyCode, govAgencyName, registrationState,
				registrationClass, serviceContext);
	}

	@Override
	public Registration updateRegistration(long groupId, long registrationId, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String govAgencyCode, String govAgencyName,
			int registrationState, String registrationClass, ServiceContext serviceContext) throws PortalException {

		int start = -1, end = -1;

		long userId = serviceContext.getUserId();

		List<RegistrationForm> lstRegistrationForm = RegistrationFormLocalServiceUtil.getRegistrationForms(start, end);

		List<RegistrationForm> lstRegistrationFormchange = new ArrayList<RegistrationForm>();
		
		// changeType removed in registrationForm
		for (RegistrationForm registrationForm : lstRegistrationForm) {
			if (registrationForm.isRemoved() == false) {
				registrationForm.setRemoved(true);
				RegistrationForm registrationFormChanged = RegistrationFormLocalServiceUtil
						.updateRegistrationForm(registrationForm);
				lstRegistrationFormchange.add(registrationFormChanged);
			}
		}
		
		// add registrationLog
		if(Validator.isNotNull(lstRegistrationFormchange)){
			addLog("", groupId, userId, registrationId, "", lstRegistrationFormchange);
		}

		// create referenceUid
		String referenceUid = UUID.randomUUID().toString();

		//get lstRegistrationTemplate
		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplateses(start, end);
		
		//add registrationForm
		for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
			int fileEntryId = getfileEntryId(registrationTemplates.getSampleData(),
					registrationTemplates.getFormScript(), registrationTemplates.getFormReport());

			RegistrationFormLocalServiceUtil.addRegistrationForm(groupId,
					registrationId, referenceUid, registrationTemplates.getFormNo(),
					registrationTemplates.getFormName(), registrationTemplates.getSampleData(),
					registrationTemplates.getFormScript(), registrationTemplates.getFormReport(), fileEntryId, false,
					false, serviceContext);
		}

		return RegistrationLocalServiceUtil.updateRegistration(groupId, registrationId, applicantName, applicantIdType,
				applicantIdNo, applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode,
				wardName, contactName, contactTelNo, contactEmail, govAgencyCode, govAgencyName, registrationState,
				registrationClass, serviceContext);

	}

	@Override
	public void delete(long registrationId) throws PortalException {
		RegistrationLocalServiceUtil.deleteRegistration(registrationId);

	}

	@Override
	public Registration getDetail(long registrationId) throws PortalException {
		return RegistrationLocalServiceUtil.getRegistration(registrationId);

	}

	@Override
	public RegistrationLog addLog(String author, long groupId, long userId, long registrationId, String content,
			List<RegistrationForm> payload) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("result", payload);
		String strPayload = jsonObj.toJSONString();
		return RegistrationLogLocalServiceUtil.addLog(author, groupId, userId, registrationId, content, strPayload);

	}

	public int getfileEntryId(String formdata, String formScript, String formReport) {

		int fileEntryId = 0;

		return fileEntryId;
	}
}