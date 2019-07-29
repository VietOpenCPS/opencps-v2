/**
 * CcApi_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.bulkSms.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class CcApi_ServiceLocator extends org.apache.axis.client.Service implements ws.bulkSms.impl.CcApi_Service {

	private static Log _log = LogFactoryUtil.getLog(CcApi_ServiceLocator.class);

    public CcApi_ServiceLocator() {
    }


    public CcApi_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CcApi_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CcApiPort
    private java.lang.String CcApiPort_address = "http://125.235.4.202:8998/bulkapi";

    public java.lang.String getCcApiPortAddress() {
        return CcApiPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CcApiPortWSDDServiceName = "CcApiPort";

    public java.lang.String getCcApiPortWSDDServiceName() {
        return CcApiPortWSDDServiceName;
    }

    public void setCcApiPortWSDDServiceName(java.lang.String name) {
        CcApiPortWSDDServiceName = name;
    }

    public ws.bulkSms.impl.CcApi_PortType getCcApiPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CcApiPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCcApiPort(endpoint);
    }

    public ws.bulkSms.impl.CcApi_PortType getCcApiPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.bulkSms.impl.CcApiPortBindingStub _stub = new ws.bulkSms.impl.CcApiPortBindingStub(portAddress, this);
            _stub.setPortName(getCcApiPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
        	_log.debug(e);
            return null;
        }
    }

    public void setCcApiPortEndpointAddress(java.lang.String address) {
        CcApiPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.bulkSms.impl.CcApi_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.bulkSms.impl.CcApiPortBindingStub _stub = new ws.bulkSms.impl.CcApiPortBindingStub(new java.net.URL(CcApiPort_address), this);
                _stub.setPortName(getCcApiPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CcApiPort".equals(inputPortName)) {
            return getCcApiPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "CcApi");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "CcApiPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CcApiPort".equals(portName)) {
            setCcApiPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
