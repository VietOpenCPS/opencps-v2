/**
 * CcApi_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.bulkSms.impl;

public interface CcApi_PortType extends java.rmi.Remote {
    public java.lang.String getIp() throws java.rmi.RemoteException;
    public ws.bulkSms.impl.ResultCp wsGetCpCode(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String taxCode) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception;
    public com.viettel.smsbrand.CpBalance checkBalance(java.lang.String user, java.lang.String password, java.lang.String CPCode) throws java.rmi.RemoteException;
    public ws.bulkSms.impl.Result wsCpMt(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String userID, java.lang.String receiverID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String content, java.lang.String contentType) throws java.rmi.RemoteException;
    public ws.bulkSms.impl.Result wsCpMt2(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String userID, java.lang.String receiverID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String content, java.lang.String contentType, java.lang.String senderCpCode) throws java.rmi.RemoteException;
    public ws.bulkSms.impl.ResultMultiReceiver wsCpMt4(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String contentType, ws.bulkSms.impl.MsgRequest[] msg) throws java.rmi.RemoteException;
    public ws.bulkSms.impl.ReportHourBO[] wsReportHour(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String timeReport) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception;
    public ws.bulkSms.impl.ReportDailyBO[] wsReportDaily(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception;
    public ws.bulkSms.impl.ReportMonthBO[] wsReportMonth(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String startMonth, java.lang.String endMonth) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception;
    public ws.bulkSms.impl.FailSubReponse getFailSub(java.lang.String username, java.lang.String password, java.lang.String cpCode, java.lang.String alias, java.lang.String date, java.lang.String pageNumber, java.lang.String pageSize) throws java.rmi.RemoteException;
    public ws.bulkSms.impl.CreateMtResult wsCpBatchMt(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String commandCode, ws.bulkSms.impl.RequestMt[] requestMt) throws java.rmi.RemoteException;
    public ws.bulkSms.impl.Result wsEcomCpMt(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String userID, java.lang.String receiverID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String content, java.lang.String contentType, java.lang.String saleOrderId, java.lang.String packageId, java.lang.String orderNum) throws java.rmi.RemoteException;
}
