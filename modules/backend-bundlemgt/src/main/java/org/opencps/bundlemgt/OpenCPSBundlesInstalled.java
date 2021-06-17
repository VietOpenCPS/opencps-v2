
package org.opencps.bundlemgt;

import java.util.LinkedHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * @author trungnt
 */
public class OpenCPSBundlesInstalled {

	private static LinkedHashMap<String, Long> _bundleIds;

	private static LinkedHashMap<String, Bundle> _bundles;

	private static BundleContext _bundleContext;

	public static BundleContext getBundleContext() {

		return _bundleContext;
	}

	public static LinkedHashMap<String, Long> getBundleIds() {

		return _bundleIds;
	}

	public static LinkedHashMap<String, Bundle> getBundles() {

		return _bundles;
	}

	public static String getBundleState(String symbolicName) {

		String stateName;
		
		if (OpenCPSBundlesInstalled.getBundles().containsKey(symbolicName)) {
			Bundle bundle = OpenCPSBundlesInstalled.getBundles().get(symbolicName);
			int state = bundle.getState();
			switch (state) {
			case 0x00000001:
				stateName = "UNINSTALLED";
				break;

			case 0x00000002:
				stateName = "INSTALLED";
				break;

			case 0x00000004:
				stateName = "RESOLVED";
				break;

			case 0x00000008:
				stateName = "STARTING";
				break;

			case 0x00000010:
				stateName = "STOPPING";
				break;

			case 0x00000020:
				stateName = "ACTIVE";
				break;

			default:
				stateName = "UNINSTALLED";
				break;
			}
		}
		else {
			stateName = "UNINSTALLED";
		}
		
		return stateName;
	}

	public void setBundleContext(BundleContext bundleContext) {

		OpenCPSBundlesInstalled._bundleContext = bundleContext;
	}

	public void setBundles(LinkedHashMap<String, Bundle> bundles) {

		OpenCPSBundlesInstalled._bundles = bundles;
	}

	public OpenCPSBundlesInstalled(BundleContext bundleContext, Bundle[] bundles) {
		LinkedHashMap<String, Long> mapIds = new LinkedHashMap<>();
		LinkedHashMap<String, Bundle> mapBundles = new LinkedHashMap<>();
		if (bundles != null && bundles.length > 0) {
			for (Bundle bundle : bundles) {
				mapIds.put(bundle.getSymbolicName(), bundle.getBundleId());
				mapBundles.put(bundle.getSymbolicName(), bundle);
			}
		}
		this.setBundleContext(bundleContext);
		this.setBundleIds(mapIds);
		this.setBundles(mapBundles);	
	}

	public void setBundleIds(LinkedHashMap<String, Long> bundleIdentify) {

		OpenCPSBundlesInstalled._bundleIds = bundleIdentify;
	}
}
