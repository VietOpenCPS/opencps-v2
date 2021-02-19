
package org.opencps.bundlemgt;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


/**
 * @author trungnt
 */
@Component(immediate = true)
public class OpenCPSBundleChecker {

	@Activate
	public void start() {

		try {

			Bundle bundle = FrameworkUtil.getBundle(getClass());

			BundleContext ctx = bundle.getBundleContext();

			Bundle[] bundles = ctx.getBundles();

			new OpenCPSBundlesInstalled(ctx, bundles);

		}
		catch (Exception e) {
			_log.error(e);
		}

		String text = "OPENCPS Bundle";

		
		System.out.println(text);
		//_log.info(stringBuffer.toString());
	}

	private Log _log = LogFactoryUtil.getLog(getClass());
}
