package org.opencps.thirdparty.system.jaxws;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class SOAPClient {
	public String callWebService(String soapAction, String soapEnvBody) throws ClientProtocolException, IOException {
		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://example.com/v1.0/Records\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><SOAP-ENV:Body>"
				+ soapEnvBody + "</SOAP-ENV:Body></SOAP-ENV:Envelope>";
		StringEntity stringEntity = new StringEntity(body, "UTF-8");
		stringEntity.setChunked(true);

		// Request parameters and other properties.
		HttpPost httpPost = new HttpPost("");
		httpPost.setEntity(stringEntity);
		httpPost.addHeader("Accept", "text/xml");
		httpPost.addHeader("SOAPAction", soapAction);

		// Execute and get the response.
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		String strResponse = null;
		if (entity != null) {
			strResponse = EntityUtils.toString(entity);
		}

		return strResponse;
	}
}
