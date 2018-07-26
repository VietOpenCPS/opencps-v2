package org.opencps.statistic.rest.application;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import opencps.statistic.common.webservice.exception.OpencpsApiError;
import opencps.statistic.common.webservice.exception.OpencpsApiErrorResponse;

/**
 * @author khoavu
 */
@ApplicationPath("/statistics")
@Component(immediate = true, service = Application.class)
public class OpencpsStatisticRestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces("text/plain")
	public String working() {
		
		return "It works!";
	}
	
	  public static void throwException(ServiceException serviceException) throws ServiceException {
		    ResponseBuilder builder = Response.status(Response.Status.NOT_ACCEPTABLE);
		    builder.type("application/json");
		    builder.entity(serviceException.getFaultDetails());
		    throw new WebApplicationException(builder.build());
		  }

	private ResponseEntity<Object> buildResponseEntity(OpencpsApiError oleApiError, HttpHeaders headers,
			HttpStatus status) {

		OpencpsApiErrorResponse oleApiErrorResponse = new OpencpsApiErrorResponse(oleApiError);
		return new ResponseEntity<>(oleApiErrorResponse, headers, status);
	}

}