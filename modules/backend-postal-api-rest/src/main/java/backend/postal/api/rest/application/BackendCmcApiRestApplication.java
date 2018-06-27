package backend.postal.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.opencps.api.context.provider1.CompanyContextProvider;
import org.opencps.api.context.provider1.LocaleContextProvider;
import org.opencps.api.context.provider1.ServiceContextProvider;
import org.opencps.api.context.provider1.UserContextProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import backend.postal.api.rest.controller.impl.EInvoiceManagementImpl;
import backend.postal.api.rest.controller.impl.EvaluationManagementImpl;
import backend.postal.api.rest.controller.impl.VotingManagementImpl;

/**
 * @author admin
 */
@ApplicationPath("/v2/pk5")
@Component(immediate = true, service = Application.class)
public class BackendCmcApiRestApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		// add REST endpoints (resources)
		singletons.add(this);
		singletons.add(new EvaluationManagementImpl());
		singletons.add(new EInvoiceManagementImpl());
		singletons.add(new VotingManagementImpl());
		
		// add service provider
		singletons.add(_serviceContextProvider1);
		singletons.add(_companyContextProvider1);
		singletons.add(_localeContextProvider1);
		singletons.add(_userContextProvider1);
		
		return singletons;
	}
	
	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}
	@Reference
	private CompanyContextProvider _companyContextProvider1;

	@Reference
	private LocaleContextProvider _localeContextProvider1;

	@Reference
	private UserContextProvider _userContextProvider1;

	@Reference
	private ServiceContextProvider _serviceContextProvider1;
	
}

