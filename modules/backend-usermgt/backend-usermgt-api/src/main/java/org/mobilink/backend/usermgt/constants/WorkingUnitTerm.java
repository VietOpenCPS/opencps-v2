package org.mobilink.backend.usermgt.constants;

import java.util.Date;

import javax.portlet.ActionRequest;

import org.opencps.datamgt.utils.DateTimeUtils;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Binhth
 * @see WorkingUnitTerm
 */

public class WorkingUnitTerm {

	public static final String WORKINGUNIT_ID = "workingUnitId";

	public static final String GROUP_ID = "groupId";

	public static final String COMPANY_ID = "companyId";
	
	public static final String USER_ID = "userId";
	
	public static final String USER_NAME = "userName";
	
	public static final String CREATE_DATE = "createDate";
	
	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String NAME = "name";

	public static final String ENNAME = "enName";

	public static final String GOV_AGENCY_CODE = "govAgencyCode";
	
	public static final String PARENT_WORKING_UNIT_ID = "parentWorkingUnitId";
	
	public static final String SIBLING = "sibling";
	
	public static final String TREEINDEX = "treeIndex";
	
	public static final String ADDRESS = "address";
	
	public static final String CITY_CODE = "cityCode";
	
	public static final String DISTRICT_CODE = "districtCode";
	
	public static final String WARD_CODE = "wardCode";
	
	public static final String TEL_NO = "telNo";
	
	public static final String FAX_NO = "faxNo";
	
	public static final String EMAIL = "email";
	
	public static final String WEBSITE = "website";
	
	public static final String IS_EMPLOYER = "isEmployer";
	
	public static final String MANAGER_WORKING_UNIT_ID = "managerWorkingUnitId";
	
	public static final String MAPPING_ORGANISATION_ID = "mappingOrganisationId";
	
	public static final String SITE_TEMPLATE_ID = "siteTemplateId";
	// sortable
	public static final String WORKINGUNIT_ID_SORTABLE = "workingUnitId_sortable";

	public static final String GROUP_ID_SORTABLE = "groupId_sortable";

	public static final String COMPANY_ID_SORTABLE = "companyId_sortable";
	
	public static final String USER_ID_SORTABLE = "userId_sortable";
	
	public static final String USER_NAME_SORTABLE = "userName_sortable";
	
	public static final String CREATE_DATE_SORTABLE = "createDate_sortable";
	
	public static final String MODIFIED_DATE_SORTABLE = "modifiedDate_sortable";

	public static final String NAME_SORTABLE = "name_sortable";

	public static final String ENNAME_SORTABLE = "enName_sortable";

	public static final String GOV_AGENCY_CODE_SORTABLE = "govAgencyCode_sortable";
	
	public static final String PARENT_WORKING_UNIT_ID_SORTABLE = "parentWorkingUnitId_sortable";
	
	public static final String SIBLING_SORTABLE = "sibling_sortable";
	
	public static final String TREEINDEX_SORTABLE = "treeIndex_sortable";
	
	public static final String ADDRESS_SORTABLE = "address_sortable";
	
	public static final String CITY_CODE_SORTABLE = "cityCode_sortable";
	
	public static final String DISTRICT_CODE_SORTABLE = "districtCode_sortable";
	
	public static final String WARD_CODE_SORTABLE = "wardCode_sortable";
	
	public static final String TEL_NO_SORTABLE = "telNo_sortable";
	
	public static final String FAX_NO_SORTABLE = "faxNo_sortable";
	
	public static final String EMAIL_SORTABLE = "email_sortable";
	
	public static final String WEBSITE_SORTABLE = "website_sortable";
	
	public static final String IS_EMPLOYER_SORTABLE = "isEmployer_sortable";
	
	public static final String MANAGER_WORKING_UNIT_ID_SORTABLE = "managerWorkingUnitId_sortable";
	
	public static final String MAPPING_ORGANISATION_ID_SORTABLE = "mappingOrganisationId_sortable";
	
	private long workingUnitId;

	private long groupId;

	private long companyId;
	
	private long userId;
	
	private String userName;
	
	private Date createDate;
	
	private Date modifiedDate;

	private String name;
	private String enName;
	private String govAgencyCode;
	private long parentWorkingUnitId;
	private String sibling;
	private String treeIndex;
	private String address;
	private String cityCode;
	private String districtCode;
	private String wardCode;
	private String telNo;
	private String faxNo;
	private String email;
	private String website;
	private boolean isEmployer;
	private long managerWorkingUnitId;
	private long mappingOrganisationId;
	private long employeeTemplateId;
	private long partnerTemplateId;
	private long adminUserId;
	
	public WorkingUnitTerm() {
		
	}

	public WorkingUnitTerm(ActionRequest request) {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		workingUnitId = ParamUtil.getLong(request, WORKINGUNIT_ID);
		
		groupId = themeDisplay.getScopeGroupId();
		
		companyId = themeDisplay.getCompanyId();
		
		userId = themeDisplay.getUserId();
		
		userName = themeDisplay.getUser().getFullName();
		
		createDate = ParamUtil.getDate(request, CREATE_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		modifiedDate = ParamUtil.getDate(request, MODIFIED_DATE,
				DateTimeUtils.getDateTimeFormat(DateTimeUtils._VN_DATE_TIME_FORMAT));
		
		name = ParamUtil.getString(request, NAME);
		enName = ParamUtil.getString(request, ENNAME);
		govAgencyCode = ParamUtil.getString(request, GOV_AGENCY_CODE);
		parentWorkingUnitId = ParamUtil.getLong(request, PARENT_WORKING_UNIT_ID);
		sibling = ParamUtil.getString(request, SIBLING);
		treeIndex = ParamUtil.getString(request, TREEINDEX);
		address = ParamUtil.getString(request, ADDRESS);
		cityCode = ParamUtil.getString(request, CITY_CODE);
		districtCode = ParamUtil.getString(request, DISTRICT_CODE);
		wardCode = ParamUtil.getString(request, WARD_CODE);
		telNo = ParamUtil.getString(request, TEL_NO);
		faxNo = ParamUtil.getString(request, FAX_NO);
		email = ParamUtil.getString(request, EMAIL);
		website = ParamUtil.getString(request, WEBSITE);
		isEmployer = ParamUtil.getBoolean(request, IS_EMPLOYER);
		managerWorkingUnitId = ParamUtil.getLong(request, MANAGER_WORKING_UNIT_ID);
		mappingOrganisationId = ParamUtil.getLong(request, MAPPING_ORGANISATION_ID);
		
	}

	public long getWorkingUnitId() {
		return workingUnitId;
	}

	public void setWorkingUnitId(long workingUnitId) {
		this.workingUnitId = workingUnitId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getGovAgencyCode() {
		return govAgencyCode;
	}

	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}

	public long getParentWorkingUnitId() {
		return parentWorkingUnitId;
	}

	public void setParentWorkingUnitId(long parentWorkingUnitId) {
		this.parentWorkingUnitId = parentWorkingUnitId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public boolean isEmployer() {
		return isEmployer;
	}

	public void setEmployer(boolean isEmployer) {
		this.isEmployer = isEmployer;
	}

	public long getManagerWorkingUnitId() {
		return managerWorkingUnitId;
	}

	public void setManagerWorkingUnitId(long managerWorkingUnitId) {
		this.managerWorkingUnitId = managerWorkingUnitId;
	}

	public long getMappingOrganisationId() {
		return mappingOrganisationId;
	}

	public void setMappingOrganisationId(long mappingOrganisationId) {
		this.mappingOrganisationId = mappingOrganisationId;
	}

	public long getEmployeeTemplateId() {
		return employeeTemplateId;
	}

	public void setEmployeeTemplateId(long employeeTemplateId) {
		this.employeeTemplateId = employeeTemplateId;
	}

	public long getPartnerTemplateId() {
		return partnerTemplateId;
	}

	public void setPartnerTemplateId(long partnerTemplateId) {
		this.partnerTemplateId = partnerTemplateId;
	}

	public long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(long adminUserId) {
		this.adminUserId = adminUserId;
	}

}
