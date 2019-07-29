package backend.admin.config.whiteboard;

import java.util.LinkedHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * @author binhth
 */
public class AdminBundlesInstalled {

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
		
		if (AdminBundlesInstalled.getBundles().containsKey(symbolicName)) {
			Bundle bundle = AdminBundlesInstalled.getBundles().get(symbolicName);
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

	public static void setBundleContext(BundleContext bundleContext) {

		AdminBundlesInstalled._bundleContext = bundleContext;
	}

	public static void setBundles(LinkedHashMap<String, Bundle> bundles) {

		AdminBundlesInstalled._bundles = bundles;
	}

	public AdminBundlesInstalled(BundleContext bundleContext, Bundle[] bundles) {
		LinkedHashMap<String, Long> mapIds = new LinkedHashMap<>();
		LinkedHashMap<String, Bundle> mapBundles = new LinkedHashMap<>();
		if (bundles != null && bundles.length > 0) {
			for (Bundle bundle : bundles) {
				mapIds.put(bundle.getSymbolicName(), bundle.getBundleId());
				mapBundles.put(bundle.getSymbolicName(), bundle);
			}
		}
		AdminBundlesInstalled.setBundleContext(bundleContext);
		AdminBundlesInstalled.setBundleIds(mapIds);
		AdminBundlesInstalled.setBundles(mapBundles);	
	}

	public static void setBundleIds(LinkedHashMap<String, Long> bundleIdentify) {

		AdminBundlesInstalled._bundleIds = bundleIdentify;
	}
}
