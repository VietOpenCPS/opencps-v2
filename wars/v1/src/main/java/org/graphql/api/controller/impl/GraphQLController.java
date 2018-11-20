package org.graphql.api.controller.impl;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;

/**
 * Created by binhth
 */
@RestController
public class GraphQLController {

	public GraphQLController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	GraphQLService graphQLService;

	public GraphQLController(GraphQLService graphQLService) {
		this.graphQLService = graphQLService;
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST, produces = "application/json; charset=utf-8" )
	public ResponseEntity<Object> allItems(@RequestBody String query) {

		ExecutionResult result = graphQLService.getGraphQL().execute(query);

		Gson gson = new Gson();

		String json = gson.toJson(result.getData(), LinkedHashMap.class);

		return new ResponseEntity<>(json, HttpStatus.OK);

	}

}
