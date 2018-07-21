package org.opencps.statistic.rest.application;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.opencps.statistic.rest.facade.OpencpCallServiceFacade;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author khoavu
 */
@ApplicationPath("/statistics")
@Component(immediate = true, service = Application.class)
public class OpencpsStatisticRestApplication extends Application {
	


	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
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
		try {
			return _opencpCallServiceFacade.callWebPage("hello");

		} catch (Exception e) {
			return "error";
		}
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
	
	@Reference
	private OpencpCallServiceFacade<String, String> _opencpCallServiceFacade;

}