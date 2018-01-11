package org.opencps.dossiermgt.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
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

public class DeliverableActionsImpl implements DeliverableActions {

	Log _log = LogFactoryUtil.getLog(DeliverableActionsImpl.class);

	@Override
	public JSONObject getListDeliverable(String state, String agency, String type, String applicant) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Deliverable> listDeliverable = DeliverableLocalServiceUtil.getListDeliverable(state, agency, type,
				applicant);
		result.put("data", listDeliverable);
		result.put("total", listDeliverable.size());
		return result;
	}

	@Override
	public void addDeliverable(DeliverableImpl model) {
		DeliverableLocalServiceUtil.insert(model);
	}

	@Override
	public Deliverable getListDeliverableDetail(Long deliverableId) throws NoSuchDeliverableException {
//		Deliverable deliverable = DeliverableLocalServiceUtil.getListDeliverableDetai(deliverableId);
		Deliverable deliverable = null;
		return deliverable;
	}

//	@Override
//	public void deleteDeliverable(Long deliverableId) throws PortalException {
//		DeliverableLocalServiceUtil.deleteDeliverable(deliverableId);
//
//	}

	//12
	@Override
	public JSONArray getFormDataByTypecode(long groupId, String registrationId, String typeCode,
			String[] splitProperties) {
		// TODO Auto-generated method stub
		List<Deliverable> registrationList = new ArrayList<Deliverable>();
//		List<Deliverable> registrationList = DeliverableLocalServiceUtil.getFormDataByTypeCode(groupId, 
//				registrationId, typeCode);
//				List<JSONObject> formDataList = new ArrayList<JSONObject>();
		JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
//		for (Deliverable reg : registrationList) {
//			String formData = reg.getFormData();
//			formDataArr.put(JSONFactoryUtil.createJSONObject(formData));
//		}
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

	@Override
	public Deliverable addDeliverable(long groupId, String deliverableType, String deliverableCode,
			String govAgencyCode, String applicantIdNo, String applicantName, String subject, String issueDate,
			String expireDate, String revalidate, String deliverableState, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DeliverableLocalServiceUtil.addDeliverable(groupId, deliverableType, deliverableCode,
				govAgencyCode, applicantIdNo, applicantName, subject, issueDate, expireDate,
				revalidate, deliverableState, serviceContext);
	}

	@Override
	public JSONObject getListDeliverable(long companyId, LinkedHashMap<String, Object> params, Sort[] sorts, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		Hits hits = null;
		
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);
		
		try {
			
			hits = DeliverableLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
			
			result.put("data", hits.toList());
			
			long total = DeliverableLocalServiceUtil.countLucene(params, searchContext);
			
			result.put("total", total);
			
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}

	@Override
	public Deliverable getDetailById(Long id, long groupId) throws NoSuchDeliverableException {
		return DeliverableLocalServiceUtil.getDeliverableDetail(id, groupId);
	}

	@Override
	public Deliverable updateDeliverable(long groupId, long id, String subject, String issueDate, String expireDate,
			String revalidate, String deliverableState, String deliverableAction, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DeliverableLocalServiceUtil.updateDeliverable(groupId, id, subject, issueDate, expireDate, revalidate,
				deliverableState, deliverableAction, serviceContext);
	}

	@Override
	public Deliverable deleteById(Long id, long groupId) throws NoSuchDeliverableException {
		// TODO Auto-generated method stub
		return DeliverableLocalServiceUtil.deleteDeliverable(groupId, id);
	}

	@Override
	public JSONObject getFormDataById(long companyId, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		Hits hits = null;
		
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);
		
		try {
			
			hits = DeliverableLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
			
			result.put("data", hits.toList());
			
//			long total = DeliverableLocalServiceUtil.countLucene(params, searchContext);
//			
//			result.put("total", total);
			
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}

	//7
	@Override
	public Deliverable updateFormData(long groupId, long id, String formData, ServiceContext serviceContext) throws NoSuchDeliverableException{
		return DeliverableLocalServiceUtil.updateFormData(groupId, id, formData, serviceContext);
	}
}
