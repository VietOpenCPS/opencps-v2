package org.opencps.usermgt.constants;

import java.util.Date;

import javax.portlet.ActionRequest;

import org.opencps.datamgt.utils.DateTimeUtils;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Binhth
 * @see ContactGroupTerm
 */

public class ContactGroupTerm {

	public static final String COMPANY_ID = "companyId";

	public static final String CONTACTGROUP = "contactGroup";

	public static final String CONTACT_GROUP_ID = "contactGroupId";

	public static final String CREATE_DATE = "createDate";

	public static final String GROUP_ID = "groupId";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String GROUP_NAME = "groupName";

	public static final String CONTACT_LIST = "contactList";

	public static final String SHARED = "shared";
	
	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	// sortable
	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";

	public static final String CONTACTGROUP_SORTABLE = "contactGroup_sortable";

	public static final String CONTACT_GROUP_ID_SORTABLE = "contactGroupId_sortable";

	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String GROUP_NAME_SORTABLE = "groupName_sortable";

	public static final String CONTACT_LIST_SORTABLE = "contactList_sortable";

	public static final String SHARED_SORTABLE = "shared_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";

	public static final String USER_NAME_SORTABLE = "userName_sortable";

	private long companyId;
	private long contactGroupId;
	private Date createDate;
	private long groupId;
	private Date modifiedDate;
	private int shared;
	private long userId;
	private String groupName;
	private String contactList;

	public ContactGroupTerm() {
	}

	public ContactGroupTerm(ActionRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		contactGroupId = ParamUtil.getLong(request, CONTACT_GROUP_ID);
		groupId = themeDisplay.getScopeGroupId();
		companyId = themeDisplay.getCompanyId();
		userId = themeDisplay.getUserId();
		createDate = ParamUtil.getDate(request, CREATE_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		modifiedDate = ParamUtil.getDate(request, MODIFIED_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		groupName = ParamUtil.getString(request, GROUP_NAME);
		contactList = ParamUtil.getString(request, CONTACT_LIST);
		shared = ParamUtil.getInteger(request, SHARED);
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getContactGroupId() {
		return contactGroupId;
	}

	public void setContactGroupId(long contactGroupId) {
		this.contactGroupId = contactGroupId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getShared() {
		return shared;
	}

	public void setShared(int shared) {
		this.shared = shared;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getContactList() {
		return contactList;
	}

	public void setContactList(String contactList) {
		this.contactList = contactList;
	}

}
