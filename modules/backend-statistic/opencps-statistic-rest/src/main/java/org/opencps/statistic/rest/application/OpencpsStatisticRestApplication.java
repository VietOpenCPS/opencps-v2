package org.opencps.statistic.rest.application;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.statistic.rest.dto.DossierRequest;
import org.opencps.statistic.rest.dto.DossierResponse;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.service.DossierFinderService;
import org.opencps.statistic.rest.service.DossierFinderServiceImpl;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
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

	private DossierFinderService dossierFinderService = new DossierFinderServiceImpl();

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/dossiers")
	public DossierResponse searchDossier(@HeaderParam("groupId") long groupId,
			@QueryParam("registerBookCode") String registerBookCode, @QueryParam("processNo") String processNo,
			@QueryParam("serviceCode") String serviceCode, @QueryParam("govAgencyCode") String govAgencyCode,
			@QueryParam("applicantIdType") String applicantIdType, @QueryParam("applicantIdNo") String applicantIdNo,
			@QueryParam("cityCode") String cityCode, @QueryParam("districtCode") String districtCode,
			@QueryParam("wardCode") String wardCode, @QueryParam("contactTelNo") String contactTelNo,
			@QueryParam("contactEmail") String contactEmail, @QueryParam("delegateIdNo") String delegateIdNo,
			@QueryParam("delegateTelNo") String delegateTelNo, @QueryParam("dossierStatus") String dossierStatus,
			@QueryParam("dossierSubStatus") String dossierSubStatus,
			@QueryParam("dossierActionId") long dossierActionId, @QueryParam("viaPostal") int viaPostal,
			@QueryParam("online") boolean online, @QueryParam("onlineValue") boolean onlineValue,
			@QueryParam("originality") int originality, @QueryParam("serverNo") String serverNo,
			@QueryParam("originDossierId") long originDossierId, @QueryParam("received") boolean received,
			@QueryParam("released") boolean released, @QueryParam("betime") boolean betime,
			@QueryParam("ontime") boolean ontime, @QueryParam("undue") boolean undue, @QueryParam("start") int start,
			@QueryParam("end") int end) {

		DossierRequest dossierRequest = new DossierRequest();

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;

		dossierRequest.setRegisterBookCode(registerBookCode);
		dossierRequest.setProcessNo(processNo);
		dossierRequest.setServiceCode(serviceCode);
		dossierRequest.setGovAgencyCode(govAgencyCode);
		dossierRequest.setApplicantIdType(applicantIdType);
		dossierRequest.setApplicantIdNo(applicantIdNo);
		dossierRequest.setCityCode(cityCode);
		dossierRequest.setDistrictCode(districtCode);
		dossierRequest.setWardCode(wardCode);
		dossierRequest.setContactTelNo(contactTelNo);
		dossierRequest.setContactEmail(contactEmail);
		dossierRequest.setDelegateIdNo(delegateIdNo);
		dossierRequest.setDelegateTelNo(delegateTelNo);
		dossierRequest.setDossierStatus(dossierStatus);
		dossierRequest.setDossierSubStatus(dossierSubStatus);
		dossierRequest.setDossierActionId(dossierActionId);
		dossierRequest.setDossierActionId(dossierActionId);
		dossierRequest.setViaPostal(viaPostal);
		dossierRequest.setOnline(online);
		dossierRequest.setOriginDossierId(originDossierId);
		dossierRequest.setServerNo(serverNo);
		dossierRequest.setOriginDossierId(originDossierId);
		dossierRequest.setStart(start);
		dossierRequest.setEnd(end);
		dossierRequest.setReceived(received);
		dossierRequest.setReleased(released);
		dossierRequest.setBetime(betime);
		dossierRequest.setOntime(ontime);
		dossierRequest.setGroupId(groupId);
		dossierRequest.setStart(start);
		dossierRequest.setEnd(end);
		dossierRequest.setUndue(undue);

		validInputDossiers(start, end);

		try {
			return dossierFinderService.searchDossierService(dossierRequest);
		} catch (Exception e) {

			OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();
			serviceExceptionDetails.setFaultCode("500");
			serviceExceptionDetails.setFaultMessage(e.getMessage());

			throwException(new OpencpsServiceException(serviceExceptionDetails));
		}

		return null;
	}

	@GET
	public DossierStatisticResponse searchDossierStatistic(@HeaderParam("groupId") long groupId,
			@QueryParam("month") int month, @QueryParam("year") int year, @QueryParam("domain") String domain,
			@QueryParam("agency") String govAgencyCode, @QueryParam("group") String groupAgencyCode,
			@QueryParam("reporting") boolean reporting, @QueryParam("start") int start, @QueryParam("end") int end) {

		LOG.info("GET DossierStatisticResponse");
		LOG.info("YEAR" + year);
		LOG.info("MONTH" + month);

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

			serviceExceptionDetails.setFaultCode("500");
			serviceExceptionDetails.setFaultMessage(e.getMessage());

			throwException(new OpencpsServiceException(serviceExceptionDetails));
		}

		return null;
	}

	private void validInputDossiers(int start, int end) {

		OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

		if (end < start) {
			serviceExceptionDetails.setFaultCode("400");
			serviceExceptionDetails.setFaultMessage("Invalid start, end");
			throwException(new OpencpsServiceException(serviceExceptionDetails));
		}
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

		if (month < 0 || month > 12) {
			serviceExceptionDetails.setFaultCode("400");
			serviceExceptionDetails.setFaultMessage("Invalid month");
			throwException(new OpencpsServiceException(serviceExceptionDetails));

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