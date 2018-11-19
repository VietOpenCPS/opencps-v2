
package org.opencps.sms.service.dto;

public class DossierData {

    public String getDossierStatus() {

        return dossierStatus;
    }

    public void setDossierStatus(String dossierStatus) {

        this.dossierStatus = dossierStatus;
    }

    public String getDossierSubStatus() {

        return dossierSubStatus;
    }

    public void setDossierSubStatus(String dossierSubStatus) {

        this.dossierSubStatus = dossierSubStatus;
    }

    public String getReceiveDate() {

        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {

        this.receiveDate = receiveDate;
    }

    public String getOnline() {

        return online;
    }

    public void setOnline(String online) {

        this.online = online;
    }

    public String getDueDate() {

        return dueDate;
    }

    public void setDueDate(String dueDate) {

        this.dueDate = dueDate;
    }

    public String getExtendDate() {

        return extendDate;
    }

    public void setExtendDate(String extendDate) {

        this.extendDate = extendDate;
    }

    public String getReleaseDate() {

        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {

        this.releaseDate = releaseDate;
    }

    public String getGovAgencyCode() {

        return govAgencyCode;
    }

    public void setGovAgencyCode(String govAgencyCode) {

        this.govAgencyCode = govAgencyCode;
    }

    public String getServiceCode() {

        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {

        this.serviceCode = serviceCode;
    }

    public long getGroupId() {

        return groupId;
    }

    public void setGroupId(long groupId) {

        this.groupId = groupId;
    }

    public String getGovAgencyName() {

        return govAgencyName;
    }

    public void setGovAgencyName(String govAgencyName) {

        this.govAgencyName = govAgencyName;
    }

    public String getFinishDate() {

        return finishDate;
    }

    public void setFinishDate(String finishDate) {

        this.finishDate = finishDate;
    }

    public String getDossierStatusText() {

        return dossierStatusText;
    }

    public void setDossierStatusText(String dossierStatusText) {

        this.dossierStatusText = dossierStatusText;
    }

    public String getDossierSubStatusText() {

        return dossierSubStatusText;
    }

    public void setDossierSubStatusText(String dossierSubStatusText) {

        this.dossierSubStatusText = dossierSubStatusText;
    }

    public String getDossierOverdue() {

        return dossierOverdue;
    }

    public void setDossierOverdue(String dossierOverdue) {

        this.dossierOverdue = dossierOverdue;
    }
    
    public String getDossierNo() {
    
        return dossierNo;
    }

    
    public void setDossierNo(String dossierNo) {
    
        this.dossierNo = dossierNo;
    }
    
    public String getApplicantName() {
    
        return applicantName;
    }

    
    public void setApplicantName(String applicantName) {
    
        this.applicantName = applicantName;
    }
    
    private String dossierStatus;
    private String dossierSubStatus;
    private String receiveDate;
    private String online;
    private String dueDate;
    private String extendDate;
    private String releaseDate;
    private String serviceCode;
    private long groupId;
    private String govAgencyCode;
    private String govAgencyName;
    private String finishDate;
    private String dossierStatusText;
    private String dossierSubStatusText;
    private String dossierOverdue;
    private String dossierNo;
    private String applicantName;

}
