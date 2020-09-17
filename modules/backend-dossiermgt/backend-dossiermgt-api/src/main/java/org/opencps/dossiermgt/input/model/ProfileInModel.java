package org.opencps.dossiermgt.input.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ProfileInModel {
    public ProfilePaymentFDS payment;
    public String serviceCode;
    public String govAgencyCode;
    public String templateNo;

    public String status;

    public String type;

    public String source_id;

    public String ref_code;

    public String procedures_code;

    public String system_id;

    public String system_received;

    public String profile_ems;

    public String creation_date;

    public Integer applicants_type;

    public String applicants_id;

    public String org_impl_code;

    public String accept_date;

    public String appointment_date;

    public Integer return_type;

    public String note;

    public String from_unit_code;

    public String[] to_unit_code;

    public List<ProfileAttachment> profileAttachments;

    public List<ProfileDocFee> profileDocFees;

    public List<ProfileDocPaper> profileDocPaper;

    public ProfileOwner profileOwner;

    public ProfileApplicant profileApplicant;

    public String data_eform;

    public String status_profile;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getRef_code() {
        return ref_code;
    }

    public void setRef_code(String ref_code) {
        this.ref_code = ref_code;
    }

    public String getProcedures_code() {
        return procedures_code;
    }

    public void setProcedures_code(String procedures_code) {
        this.procedures_code = procedures_code;
    }

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public String getSystem_received() {
        return system_received;
    }

    public void setSystem_received(String system_received) {
        this.system_received = system_received;
    }

    public String getProfile_ems() {
        return profile_ems;
    }

    public void setProfile_ems(String profile_ems) {
        this.profile_ems = profile_ems;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getApplicants_type() {
        return applicants_type;
    }

    public void setApplicants_type(Integer applicants_type) {
        this.applicants_type = applicants_type;
    }

    public String getApplicants_id() {
        return applicants_id;
    }

    public void setApplicants_id(String applieants_id) {
        this.applicants_id = applieants_id;
    }

    public String getOrg_impl_code() {
        return org_impl_code;
    }

    public void setOrg_impl_code(String org_impl_code) {
        this.org_impl_code = org_impl_code;
    }

    public String getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(String accept_date) {
        this.accept_date = accept_date;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public Integer getReturn_type() {
        return return_type;
    }

    public void setReturn_type(Integer return_type) {
        this.return_type = return_type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFrom_unit_code() {
        return from_unit_code;
    }

    public void setFrom_unit_code(String from_unit_code) {
        this.from_unit_code = from_unit_code;
    }

    public String[] getTo_unit_code() {
        return to_unit_code;
    }

    public void setTo_unit_code(String[] to_unit_code) {
        this.to_unit_code = to_unit_code;
    }

    public List<ProfileAttachment> getProfileAttachments() {
        return profileAttachments;
    }

    public void setProfileAttachments(List<ProfileAttachment> profileAttachments) {
        this.profileAttachments = profileAttachments;
    }

    public List<ProfileDocFee> getProfileDocFees() {
        return profileDocFees;
    }

    public void setProfileDocFees(List<ProfileDocFee> profileDocFees) {
        this.profileDocFees = profileDocFees;
    }

    public List<ProfileDocPaper> getProfileDocPaper() {
        return profileDocPaper;
    }

    public void setProfileDocPaper(List<ProfileDocPaper> profileDocPaper) {
        this.profileDocPaper = profileDocPaper;
    }

    public ProfileOwner getProfileOwner() {
        return profileOwner;
    }

    public void setProfileOwner(ProfileOwner profileOwner) {
        this.profileOwner = profileOwner;
    }

    public ProfileApplicant getProfileApplicant() {
        return profileApplicant;
    }

    public void setProfileApplicant(ProfileApplicant profileApplicant) {
        this.profileApplicant = profileApplicant;
    }

    public String getData_eform() {
        return data_eform;
    }

    public void setData_eform(String data_eform) {
        this.data_eform = data_eform;
    }

    public String getStatus_profile() {
        return status_profile;
    }

    public void setStatus_profile(String status_profile) {
        this.status_profile = status_profile;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getGovAgencyCode() {
        return govAgencyCode;
    }

    public void setGovAgencyCode(String govAgencyCode) {
        this.govAgencyCode = govAgencyCode;
    }

    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }

    public ProfilePaymentFDS getPayment() {
        return payment;
    }

    public void setPayment(ProfilePaymentFDS payment) {
        this.payment = payment;
    }
}
