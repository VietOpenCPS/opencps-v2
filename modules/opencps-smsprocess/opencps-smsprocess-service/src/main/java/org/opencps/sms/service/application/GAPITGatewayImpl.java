package org.opencps.sms.service.application;

import javax.jws.WebService;

import org.opencps.sms.service.dto.ReceiveMORequestType;
import org.opencps.sms.service.dto.ReceiveMOResponseType;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = "jaxws=true", service = GAPITGatewayImpl.class)
@WebService(endpointInterface = "org.opencps.sms.service.application.GAPITGateway")
public class GAPITGatewayImpl implements GAPITGateway{

    @Override
    public ReceiveMOResponseType getReceiveMO(ReceiveMORequestType receiveMORequestType) {
        ReceiveMOResponseType receiveMOResponseType = new ReceiveMOResponseType();
        receiveMOResponseType.setReceiveMOResult(1);

        return receiveMOResponseType;
    }

}
