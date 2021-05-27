package org.opencps.statistic.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBusEventListener;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = MessageBusEventListener.class)
public class EngineMessageBusEventListener implements MessageBusEventListener {

	private Log _log = LogFactoryUtil.getLog(EngineMessageBusEventListener.class);

	@Override
	public void destinationAdded(Destination destination) {
		// TODO Auto-generated method stub
		_log.info("===destinationAdded==="+destination.getName());
	}


	@Override
	public void destinationRemoved(Destination destination) {
		// TODO Auto-generated method stub
		_log.info("===destinationRemoved==="+destination.getName());
	}
}
