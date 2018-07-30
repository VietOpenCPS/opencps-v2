package org.opencps.statistic.rest.application;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.statistic.rest.dto.DossierResponse;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.dao.orm.QueryUtil;

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

	private DossierStatisticFinderService dossierStatisticFinderService = new DossierStatisticFinderServiceImpl();

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	@GET
	public DossierResponse searchDossier(@HeaderParam("groupId") long groupId, @QueryParam("govAgencyCode") String govAgencyCode,
			@QueryParam("groupAgencyCode") String groupAgencyCode, @QueryParam("reporting") boolean reporting,
			@QueryParam("start") int start, @QueryParam("end") int end) {

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;

		
		return null;
	}
	

	@GET
	public DossierStatisticResponse searchDossierStatistic(@HeaderParam("groupId") long groupId,
			@QueryParam("domain") String domain, @QueryParam("govAgencyCode") String govAgencyCode,
			@QueryParam("groupAgencyCode") String groupAgencyCode, @QueryParam("reporting") boolean reporting,
			@QueryParam("start") int start, @QueryParam("end") int end) {

		LOG.info("GET DossierStatisticResponse");

		LOG.info("GROUPID_" + groupId);
		LOG.info("DOMAIN_" + domain);
		LOG.info("GOVAGENCYCODE_" + govAgencyCode);
		LOG.info("GROUPAGENCYCODE_" + groupAgencyCode);
		LOG.info("REPORTING_" + reporting);

		DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;

		LOG.info("START_" + start);
		LOG.info("END_" + end);

		dossierStatisticRequest.setDomain(domain);
		dossierStatisticRequest.setGovAgencyCode(govAgencyCode);
		dossierStatisticRequest.setGroupAgencyCode(groupAgencyCode);
		dossierStatisticRequest.setReporting(reporting);
		dossierStatisticRequest.setGroupId(groupId);
		dossierStatisticRequest.setStart(start);
		dossierStatisticRequest.setEnd(end);

		OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();
		serviceExceptionDetails.setFaultCode("500");
		serviceExceptionDetails.setFaultMessage("Fault");

		try {
			return dossierStatisticFinderService.finderDossierStatistic(dossierStatisticRequest);
		} catch (Exception e) {
			
			
			throwException(new OpencpsServiceException(serviceExceptionDetails));
		}

		return null;
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