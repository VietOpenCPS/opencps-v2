package org.opencps.usermgt.constants;

import java.util.Date;

import javax.portlet.ActionRequest;

import org.opencps.datamgt.utils.DateTimeUtils;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Binhth
 * @see EmployeeTerm
 */

public class EmployeeTerm {

	public static final String EMPLOYEE_ID = "employeeId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";
	
	public static final String USER_ID = "userId";
	
	public static final String USER_NAME = "userName";
	
	public static final String CREATE_DATE = "createDate";
	
	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String WORKING_UNIT_ID = "workingUnitId";
	
	public static final String FULL_NAME = "fullName";
	
	public static final String EMPLOYEE_NO = "employeeNo";
	
	public static final String GENDER = "gender";
	
	public static final String BIRTH_DATE = "birthDate";
	
	public static final String TELNO = "telNo";
	
	public static final String MOBILE = "mobile";
	
	public static final String EMAIL = "email";
	
	public static final String WORKING_STATUS = "workingStatus";
	
	public static final String MAPPING_USER_ID = "mappingUserId";
	
	public static final String MAIN_JOBPOST_ID = "mainJobPostId";
	
	public static final String FORMSCRIPT = "formScript";
	
	public static final String FORMDATA = "formData";
	
	public static final String IS_USERACCOUNT = "isUserAccount";
	
	public static final String EMPLOYEE = "employee";
	// sortable
	public static final String EMPLOYEE_ID_SORTABLE = "employeeId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String WORKING_UNIT_ID_SORTABLE = "workingUnitId_sortable";
	
	public static final String FULL_NAME_SORTABLE = "fullName_sortable";
	
	public static final String EMPLOYEE_NO_SORTABLE = "employeeNo_sortable";
	
	public static final String GENDER_SORTABLE = "gender_sortable";
	
	public static final String BIRTH_DATE_SORTABLE = "birthDate_sortable";
	
	public static final String TELNO_SORTABLE = "telNo_sortable";
	
	public static final String MOBILE_SORTABLE = "mobile_sortable";
	
	public static final String EMAIL_SORTABLE = "email_sortable";
	
	public static final String WORKING_STATUS_SORTABLE = "workingStatus_sortable";
	
	public static final String MAPPING_USER_ID_SORTABLE = "mappingUserId_sortable";
	
	public static final String MAIN_JOBPOST_ID_SORTABLE = "mainJobPostId_sortable";
	
	public static final String FORMSCRIPT_SORTABLE = "formScript_sortable";
	
	public static final String FORMDATA_SORTABLE = "formData_sortable";
	
	public static final String EMPLOYEE_SORTABLE = "employee_sortable";

	private long employeeId;

	private long groupId;

	private long companyId;
	
	private long userId;
	
	private String userName;
	
	private Date createDate;
	
	private Date modifiedDate;

	private long workingUnitId;
	
	private String fullName;
	private String employeeNo;
	private int gender;
	private Date birthDate;
	private String telNo;
	private String mobile;
	private String email;
	private int workingStatus;
	private long mappingUserId;
	private long mainJobPostId;
	private String formScript;
	private String formData;
	private boolean isEmployer;
	
	public EmployeeTerm() {
		
	}

	public EmployeeTerm(ActionRequest request) {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		employeeId = ParamUtil.getLong(request, EMPLOYEE_ID);
		
		groupId = themeDisplay.getScopeGroupId();
		
		companyId = themeDisplay.getCompanyId();
		
		userId = themeDisplay.getUserId();
		
		userName = themeDisplay.getUser().getFullName();
		
		createDate = ParamUtil.getDate(request, CREATE_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		modifiedDate = ParamUtil.getDate(request, MODIFIED_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		workingUnitId = ParamUtil.getLong(request, WORKING_UNIT_ID);
		
		fullName = ParamUtil.getString(request, FULL_NAME);
		
		employeeNo = ParamUtil.getString(request, EMPLOYEE_NO);
		
		gender = ParamUtil.getInteger(request, GENDER);
		
		birthDate = ParamUtil.getDate(request, BIRTH_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		telNo = ParamUtil.getString(request, TELNO);
		
		mobile = ParamUtil.getString(request, MOBILE);
		
		email = ParamUtil.getString(request, EMAIL);
		
		workingStatus = ParamUtil.getInteger(request, WORKING_STATUS);
		
		mappingUserId = ParamUtil.getLong(request, MAPPING_USER_ID);
		
		mainJobPostId = ParamUtil.getLong(request, MAIN_JOBPOST_ID);
		
		formScript = ParamUtil.getString(request, FORMSCRIPT);
		
		formData = ParamUtil.getString(request, FORMDATA);
		
		isEmployer = ParamUtil.getBoolean(request, IS_USERACCOUNT);
		
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public long getWorkingUnitId() {
		return workingUnitId;
	}

	public void setWorkingUnitId(long workingUnitId) {
		this.workingUnitId = workingUnitId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getWorkingStatus() {
		return workingStatus;
	}

	public void setWorkingStatus(int workingStatus) {
		this.workingStatus = workingStatus;
	}

	public long getMappingUserId() {
		return mappingUserId;
	}

	public void setMappingUserId(long mappingUserId) {
		this.mappingUserId = mappingUserId;
	}

	public long getMainJobPostId() {
		return mainJobPostId;
	}

	public void setMainJobPostId(long mainJobPostId) {
		this.mainJobPostId = mainJobPostId;
	}

	public String getFormScript() {
		return formScript;
	}

	public void setFormScript(String formScript) {
		this.formScript = formScript;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public boolean isEmployer() {
		return isEmployer;
	}

	public void setEmployer(boolean isEmployer) {
		this.isEmployer = isEmployer;
	}


}
