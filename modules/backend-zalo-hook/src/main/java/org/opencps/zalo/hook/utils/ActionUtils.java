package org.opencps.zalo.hook.utils;

import com.google.gson.JsonObject;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.zalo.hook.api.ZaloWebhookManagement;
import org.opencps.zalo.hook.client.ZaloOaClient;
import org.opencps.zalo.hook.exception.APIException;

import java.util.HashMap;
import java.util.Map;

public class ActionUtils {
    private static final String OAID_TOKEN_ACCESS = "oaid_token_access";
    private static final String API_SEND_MESSAGE = "api_send_message";

    private static final String DOSSIER_ERROR_PASSWORD_MESS = "dossierErrorPasswordMes";
    private static final String DOSSIER_NOT_FOUND_MES = "dossierNotFoundMes";
    private static final String DOSSIER_ERROR_FIND_MES = "dossierErrorFindMes";


    private static final Log _log = LogFactoryUtil.getLog(ZaloWebhookManagement.class);

    public static String execFindDossier(String dossierNo, String password_, ServerConfig serverConfig) throws JSONException {

        JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

        Dossier dossier = null;
        try {
            dossier = DossierLocalServiceUtil.fetchByDO_NO(dossierNo);
        } catch (Exception ex){
            _log.error(ex.getMessage());
            return config.getString(DOSSIER_ERROR_FIND_MES);
        }
        if(Validator.isNotNull(dossier) ){
            if(dossier.getPassword().equals(password_)){
                return generateMessage(dossier);
            } else {
                return config.getString(DOSSIER_ERROR_PASSWORD_MESS);
            }
        } else {
            return config.getString(DOSSIER_NOT_FOUND_MES);
        }



    }

    private static String generateMessage(Dossier dossier){
        String onlineText = "";
        if(dossier.getOnline()){
            onlineText = "Hồ sơ nộp trực tuyến";
        } else {
            onlineText = "Hồ sơ nộp trực tiếp";
        }
        String context = "Kết quả tra cứu: "
                + "\n + Mã hồ sơ: " + dossier.getDossierNo()
                + "\n + Tên hồ sơ: " + dossier.getDossierName()
                + "\n + Trạng thái: " + dossier.getDossierStatusText()
                + "\n + Đơn vị tiếp nhận: " + dossier.getGovAgencyName()
                + "\n + Ngày tiếp nhận: " + DatetimeUtil.convertDateToString(dossier.getReceiveDate())
                + "\n + Ngày hẹn trả: " + DatetimeUtil.convertDateToString(dossier.getDueDate())
                + "\n + Ngày có kết quả: " + DatetimeUtil.convertDateToString(dossier.getReleaseDate())
                + "\n + Hình thức nộp: " + onlineText
                + "\n + Thư điện tử: " + dossier.getContactEmail();

        return context;
    }
    public static void execSendMessage(String idSender, String replyMessage, ServerConfig serverConfig) throws APIException, JSONException {

        JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

        String access_token = config.getString(OAID_TOKEN_ACCESS);
        String api_send_message = config.getString(API_SEND_MESSAGE);

        ZaloOaClient client = new ZaloOaClient();

        if(Validator.isNotNull(access_token)){
            Map<String, Object> params = new HashMap<>();
            params.put("access_token", access_token);

            JsonObject id = new JsonObject();
            id.addProperty("user_id", idSender);

            JsonObject text = new JsonObject();
            text.addProperty("text", replyMessage);

            JsonObject bodyRaw = new JsonObject();
            bodyRaw.add("recipient", id);
            bodyRaw.add("message", text);

            client.excuteRequest(api_send_message, "POST", params, bodyRaw);
        } else {
            _log.error("Please config "+OAID_TOKEN_ACCESS+" in serverConfig where serverConfigId = " + serverConfig.getServerConfigId());
        }


    }


}
