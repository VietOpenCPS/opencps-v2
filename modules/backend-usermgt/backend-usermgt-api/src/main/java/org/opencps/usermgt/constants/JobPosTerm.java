package org.opencps.usermgt.constants;

import java.util.Date;

/**
 * @author Binhth
 * @see JobPosTerm
 */

public class JobPosTerm {

	public static final String JOBPOS_ID = "jobPosId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String TITLE = "title";

	public static final String DESCRIPTION = "description";

	public static final String LEADER = "leader";

	public static final String MAPPING_ROLE_ID = "mappingRoleId";
	
	public static final String JOBPOS_CODE = "jobPosCode";

	// sortable
	public static final String JOBPOS_ID_SORTABLE = "jobPosId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String TITLE_SORTABLE = "title_sortable";

	public static final String DESCRIPTION_SORTABLE = "description_sortable";

	public static final String LEADER_SORTABLE = "leader_sortable";

	public static final String MAPPING_ROLE_ID_SORTABLE = "mappingRoleId_sortable";

	public static final String JOBPOS_CODE_SORTABLE = "jobPosCode_sortable";

	private long jobPosId;

	private long groupId;

	private long companyId;

	private long userId;

	private String userName;

	private Date createDate;

	private Date modifiedDate;

	private String title;

	private long mappingRoleId;

	private String description;

	private int leader;

	public long getJobPosId() {
		return jobPosId;
	}

	public void setJobPosId(long jobPosId) {
		this.jobPosId = jobPosId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getMappingRoleId() {
		return mappingRoleId;
	}

	public void setMappingRoleId(long mappingRoleId) {
		this.mappingRoleId = mappingRoleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLeader() {
		return leader;
	}

	public void setLeader(int leader) {
		this.leader = leader;
	}

}
