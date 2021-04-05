package org.opencps.backend.systemlogmgt.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class SystemLogServiceTestUtil {
	
	public static String test() {
		try {
			SystemLogServiceTest serviceTest = getService();
			return serviceTest.test();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public static SystemLogServiceTest getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SystemLogServiceTest, SystemLogServiceTest> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SystemLogServiceTest.class);
	
		System.out.println(bundle.getBundleId() + "  >>>>>>>>>>>>>>>>>>>");

		ServiceTracker<SystemLogServiceTest, SystemLogServiceTest> tracker = new ServiceTracker<SystemLogServiceTest, SystemLogServiceTest>(bundle.getBundleContext(), SystemLogServiceTest.class, null);
		if(tracker != null) {
			System.out.println("===========>>>>>>>>>>>> tracker " + tracker.getTrackingCount() + "|" + tracker.size());
		}
		
	

		tracker.open();
		
		System.out.println("===========>>>>>>>>>>>> tracker open ");
		
		if(tracker.getService() != null) {
			System.out.println(" tracker.getService() " + tracker.getService().getClass().getName());
		}else {
			System.out.println(" nullllllllllllllllllllllllllllllllllllllllll");
		}

		_serviceTracker = tracker;
	}
}
