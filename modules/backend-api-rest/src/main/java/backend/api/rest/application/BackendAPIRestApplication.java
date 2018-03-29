
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
import org.opencps.api.controller.impl.ApplicantManagementImpl;
import org.opencps.api.controller.impl.CommentManagementImpl;
import org.opencps.api.controller.impl.DataManagementImpl;
import org.opencps.api.controller.impl.DeliverableTypesManagementImpl;
import org.opencps.api.controller.impl.DeliverablesLogManagementImpl;
import org.opencps.api.controller.impl.DeliverablesManagementImpl;
import org.opencps.api.controller.impl.DossierActionManagementImpl;
import org.opencps.api.controller.impl.DossierFileManagementImpl;
import org.opencps.api.controller.impl.DossierLogManagementImpl;
import org.opencps.api.controller.impl.DossierManagementImpl;
import org.opencps.api.controller.impl.DossierSyncManagementImpl;
import org.opencps.api.controller.impl.DossierTemplateManagementImpl;
import org.opencps.api.controller.impl.EmployeeManagementImpl;
import org.opencps.api.controller.impl.FileAttachManagementImpl;
import org.opencps.api.controller.impl.HolidayManagementImpl;
import org.opencps.api.controller.impl.JobposManagementImpl;
import org.opencps.api.controller.impl.NotificationQueueManagementImpl;
import org.opencps.api.controller.impl.NotificationTemplateImpl;
import org.opencps.api.controller.impl.NotificationTypeManagementImpl;
import org.opencps.api.controller.impl.OfficeSiteManagementImpl;
import org.opencps.api.controller.impl.PaymentConfigManagementImpl;
import org.opencps.api.controller.impl.PaymentFileManagementImpl;
import org.opencps.api.controller.impl.ProcessPluginManagementImpl;
import org.opencps.api.controller.impl.RegistrationFormManagementImpl;
import org.opencps.api.controller.impl.RegistrationLogManagementImpl;
import org.opencps.api.controller.impl.RegistrationManagementImpl;
import org.opencps.api.controller.impl.RegistrationTemplatesManagementImpl;
import org.opencps.api.controller.impl.ServerConfigManagementImpl;
import org.opencps.api.controller.impl.ServiceConfigManagementImpl;
import org.opencps.api.controller.impl.ServiceInfoManagementImpl;
import org.opencps.api.controller.impl.ServiceProcessManagementImpl;
import org.opencps.api.controller.impl.SignatureManagementImpl;
import org.opencps.api.controller.impl.StatisticManagementImpl;
import org.opencps.api.controller.impl.UserInfoLogManagementImpl;
import org.opencps.api.controller.impl.UserManagementImpl;
import org.opencps.api.controller.impl.WorkTimeManagementImpl;
import org.opencps.api.controller.impl.WorkingUnitManagementImpl;
import org.opencps.dossiermgt.model.impl.DossierStatisticImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@ApplicationPath("/v2")
@Component(immediate = true, service = Application.class)
public class BackendAPIRestApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		
		
		// add REST endpoints (resources)
		singletons.add(new ApplicantManagementImpl());
		singletons.add(new ServiceInfoManagementImpl());
		singletons.add( new ServiceConfigManagementImpl());
		singletons.add( new DossierTemplateManagementImpl());
		singletons.add(new ServiceProcessManagementImpl());
		singletons.add(new PaymentConfigManagementImpl());
		singletons.add(new PaymentFileManagementImpl());
		singletons.add(new DossierManagementImpl());
		singletons.add(new DossierFileManagementImpl());
		singletons.add(new DossierActionManagementImpl());
		singletons.add(new DossierLogManagementImpl());
		singletons.add(new ServerConfigManagementImpl());
		singletons.add(new DossierSyncManagementImpl());

		singletons.add(new DataManagementImpl());
		singletons.add(new HolidayManagementImpl());
		singletons.add(new WorkTimeManagementImpl());
		singletons.add(new NotificationTemplateImpl());
		singletons.add(new NotificationTypeManagementImpl());
		singletons.add(new NotificationQueueManagementImpl());
		singletons.add(new OfficeSiteManagementImpl());
		singletons.add(new WorkingUnitManagementImpl());
		singletons.add(new JobposManagementImpl());
		singletons.add(new UserManagementImpl());
		singletons.add(new EmployeeManagementImpl());
		singletons.add(new DossierStatisticImpl());
		singletons.add(new FileAttachManagementImpl());
		singletons.add(new StatisticManagementImpl());
		singletons.add(new DeliverableTypesManagementImpl());
		//
		singletons.add(new DeliverablesManagementImpl());
		singletons.add(new DeliverablesLogManagementImpl());
		//
		singletons.add(new RegistrationTemplatesManagementImpl());
		singletons.add(new CommentManagementImpl());
		singletons.add(new RegistrationManagementImpl());
		singletons.add(new RegistrationFormManagementImpl());
		singletons.add(new RegistrationLogManagementImpl());
		singletons.add(new ProcessPluginManagementImpl());
		singletons.add(new SignatureManagementImpl());
		singletons.add(new UserInfoLogManagementImpl());
		
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