package org.opencps.api.oai.model.oaipmh;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oMApplicant", propOrder = {
        "screenName",
        "groupId",
        "fullName",
        "email",
        "telNo",
        "workingStatus",
        "govAgencyCode",
        "govAgencyName",
        "roles",
        "modifiedDate"

})
public class OMApplicant {
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String screenName;
    protected long groupId;
    @XmlElement(required = true)
    protected String fullName;
    protected String email;
    protected String telNo;
    protected Long workingStatus;
    protected String govAgencyCode;
    protected String govAgencyName;
    protected OMRoles roles;
    protected String modifiedDate;


    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Long getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(Long workingStatus) {
        this.workingStatus = workingStatus;
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

    public OMRoles getRoles() {
        return roles;
    }

    public void setRoles(OMRoles roles) {
        this.roles = roles;
    }
}
