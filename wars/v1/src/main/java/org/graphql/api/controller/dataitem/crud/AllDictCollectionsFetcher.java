package org.graphql.api.controller.dataitem.crud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.graphql.api.controller.utils.WebKeys;

/**
 * Created binhth
 */
@Component
public class AllDictCollectionsFetcher implements DataFetcher<List<Object>> {

    @Override
    public List<Object> get(DataFetchingEnvironment dataFetchingEnvironment) {
    	
    	int start = dataFetchingEnvironment.getArgument(WebKeys.START);
    	int end = dataFetchingEnvironment.getArgument(WebKeys.END);
    	
    	System.out.println("AllDictCollectionsFetcher.get(start)" + start);
    	System.out.println("AllDictCollectionsFetcher.get(end)" + end);
    	
        return new ArrayList<>();
        
    }
}
