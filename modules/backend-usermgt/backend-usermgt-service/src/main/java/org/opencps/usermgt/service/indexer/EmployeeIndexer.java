package org.opencps.usermgt.service.indexer;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;



public class EmployeeIndexer extends BaseIndexer<Employee> {

	public static final String CLASS_NAME = Employee.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext) throws Exception {

		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Object> params = (LinkedHashMap<String, Object>) searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String) params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				addSearchExpando(searchQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Override
	protected void doDelete(Employee employee) throws Exception {
		deleteDocument(employee.getCompanyId(), employee.getEmployeeId());
	}

	@Override
	protected Document doGetDocument(Employee employee) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, employee);

		document.addKeywordSortable(Field.COMPANY_ID, String.valueOf(employee.getCompanyId()));
		document.addDateSortable(Field.MODIFIED_DATE, employee.getModifiedDate());
		document.addKeywordSortable(Field.USER_ID, String.valueOf(employee.getUserId()));
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(employee.getUserName()));

		document.addNumberSortable(EmployeeTerm.EMPLOYEE_ID, employee.getEmployeeId());
		
		document.addNumberSortable(EmployeeTerm.GROUP_ID, employee.getGroupId());
		document.addTextSortable(EmployeeTerm.TITLE, employee.getTitle());
		document.addTextSortable(EmployeeTerm.FULL_NAME, employee.getFullName());
		document.addTextSortable(EmployeeTerm.EMPLOYEE_NO, employee.getEmployeeNo());
		document.addNumberSortable(EmployeeTerm.GENDER, employee.getGender());
		document.addDateSortable(EmployeeTerm.BIRTH_DATE, employee.getBirthdate());
		document.addTextSortable(EmployeeTerm.TELNO, employee.getTelNo());
		document.addTextSortable(EmployeeTerm.MOBILE, employee.getMobile());
		document.addTextSortable(EmployeeTerm.EMAIL, employee.getEmail());
		document.addNumberSortable(EmployeeTerm.WORKING_STATUS, employee.getWorkingStatus());
		document.addNumberSortable(EmployeeTerm.MAPPING_USER_ID, employee.getMappingUserId());
		document.addNumberSortable(EmployeeTerm.MAIN_JOBPOST_ID, employee.getMainJobPostId());

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("email-address")) {
			return "emailAddress";
		} else if (orderByCol.equals("first-name")) {
			return "firstName";
		} else if (orderByCol.equals("job-title")) {
			return "jobTitle";
		} else if (orderByCol.equals("last-name")) {
			return "lastName";
		} else {
			return orderByCol;
		}
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(Employee employee) throws Exception {
		Document document = getDocument(employee);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), employee.getCompanyId(), document,
				isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Employee employee = EmployeeLocalServiceUtil.getEmployee(classPK);
		doReindex(employee);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEmployee(companyId);
	}

	protected void reindexEmployee(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = EmployeeLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Employee>() {

					@Override
					public void performAction(Employee employee) {
						try {
							Document document = getDocument(employee);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + employee.getEmployeeId(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(EmployeeIndexer.class);

}