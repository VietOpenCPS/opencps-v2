package org.opencps.sms.service.application;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.opencps.sms.service.dto.ReceiveMORequestType;
import org.opencps.sms.service.dto.ReceiveMOResponseType;
import org.osgi.service.component.annotations.Component;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface GAPITGateway {
    @WebMethod
    public ReceiveMOResponseType getReceiveMO(ReceiveMORequestType receiveMORequestType);
}
