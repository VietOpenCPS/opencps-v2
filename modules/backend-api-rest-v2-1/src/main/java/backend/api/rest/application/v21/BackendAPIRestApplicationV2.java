package backend.api.rest.application.v21;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.opencps.api.context.provider.v2.CompanyContextProvider;
import org.opencps.api.context.provider.v2.LocaleContextProvider;
import org.opencps.api.context.provider.v2.ServiceContextProvider;
import org.opencps.api.context.provider.v2.UserContextProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.model.User;

import backend.api.rest.application.v21.impl.ActionConfigApiImpl;
import backend.api.rest.application.v21.impl.MenuConfigApiImpl;
import backend.api.rest.application.v21.impl.StepConfigApiImpl;

/**
 * @author binhth
 */
@ApplicationPath("/v2_1")
@Component(immediate = true, service = Application.class)
public class BackendAPIRestApplicationV2 extends Application {

	@Reference
	private CompanyContextProvider _companyContextProvider;

	@Reference
	private LocaleContextProvider _localeContextProvider;

	@Reference
	private UserContextProvider _userContextProvider;

	@Reference
	private ServiceContextProvider _serviceContextProvider;
	
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		
		// add service provider
		singletons.add(_serviceContextProvider);
		singletons.add(_companyContextProvider);
		singletons.add(_localeContextProvider);
		singletons.add(_userContextProvider);
		
		singletons.add(new ActionConfigApiImpl());
		singletons.add(new MenuConfigApiImpl());
		singletons.add(new StepConfigApiImpl());
		
		return singletons;	
	}

	@GET
	@Produces("text/plain")
	public String working(@Context User user) {
		System.out.println(user);
		System.out.println();
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
	
	@DELETE
	@Path("/morning/delete")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String morningRemove() {

		String greeting = "Good Morning remove ";

		return greeting;
	}
	
	@GET
	@Path("/morningsssss/{id}")
	@Produces("text/plain")
	public String hellos(@PathParam("id") String id) {
		return "Good morning get!";
	}
	
	@PUT
	@Path("/morningsssss/{id}")
	@Produces("text/plain")
	public String puthellos(@PathParam("id") String id) {
		return "Good morning put!";
	}
}