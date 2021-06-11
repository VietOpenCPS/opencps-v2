package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.dossiermgt.input.model.SyncTrackingInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface ApiThirdPartyService {
    public String getTokenLGSP(JSONObject paygovConfig) throws Exception;
    public String getUrlRedirectToPaygov(String token, Map<String, Object> body, JSONObject paygovConfig) throws Exception;
    public String getUrlRedirectToPaygovBackup(String token, Map<String, Object> body, JSONObject paygovConfig) throws Exception;
    public boolean checkSum(String token, Map<String, Object> body);
    public void callApiSaveTracking(SyncTrackingInfo body) throws Exception;
    public JSONObject callApi(String url, HttpHeaders headers, Map<String, Object> body);
    public JSONObject callApiAndTrackingWithMapBody(String url, SyncTrackingInfo syncTrackingInfo,
                                                    HttpHeaders headers, Map<String, Object> body);

    public JSONObject callAPIPaygovPrintInvoice(Map<String, Object> body, JSONObject paygovConfig) throws Exception;

    public JSONObject callApiAndTracking(String url, SyncTrackingInfo syncTrackingInfo,
                                         HttpHeaders headers, Object body);
    public JSONObject callApiEncode(String url, HttpHeaders headers, MultiValueMap<String, String> body) throws Exception;
    public JSONArray callApiWithResponseArray(String url, HttpHeaders headers, Map<String, Object> body);
    public JSONObject get(String url, HttpHeaders headers, SyncTrackingInfo syncTrackingInfo);
    public JSONObject getNew(String url, String token);
}
