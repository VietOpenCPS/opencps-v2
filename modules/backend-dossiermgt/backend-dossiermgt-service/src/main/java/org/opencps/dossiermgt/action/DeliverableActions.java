package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DeliverableActions {
	public JSONObject getListDeliverable(int state, String agency, String type, String applicant);

	//4
	public Deliverable updateDeliverable(long groupId, long id, String subject, String issueDate, String expireDate,
			String revalidate, String deliverableState, String deliverableAction, ServiceContext serviceContext);

	//2
	public Deliverable addDeliverable(long groupId, String deliverableType, String deliverableCode,
			String govAgencyCode, String govAgencyName, String applicantIdNo, String applicantName, String subject,
			String issueDate, String expireDate, String revalidate, String deliverableState,
			ServiceContext serviceContext);

	//1
	public JSONObject getListDeliverable(long userId, long companyId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	//3, 8, 9
	public Deliverable getDetailById(long id) throws NoSuchDeliverableException;

	//5
	public Deliverable deleteById(long id) throws PortalException;

	//6
	public JSONObject getFormDataById(long companyId, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			ServiceContext serviceContext);

	//7
	public Deliverable updateFormData(long groupId, long id, String formData, ServiceContext serviceContext) throws NoSuchDeliverableException;

	//
	public JSONObject getFormDataByTypecode(long companyId, LinkedHashMap<String, Object> params, Sort[] object, int start,
			int end, ServiceContext serviceContext);

	//
	public List<Deliverable> getDeliverableByState(String strDeliverableCode, int state);
}
