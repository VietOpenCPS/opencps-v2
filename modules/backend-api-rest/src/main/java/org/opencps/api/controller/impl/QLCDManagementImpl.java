package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.api.controller.QLCDManagement;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.QLCDIntegrationAction;
import org.opencps.dossiermgt.action.impl.QLCDIntegrationActionImpl;
import org.opencps.dossiermgt.constants.QLCDConstants;

import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;

public class QLCDManagementImpl implements QLCDManagement {
    private static final Log _log = LogFactoryUtil.getLog(QLCDManagementImpl.class);
    private static final String API_SYNC_QLCD = "API_SYNC_QLCD";
    private static final String SERVER_HAUGIANG = "HAUGIANG";

    private List<ServerConfig> listConfig;

    public QLCDManagementImpl() {
        this.listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(API_SYNC_QLCD, API_SYNC_QLCD);
    }

    @Override
    public Response forwardApi(String body) {
        _log.info("forwarding api...");
        String type = "noType";
        try {
            if(Validator.isNull(body)) {
                throw new Exception("No body json was found");
            }

            if(Validator.isNull(this.listConfig) || this.listConfig.isEmpty()
                    || Validator.isNull(this.listConfig.get(0))) {
                throw new Exception("No config was found with protocol: " + API_SYNC_QLCD);
            }

            ServerConfig serverConfig = this.listConfig.get(0);
            if(Validator.isNull(serverConfig.getConfigs())
                    || serverConfig.getConfigs().isEmpty()) {
                throw new Exception("No config in server config");
            }

            JSONObject configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
            if(Validator.isNull(configJson)) {
                throw new Exception("Parse config json error");
            }

            String unit = configJson.getString(QLCDConstants.CONFIG_SERVER);
            if(Validator.isNull(unit) || unit.isEmpty()) {
                throw new Exception("No server config was found");
            }

            QLCDIntegrationAction qlcdAction = new QLCDIntegrationActionImpl(serverConfig);
            JSONObject bodyJson = JSONFactoryUtil.createJSONObject(body);
            if(!bodyJson.has("type") || Validator.isNull(bodyJson.getString("type"))) {
                throw new Exception("No type api was found");
            }

            type = bodyJson.getString("type");
            String responseCongDan = "";
            JSONObject typeServer = JSONFactoryUtil.createJSONObject();

            typeServer.put("partnerCode", QLCDConstants.UNIT_HG);

            String token = qlcdAction.getToken(unit);
            responseCongDan = qlcdAction.sendData(token, bodyJson, unit);

            return Response.status(HttpURLConnection.HTTP_OK).entity(responseCongDan).build();
        } catch (Exception e) {
            _log.error("Error when forward api " + type, e);
            JSONObject errorResponse = JSONFactoryUtil.createJSONObject();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("errorCode", "01");
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(errorResponse.toJSONString()).build();
        }
    }
}
