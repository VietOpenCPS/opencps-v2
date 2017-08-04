package org.mobilink.backend.usermgt.constants;

import java.util.Date;

import javax.portlet.ActionRequest;

import org.opencps.datamgt.utils.DateTimeUtils;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Binhth
 * @see JobPosTerm
 */

public class JobPosTerm {

	public static final String JOBPOS_ID = "jobPosId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";
	
	public static final String USER_ID = "userId";
	
	public static final String USER_NAME = "userName";
	
	public static final String CREATE_DATE = "createDate";
	
	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String WORKING_UNIT_ID = "workingUnitId";
	
	public static final String TITLE = "title";
	
	public static final String DESCRIPTION = "description";
	
	public static final String DIRECT_WORKING_UNIT_ID = "directWorkingUnitId";
	
	public static final String LEADER = "leader";
	
	public static final String MAPPING_ROLE_ID = "mappingRoleId";
	
	public static final String HIDDEN_JOBPOS = "hiddenJobPos";
	
	public static final String MJOBPOS = "mJobPos";
	// sortable
	public static final String JOBPOS_ID_SORTABLE = "jobPosId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String WORKING_UNIT_ID_SORTABLE = "workingUnitId_sortable";
	
	public static final String TITLE_SORTABLE = "title_sortable";
	
	public static final String DESCRIPTION_SORTABLE = "description_sortable";
	
	public static final String DIRECT_WORKING_UNIT_ID_SORTABLE = "directWorkingUnitId_sortable";
	
	public static final String LEADER_SORTABLE = "leader_sortable";
	
	public static final String MAPPING_ROLE_ID_SORTABLE = "mappingRoleId_sortable";
	
	public static final String HIDDEN_JOBPOS_SORTABLE = "hiddenJobPos_sortable";

	private long jobPosId;

	private long groupId;

	private long companyId;
	
	private long userId;
	
	private String userName;
	
	private Date createDate;
	
	private Date modifiedDate;

	private long workingUnitId;
	
	private String title;
	
	private String description;
	
	private long directWorkingUnitId;
	
	private int leader;
	
	private long mappingRoleId;
	
	private boolean hiddenJobPos;
	
	public JobPosTerm() {
		
	}

	public JobPosTerm(ActionRequest request) {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		jobPosId = ParamUtil.getLong(request, JOBPOS_ID);
		
		groupId = themeDisplay.getScopeGroupId();
		
		companyId = themeDisplay.getCompanyId();
		
		userId = themeDisplay.getUserId();
		
		userName = themeDisplay.getUser().getFullName();
		
		createDate = ParamUtil.getDate(request, CREATE_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		modifiedDate = ParamUtil.getDate(request, MODIFIED_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		workingUnitId = ParamUtil.getLong(request, WORKING_UNIT_ID);
		
		title = ParamUtil.getString(request, TITLE);
		
		description = ParamUtil.getString(request, DESCRIPTION);
		
		directWorkingUnitId = ParamUtil.getLong(request, DIRECT_WORKING_UNIT_ID);
		
		leader = ParamUtil.getInteger(request, LEADER);
		
		mappingRoleId = ParamUtil.getLong(request, MAPPING_ROLE_ID);
		
		hiddenJobPos = ParamUtil.getBoolean(request, HIDDEN_JOBPOS);
		
	}

	public long getJobPosId() {
		return jobPosId;
	}

	public void setJobPosId(long jobPosId) {
		this.jobPosId = jobPosId;
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

	public long getWorkingUnitId() {
		return workingUnitId;
	}

	public void setWorkingUnitId(long workingUnitId) {
		this.workingUnitId = workingUnitId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDirectWorkingUnitId() {
		return directWorkingUnitId;
	}

	public void setDirectWorkingUnitId(long directWorkingUnitId) {
		this.directWorkingUnitId = directWorkingUnitId;
	}

	public int getLeader() {
		return leader;
	}

	public void setLeader(int leader) {
		this.leader = leader;
	}

	public long getMappingRoleId() {
		return mappingRoleId;
	}

	public void setMappingRoleId(long mappingRoleId) {
		this.mappingRoleId = mappingRoleId;
	}

	public boolean isHiddenJobPos() {
		return hiddenJobPos;
	}

	public void setHiddenJobPos(boolean hiddenJobPos) {
		this.hiddenJobPos = hiddenJobPos;
	}


}
