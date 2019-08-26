package org.graphql.api.model;

import org.opencps.dossiermgt.model.DeliverableType;

public class DeliverableTypeDynamic {

	public DeliverableTypeDynamic(DeliverableType deliverableType, Boolean moderator) {
		
		this.uuid_ = deliverableType.getUserUuid();
		this.deliverableTypeId = deliverableType.getDeliverableTypeId();
		this.companyId = deliverableType.getCompanyId();
		this.groupId = deliverableType.getGroupId();
		this.userId = deliverableType.getUserId();
		this.userName = deliverableType.getUserName();
		this.createDate = deliverableType.getCreateDate().toString();
		this.modifiedDate = deliverableType.getModifiedDate().toString();
		this.typeCode = deliverableType.getTypeCode();
		this.typeName = deliverableType.getTypeName();
		this.formScriptFileId = deliverableType.getFormScriptFileId();
		this.formReportFileId = deliverableType.getFormReportFileId();
		this.codePattern = deliverableType.getCodePattern();
		this.counter = deliverableType.getCounter();
		this.mappingData = deliverableType.getMappingData();
		this.dataConfig = deliverableType.getDataConfig();
		this.tableConfig = deliverableType.getTableConfig();
		this.docSync = deliverableType.getDocSync();
		this.govAgencies = deliverableType.getGovAgencies();
		this.moderator = moderator;
	}
	
	String uuid_;
	Long deliverableTypeId;
	Long companyId;
	Long groupId;
	Long userId;
	String userName;
	String createDate;
	String modifiedDate;
	String typeCode;
	String typeName;
	Long formScriptFileId;
	Long formReportFileId;
	String codePattern;
	Long counter;
	String mappingData;
	String dataConfig;
	String tableConfig;
	Integer docSync;
	String govAgencies;
	Boolean  moderator;
	
	public String getUuid_() {
	
		return uuid_;
	}
	
	public void setUuid_(String uuid_) {
	
		this.uuid_ = uuid_;
	}
	
	public Long getDeliverableTypeId() {
	
		return deliverableTypeId;
	}
	
	public void setDeliverableTypeId(Long deliverableTypeId) {
	
		this.deliverableTypeId = deliverableTypeId;
	}
	
	public Long getCompanyId() {
	
		return companyId;
	}
	
	public void setCompanyId(Long companyId) {
	
		this.companyId = companyId;
	}
	
	public Long getGroupId() {
	
		return groupId;
	}
	
	public void setGroupId(Long groupId) {
	
		this.groupId = groupId;
	}
	
	public Long getUserId() {
	
		return userId;
	}
	
	public void setUserId(Long userId) {
	
		this.userId = userId;
	}
	
	public String getUserName() {
	
		return userName;
	}
	
	public void setUserName(String userName) {
	
		this.userName = userName;
	}
	
	public String getCreateDate() {
	
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
	
		this.createDate = createDate;
	}
	
	public String getModifiedDate() {
	
		return modifiedDate;
	}
	
	public void setModifiedDate(String modifiedDate) {
	
		this.modifiedDate = modifiedDate;
	}
	
	public String getTypeCode() {
	
		return typeCode;
	}
	
	public void setTypeCode(String typeCode) {
	
		this.typeCode = typeCode;
	}
	
	public String getTypeName() {
	
		return typeName;
	}
	
	public void setTypeName(String typeName) {
	
		this.typeName = typeName;
	}
	
	public Long getFormScriptFileId() {
	
		return formScriptFileId;
	}
	
	public void setFormScriptFileId(Long formScriptFileId) {
	
		this.formScriptFileId = formScriptFileId;
	}
	
	public Long getFormReportFileId() {
	
		return formReportFileId;
	}
	
	public void setFormReportFileId(Long formReportFileId) {
	
		this.formReportFileId = formReportFileId;
	}
	
	public String getCodePattern() {
	
		return codePattern;
	}
	
	public void setCodePattern(String codePattern) {
	
		this.codePattern = codePattern;
	}
	
	public Long getCounter() {
	
		return counter;
	}
	
	public void setCounter(Long counter) {
	
		this.counter = counter;
	}
	
	public String getMappingData() {
	
		return mappingData;
	}
	
	public void setMappingData(String mappingData) {
	
		this.mappingData = mappingData;
	}
	
	public String getDataConfig() {
	
		return dataConfig;
	}
	
	public void setDataConfig(String dataConfig) {
	
		this.dataConfig = dataConfig;
	}
	
	public String getTableConfig() {
	
		return tableConfig;
	}
	
	public void setTableConfig(String tableConfig) {
	
		this.tableConfig = tableConfig;
	}
	
	public Integer getDocSync() {
	
		return docSync;
	}
	
	public void setDocSync(Integer docSync) {
	
		this.docSync = docSync;
	}
	
	public String getGovAgencies() {
	
		return govAgencies;
	}
	
	public void setGovAgencies(String govAgencies) {
	
		this.govAgencies = govAgencies;
	}
	
	public Boolean getModerator() {
	
		return moderator;
	}
	
	public void setModerator(Boolean moderator) {
	
		this.moderator = moderator;
	}

}
