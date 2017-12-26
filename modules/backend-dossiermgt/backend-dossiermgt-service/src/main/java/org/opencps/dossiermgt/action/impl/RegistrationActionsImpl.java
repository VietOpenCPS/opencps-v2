package org.opencps.dossiermgt.action.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.action.RegistrationActions;
import org.opencps.dossiermgt.action.RegistrationLogActions;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationLog;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLogLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationActionsImpl implements RegistrationActions {
	
	Log _log = LogFactoryUtil.getLog(RegistrationActionsImpl.class);
	
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
		String content = "";
		RegistrationLogActions registrationLogActions = new RegistrationLogActionsImpl();
		List<RegistrationLog> lstRegistrationLog = registrationLogActions.getRegistrationLogbyId(groupId,
				registrationId);
		if (lstRegistrationLog.size() == 0) {
			content = "1";
		} else {
			content = String.valueOf(Integer.valueOf(lstRegistrationLog.get(0).getContent()) + 1);
		}

		if (Validator.isNotNull(lstRegistrationFormchange)) {
			if (registrationState == 2 || registrationState == 3) {
				addLog("", groupId, userId, registrationId, content, lstRegistrationFormchange);
			}
		}

		// create referenceUid
		String referenceUid = UUID.randomUUID().toString();

		// get lstRegistrationTemplate
		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplateses(start, end);

		// add registrationForm
		for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
			int fileEntryId = getfileEntryId(registrationTemplates.getSampleData(),
					registrationTemplates.getFormScript(), registrationTemplates.getFormReport());

			RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, registrationId, referenceUid,
					registrationTemplates.getFormNo(), registrationTemplates.getFormName(),
					registrationTemplates.getSampleData(), registrationTemplates.getFormScript(),
					registrationTemplates.getFormReport(), fileEntryId, false, false, serviceContext);
		}

		return RegistrationLocalServiceUtil.updateRegistration(groupId, registrationId, applicantName, applicantIdType,
				applicantIdNo, applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode,
				wardName, contactName, contactTelNo, contactEmail, govAgencyCode, govAgencyName, registrationState,
				registrationClass, serviceContext);

	}

	@Override
	public Registration delete(long registrationId) throws PortalException {
		return RegistrationLocalServiceUtil.deleteRegistration(registrationId);

	}

	@Override
	public Registration getDetail(long registrationId) throws PortalException {
		return RegistrationLocalServiceUtil.getRegistration(registrationId);

	}

	@Override
	public RegistrationLog addLog(String author, long groupId, long userId, long registrationId, String content,
			List<RegistrationForm> payload) {
		JSONArray jsArray = JSONFactoryUtil.createJSONArray();
		for (RegistrationForm registrationForm : payload) {
			JSONObject mediaItemsJsonObject = JSONFactoryUtil.createJSONObject();

			mediaItemsJsonObject.put("registrationFormId", registrationForm.getRegistrationFormId());
			mediaItemsJsonObject.put("groupId", registrationForm.getGroupId());
			mediaItemsJsonObject.put("userId", registrationForm.getUserId());
			mediaItemsJsonObject.put("createDate", registrationForm.getCreateDate());
			mediaItemsJsonObject.put("modifiedDate", registrationForm.getModifiedDate());
			mediaItemsJsonObject.put("registrationId", registrationForm.getRegistrationId());
			mediaItemsJsonObject.put("referenceUid", registrationForm.getReferenceUid());
			mediaItemsJsonObject.put("formNo", registrationForm.getFormNo());
			mediaItemsJsonObject.put("formName", registrationForm.getFormName());
			mediaItemsJsonObject.put("formData", registrationForm.getFormData());
			mediaItemsJsonObject.put("formScript", registrationForm.getFormScript());
			mediaItemsJsonObject.put("formReport", registrationForm.getFormReport());
			mediaItemsJsonObject.put("fileEntryId", registrationForm.getFileEntryId());
			mediaItemsJsonObject.put("isNew", registrationForm.getIsNew());
			mediaItemsJsonObject.put("removed", registrationForm.getRemoved());
			jsArray.put(mediaItemsJsonObject);
		}
		// jsonObj.put("result", jsArray.to);
		String strPayload = jsArray.toJSONString();
		return RegistrationLogLocalServiceUtil.addLog(author, groupId, userId, registrationId, content, strPayload);

	}

	public int getfileEntryId(String formdata, String formScript, String formReport) {

		int fileEntryId = 0;

		return fileEntryId;
	}

	@Override
	public JSONObject getRegistrations(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = RegistrationLocalServiceUtil.searchLucene(userId,params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

//			long total = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", hits.toList().size());

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
}