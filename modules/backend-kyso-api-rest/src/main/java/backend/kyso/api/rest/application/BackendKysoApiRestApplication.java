package backend.kyso.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.opencps.kyso.api.controller.impl.DigitalSignatureManagementImpl;
import org.osgi.service.component.annotations.Component;

/**
 * @author GIAHUY
 */
@ApplicationPath("/v2")
@Component(immediate = true, service = Application.class)
public class BackendKysoApiRestApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		
		// add REST endpoints (resources)
		singletons.add(new DigitalSignatureManagementImpl());
//		singletons.add(new ServiceInfoManagementImpl());
	
		return singletons;
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

	@GET
	@Path("/morning")
	@Produces("text/plain")
	public String hello() {
		return "Good morning!";
	}

	@GET
	@Path("/morning/{name}")
	@Produces("text/plain")
	public String morning(
		@PathParam("name") String name,
		@QueryParam("drink") String drink) {

		String greeting = "Good Morning " + name;

		if (drink != null) {
			greeting += ". Would you like some " + drink + "?";
		}

		return greeting;
	}

}