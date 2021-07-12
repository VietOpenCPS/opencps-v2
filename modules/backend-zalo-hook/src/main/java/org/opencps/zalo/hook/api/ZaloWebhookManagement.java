package org.opencps.zalo.hook.api;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import org.opencps.zalo.hook.constants.ZaloHookConstantKeys;
import org.opencps.zalo.hook.exception.APIException;
import org.opencps.zalo.hook.utils.ActionUtils;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;


@Component(immediate = true, property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/zaloSearch",
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.zaloSearch" }, service = Application.class)
public class ZaloWebhookManagement extends Application {

    @Override
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }

    private static final Log _log = LogFactoryUtil.getLog(ZaloWebhookManagement.class);


    @GET
    @Path("/action")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response test(@Context HttpServletRequest request, @Context HttpServletResponse response,
                         @Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
                         @Context ServiceContext serviceContext) {
        return Response.status(HttpURLConnection.HTTP_OK).entity("OK").build();
    }

    @POST
    @Path("/action")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response doAction(@Context HttpServletRequest request, @Context HttpServletResponse response,
                         @Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
                         @Context ServiceContext serviceContext, String body) throws JSONException, APIException {
        String messageReply = "";
        try {
            JSONObject bodyJson = JSONFactoryUtil.createJSONObject(body);
            JSONObject sender = bodyJson.getJSONObject(ZaloHookConstantKeys.ZALO_V2_PARAM_SENDER);
            String senderId = sender.getString(ZaloHookConstantKeys.ZALO_V2_PARAM_SENDER_ID);
            try {
                JSONObject message = bodyJson.getJSONObject(ZaloHookConstantKeys.ZALO_V2_ACTION_POST_MESSAGE);
                String textJson = message.getString(ZaloHookConstantKeys.ZALO_V2_PARAM_MESSAGE_TEXT);
                _log.info("text : " + textJson);
                if(textJson.startsWith(StringPool.POUND) && !textJson.startsWith(StringPool.POUND + ZaloHookConstantKeys.ZALO_MESSAGE_SYNTAX_SEARCH_DOSSIER + "Menu")){
                    textJson = textJson.replace(StringPool.POUND + ZaloHookConstantKeys.ZALO_MESSAGE_SYNTAX_SEARCH_DOSSIER, "");
                    textJson = textJson.trim();
                    String[] arrayText = textJson.split(StringPool.SPACE);
                    if(arrayText.length == 2){
                        String dossierNo = arrayText[0];
                        String password_ = arrayText[1];
                        messageReply = ActionUtils.execFindDossier(dossierNo, password_);
                    } else {
                        messageReply = "Cú pháp không hợp lệ! Vui lòng thử lại! 123";
                    }
                } else {
                    return Response.status(HttpURLConnection.HTTP_OK).entity("OK").build();
                }

            } catch (Exception ex){
                messageReply = "Cú pháp không hợp lệ! Vui lòng thử lại! 456";
            }

            ActionUtils.execSendMessage(senderId, messageReply);
        } catch (Exception e){

        }

        return Response.status(HttpURLConnection.HTTP_OK).entity("OK").build();
    }



}
