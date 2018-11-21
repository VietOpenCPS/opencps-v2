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
	DeliverableService deliverableService;
	@Autowired
	DataItemService dataItemService;

	public GraphQLController(DeliverableService deliverableService, DataItemService dataItemService) {
		this.deliverableService = deliverableService;
		this.dataItemService = dataItemService;
	}

	@RequestMapping(value = "/deliverable", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> deliverable(@RequestBody String query) {
		System.out.println("GraphQLController.deliverable()" + query);
		ExecutionResult result = deliverableService.getGraphQL().execute(query);

		Gson gson = new Gson();

		String json = gson.toJson(result.getData(), LinkedHashMap.class);

		return new ResponseEntity<>(json, HttpStatus.OK);

	}

	@RequestMapping(value = "/dataitem", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> dataitem(@RequestBody String query) {

		ExecutionResult result = dataItemService.getGraphQL().execute(query);

		Gson gson = new Gson();

		String json = gson.toJson(result.getData(), LinkedHashMap.class);

		return new ResponseEntity<>(json, HttpStatus.OK);

	}

}
