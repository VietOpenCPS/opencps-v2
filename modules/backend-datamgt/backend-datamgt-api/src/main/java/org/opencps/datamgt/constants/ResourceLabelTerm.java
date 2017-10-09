package org.opencps.datamgt.constants;

import java.util.Date;

public class ResourceLabelTerm {

	public static final String RESOURCE_LABELID = "resourceLabelId";
	
	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";
	
	public static final String CLASS_NAME = "className";
	
	public static final String CLASS_PK = "classPK";
	
	public static final String LABEL_ID = "labelId";
	
	public static final String RESOURCE_LABELID_SORTABLE = "resourceLabelId_sortable";
	
	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";
	
	public static final String CLASS_NAME_SORTABLE = "className_sortable";
	
	public static final String CLASS_PK_SORTABLE = "classPK_sortable";
	
	public static final String LABEL_ID_SORTABLE = "labelId_Number_sortable";
	
	public ResourceLabelTerm() {
		// TODO Auto-generated constructor stub
	}
	
	private long resourceLabelId;
	private long companyId;
	private long groupId;

	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	
	private String className;
	private String classPK;
	private long labelId;
	
	public long getResourceLabelId() {
		return resourceLabelId;
	}
	public void setResourceLabelId(long resourceLabelId) {
		this.resourceLabelId = resourceLabelId;
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
	public long getLabelId() {
		return labelId;
	}
	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}
}
