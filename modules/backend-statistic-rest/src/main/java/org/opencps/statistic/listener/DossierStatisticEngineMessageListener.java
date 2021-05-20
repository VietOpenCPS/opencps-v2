package org.opencps.statistic.listener;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;


import org.opencps.statistic.rest.util.DossierStatisticUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(immediate = true, property = {
		"destination.name=invoke/dossierstatisticenginescheduler/in/destination" }, service = MessageListener.class)
public class DossierStatisticEngineMessageListener implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		// TODO Auto-generated method stub
		try {
			_doReceive(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	private synchronized void _doReceive(Message message) {

		try {
			_log.info("++++_doReceive+++");


			DossierStatisticUtils dossierStatisticUtils = new DossierStatisticUtils();
			dossierStatisticUtils.invokeDossierStatistic();

		} catch (Exception e) {
			_log.error(e);
		} finally {
			_log.info("===finally===");
		}

	}

	private Log _log = LogFactoryUtil.getLog(DossierStatisticEngineMessageListener.class);

	@Reference
	private MessageBus _messageBus;
}
