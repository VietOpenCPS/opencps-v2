package org.opencps.event.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.service.ServiceContext;

public class VnpostEvent implements MessageListener {
	
	private static final String VNPOST_BASE_PATH = "/postal/vnpost";
	
	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			_doReceiveRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}
	
	private void _doReceiveRequest(Message message) {		
		_log.info("GO GO VNPOST event");
		JSONObject dossierObj = (JSONObject) message.get("dossier");;
		long groupId = dossierObj.getLong(DossierTerm.GROUP_ID);
		InvokeREST callRest = new InvokeREST();
		String baseUrl = "/o/rest/v2";
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();
		
		params.put("customerCode", "cthh");	
		params.put("orderNumber", dossierObj.getString(DossierTerm.DOSSIER_NO)); 	    	
		params.put("senderProvince", "10"); 
		params.put("senderAddress", "51 NGO QUYEN"); 
		params.put("senderName", dossierObj.getString(DossierTerm.GOV_AGENCY_NAME)); 
		params.put("senderTel", "cthh"); 
		params.put("receiverName", dossierObj.getString(DossierTerm.APPLICANT_NAME)); 
		params.put("receiverAddress", dossierObj.get(DossierTerm.ADDRESS)); 
		params.put("receiverTel", dossierObj.getString(DossierTerm.CONTACT_TEL_NO));
		params.put("receiverProvince", dossierObj.getString(DossierTerm.POSTAL_WARD_CODE)); 
		params.put("codAmount", ""); 
		params.put("senderDistrict", ""); 
		params.put("senderEmail", ""); 
		params.put("senderDesc", ""); 
		params.put("receiverDistrict", ""); 
		params.put("receiverEmail", ""); 
		
		ServiceContext context = null;
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json", baseUrl,
				VNPOST_BASE_PATH, "", "", properties, params, context);
		
		_log.info("Call post API SEND VNPOST result: " + resultObj.toJSONString());
		
		if(resultObj != null) {
			try {
				DossierLocalServiceUtil.updateViaPostal(groupId, dossierObj.getLong(DossierTerm.DOSSIER_ID),
						dossierObj.getString(DossierTerm.REFERENCE_UID), 3, context);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				_log.info(e);
			} // 3: sended to vnpost
		}
	}

	private Log _log = LogFactoryUtil.getLog(VnpostEvent.class);
}
