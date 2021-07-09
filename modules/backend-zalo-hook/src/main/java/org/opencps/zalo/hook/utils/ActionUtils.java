package org.opencps.zalo.hook.utils;

import com.google.gson.JsonObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.zalo.hook.api.ZaloWebhookManagement;
import org.opencps.zalo.hook.client.ZaloOaClient;
import org.opencps.zalo.hook.exception.APIException;

import java.util.HashMap;
import java.util.Map;

public class ActionUtils {
    private static final Log _log = LogFactoryUtil.getLog(ZaloWebhookManagement.class);

    private static final String access_token = "dQ6MGZAQ8nkPevP5CfWe6PwcdaOsWsX-ozcB96EBEJgYwj9dMh00Mhk8m6LOu7HzazcT9cs4Q2E3wvu3Rz8f6O3foGL_gG4EjDZD20ArUZciw9LZOwvvD87hYn9KsK58Xf2ZGblfOKIfZgfxKTOVKx3bpdDxh2zDfz_S5mgc5JJEoyGg5xe51yFhrX0Mc1ahoT3r1p-H0oRktETw4AHNNCNUwNniiZfVfvJkRWMf8KBqyvHO5OvW0lopWraMoJ80svJu90BTDdpMx_j088zAHVUzgbW1WcDcph7SNviBXcurX2CK";

    public static String execFindDossier(String dossierNo, String password_) {

        Dossier dossier = null;
        try {
            dossier = DossierLocalServiceUtil.findDossierByDossierNO_Password(dossierNo, password_);
        } catch (Exception ex){
            _log.error(ex.getMessage());
            return "Có lỗi khi tìm kiếm hồ sơ! Vui lòng liên hệ với quản trị viên để nhận hỗ trợ";
        }
        if(Validator.isNotNull(dossier)){
            return generateMessage(dossier);
        } else {
            return "Không tìm thấy hồ sơ!";
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
                + "\n + Ngày tiếp nhận: " + dossier.getReceiveDate()
                + "\n + Ngày hẹn trả: " + dossier.getDueDate()
                + "\n + Ngày có kết quả: " + dossier.getReleaseDate()
                + "\n + Hình thức nộp: " + onlineText
                + "\n + Thư điện tử: " + dossier.getContactEmail();



        return context;
    }
    public static void execSendMessage(String idSender, String replyMessage) throws APIException {
        ZaloOaClient client = new ZaloOaClient();

        Map<String, Object> params = new HashMap<>();
        params.put("access_token", ActionUtils.access_token);

        JsonObject id = new JsonObject();
        id.addProperty("user_id", idSender);

        JsonObject text = new JsonObject();
        text.addProperty("text", replyMessage);

        JsonObject bodyRaw = new JsonObject();
        bodyRaw.add("recipient", id);
        bodyRaw.add("message", text);

        client.excuteRequest("https://openapi.zalo.me/v2.0/oa/message", "POST", params, bodyRaw);
    }


}
