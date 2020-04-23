package opencps.esb.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.opencps.esb.api.controller.impl.ESBEgovManagementImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author GIAHUY
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/rest/esb",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.restesb"
	},
	service = Application.class
)
public class OpencpsEsbApiRestApplication extends Application {

	@Reference
	private UserContextProvider _userContextProvider;
	@Reference
	private CompanyContextProvider _companyContextProvider;
	@Reference
	private LocaleContextProvider _localeContextProvider;
	@Reference
	private ServiceContextProvider _serviceContextProvider;

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		
		
		singletons.add(new ESBEgovManagementImpl());
		singletons.add(this);
		
		singletons.add(_serviceContextProvider);
		singletons.add(_companyContextProvider);
		singletons.add(_localeContextProvider);
		singletons.add(_userContextProvider);
		
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