package org.opencps.dossiermgt.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationFormActionsImpl implements RegistrationFormActions {

	private static final Log _log = LogFactoryUtil.getLog(RegistrationFormActionsImpl.class);

	@Override
	public RegistrationForm insert(long groupId, long companyId, long registrationId, String referenceUid, String formNo,
			String formName, String formData, String formScript, String formReport, long fileEntryId, boolean isNew,
			boolean removed, ServiceContext serviceContext) throws PortalException {

		return RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, companyId, registrationId, referenceUid, formNo,
				formName, formData, formScript, formReport, fileEntryId, isNew, removed, serviceContext);
	}

	@Override
	public RegistrationForm update(RegistrationForm model) {
		return RegistrationFormLocalServiceUtil.updateRegistrationForm(model);

	}

	@Override
	public RegistrationForm deleteRegistrationForm(long groupId, long registrationId, String referenceUid)
			throws PortalException {

		return RegistrationFormLocalServiceUtil.deleteRegistrationForm(groupId, registrationId, referenceUid);

	}

	@Override
	public RegistrationForm getDetail(long registrationFormId) throws PortalException {
		return RegistrationFormLocalServiceUtil.getRegistrationForm(registrationFormId);

	}

	@Override
	public List<RegistrationForm> getFormbyRegId(long groupId, long registrationId) throws PortalException {

		List<RegistrationForm> lstRegistrationForm = RegistrationFormLocalServiceUtil.getFormsbyRegId(groupId,
				registrationId);

		return lstRegistrationForm;
	}

	@Override
	public void addRegistrationFormbaseonRegTemplate(long groupId, long companyId, long registrationId, String govAgencyCode,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// get lstRegistrationTemplate
		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplatesbyGOVCODE(groupId, govAgencyCode);

		// add registrationForm
		for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
			// create referenceUid
			String referenceUid = UUID.randomUUID().toString();

			RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, companyId, registrationId, referenceUid,
					registrationTemplates.getFormNo(), registrationTemplates.getFormName(),
					registrationTemplates.getSampleData(), registrationTemplates.getFormScript(),
					registrationTemplates.getFormReport(), 0, false, false, serviceContext);
		}
	}
	
	@Override
	public List<RegistrationForm> deleteRegistrationForms(long groupId, long registrationId) throws PortalException {

		return RegistrationFormLocalServiceUtil.deleteRegistrationForms(groupId, registrationId);

	}
	
	@Override
	public RegistrationForm updateRegFormFormData(long groupId, long registrationId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return RegistrationFormLocalServiceUtil.updateFormData(groupId, registrationId, referenceUid, formData, serviceContext);
	}
	
	@Override
	public RegistrationForm updateIsNew(long groupId, long registrationId, String referenceUid, boolean isNew,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return RegistrationFormLocalServiceUtil.updateIsNew(groupId, registrationId, referenceUid, isNew,
				serviceContext);
	}
	
	@Override
	public JSONObject getRegistrationForms(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = RegistrationFormLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = RegistrationFormLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	//18
	@Override
	public JSONArray getFormDataByFormNo(long groupId, long registrationId, String formNo, String[] splitProperties) throws JSONException {
		// TODO Auto-generated method stub
		List<RegistrationForm> registrationList = RegistrationFormLocalServiceUtil.getFormDataByFormNo(groupId, 
				registrationId, formNo);
//		List<JSONObject> formDataList = new ArrayList<JSONObject>();
		JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
		for (RegistrationForm reg : registrationList) {
			String formData = reg.getFormData();
			formDataArr.put(JSONFactoryUtil.createJSONObject(formData));
		}
		Boolean flag = false;
		JSONArray formDataFilterArr = JSONFactoryUtil.createJSONArray();
		if (splitProperties != null) {
			for (int i = 0; i < formDataArr.length(); i++) {
				JSONObject jsonFormData = formDataArr.getJSONObject(i);
				Iterator<String> keyForm = jsonFormData.keys();
				List<String> keyFormDataList = new ArrayList<String>();
				while(keyForm.hasNext()) {
					String keys = keyForm.next();
					keyFormDataList.add(keys);
				}
				for (String parts : splitProperties) {
					for (String key : keyFormDataList) {
						if (Validator.isNotNull(parts) && parts.equals(key)) {
							flag = true;
						} else {
							flag = false;
						}
					}
				}
				if (flag) {
					JSONObject formDataDetail = JSONFactoryUtil.createJSONObject();
					for (String parts : splitProperties) {
						formDataDetail.put(parts, jsonFormData.get(parts));
					}
					formDataFilterArr.put(formDataDetail);
				}
			}
		} else {
			return formDataArr;
		}
		return formDataFilterArr;
	}

	//18
//	@Override
//	public JSONObject getFormDataByFormNo(long companyId, LinkedHashMap<String, Object> params,
//			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
//		JSONObject result = JSONFactoryUtil.createJSONObject();
//
//		Hits hits = null;
//
//		long userId = serviceContext.getUserId();
//
//		SearchContext searchContext = new SearchContext();
//		searchContext.setCompanyId(companyId);
//
//		try {
//
//			hits = RegistrationFormLocalServiceUtil.searchLucene(userId, params, sorts, start, end, searchContext);
//
//			List<Document> test = hits.toList();
//			result.put("data", test);
//			
//
//			long total = RegistrationFormLocalServiceUtil.countLucense(userId, params, sorts, start, end, searchContext);
//
//			result.put("total", total);
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//		return result;
//	}
}
