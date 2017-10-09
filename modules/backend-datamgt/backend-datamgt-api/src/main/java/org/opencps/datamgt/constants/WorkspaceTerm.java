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

public class WorkspaceTerm {
	
	public static final String WORKSPACE_ID = "workspaceId";

	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String WORKSPACE_NAME = "name";
	
	public static final String SCOPE = "scope";
	
	public static final String SEQ_ORDER = "seqOrder";
	
	public static final String OWN_USER = "ownUser";
	
	public static final String PARENT_ID = "parentId";
	
	public static final String TREE_INDEX = "treeIndex";
	
	public static final String LEVEL = "level";
	
	public static final String WORKSPACE_ID_SORTABLE = "workspaceId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String WORKSPACE_NAME_SORTABLE = "name_sortable";
	
	public static final String SCOPE_SORTABLE = "scope_sortable";
	
	public static final String SEQ_ORDER_SORTABLE = "seqOrder_sortable";
	
	public static final String PARENT_ID_SORTABLE = "parentId_sortable";
	
	public static final String TREE_INDEX_SORTABLE = "treeIndex_sortable";
	
	public static final String LEVEL_SORTABLE = "level_sortable";
	
	public WorkspaceTerm() {

	}

	
	private long workspaceId;
	private long companyId;
	private long groupId;

	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	private int seqOrder;
	private String scope;
	private long parentId;
	private String treeIndex;
	private int level;
	
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public long getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(long workspaceId) {
		this.workspaceId = workspaceId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	private String name;

	public int getSeqOrder() {
		return seqOrder;
	}

	public void setSeqOrder(int seqOrder) {
		this.seqOrder = seqOrder;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getTreeIndex() {
		return treeIndex;
	}

	public void setTreeIndex(String treeIndex) {
		this.treeIndex = treeIndex;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
