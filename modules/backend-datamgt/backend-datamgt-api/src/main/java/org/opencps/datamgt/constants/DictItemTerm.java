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
 * @see DictItemTerm
 */
public class DictItemTerm {

	public static final String DICT_ITEM_ID = "dictItemId";
	
	public static final String COMPANY_ID = "companyId";
	
	public static final String GROUP_ID = "groupId";
	
	public static final String USER_ID = "userId";
	
	public static final String USER_NAME = "userName";
	
	public static final String CREATE_DATE = "createDate";
	
	public static final String MODIFIED_DATE = "modifiedDate";
	
	public static final String DICT_COLLECTION_ID = "dictCollectionId";
	
	public static final String ITEM_CODE = "itemCode";
	
	public static final String UN_ITEM_CODE = "unItemCode";
	
	public static final String DICT_COLLECTION_CODE = "dictCollectionCode";
	
	public static final String ITEM_NAME = "itemName";
	
	public static final String ITEM_NAME_EN = "itemNameEN";
	
	public static final String ITEM_DESCRIPTION = "itemDescription";
	
	public static final String PARENT_ITEM_ID = "parentItemId";
	
	public static final String PARENT_ITEM_CODE = "parentItemCode";
	
	public static final String SIBLING = "sibling";
	
	public static final String SIBLING_SEARCH = "siblingSearch";
	
	public static final String SIBLING_AGENCY = "siblingAgency";
	
	public static final String SIBLING_DOMAIN = "siblingDomain";
	
	public static final String TREE_INDEX = "treeIndex";
	
	public static final String LEVEL = "level";
	
	public static final String META_DATA = "metaData";
	
	public static final String IS_GLOBAL = "isGlobal";
	
	//sortable
	public static final String DICT_ITEM_ID_SORTABLE = "dictItemId_sortable";
	
	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String GROUP_ID_SORTABLE = "groupId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";
	
	public static final String DICT_COLLECTION_ID_SORTABLE = "dictCollectionId_sortable";
	
	public static final String ITEM_CODE_SORTABLE = "itemCode_sortable";
	
	public static final String ITEM_NAME_SORTABLE = "itemName_sortable";
	
	public static final String ITEM_NAME_EN_SORTABLE = "itemNameEN_sortable";
	
	public static final String ITEM_DESCRIPTION_SORTABLE = "itemDescription_sortable";
	
	public static final String PARENT_ITEM_ID_SORTABLE = "parentItemId_sortable";
	
	public static final String SIBLING_SORTABLE = "sibling_sortable";
	
	public static final String SIBLING_SEARCH_SORTABLE = "siblingSearch_sortable";
	
	public static final String SIBLING_AGENCY_SORTABLE = "siblingAgency_sortable";
	
	public static final String SIBLING_DOMAIN_SORTABLE = "siblingDomain_sortable";
	
	public static final String TREE_INDEX_SORTABLE = "treeIndex_sortable";
	
	public static final String ISSUE_STATUS_SORTABLE = "issueStatus_sortable";
	
	public static final String LEVEL_SORTABLE = "level_sortable";
	
	public static final String META_DATA_SORTABLE = "metaData_sortable";
	
	public DictItemTerm() {

		// TODO Auto-generated constructor stub

	}

	private long dictItemId;
	private long companyId;
	private long groupId;
	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	private long dictCollectionId;
	private String itemCode;
	private String itemName;
	private String itemNameEN;
	private String itemDescription;
	private long parentItemId;
	private String sibling;
	private String treeIndex;
	private int level;
	private String metaData;
	
	public long getDictItemId() {
		return dictItemId;
	}

	public void setDictItemId(long dictItemId) {
		this.dictItemId = dictItemId;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public long getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(long parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getSibling() {
		return sibling;
	}

	public void setSibling(String sibling) {
		this.sibling = sibling;
	}

	public String getTreeIndex() {
		return treeIndex;
	}

	public void setTreeIndex(String treeIndex) {
		this.treeIndex = treeIndex;
	}

	public String getitemNameEN() {
		return itemNameEN;
	}

	public void setitemNameEN(String itemNameEN) {
		this.itemNameEN = itemNameEN;
	}

	public String getItemNameEN() {
		return itemNameEN;
	}

	public void setItemNameEN(String itemNameEN) {
		this.itemNameEN = itemNameEN;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

}