package org.opencps.event.message;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
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
		JSONObject msgData = (JSONObject) message.get("msgToEngine");
		JSONObject dossierObj = msgData.getJSONObject("dossier");
		long groupId = dossierObj.getLong(DossierTerm.GROUP_ID);
		String serverNo = dossierObj.getString(DossierTerm.SERVER_NO);
		ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
		if (serverConfig != null) {
			try {
				OpenCPSRestClient client = OpenCPSRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(serverConfig.getConfigs()));
				client.publishDossier(OpenCPSConverter.convertDossierSummary(dossierObj));
			} catch (JSONException e) {
			}
			
		}
	}

	private Log _log = LogFactoryUtil.getLog(PublishEvent.class);
}
