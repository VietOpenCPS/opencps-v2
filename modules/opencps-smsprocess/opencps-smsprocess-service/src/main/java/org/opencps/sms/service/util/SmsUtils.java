
package org.opencps.sms.service.util;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.opencps.sms.service.client.SMSAgentWSLocator;
import org.opencps.sms.service.client.SMSAgentWSSoap;
import org.opencps.sms.service.dto.MOData;

public class SmsUtils {

	public static final String FORMAT_TIME = "yyyy-MM-dd hh:mm:ss";

    public static Date convertDate(String src) {

        try {
        	SimpleDateFormat _dateFormat = new SimpleDateFormat(FORMAT_TIME);
            return _dateFormat.parse(src);
        }
        catch (ParseException e) {
            return null;
        }
    }

    public static int sendMt(MOData moData)
        throws ServiceException, RemoteException {

        SMSAgentWSLocator locator = new SMSAgentWSLocator();

        SMSAgentWSSoap soap = locator.getSMSAgentWSSoap();

        return soap.sendMT(
            moData.getMtseq(), moData.getMoid(), moData.getMoseq(), moData.getSrc(), moData.getDest(),
            moData.getCmdcode(), moData.getMsgbody(), moData.getMsgtype(), moData.getMsgtitle(), moData.getMttotalseg(),
            moData.getMtsegref(), moData.getCpid(), moData.getServiceid(), moData.getReqtime(), moData.getProcresult(),
            moData.getUserName(), moData.getPassword());
    }
}
