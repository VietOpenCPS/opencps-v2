/**
 * SMSAgentWSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.opencps.sms.service.client;

public interface SMSAgentWSSoap extends java.rmi.Remote {

    /**
     * Send MT message from CP to Agent
     */
    public int add(int a, int b) throws java.rmi.RemoteException;

    /**
     * Send MT message from CP to Agent
     */
    public int sendMT(java.lang.String mtseq, java.lang.String moid, java.lang.String moseq, java.lang.String src, java.lang.String dest, java.lang.String cmdcode, java.lang.String msgbody, java.lang.String msgtype, java.lang.String msgtitle, java.lang.String mttotalseg, java.lang.String mtseqref, java.lang.String cpid, java.lang.String serviceid, java.lang.String reqtime, java.lang.String procresult, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Send multiple MT message from CP to Agent
     */
    public int sendMultiMT(org.opencps.sms.service.client.MessageEntity[] messages, java.lang.String msgtype, java.lang.String msgtitle, java.lang.String cpid, java.lang.String serviceid, java.lang.String reqtime, java.lang.String procresult, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
}
