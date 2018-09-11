package org.opencps.dossiermgt.action.keypay.util;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class KPRest {
    private WebTarget webTarget;
    private Client client;
    //local
    //private static final String BASE_URI = "http://localhost:8080/webservices/resources";
    //test
//    private static final String BASE_URI = "http://113.160.58.22:5555/webservices/resources";
    //staging
    private String BASE_URI = "http://webservices.keypay.vn/resources";
//    private static final String BASE_URI = "http://localhost/webservices/resources";
    //product
//    private static final String BASE_URI = "http://webservices.keypay.gov.vn/resources";

    public KPRest() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public KPRest(String baseUri) {
    	BASE_URI = baseUri;
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }
    
    public String sendOrder(String return_url,
            String version, String current_locale,
            String currency_code, String command,
            String merchant_trans_id, String country_code,
            String good_code, String xml_description,
            String net_cost, String ship_fee, String tax,
            String merchant_code,
            String service_code, String secure_hash,
            String desc_1, String desc_2, String desc_3,
            String desc_4, String desc_5, String internal_bank) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (return_url != null) {
            resource = resource.queryParam("return_url", return_url);
        }
        if (version != null) {
            resource = resource.queryParam("version", version);
        }
        if (current_locale != null) {
            resource = resource.queryParam("current_locale", current_locale);
        }
        if (currency_code != null) {
            resource = resource.queryParam("currency_code", currency_code);
        }
        if (command != null) {
            resource = resource.queryParam("command", command);
        }
        if (country_code != null) {
            resource = resource.queryParam("country_code", country_code);
        }
        if (xml_description != null) {
            resource = resource.queryParam("xml_description", xml_description);
        }
        if (net_cost != null) {
            resource = resource.queryParam("net_cost", net_cost);
        }
        if (ship_fee != null) {
            resource = resource.queryParam("ship_fee", ship_fee);
        }
        if (tax != null) {
            resource = resource.queryParam("tax", tax);
        }
        if (service_code != null) {
            resource = resource.queryParam("service_code", service_code);
        }
        if (desc_1 != null) {
            resource = resource.queryParam("desc_1", desc_1);
        }
        if (desc_2 != null) {
            resource = resource.queryParam("desc_2", desc_2);
        }
        if (desc_3 != null) {
            resource = resource.queryParam("desc_3", desc_3);
        }
        if (desc_4 != null) {
            resource = resource.queryParam("desc_4", desc_4);
        }
        if (desc_5 != null) {
            resource = resource.queryParam("desc_5", desc_5);
        }
        if (internal_bank != null) {
            resource = resource.queryParam("internal_bank", internal_bank);
        }

        if (merchant_code != null) {
            resource = resource.queryParam("merchant_code", merchant_code);
        }
        if (good_code != null) {
            resource = resource.queryParam("good_code", good_code);
        }
        if (merchant_trans_id != null) {
            resource = resource.queryParam("merchant_trans_id", merchant_trans_id);
        }
        if (secure_hash != null) {
            resource = resource.queryParam("secure_hash", secure_hash);
        }

        resource = resource.path("sendOrder");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String QuerryBillStatus(String merchant_trans_id, String good_code, String trans_id, String merchant_code, String secure_hash) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (merchant_code != null) {
            resource = resource.queryParam("merchant_code", merchant_code);
        }
        if (good_code != null) {
            resource = resource.queryParam("good_code", good_code);
        }
        if (merchant_trans_id != null) {
            resource = resource.queryParam("merchant_trans_id", merchant_trans_id);
        }
        if (secure_hash != null) {
            resource = resource.queryParam("secure_hash", secure_hash);
        }
        if (trans_id != null) {
            resource = resource.queryParam("trans_id", trans_id);
        }
        resource = resource.path("QuerryBillStatus");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
}