package org.opencps.usermgt.constants;

import java.util.Date;

import javax.portlet.ActionRequest;

import org.opencps.datamgt.utils.DateTimeUtils;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Binhth
 * @see JoinSiteStatusTerm
 */

public class JoinSiteStatusTerm {

	public static final String JOIN_SITE_STATUS_ID = "JoinSiteStatusId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";
	
	public static final String USER_ID = "userId";
	
	public static final String USER_NAME = "userName";
	
	public static final String CREATE_DATE = "createDate";
	
	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String EMPLOYEE_ID = "employeeId";
	
	public static final String JOIN_SITE_GROUP_ID = "joinSiteGroupId";
	
	public static final String STATUS = "status";

	public static final String JOINSITESTATUS = "JoinSiteStatus";
	// sortable
	public static final String JOIN_SITE_STATUS_ID_SORTABLE = "JoinSiteStatusId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String EMPLOYEE_ID_SORTABLE = "employeeId_sortable";
	
	public static final String JOIN_SITE_GROUP_ID_SORTABLE = "joinSiteGroupId_sortable";
	
	public static final String STATUS_SORTABLE = "status_sortable";

	private long JoinSiteStatusId;

	private long groupId;

	private long companyId;
	
	private long userId;
	
	private String userName;
	
	private Date createDate;
	
	private Date modifiedDate;

	private long joinSiteGroupId;
	
	private long employeeId;
	
	private int status;
	
	public JoinSiteStatusTerm() {
		
	}

	public JoinSiteStatusTerm(ActionRequest request) {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		JoinSiteStatusId = ParamUtil.getLong(request, JOIN_SITE_STATUS_ID);
		
		groupId = themeDisplay.getScopeGroupId();
		
		companyId = themeDisplay.getCompanyId();
		
		userId = themeDisplay.getUserId();
		
		userName = themeDisplay.getUser().getFullName();
		
		createDate = ParamUtil.getDate(request, CREATE_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		modifiedDate = ParamUtil.getDate(request, MODIFIED_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		employeeId = ParamUtil.getLong(request, EMPLOYEE_ID);
		
		joinSiteGroupId = ParamUtil.getLong(request, JOIN_SITE_GROUP_ID);
		
		status = ParamUtil.getInteger(request, STATUS);
		
	}

	public long getJoinSiteStatusId() {
		return JoinSiteStatusId;
	}

	public void setJoinSiteStatusId(long joinSiteStatusId) {
		JoinSiteStatusId = joinSiteStatusId;
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

	public long getJoinSiteGroupId() {
		return joinSiteGroupId;
	}

	public void setJoinSiteGroupId(long joinSiteGroupId) {
		this.joinSiteGroupId = joinSiteGroupId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
