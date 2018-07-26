package sample.rest.maven.application;

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
import org.opencps.statistic.rest.facade.OpencpsCallServiceRestFacadeImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gm.gcc.cei.ole.support.dealership.dto.DealershipResponse;
/**
 * @author liferay
 */
@ApplicationPath("/samples")
@Component(immediate = true, service = Application.class)
public class SampleRestMavenApplication extends Application {
	private final static Logger LOG = LoggerFactory.getLogger(SampleRestMavenApplication.class);

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	

	@GET
	@Produces("text/plain")
	public String working() {
		LOG.info("ALO");
		OpencpCallServiceFacade<String, String> restFacede = new OpencpsCallServiceRestFacadeImpl();

		try {
			
			return restFacede.callWebPage("http://google.com");
		} catch (Exception e) {

		}
		return null;

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