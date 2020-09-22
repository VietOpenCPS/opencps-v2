/**
 * 
 */
package org.opencps.event.message;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;

/**
 * @author moon
 *
 */
public class KeypayV3Event implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			_doReceiveRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	private void _doReceiveRequest(Message message) throws PortalException {

		JSONObject dossierObj = (JSONObject) message.get(DossierTerm.CONSTANT_DOSSIER);
		long groupId = dossierObj.getLong(Field.GROUP_ID);
		long dossierId = dossierObj.getLong(DossierTerm.DOSSIER_ID);
		InvokeREST callRest = new InvokeREST();
		String baseUrl = RESTFulConfiguration.SERVER_PATH_BASE;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();
		ServiceContext context = null;

		params.put(DossierTerm.DOSSIER_ID, dossierId);

		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, baseUrl,
				"o/pgi/keypayv3/create", "", "", properties, params, context);
		_log.debug("baseUrl: " + baseUrl + "      " + "o/pgi/keypayv3/create");
		_log.debug("Call post API SEND keypayv3 result: " + resultObj.toJSONString());
		_log.debug(params);
	}

	private Log _log = LogFactoryUtil.getLog(KeypayV3Event.class);
}
