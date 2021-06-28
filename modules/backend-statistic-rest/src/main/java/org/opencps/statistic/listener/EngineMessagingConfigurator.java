package org.opencps.statistic.listener;

import com.liferay.portal.kernel.concurrent.CallerRunsPolicy;
import com.liferay.portal.kernel.concurrent.RejectedExecutionHandler;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

@Component(immediate = true, service = EngineMessagingConfigurator.class)
public class EngineMessagingConfigurator {

	private Log _log = LogFactoryUtil.getLog(EngineMessagingConfigurator.class);

	@Activate
	protected void activate(BundleContext bundleContext) {

		_bundleContext = bundleContext;

		_log.info("===active Listener===");

		registerListener("invoke/dossiersyncstatisticsheduler/in/destination");
		registerListener("invoke/dossierstatisticenginescheduler/in/destination");

	}

	private void registerListener(String destinationName){

		try {

			/**/
			DestinationConfiguration destinationConfiguration = new DestinationConfiguration(
					DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
					destinationName);

			destinationConfiguration.setMaximumQueueSize(_MAXIMUM_QUEUE_SIZE);

			RejectedExecutionHandler rejectedExecutionHandler = new CallerRunsPolicy() {

				@Override
				public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

					if (_log.isWarnEnabled()) {
						_log.warn("The current thread will handle the request "
								+ "because the graph walker's task queue is at " + "its maximum capacity");
					}

					super.rejectedExecution(runnable, threadPoolExecutor);
				}

			};

			destinationConfiguration.setRejectedExecutionHandler(rejectedExecutionHandler);

			// Create the destination

			Destination destination = _destinationFactory.createDestination(destinationConfiguration);

			Dictionary<String, Object> properties = new HashMapDictionary<>();

			properties.put("destination.name", destination.getName());

			ServiceRegistration<Destination> serviceRegistration = _bundleContext.registerService(Destination.class,
					destination, properties);

			_serviceRegistrations.put(destination.getName(), serviceRegistration);
		}catch(Exception e){
			_log.error(e);
		}

	}

	@Deactivate
	protected void deactivate() {

		// Unregister and destroy destinations this component unregistered

		for (ServiceRegistration<Destination> serviceRegistration : _serviceRegistrations.values()) {

			Destination destination = _bundleContext.getService(serviceRegistration.getReference());

			serviceRegistration.unregister();

			destination.destroy();

		}

		_serviceRegistrations.clear();

	}

	private BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;
	private int _MAXIMUM_QUEUE_SIZE = 10;

	private final Map<String, ServiceRegistration<Destination>> _serviceRegistrations = new HashMap<>();

}
