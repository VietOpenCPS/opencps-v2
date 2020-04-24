package org.opencps.event.message;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
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
import org.opencps.dossiermgt.constants.VnpostCollectionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class VnpostCollectionEvent implements MessageListener {
	
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
		Dossier dossier = null;

		long dossierId = dossierObj.getLong(DossierTerm.DOSSIER_ID);
		String refId = dossierObj.getString(DossierTerm.REFERENCE_UID);
		if (dossierId != 0) {
			dossier = DossierLocalServiceUtil.getDossier(dossierId);
		}
		else {
			dossier = DossierLocalServiceUtil.getByRef(groupId, refId);
		}
		if (dossier.getVnpostalStatus() != 1) {
			return;
		}

		JSONObject vnpostProfile = JSONFactoryUtil.createJSONObject(dossierObj.getString(DossierTerm.VNPOSTAL_PROFILE));
		InvokeREST callRest = new InvokeREST();
		String baseUrl = RESTFulConfiguration.SERVER_PATH_BASE;
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();
		params.put(VnpostCollectionTerm.ORDER_NUMBER, dossierObj.getString(DossierTerm.DOSSIER_NO) + StringPool.DOUBLE_UNDERLINE + Math.random());
		params.put(VnpostCollectionTerm.GOV_AGENCY_CODE, dossierObj.getString(DossierTerm.GOV_AGENCY_CODE));
		params.put(VnpostCollectionTerm.GOV_AGENCY_NAME, dossierObj.getString(DossierTerm.GOV_AGENCY_NAME));
		params.put(VnpostCollectionTerm.COD_AMOUNT, 0);
		params.put(VnpostCollectionTerm.SENDER_PROVINCE, vnpostProfile.getString(VnpostCollectionTerm.PROFILE_CITY_CODE));
		params.put(VnpostCollectionTerm.SENDER_DISTRICT, vnpostProfile.getString(VnpostCollectionTerm.PROFILE_DISTRICT_CODE));
		params.put(VnpostCollectionTerm.SENDER_ADDRESS, vnpostProfile.getString(VnpostCollectionTerm.PROFILE_ADDRESS));
		params.put(VnpostCollectionTerm.SENDER_NAME, dossierObj.getString(DossierTerm.DELEGATE_NAME));
		params.put(VnpostCollectionTerm.SENDER_MAIL, dossierObj.getString(DossierTerm.DELEGATE_EMAIL));
		params.put(VnpostCollectionTerm.SENDER_TEL, vnpostProfile.getString(VnpostCollectionTerm.PROFILE_TEL_NO));
		params.put(VnpostCollectionTerm.SENDER_DESC, dossierObj.getString(DossierTerm.APPLICANT_NAME));
		params.put(VnpostCollectionTerm.DESCRIPTION, vnpostProfile.getString(VnpostCollectionTerm.PROFILE_SERVICE_NAME));
		
		ServiceContext context = null;
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, baseUrl,
				VnpostCollectionTerm.VNPOST_BASE_PATH, "", "", properties, params, context);
		_log.debug("baseUrl: " + baseUrl + "      " + VnpostCollectionTerm.VNPOST_BASE_PATH);
		_log.debug("Call post API SEND VNPOST result: " + resultObj.toJSONString());
		_log.debug(params);
		if(resultObj != null) {
			dossier.setVnpostalStatus(VnpostCollectionTerm.VNPOSTAL_STAUS_2);

			DossierLocalServiceUtil.updateDossier(dossier);
		}
	}

	private Log _log = LogFactoryUtil.getLog(VnpostCollectionEvent.class);
}