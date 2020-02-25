package org.opencps.api.controller.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Method;

import org.opencps.api.oai.model.oaipmh.OAIPMHtype;
import org.opencps.api.oai.model.oaipmh.RequestType;

public class OAIBuilderUtils {

	private static final Log _log = LogFactoryUtil.getLog(OAIBuilderUtils.class);

	private static final String M_CALCULATE = "calculate";
	private RequestType query;
	private OAIPMHtype results;
	private static OAIBuilderUtils instance;

	private OAIBuilderUtils(RequestType query) {
		super();
		this.query = query;
		init();
	}

	public static OAIBuilderUtils getInstance(RequestType query) {
		if (instance == null) {
			instance = new OAIBuilderUtils(query);
		}
		return instance;
	}

	public RequestType getQuery() {
		return query;
	}

	public void setQuery(RequestType query) {
		this.query = query;
	}

	public OAIPMHtype getResults() {
		return results;
	}

	public void setResults(OAIPMHtype results) {
		this.results = results;
	}

	public void destroy() {
		query = null;
		results = null;
		instance = null;
	}

	private void init() {

		if (this.query == null) {
			return;
		}
		String runMethodName = M_CALCULATE + this.query.getVerb().value();
		try {

			Method calculateMethod = OAIMethodUtils.class.getDeclaredMethod(runMethodName, RequestType.class);
			this.results = (OAIPMHtype) calculateMethod.invoke(new OAIMethodUtils(), this.query);

		} catch (Exception e) {
			_log.error(e);
		}
	}

}
