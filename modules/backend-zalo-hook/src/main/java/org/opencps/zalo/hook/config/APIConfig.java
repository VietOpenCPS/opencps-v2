package org.opencps.zalo.hook.config;

import org.opencps.zalo.hook.exception.APIException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class APIConfig {

    public static final String DEFAULT_OA_API_VERSION = "v2.0";
    public static final String DEFAULT_3RDAPP_API_VERSION = "v2.0";
    public static final String DEFAULT_OA_API_BASE = "https://openapi.zaloapp.com/";

    public static final String USER_AGENT = "zalosdk/2.0 Zalo Open API Java SDK";
    public static final String SDK_VERSION = "Java 2.0";
    public static final String SDK_SOURCE = "JavaSDK-2.0";
    public static final Map<String, String> DEFAULT_HEADER = createDefaultHeader();
    public static String TEMPORARY_DIR = null;

    public static Map<String, String> createDefaultHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("User-Agent", USER_AGENT);
        map.put("SDK_VERSION", SDK_VERSION);
        map.put("SDK-Source", SDK_SOURCE);
        return map;
    }

    public static void setTempDir(String dir) throws APIException {
        File file = new File(dir);
        if (file.isDirectory()) {
            try {
                TEMPORARY_DIR = file.getCanonicalPath();
                return;
            } catch (IOException ex) {
                throw new APIException(ex);
            }
        }
        throw new APIException(String.format("%s is not directory", dir));
    }
};
