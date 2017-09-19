package org.opencps.usermgt.constants;

import java.util.Date;

/**
 * @author Binhth
 * @see ResourceUserTerm
 */

public class ResourceUserTerm {

	public static final String RESOURCEUSER_ID = "resourceUserId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String CLASS_NAME = "className";

	public static final String CLASS_PK = "classPK";

	public static final String TO_USERID = "toUserId";

	public static final String TO_USERNAME = "toUserName";
	
	public static final String RESOURCEUSER_ID_SORTABLE = "resourceUserId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String CLASS_NAME_SORTABLE = "className_sortable";

	public static final String CLASS_PK_SORTABLE = "classPK_sortable";

	public static final String TO_USERID_SORTABLE = "toUserId_sortable";

	private long resourceUserId;

	private long groupId;

	private long companyId;

	private long userId;

	private String userName;

	private Date createDate;

	private Date modifiedDate;

	private String className;
	private String classPK;
	private long toUserId;

	public ResourceUserTerm() {

	}

	public long getResourceUserId() {
		return resourceUserId;
	}

	public void setResourceUserId(long resourceUserId) {
		this.resourceUserId = resourceUserId;
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

	public long getToUserId() {
		return toUserId;
	}

	public void setToUserId(long toUserId) {
		this.toUserId = toUserId;
	}
}
