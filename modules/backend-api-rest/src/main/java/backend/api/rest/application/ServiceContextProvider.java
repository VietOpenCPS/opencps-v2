package backend.api.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.osgi.service.component.annotations.Component;


@Component(immediate = true, service = ServiceContextProvider.class)
@Provider
public class ServiceContextProvider implements ContextProvider<ServiceContext> {

	@Override
	public ServiceContext createContext(Message message) {
		ServiceContext serviceContext = null;

		// get the current HttpServletRequest for building the service context
		// instance.
		HttpServletRequest request = (HttpServletRequest) message.getContextualProperty(ConstantUtils.HTTP_REQUEST);

		try {
			// now we can create a service context
			serviceContext = ServiceContextFactory.getInstance(request);

			// done!
		} catch (PortalException e) {
			_log.warn("Failed creating service context: " + e.getMessage(), e);
		}

		// return the new instance.
		return serviceContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(ServiceContextProvider.class);

}
