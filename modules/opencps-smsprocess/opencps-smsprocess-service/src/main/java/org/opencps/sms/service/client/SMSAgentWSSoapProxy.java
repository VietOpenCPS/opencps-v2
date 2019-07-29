package org.opencps.sms.service.client;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class SMSAgentWSSoapProxy implements org.opencps.sms.service.client.SMSAgentWSSoap {
  private String _endpoint = null;
  private org.opencps.sms.service.client.SMSAgentWSSoap sMSAgentWSSoap = null;
  private static Log _log = LogFactoryUtil.getLog(SMSAgentWSSoapProxy.class);
  
  public SMSAgentWSSoapProxy() {
    _initSMSAgentWSSoapProxy();
  }
  
  public SMSAgentWSSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSMSAgentWSSoapProxy();
  }
  
  private void _initSMSAgentWSSoapProxy() {
    try {
      sMSAgentWSSoap = (new org.opencps.sms.service.client.SMSAgentWSLocator()).getSMSAgentWSSoap();
      if (sMSAgentWSSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sMSAgentWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sMSAgentWSSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (sMSAgentWSSoap != null)
      ((javax.xml.rpc.Stub)sMSAgentWSSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.opencps.sms.service.client.SMSAgentWSSoap getSMSAgentWSSoap() {
    if (sMSAgentWSSoap == null)
      _initSMSAgentWSSoapProxy();
    return sMSAgentWSSoap;
  }
  
  public int add(int a, int b) throws java.rmi.RemoteException{
    if (sMSAgentWSSoap == null)
      _initSMSAgentWSSoapProxy();
    return sMSAgentWSSoap.add(a, b);
  }
  
  public int sendMT(java.lang.String mtseq, java.lang.String moid, java.lang.String moseq, java.lang.String src, java.lang.String dest, java.lang.String cmdcode, java.lang.String msgbody, java.lang.String msgtype, java.lang.String msgtitle, java.lang.String mttotalseg, java.lang.String mtseqref, java.lang.String cpid, java.lang.String serviceid, java.lang.String reqtime, java.lang.String procresult, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (sMSAgentWSSoap == null)
      _initSMSAgentWSSoapProxy();
    return sMSAgentWSSoap.sendMT(mtseq, moid, moseq, src, dest, cmdcode, msgbody, msgtype, msgtitle, mttotalseg, mtseqref, cpid, serviceid, reqtime, procresult, username, password);
  }
  
  public int sendMultiMT(org.opencps.sms.service.client.MessageEntity[] messages, java.lang.String msgtype, java.lang.String msgtitle, java.lang.String cpid, java.lang.String serviceid, java.lang.String reqtime, java.lang.String procresult, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (sMSAgentWSSoap == null)
      _initSMSAgentWSSoapProxy();
    return sMSAgentWSSoap.sendMultiMT(messages, msgtype, msgtitle, cpid, serviceid, reqtime, procresult, username, password);
  }
  
  
}