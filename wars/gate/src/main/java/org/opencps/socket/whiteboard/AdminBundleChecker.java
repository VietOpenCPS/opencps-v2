
package org.opencps.socket.whiteboard;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author trungnt
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
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" ___      ___	__________");
		stringBuffer.append(" ||\\    //||	||		||");
		stringBuffer.append(" || \\  // ||	||		||");
		stringBuffer.append(" ||  \\//  ||	||		||");
		stringBuffer.append(" ||        ||	||		||");
		
		System.out.println("START MOBILINK KERNEL ^_^");
	}
	
	private Log _log = LogFactoryUtil.getLog(getClass());
}
