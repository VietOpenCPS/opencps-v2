package org.opencps.sms.service.dto;

import java.util.Date;

public class SmsSearchData {
    private String src;

    public long getSmsId() {

        return smsId;
    }

    public void setSmsId(long smsId) {

        this.smsId = smsId;
    }

    public String getSmsReq() {

        return smsReq;
    }

    public void setSmsReq(String smsReq) {

        this.smsReq = smsReq;
    }

    public String getSmsReply() {

        return smsReply;
    }

    public void setSmsReply(String smsReply) {

        this.smsReply = smsReply;
    }

    public String getDossierNo() {

        return dossierNo;
    }

    public void setDossierNo(String dossierNo) {

        this.dossierNo = dossierNo;
    }

    public String getApplicationName() {

        return applicationName;
    }

    public void setApplicationName(String applicationName) {

        this.applicationName = applicationName;
    }

    public Date getReqDate() {

        return reqDate;
    }

    public void setReqDate(Date reqDate) {

        this.reqDate = reqDate;
    }

    public Date getReplyDate() {

        return replyDate;
    }

    public void setReplyDate(Date replyDate) {

        this.replyDate = replyDate;
    }

    private long smsId;
    private String smsReq;
    private String smsReply;
    private String dossierNo;
    private String applicationName;
    private Date reqDate;
    private Date replyDate;

    public String getSrc() {
    
        return src;
    }

    
    public void setSrc(String src) {
    
        this.src = src;
    }
}
