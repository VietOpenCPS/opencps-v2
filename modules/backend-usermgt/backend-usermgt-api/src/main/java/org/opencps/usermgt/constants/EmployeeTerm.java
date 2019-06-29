package org.opencps.usermgt.constants;

import java.util.Date;

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
	
	public static final String IS_USERACCOUNT = "isUserAccount";
	
	public static final String TITLE = "title";
	
	public static final String WORKING_UNIT_NAME = "workingUnitName";
	
	public static final String WORKING_UNIT_ID = "workingUnitId";
	
	public static final String JOB_POS_TITLE = "jobPosTitle";

	public static final String JOB_POS_CODE = "jobPosCode";

	public static final String JOB_POS_CODE_SEARCH = "jobPosCodeSearch";

	public static final String JOB_POS_ID = "jobPosId";
	
	public static final String MONTH = "month";
	
	public static final String ACTIVE = "active";
	
	public static final String RECRUIT_DATE = "recruitDate";
	
	public static final String LEAVE_DATE = "leaveDate";
	
	// sortable
	public static final String EMPLOYEE_ID_SORTABLE = "employeeId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

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
	
	public static final String TITLE_SORTABLE = "title_sortable";

	private long employeeId;

	private long groupId;

	private long companyId;
	
	private long userId;
	
	private String userName;
	
	private Date createDate;
	
	private Date modifiedDate;

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
	
	private boolean isEmployer;
	
	private String title;
	
	private Date recruitDate;
	
	private Date leaveDate;
	
	public EmployeeTerm() {
		
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

	public boolean isEmployer() {
		return isEmployer;
	}

	public void setEmployer(boolean isEmployer) {
		this.isEmployer = isEmployer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRecruitDate() {
		return recruitDate;
	}

	public void setRecruitDate(Date recruitDate) {
		this.recruitDate = recruitDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

}
