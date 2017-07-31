package backend.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.mobilink.api.controller.impl.DataManagementImpl;
import org.osgi.service.component.annotations.Component;

@ApplicationPath("/v2")
@Component(immediate = true, service = Application.class)
public class BackendAPIRestApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		
		// add REST endpoints (resources)
		singletons.add(new DataManagementImpl());
		
		return singletons;	
	}

	@GET
	@Path("chao")
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}
}