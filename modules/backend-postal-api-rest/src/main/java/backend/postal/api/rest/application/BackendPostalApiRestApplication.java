package backend.postal.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import backend.postal.api.rest.controller.impl.EInvoiceManagementImpl;
import backend.postal.api.rest.controller.impl.EvaluationManagementImpl;
import backend.postal.api.rest.controller.impl.SInvoiceManagementImpl;
import backend.postal.api.rest.controller.impl.VNPostManagementImpl;
import backend.postal.api.rest.controller.impl.VotingManagementImpl;

/**
 * @author admin
 */
@Component( 
property = { 
    JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/rest/v2/postal", 
    JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.restv2postal"
}, 
service = Application.class)
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
		singletons.add(new VotingManagementImpl());
		singletons.add(new SInvoiceManagementImpl());
		
		singletons.add(this);
		
		return singletons;
		
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

}