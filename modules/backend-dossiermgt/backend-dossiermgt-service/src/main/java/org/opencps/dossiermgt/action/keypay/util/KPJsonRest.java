package org.opencps.dossiermgt.action.keypay.util;

import java.io.InputStream;
import java.net.URL;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.KeyPayTerm;

public class KPJsonRest {
	private WebTarget webTarget;
	private Client client;

	private String BASE_URI = "http://webservices.keypay.vn/jsonresources";

	public KPJsonRest() {
		client = javax.ws.rs.client.ClientBuilder.newClient();
		webTarget = client.target(ReadFilePropertiesUtils.get(ConstantUtils.BASE_JSON_URI)).path(KeyPayTerm.GENERIC);
	}

	public KPJsonRest(String baseUri) {
		BASE_URI = baseUri;
		client = javax.ws.rs.client.ClientBuilder.newClient();
		webTarget = client.target(ReadFilePropertiesUtils.get(ConstantUtils.BASE_JSON_URI)).path(KeyPayTerm.GENERIC);
	}

	public String sendOrder(String return_url, String version, String current_locale, String currency_code,
			String command, String merchant_trans_id, String country_code, String good_code, String xml_description,
			String net_cost, String ship_fee, String tax, String merchant_code, String service_code, String secure_hash,
			String desc_1, String desc_2, String desc_3, String desc_4, String desc_5, String internal_bank)
			throws ClientErrorException {
		WebTarget resource = webTarget;
		if (merchant_code != null) {
			resource = resource.queryParam(KeyPayTerm.MERCHANT_CODE, merchant_code);
		}
		if (good_code != null) {
			resource = resource.queryParam(KeyPayTerm.GOOD_CODE, good_code);
		}
		if (xml_description != null) {
			resource = resource.queryParam(KeyPayTerm.XML_DESCRIPTION, xml_description);
		}
		if (internal_bank != null) {
			resource = resource.queryParam(KeyPayTerm.INTERNAL_BANK, internal_bank);
		}
		if (merchant_trans_id != null) {
			resource = resource.queryParam(KeyPayTerm.MERCHANT_TRANS_ID, merchant_trans_id);
		}
		if (current_locale != null) {
			resource = resource.queryParam(KeyPayTerm.CURRENT_LOCALE, current_locale);
		}
		if (tax != null) {
			resource = resource.queryParam(KeyPayTerm.TAX, tax);
		}
		if (desc_1 != null) {
			resource = resource.queryParam(KeyPayTerm.DESC_1, desc_1);
		}
		if (desc_3 != null) {
			resource = resource.queryParam(KeyPayTerm.DESC_3, desc_3);
		}
		if (version != null) {
			resource = resource.queryParam(KeyPayTerm.VERSION, version);
		}
		if (desc_2 != null) {
			resource = resource.queryParam(KeyPayTerm.DESC_2, desc_2);
		}
		if (currency_code != null) {
			resource = resource.queryParam(KeyPayTerm.CURRENCY_CODE, currency_code);
		}
		if (command != null) {
			resource = resource.queryParam(KeyPayTerm.COMMAND, command);
		}
		if (desc_5 != null) {
			resource = resource.queryParam(KeyPayTerm.DESC_5, desc_5);
		}
		if (desc_4 != null) {
			resource = resource.queryParam(KeyPayTerm.DESC_4, desc_4);
		}
		if (ship_fee != null) {
			resource = resource.queryParam(KeyPayTerm.SHIP_FEE, ship_fee);
		}
		if (country_code != null) {
			resource = resource.queryParam(KeyPayTerm.COUNTRY_CODE, country_code);
		}
		if (secure_hash != null) {
			resource = resource.queryParam(KeyPayTerm.SECURE_HASH, secure_hash);
		}
		if (return_url != null) {
			resource = resource.queryParam(KeyPayTerm.RETURN_URL, return_url);
		}
		if (service_code != null) {
			resource = resource.queryParam(KeyPayTerm.SERVICE_CODE, service_code);
		}
		if (net_cost != null) {
			resource = resource.queryParam(KeyPayTerm.NET_COST, net_cost);
		}
		resource = resource.path(KeyPayTerm.SEND_ORDER);
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
	}

	public String QuerryBillStatus(String merchant_trans_id, String good_code, String trans_id, String merchant_code,
			String secure_hash) throws ClientErrorException {
		WebTarget resource = webTarget;
		if (merchant_code != null) {
			resource = resource.queryParam(KeyPayTerm.MERCHANT_CODE, merchant_code);
		}
		if (good_code != null) {
			resource = resource.queryParam(KeyPayTerm.GOOD_CODE, good_code);
		}
		if (merchant_trans_id != null) {
			resource = resource.queryParam(KeyPayTerm.MERCHANT_TRANS_ID, merchant_trans_id);
		}
		if (secure_hash != null) {
			resource = resource.queryParam(KeyPayTerm.SECURE_HASH, secure_hash);
		}
		if (trans_id != null) {
			resource = resource.queryParam(KeyPayTerm.TRANS_ID, trans_id);
		}
		resource = resource.path(KeyPayTerm.BILL_STATUS);
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
	}

	public void close() {
		client.close();
	}
}