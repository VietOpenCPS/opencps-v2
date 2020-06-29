package org.opencps.api.oai.model.oaipmh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oMDeliverable", propOrder = { "deliverableId", "companyId", "groupId", "userId", "userName",
		"createDate", "modifiedDate", "deliverableCode", "deliverableName", "deliverableType", "govAgencyCode", "govAgencyName",
		"applicantIdNo", "applicantName", "subject", "formData", "formScript", "formScriptFileId", "formReport", "formReportFileId",
		"issueDate", "expireDate",
		"revalidate", "deliverableState", "fileEntryId", "docSync", "dossierId", "fileAttachs", "domain", "filePath", "referenceUid" })
public class OMDeliverable {

	@XmlElement(required = true)
	@XmlSchemaType(name = "anyURI")
	protected Long deliverableId;
	protected Long groupId;
	protected Long companyId;
	protected Long userId;
	protected Long userName;
	protected String createDate;
	protected String modifiedDate;
	protected String deliverableCode;
	protected String deliverableName;
	protected String deliverableType;
	protected String govAgencyCode;
	protected String govAgencyName;
	protected String applicantIdNo;
	protected String applicantName;
	protected String subject;
	protected String formData;
	protected String formScript;
	protected Long formScriptFileId;
	protected String formReport;
	protected Long formReportFileId;
	protected String issueDate;
	protected String expireDate;
	protected String revalidate;
	protected String deliverableState;
	protected Long fileEntryId;
	protected String docSync;
	protected Long dossierId;
	protected String fileAttachs;
	protected String domain;
	protected String filePath;
	protected String referenceUid;

	public String getReferenceUid() {
		return referenceUid;
	}
	public void setReferenceUid(String referenceUid) {
		this.referenceUid = referenceUid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getDeliverableId() {
		return deliverableId;
	}
	public void setDeliverableId(Long deliverableId) {
		this.deliverableId = deliverableId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserName() {
		return userName;
	}
	public void setUserName(Long userName) {
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
	public String getDeliverableCode() {
		return deliverableCode;
	}
	public void setDeliverableCode(String deliverableCode) {
		this.deliverableCode = deliverableCode;
	}
	public String getDeliverableName() {
		return deliverableName;
	}
	public void setDeliverableName(String deliverableName) {
		this.deliverableName = deliverableName;
	}
	public String getDeliverableType() {
		return deliverableType;
	}
	public void setDeliverableType(String deliverableType) {
		this.deliverableType = deliverableType;
	}
	public String getGovAgencyCode() {
		return govAgencyCode;
	}
	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}
	public String getGovAgencyName() {
		return govAgencyName;
	}
	public void setGovAgencyName(String govAgencyName) {
		this.govAgencyName = govAgencyName;
	}
	public String getApplicantIdNo() {
		return applicantIdNo;
	}
	public void setApplicantIdNo(String applicantIdNo) {
		this.applicantIdNo = applicantIdNo;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFormData() {
		return formData;
	}
	public void setFormData(String formData) {
		this.formData = formData;
	}
	public String getFormScript() {
		return formScript;
	}
	public void setFormScript(String formScript) {
		this.formScript = formScript;
	}
	public Long getFormScriptFileId() {
		return formScriptFileId;
	}
	public void setFormScriptFileId(Long formScriptFileId) {
		this.formScriptFileId = formScriptFileId;
	}
	public String getFormReport() {
		return formReport;
	}
	public void setFormReport(String formReport) {
		this.formReport = formReport;
	}
	public Long getFormReportFileId() {
		return formReportFileId;
	}
	public void setFormReportFileId(Long formReportFileId) {
		this.formReportFileId = formReportFileId;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getRevalidate() {
		return revalidate;
	}
	public void setRevalidate(String revalidate) {
		this.revalidate = revalidate;
	}
	public String getDeliverableState() {
		return deliverableState;
	}
	public void setDeliverableState(String deliverableState) {
		this.deliverableState = deliverableState;
	}
	public Long getFileEntryId() {
		return fileEntryId;
	}
	public void setFileEntryId(Long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}
	public String getDocSync() {
		return docSync;
	}
	public void setDocSync(String docSync) {
		this.docSync = docSync;
	}
	public Long getDossierId() {
		return dossierId;
	}
	public void setDossierId(Long dossierId) {
		this.dossierId = dossierId;
	}
	public String getFileAttachs() {
		return fileAttachs;
	}
	public void setFileAttachs(String fileAttachs) {
		this.fileAttachs = fileAttachs;
	}
	public OMDeliverable(Long deliverableId, Long groupId, Long companyId, Long userId, Long userName,
			String createDate, String modifiedDate, String deliverableCode, String deliverableName,
			String deliverableType, String govAgencyCode, String govAgencyName, String applicantIdNo,
			String applicantName, String subject, String formData, String formScript, Long formScriptFileId,
			String formReport, Long formReportFileId, String issueDate, String expireDate, String revalidate,
			String deliverableState, Long fileEntryId, String docSync, Long dossierId, String fileAttachs) {
		super();
		this.deliverableId = deliverableId;
		this.groupId = groupId;
		this.companyId = companyId;
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.deliverableCode = deliverableCode;
		this.deliverableName = deliverableName;
		this.deliverableType = deliverableType;
		this.govAgencyCode = govAgencyCode;
		this.govAgencyName = govAgencyName;
		this.applicantIdNo = applicantIdNo;
		this.applicantName = applicantName;
		this.subject = subject;
		this.formData = formData;
		this.formScript = formScript;
		this.formScriptFileId = formScriptFileId;
		this.formReport = formReport;
		this.formReportFileId = formReportFileId;
		this.issueDate = issueDate;
		this.expireDate = expireDate;
		this.revalidate = revalidate;
		this.deliverableState = deliverableState;
		this.fileEntryId = fileEntryId;
		this.docSync = docSync;
		this.dossierId = dossierId;
		this.fileAttachs = fileAttachs;
	}
	
	public OMDeliverable() {
		super();
		// TODO Auto-generated constructor stub
	}
}
