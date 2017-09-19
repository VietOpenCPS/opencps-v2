package org.opencps.usermgt.constants;

import java.util.Date;

/**
 * @author Binhth
 * @see EmployeeJobPosTerm
 */

public class EmployeeJobPosTerm {

	public static final String EMPLOYEE_JOBPOST_ID = "employeeJobPosId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";
	
	public static final String USER_ID = "userId";
	
	public static final String USER_NAME = "userName";
	
	public static final String CREATE_DATE = "createDate";
	
	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String EMPLOYEE_ID = "employeeId";
	
	public static final String JOBPOST_ID = "jobPostId";
	
	public static final String JOBPOST_TITLE = "jobPosTitle";
	
	public static final String LEADER = "leader";
	
	public static final String MAIN_JOBPOS = "mainJobPos";

	public static final String WORKING_UNIT_ID = "workingUnitId";
	
	public static final String WORKING_UNIT_NAME = "workingUnitName";
	
	// sortable
	public static final String EMPLOYEE_JOBPOST_ID_SORTABLE = "employeeJobPosId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String EMPLOYEE_ID_SORTABLE = "employeeId_sortable";
	
	public static final String JOBPOST_ID_SORTABLE = "jobPostId_sortable";
	
	public static final String WORKING_UNIT_ID_SORTABLE = "workingUnitId_sortable";

	private long employeeJobPosId;

	private long groupId;

	private long companyId;
	
	private long userId;
	
	private String userName;
	
	private Date createDate;
	
	private Date modifiedDate;

	private long employeeId;
	
	private long jobPostId;
	
	private long workingUnitId;
	
	public EmployeeJobPosTerm() {
		
	}

	public long getEmployeeJobPosId() {
		return employeeJobPosId;
	}

	public void setEmployeeJobPosId(long employeeJobPosId) {
		this.employeeJobPosId = employeeJobPosId;
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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getJobPostId() {
		return jobPostId;
	}

	public void setJobPostId(long jobPostId) {
		this.jobPostId = jobPostId;
	}

	public long getWorkingUnitId() {
		return workingUnitId;
	}

	public void setWorkingUnitId(long workingUnitId) {
		this.workingUnitId = workingUnitId;
	}

}
