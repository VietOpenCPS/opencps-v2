
package backend.admin.config.whiteboard;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author binhth
 *
 */
@Component(immediate = true)
public class AdminBundleChecker {

	@Activate
	public void start() {

		try {
			
			Bundle bundle = FrameworkUtil.getBundle(getClass());

			BundleContext ctx =  bundle.getBundleContext();
			
			Bundle[] bundles = ctx.getBundles();
			
			new AdminBundlesInstalled(ctx, bundles);
			

		}
		catch (Exception e) {
			_log.error(e);
		}
		
		System.out.println("START OPENCPS KERNEL ^_^");
	}
	
	private Log _log = LogFactoryUtil.getLog(getClass());
}
