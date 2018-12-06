package backend.admin.config.whiteboard;

import java.util.Hashtable;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author binhth
 */
@Component(immediate = true)
public class Configurator {
	@Activate
    public void activate(BundleContext bundleContext) {
        java.util.Dictionary<String, Object> servletContextProps =
            new Hashtable<>();

        servletContextProps.put("websocket.active", Boolean.TRUE);

        bundleContext.registerService(
            ServletContext.class, _servletContext, servletContextProps);
    }

    @Reference(target = "(original.bean=true)")
    private void setServletContext(ServletContext servletContext) {
        _servletContext = servletContext;
    }

    private ServletContext _servletContext;
}
