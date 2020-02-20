
package org.opencps.sms.service.application;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.opencps.sms.model.SMSGatewayLog;
import org.opencps.sms.service.DossierLookUpFacade;
import org.opencps.sms.service.DossierLookUpFacadeImpl;
import org.opencps.sms.service.action.SMSLogAction;
import org.opencps.sms.service.client.SMSAgentWSLocator;
import org.opencps.sms.service.client.SMSAgentWSSoap;
import org.opencps.sms.service.dto.DossierData;
import org.opencps.sms.service.dto.DossierRequest;
import org.opencps.sms.service.dto.DossierResponse;
import org.opencps.sms.service.dto.MOData;
import org.opencps.sms.service.dto.ReceiveMORequestType;
import org.opencps.sms.service.dto.ReceiveMOResponseType;
import org.opencps.sms.service.util.Constants;
import org.opencps.sms.service.util.DossierStatus;
import org.opencps.sms.service.util.MessageUtil;
import org.opencps.sms.service.util.ServiceProps;
import org.osgi.service.component.annotations.Component;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import ws.bulkSms.impl.CcApi_PortType;
import ws.bulkSms.impl.CcApi_ServiceLocator;

@Component(immediate = true, property = "jaxws=true", service = SMSGateway.class)
@WebService(name = "MonoWebServicePortType", serviceName = "MonoWebService")
@SOAPBinding(style = Style.DOCUMENT)
public class SMSGateway {

    private static final Log _log = LogFactoryUtil.getLog(SMSGateway.class);

    private DossierLookUpFacade<DossierRequest, DossierResponse> dossierLookUpServie = new DossierLookUpFacadeImpl();

    @WebMethod(operationName = "ReceiveMO")
    @WebResult(name = "")
    public ReceiveMOResponseType ReceiveMO(@WebParam(name = "ReceiveMO") ReceiveMORequestType receiveMORequestType) {

        _log.info("CALLING TIME " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        
        //Add SMSGatewayLog
        
        Date reqDate = new Date();
        
        SMSGatewayLog smsGatewayLog = SMSLogAction.addSMSGatewayLog(
            receiveMORequestType.getSrc(), receiveMORequestType.getMsgbody(), getDossierNo(receiveMORequestType.getMsgbody()) , reqDate);

        ReceiveMOResponseType receiveMOResponseType = new ReceiveMOResponseType();

        try {
            
            String applicationName = StringPool.BLANK;
            
            String smsReply = buildContent(receiveMORequestType.getMsgbody(), applicationName);
            MOData moData = new MOData(processPerfixNumber(receiveMORequestType.getSrc()), smsReply);

            Date replyDate = new Date();

            SMSLogAction.updateSMSGatewayLogReply(smsGatewayLog.getSmsId(), smsReply, replyDate, applicationName);
            _log.info("statusCode**" + receiveMORequestType.getSrc() + " " + processPerfixNumber(receiveMORequestType.getSrc()) + " APN " + applicationName);

            _log.info("statusCode**" + moData.getUserName() + " " + moData.getPassword() + " APN " + applicationName);

            int statusCode = sendMt(moData);
            
            _log.info("statusCode**" + statusCode);
            System.out.println("statusCode**" + statusCode);
            //Send viettel
    		CcApi_ServiceLocator locator = new CcApi_ServiceLocator();
    		CcApi_PortType portType = locator.getCcApiPort();
    		
    		portType.wsCpMt(Constants.DEFAULT_USER, Constants.DEFAULT_SECRET, 
    				Constants.DEFAULT_CP_CODE, Constants.DEFAULT_REQUEST_ID, receiveMORequestType.getSrc(), receiveMORequestType.getSrc(), Constants.DEFAULT_SERVICE_ID, Constants.DEFAULT_COMMAND_CODE, smsReply, Constants.DEFAULT_CONTENT_TYPE);            
            
            SMSLogAction.updateSMSGatewayLogSuccess(smsGatewayLog.getSmsId());
            
            receiveMOResponseType.setReceiveMOResult(200);
       }
         catch (AxisFault e) {
        	_log.debug(e);
             SMSLogAction.updateSMSGatewayLogFail(smsGatewayLog.getSmsId());

        }
        catch (RemoteException e) {
            SMSLogAction.updateSMSGatewayLogFail(smsGatewayLog.getSmsId());
            _log.info(e);
        }
        catch (ServiceException e) {
            SMSLogAction.updateSMSGatewayLogFail(smsGatewayLog.getSmsId());
            _log.info(e);
        }
        catch (UpstreamServiceTimedOutException | UpstreamServiceFailedException e) {
        	_log.debug(e);
            SMSLogAction.updateSMSGatewayLogFail(smsGatewayLog.getSmsId());
        } 

        return receiveMOResponseType;
    }

    private int sendMt(MOData moData)
        throws ServiceException, RemoteException {

        SMSAgentWSLocator locator = new SMSAgentWSLocator();

        SMSAgentWSSoap soap = locator.getSMSAgentWSSoap();

        return soap.sendMT(
            moData.getMtseq(), moData.getMoid(), moData.getMoseq(), moData.getSrc(), moData.getDest(),
            moData.getCmdcode(), moData.getMsgbody(), moData.getMsgtype(), moData.getMsgtitle(), moData.getMttotalseg(),
            moData.getMtsegref(), moData.getCpid(), moData.getServiceid(), moData.getReqtime(), moData.getProcresult(),
            moData.getUserName(), moData.getPassword());
    }
    
    private String processPerfixNumber(String src) {
        
        if (src.contains(Constants.VN_PHONE_PLUS)) {
            src = src.replace(Constants.VN_PHONE_PLUS, String.valueOf(0));
        }

        if (Constants.VN_PHONE.equals(src.substring(0, 2))) {
           StringBuilder sb = new StringBuilder(src);
           sb.replace(0, 2, String.valueOf(0));
           
           src = sb.toString();
           
        }
       
       return src;
    }
    
    private String getDossierNo(String dossierNo) {
       
        if (Validator.isNotNull(dossierNo)) {
            String[] slip = StringUtil.split(dossierNo, StringPool.SPACE);
            
            _log.info("LENGHT "+slip.length);
            
            if (slip.length >= 2) {
                dossierNo = slip[1];
            }
            
            return dossierNo;
        }
        
        return "";
    }

    private String buildContent(String dossierNo, String applicationName)
        throws UpstreamServiceTimedOutException, UpstreamServiceFailedException{
        
        StringBuilder sb = new StringBuilder();
        
        String replyContent = StringPool.BLANK;

        if (Validator.isNotNull(dossierNo)) {
            String[] slip = StringUtil.split(dossierNo, StringPool.SPACE);
            
            _log.info("LENGHT "+slip.length);
            
            if (slip.length >= 2) {
                dossierNo = slip[1];
            }
            else {
//                sb.append("Xin chao! ");
//                sb.append("De nghi ca nhan/doanh nghiep vui long kiem tra lai chinh cu phap tin nhan. ");
//                sb.append("Tran trong cam on!");
            	sb.append(MessageUtil.getMessage(Constants.SMS_FORMAT_ERROR_MESSAGE));

                return sb.toString();
            }
        }
        else {
//            sb.append("Xin chao! ");
//            sb.append("De nghi ca nhan/doanh nghiep vui long kiem tra lai chinh cu phap tin nhan. ");
//            sb.append("Tran trong cam on!");
        	sb.append(MessageUtil.getMessage(Constants.SMS_FORMAT_ERROR_MESSAGE));
            
            
            return sb.toString();
        }

        DossierRequest dossierRequest = new DossierRequest();

        dossierRequest.setApplicantIdNo("");
        dossierRequest.setDossierNo(dossierNo);
        dossierRequest.setStart(GetterUtil.getInteger(ServiceProps.get(Constants.OPENCPS_BACKEND_SEARCH_START)));
        dossierRequest.setEnd(GetterUtil.getInteger(ServiceProps.get(Constants.OPENCPS_BACKEND_SEARCH_END)));
        dossierRequest.setGroupId(GetterUtil.getLong(ServiceProps.get(Constants.OPENCPS_GROUP_ID_CONFIG)));

        DossierResponse dossierResponse = dossierLookUpServie.callDossierRestService(dossierRequest);

        DossierData data = null;
        

        if (Validator.isNotNull(dossierResponse) && dossierResponse.getTotal() != 0) {
            data = dossierResponse.getData().get(0);
        } else {
//            sb.append("Xin chao! ");
//            sb.append("De nghi ca nhan/doanh nghiep vui long kiem tra lai ma so ho so. ");
//            sb.append("Tran trong cam on.");
        	sb.append(MessageUtil.getMessage(Constants.SMS_DOSSIER_ERROR_MESSAGE));

            return sb.toString();
        }

        if (Validator.isNotNull(data)) {
            
            //applicationName = data.getApplicantName();
            
            replyContent = buildSMSContent(data);
        }

        return replyContent;
    }

    private String buildSMSContent(DossierData data) {
        
        StringBuffer sb = new StringBuffer();
        
        String dossierStatus = data.getDossierStatus();
        
        switch (dossierStatus) {
        case DossierStatus.PROCESSING:
        	String processingText = String.format(MessageUtil.getMessage(Constants.SMS_DOSSIER_PROCESSING_MESSAGE), data.getApplicantName(), data.getDossierNo());        	
//            sb.append("Xin chao, " + data.getApplicantName() + "! ");
//            sb.append("Ho so "); 
//            sb.append(data.getDossierNo());
//            sb.append(" dang trong qua trinh tham dinh. ");
//            sb.append("Tran trong cam on!");
            sb.append(processingText);
           break;
        case DossierStatus.WAITING:
        	String waitingText = String.format(MessageUtil.getMessage(Constants.SMS_DOSSIER_WAITING_MESSAGE), data.getApplicantName(), data.getDossierNo());
        	
//            sb.append("Xin chao, " + data.getApplicantName() + "! ");
//            sb.append("Ho so " + data.getDossierNo()+ ". "); 
//            sb.append("De nghi Cong dan/ doanh nghiep som hoan thien bo sung ho so theo yeu cau. ");
//            sb.append("Tran trong cam on!");
        	sb.append(waitingText);
            break;
        case DossierStatus.DONE:
//            sb.append("Xin chao, ");
//            sb.append(data.getApplicantName() + "! ");
//            sb.append("De nghi den nhan ket qua tai " + data.getGovAgencyName() + ". ");
            if (isOntime(data)) {
            	String ontimeText = String.format(Constants.SMS_DOSSIER_DONE_ONTIME_MESSAGE, data.getApplicantName(), data.getGovAgencyName(), data.getDossierNo());
                
//            	sb.append("Ho so da giai quyet truoc han. ");
//                sb.append("Ma ho so la " + data.getDossierNo() + ". ");
            	sb.append(ontimeText);
            } else {
            	String overtimeText = String.format(Constants.SMS_DOSSIER_DONE_OVERTIME_MESSAGE, data.getApplicantName(), data.getGovAgencyName(), data.getDossierNo());
//                sb.append("Ma ho so la " + data.getDossierNo() + ". ");
//                sb.append("*Luu y: mang theo giay to can thiet. ");
            	sb.append(overtimeText);
            }
//            sb.append("Tran trong cam on!");
            
            break;

        case DossierStatus.DENIED:
        	String deniedText = String.format(MessageUtil.getMessage(Constants.SMS_DOSSIER_DENIED_MESSAGE), data.getApplicantName(), data.getDossierNo(), data.getApplicantName());
//            sb.append("Xin chao, " + data.getApplicantName() + "! ");
//            sb.append("Ho so " + data.getDossierNo() + ". "); 
//            sb.append("Ho so cua " + data.getApplicantName() + " da bi tu choi giai quyet. ");
//            sb.append("Tran trong cam on!");
        	sb.append(deniedText);
            break;

        default:
        	String defaultText = String.format(MessageUtil.getMessage(Constants.SMS_DOSSIER_DEFAULT_MESSAGE), data.getApplicantName(), data.getDossierNo());
//            sb.append("Xin chao, " + data.getApplicantName() + "! ");
//            sb.append("Ho so " + data.getDossierNo() + " "); 
//            sb.append("dang trong qua trinh giai quyet. ");
//            sb.append("Tran trong cam on!");
        	sb.append(defaultText);
            break;
        }
        
        
        return sb.toString();
    }
    
    private boolean isOntime(DossierData data) {

        boolean isOntime = true;

        data.getDueDate();

        if (Validator.isNotNull(data.getDueDate()) && Validator.isNotNull(data.getFinishDate())) {
            try {
                if (_dateFormat.parse(data.getDueDate()).after(_dateFormat.parse(data.getFinishDate()))) {
                    isOntime = false;
                }
            }
            catch (ParseException e) {
            }
        }

        return isOntime;
    }
    
    private SimpleDateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

}
