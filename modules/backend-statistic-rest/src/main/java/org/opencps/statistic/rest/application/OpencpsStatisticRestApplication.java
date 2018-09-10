package org.opencps.statistic.rest.application;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.StringPool;

import opencps.statistic.common.webservice.exception.OpencpsServiceException;
import opencps.statistic.common.webservice.exception.OpencpsServiceExceptionDetails;
import opencps.statistic.common.webservice.exception.ServiceException;

/**
 * @author khoavu
 */
@Component( 
property = { 
    JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/rest/statistics", 
    JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.reststatistics"
}, 
service = Application.class)
@Consumes("application/json")
@Produces("application/json")
public class OpencpsStatisticRestApplication extends Application {

	private final static Logger LOG = LoggerFactory.getLogger(OpencpsStatisticRestApplication.class);

	private DossierStatisticFinderService dossierStatisticFinderService = new DossierStatisticFinderServiceImpl();

	public static final String ALL_MONTH = "-1";

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}


	@GET
	public DossierStatisticResponse searchDossierStatistic(@HeaderParam("groupId") long groupId,
			@QueryParam("month") @DefaultValue(ALL_MONTH) int month, @QueryParam("year") int year,
			@QueryParam("domain") @DefaultValue(StringPool.BLANK) String domain,
			@QueryParam("agency") @DefaultValue(StringPool.BLANK) String govAgencyCode,
			@QueryParam("group") String groupAgencyCode, @QueryParam("reporting") boolean reporting,
			@QueryParam("start") int start, @QueryParam("end") int end) {

		LOG.info("GET DossierStatisticResponse");

		DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;

		validInput(month, year, start, end);

		dossierStatisticRequest.setDomain(domain);
		dossierStatisticRequest.setGovAgencyCode(govAgencyCode);
		dossierStatisticRequest.setGroupAgencyCode(groupAgencyCode);
		dossierStatisticRequest.setReporting(reporting);
		dossierStatisticRequest.setGroupId(groupId);
		dossierStatisticRequest.setStart(start);
		dossierStatisticRequest.setEnd(end);
		dossierStatisticRequest.setMonth(month);
		dossierStatisticRequest.setYear(year);

		OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

		try {
			return dossierStatisticFinderService.finderDossierStatistic(dossierStatisticRequest);
		} catch (Exception e) {
			
			LOG.error("error", e);
			
			serviceExceptionDetails.setFaultCode("500");
			serviceExceptionDetails.setFaultMessage(e.getMessage());

			throwException(new OpencpsServiceException(serviceExceptionDetails));
		}

		return null;
	}


	private void validInput(int month, int year, int start, int end) {
		OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

		LocalDate localDate = LocalDate.now();

		if (end < start) {
			serviceExceptionDetails.setFaultCode("400");
			serviceExceptionDetails.setFaultMessage("Invalid start, end");
			throwException(new OpencpsServiceException(serviceExceptionDetails));
		}

		if (year < DossierStatisticConstants.START_YEARS || year > localDate.getYear()) {
			serviceExceptionDetails.setFaultCode("400");
			serviceExceptionDetails.setFaultMessage("Invalid year");
			throwException(new OpencpsServiceException(serviceExceptionDetails));

		}

		if (month != -1) {
			if (month < 0 || month > 12) {
				serviceExceptionDetails.setFaultCode("400");
				serviceExceptionDetails.setFaultMessage("Invalid month");
				throwException(new OpencpsServiceException(serviceExceptionDetails));

			}
		}

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