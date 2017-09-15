package backend.api.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.opencps.api.context.provider.CompanyContextProvider;
import org.opencps.api.context.provider.LocaleContextProvider;
import org.opencps.api.context.provider.ServiceContextProvider;
import org.opencps.api.context.provider.UserContextProvider;
import org.opencps.api.controller.ServiceInfoManagement;
import org.opencps.api.controller.impl.ApplicantManagementImpl;
import org.opencps.api.controller.impl.DataManagementImpl;
import org.opencps.api.controller.impl.HolidayManagementImpl;
import org.opencps.api.controller.impl.ServiceInfoManagementImpl;
import org.opencps.api.controller.impl.WorkTimeManagementImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@ApplicationPath("/v2")
@Component(immediate = true, service = Application.class)
public class BackendAPIRestApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		
		ServiceInfoManagement serviceInfo = new ServiceInfoManagementImpl();
	
		// add REST endpoints (resources)
		singletons.add(new DataManagementImpl());
		singletons.add(serviceInfo);
		singletons.add(new ApplicantManagementImpl());
		singletons.add(new HolidayManagementImpl());
		singletons.add(new WorkTimeManagementImpl());

		// add service provider
		singletons.add(_serviceContextProvider);
		singletons.add(_companyContextProvider);
		singletons.add(_localeContextProvider);
		singletons.add(_userContextProvider);
		
		return singletons;	
	}

	@GET
	@Path("chao")
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}
	
	@Reference
	private CompanyContextProvider _companyContextProvider;

	@Reference
	private LocaleContextProvider _localeContextProvider;

	@Reference
	private UserContextProvider _userContextProvider;

	@Reference
	private ServiceContextProvider _serviceContextProvider;

}