package backend.postal.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

import backend.postal.api.rest.controller.impl.VNPostManagementImpl;

/**
 * @author admin
 */
@ApplicationPath("/v2/postal")
@Component(immediate = true, service = Application.class)
public class BackendPostalApiRestApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		// add REST endpoints (resources)
		singletons.add(new VNPostManagementImpl());
		return singletons;
	}
}