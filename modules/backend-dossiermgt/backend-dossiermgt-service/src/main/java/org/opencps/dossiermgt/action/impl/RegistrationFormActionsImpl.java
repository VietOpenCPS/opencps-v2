package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

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
//		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
//				.getRegistrationTemplatesbyGOVCODE(groupId, govAgencyCode);
		//TODO: Fix govAgencyCode = null
		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplatesbyGroupId(groupId);

		// add registrationForm
		if (lstRegistrationTemplate != null && lstRegistrationTemplate.size() > 0) {
			for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
				// create referenceUid
				String referenceUid = UUID.randomUUID().toString();

				RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, companyId, registrationId, referenceUid,
						registrationTemplates.getFormNo(), registrationTemplates.getFormName(),
						registrationTemplates.getSampleData(), registrationTemplates.getFormScript(),
						registrationTemplates.getFormReport(), 0, false, false, serviceContext);
			}
		}
	}
	
	@Override
	public void cloneRegistrationFormByRegistrationId(long groupId, long oldRegistrationId, long newRegistrationId, ServiceContext serviceContext) 
	    throws PortalException, SystemException {
        
	    // get RegistrationForm
        List<RegistrationForm> registrationForms = RegistrationFormLocalServiceUtil.getFormsbyRegId(
                groupId, oldRegistrationId);
        
//        List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
//				.getRegistrationTemplatesbyGroupId(groupId);

        // add registrationForm
//        if (registrationForms != null && registrationForms.size() > 0) {
//	        for (RegistrationForm registrationForm : registrationForms) {
//	            if(!registrationForm.getRemoved()) {
//	            	if (lstRegistrationTemplate != null && lstRegistrationTemplate.size() > 0) {
//		                for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
//		        			// create referenceUid
//		        			String referenceUid = UUID.randomUUID().toString();
//		        			_log.info("referenceUid: "+referenceUid);
//		
//		        			RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, registrationForm.getCompanyId(), newRegistrationId, referenceUid,
//		        					registrationTemplates.getFormNo(), registrationTemplates.getFormName(),
//		        					registrationForm.getFormData(), registrationTemplates.getFormScript(),
//		        					registrationTemplates.getFormReport(), 0, false, false, serviceContext);
//		        		}
//	            	}
//	            }
//	        }
//        }

        // add test registrationForm
        if (registrationForms != null && registrationForms.size() > 0) {
	        for (RegistrationForm registrationForm : registrationForms) {
	            if(!registrationForm.getRemoved()) {
        			// create referenceUid
        			String referenceUid = UUID.randomUUID().toString();
        			_log.info("referenceUid: "+referenceUid);

        			RegistrationFormLocalServiceUtil.addRegistrationForm(groupId, registrationForm.getCompanyId(), newRegistrationId, referenceUid,
        					registrationForm.getFormNo(), registrationForm.getFormName(),
        					registrationForm.getFormData(), registrationForm.getFormScript(),
        					registrationForm.getFormReport(), 0, false, false, serviceContext);
	            }
	        }
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
