package org.opencps.event.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.opencps.backend.dossiermgt.logistic.ViettelPostManagement;
import org.opencps.backend.dossiermgt.logistic.ViettelPostManagementImpl;
import org.opencps.backend.dossiermgt.logistic.ViettelPostTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.*;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.Validator;

public class VnpostEvent implements MessageListener {

	private static final String VNPOST_BASE_PATH = "postal/vnpost";

	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			_log.info("-----RECEIVING MESSAGE LOGISTIC...");
			JSONObject dossierObj = (JSONObject) message.get("dossier");
			long groupId = dossierObj.getLong(Field.GROUP_ID);

			ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId, ViettelPostTerm.SERVER_NO,
					ViettelPostTerm.PROTOCOL);

			Integer typePost = getTypeLogistic(serverConfig);
			_log.info("-----Type post: " + typePost);
			if(typePost.equals(LogisticConstants.DEFAULT) || typePost.equals(LogisticConstants.VN_POST)) {
				vnPostHandle(message);
			}else if(typePost.equals(LogisticConstants.VIETTEL_POST)) {
				viettelPostHandle(dossierObj, serverConfig);
			} else {
				//todo for other logistic unit
			}
			_log.info("-----DONE CREATE BILL LOGISTIC...");

		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	private Integer getTypeLogistic(ServerConfig serverConfig) {
		try {
			if(Validator.isNull(serverConfig)) {
				throw new Exception("Config null");
			}

			if(Validator.isNull(serverConfig.getConfigs())
					|| serverConfig.getConfigs().isEmpty()) {
				throw new Exception("Config null");
			}

			JSONObject configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());;
			if(Validator.isNull(configJson)) {
				throw new Exception("Config null");
			}

			String typeLogisticString = configJson.getString(ViettelPostTerm.TYPE_POST_CONFIG);
			if(Validator.isNull(typeLogisticString) || typeLogisticString.isEmpty()) {
				throw new Exception("No type post in config");
			}

			return Integer.valueOf(typeLogisticString);
		} catch(Exception e) {
			_log.error(e.getMessage());
			return LogisticConstants.DEFAULT;
		}
	}

	private void viettelPostHandle(JSONObject dossierObj, ServerConfig serverConfig) {
		try{
			_log.info("-----Creating bill viettel post for dossier: " + dossierObj.getString(DossierTerm.DOSSIER_ID));
			ViettelPostManagement viettelPostManagement = new ViettelPostManagementImpl(serverConfig);
			String token = viettelPostManagement.getToken();
			String orderService = viettelPostManagement.getOrderService(token, dossierObj);
			viettelPostManagement.postBill(token, orderService, dossierObj);
			_log.info("-----Done create bill viettel");
		} catch (Exception e) {
			_log.error("-----ERROR Create bill viettel post" + e.getMessage());
		}
	}

	private void vnPostHandle(Message message) {

		try {
			JSONObject dossierObj = (JSONObject) message.get("dossier");
			long groupId = dossierObj.getLong(Field.GROUP_ID);
			Dossier dossier = null;

			long dossierId = dossierObj.getLong(DossierTerm.DOSSIER_ID);
			String refId = dossierObj.getString(DossierTerm.REFERENCE_UID);
			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.getDossier(dossierId);
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, refId);
			}
			if (dossier.getVnpostalStatus() != 1) {
				return;
			}

			InvokeREST callRest = new InvokeREST();
			String baseUrl = RESTFulConfiguration.SERVER_PATH_BASE;
			HashMap<String, String> properties = new HashMap<String, String>();
			Map<String, Object> params = new HashMap<>();
			String senderDesc = "Chuyển phát hồ sơ khách hàng: ";
			_log.info("SONDT VNPOST EVENT dossierObj ========= "+ dossierObj);
			params.put(VnpostCollectionTerm.GOV_AGENCY_CODE, dossierObj.getString(DossierTerm.GOV_AGENCY_CODE));
			params.put(VnpostCollectionTerm.GOV_AGENCY_NAME, dossierObj.getString(DossierTerm.GOV_AGENCY_NAME));
			params.put(KeyPayTerm.ORDERNUMBER, dossierObj.getString(DossierTerm.DOSSIER_NO));
			params.put(KeyPayTerm.ORDERNUMBER, dossierObj.getString(DossierTerm.DOSSIER_NO));
			params.put(KeyPayTerm.SENDERNAME, dossierObj.getString(DossierTerm.GOV_AGENCY_NAME));
			params.put(KeyPayTerm.RECEIVERNAME, dossierObj.getString(DossierTerm.DELEGATE_NAME));
			params.put(KeyPayTerm.RECEIVERADDRESS, dossierObj.get(DossierTerm.POSTAL_ADDRESS));
			params.put(KeyPayTerm.RECEIVERTEL, dossierObj.getString(DossierTerm.POSTAL_TEL_NO));
			params.put(KeyPayTerm.RECEIVERPROVINCE, dossierObj.getString(DossierTerm.POSTAL_CITY_CODE));
			params.put(KeyPayTerm.RECEIVERDISTRICT, dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE));
			params.put(KeyPayTerm.RECEIVEREMAIL, dossierObj.getString(DossierTerm.DELEGATE_EMAIL));
			params.put(VnpostCollectionTerm.SENDER_DESC, createVnpostSenderDesc(dossier, false));

			ServiceContext context = null;

			JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, baseUrl,
					KeyPayTerm.VNPOST_BASE_PATH, "", "", properties, params, context);
			_log.info("===========" + baseUrl + "      " + KeyPayTerm.VNPOST_BASE_PATH);
			_log.info("Call post API SEND VNPOST result: " + resultObj.toJSONString());

			if(resultObj != null) {
				DossierLocalServiceUtil.updateViaPostal(groupId, dossierObj.getLong(DossierTerm.DOSSIER_ID),
						dossierObj.getString(DossierTerm.REFERENCE_UID), 3, context);
			}
		} catch (Exception e) {
			// TODO: handle exception
			_log.info(e);
		}
	}

	// TODO: fix and waiting
	private String createVnpostSenderDesc (Dossier dossier, boolean isInput) {

		String dossierNo = Validator.isNotNull(dossier.getDossierNo()) ? "Mã hồ sơ: " + dossier.getDossierNo() + "||" : StringPool.BLANK;
		String serviceType = isInput ? "Thu gom hồ sơ tại nhà" : "Trả hồ sơ tại nhà";
		String senderName = dossierNo + "Tên thủ tục: " + dossier.getServiceName();
		try {
			List<DossierPart> dossierParts = DossierPartLocalServiceUtil.getByTemplateNo(dossier.getGroupId(),
					dossier.getDossierTemplateNo());
			for (DossierPart dp : dossierParts) {
				int i = 1;
				if ((!isInput && dp.getPartType() == 2) || (isInput && dp.getPartType() != 2)) {
					List<DossierFile> df = DossierFileLocalServiceUtil.getDossierFileByDID_DPNO(dossier.getDossierId(), dp.getPartNo(), false);
					if (df.size() > 0) {
						senderName += "||Thành phần hồ sơ " + i + ": " + dp.getPartName();
						i++;
					}
				}
			}
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		senderName  += "||Tên dịch vụ: " + serviceType + "||CMND: " + dossier.getApplicantIdNo();
		return senderName;
	}

	private Log _log = LogFactoryUtil.getLog(VnpostEvent.class);
}
