package com.dnebinger.rest.internal.context.provider.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;

/**
 * @author binhth
 */
@Component(immediate = true, service = ContextProvider.class)
@Provider
public class ServiceContextContentProvider implements ContextProvider<ServiceContext> {
	/**
	 * Creates the context instance
	 *
	 * @param message the current message
	 * @return the context
	 */
	@Override
	public ServiceContext createContext(Message message) {
		ServiceContext serviceContext = null;
		System.out.println("ServiceContextContentProvider.createContext()");
		// get the current HttpServletRequest for building the service context instance.
		HttpServletRequest request = (HttpServletRequest) message.getContextualProperty(PROPKEY_HTTP_REQUEST);

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

	private static final String PROPKEY_HTTP_REQUEST = "HTTP.REQUEST";

	private static final Log _log = LogFactoryUtil.getLog(ServiceContextContentProvider.class);
}