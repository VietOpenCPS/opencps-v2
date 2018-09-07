package backend.api.rest.application;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Portal;

@Component(immediate = true, service = UserContextProvider.class)
@Provider
public class UserContextProvider implements ContextProvider<User> {

    @Override
    public User createContext(Message message) {
        try {
            return _portal.getUser(
                (HttpServletRequest)message.getContextualProperty(
                    "HTTP.REQUEST"));
        }
        catch (PortalException pe) {
            if (_log.isWarnEnabled()) {
                _log.warn("Unable to get user", pe);
            }

            return null;
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
        UserContextProvider.class);

    @Reference
    private Portal _portal;

}
