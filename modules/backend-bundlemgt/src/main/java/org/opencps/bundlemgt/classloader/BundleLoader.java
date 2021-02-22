
package org.opencps.bundlemgt.classloader;

import org.opencps.bundlemgt.OpenCPSBundlesInstalled;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

/**
 * @author trungnt
 */
public class BundleLoader {

	public BundleLoader(String bundleSymbolicName) {
		Bundle bundle = OpenCPSBundlesInstalled.getBundles().get(bundleSymbolicName);
		if (bundle != null) {
			BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);
			this.setClassLoader(bundleWiring.getClassLoader());
		}

	}

	private ClassLoader _classLoader;

	public ClassLoader getClassLoader() {

		return _classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {

		this._classLoader = classLoader;
	}

}
