package org.opencps.usermgt.constants;

import java.io.File;
import java.util.Date;

import javax.portlet.ActionRequest;

import org.opencps.datamgt.utils.DateTimeUtils;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Binhth
 * @see EmployeeFileTerm
 */

public class EmployeeFileTerm {

	public static final String EMPLOYEE_FILE_ID = "employeeFileId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";
	
	public static final String USER_ID = "userId";
	
	public static final String USER_NAME = "userName";
	
	public static final String CREATE_DATE = "createDate";
	
	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String EMPLOYEE_ID = "employeeId";
	
	public static final String FILE_ENTRY_ID = "fileEntryId";
	
	public static final String DOCUMENT_NAME = "documentName";

	public static final String EMPLOYEEFILE = "employeeFile";
	// sortable
	public static final String EMPLOYEE_FILE_ID_SORTABLE = "employeeFileId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String EMPLOYEE_ID_SORTABLE = "employeeId_sortable";
	
	public static final String FILE_ENTRY_ID_SORTABLE = "fileEntryId_sortable";
	
	public static final String DOCUMENT_NAME_SORTABLE = "documentName_sortable";

	private long employeeFileId;

	private long groupId;

	private long companyId;
	
	private long userId;
	
	private String userName;
	
	private Date createDate;
	
	private Date modifiedDate;

	private long employeeId;
	
	private long fileEntryId;
	
	private String documentName;
	
	private String fileName;
	
	private File file;
	
	private String mimeType;
	
	public EmployeeFileTerm() {
		
	}

	public EmployeeFileTerm(ActionRequest actionRequest) {

		UploadPortletRequest request = PortalUtil.getUploadPortletRequest(actionRequest);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		fileName= request.getFileName("uploadedFile");
		
		file =  request.getFile("uploadedFile");

		mimeType = request.getContentType("uploadedFile");
		
		employeeFileId = ParamUtil.getLong(request, EMPLOYEE_FILE_ID);
		
		groupId = themeDisplay.getScopeGroupId();
		
		companyId = themeDisplay.getCompanyId();
		
		userId = themeDisplay.getUserId();
		
		userName = themeDisplay.getUser().getFullName();
		
		createDate = ParamUtil.getDate(request, CREATE_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		modifiedDate = ParamUtil.getDate(request, MODIFIED_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		employeeId = ParamUtil.getLong(request, EMPLOYEE_ID);
		
		fileEntryId = ParamUtil.getLong(request, FILE_ENTRY_ID);
		
		documentName = ParamUtil.getString(request, DOCUMENT_NAME);
		
		if(Validator.isNull(documentName)){
			documentName = fileName;
		}
		
	}

	public long getEmployeeFileId() {
		return employeeFileId;
	}

	public void setEmployeeFileId(long employeeFileId) {
		this.employeeFileId = employeeFileId;
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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
