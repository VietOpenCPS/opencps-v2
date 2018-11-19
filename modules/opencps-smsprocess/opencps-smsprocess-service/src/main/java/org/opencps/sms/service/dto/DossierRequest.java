
package org.opencps.sms.service.dto;

public class DossierRequest {

    private long groupId;
    private int start;
    private int end;
    private String dossierNo;
    private String applicantIdNo;

    public int getStart() {

        return start;
    }

    public void setStart(int start) {

        this.start = start;
    }

    public int getEnd() {

        return end;
    }

    public void setEnd(int end) {

        this.end = end;
    }

    public String getDossierNo() {

        return dossierNo;
    }

    public void setDossierNo(String dossierNo) {

        this.dossierNo = dossierNo;
    }

    public String getApplicantIdNo() {

        return applicantIdNo;
    }

    public void setApplicantIdNo(String applicantIdNo) {

        this.applicantIdNo = applicantIdNo;
    }

    public long getGroupId() {

        return groupId;
    }

    public void setGroupId(long groupId) {

        this.groupId = groupId;
    }

}
