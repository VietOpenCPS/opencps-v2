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

package org.opencps.datamgt.constants;

import java.util.Date;

/**
 * @author Binhth
 * @see DictCollectionTerm
 */
public class DictCollectionTerm {

	public static final String DICT_COLLECTION_ID = "dictCollectionId";

	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String COLLECTION_CODE = "collectionCode";

	public static final String COLLECTION_NAME = "collectionName";
	
	public static final String COLLECTION_NAME_EN = "collectionNameEN";

	public static final String DESCRIPTION = "description";

	public static final String IS_GLOBAL = "isGlobal";
	
	public static final String DATAFORM = "dataForm";
	
	public static final String STATUS = "status";
	
	// sortable
	public static final String DICT_COLLECTION_ID_SORTABLE = "dictCollectionId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String COLLECTION_CODE_SORTABLE = "collectionCode_sortable";

	public static final String COLLECTION_NAME_SORTABLE = "collectionName_sortable";

	public static final String DESCRIPTION_SORTABLE = "description_sortable";

	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	
	public DictCollectionTerm() {

		// TODO Auto-generated constructor stub

	}

	private long dictCollectionId;
	private long companyId;
	private long groupId;
	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	private String collectionCode;
	private String collectionName;
	private String description;
	private String collectionNameEN;
	private String dataForm;
	
	public long getDictCollectionId() {
		return dictCollectionId;
	}

	public void setDictCollectionId(long dictCollectionId) {
		this.dictCollectionId = dictCollectionId;
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

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCollectionNameEN() {
		return collectionNameEN;
	}

	public void setCollectionNameEN(String collectionNameEN) {
		this.collectionNameEN = collectionNameEN;
	}

	public String getDataForm() {
		return dataForm;
	}

	public void setDataForm(String dataForm) {
		this.dataForm = dataForm;
	}

}