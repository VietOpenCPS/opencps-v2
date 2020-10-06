package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.http.HttpConnection;
import org.opencps.dossiermgt.service.AccessTokenLocalServiceUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

public class ApiThirdPartyServiceImpl implements ApiThirdPartyService{
//    private RestTemplate restTemplate;
//    private static final Integer timeout = 30000 ;
    private Log _log = LogFactoryUtil.getLog(ApiThirdPartyServiceImpl.class);

    public ApiThirdPartyServiceImpl(){
//        this.restTemplate = new RestTemplate(setConfigRestTemplate(timeout));
    }

//    private ClientHttpRequestFactory setConfigRestTemplate(Integer timeout) {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
//                = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setConnectTimeout(timeout);
//        return clientHttpRequestFactory;
//    }

    @Override
    public JSONObject callApi(String url, HttpURLConnection conn, JSONObject body){
//        try {
            _log.info("Calling api viettle: " + url + " Connection : " + conn);
            try {
                byte[] postData = body.toJSONString().getBytes("UTF-8");
//                int postDataLength = postData.length;
//                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.write(postData);
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output = StringPool.BLANK;
                StringBuilder sb = new StringBuilder();
                while ((output = bufferedReader.readLine()) != null) {
                    sb.append(output);
                }
                System.out.println("response: " + sb.toString());

                JSONObject jsonObject = JSONFactoryUtil.createJSONObject(sb.toString());
                System.out.println(jsonObject);
                return jsonObject;
            }catch (Exception e){
               _log.info(e.getMessage());
               e.printStackTrace();
               return null;
            }
//            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
//            _log.info("Response api viettle: " + response);

//            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
//            System.out.println(jsonObject);
//        } catch (Exception e) {
//            _log.debug(e);
//            return null;
//        }
    }


    @Override
    public JSONArray callApiWithResponseArray(String url, HttpURLConnection conn, JSONObject body) {
        _log.info("Calling api viettle: " + url);
        try {
            byte[] postData = body.toJSONString().getBytes("UTF-8");
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }
//            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = StringPool.BLANK;
            StringBuilder sb = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                sb.append(output);
            }
            _log.info("Response api viettle: " + sb.toString());

            JSONArray jsonArray = JSONFactoryUtil.createJSONArray(sb.toString());
            System.out.println(jsonArray);
            return jsonArray;
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

}
