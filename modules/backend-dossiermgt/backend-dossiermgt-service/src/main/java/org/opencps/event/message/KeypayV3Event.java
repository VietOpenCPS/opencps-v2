/**
 * 
 */
package org.opencps.event.message;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

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
		try {
			JSONObject dossierObj = (JSONObject) message.get(DossierTerm.CONSTANT_DOSSIER);
			long groupId = dossierObj.getLong(Field.GROUP_ID);
			long dossierId = dossierObj.getLong(DossierTerm.DOSSIER_ID);
			InvokeREST callRest = new InvokeREST();
			String baseUrl = RESTFulConfiguration.SERVER_PATH_BASE;
			HashMap<String, String> properties = new HashMap<String, String>();
			Map<String, Object> params = new HashMap<>();
			ServiceContext context = null;

			params.put(DossierTerm.DOSSIER_ID, dossierId);
//			_log.info("DossierId " + dossierId + " GroupId " + groupId);
			Thread.sleep(2000);
			Dossier dossier3 = DossierLocalServiceUtil.fetchDossier(dossierId);
			_log.info(" Log Dossier " + JSONFactoryUtil.looseSerialize(dossier3));
			JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, baseUrl,
					"o/pgi/keypayv3/create", "", "", properties, params, context);
			_log.info("baseUrl: " + baseUrl + "o/pgi/keypayv3/create");
			_log.info("Call post API SEND keypayv3 result: " + resultObj.toJSONString());
			_log.info(params);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private Log _log = LogFactoryUtil.getLog(KeypayV3Event.class);
}
