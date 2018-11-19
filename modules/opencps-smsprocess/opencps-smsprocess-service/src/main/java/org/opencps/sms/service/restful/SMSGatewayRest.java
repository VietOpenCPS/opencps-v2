
package org.opencps.sms.service.restful;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

@ApplicationPath("/smsgateway")
@Component(immediate = true, service = Application.class)
@Consumes("application/json")
@Produces("application/json")
public class SMSGatewayRest extends Application{

    public Set<Object> getSingletons() {

        return Collections.<Object> singleton(this);
    }

    @GET
    @Produces("text/plain")
    public String working() {

        return "It works!";
    }
}
