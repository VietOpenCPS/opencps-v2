package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierTemplateActions {

	public JSONObject getDossierTemplates(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext)
			throws PortalException;

	public DossierTemplate updateDossierTemplate(long groupId, long dossierTemplateId, String templateName,
			String templateNo, String description, String newFormScript, ServiceContext context) throws PortalException;

	public DossierTemplate getDossierTemplate(long dossierTemplateId) throws PortalException;

	public DossierTemplate getDossierTemplate(long groupId, String templateNo) throws PortalException;

	public DossierTemplate removeDossierTemplate(long dossierTemplateId) throws PortalException;

	public JSONObject getDossierParts(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException;

	public JSONObject getDBDossierParts(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException;

	public DossierPart updateDossierPart(long groupId, long dossierPartId, String templateNo, String partNo,
			String partName, String partTip, int partType, boolean multiple, String formScript, String formReport,
			String sampleData, boolean required, String fileTemplateNo, boolean eSign, ServiceContext context)
			throws PortalException;

	public DossierPart updateDossierPart(long groupId, long dossierPartId, String templateNo, String partNo,
			String partName, String partTip, int partType, boolean multiple, String formScript, String formReport,
			String sampleData, boolean required, String fileTemplateNo, boolean eSign, String deliverableType,
			int deliverableAction, ServiceContext context) throws PortalException;

	public DossierPart removeDossierPart(long dossierPartId) throws PortalException;

	public String updateFormScript(long groupId, long dossierTemplateId, String partNo, String input,
			ServiceContext context) throws PortalException;

	public String getFormScript(long groupId, long dossierTemplateId, String partNo) throws PortalException;

	public String updateFormReport(long groupId, long dossierTemplateId, String partNo, String input,
			ServiceContext context) throws PortalException;

	public String getFormReport(long groupId, long dossierTemplateId, String partNo) throws PortalException;

	public String updateSample(long groupId, long dossierTemplateId, String partNo, String input,
			ServiceContext context) throws PortalException;

	public String getSample(long groupId, long dossierTemplateId, String partNo) throws PortalException;

	public void updateDossierTemplateDB(long userId, long groupId, String templateNo, String templateName, String description,
			String newFormScript,
			ServiceContext serviceContext) throws PortalException;

	public void updateDossierPartDB(long userId, long groupId, String templateNo, String partNo, String partName,
			String partTip, Integer partType, boolean multiple, String formScript, String formReport, boolean required,
			boolean esign, String fileTemplateNo, String deliverableType, Integer deliverableAction, boolean eForm,
			String sampleData, Integer fileMark, ServiceContext serviceContext) throws PortalException;

	public boolean deleteAllDossierPart(long userId, long groupId, String templateNo, ServiceContext serviceContext);

}
