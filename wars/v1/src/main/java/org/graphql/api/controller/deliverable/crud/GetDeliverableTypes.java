package org.graphql.api.controller.deliverable.crud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class GetDeliverableTypes implements DataFetcher<List<Object>> {

	@Override
	public List<Object> get(DataFetchingEnvironment dataFetchingEnvironment) {

		int start = dataFetchingEnvironment.getArgument("start");
		int end = dataFetchingEnvironment.getArgument("end");

		return new ArrayList<>();

	}
}
