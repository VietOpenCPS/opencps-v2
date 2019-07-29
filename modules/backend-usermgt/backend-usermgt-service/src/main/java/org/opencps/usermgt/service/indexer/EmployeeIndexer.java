package org.opencps.usermgt.service.indexer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
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
		document.addDateSortable(EmployeeTerm.RECRUIT_DATE, employee.getRecruitDate());
		document.addDateSortable(EmployeeTerm.LEAVE_DATE, employee.getLeaveDate());
		
		EmployeeJobPos employeeJobPos = EmployeeJobPosLocalServiceUtil.fetchEmployeeJobPos(employee.getMainJobPostId());
//				employee.getGroupId(), employee.getEmployeeId(), employee.getMainJobPostId());
		
		long workingUnitId = Validator.isNotNull(employeeJobPos)?employeeJobPos.getWorkingUnitId():0;
		
		String workingUnitName = StringPool.BLANK;
		
		if(workingUnitId > 0){
			
			WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(workingUnitId);
			
			workingUnitName = Validator.isNotNull(workingUnit)?workingUnit.getName():StringPool.BLANK;
			
		}

		JobPos jobPos = Validator.isNotNull(employeeJobPos)
				? JobPosLocalServiceUtil.fetchJobPos(employeeJobPos.getJobPostId())
				: null;
		String jobPosTitle = jobPos != null ? jobPos.getTitle():StringPool.BLANK;
		String jobPosCode = jobPos != null ? jobPos.getJobPosCode():StringPool.BLANK;
		
		document.addTextSortable(EmployeeTerm.WORKING_UNIT_NAME, workingUnitName);
		document.addNumberSortable(EmployeeTerm.WORKING_UNIT_ID, workingUnitId);
		
		document.addTextSortable(EmployeeTerm.JOB_POS_TITLE, jobPosTitle);

		document.addTextSortable(EmployeeTerm.JOB_POS_CODE, jobPosCode);
		if (Validator.isNotNull(jobPosCode)) {
			String jobPosCodeSearch = splitSpecial(jobPosCode);
			document.addTextSortable(EmployeeTerm.JOB_POS_CODE_SEARCH, jobPosCodeSearch);
		}

		document.addNumberSortable(EmployeeTerm.JOB_POS_ID, employee.getMainJobPostId());
		
		Calendar cal = Calendar.getInstance();

		if (Validator.isNotNull(employee.getBirthdate())) {
			cal.setTime(employee.getBirthdate());
			document.addNumberSortable(EmployeeTerm.MONTH, cal.get(Calendar.MONTH) + 1);
		} else {

			document.addNumberSortable(EmployeeTerm.MONTH, -1);

		}
		
		long userId = employee.getMappingUserId();
		
		int status = WorkflowConstants.STATUS_DENIED;
		
		if(userId > 0){
			
			User user = UserLocalServiceUtil.fetchUser(userId);
			
			status = Validator.isNotNull(user)?user.getStatus():WorkflowConstants.STATUS_DENIED;
			
		}
		
		document.addNumberSortable(EmployeeTerm.ACTIVE, status);
		
		return document;
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

	private static String splitSpecial(String value) {
		String[] charSpecialArr = new String[] { "+", "-", "=", "&&", "||", ">", "<", "!", "(", ")", "{", "}", "[", "]",
				"^", "~", "?", ":", "\\", "/", ".", "," };
		String valueSplit = StringPool.BLANK;
		for (int i = 0; i < charSpecialArr.length; i++) {
			String specialCharacter = charSpecialArr[i];
			if (i == 0) {
				if (value.contains(specialCharacter)) {
					valueSplit = value.replaceAll(Pattern.quote(specialCharacter), StringPool.UNDERLINE);
				} else {
					valueSplit = value;
				}
			} else {
				if (value.contains(specialCharacter)) {
					valueSplit = valueSplit.replaceAll(Pattern.quote(specialCharacter), StringPool.UNDERLINE);
				}
			}
		}

		return valueSplit;
	}

	private static final Log _log = LogFactoryUtil.getLog(EmployeeIndexer.class);

}