package backend.api.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(immediate = true, service = CompanyContextProvider.class)
@Provider
public class CompanyContextProvider implements ContextProvider<Company> {

	@Override
	public Company createContext(Message message) {
		try {
			return _portal.getCompany((HttpServletRequest) message.getContextualProperty(ConstantUtils.HTTP_REQUEST));
		} catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get company", pe);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(CompanyContextProvider.class);

	@Reference
	private Portal _portal;

}
