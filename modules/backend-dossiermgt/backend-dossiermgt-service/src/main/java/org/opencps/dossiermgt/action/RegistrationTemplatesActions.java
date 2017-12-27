package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.RegistrationTemplates;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public interface RegistrationTemplatesActions {

	public JSONObject getRegistrationTemplates(long groupId, int start, int end);

	public RegistrationTemplates addRegistrationTemplate(long groupId, String govAgencyCode, String govAgencyName,
			String formNo, String formName, boolean multiple, String formScript, String formReport, String sampleData,
			ServiceContext serviceContext) throws PortalException, SystemException;

	public RegistrationTemplates updateRegistrationTemplates(long groupId, long registrationTemplateId,
			String govAgencyCode, String govAgencyName, String formNo, String formName, boolean multiple,
			String formScript, String formReport, String sampleData, ServiceContext serviceContext)
			throws SystemException, PortalException;

	public RegistrationTemplates removeRegistrationTemplate(long groupId, long registrationTemplateId)
			throws PortalException;

	public RegistrationTemplates updateFormScript(long groupId, long registrationTemplateId, String formScript,
			ServiceContext serviceContext) throws SystemException, PortalException;

	public RegistrationTemplates updateFormReport(long groupId, long registrationTemplatesId, String formReport,
			ServiceContext serviceContext) throws PortalException, SystemException;

	public RegistrationTemplates updateSampledata(long groupId, long registrationTemplatesId, String sampleData,
			ServiceContext serviceContext) throws SystemException, PortalException;

	public JSONObject getRegistrationTemplates(long groupId,String formNo, String govAgencyCode);

}
