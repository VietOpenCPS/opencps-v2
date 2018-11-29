package org.opencps.event.message;

import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.rest.utils.LGSPRestClient;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class LGSPEvent implements MessageListener {
	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			_doReceiveRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}
	
	private void _doReceiveRequest(Message message) {		
		_log.info("LGSP dossier event");
		JSONObject dossierObj = (JSONObject) message.get("dossier");
		long groupId = dossierObj.getLong(DossierTerm.GROUP_ID);
		List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getByProtocol(groupId, ServerConfigTerm.LGSP_PROTOCOL);
		for (ServerConfig sc : lstServers) {
			try {
				LGSPRestClient client = LGSPRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
				Mtoken token = client.getToken();
				client.publishDossier(token.getAccessToken(), OpenCPSConverter.convertDossierPublish(dossierObj));
				client.postDocumentTrace(token.getAccessToken(), dossierObj.getLong(DossierTerm.DOSSIER_ID));
			} catch (JSONException e) {
				_log.error(e);
			}			
		}
	}

	private Log _log = LogFactoryUtil.getLog(LGSPEvent.class);
}
