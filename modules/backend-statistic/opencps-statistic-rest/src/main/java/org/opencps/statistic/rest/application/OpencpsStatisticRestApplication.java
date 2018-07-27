package org.opencps.statistic.rest.application;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opencps.statistic.common.webservice.exception.OpencpsServiceException;
import opencps.statistic.common.webservice.exception.OpencpsServiceExceptionDetails;
import opencps.statistic.common.webservice.exception.ServiceException;

/**
 * @author khoavu
 */
@ApplicationPath("/statistics")
@Component(immediate = true, service = Application.class)
@Consumes("application/json")
@Produces("application/json")
public class OpencpsStatisticRestApplication extends Application {

	private final static Logger LOG = LoggerFactory.getLogger(OpencpsStatisticRestApplication.class);

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	public DossierStatisticResponse working() {

		LOG.info("GET DossierStatisticResponse");

		DossierStatisticResponse dossierStatisticResponse = new DossierStatisticResponse();
		dossierStatisticResponse.setTotal(1);
		DossierStatisticData dossierStatisticData = new DossierStatisticData();
		dossierStatisticData.setBetimesCount(1);
		dossierStatisticResponse.setDossierStatisticData(dossierStatisticData);
		
		OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();
		serviceExceptionDetails.setFaultCode("100");
		serviceExceptionDetails.setFaultMessage("Student Name is not correct");
		//throwException(new OpencpsServiceException(serviceExceptionDetails));

		return dossierStatisticResponse;
	}

	/**
	 * Handle Exception
	 * 
	 * @param serviceException
	 * @throws ServiceException
	 */
	public static void throwException(OpencpsServiceException serviceException) throws ServiceException {
		ResponseBuilder builder = Response.status(Response.Status.NOT_ACCEPTABLE);
		builder.type("application/json");
		builder.entity(serviceException.getFaultDetails());
		throw new WebApplicationException(builder.build());
	}

}