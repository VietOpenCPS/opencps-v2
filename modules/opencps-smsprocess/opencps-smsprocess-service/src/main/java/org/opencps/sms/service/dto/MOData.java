package org.opencps.sms.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.opencps.sms.service.util.Constants;
import org.opencps.sms.service.util.DossierServiceProps;
import org.opencps.sms.service.util.ServiceProps;


public class MOData {
    
    
    public String getMtseq() {
    
        return mtseq;
    }

    
    public void setMtseq(String mtseq) {
    
        this.mtseq = mtseq;
    }

    
    public String getMoid() {
    
        return moid;
    }

    
    public void setMoid(String moid) {
    
        this.moid = moid;
    }

    
    public String getMoseq() {
    
        return moseq;
    }

    
    public void setMoseq(String moseq) {
    
        this.moseq = moseq;
    }

    
    public String getSrc() {
    
        return src;
    }

    
    public void setSrc(String src) {
    
        this.src = src;
    }

    
    public String getDest() {
    
        return dest;
    }

    
    public void setDest(String dest) {
    
        this.dest = dest;
    }

    
    public String getCmdcode() {
    
        return cmdcode;
    }

    
    public void setCmdcode(String cmdcode) {
    
        this.cmdcode = cmdcode;
    }

    
    public String getMsgbody() {
    
        return msgbody;
    }

    
    public void setMsgbody(String msgbody) {
    
        this.msgbody = msgbody;
    }

    
    public String getMsgtype() {
    
        return msgtype;
    }

    
    public void setMsgtype(String msgtype) {
    
        this.msgtype = msgtype;
    }

    
    public String getMsgtitle() {
    
        return msgtitle;
    }

    
    public void setMsgtitle(String msgtitle) {
    
        this.msgtitle = msgtitle;
    }

    
    public String getMttotalseg() {
    
        return mttotalseg;
    }

    
    public void setMttotalseg(String mttotalseg) {
    
        this.mttotalseg = mttotalseg;
    }

    
    public String getMtsegref() {
    
        return mtsegref;
    }

    
    public void setMtsegref(String mtsegref) {
    
        this.mtsegref = mtsegref;
    }

    
    public String getCpid() {
    
        return cpid;
    }

    
    public void setCpid(String cpid) {
    
        this.cpid = cpid;
    }

    
    public String getServiceid() {
    
        return serviceid;
    }

    
    public void setServiceid(String serviceid) {
    
        this.serviceid = serviceid;
    }

    
    public String getReqtime() {
    
        return reqtime;
    }

    
    public void setReqtime(String reqtime) {
    
        this.reqtime = reqtime;
    }

    
    public String getProcresult() {
    
        return procresult;
    }

    
    public void setProcresult(String procresult) {
    
        this.procresult = procresult;
    }

    
    public String getUserName() {
    
        return userName;
    }

    
    public void setUserName(String userName) {
    
        this.userName = userName;
    }

    
    public String getPassword() {
    
        return password;
    }

    
    public void setPassword(String password) {
    
        this.password = password;
    }

    private String mtseq;
    private String moid;
    private String moseq;
    private String src;
    private String dest;
    private String cmdcode;// GAP
    private String msgbody;
    private String msgtype;
    private String msgtitle;
    private String mttotalseg;// 1
    private String mtsegref;// 1
    private String cpid;
    private String serviceid;
    private String reqtime;
    private String procresult;
    private String userName;
    private String password;
    
    public MOData(String dest, String msgbody) {
        this.mtseq = "111";
        this.moid = "123";
        this.moseq = "12345";
        this.src = DossierServiceProps.get(Constants.OPENCPS_BACKEND_MO_NUMBER);
        this.dest = dest;
        this.cmdcode = "GAP";
        this.msgbody = msgbody;
        this.msgtype = "text";
        this.msgtitle = "welcome";
        this.mttotalseg = "1";
        this.mtsegref = "1";
        this.cpid = "10682";
        this.serviceid = "Service01";
        this.reqtime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.procresult = "0";
        this.userName = DossierServiceProps.get(Constants.OPENCPS_BACKEND_SMSGATEWAY_USER);
        this.password = DossierServiceProps.get(Constants.OPENCPS_BACKEND_SMSGATEWAY_PASSWORD);
        System.out.println("MO **" + this.src + "," + this.userName + "," + this.password);
    }


    
}
