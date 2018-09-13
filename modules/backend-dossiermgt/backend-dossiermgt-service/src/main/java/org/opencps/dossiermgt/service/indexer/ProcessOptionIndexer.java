package org.opencps.dossiermgt.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.dossiermgt.constants.ProcessOptionTerm;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ProcessOptionIndexer extends BaseIndexer<ProcessOption> {

	public static final String CLASS_NAME = ProcessOption.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ProcessOption object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(ProcessOption object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.MODIFIED_DATE, object.getCreateDate());
		document.addDateSortable(Field.CREATE_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		// add number fields
		document.addNumberSortable(ProcessOptionTerm.SEQ_ORDER, object.getOptionOrder());
		document.addNumberSortable(ProcessOptionTerm.SERVICE_CONFIG_ID, object.getServiceConfigId());
		document.addNumberSortable(ProcessOptionTerm.SERVICE_PROCESS_ID, object.getServiceProcessId());
		document.addNumberSortable(ProcessOptionTerm.DOSSIER_TEMPLATEID, object.getDossierTemplateId());

		// add text fields
		document.addTextSortable(ProcessOptionTerm.AUTO_SELECT, object.getAutoSelect());
		document.addTextSortable(ProcessOptionTerm.INSTRUCTION_NOTE, object.getInstructionNote());
		document.addTextSortable(ProcessOptionTerm.SUBMISSION_NOTE, object.getSubmissionNote());
		document.addTextSortable(ProcessOptionTerm.OPTION_NAME, object.getOptionName());

		
		// add extend fields
		JSONObject extContent = getExtentData(object.getDossierTemplateId(), object.getServiceProcessId(),
				object.getServiceConfigId());

		document.addTextSortable(ProcessOptionTerm.TEMPLATE_NO, extContent.getString(ProcessOptionTerm.TEMPLATE_NO));
		document.addTextSortable(ProcessOptionTerm.TEMPLATE_NAME,
				extContent.getString(ProcessOptionTerm.TEMPLATE_NAME));
		document.addTextSortable(ProcessOptionTerm.PROCESS_NO, extContent.getString(ProcessOptionTerm.PROCESS_NO));
		document.addTextSortable(ProcessOptionTerm.PROCESS_NAME, extContent.getString(ProcessOptionTerm.PROCESS_NAME));

		document.addTextSortable(ActionKeys.APPLICANT_CTZ, extContent.getString(ActionKeys.APPLICANT_CTZ));

		document.addTextSortable(ActionKeys.APPLICANT_BUSINESS, extContent.getString(ActionKeys.APPLICANT_BUSINESS));
		return document;
	}

	private JSONObject getExtentData(long dossierTemplateId, long serviceProcessId, long serviceConfigId) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		try {
			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(dossierTemplateId);
			jsonObj.put(ProcessOptionTerm.TEMPLATE_NO, dossierTemplate.getTemplateNo());
			jsonObj.put(ProcessOptionTerm.TEMPLATE_NAME, dossierTemplate.getTemplateName());
		} catch (Exception e) {
			_log.error(e);
		}

		try {
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceProcess(serviceProcessId);
			jsonObj.put(ProcessOptionTerm.PROCESS_NO, serviceProcess.getProcessNo());
			jsonObj.put(ProcessOptionTerm.PROCESS_NAME, serviceProcess.getProcessName());

		} catch (Exception e) {
			_log.error(e);
		}

		try {
			ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.getServiceConfig(serviceConfigId);

			if (serviceConfig.getForBusiness())
				jsonObj.put(ActionKeys.APPLICANT_BUSINESS, Boolean.toString(serviceConfig.getForBusiness()));

			if (serviceConfig.getForCitizen())
				jsonObj.put(ActionKeys.APPLICANT_CTZ, Boolean.toString(serviceConfig.getForCitizen()));

		} catch (Exception e) {
			_log.error(e);
		}

		return jsonObj;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		ProcessOption object = ProcessOptionLocalServiceUtil.getProcessOption(classPK);
		doReindex(object);

	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}
	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ProcessOptionLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProcessOption>() {

					@Override
					public void performAction(ProcessOption object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	@Override
	protected void doReindex(ProcessOption object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	Log _log = LogFactoryUtil.getLog(ProcessOptionIndexer.class);

}
