package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.RegistrationForm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface RegistrationFormActions {

	public RegistrationForm update(RegistrationForm model);

	public RegistrationForm getDetail(long registrationFormId) throws PortalException;

	public List<RegistrationForm> getFormbyRegId(long groupId, long registrationId) throws PortalException;

	public RegistrationForm insert(long groupId, long registrationId, String referenceUid, String formNo, String formName,
			String formData, String formScript, String formReport, long fileEntryId, boolean isNew, boolean removed,
			ServiceContext serviceContext) throws PortalException;

	public RegistrationForm deleteRegistrationForm(long groupId, long registrationId, String referenceUid) throws PortalException;

	public void addRegistrationFormbaseonRegTemplate(long groupId, long registrationId, String govAgencyCode, ServiceContext serviceContext)
			throws PortalException;

	public List<RegistrationForm> deleteRegistrationForms(long groupId, long registrationId) throws PortalException;

	public RegistrationForm updateRegFormFormData(long groupId, long registrationId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException;

	//TODO: 18
	public JSONArray getFormDataByFormNo(long groupId, long registrationId, String formNo, String[] splitProperties) throws JSONException;
}
