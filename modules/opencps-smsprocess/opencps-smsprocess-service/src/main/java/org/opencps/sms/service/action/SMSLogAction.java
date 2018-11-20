
package org.opencps.sms.service.action;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import org.opencps.sms.model.SMSGatewayLog;
import org.opencps.sms.model.impl.SMSGatewayLogImpl;
import org.opencps.sms.service.SMSGatewayLogLocalServiceUtil;

public class SMSLogAction {

    public static SMSGatewayLog addSMSGatewayLog(String src, String smsReq, String dossierNo, Date reqDate) {

        SMSGatewayLog gatewayLog = new SMSGatewayLogImpl();

        long id = CounterLocalServiceUtil.increment(SMSGatewayLog.class.getName());

        gatewayLog.setSmsId(id);
        gatewayLog.setSrc(src);
        gatewayLog.setSmsReq(smsReq);
        gatewayLog.setDossierNo(dossierNo);
        gatewayLog.setReqDate(reqDate);

        return SMSGatewayLogLocalServiceUtil.addSMSGatewayLog(gatewayLog);

    }

    public static SMSGatewayLog updateSMSGatewayLogReply(
        long id, String smsReply, Date replyDate, String applicationName) {

        SMSGatewayLog gatewayLog = SMSGatewayLogLocalServiceUtil.fetchSMSGatewayLog(id);

        if (Validator.isNotNull(gatewayLog)) {
            gatewayLog.setSmsReply(smsReply);
            gatewayLog.setReplyDate(replyDate);
            gatewayLog.setApplicationName(applicationName);
            
            return SMSGatewayLogLocalServiceUtil.updateSMSGatewayLog(gatewayLog);
        }
        else {
            return null;
        }
    }
    
    public static SMSGatewayLog updateSMSGatewayLogFail(
        long id) {

        SMSGatewayLog gatewayLog = SMSGatewayLogLocalServiceUtil.fetchSMSGatewayLog(id);

        if (Validator.isNotNull(gatewayLog)) {
            gatewayLog.setStatus(0);
            
            return SMSGatewayLogLocalServiceUtil.updateSMSGatewayLog(gatewayLog);
        }
        else {
            return null;
        }
    }
    
    public static SMSGatewayLog updateSMSGatewayLogSuccess(
        long id) {

        SMSGatewayLog gatewayLog = SMSGatewayLogLocalServiceUtil.fetchSMSGatewayLog(id);

        if (Validator.isNotNull(gatewayLog)) {
            gatewayLog.setStatus(1);
            
            return SMSGatewayLogLocalServiceUtil.updateSMSGatewayLog(gatewayLog);
        }
        else {
            return null;
        }
    }
    public static SMSGatewayLog updateSMSGatewayLogResend(
        long id, Date lastReplyManualDate, long lastReplyManualUserId, String lastReplyManualUserName) {

        SMSGatewayLog gatewayLog = SMSGatewayLogLocalServiceUtil.fetchSMSGatewayLog(id);

        if (Validator.isNotNull(gatewayLog)) {
            gatewayLog.setLastReplyManualDate(lastReplyManualDate);
            gatewayLog.setLastReplyManualUserId(lastReplyManualUserId);
            gatewayLog.setLastReplyManualUserName(lastReplyManualUserName);
            
            return SMSGatewayLogLocalServiceUtil.updateSMSGatewayLog(gatewayLog);
        }
        else {
            return null;
        }
    }
}
