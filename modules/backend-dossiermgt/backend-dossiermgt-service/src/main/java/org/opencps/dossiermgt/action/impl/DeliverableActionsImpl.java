package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
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
	public JSONObject getListDeliverable(int state, String agency, String type, String applicant) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Deliverable> listDeliverable = DeliverableLocalServiceUtil.getListDeliverable(state, agency, type,
				applicant);
		result.put("data", listDeliverable);
		result.put("total", listDeliverable.size());
		return result;
	}

	//12
	@Override
	public JSONObject getFormDataByTypecode(long companyId, LinkedHashMap<String, Object> params, Sort[] object, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		Hits hits = null;
		_log.info("companyId: "+companyId);
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);
		
		try {
			
			hits = DeliverableLocalServiceUtil.searchLucene(params, object, start, end, searchContext);
			
			result.put("data", hits.toList());
			
			long total = DeliverableLocalServiceUtil.countLucene(params, searchContext);
			
			result.put("total", total);
			
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}

	@Override
	public Deliverable addDeliverable(long groupId, String deliverableType, String deliverableCode,
			String govAgencyCode, String govAgencyName, String applicantIdNo, String applicantName, String subject,
			String issueDate, String expireDate, String revalidate, String deliverableState,
			ServiceContext serviceContext) {
		return DeliverableLocalServiceUtil.addDeliverable(groupId, deliverableType, deliverableCode,
				govAgencyCode, govAgencyName, applicantIdNo, applicantName, subject, issueDate, expireDate,
				revalidate, deliverableState, serviceContext);
	}

	@Override
	public JSONObject getListDeliverable(long userId, long companyId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
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
	public Deliverable getDetailById(long id) throws NoSuchDeliverableException {
		return DeliverableLocalServiceUtil.getDeliverableDetail(id);
	}

	@Override
	public Deliverable updateDeliverable(long groupId, long id, String subject, String issueDate, String expireDate,
			String revalidate, String deliverableState, String deliverableAction, ServiceContext serviceContext) {
		return DeliverableLocalServiceUtil.updateDeliverable(groupId, id, subject, issueDate, expireDate, revalidate,
				deliverableState, deliverableAction, serviceContext);
	}

	@Override
	public Deliverable deleteById(long id) throws PortalException {
		return DeliverableLocalServiceUtil.deleteDeliverable(id);
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

	@Override
	public List<Deliverable> getDeliverableByState(String strDeliverableCode, int state) {
		if (Validator.isNotNull(strDeliverableCode)) {
			return DeliverableLocalServiceUtil.findDeliverableByState(strDeliverableCode, state);
		} else {
			return null;
		}
	}

}
