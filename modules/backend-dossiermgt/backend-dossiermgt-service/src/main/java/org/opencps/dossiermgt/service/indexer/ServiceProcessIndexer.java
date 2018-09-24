package org.opencps.dossiermgt.service.indexer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.ServiceProcessRoleTerm;
import org.opencps.dossiermgt.constants.ServiceProcessTerm;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class ServiceProcessIndexer extends BaseIndexer<ServiceProcess> {
	public static final String CLASS_NAME = ServiceProcess.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ServiceProcess object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());

	}

	@Override
	protected Document doGetDocument(ServiceProcess object) throws Exception {
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
		document.addNumberSortable(ServiceProcessTerm.DURATION_COUNT, object.getDurationCount());
		document.addNumberSortable(ServiceProcessTerm.DURATION_UNIT, object.getDurationUnit());
		document.addNumberSortable(ServiceProcessTerm.COUNTER, object.getCounter());

		// add text fields
		document.addTextSortable(ServiceProcessTerm.PROCESS_NO, object.getProcessNo());
		document.addTextSortable(ServiceProcessTerm.PROCESS_NAME, object.getProcessName());
		document.addTextSortable(ServiceProcessTerm.DESCRIPTION, object.getDescription());
		document.addTextSortable(ServiceProcessTerm.DOSSIER_NO_PATTERN, object.getDossierNoPattern());
		document.addTextSortable(ServiceProcessTerm.DUEDATE_PATTERN, object.getDueDatePattern());
		document.addTextSortable(ServiceProcessTerm.SERVER_NO, object.getServerNo());
		
		ServerConfig server = ServerConfigLocalServiceUtil.getByCode(object.getServerNo());
		
		String serverName = StringPool.BLANK;
		
		if (Validator.isNotNull(server))
			serverName = server.getServerName();
		
		document.addTextSortable(ServiceProcessTerm.SERVER_NAME, serverName);

		document.addTextSortable(ServiceProcessTerm.GENERATE_DOSSIER_NO,
				Boolean.toString(object.getGenerateDossierNo()));
		document.addTextSortable(ServiceProcessTerm.GENERATE_DUE_DATE, Boolean.toString(object.getGenerateDueDate()));
		document.addTextSortable(ServiceProcessTerm.GENERATE_SECRET, Boolean.toString(object.getGeneratePassword()));
		document.addTextSortable(ServiceProcessTerm.DIRECT_NOTIFICATION,
				Boolean.toString(object.getDirectNotification()));
		
		// add extra fields (ServiceProcessRole)
		List<ServiceProcessRole> roles = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(object.getPrimaryKey());
		
		long [] roleArray = ListUtil.toLongArray(roles, ServiceProcessRole.ROLE_ID_ACCESSOR);
		
		document.addNumber(ServiceProcessRoleTerm.ROLE_ID, roleArray);
		
		return document;
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
		ServiceProcess object = ServiceProcessLocalServiceUtil.getServiceProcess(classPK);

		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(ServiceProcess object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ServiceProcessLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ServiceProcess>() {

					@Override
					public void performAction(ServiceProcess object) {
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
	

	Log _log = LogFactoryUtil.getLog(ServiceProcessIndexer.class);
}

