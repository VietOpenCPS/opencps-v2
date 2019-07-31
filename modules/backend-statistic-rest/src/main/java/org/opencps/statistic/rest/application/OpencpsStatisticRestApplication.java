package org.opencps.statistic.rest.application;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierSearchModel;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticModel;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.dto.GetPersonData;
import org.opencps.statistic.rest.dto.GetPersonRequest;
import org.opencps.statistic.rest.dto.GetPersonResponse;
import org.opencps.statistic.rest.dto.GetVotingResultData;
import org.opencps.statistic.rest.dto.GetVotingResultRequest;
import org.opencps.statistic.rest.dto.GetVotingResultResponse;
import org.opencps.statistic.rest.dto.PersonRequest;
import org.opencps.statistic.rest.dto.PersonResponse;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.ServiceDomainData;
import org.opencps.statistic.rest.dto.ServiceDomainRequest;
import org.opencps.statistic.rest.dto.ServiceDomainResponse;
import org.opencps.statistic.rest.dto.StatisticFixedModel;
import org.opencps.statistic.rest.dto.StatisticFixedResult;
import org.opencps.statistic.rest.dto.VotingResultRequest;
import org.opencps.statistic.rest.dto.VotingResultResponse;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.rest.dto.VotingSearchModel;
import org.opencps.statistic.rest.engine.service.StatisticEngineFetch;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.rest.engine.service.StatisticSumYearService;
import org.opencps.statistic.rest.engine.service.StatisticUtils;
import org.opencps.statistic.rest.facade.OpencpsCallDossierRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallPersonRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.facade.OpencpsCallServiceDomainRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallStatisticRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallVotingRestFacadeImpl;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;
import org.opencps.statistic.rest.service.PersonStatisticFinderService;
import org.opencps.statistic.rest.service.PersonStatisticFinderServiceImpl;
import org.opencps.statistic.rest.service.VotingStatisticFinderService;
import org.opencps.statistic.rest.service.VotingStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierConstants;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.DossierStatisticUtils;
import org.opencps.statistic.rest.util.StatisticDataUtil;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opencps.statistic.common.webservice.exception.OpencpsServiceException;
import opencps.statistic.common.webservice.exception.OpencpsServiceExceptionDetails;
import opencps.statistic.common.webservice.exception.ServiceException;
import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

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
			@BeanParam DossierSearchModel query) {

		//LOG.info("GET DossierStatisticResponse");
		//_log.info("START DossierStatisticResponse: "+query.getAgency());

		int start = query.getStart();
		int end = query.getEnd();
		int month = query.getMonth();
		int year = query.getYear();
		String govAgencyCode = query.getAgency();
		String domain = query.getDomain();
		String groupAgencyCode = query.getGroupAgencyCode();
		String fromStatisticDate = query.getFromStatisticDate();
		String toStatisticDate = query.getToStatisticDate();
		//boolean reporting = query.getReporting();
		Integer reCalculate = query.getReCalculate();
		if (reCalculate == null) {
			reCalculate = 0;
		}

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;
		
		boolean calculate = true;
		if (Validator.isNotNull(fromStatisticDate) ||Validator.isNotNull(toStatisticDate)) {
			calculate = false;
		}

		if (!calculate) {
			String status = query.getStatus();
			String substatus = query.getSubstatus();
			String service = query.getService();
			String template = query.getTemplate();
			String originality = query.getOriginality();
			String owner = query.getOwner();
			//String follow = query.getFollow();
			String step = query.getStep();
			//String top = query.getTop();
			String dossierIdNo = query.getDossierNo();
//			int monthStatistic = 0;
//			//Get month statistic
//			if (Validator.isNotNull(fromStatisticDate)) {
//				String[] splitD = fromStatisticDate.split("/");
//				if (splitD.length == 3 ||
//						splitD[1].length() <= 2 ||
//						splitD[0].length() <= 2) {
//					monthStatistic = Integer.valueOf((splitD[1].length() == 1) ? "0" + splitD[1] : splitD[1]);
//				}
//			}
			Date fromCalDate = null;
			Date toCalDate = null;
			if (Validator.isNotNull(fromStatisticDate)) {
				Date fromDate = StatisticUtils.convertStringToDate(fromStatisticDate, StatisticUtils.DATE_FORMAT);
				fromCalDate = StatisticUtils.getStartDay(fromDate);
			}
			if (Validator.isNotNull(toStatisticDate)) {
				Date toDate = StatisticUtils.convertStringToDate(toStatisticDate, StatisticUtils.DATE_FORMAT);
				toCalDate = StatisticUtils.getEndDay(toDate);
			}
			//System.out.println("fromStatisticDate: "+fromStatisticDate);
			//System.out.println("toStatisticDate: "+toStatisticDate);
			//String fromReceiveDate = query.getFromReceiveDate();
			//String toReceiveDate = query.getToReceiveDate();
			//String fromReleaseDate = query.getFromReleaseDate();
			//String toReleaseDate = query.getToReleaseDate();
			//String fromFinishDate = query.getFromFinishDate();
			//String toFinishDate = query.getToFinishDate();
			//_log.info("fromFinishDate: "+fromFinishDate);
			//_log.info("toFinishDate: "+toFinishDate);

			//String fromReceiveNotDoneDate = query.getFromReceiveNotDoneDate();
			//String toReceiveNotDoneDate = query.getToReceiveNotDoneDate();
			//boolean online = Boolean.valueOf(query.getOnline());
			//String applicantIdNo = query.getApplicantIdNo();
			//Integer originDossierId = query.getOriginDossierId();
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
				payload.setStatus(status);
				payload.setSubstatus(substatus);
				payload.setServiceCode(service);
				payload.setTemplate(template);
				payload.setOriginality(originality);
				payload.setOwner(owner);
				payload.setStep(step);
				//payload.setTop(top);
				payload.setDossierNo(dossierIdNo);
				payload.setOnlineStatistic(query.getOnline());
				
				GetDossierResponse dossierResponse = new GetDossierResponse();
						
				if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
					dossierResponse = callDossierRestService.callRestService(payload);
				}
				else {
					DossierActions actions = new DossierActionsImpl();
					Sort[] sorts = null;
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + "_sortable", Sort.STRING_TYPE,
							GetterUtil.getBoolean("true")) };
					LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
					params.put(Field.GROUP_ID, String.valueOf(groupId));
					if (payload.isCalculate()) {
						params.put(DossierTerm.YEAR, year);
						params.put(DossierTerm.MONTH, month);				
					}
					else {
						if (Validator.isNotNull(payload.getGovAgencyCode())) {
							params.put("agency", payload.getGovAgencyCode());
						}
						if (Validator.isNotNull(payload.getFromStatisticDate())) {
							params.put("fromStatisticDate", APIDateTimeUtils.convertNormalDateToLuceneDate(payload.getFromStatisticDate()));
						}
						if (Validator.isNotNull(payload.getToStatisticDate())) {
							params.put("toStatisticDate", APIDateTimeUtils.convertNormalDateToLuceneDate(payload.getToStatisticDate()));
						}				
					}
					params.put(DossierConstants.DOSSIER_STATUS, payload.getStatus());
					params.put(DossierConstants.DOSSIER_SUB_STATUS, payload.getSubstatus());
					params.put(DossierConstants.SERVICECODE, payload.getServiceCode());
					params.put(DossierConstants.ONLINE, payload.getOnlineStatistic());
					params.put(DossierConstants.ORIGINALITY, payload.getOriginality());
					params.put(DossierConstants.TEMPLATE, payload.getTemplate());
					params.put(DossierConstants.STEP, payload.getStep());
					params.put(DossierConstants.DOSSIER_NO, payload.getDossierNo());

					params.put("top", "statistic");
					
					Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
					long companyId = company.getCompanyId(); 
					
					JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
					List<Document> datas = (List<Document>) jsonData.get("data");
					List<GetDossierData> dossierData = new ArrayList<>();
					_log.debug("GET DOSSIER SIZE: " + datas.size());
					for (Document doc : datas) {
						GetDossierData model = new GetDossierData();
						model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
						model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
						model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
						model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
						if (Validator.isNotNull(doc.get(DossierTerm.RECEIVE_DATE))) {
							Date receiveDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.RECEIVE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
							model.setReceiveDate(APIDateTimeUtils.convertDateToString(receiveDate, APIDateTimeUtils._NORMAL_PARTTERN));
						} else {
							model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
						}
						model.setDueDate(doc.get(DossierTerm.DUE_DATE));
						model.setExtendDate(doc.get(DossierTerm.EXTEND_DATE));
						model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));
						model.setReleaseDate(doc.get(DossierTerm.RELEASE_DATE));
						model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
						model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
						model.setLockState(doc.get(DossierTerm.LOCK_STATE));
						model.setDomainCode(doc.get(DossierTerm.DOMAIN_CODE));
						model.setDomainName(doc.get(DossierTerm.DOMAIN_NAME));
						model.setOnline(Boolean.parseBoolean(doc.get(DossierTerm.ONLINE)));
						
						dossierData.add(model);
					}
					
					dossierResponse.setTotal(datas.size());
					dossierResponse.setData(dossierData);
				}
				if (dossierResponse != null && fromCalDate != null && toCalDate != null) {
					List<GetDossierData> dossierDataList = dossierResponse.getData();
					List<DossierStatisticData> statisticDataList = new ArrayList<>();
					if (dossierDataList != null && dossierDataList.size() > 0) {
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();
						Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();
						engineFetch.fetchSumStatisticData(groupId, statisticData, dossierDataList, fromCalDate, toCalDate,
								false);
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
		} else {
			try {
				if (reCalculate == 1) {
					Date firstDay = StatisticUtils.getFirstDay(month, year);
					Date lastDay = StatisticUtils.getLastDay(month, year);
					processUpdateDB(groupId, firstDay, lastDay, month, year, true);
				}

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
				//dossierStatisticRequest.setReporting(reporting);
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
		}

		return null;
	}

	@GET
	@Path("/votings")
	public VotingResultResponse searchVotingStatistic(@HeaderParam("groupId") long groupId,
			@BeanParam VotingSearchModel query) {

		//LOG.info("GET DossierStatisticResponse");
		//_log.info("START AgencyCode: "+query.getAgency());
		//_log.info("START VotingCode: "+query.getVotingCode());
		VotingStatisticFinderService votingStatisticFinderService = new VotingStatisticFinderServiceImpl();

		int start = query.getStart();
		int end = query.getEnd();
		int month = query.getMonth();
		int year = query.getYear();
		String govAgencyCode = query.getAgency();
		String domain = query.getDomain();
		String votingCode = query.getVotingCode();
		//boolean reCalculate = query.isReCalculate();
		String fromStatisticDate = query.getFromStatisticDate();
		String toStatisticDate = query.getToStatisticDate();

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;

		boolean calculate = true;
		if (Validator.isNotNull(fromStatisticDate) || Validator.isNotNull(toStatisticDate)) {
			calculate = false;
		}

		if (!calculate) {
			//
			OpencpsCallRestFacade<GetVotingResultRequest, GetVotingResultResponse> callVotingRestService = new OpencpsCallVotingRestFacadeImpl();

			try {
				GetVotingResultRequest payload = new GetVotingResultRequest();
				if ("all".equals(govAgencyCode)) {
					payload.setGovAgencyCode(StringPool.BLANK);
				} else {
					payload.setGovAgencyCode(govAgencyCode);
				}
				payload.setGroupId(groupId);
				payload.setStart(start);
				payload.setEnd(end);
				payload.setFromVotingDate(fromStatisticDate);
				payload.setToVotingDate(toStatisticDate);
				payload.setCalculate(calculate);
				//
				Date fromCalDate = null;
				Date toCalDate = null;
				if (Validator.isNotNull(fromStatisticDate)) {
					Date fromDate = StatisticUtils.convertStringToDate(fromStatisticDate, StatisticUtils.DATE_FORMAT);
					fromCalDate = StatisticUtils.getStartDay(fromDate);
				}
				if (Validator.isNotNull(toStatisticDate)) {
					Date toDate = StatisticUtils.convertStringToDate(toStatisticDate, StatisticUtils.DATE_FORMAT);
					toCalDate = StatisticUtils.getEndDay(toDate);
				}
				List<ServerConfig> lstScs =  ServerConfigLocalServiceUtil.getByProtocol(groupId, DossierStatisticConstants.STATISTIC_PROTOCOL);
				
				if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
					if (lstScs.size() >= 1) {
						JSONObject scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							payload.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							payload.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.VOTING_ENDPOINT_KEY)) {
							payload.setEndpoint(scObject.getString(DossierStatisticConstants.VOTING_ENDPOINT_KEY));
						}						
					}
				}
				GetVotingResultResponse votingResponse = null;
				if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
					votingResponse = callVotingRestService.callRestService(payload);
				}
				else {
					votingResponse = StatisticDataUtil.getLocalVotingResponse(payload);
				}
				if (votingResponse != null && fromCalDate != null && toCalDate != null) {
					List<GetVotingResultData> votingDataList = votingResponse.getData();
					List<VotingResultStatisticData> statisticDataList = new ArrayList<>();
					if (votingDataList != null && votingDataList.size() > 0) {
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();
						//Map<String, VotingResultData> statisticData = new HashMap<String, VotingResultData>();
						Map<String, VotingResultStatisticData> statisticData = engineFetch
								.getStatisticVotingData(groupId, votingDataList, fromCalDate, toCalDate);
						//
						statisticData.forEach((k, v) -> 
						statisticDataList.add(v));
					}
					//
					VotingResultResponse statisticResponse = new VotingResultResponse();
					statisticResponse.setTotal(statisticDataList.size());
					statisticResponse.setData(statisticDataList);
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
		} else {
			try {

				validInput(month, year, start, end);
				//
				VotingResultRequest votingRequest = new VotingResultRequest();
				votingRequest.setVotingCode(votingCode);
				votingRequest.setDomain(domain);
				if ("all".equals(govAgencyCode)) {
					votingRequest.setGovAgencyCode(StringPool.BLANK);
				} else {
					votingRequest.setGovAgencyCode(govAgencyCode);
				}
				votingRequest.setGroupId(groupId);
				votingRequest.setStart(start);
				votingRequest.setEnd(end);
				votingRequest.setMonth(month);
				votingRequest.setYear(year);
				//
				VotingResultResponse statisticResponse = votingStatisticFinderService
						.finderVotingStatistic(votingRequest);
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
			
		}

		return null;
	}

	@GET
	@Path("/persons")
	public PersonResponse searchPersonStatistic(@HeaderParam("groupId") long groupId,
			@BeanParam VotingSearchModel query) {

		//LOG.info("GET DossierStatisticResponse");
		//_log.info("START AgencyCode: "+query.getAgency());
		//_log.info("START VotingCode: "+query.getVotingCode());
		//_log.info("START EmployeeId: "+query.getEmployeeId());

		PersonStatisticFinderService personStatisticFinderService = new PersonStatisticFinderServiceImpl();

		int start = query.getStart();
		int end = query.getEnd();
		int month = query.getMonth();
		int year = query.getYear();
		String govAgencyCode = query.getAgency();
		long employeeId = GetterUtil.getLong(query.getEmployeeId());
		String votingCode = query.getVotingCode();
		String fromStatisticDate = query.getFromStatisticDate();
		String toStatisticDate = query.getToStatisticDate();
		//boolean reCalculate = query.isReCalculate();

		if (start == 0)
			start = QueryUtil.ALL_POS;

		if (end == 0)
			end = QueryUtil.ALL_POS;
		
		boolean calculate = true;
		if (Validator.isNotNull(fromStatisticDate) ||Validator.isNotNull(toStatisticDate)) {
			calculate = false;
		}

		if (!calculate) {

			Date fromCalDate = null;
			Date toCalDate = null;
			if (Validator.isNotNull(fromStatisticDate)) {
				Date fromDate = StatisticUtils.convertStringToDate(fromStatisticDate, StatisticUtils.DATE_FORMAT);
				fromCalDate = StatisticUtils.getStartDay(fromDate);
			}
			if (Validator.isNotNull(toStatisticDate)) {
				Date toDate = StatisticUtils.convertStringToDate(toStatisticDate, StatisticUtils.DATE_FORMAT);
				toCalDate = StatisticUtils.getEndDay(toDate);
			}
			//
			OpencpsCallRestFacade<GetPersonRequest, GetPersonResponse> callPersonRestService = new OpencpsCallPersonRestFacadeImpl();
			List<ServerConfig> lstScs =  ServerConfigLocalServiceUtil.getByProtocol(groupId, DossierStatisticConstants.STATISTIC_PROTOCOL);
			
			try {
				GetPersonRequest payload = new GetPersonRequest();
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
				if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
					if (lstScs.size() >= 1) {
						JSONObject scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							payload.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							payload.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.VOTING_ENDPOINT_KEY)) {
							payload.setEndpoint(scObject.getString(DossierStatisticConstants.VOTING_ENDPOINT_KEY));
						}						
					}
				}			
				
				//
				GetPersonResponse personResponse = null;
				if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
					personResponse = callPersonRestService.callRestService(payload);
				}
				else {
					personResponse = StatisticDataUtil.getLocalPersonResponse(payload);
				}
				
				if (personResponse != null && fromCalDate != null && toCalDate != null) {
					List<GetPersonData> personDataList = personResponse.getData();
					List<PersonStatisticData> statisticDataList = new ArrayList<>();
					if (personDataList != null && personDataList.size() > 0) {
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();
						Map<String, PersonStatisticData> statisticData = engineFetch
								.getStatisticPersonData(groupId, personDataList, fromCalDate, toCalDate);
						//
						statisticData.forEach((k, v) -> 
						statisticDataList.add(v));
					}
					//
					PersonResponse statisticResponse = new PersonResponse();
					statisticResponse.setTotal(statisticDataList.size());
					//statisticResponse.setDossierStatisticData(statisticDataList);
					statisticResponse.setData(statisticDataList);
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
		} else {
			try {
				validInput(month, year, start, end);
				//
				PersonRequest personRequest = new PersonRequest();
				personRequest.setVotingCode(votingCode);
				personRequest.setEmployeeId(employeeId);
				if ("all".equals(govAgencyCode)) {
					personRequest.setGovAgencyCode(StringPool.BLANK);
				} else {
					personRequest.setGovAgencyCode(govAgencyCode);
				}
				personRequest.setGroupId(groupId);
				personRequest.setStart(start);
				personRequest.setEnd(end);
				personRequest.setMonth(month);
				personRequest.setYear(year);
				//
				PersonResponse statisticResponse = personStatisticFinderService.finderPersonStatistic(personRequest);
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
		}

		return null;
	}


	private void validInput(int month, int year, int start, int end) {
		OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

		//LocalDate localDate = LocalDate.now();

		if (end < start) {
			serviceExceptionDetails.setFaultCode("400");
			serviceExceptionDetails.setFaultMessage("Invalid start, end");
			throwException(new OpencpsServiceException(serviceExceptionDetails));
		}

//		if (year < DossierStatisticConstants.START_YEARS || year > localDate.getYear()) {
//			serviceExceptionDetails.setFaultCode("400");
//			serviceExceptionDetails.setFaultMessage("Invalid year");
//			throwException(new OpencpsServiceException(serviceExceptionDetails));
//
//		}

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

	private void processUpdateDB(long groupId, Date firstDay, Date lastDay, int month, int year, boolean reporting)
			throws Exception {

		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		//StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
		long companyId = 0;
		if (group.getType() == 1 && group.isSite()) {
			companyId = group.getCompanyId();
		}

		// Get service domain to groupId
		ServiceDomainRequest sdPayload = new ServiceDomainRequest();
		sdPayload.setGroupId(groupId);
		List<ServerConfig> lstScs =  ServerConfigLocalServiceUtil.getByProtocol(groupId, DossierStatisticConstants.STATISTIC_PROTOCOL);
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			if (lstScs.size() >= 1) {
				JSONObject scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
				if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
					sdPayload.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
				}
				if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
					sdPayload.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
				}
				if (scObject.has(DossierStatisticConstants.SERVICE_DOMAIN_ENDPOINT_KEY)) {
					sdPayload.setEndpoint(scObject.getString(DossierStatisticConstants.SERVICE_DOMAIN_ENDPOINT_KEY));
				}						
			}
		}
		ServiceDomainResponse serviceDomainResponse = null;
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
		}
		else {
			serviceDomainResponse = StatisticDataUtil.getLocalServiceDomain(sdPayload);
		}
		// Get dossier to groupId
		GetDossierRequest payload = new GetDossierRequest();
		payload.setGroupId(groupId);
		// Delete data old of month/year
//		engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);

		payload.setMonth(Integer.toString(month));
		payload.setYear(Integer.toString(year));
		payload.setCalculate(true);
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			if (lstScs.size() >= 1) {
				JSONObject scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
				if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
					payload.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
				}
				if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
					payload.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
				}
				if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
					payload.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
				}						
			}
		}
		
		GetDossierResponse dossierResponse = null;
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			dossierResponse = callDossierRestService.callRestService(payload);
		}
		else {
			dossierResponse = new GetDossierResponse();
			DossierActions actions = new DossierActionsImpl();
			Sort[] sorts = null;
			sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean("true")) };
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			if (payload.isCalculate()) {
				params.put(DossierTerm.YEAR, year);
				params.put(DossierTerm.MONTH, month);				
			}
			else {
				if (Validator.isNotNull(payload.getGovAgencyCode())) {
					params.put("agency", payload.getGovAgencyCode());
				}
				if (Validator.isNotNull(payload.getFromStatisticDate())) {
					params.put("fromStatisticDate", payload.getFromStatisticDate());
				}
				if (Validator.isNotNull(payload.getToStatisticDate())) {
					params.put("toStatisticDate", payload.getToStatisticDate());
				}				
			}
			params.put("top", "statistic");
			
			JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
			List<Document> datas = (List<Document>) jsonData.get("data");
			dossierResponse.setTotal(datas.size());
			List<GetDossierData> dossierData = new ArrayList<>();
			_log.debug("GET DOSSIER SIZE: " + datas.size());
			for (Document doc : datas) {
				GetDossierData model = new GetDossierData();
				model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
				model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
				model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
				model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
				if (Validator.isNotNull(doc.get(DossierTerm.RECEIVE_DATE))) {
					Date receiveDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.RECEIVE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
					model.setReceiveDate(APIDateTimeUtils.convertDateToString(receiveDate, APIDateTimeUtils._NORMAL_PARTTERN));
				} else {
					model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
				}
				model.setDueDate(doc.get(DossierTerm.DUE_DATE));
				model.setExtendDate(doc.get(DossierTerm.EXTEND_DATE));
				model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));
				model.setReleaseDate(doc.get(DossierTerm.RELEASE_DATE));
				model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
				model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
				model.setLockState(doc.get(DossierTerm.LOCK_STATE));
				model.setDomainCode(doc.get(DossierTerm.DOMAIN_CODE));
				model.setDomainName(doc.get(DossierTerm.DOMAIN_NAME));
				model.setOnline(Boolean.parseBoolean(doc.get(DossierTerm.ONLINE)));
				
				dossierData.add(model);
			}	
			
			dossierResponse.setData(dossierData);
		}
		if (dossierResponse != null) {
			List<GetDossierData> dossierDataList = dossierResponse.getData();
			if (dossierDataList != null && dossierDataList.size() > 0) {
				if (serviceDomainResponse != null) {
					List<ServiceDomainData> serviceDomainDataList = serviceDomainResponse.getData();
					if (serviceDomainDataList != null && serviceDomainDataList.size() > 0) {
						for (ServiceDomainData sdd : serviceDomainDataList) {
//							boolean existsDomain = false;
//							for (GetDossierData dd : dossierDataList) {
//								if (dd.getDomainCode().equals(sdd.getItemCode())) {
//									existsDomain = true;
//									break;
//								}
//							}
//							if (!existsDomain) {
//								try {
//									engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month,
//											year);
//								} catch (NoSuchOpencpsDossierStatisticException e) {
//									_log.error(e);
//								}
//							}
						}
					}
				} else {
//					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
				}

				StatisticEngineFetch engineFetch = new StatisticEngineFetch();

				Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

				engineFetch.fecthStatisticData(groupId, statisticData, dossierDataList, firstDay, lastDay, reporting);

				StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();

				statisticEngineUpdate.updateStatisticData(statisticData);
			} else {
				List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
				if (serviceDomainData != null) {
					for (ServiceDomainData sdd : serviceDomainData) {
//						try {
//							engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//						} catch (NoSuchOpencpsDossierStatisticException e) {
//
//						}
					}
				}
//				engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
			}
		} else {
//			engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
		}

		/* Update summary */
		//Delete record
//		engineUpdateAction.removeDossierStatisticByYear(companyId, groupId, 0, year);
		//
		StatisticSumYearService statisticSumYearService = new StatisticSumYearService();

		statisticSumYearService.caculateSumYear(companyId, groupId, year);
	}

	@POST
	@Path("/reports")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public DossierStatisticModel fixDossierStatistic(@HeaderParam("groupId") long groupId,
			@BeanParam DossierStatisticModel input) {
		try {
			OpencpsDossierStatistic statistic = OpencpsDossierStatisticLocalServiceUtil.createOrUpdateStatistic(
					0l, groupId, -1, "ADM", input.getMonth(), input.getYear(), input.getTotalCount(), input.getDeniedCount(), 
					input.getCancelledCount(), input.getProcessCount(), input.getRemainingCount(), input.getReceivedCount(), 
					input.getOnlineCount(), input.getReleaseCount(), input.getBetimesCount(), input.getOntimeCount(), input.getOvertimeCount(), input.getDoneCount(), 
					input.getReleasingCount(), input.getUnresolvedCount(), input.getProcessingCount(), input.getUndueCount(), 
					input.getOverdueCount(), input.getPausingCount(), input.getOntimePercentage(), input.getOvertimeInside(), 
					input.getOvertimeOutside(), input.getInteroperatingCount(), input.getWaitingCount(), input.getGovAgencyCode(), input.getGovAgencyName(), input.getDomainCode(), input.getDomainName(), input.getReporting(), input.getOnegateCount(), 
					input.getOutsideCount(), input.getInsideCount());
			input.setDomainCode(statistic.getDomainCode());
		} catch (SystemException e) {
			_log.debug(e);
		} catch (PortalException e) {
			_log.debug(e);
		}
		
		return input;
	}
	
	@POST
	@Path("/reports/fixed")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public StatisticFixedResult fixedStatistic(@HeaderParam("groupId") long groupId, @BeanParam StatisticFixedModel input) {
		int month = input.getMonth();
		int year = input.getYear();
		int monthCurrent = LocalDate.now().getMonthValue();
		int yearCurrent = LocalDate.now().getYear();
		StatisticFixedResult result = new StatisticFixedResult();
		if (yearCurrent < year) {
			return result;
		}
		if (year == yearCurrent && month >= monthCurrent) {
			return result;
		}
		List<OpencpsDossierStatistic> lstStatistics = OpencpsDossierStatisticLocalServiceUtil.getDossierStatisticByMonthYear(groupId, month, year);
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(groupId, ServerConfigTerm.PUBLISH_PROTOCOL);
		for (OpencpsDossierStatistic statistic : lstStatistics) {
			if (Validator.isNotNull(statistic.getGovAgencyCode())) {
				statistic.setModifiedDate(new Date());
				statistic.setReporting(true);
				OpencpsDossierStatisticLocalServiceUtil.updateOpencpsDossierStatistic(statistic);
				
				//Chốt lên cổng tra cứu
				for (ServerConfig sc : lstScs) {
					if (Validator.isNotNull(sc.getConfigs())) {
						try {
							JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
							if (configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
								long publishGroupId = configObj.getLong(SyncServerTerm.SERVER_GROUP_ID);
								OpencpsCallRestFacade<DossierStatisticModel, DossierStatisticModel> callReportService = new OpencpsCallStatisticRestFacadeImpl();
								DossierStatisticModel request = new DossierStatisticModel();
								request.setGroupId(publishGroupId);
								request.setBetimesCount(statistic.getBetimesCount());
								request.setCancelledCount(statistic.getCancelledCount());
								request.setDeniedCount(statistic.getDeniedCount());
								request.setDomainCode(statistic.getDomainCode());
								request.setDomainName(statistic.getDomainName());
								request.setDoneCount(statistic.getDoneCount());
								request.setGovAgencyCode(statistic.getGovAgencyCode());
								request.setGovAgencyName(statistic.getGovAgencyName());
								request.setGroupAgencyCode(statistic.getGroupAgencyCode());
								request.setInsideCount(statistic.getInsideCount());
								request.setInteroperatingCount(statistic.getInteroperatingCount());
								request.setMonth(statistic.getMonth());
								request.setOnegateCount(statistic.getOnegateCount());
								request.setOnlineCount(statistic.getOnlineCount());
								request.setOntimeCount(statistic.getOntimeCount());
								request.setOntimePercentage(statistic.getOntimePercentage());
								request.setOutsideCount(statistic.getOutsideCount());
								request.setOverdueCount(statistic.getOverdueCount());
								request.setOvertimeCount(statistic.getOvertimeCount());
								request.setOvertimeInside(statistic.getOvertimeInside());
								request.setOvertimeOutside(statistic.getOvertimeOutside());
								request.setPausingCount(statistic.getPausingCount());
								request.setProcessCount(statistic.getProcessCount());
								request.setProcessingCount(statistic.getProcessingCount());
								request.setReceivedCount(statistic.getReceivedCount());
								request.setReleaseCount(statistic.getReleaseCount());
								request.setReleasingCount(statistic.getReleasingCount());
								request.setRemainingCount(statistic.getRemainingCount());
								request.setReporting(statistic.getReporting());
								request.setTotalCount(statistic.getTotalCount());
								request.setUndueCount(statistic.getUndueCount());
								request.setUnresolvedCount(statistic.getUnresolvedCount());
								request.setWaitingCount(statistic.getWaitingCount());
								request.setYear(statistic.getYear());
								DossierStatisticModel model = callReportService.callRestService(request);
								_log.debug(model);
							}
						}
						catch (JSONException e) {
							_log.debug(e);
						} catch (UpstreamServiceTimedOutException e) {
							_log.debug(e);
						} catch (UpstreamServiceFailedException e) {
							_log.debug(e);
						}
					}
				}
			}
		}
		
		result.setErrorCode(0);
		result.setErrorMessage("");
		result.setSuccess(true);
		return result;
	}
	
	@GET
	@Path("/testsearch")
	public String searchDossierStatistic(@HeaderParam("groupId") long groupId,
			@QueryParam("month") Integer month, @QueryParam("year") Integer year, @QueryParam("domainCode") String domainCode, @QueryParam("govAgencyCode") String govAgencyCode) {
		List<OpencpsDossierStatistic> allSiteDatas = OpencpsDossierStatisticLocalServiceUtil.findByG(groupId);
		
		OpencpsDossierStatistic statistic = DossierStatisticUtils.checkExists(month, year, domainCode, govAgencyCode, allSiteDatas);
		if (statistic == null) {
			return "Not found";
		}
		else {
			return statistic.getTotalCount() + "";
		}
	}
}