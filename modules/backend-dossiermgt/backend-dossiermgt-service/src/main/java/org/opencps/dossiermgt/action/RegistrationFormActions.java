package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.RegistrationForm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface RegistrationFormActions {

	public RegistrationForm update(RegistrationForm model);

	public RegistrationForm getDetail(long registrationFormId) throws PortalException;

	public List<RegistrationForm> getFormbyRegId(long groupId, long registrationId) throws PortalException;

	public RegistrationForm insert(long groupId, long companyId, long registrationId, String referenceUid, String formNo, String formName,
			String formData, String formScript, String formReport, long fileEntryId, boolean isNew, boolean removed,
			ServiceContext serviceContext) throws PortalException;

	public boolean deleteRegistrationForm(String referenceUid) throws PortalException;

	public void addRegistrationFormbaseonRegTemplate(long groupId,  long companyId, long registrationId, String govAgencyCode, ServiceContext serviceContext)
			throws PortalException;

	public List<RegistrationForm> deleteRegistrationForms(long groupId, long registrationId) throws PortalException;

	public RegistrationForm updateRegFormFormData(long groupId, long registrationId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException;

	public RegistrationForm updateIsNew(long groupId, long registrationId, String referenceUid, boolean isNew,
			ServiceContext serviceContext) throws SystemException, PortalException;

	public JSONObject getRegistrationForms(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

    public void cloneRegistrationFormByRegistrationId(
        long groupId, long oldRegistrationId, long newRegistrationId, ServiceContext serviceContext)
        throws PortalException, SystemException;

}
