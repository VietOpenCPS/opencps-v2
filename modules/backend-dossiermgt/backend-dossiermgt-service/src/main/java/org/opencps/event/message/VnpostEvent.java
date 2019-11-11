package org.opencps.event.message;

import com.liferay.petra.string.StringPool;
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

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class VnpostEvent implements MessageListener {
	
	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			_doReceiveRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}
	
	private void _doReceiveRequest(Message message) {		

		JSONObject dossierObj = (JSONObject) message.get("dossier");
		long groupId = dossierObj.getLong(Field.GROUP_ID);
		InvokeREST callRest = new InvokeREST();
		String baseUrl = RESTFulConfiguration.SERVER_PATH_BASE;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();
		_log.info("SONDT VNPOST EVENT dossierObj ========= "+ dossierObj);
		params.put("orderNumber", dossierObj.getString(DossierTerm.DOSSIER_NO)); 	    	
		params.put("senderName", dossierObj.getString(DossierTerm.GOV_AGENCY_NAME)); 
		params.put("receiverName", dossierObj.getString(DossierTerm.DELEGATE_NAME)); 
		params.put("receiverAddress", dossierObj.get(DossierTerm.POSTAL_ADDRESS)); 
		params.put("receiverTel", dossierObj.getString(DossierTerm.POSTAL_TEL_NO));
		params.put("receiverProvince", dossierObj.getString(DossierTerm.POSTAL_CITY_CODE));
		params.put("receiverDistrict", dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE));
		params.put("receiverEmail", dossierObj.getString(DossierTerm.DELEGATE_EMAIL));
		params.put("senderDesc", dossierObj.getString(DossierTerm.DELEGATE_NAME));
		
		ServiceContext context = null;
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, ReadFilePropertiesUtils.get(ConstantUtils.CONTENT_TYPE_JSON), baseUrl,
				ReadFilePropertiesUtils.get(ConstantUtils.VNPOST_BASE_PATH), StringPool.BLANK, StringPool.BLANK, properties, params, context);
		
		_log.info("Call post API SEND VNPOST result: " + resultObj.toJSONString());
		
		if(resultObj != null) {
			try {
				DossierLocalServiceUtil.updateViaPostal(groupId, dossierObj.getLong(DossierTerm.DOSSIER_ID),
						dossierObj.getString(DossierTerm.REFERENCE_UID), 3, context);
			} catch (PortalException e) {
				_log.debug(e);
			} // 3: sended to vnpost
		}
	}

	private Log _log = LogFactoryUtil.getLog(VnpostEvent.class);
}
