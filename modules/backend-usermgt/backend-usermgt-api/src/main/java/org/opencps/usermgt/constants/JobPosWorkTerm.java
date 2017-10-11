package org.opencps.usermgt.constants;

import java.util.Date;

/**
 * @author Binhth
 * @see JobPosWorkTerm
 */

public class JobPosWorkTerm {

	public static final String JOBPOS_WORK_ID = "jobPosWorkId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String JOBPOS_ID = "jobPostId";

	public static final String CHECKLIST_CAT = "checklistCat";
	
	public static final String CHECKLIST_CAT_NAME = "checklistCatName";
	
	public static final String CHECKLIST_TYPE = "checklistType";

	// sortable
	public static final String JOBPOS_WORK_ID_SORTABLE = "jobPosWorkId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String JOBPOS_ID_SORTABLE = "jobPostId_sortable";

	public static final String CHECKLIST_CAT_SORTABLE = "checklistCat_sortable";

	private long jobPosWorkId;

	private long groupId;

	private long companyId;

	private long userId;

	private String userName;

	private Date createDate;

	private Date modifiedDate;

	private long jobPostId;

	private String checklistCat;

	public long getJobPosWorkId() {
		return jobPosWorkId;
	}

	public void setJobPosWorkId(long jobPosWorkId) {
		this.jobPosWorkId = jobPosWorkId;
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

	public long getJobPostId() {
		return jobPostId;
	}

	public void setJobPostId(long jobPostId) {
		this.jobPostId = jobPostId;
	}

	public String getChecklistCat() {
		return checklistCat;
	}

	public void setChecklistCat(String checklistCat) {
		this.checklistCat = checklistCat;
	}

}
