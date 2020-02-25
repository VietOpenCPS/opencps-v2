package org.opencps.api.oai.model.oaipmh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oMDeliverableType", propOrder = { "deliverableTypeId", "companyId", "groupId", "userId", "userName",
		"createDate", "modifiedDate", "typeCode", "typeName", "formScript", "formReport", "formScriptFileId",
		"formReportFileId", "codePattern", "dataConfig", "tableConfig", "counter", "mappingData", "docSync",
		"govAgencies" })
public class OMDeliverableType {

	@XmlElement(required = true)
	@XmlSchemaType(name = "anyURI")
	protected Long deliverableTypeId;
	protected Long companyId;
	protected Long groupId;
	protected Long userId;
	protected String userName;
	protected String createDate;
	protected String modifiedDate;
	protected String typeCode;
	protected String typeName;
	protected String formScript;
	protected String formReport;
	protected Long formScriptFileId;
	protected Long formReportFileId;
	protected String codePattern;
	protected String dataConfig;
	protected String tableConfig;
	protected Long counter;
	protected String mappingData;
	protected Integer docSync;
	protected String govAgencies;

	public OMDeliverableType() {
		super();
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

	public String getFormScript() {
		return formScript;
	}

	public void setFormScript(String formScript) {
		this.formScript = formScript;
	}

	public String getFormReport() {
		return formReport;
	}

	public void setFormReport(String formReport) {
		this.formReport = formReport;
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

	public OMDeliverableType(Long deliverableTypeId, Long companyId, Long groupId, Long userId, String userName,
			String createDate, String modifiedDate, String typeCode, String typeName, String formScript,
			String formReport, Long formScriptFileId, Long formReportFileId, String codePattern, String dataConfig,
			String tableConfig, Long counter, String mappingData, Integer docSync, String govAgencies) {
		super();
		this.deliverableTypeId = deliverableTypeId;
		this.companyId = companyId;
		this.groupId = groupId;
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.formScript = formScript;
		this.formReport = formReport;
		this.formScriptFileId = formScriptFileId;
		this.formReportFileId = formReportFileId;
		this.codePattern = codePattern;
		this.dataConfig = dataConfig;
		this.tableConfig = tableConfig;
		this.counter = counter;
		this.mappingData = mappingData;
		this.docSync = docSync;
		this.govAgencies = govAgencies;
	}

}
