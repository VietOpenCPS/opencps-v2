package org.graphql.api.controller.deliverable.crud;

import org.springframework.stereotype.Component;
import org.graphql.api.controller.utils.WebKeys;

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
		
		String collectionCode = dataFetchingEnvironment.getArgument(WebKeys.COLLECTION_CODE);

		System.out.println("createDictCollection.get(collectionCode)" + collectionCode);

		return new Object();

	}
}
