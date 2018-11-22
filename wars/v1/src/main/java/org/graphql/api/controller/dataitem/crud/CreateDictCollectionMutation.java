package org.graphql.api.controller.dataitem.crud;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class CreateDictCollectionMutation implements DataFetcher<DictCollection> {

	@Override
	public DictCollection get(DataFetchingEnvironment dataFetchingEnvironment) {

		System.out.println("MutationWiring.createDictCollection()");
		
		String collectionCode = dataFetchingEnvironment.getArgument("collectionCode");

		System.out.println("createDictCollection.get(collectionCode)" + collectionCode);

		DictCollection results = DictCollectionLocalServiceUtil.fetchDictCollection(1);

		return results;

	}
}
