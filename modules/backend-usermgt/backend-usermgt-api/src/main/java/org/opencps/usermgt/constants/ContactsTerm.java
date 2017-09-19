package org.opencps.usermgt.constants;

import java.util.Date;

/**
 * @author Binhth
 * @see ContactsTerm
 */

public class ContactsTerm {

	public static final String COMPANY_ID = "companyId";

	public static final String COMPANY_NAME = "companyName";

	public static final String CONTACT = "contact";

	public static final String CONTACT_ID = "contactId";

	public static final String CREATE_DATE = "createDate";

	public static final String EMAIL = "email";

	public static final String FULL_NAME = "fullName";

	public static final String GROUP_ID = "groupId";

	public static final String CONTACT_TYPE = "contactType";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String SHARED = "shared";

	public static final String TEL_NO = "telNo";

	public static final String USER_ID = "userId";

	public static final String USER_MAPPING_ID = "userMappingId";

	public static final String USER_NAME = "userName";

	public static final String GROUP_LIST = "groupList";

	// sortable
	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String COMPANY_NAME_SORTABLE = "companyName_sortable";

	public static final String CONTACT_SORTABLE = "contact_sortable";

	public static final String CONTACT_ID_SORTABLE = "contactId_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String EMAIL_SORTABLE = "email_sortable";

	public static final String FULL_NAME_SORTABLE = "fullName_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String CONTACT_TYPE_SORTABLE = "contactType_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String SHARED_SORTABLE = "shared_sortable";

	public static final String TEL_NO_SORTABLE = "telNo_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_MAPPING_ID_SORTABLE = "userMappingId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String GROUP_LIST_SORTABLE = "groupList_sortable";

	private long companyId;
	private String companyName;
	private long contactId;
	private Date createDate;
	private String email;
	private String fullName;
	private long groupId;
	private int contactType;
	private Date modifiedDate;
	private boolean shared;
	private String telNo;
	private long userId;
	private long userMappingId;
	private String userName;
	private String groupList;
	private boolean isGroupUser;

	public ContactsTerm() {
	}

	public long getCompanyId() {
		return this.companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public long getContactId() {
		return this.contactId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public long getGroupId() {
		return this.groupId;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public long getUserId() {
		return this.userId;
	}

	public long getUserMappingId() {
		return this.userMappingId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserMappingId(long userMappingId) {
		this.userMappingId = userMappingId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isGroupUser() {
		return isGroupUser;
	}

	public void setGroupUser(boolean isGroupUser) {
		this.isGroupUser = isGroupUser;
	}

	public int getContactType() {
		return contactType;
	}

	public void setContactType(int contactType) {
		this.contactType = contactType;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}

}