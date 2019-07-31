package org.graphql.api.controller.deliverable.crud;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class GetDeliverableType implements DataFetcher<Object> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public GetDeliverableType(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Object get(DataFetchingEnvironment dataFetchingEnvironment) {

		//String typeCode = dataFetchingEnvironment.getArgument("typeCode");
		System.out.println("GetDeliverableType.get()" + request);
		Enumeration<String> headerNames = request.getHeaderNames();

		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				System.out.println("KEY: " + key);
				System.out.println("VALUE: " + value);
			}
		}

		return new Object();

	}
}
