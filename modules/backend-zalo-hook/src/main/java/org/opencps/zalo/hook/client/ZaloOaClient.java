package org.opencps.zalo.hook.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.opencps.zalo.hook.config.APIConfig;
import org.opencps.zalo.hook.exception.APIException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ZaloOaClient extends ZaloBaseClient{

    public ZaloOaClient() {
    }

    public JsonObject excuteRequest(String endPoint, String method, Map<String, Object> params, JsonObject bodyData) throws APIException {
        File file = null;
        Map<String, String> sortedMap = new TreeMap<>();
        if (params == null) {
            params = new HashMap<>();
        }
        for (Map.Entry<String, Object> entrySet : params.entrySet()) {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();
            if (value instanceof File) {
                file = (File) value;
            } else {
                sortedMap.put(key, value.toString());
            }
        }

        String response;
        if (file != null) {
            response = sendHttpUploadRequest(endPoint, file, sortedMap, APIConfig.DEFAULT_HEADER);
        } else {
            if ("GET".equals(method.toUpperCase())) {
                response = sendHttpGetRequest(endPoint, sortedMap, APIConfig.DEFAULT_HEADER);
            } else {
                response = sendHttpPostRequest(endPoint, sortedMap, bodyData, APIConfig.DEFAULT_HEADER);
            }
        }
        JsonObject result = null;
        try {
            JsonParser parser = new JsonParser();
            result = parser.parse(response).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            throw new APIException("Response is not json: " + response);
        }
        return result;
    }
}
