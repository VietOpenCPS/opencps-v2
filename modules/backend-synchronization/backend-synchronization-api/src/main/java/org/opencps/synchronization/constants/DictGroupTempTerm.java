/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.synchronization.constants;

import java.util.Date;

/**
 * @author Binhth
 * @see DictGroupTempTerm
 */
public class DictGroupTempTerm {

	public static final String DICT_GROUPID = "dictGroupId";

	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String DICT_COLLECTIONID = "dictCollectionId";
	
	public static final String DICT_COLLECTION_CODE = "dictCollectionCode";

	public static final String GROUP_CODE = "groupCode";
	
	public static final String GROUP_NAME = "groupName";

	public static final String GROUP_NAME_EN = "groupNameEN";

	public static final String GROUP_DESCRIPTION = "groupDescription";
	
	public static final String STATUS = "status";
	
	public static final String DICT_GROUPID_SORTABLE = "dictGroupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String DICT_COLLECTIONID_SORTABLE = "dictCollectionId_sortable";

	public static final String GROUP_CODE_SORTABLE = "groupCode_sortable";
	
	public static final String GROUP_NAME_SORTABLE = "groupName_sortable";

	public static final String GROUP_NAME_EN_SORTABLE = "groupNameEN_sortable";

	public static final String GROUP_DESCRIPTION_SORTABLE = "groupDescription_sortable";
	
	public DictGroupTempTerm() {

		// TODO Auto-generated constructor stub

	}

	private long dictGroupId;
	private long companyId;
	private long groupId;
	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	private long dictCollectionId;
	private String groupCode;
	private String groupName;
	private String groupNameEN;
	private String groupDescription;

	public long getDictGroupId() {
		return dictGroupId;
	}
	public void setDictGroupId(long dictGroupId) {
		this.dictGroupId = dictGroupId;
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
	public long getDictCollectionId() {
		return dictCollectionId;
	}
	public void setDictCollectionId(long dictCollectionId) {
		this.dictCollectionId = dictCollectionId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupNameEN() {
		return groupNameEN;
	}
	public void setGroupNameEN(String groupNameEN) {
		this.groupNameEN = groupNameEN;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	
}