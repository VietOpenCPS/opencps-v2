package org.opencps.event.message;

import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class PublishEvent implements MessageListener {
	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			_doReceiveRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}
	
	private void _doReceiveRequest(Message message) {		
		_log.info("Publish dossier event");
		JSONObject dossierObj = (JSONObject) message.get("dossier");;
		long groupId = dossierObj.getLong(DossierTerm.GROUP_ID);
		List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getByProtocol(groupId, ServerConfigTerm.PUBLISH_PROTOCOL);
		for (ServerConfig sc : lstServers) {
			try {
				OpenCPSRestClient client = OpenCPSRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
				client.publishDossier(OpenCPSConverter.convertDossierSummary(dossierObj));
			} catch (JSONException e) {
			}			
		}
	}

	private Log _log = LogFactoryUtil.getLog(PublishEvent.class);
}
