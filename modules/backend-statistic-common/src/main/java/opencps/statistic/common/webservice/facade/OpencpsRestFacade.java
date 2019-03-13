package opencps.statistic.common.webservice.facade;

import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.util.OpencpsUtils;

/**
 * Abstract class for implementing REST web service calls. Implement and
 * override the makeServiceCall and do your setup there (build rest request
 * object if needed, url formatting) and use the executeGenericRestCall to begin
 * the rest call.
 *
 * Use the helper methods available below to set up the data for the rest call.
 *
 * @author khoavu
 */
public abstract class OpencpsRestFacade<T, R> {
	private static final int DEFAULT_CONNECT_TIMEOUT = 3 * 60000;
	private static final int DEFAULT_READ_TIMEOUT = 3 * 60000;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpencpsRestFacade.class);

	private List<Class<? extends Exception>> exceptionsWhitelist = new ArrayList<>();

	public List<Class<? extends Exception>> getExceptionsWhitelist() {
		return exceptionsWhitelist;
	}

	private Proxy proxy = null;

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	protected abstract R makeServiceCall(T payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException;

	/**
	 * Uses RestTemplate to execute an HTTP rest request
	 * 
	 * @param url
	 * @param requestMethod
	 * @param requestHeaders
	 * @param requestContent
	 * @param responseClass
	 * @return
	 */
	protected ResponseEntity<R> executeGenericRestCall(String url, HttpMethod requestMethod, HttpHeaders requestHeaders,
			Object requestContent, Class<R> responseClass)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		//LOGGER.info("RESOURCE: {}", url);

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
		requestFactory.setReadTimeout(DEFAULT_READ_TIMEOUT);
		
		Optional.ofNullable(proxy).ifPresent(requestFactory::setProxy);

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		// log the request content as json formatted
		OpencpsUtils.logAsFormattedJson(LOGGER, requestContent);

		try {
			OpencpsUtils.logAsFormattedJson(LOGGER, restTemplate.exchange(url, requestMethod,
					new HttpEntity<>(requestContent, buildJsonHeader(requestHeaders)), responseClass));

			return restTemplate.exchange(url, requestMethod,
					new HttpEntity<>(requestContent, buildJsonHeader(requestHeaders)), responseClass);
		} catch (ResourceAccessException e) {
			LOGGER.error("REST call resource error: {} ", e.getMessage());
			throw new UpstreamServiceTimedOutException(e);
		} catch (Exception e) { // catch everything we don't know about here :)
			LOGGER.error("REST call exception error: {}", e.getMessage());
			if (!exceptionsWhitelist.contains(e.getClass())) {
				throw new UpstreamServiceFailedException(e);
			}
			throw e;
		}
	}

	/**
	 * Helper method to build the url when there are path variables.
	 *
	 * @param url
	 * @param pathVariables
	 * @param queryParams
	 * @return
	 */
	protected static String buildUrl(String url, @NotNull HashMap<String, String> pathVariables,
			MultiValueMap<String, String> queryParams) {

		URI uri = null;

		// Set Path Variables from key value pairs
		// NOTE: This is NOT null safe. pathVariables cannot be null.
		uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(pathVariables).toUri();

		// Set Query Parameters
		uri = UriComponentsBuilder.fromUri(uri).queryParams(queryParams).build().toUri();

		return uri.toString();
	}

	/**
	 * Helper method to build the url if the url is static and doesn't change
	 *
	 * @param httpUrl
	 * @param pathComplete
	 * @param queryParams
	 * @return
	 */
	protected static String buildUrl(String httpUrl, String pathComplete, HashMap<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl);

		builder.path(pathComplete);

		if (null != queryParams) {
			// build the URL with all the params it needs
			builder = buildUrlParams(builder, queryParams);
		}

		UriComponents uriComponents = builder.build();
		return uriComponents.toString();
	}

	private static UriComponentsBuilder buildUrlParams(UriComponentsBuilder builder,
			HashMap<String, String> queryParams) {

		// for each key value pair in queryParams, it's calling the queryParam()
		// method
		// on the UriComponentsBuilder to add the param
		queryParams.forEach(builder::queryParam);

		return builder;
	}

	/**
	 * Default method for building the JsonHeaders
	 *
	 * @param httpHeaders
	 * @return
	 */
	protected HttpHeaders buildJsonHeader(HttpHeaders httpHeaders) {
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		return httpHeaders;
	}

	/**
	 * Default method for setting the HttpHeaders authorization token
	 *
	 * @param httpHeaders
	 * @param authorizationField
	 * @return
	 */
	protected HttpHeaders setHttpHeadersAuthorization(HttpHeaders httpHeaders, String authorizationField) {

		httpHeaders.add("Authorization", "Basic " + authorizationField);

		return httpHeaders;
	}
	
	protected HttpHeaders setHttpHeadersAuthorization(HttpHeaders httpHeaders, String userName, String password) {
		String authString = userName + ":" + password;
		
		String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

		httpHeaders.add("Authorization", "Basic " + authStringEnc);

		return httpHeaders;
	}
}