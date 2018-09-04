package backend.postal.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import backend.postal.api.rest.controller.impl.EInvoiceManagementImpl;
import backend.postal.api.rest.controller.impl.EvaluationManagementImpl;
import backend.postal.api.rest.controller.impl.VNPostManagementImpl;

/**
 * @author admin
 */
@Component(immediate = true, service = Application.class)
public class BackendPostalApiRestApplication extends Application {

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
		
		singletons.add(_serviceContextProvider);
		singletons.add(_companyContextProvider);
		singletons.add(_localeContextProvider);
		singletons.add(_userContextProvider);
		
		singletons.add(new EvaluationManagementImpl());
		singletons.add(new EInvoiceManagementImpl());
		singletons.add(new VNPostManagementImpl());
		
		singletons.add(this);
		
		return singletons;
		
	}
	
	@GET
	@Path("/aabcsbb")
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}
	
}