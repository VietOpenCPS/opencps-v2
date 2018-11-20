package org.graphql.api.controller.deliverable.crud;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class CreateDeliverableType implements DataFetcher<Object> {

	@Override
	public Object get(DataFetchingEnvironment dataFetchingEnvironment) {

		System.out.println("MutationWiring.createDictCollection()");
		
		String collectionCode = dataFetchingEnvironment.getArgument("collectionCode");

		System.out.println("createDictCollection.get(collectionCode)" + collectionCode);

		return new Object();

	}
}
