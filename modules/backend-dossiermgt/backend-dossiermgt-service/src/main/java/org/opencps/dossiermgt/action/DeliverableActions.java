package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DeliverableActions {
	public JSONObject getListDeliverable(String state, String agency, String type, String applicant);

//	public void addDeliverable(DeliverableImpl model);

//	public Deliverable getListDeliverableDetail(Long deliverableId) throws NoSuchDeliverableException;

	//4
	public Deliverable updateDeliverable(long groupId, long id, String subject, String issueDate, String expireDate,
			String revalidate, String deliverableState, String deliverableAction, ServiceContext serviceContext);

	//12
//	public JSONArray getFormDataByTypecode(long groupId, String registrationId, String typeCode, String[] splitProperties);
	
	//2
	public Deliverable addDeliverable(long groupId, String deliverableType, String deliverableCode,
			String govAgencyCode, String applicantIdNo, String applicantName, String subject, String issueDate,
			String expireDate, String revalidate, String deliverableState, ServiceContext serviceContext);

	//1
	public JSONObject getListDeliverable(long companyId, LinkedHashMap<String, Object> params, Sort[] sorts, int start,
			int end, ServiceContext serviceContext);

	//3, 8, 9
	public Deliverable getDetailById(Long id, long groupId) throws NoSuchDeliverableException;

	//5
	public Deliverable deleteById(Long id, long groupId) throws NoSuchDeliverableException;

	//6
	public JSONObject getFormDataById(long companyId, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			ServiceContext serviceContext);

	//7
	public Deliverable updateFormData(long groupId, long id, String formData, ServiceContext serviceContext) throws NoSuchDeliverableException;

	//
	public JSONObject getFormDataByTypecode(long companyId, LinkedHashMap<String, Object> params, Sort[] object, int start,
			int end, ServiceContext serviceContext);

}
