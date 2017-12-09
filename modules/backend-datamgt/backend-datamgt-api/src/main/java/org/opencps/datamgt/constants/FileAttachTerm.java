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

public class FileAttachTerm {

	public static final String FILEATTACH_ID = "fileAttachId";

	public static final String COMPANY_ID = "companyId";

	public static final String GROUP_ID = "groupId";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String CLASS_NAME = "className";

	public static final String CLASS_PK = "classPK";

	public static final String FULLNAME = "fullName";

	public static final String EMAIL = "email";

	public static final String FILE_ENTRY_ID = "fileEntryId";

	public static final String SOURCE = "source";

	public static final String SOURCE_URL = "sourceUrl";

	public static final String DOCFILE_ID = "docFileId";
	
	public static final String FILE_NAME = "fileName";

	private long fileAttachId;
	private long companyId;
	private long groupId;

	private long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;

	private String className;
	private String classPK;
	private String fullName;
	private String email;
	private long fileEntryId;
	private String source;
	private String sourceUrl;
	private long docFileId;
	private String fileName;
	
	public FileAttachTerm() {

		// TODO Auto-generated constructor stub

	}

	public long getFileAttachId() {
		return fileAttachId;
	}

	public void setFileAttachId(long fileAttachId) {
		this.fileAttachId = fileAttachId;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public long getDocFileId() {
		return docFileId;
	}

	public void setDocFileId(long docFileId) {
		this.docFileId = docFileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
