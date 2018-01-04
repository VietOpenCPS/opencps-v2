package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.DossierTemplateActions;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class DossierTemplateActionsImpl implements DossierTemplateActions {

	public static final int DOSSIER_PART_CONTENT_TYPE_SCRIPT = 1;
	public static final int DOSSIER_PART_CONTENT_TYPE_REPORT = 2;
	public static final int DOSSIER_PART_CONTENT_TYPE_SAMPLE = 3;

	@Override
	public JSONObject getDossierTemplates(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext)
			throws PortalException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		hits = DossierTemplateLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

		result.put("data", hits.toList());

		long total = DossierTemplateLocalServiceUtil.countLucene(params, searchContext);

		result.put("total", total);

		return result;

	}

	@Override
	public DossierTemplate updateDossierTemplate(long groupId, long dossierTemplateId, String templateName,
			String templateNo, String description, ServiceContext context) throws PortalException {

		return DossierTemplateLocalServiceUtil.updateDossierTemplate(groupId, dossierTemplateId, templateName,
				templateNo, description, context);
	}

	@Override
	public DossierTemplate getDossierTemplate(long dossierTemplateId) throws PortalException {
		return DossierTemplateLocalServiceUtil.getDossierTemplate(dossierTemplateId);
	}

	@Override
	public DossierTemplate getDossierTemplate(long groupId, String templateNo) throws PortalException {
		return DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, templateNo);
	}

	@Override
	public DossierTemplate removeDossierTemplate(long dossierTemplateId) throws PortalException {
		return DossierTemplateLocalServiceUtil.removeDossierTemplate(dossierTemplateId);
	}

	@Override
	public JSONObject getDossierParts(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		hits = DossierPartLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

		result.put("data", hits.toList());

		long total = DossierPartLocalServiceUtil.countLucene(params, searchContext);

		result.put("total", total);

		return result;
	}

	@Override
	public JSONObject getDBDossierParts(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		List<DossierPart> dossierParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId,
				GetterUtil.getString(params.get(DossierPartTerm.TEMPLATE_NO)));

		result.put("data", dossierParts);

		long total = DossierPartLocalServiceUtil.countLucene(params, searchContext);

		result.put("total", total);

		return result;
	}

	@Deprecated
	@Override
	public DossierPart updateDossierPart(long groupId, long dossierPartId, String templateNo, String partNo,
			String partName, String partTip, int partType, boolean multiple, String formScript, String formReport,
			String sampleData, boolean required, String fileTemplateNo, boolean eSign, ServiceContext context)
			throws PortalException {
		return DossierPartLocalServiceUtil.updateDossierPart(groupId, dossierPartId, templateNo, partNo, partName,
				partTip, partType, multiple, formScript, formReport, sampleData, required, fileTemplateNo, eSign,
				context);
	}

	@Override
	public DossierPart removeDossierPart(long dossierPartId) throws PortalException {
		return DossierPartLocalServiceUtil.removeDossierPart(dossierPartId);
	}

	@Override
	public String updateFormScript(long groupId, long dossierTemplateId, String partNo, String input,
			ServiceContext context) throws PortalException {

		long dossierPartId = getDosssierPartId(groupId, dossierTemplateId, partNo);

		return DossierPartLocalServiceUtil.updateContent(dossierPartId, DOSSIER_PART_CONTENT_TYPE_SCRIPT, input,
				context);
	}

	@Override
	public String getFormScript(long groupId, long dossierTemplateId, String partNo) throws PortalException {
		long dossierPartId = getDosssierPartId(groupId, dossierTemplateId, partNo);

		return DossierPartLocalServiceUtil.getContent(dossierPartId, DOSSIER_PART_CONTENT_TYPE_SCRIPT);
	}

	@Override
	public String updateFormReport(long groupId, long dossierTemplateId, String partNo, String input,
			ServiceContext context) throws PortalException {
		long dossierPartId = getDosssierPartId(groupId, dossierTemplateId, partNo);

		return DossierPartLocalServiceUtil.updateContent(dossierPartId, DOSSIER_PART_CONTENT_TYPE_REPORT, input,
				context);
	}

	@Override
	public String getFormReport(long groupId, long dossierTemplateId, String partNo) throws PortalException {
		long dossierPartId = getDosssierPartId(groupId, dossierTemplateId, partNo);
		return DossierPartLocalServiceUtil.getContent(dossierPartId, DOSSIER_PART_CONTENT_TYPE_REPORT);
	}

	@Override
	public String updateSample(long groupId, long dossierTemplateId, String partNo, String input,
			ServiceContext context) throws PortalException {
		long dossierPartId = getDosssierPartId(groupId, dossierTemplateId, partNo);

		return DossierPartLocalServiceUtil.updateContent(dossierPartId, DOSSIER_PART_CONTENT_TYPE_SAMPLE, input,
				context);
	}

	@Override
	public String getSample(long groupId, long dossierTemplateId, String partNo) throws PortalException {
		long dossierPartId = getDosssierPartId(groupId, dossierTemplateId, partNo);
		return DossierPartLocalServiceUtil.getContent(dossierPartId, DOSSIER_PART_CONTENT_TYPE_SAMPLE);
	}

	/**
	 * @param groupId
	 * @param dossierTemplateId
	 * @param partNo
	 * @return
	 */
	private long getDosssierPartId(long groupId, long dossierTemplateId, String partNo) {
		long dossierPartId = 0;

		try {
			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.fetchDossierTemplate(dossierTemplateId);

			String templateNo = dossierTemplate.getTemplateNo();

			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, templateNo, partNo);

			dossierPartId = dossierPart.getDossierPartId();

		} catch (Exception e) {
			dossierPartId = 0; // :)
		}

		return dossierPartId;
	}

	@Override
	public DossierPart updateDossierPart(long groupId, long dossierPartId, String templateNo, String partNo,
			String partName, String partTip, int partType, boolean multiple, String formScript, String formReport,
			String sampleData, boolean required, String fileTemplateNo, boolean eSign, String deliverableType,
			int deliverableAction, ServiceContext context) throws PortalException {
		return DossierPartLocalServiceUtil.updateDossierPart(groupId, dossierPartId, templateNo, partNo, partName,
				partTip, partType, multiple, formScript, formReport, sampleData, required, fileTemplateNo, eSign,
				deliverableType, deliverableAction, context);
	}

}
