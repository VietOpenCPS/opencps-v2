package org.opencps.statistic.rest.application;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.dto.ServiceDomainData;
import org.opencps.statistic.rest.dto.ServiceDomainRequest;
import org.opencps.statistic.rest.dto.ServiceDomainResponse;
import org.opencps.statistic.rest.engine.service.StatisticEngineFetch;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.rest.engine.service.StatisticSumYearService;
import org.opencps.statistic.rest.facade.OpencpsCallDossierRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.facade.OpencpsCallServiceDomainRestFacadeImpl;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static Log _log = LogFactoryUtil.getLog(OpencpsStatisticRestApplication.class);

	@GET
	public DossierStatisticResponse searchDossierStatistic(@HeaderParam("groupId") long groupId,
			@QueryParam("month") @DefaultValue(ALL_MONTH) int month, @QueryParam("year") int year,
			@QueryParam("domain") @DefaultValue(StringPool.BLANK) String domain,
			@QueryParam("agency") @DefaultValue(StringPool.BLANK) String govAgencyCode,
			@QueryParam("group") String groupAgencyCode, @QueryParam("reporting") boolean reporting,
			@QueryParam("start") int start, @QueryParam("end") int end, 
			@QueryParam("fromStatisticDate") @DefaultValue(StringPool.BLANK) String fromStatisticDate,
			@QueryParam("toStatisticDate") @DefaultValue(StringPool.BLANK) String toStatisticDate) {

		//LOG.info("GET DossierStatisticResponse");
		_log.info("START DossierStatisticResponse: "+govAgencyCode);

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;
		
		boolean calculate = false;
		if (month > 0 || year > 0) {
			calculate = true;
		}

		if (calculate) {
			try {
				//processUpdateDB(groupId, month, year);
				//
				validInput(month, year, start, end);
				//
				DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();
				dossierStatisticRequest.setDomain(domain);
				if ("all".equals(govAgencyCode)) {
					dossierStatisticRequest.setGovAgencyCode(StringPool.BLANK);
				} else {
					dossierStatisticRequest.setGovAgencyCode(govAgencyCode);
				}
				dossierStatisticRequest.setGroupAgencyCode(groupAgencyCode);
				dossierStatisticRequest.setReporting(reporting);
				dossierStatisticRequest.setGroupId(groupId);
				dossierStatisticRequest.setStart(start);
				dossierStatisticRequest.setEnd(end);
				dossierStatisticRequest.setMonth(month);
				dossierStatisticRequest.setYear(year);
				//
				DossierStatisticResponse statisticResponse = dossierStatisticFinderService
						.finderDossierStatistic(dossierStatisticRequest);
				if (statisticResponse != null) {
					statisticResponse.setAgency(govAgencyCode);
				}

				return statisticResponse;
			} catch (Exception e) {
				LOG.error("error", e);
				OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

				serviceExceptionDetails.setFaultCode("500");
				serviceExceptionDetails.setFaultMessage(e.getMessage());

				throwException(new OpencpsServiceException(serviceExceptionDetails));
			}
			
		} else {
			try {
				GetDossierRequest payload = new GetDossierRequest();
				if ("all".equals(govAgencyCode)) {
					payload.setGovAgencyCode(StringPool.BLANK);
				} else {
					payload.setGovAgencyCode(govAgencyCode);
				}
				payload.setGroupId(groupId);
				payload.setStart(start);
				payload.setEnd(end);
				payload.setFromStatisticDate(fromStatisticDate);
				payload.setToStatisticDate(toStatisticDate);
				payload.setCalculate(calculate);
				
				GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);
				if (dossierResponse != null) {
					List<GetDossierData> dossierDataList = dossierResponse.getData();
					List<DossierStatisticData> statisticDataList = new ArrayList<>();
					if (dossierDataList != null && dossierDataList.size() > 0) {
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();
						Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();
						engineFetch.fecthStatisticData(groupId, statisticData, dossierDataList, month);
						//StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
						//statisticEngineUpdate.updateStatisticData(statisticData);
						//
						statisticData.forEach((k, v) -> 
						statisticDataList.add(v));
					}
					//
					DossierStatisticResponse statisticResponse = new DossierStatisticResponse();
					statisticResponse.setTotal(statisticDataList.size());
					statisticResponse.setDossierStatisticData(statisticDataList);
					if (statisticResponse != null) {
						statisticResponse.setAgency(govAgencyCode);
					}

					return statisticResponse;
				}

			} catch (Exception e) {
				
				LOG.error("error", e);
				OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

				serviceExceptionDetails.setFaultCode("500");
				serviceExceptionDetails.setFaultMessage(e.getMessage());

				throwException(new OpencpsServiceException(serviceExceptionDetails));
			}
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

	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();
	private OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();

	private void processUpdateDB(long groupId, int month, int year) throws Exception {

		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
		long companyId = 0;
		if (group.getType() == 1 && group.isSite()) {
			companyId = group.getCompanyId();
		}

		// Get service domain to groupId
		ServiceDomainRequest sdPayload = new ServiceDomainRequest();
		sdPayload.setGroupId(groupId);
		ServiceDomainResponse serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
		// Get dossier to groupId
		GetDossierRequest payload = new GetDossierRequest();
		payload.setGroupId(groupId);
		// Delete data old of month/year
		engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);

		payload.setMonth(Integer.toString(month));
		payload.setYear(Integer.toString(year));

		GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);
		if (dossierResponse != null) {
			List<GetDossierData> dossierDataList = dossierResponse.getData();
			if (dossierDataList != null && dossierDataList.size() > 0) {
				if (serviceDomainResponse != null) {
					List<ServiceDomainData> serviceDomainDataList = serviceDomainResponse.getData();
					if (serviceDomainDataList != null && serviceDomainDataList.size() > 0) {
						for (ServiceDomainData sdd : serviceDomainDataList) {
							boolean existsDomain = false;
							for (GetDossierData dd : dossierDataList) {
								if (dd.getDomainCode().equals(sdd.getItemCode())) {
									existsDomain = true;
									break;
								}
							}
							if (!existsDomain) {
								try {
									engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month,
											year);
								} catch (NoSuchOpencpsDossierStatisticException e) {
									_log.error(e);
								}
							}
						}
					}
				} else {
					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
				}

				StatisticEngineFetch engineFetch = new StatisticEngineFetch();

				Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

				engineFetch.fecthStatisticData(groupId, statisticData, dossierDataList, month);

				StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();

				statisticEngineUpdate.updateStatisticData(statisticData);
			} else {
				List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
				if (serviceDomainData != null) {
					for (ServiceDomainData sdd : serviceDomainData) {
						try {
							engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
						} catch (NoSuchOpencpsDossierStatisticException e) {

						}
					}
				}
				engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
			}
		} else {
			engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
		}

		/* Update summary */
		StatisticSumYearService statisticSumYearService = new StatisticSumYearService();

		statisticSumYearService.caculateSumYear(companyId, groupId);

	}

	private static String convertNormalDateToLuceneDate(String normal) {
		if (Validator.isNull(normal)) {
			return StringPool.BLANK;
		}
		String[] splitD = normal.split("/");
		if (splitD.length != 3 ||
				splitD[1].length() > 2 ||
				splitD[0].length() > 2) {
			return StringPool.BLANK;
		}
		String year = splitD[2];
		String month = (splitD[1].length() == 1) ? "0" + splitD[1] : splitD[1];
		String day = (splitD[0].length() == 1) ? "0" + splitD[0] : splitD[0];
		
		return year + month + day;
	}
}