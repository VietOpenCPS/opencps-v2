package org.opencps.datamgt.constants;

import java.util.Date;

public class ResourceWorkspaceTerm {

	public static final String RESOURCE_WORKSPACEID = "resourceWorkspaceId";
	
	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";
	
	public static final String CLASS_NAME = "className";
	
	public static final String CLASS_PK = "classPK";
	
	public static final String WORKSPACE_ID = "workspaceId";
	
	public static final String RESOURCE_WORKSPACEID_SORTABLE = "resourceWorkspaceId_sortable";
	
	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";
	
	public static final String CLASS_NAME_SORTABLE = "className_sortable";
	
	public static final String CLASS_PK_SORTABLE = "classPK_sortable";
	
	public static final String WORKSPACE_ID_SORTABLE = "workspaceId_Number_sortable";
	
	public ResourceWorkspaceTerm() {
		// TODO Auto-generated constructor stub
	}
	
	private long resourceWorkspaceId;
	private long companyId;
	private long groupId;

	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	
	private String className;
	private String classPK;
	private long workspaceId;
	
	public long getResourceWorkspaceId() {
		return resourceWorkspaceId;
	}
	public void setResourceWorkspaceId(long resourceWorkspaceId) {
		this.resourceWorkspaceId = resourceWorkspaceId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassPK() {
		return classPK;
	}
	public void setClassPK(String classPK) {
		this.classPK = classPK;
	}
	public long getWorkspaceId() {
		return workspaceId;
	}
	public void setWorkspaceId(long workspaceId) {
		this.workspaceId = workspaceId;
	}
}
