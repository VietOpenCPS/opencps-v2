package ws.bulkSms.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class CcApiProxy implements ws.bulkSms.impl.CcApi_PortType {
  private String _endpoint = null;
  private ws.bulkSms.impl.CcApi_PortType ccApi_PortType = null;
  private static Log _log = LogFactoryUtil.getLog(CcApiProxy.class);
  
  public CcApiProxy() {
    _initCcApiProxy();
  }
  
  public CcApiProxy(String endpoint) {
    _endpoint = endpoint;
    _initCcApiProxy();
  }
  
  private void _initCcApiProxy() {
    try {
      ccApi_PortType = (new ws.bulkSms.impl.CcApi_ServiceLocator()).getCcApiPort();
      if (ccApi_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ccApi_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ccApi_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {
    	_log.debug(serviceException);
    }
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ccApi_PortType != null)
      ((javax.xml.rpc.Stub)ccApi_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ws.bulkSms.impl.CcApi_PortType getCcApi_PortType() {
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType;
  }
  
  public java.lang.String getIp() throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.getIp();
  }
  
  public ws.bulkSms.impl.ResultCp wsGetCpCode(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String taxCode) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsGetCpCode(user, password, CPCode, taxCode);
  }
  
  public com.viettel.smsbrand.CpBalance checkBalance(java.lang.String user, java.lang.String password, java.lang.String CPCode) throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.checkBalance(user, password, CPCode);
  }
  
  public ws.bulkSms.impl.Result wsCpMt(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String userID, java.lang.String receiverID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String content, java.lang.String contentType) throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsCpMt(user, password, CPCode, requestID, userID, receiverID, serviceID, commandCode, content, contentType);
  }
  
  public ws.bulkSms.impl.Result wsCpMt2(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String userID, java.lang.String receiverID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String content, java.lang.String contentType, java.lang.String senderCpCode) throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsCpMt2(user, password, CPCode, requestID, userID, receiverID, serviceID, commandCode, content, contentType, senderCpCode);
  }
  
  public ws.bulkSms.impl.ResultMultiReceiver wsCpMt4(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String contentType, ws.bulkSms.impl.MsgRequest[] msg) throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsCpMt4(user, password, CPCode, requestID, serviceID, commandCode, contentType, msg);
  }
  
  public ws.bulkSms.impl.ReportHourBO[] wsReportHour(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String timeReport) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsReportHour(user, password, CPCode, timeReport);
  }
  
  public ws.bulkSms.impl.ReportDailyBO[] wsReportDaily(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String startDate, java.lang.String endDate) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsReportDaily(user, password, CPCode, startDate, endDate);
  }
  
  public ws.bulkSms.impl.ReportMonthBO[] wsReportMonth(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String startMonth, java.lang.String endMonth) throws java.rmi.RemoteException, ws.bulkSms.impl.Exception{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsReportMonth(user, password, CPCode, startMonth, endMonth);
  }
  
  public ws.bulkSms.impl.FailSubReponse getFailSub(java.lang.String username, java.lang.String password, java.lang.String cpCode, java.lang.String alias, java.lang.String date, java.lang.String pageNumber, java.lang.String pageSize) throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.getFailSub(username, password, cpCode, alias, date, pageNumber, pageSize);
  }
  
  public ws.bulkSms.impl.CreateMtResult wsCpBatchMt(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String commandCode, ws.bulkSms.impl.RequestMt[] requestMt) throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsCpBatchMt(user, password, CPCode, commandCode, requestMt);
  }
  
  public ws.bulkSms.impl.Result wsEcomCpMt(java.lang.String user, java.lang.String password, java.lang.String CPCode, java.lang.String requestID, java.lang.String userID, java.lang.String receiverID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String content, java.lang.String contentType, java.lang.String saleOrderId, java.lang.String packageId, java.lang.String orderNum) throws java.rmi.RemoteException{
    if (ccApi_PortType == null)
      _initCcApiProxy();
    return ccApi_PortType.wsEcomCpMt(user, password, CPCode, requestID, userID, receiverID, serviceID, commandCode, content, contentType, saleOrderId, packageId, orderNum);
  }
  
  
}