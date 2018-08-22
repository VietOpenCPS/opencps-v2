package restprovide.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author binhth
 */
@ApplicationPath("/greetings")
@Component(immediate = true, service = Application.class)
public class RestprovideApplication extends Application {

	@Reference
	private UserContextProvider _userContextProvider;
	@Reference
	private CompanyContextProvider _companyContextProvider;
	@Reference
	private LocaleContextProvider _localeContextProvider;
	@Reference
	private ServiceContextProvider _serviceContextProvider;
	
	@Override
    public Set<Object> getSingletons() {

        Set<Object> singletons = new HashSet<>();

        singletons.add(_userContextProvider);
        singletons.add(_companyContextProvider);
        singletons.add(_localeContextProvider);
        singletons.add(_serviceContextProvider);

        singletons.add(this);
        
        return singletons;

    }

	@GET
	@Produces("text/plain")
	public String working(@Context ServiceContext serviceContext) {
		System.out.println("RestprovideApplication.working()" + serviceContext.getUserId());
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