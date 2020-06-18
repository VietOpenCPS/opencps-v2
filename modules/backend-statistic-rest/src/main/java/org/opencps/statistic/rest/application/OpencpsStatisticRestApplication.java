package org.opencps.statistic.rest.application;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
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
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsDossierStatisticManual;
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
import org.opencps.statistic.rest.dto.RealtimeData;
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
import org.opencps.statistic.rest.service.DossierStatisticManualFinderService;
import org.opencps.statistic.rest.service.DossierStatisticManualFinderServiceImpl;
import org.opencps.statistic.rest.service.PersonStatisticFinderService;
import org.opencps.statistic.rest.service.PersonStatisticFinderServiceImpl;
import org.opencps.statistic.rest.service.VotingStatisticFinderService;
import org.opencps.statistic.rest.service.VotingStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierConstants;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.DossierStatisticUtils;
import org.opencps.statistic.rest.util.StatisticDataUtil;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.statistic.service.OpencpsDossierStatisticManualLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

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
	private DossierStatisticManualFinderService dossierStatisticManualFinderService = new DossierStatisticManualFinderServiceImpl();

	public static final String ALL_MONTH = "-1";

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	private static Log _log = LogFactoryUtil.getLog(OpencpsStatisticRestApplication.class);

	@GET
	public Response searchDossierStatistic(@HeaderParam("groupId") long groupId,
			@BeanParam DossierSearchModel query, @Context Request requestCC) {

		//LOG.info("GET DossierStatisticResponse");
		//_log.info("START DossierStatisticResponse: "+query.getAgency());
		CacheControl cc = new CacheControl();
	    cc.setMaxAge(60);
	    cc.setPrivate(true);
	    
		int start = query.getStart();
		int end = query.getEnd();
		int month = query.getMonth();
		int year = query.getYear();
		String govAgencyCode = query.getAgency();
		String domain = query.getDomain();
		String system = query.getSystem();
		if (Validator.isNull(system)) {
			system = String.valueOf(0);
		}
//		String groupAgencyCode = query.getGroupAgencyCode();
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
				if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ALL).equals(govAgencyCode)) {
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
				payload.setSystem(system);
				
				GetDossierResponse dossierResponse = new GetDossierResponse();
						
				if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
					dossierResponse = callDossierRestService.callRestService(payload);
				}
				else {
					DossierActions actions = new DossierActionsImpl();
					Sort[] sorts = null;
					sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
							Boolean.TRUE) };
					LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
					params.put(Field.GROUP_ID, String.valueOf(groupId));
					if (payload.isCalculate()) {
						params.put(DossierTerm.YEAR, year);
						params.put(DossierTerm.MONTH, month);				
					}
					else {
						if (Validator.isNotNull(payload.getGovAgencyCode())) {
							params.put(DossierTerm.AGENCYS, payload.getGovAgencyCode());
						}
						if (Validator.isNotNull(payload.getFromStatisticDate())) {
							params.put(DossierTerm.FROM_STATISTIC_DATE, APIDateTimeUtils.convertNormalDateToLuceneDate(payload.getFromStatisticDate()));
						}
						if (Validator.isNotNull(payload.getToStatisticDate())) {
							params.put(DossierTerm.TO_STATISTIC_DATE, APIDateTimeUtils.convertNormalDateToLuceneDate(payload.getToStatisticDate()));
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
					params.put(DossierConstants.SYSTEM, payload.getSystem());

					params.put(DossierTerm.TOP, DossierStatisticConstants.TOP_STATISTIC);
					
					Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
					long companyId = company.getCompanyId(); 
					
					JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
					List<Document> datas = (List<Document>) jsonData.get(ConstantUtils.DATA);
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
						model.setSystem(doc.get(DossierTerm.SYSTEM_ID));
						model.setViaPostal(Integer.parseInt(doc.get(DossierTerm.VIA_POSTAL)));
						model.setFromViaPostal(GetterUtil.getInteger(doc.get(DossierTerm.FROM_VIA_POSTAL)));
						
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

					ResponseBuilder builder = Response.ok(statisticResponse);
				    builder.cacheControl(cc);
				    return builder.build();
				}

			} catch (Exception e) {
				LOG.error("error", e);
				OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

				serviceExceptionDetails.setFaultCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
				serviceExceptionDetails.setFaultMessage(e.getMessage());

				throwException(new OpencpsServiceException(serviceExceptionDetails));
			}
		} else {
			try {
				if (reCalculate == 1) {
					Date firstDay = StatisticUtils.getFirstDay(month, year);
					Date lastDay = StatisticUtils.getLastDay(month, year);
					processUpdateDB(groupId, firstDay, lastDay, month, year, true, new ArrayList<String>());
				}

				validInput(month, year, start, end);
				//
				DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();
				dossierStatisticRequest.setDomain(domain);
				if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ALL).equals(govAgencyCode)) {
					dossierStatisticRequest.setGovAgencyCode(StringPool.BLANK);
				} else {
					dossierStatisticRequest.setGovAgencyCode(govAgencyCode);
				}
				dossierStatisticRequest.setSystem(system);
//				dossierStatisticRequest.setGroupAgencyCode(groupAgencyCode);
				//dossierStatisticRequest.setReporting(reporting);
				dossierStatisticRequest.setGroupId(groupId);
				dossierStatisticRequest.setStart(start);
				dossierStatisticRequest.setEnd(end);
				dossierStatisticRequest.setMonth(month);
				dossierStatisticRequest.setYear(year);
				if (Validator.isNotNull(query.getGroupCode())) {
					DictGroup dg = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(query.getGroupCode(), groupId);
					List<DictItem> lstItems = new ArrayList<DictItem>();
					
					if (dg != null) {
						List<DictItemGroup> lstDigs = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId, dg.getDictGroupId());
						
						for (DictItemGroup dig : lstDigs) {
							DictItem di = DictItemLocalServiceUtil.fetchDictItem(dig.getDictItemId());
							DictItem parentDi = DictItemLocalServiceUtil.fetchDictItem(di.getParentItemId());
//							System.out.println("DICT ITEM: " + di + ", parent: " + parentDi + ", " + query.getParentAgency());
							if (!Validator.isNull(query.getParentAgency())) {
								if (di != null && query.getParentAgency() != null && parentDi != null && parentDi.getItemCode().contentEquals(query.getParentAgency())) {
									lstItems.add(di);									
								}
							}
							else {
								lstItems.add(di);
							}
						}
					}
					
					Comparator<DictItem> compareByItemCode = new Comparator<DictItem>() {
						@Override
					    public int compare(DictItem o1, DictItem o2) {
					        return o1.getItemCode().compareTo(o2.getItemCode());
					    }
					};
					ArrayList<DictItem> lstSortItems = new ArrayList<DictItem>();
					lstSortItems.addAll(lstItems);
					
					Collections.sort(lstSortItems, compareByItemCode);
					lstItems = lstSortItems;
					
					if (!Validator.isNull(query.getParentAgency())) {
						if (dg != null) {
							StringBuilder groupAgencyCodeFilter = new StringBuilder();
							for (DictItem di : lstItems) {
								if (!StringPool.BLANK.contentEquals(groupAgencyCodeFilter.toString())) {
									groupAgencyCodeFilter.append(StringPool.COMMA);
								}
								groupAgencyCodeFilter.append(di.getItemCode());
							}
							dossierStatisticRequest.setGroupAgencyCode(groupAgencyCodeFilter.toString());
							dossierStatisticRequest.setSystem(DossierConstants.TOTAL);
//							System.out.println("GROUP CODE AGENCY FILTER: " + groupAgencyCodeFilter);
						}						
					}
					else {
						StringBuilder groupAgencyCodeFilter = new StringBuilder();
						for (DictItem di : lstItems) {
							if (di.getLevel() == 0) {
								if (!StringPool.BLANK.contentEquals(groupAgencyCodeFilter.toString())) {
									groupAgencyCodeFilter.append(StringPool.COMMA);
								}
								groupAgencyCodeFilter.append(di.getItemCode());
							}
						}
						dossierStatisticRequest.setGroupAgencyCode(groupAgencyCodeFilter.toString());
						dossierStatisticRequest.setSystem(DossierConstants.TOTAL);
					}
				}
				//
				DossierStatisticResponse statisticResponse = dossierStatisticFinderService
						.finderDossierStatisticSystem(dossierStatisticRequest);
//				System.out.println("SEARCH GROUP CODE: " + query.getGroupCode());
				if (Validator.isNotNull(query.getGroupCode())) {
					if (Validator.isNull(query.getParentAgency())) {
						DictGroup dg = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(query.getGroupCode(), groupId);
						
//						System.out.println("SEARCH DICT GROUP: " + dg);
						if (dg != null) {
							List<DictItemGroup> lstDigs = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId, dg.getDictGroupId());
							
							List<DictItem> lstItems = new ArrayList<DictItem>();
							for (DictItemGroup dig : lstDigs) {
								DictItem di = DictItemLocalServiceUtil.fetchDictItem(dig.getDictItemId());
								lstItems.add(di);									
							}
							dossierStatisticRequest.setGroupAgencyCode(StringPool.BLANK);
							dossierStatisticRequest.setSystem(DossierConstants.TOTAL);
							for (DictItem di : lstItems) {
								dossierStatisticRequest.setGovAgencyCode(di.getItemCode());
//								System.out.println("SEARCH GROUP AGENCY: " + di.getItemCode());
								DossierStatisticResponse statisticResponseTemp = dossierStatisticFinderService
										.finderDossierStatisticSystem(dossierStatisticRequest);
//								System.out.println("SEARCH GROUP CODE SIZE: " + statisticResponseTemp.getDossierStatisticData().size());
								if (statisticResponseTemp.getDossierStatisticData().size() > 0) {
									statisticResponse.getDossierStatisticData().addAll(statisticResponseTemp.getDossierStatisticData());
								}
								else {
									DossierStatisticData data = new DossierStatisticData();
									data.setGovAgencyCode(di.getItemCode());
									data.setGovAgencyName(di.getItemName());
									statisticResponse.getDossierStatisticData().add(data);
								}
							}
							statisticResponse.setTotal(statisticResponse.getDossierStatisticData().size());
						}
					}
					else {
						DictGroup dg = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(query.getGroupCode(), groupId);
						
//						System.out.println("SEARCH DICT GROUP: " + dg);
						if (dg != null) {
							List<DictItemGroup> lstDigs = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId, dg.getDictGroupId());
							
							List<DictItem> lstItems = new ArrayList<DictItem>();
							for (DictItemGroup dig : lstDigs) {
								DictItem di = DictItemLocalServiceUtil.fetchDictItem(dig.getDictItemId());
								DictItem parentDi = DictItemLocalServiceUtil.fetchDictItem(di.getParentItemId());
								if (parentDi != null && query.getParentAgency().contentEquals(parentDi.getItemCode())) {
									lstItems.add(di);																		
								}
							}
							dossierStatisticRequest.setGroupAgencyCode(StringPool.BLANK);
							dossierStatisticRequest.setSystem(DossierConstants.TOTAL);
							for (DictItem di : lstItems) {
								dossierStatisticRequest.setGovAgencyCode(di.getItemCode());
//								System.out.println("SEARCH GROUP AGENCY: " + di.getItemCode());
								DossierStatisticResponse statisticResponseTemp = dossierStatisticFinderService
										.finderDossierStatisticSystem(dossierStatisticRequest);
//								System.out.println("SEARCH GROUP CODE SIZE: " + statisticResponseTemp.getDossierStatisticData().size());
								if (statisticResponseTemp.getDossierStatisticData().size() > 0) {
									statisticResponse.getDossierStatisticData().addAll(statisticResponseTemp.getDossierStatisticData());
								}
								else {
									DossierStatisticData data = new DossierStatisticData();
									data.setGovAgencyCode(di.getItemCode());
									data.setGovAgencyName(di.getItemName());
									statisticResponse.getDossierStatisticData().add(data);
								}								
							}
							statisticResponse.setTotal(statisticResponse.getDossierStatisticData().size());
						}						
					}
				}
				if (statisticResponse != null) {
					statisticResponse.setAgency(govAgencyCode);
				}

				ResponseBuilder builder = Response.ok(statisticResponse);
			    builder.cacheControl(cc);
			    return builder.build();
			} catch (Exception e) {
				LOG.error("error", e);
				OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

				serviceExceptionDetails.setFaultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
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
				if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ALL).equals(govAgencyCode)) {
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
						if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
							payload.setPassword(scObject.getString(DossierStatisticConstants.SECRET_KEY));
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
					payload.setFromVotingDate(APIDateTimeUtils.convertNormalDateToLuceneDate(fromStatisticDate));
					payload.setToVotingDate(APIDateTimeUtils.convertNormalDateToLuceneDate(toStatisticDate));					
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

				serviceExceptionDetails.setFaultCode(String.valueOf(HttpURLConnection.HTTP_INTERNAL_ERROR));
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
				if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ALL).equals(govAgencyCode)) {
					votingRequest.setGovAgencyCode(StringPool.BLANK);
				} else {
					votingRequest.setGovAgencyCode(govAgencyCode);
				}
				votingRequest.setGroupId(groupId);
				votingRequest.setStart(start);
				votingRequest.setEnd(end);
				if (Validator.isNull(month)) {
					month = -1;
				}
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

				serviceExceptionDetails.setFaultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
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
				if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ALL).equals(govAgencyCode)) {
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
						if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
							payload.setPassword(scObject.getString(DossierStatisticConstants.SECRET_KEY));
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
					payload.setFromStatisticDate(APIDateTimeUtils.convertNormalDateToLuceneDate(fromStatisticDate));
					payload.setToStatisticDate(APIDateTimeUtils.convertNormalDateToLuceneDate(toStatisticDate));

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

				serviceExceptionDetails.setFaultCode(String.valueOf(HttpURLConnection.HTTP_INTERNAL_ERROR));
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
				if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ALL).equals(govAgencyCode)) {
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

				serviceExceptionDetails.setFaultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
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
			serviceExceptionDetails.setFaultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			serviceExceptionDetails.setFaultMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_INTERNAL_SERVER));
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
				serviceExceptionDetails.setFaultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
				serviceExceptionDetails.setFaultMessage(ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));
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
		builder.type(MediaType.APPLICATION_JSON);
		builder.entity(serviceException.getFaultDetails());
		throw new WebApplicationException(builder.build());
	}

	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();
	private OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();

	private void processUpdateDB(long groupId, Date firstDay, Date lastDay, int month, int year, boolean reporting, List<String> lstGroupGovs)
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
				if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
					sdPayload.setPassword(scObject.getString(DossierStatisticConstants.SECRET_KEY));
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
				if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
					payload.setPassword(scObject.getString(DossierStatisticConstants.SECRET_KEY));
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
			sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
					Boolean.TRUE) };
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			if (payload.isCalculate()) {
				params.put(DossierTerm.YEAR, year);
				params.put(DossierTerm.MONTH, month);				
			}
			else {
				if (Validator.isNotNull(payload.getGovAgencyCode())) {
					params.put(DossierTerm.AGENCYS, payload.getGovAgencyCode());
				}
				if (Validator.isNotNull(payload.getFromStatisticDate())) {
					params.put(DossierTerm.FROM_STATISTIC_DATE, payload.getFromStatisticDate());
				}
				if (Validator.isNotNull(payload.getToStatisticDate())) {
					params.put(DossierTerm.TO_STATISTIC_DATE, payload.getToStatisticDate());
				}				
			}
			params.put(DossierTerm.TOP, DossierStatisticConstants.TOP_STATISTIC);
			
			JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
			List<Document> datas = (List<Document>) jsonData.get(ConstantUtils.DATA);
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
				model.setViaPostal(Integer.parseInt(doc.get(DossierTerm.VIA_POSTAL)));
				model.setFromViaPostal(Integer.parseInt(doc.get(DossierTerm.FROM_VIA_POSTAL)));
				if (model.getFromViaPostal() > 0) {
					System.out.println("===========doc.get(DossierTerm.DOSSIER_NO) from VIAPOSTAL===============" + doc.get(DossierTerm.DOSSIER_NO));
				}
				
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

				engineFetch.fecthStatisticData(groupId, statisticData, dossierDataList, firstDay, lastDay, reporting, new ArrayList<String>());

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

		statisticSumYearService.caculateSumYear(companyId, groupId, year, lstGroupGovs, lstScs);
	}

	@POST
	@Path("/reports")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public DossierStatisticModel fixDossierStatistic(@HeaderParam("groupId") long groupId,
			@BeanParam DossierStatisticModel input) {
		try {
			if (Validator.isNotNull(input.getSystem())) {
				input.setSystem((String) null);
			}
			OpencpsDossierStatistic statistic = OpencpsDossierStatisticLocalServiceUtil.createOrUpdateStatistic(0l,
					groupId, -1, DossierConstants.ADM, input.getMonth(), input.getYear(), input.getSystem(), input.getTotalCount(),
					input.getDeniedCount(), input.getCancelledCount(), input.getProcessCount(),
					input.getRemainingCount(), input.getReceivedCount(), input.getOnlineCount(),
					input.getReleaseCount(), input.getBetimesCount(), input.getOntimeCount(), input.getOvertimeCount(),
					input.getDoneCount(), input.getReleasingCount(), input.getUnresolvedCount(),
					input.getProcessingCount(), input.getUndueCount(), input.getOverdueCount(), input.getPausingCount(),
					input.getOntimePercentage(), input.getOvertimeInside(), input.getOvertimeOutside(),
					input.getInteroperatingCount(), input.getWaitingCount(), input.getGovAgencyCode(),
					input.getGovAgencyName(), input.getDomainCode(), input.getDomainName(), input.getReporting(),
					input.getOnegateCount(), input.getOutsideCount(), input.getInsideCount(), input.getFromViaPostalCount());
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
							if (configObj.has(Field.GROUP_ID)) {
								long publishGroupId = configObj.getLong(Field.GROUP_ID);
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
								request.setViaPostalCount(statistic.getViaPostalCount());
								request.setSaturdayCount(statistic.getSaturdayCount());
								request.setFromViaPostalCount(statistic.getFromViaPostalCount());
								
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
		result.setErrorMessage(StringPool.BLANK);
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
			return statistic.getTotalCount() + StringPool.BLANK;
		}
	}
	
	@POST
	@Path("/import/manual")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	public Response importManualStatistic(@HeaderParam("groupId") long groupId,
		@Multipart("file") Attachment file) {
		try {
			DataHandler dataHandle = file.getDataHandler();
			
			InputStream excelFile = dataHandle.getInputStream();
			String fileName = dataHandle.getName();
			
            Workbook workbook = null;
            
            if (fileName.endsWith(DossierConstants.XLS)) {
                workbook = new HSSFWorkbook(excelFile);            	
            }
            else if (fileName.endsWith(DossierConstants.XLSX)) {
                workbook = new XSSFWorkbook(excelFile);
            }
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            Map<Integer, List<OpencpsDossierStatisticManual>> mapStatistics = new HashMap<Integer, List<OpencpsDossierStatisticManual>>();
            
            OpencpsDossierStatisticManualLocalServiceUtil.removeAll();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0) continue;
                Cell currentCell = currentRow.getCell(0);
                Integer year = 0;
                if (currentCell.getCellType() == CellType.NUMERIC) {
                	year = (int)currentCell.getNumericCellValue();
                }
                int month = 0;
                currentCell = currentRow.getCell(1);
                if (currentCell.getCellType() == CellType.NUMERIC) {
                	month = (int)currentCell.getNumericCellValue();
                }
                currentCell = currentRow.getCell(2);
                String domainCode = StringPool.BLANK;
                if (currentCell.getCellType() == CellType.STRING) {
                	domainCode = currentCell.getStringCellValue();
                }
                currentCell = currentRow.getCell(3);
                String domainName = StringPool.BLANK;
                if (currentCell.getCellType() == CellType.STRING) {
                	domainName = currentCell.getStringCellValue();
                }
                int receivedCount = 0;
                int ontimeCount = 0;
                int overdueCount = 0;
                int fromViaPostalCount = 0;
                currentCell = currentRow.getCell(4);
                CellValue cellValue = evaluator.evaluate(currentCell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                	receivedCount = (int)cellValue.getNumberValue();
                }
                currentCell = currentRow.getCell(5);
                cellValue = evaluator.evaluate(currentCell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                	ontimeCount = (int)cellValue.getNumberValue();
                }
                currentCell = currentRow.getCell(6);
                cellValue = evaluator.evaluate(currentCell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                	overdueCount = (int)cellValue.getNumberValue();
                }
                currentCell = currentRow.getCell(7);
                cellValue = evaluator.evaluate(currentCell);
                int onlineCount = 0;
                int releaseCount = 0;
                
                if (cellValue.getCellType() == CellType.NUMERIC) {
                	onlineCount = (int)cellValue.getNumberValue();
                }
                
                currentCell = currentRow.getCell(8);
                cellValue = evaluator.evaluate(currentCell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                	releaseCount = (int)cellValue.getNumberValue();
                }
                
                currentCell = currentRow.getCell(9);
                cellValue = evaluator.evaluate(currentCell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                	fromViaPostalCount = (int)cellValue.getNumberValue();
                }
                OpencpsDossierStatisticManual manual = OpencpsDossierStatisticManualLocalServiceUtil.updateStatisticManual(0l, 0, groupId, 0, StringPool.BLANK, month, year, 0, 0, 0, 0, 0, receivedCount, onlineCount, releaseCount, 0, ontimeCount, 0, 0, 0, 0, 0, 0, overdueCount, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, StringPool.BLANK, StringPool.BLANK, domainCode, domainName, false, 0, 0, 0, fromViaPostalCount);
                List<OpencpsDossierStatisticManual> lstManual = new ArrayList<OpencpsDossierStatisticManual>();
                if (mapStatistics.containsKey(year)) {
                	lstManual = mapStatistics.get(year);
                }
                else {
                	mapStatistics.put(year, lstManual);
                }
                lstManual.add(manual);
            }

            for (Integer keyYear : mapStatistics.keySet()) {
            	List<OpencpsDossierStatisticManual> lstManuals = mapStatistics.get(keyYear);
                int receivedCount = 0;
                int ontimeCount = 0;
                int overdueCount = 0;
                int onlineCount = 0;
                int releaseCount = 0;
                for (OpencpsDossierStatisticManual manual : lstManuals) {
                	receivedCount += manual.getReceivedCount();
                	ontimeCount += manual.getOntimeCount();
                	overdueCount += manual.getOverdueCount();
                	onlineCount += manual.getOnlineCount();
                	releaseCount += manual.getReleaseCount();
                }
                
                OpencpsDossierStatisticManualLocalServiceUtil.updateStatisticManual(0l, 0, groupId, 0, StringPool.BLANK, 0, keyYear, 0, 0, 0, 0, 0, receivedCount, onlineCount, releaseCount, 0, ontimeCount, 0, 0, 0, 0, 0, 0, overdueCount, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, false, 0, 0, 0, 0);
            }
    		return Response.status(200).entity(DossierConstants.JSON_SUCCESS_TRUE_EMPTY).build();
		}
		catch (Exception e) {
			LOG.error("error", e);
			return Response.status(200).entity(DossierConstants.JSON_SUCCESS_FALSE_EMPTY).build();
		}
	}
	
	@GET
	@Path("/manual")
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	public Response searchDossierStatisticManual(@HeaderParam("groupId") long groupId,
			@BeanParam DossierSearchModel query) {
		CacheControl cc = new CacheControl();
	    cc.setMaxAge(60);
	    cc.setPrivate(true);
	    
		int start = query.getStart();
		int end = query.getEnd();
		int month = query.getMonth();
		int year = query.getYear();
		String govAgencyCode = query.getAgency();
		String domain = query.getDomain();
		String system = query.getSystem();
		if (Validator.isNull(system)) {
			system = DossierConstants.ZERO;
		}
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
		} else {
			try {
				if (reCalculate == 1) {
					Date firstDay = StatisticUtils.getFirstDay(month, year);
					Date lastDay = StatisticUtils.getLastDay(month, year);
					processUpdateDB(groupId, firstDay, lastDay, month, year, true, new ArrayList<String>());
				}

				validInput(month, year, start, end);
				//
				DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();
				dossierStatisticRequest.setDomain(domain);
				if (DossierConstants.ALL.equals(govAgencyCode)) {
					dossierStatisticRequest.setGovAgencyCode(StringPool.BLANK);
				} else {
					dossierStatisticRequest.setGovAgencyCode(govAgencyCode);
				}
				dossierStatisticRequest.setSystem(system);
				dossierStatisticRequest.setGroupAgencyCode(groupAgencyCode);
				//dossierStatisticRequest.setReporting(reporting);
				dossierStatisticRequest.setGroupId(groupId);
				dossierStatisticRequest.setStart(start);
				dossierStatisticRequest.setEnd(end);
				dossierStatisticRequest.setMonth(month);
				dossierStatisticRequest.setYear(year);
				dossierStatisticRequest.setSystem(DossierConstants.SYSTEM_1);
				//
				DossierStatisticResponse statisticResponse = dossierStatisticManualFinderService
						.finderDossierStatisticSystem(dossierStatisticRequest);
				if (statisticResponse != null) {
					statisticResponse.setAgency(govAgencyCode);
				}

				ResponseBuilder builder = Response.ok(statisticResponse);
			    builder.cacheControl(cc);
			    return builder.build();
			} catch (Exception e) {
				LOG.error("error", e);
				OpencpsServiceExceptionDetails serviceExceptionDetails = new OpencpsServiceExceptionDetails();

				serviceExceptionDetails.setFaultCode(String.valueOf(HttpURLConnection.HTTP_INTERNAL_ERROR));
				serviceExceptionDetails.setFaultMessage(e.getMessage());

				throwException(new OpencpsServiceException(serviceExceptionDetails));
			}
		}

		return null;
	}	
	
	@GET
	@Path("/feedetail")
	public Response feeDetail(@HeaderParam("groupId") long groupId,
			@QueryParam("govAgencyCode") String govAgencyCode,
			@QueryParam("fromStatisticDate") String fromStatisticDate,
			@QueryParam("toStatisticDate") String toStatisticDate,
			@QueryParam("start") int start,
			@QueryParam("end") int end,
			@QueryParam("paymentStatus") int paymentStatus) {
		DossierActions actions = new DossierActionsImpl();
		Sort[] sorts = null;
		sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
				true) };
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		if (groupId > 0) {
			params.put(Field.GROUP_ID, String.valueOf(groupId));			
		}
		else {
			params.put(Field.GROUP_ID, StringPool.BLANK);
		}
		String from = APIDateTimeUtils.convertNormalDateToLuceneDate(fromStatisticDate);
		String to = APIDateTimeUtils.convertNormalDateToLuceneDate(toStatisticDate);
			
		if (Validator.isNotNull(govAgencyCode)) {
			params.put(DossierTerm.AGENCYS, govAgencyCode);
		}
		if (Validator.isNotNull(fromStatisticDate)) {
			params.put(DossierTerm.FROM_STATISTIC_DATE, from);
		}
		if (Validator.isNotNull(toStatisticDate)) {
			params.put(DossierTerm.TO_STATISTIC_DATE, to);
		}				
		//Add common params
		String strSystemId = DossierStatisticConstants.ALL_SYSTEM;
		params.put(DossierTerm.SYSTEM_ID, strSystemId);
		params.put(DossierTerm.TOP, DossierStatisticConstants.TOP_STATISTIC);
		
		Company company;
		try {
			company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			long companyId = company.getCompanyId(); 
			int startOff = QueryUtil.ALL_POS;
			int endOff = QueryUtil.ALL_POS;
			
			if (start != 0) {
				startOff = start;			
			}
			else {
			}
			if (end != 0) {
				endOff = end;
			}
			else {
			}
			JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, startOff, endOff, new ServiceContext());
			List<Document> datas = (List<Document>) jsonData.get(ConstantUtils.DATA);
			List<GetDossierData> dossierData = new ArrayList<>();
			int total = jsonData.getInt(ConstantUtils.TOTAL);
			Map<String, Map<String, List<Document>>> mapResults = new HashMap<String, Map<String,List<Document>>>();
			Map<String, String> domains = new HashMap<String, String>();
			Map<String, String> services = new HashMap<String, String>();
			
			for (Document doc : datas) {
				String domainCode = doc.get(DossierTerm.DOMAIN_CODE);
				String domainName = doc.get(DossierTerm.DOMAIN_NAME);
				
				String serviceCode = doc.get(DossierTerm.SERVICE_CODE);
				String serviceName = doc.get(DossierTerm.SERVICE_NAME);
				
				if (!domains.containsKey(domainCode)) {
					domains.put(domainCode, domainName);
				}
				if (!services.containsKey(serviceCode)) {
					services.put(serviceCode, serviceName);
				}
				if (mapResults.get(domainCode) != null) {
					Map<String, List<Document>> mapDomains = mapResults.get(domainCode);
					List<Document> lstDossiers = null;
					if (mapDomains.containsKey(serviceCode)) {
						lstDossiers = mapDomains.get(serviceCode);
					}
					else {
						lstDossiers = new ArrayList<Document>();
						mapDomains.put(serviceCode, lstDossiers);
					}
					lstDossiers.add(doc);
				}
				else {
					Map<String, List<Document>> mapDomains = new HashMap<String, List<Document>>();
					List<Document> lstDossiers = new ArrayList<Document>();
					mapDomains.put(serviceCode, lstDossiers);
					lstDossiers.add(doc);
					mapResults.put(domainCode, mapDomains);
				}
			}
			List<PaymentFile> lstPfs = null;
			if (groupId > 0) {
				if (paymentStatus != -1) {
					lstPfs = PaymentFileLocalServiceUtil.findByG_PT(groupId, paymentStatus);
				}
				else {
					lstPfs = PaymentFileLocalServiceUtil.findByG(groupId);
				}				
			}
			else {
				if (paymentStatus != -1) {
					lstPfs = PaymentFileLocalServiceUtil.findByPT(paymentStatus);
				}
				else {
					lstPfs = PaymentFileLocalServiceUtil.findAll();
				}								
			}
			Map<String, PaymentFile> mapPfs = new HashMap<String, PaymentFile>();
			for (PaymentFile pf : lstPfs) {
				mapPfs.put(String.valueOf(pf.getDossierId()), pf);
			}
			
			JSONArray results = JSONFactoryUtil.createJSONArray();
			for (String domainCode : mapResults.keySet()) {
//				JSONObject groupDomainObj = JSONFactoryUtil.createJSONObject();
//				groupDomainObj.put("domain", domains.get(domainCode));
//				JSONArray serviceArr = JSONFactoryUtil.createJSONArray();
//				JSONArray groupDomainArr = JSONFactoryUtil.createJSONArray();
				
				for (String serviceCode : mapResults.get(domainCode).keySet()) {
//					JSONObject serviceObj = JSONFactoryUtil.createJSONObject();
//					serviceObj.put("service", services.get(serviceCode));
//					JSONArray dossierArr = JSONFactoryUtil.createJSONArray();
					int count = 1;
					for (Document doc : mapResults.get(domainCode).get(serviceCode)) {
						String dossierId = doc.get(DossierTerm.DOSSIER_ID);
						if (mapPfs.containsKey(dossierId)) {
							JSONObject dossierObj = JSONFactoryUtil.createJSONObject();
							dossierObj.put("no", count++);
							dossierObj.put("dossierNo", doc.get(DossierTerm.DOSSIER_NO));
							dossierObj.put("applicantName", doc.get(DossierTerm.APPLICANT_NAME));
							String address = doc.get(DossierTerm.ADDRESS) + " " + doc.get(DossierTerm.WARD_NAME + " " + doc.get(DossierTerm.DISTRICT_NAME + " " + doc.get(DossierTerm.CITY_NAME)));
							dossierObj.put("address", address);
							PaymentFile pf = mapPfs.get(dossierId);
							String paymentDate = APIDateTimeUtils.convertDateToString(pf.getModifiedDate(), APIDateTimeUtils._NORMAL_DATE_TIME);
							dossierObj.put("paymentDate", paymentDate);
							dossierObj.put("paymentFee", pf.getFeeAmount());
							dossierObj.put("paymentAmount", pf.getPaymentAmount());
							dossierObj.put("totalAmount", (pf.getFeeAmount() + pf.getPaymentAmount()));
//							dossierArr.put(dossierObj);			
							dossierObj.put("domainCode", domainCode);
							dossierObj.put("domainName", domains.get(domainCode));
							dossierObj.put("serviceCode", serviceCode);
							dossierObj.put("serviceName", services.get(serviceCode));
							
							results.put(dossierObj);
						}
					}
//					if (dossierArr.length() > 0) {
//						serviceObj.put("data", dossierArr);						
//						serviceArr.put(serviceObj);
//					}
				}
//				if (serviceArr.length() > 0) {
//					groupDomainObj.put("data", serviceArr);					
//					results.put(groupDomainObj);
//				}
			}
			ResponseBuilder builder = Response.ok(results.toJSONString());
			return builder.build();
		} catch (PortalException e) {
			_log.debug(e);
		}
		
		ResponseBuilder builder = Response.ok("");
		return builder.build();
	}
	
	@GET
	@Path("/feesummary")
	public Response feeReportSummary(@HeaderParam("groupId") long groupId,
			@QueryParam("govAgencyCode") String govAgencyCode,
			@QueryParam("fromStatisticDate") String fromStatisticDate,
			@QueryParam("toStatisticDate") String toStatisticDate,
			@QueryParam("paymentStatus") int paymentStatus,
			@QueryParam("type") String type) {
		DossierActions actions = new DossierActionsImpl();
		Sort[] sorts = null;
		sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
				true) };
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		if (groupId > 0) {
			params.put(Field.GROUP_ID, String.valueOf(groupId));			
		}
		else {
			params.put(Field.GROUP_ID, StringPool.BLANK);
		}
		String from = APIDateTimeUtils.convertNormalDateToLuceneDate(fromStatisticDate);
		String to = APIDateTimeUtils.convertNormalDateToLuceneDate(toStatisticDate);
			
		if (Validator.isNotNull(govAgencyCode)) {
			params.put(DossierTerm.AGENCYS, govAgencyCode);
		}
		if (Validator.isNotNull(fromStatisticDate)) {
			params.put(DossierTerm.FROM_STATISTIC_DATE, from);
		}
		if (Validator.isNotNull(toStatisticDate)) {
			params.put(DossierTerm.TO_STATISTIC_DATE, to);
		}				
		//Add common params
		String strSystemId = DossierStatisticConstants.ALL_SYSTEM;
		params.put(DossierTerm.SYSTEM_ID, strSystemId);
		params.put(DossierTerm.TOP, DossierStatisticConstants.TOP_STATISTIC);
		
		Company company;
		try {
			company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			long companyId = company.getCompanyId(); 
			int startOff = QueryUtil.ALL_POS;
			int endOff = QueryUtil.ALL_POS;
			
			JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, startOff, endOff, new ServiceContext());
			List<Document> datas = (List<Document>) jsonData.get(ConstantUtils.DATA);
			List<GetDossierData> dossierData = new ArrayList<>();
			int total = jsonData.getInt(ConstantUtils.TOTAL);
			Map<String, Map<String, List<Document>>> mapResults = new HashMap<String, Map<String,List<Document>>>();
			Map<String, String> domains = new HashMap<String, String>();
			Map<String, String> services = new HashMap<String, String>();
			
			for (Document doc : datas) {
				String domainCode = doc.get(DossierTerm.DOMAIN_CODE);
				String domainName = doc.get(DossierTerm.DOMAIN_NAME);
				
				String serviceCode = doc.get(DossierTerm.SERVICE_CODE);
				String serviceName = doc.get(DossierTerm.SERVICE_NAME);
				
				if (!domains.containsKey(domainCode)) {
					domains.put(domainCode, domainName);
				}
				if (!services.containsKey(serviceCode)) {
					services.put(serviceCode, serviceName);
				}
				if (mapResults.get(domainCode) != null) {
					Map<String, List<Document>> mapDomains = mapResults.get(domainCode);
					List<Document> lstDossiers = null;
					if (mapDomains.containsKey(serviceCode)) {
						lstDossiers = mapDomains.get(serviceCode);
					}
					else {
						lstDossiers = new ArrayList<Document>();
						mapDomains.put(serviceCode, lstDossiers);
					}
					lstDossiers.add(doc);
				}
				else {
					Map<String, List<Document>> mapDomains = new HashMap<String, List<Document>>();
					List<Document> lstDossiers = new ArrayList<Document>();
					mapDomains.put(serviceCode, lstDossiers);
					lstDossiers.add(doc);
					mapResults.put(domainCode, mapDomains);
				}
			}
			List<PaymentFile> lstPfs = null;
			if (groupId > 0) {
				if (paymentStatus != -1) {
					lstPfs = PaymentFileLocalServiceUtil.findByG_PT(groupId, paymentStatus);
				}
				else {
					lstPfs = PaymentFileLocalServiceUtil.findByG(groupId);
				}
			}
			else {
				if (paymentStatus != -1) {
					lstPfs = PaymentFileLocalServiceUtil.findByPT(paymentStatus);
				}
				else {
					lstPfs = PaymentFileLocalServiceUtil.findAll();
				}								
			}
			
			Map<String, PaymentFile> mapPfs = new HashMap<String, PaymentFile>();
			for (PaymentFile pf : lstPfs) {
				mapPfs.put(String.valueOf(pf.getDossierId()), pf);
			}
			
			JSONArray results = JSONFactoryUtil.createJSONArray();
			
			if (Validator.isNotNull(type) && "service".contentEquals(type)) {
				for (String domainCode : mapResults.keySet()) {
//					JSONObject groupDomainObj = JSONFactoryUtil.createJSONObject();
//					groupDomainObj.put("domain", domains.get(domainCode));
//					JSONArray serviceArr = JSONFactoryUtil.createJSONArray();
					
					for (String serviceCode : mapResults.get(domainCode).keySet()) {
//						JSONObject serviceObj = JSONFactoryUtil.createJSONObject();
//						serviceObj.put("service", serviceCode + " - " + services.get(serviceCode));
						int count = 0;
						long totalFee = 0;
						long totalPaymentAmount = 0;
						
						for (Document doc : mapResults.get(domainCode).get(serviceCode)) {
							String dossierId = doc.get(DossierTerm.DOSSIER_ID);
							if (mapPfs.containsKey(dossierId)) {
								count++;
								PaymentFile pf = mapPfs.get(dossierId);
								totalFee += pf.getFeeAmount();
								totalPaymentAmount += pf.getPaymentAmount();
							}
						}
						if (count > 0) {
//							JSONArray paymentArr = JSONFactoryUtil.createJSONArray();
							JSONObject paymentObj = JSONFactoryUtil.createJSONObject();
							paymentObj.put("no", 1);
							paymentObj.put("serviceName", serviceCode + " - " + services.get(serviceCode));
							paymentObj.put("totalDossier", count);
							paymentObj.put("totalFeeAmount", totalFee);
							paymentObj.put("totalPaymentAmount", totalPaymentAmount);
							paymentObj.put("totalAmount", totalFee + totalPaymentAmount);
//							paymentArr.put(paymentObj);
//							serviceObj.put("data", paymentArr);						
//							serviceArr.put(serviceObj);	
							paymentObj.put("domainCode", domainCode);
							paymentObj.put("domainName", domains.get(domainCode));
							paymentObj.put("serviceCode", serviceCode);
							paymentObj.put("serviceName", services.get(serviceCode));
							
							results.put(paymentObj);
						}
					}
//					if (serviceArr.length() > 0) {
//						groupDomainObj.put("data", serviceArr);					
//						results.put(groupDomainObj);
//					}
				}				
			}
			else {
				for (String domainCode : mapResults.keySet()) {
					JSONObject groupDomainObj = JSONFactoryUtil.createJSONObject();
					groupDomainObj.put("domain", domains.get(domainCode));
					groupDomainObj.put("domainCode", domainCode);
					int count = 0;
					long totalFee = 0;
					long totalPaymentAmount = 0;
					
					for (String serviceCode : mapResults.get(domainCode).keySet()) {
						
						for (Document doc : mapResults.get(domainCode).get(serviceCode)) {
							String dossierId = doc.get(DossierTerm.DOSSIER_ID);
							if (mapPfs.containsKey(dossierId)) {
								count++;
								PaymentFile pf = mapPfs.get(dossierId);
								totalFee += pf.getFeeAmount();
								totalPaymentAmount += pf.getPaymentAmount();
							}
						}
					}
					if (count > 0) {
						groupDomainObj.put("totalDossier", count);
						groupDomainObj.put("totalFeeAmount", totalFee);
						groupDomainObj.put("totalPaymentAmount", totalPaymentAmount);
						groupDomainObj.put("totalAmount", totalFee + totalPaymentAmount);
						results.put(groupDomainObj);
					}
				}								
			}
			ResponseBuilder builder = Response.ok(results.toJSONString());
			return builder.build();
		} catch (PortalException e) {
			_log.debug(e);
		}
		
		ResponseBuilder builder = Response.ok("");
		return builder.build();
	}	
	
	@GET
	@Path("/summary")
	public Response feeReportSummary(@HeaderParam("groupId") long groupId,
			@QueryParam("govAgencyCode") String govAgencyCode,
			@QueryParam("fromStatisticDate") String fromStatisticDate,
			@QueryParam("toStatisticDate") String toStatisticDate) {
		DossierActions actions = new DossierActionsImpl();
		Sort[] sorts = null;
		sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
				true) };
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		if (groupId > 0) {
			params.put(Field.GROUP_ID, String.valueOf(groupId));			
		}
		else {
			params.put(Field.GROUP_ID, StringPool.BLANK);
		}
		String from = APIDateTimeUtils.convertNormalDateToLuceneDate(fromStatisticDate);
		String to = APIDateTimeUtils.convertNormalDateToLuceneDate(toStatisticDate);
			
		if (Validator.isNotNull(govAgencyCode)) {
			params.put(DossierTerm.AGENCYS, govAgencyCode);
		}
		if (Validator.isNotNull(fromStatisticDate)) {
			params.put(DossierTerm.FROM_STATISTIC_DATE, from);
		}
		if (Validator.isNotNull(toStatisticDate)) {
			params.put(DossierTerm.TO_STATISTIC_DATE, to);
		}				
		//Add common params
		String strSystemId = DossierStatisticConstants.ALL_SYSTEM;
		params.put(DossierTerm.SYSTEM_ID, strSystemId);
		params.put(DossierTerm.TOP, DossierStatisticConstants.TOP_STATISTIC);
		Date fromDate = APIDateTimeUtils.convertStringToDate(fromStatisticDate + " 00:00:00", APIDateTimeUtils._NORMAL_DATE_TIME);
		Date toDate = APIDateTimeUtils.convertStringToDate(toStatisticDate + " 00:00:00", APIDateTimeUtils._NORMAL_DATE_TIME);
		Company company;
		try {
			company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			long companyId = company.getCompanyId(); 
			int startOff = QueryUtil.ALL_POS;
			int endOff = QueryUtil.ALL_POS;
			
			JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, startOff, endOff, new ServiceContext());
			List<Document> datas = (List<Document>) jsonData.get(ConstantUtils.DATA);
			List<GetDossierData> dossierData = new ArrayList<>();
			int total = jsonData.getInt(ConstantUtils.TOTAL);
			Map<String, Map<String, List<Document>>> mapResults = new HashMap<String, Map<String,List<Document>>>();
			Map<String, String> govs = new HashMap<String, String>();
			Map<String, String> services = new HashMap<String, String>();
			
			for (Document doc : datas) {
				String agency = doc.get(DossierTerm.GOV_AGENCY_CODE);
				String agencyName = doc.get(DossierTerm.GOV_AGENCY_NAME);
				
				String serviceCode = doc.get(DossierTerm.SERVICE_CODE);
				String serviceName = doc.get(DossierTerm.SERVICE_NAME);
				
				if (!govs.containsKey(agency)) {
					govs.put(agency, agencyName);
				}
				if (!services.containsKey(serviceCode)) {
					services.put(serviceCode, serviceName);
				}
				if (mapResults.get(agency) != null) {
					Map<String, List<Document>> mapGovs = mapResults.get(agency);
					List<Document> lstDossiers = null;
					if (mapGovs.containsKey(serviceCode)) {
						lstDossiers = mapGovs.get(serviceCode);
					}
					else {
						lstDossiers = new ArrayList<Document>();
						mapGovs.put(serviceCode, lstDossiers);
					}
					lstDossiers.add(doc);
				}
				else {
					Map<String, List<Document>> mapGovs = new HashMap<String, List<Document>>();
					List<Document> lstDossiers = new ArrayList<Document>();
					mapGovs.put(serviceCode, lstDossiers);
					lstDossiers.add(doc);
					mapResults.put(agency, mapGovs);
				}
			}
			
			JSONArray results = JSONFactoryUtil.createJSONArray();
			
				for (String agency : mapResults.keySet()) {
					JSONObject obj = JSONFactoryUtil.createJSONObject();
					
//					JSONObject groupDomainObj = JSONFactoryUtil.createJSONObject();
//					groupDomainObj.put("domain", domains.get(domainCode));
//					JSONArray serviceArr = JSONFactoryUtil.createJSONArray();
					obj.put("govAgencyCode", agency);
					obj.put("govAgencyName", govs.get(agency));
					
					for (String serviceCode : mapResults.get(agency).keySet()) {
//						JSONObject serviceObj = JSONFactoryUtil.createJSONObject();
//						serviceObj.put("service", serviceCode + " - " + services.get(serviceCode));
						RealtimeData data = new RealtimeData();
						data.setServiceCode(serviceCode);
						data.setServiceName(services.get(serviceCode));
						
						List<Document> dossiers = mapResults.get(agency).get(serviceCode);
						updateDossierStatisticData(data, dossiers, fromDate, toDate);
						
						obj.put("serviceCode", serviceCode);
						obj.put("serviceName", services.get(serviceCode));
						obj.put("totalCount", data.getTotalCount());
						obj.put("onegateCount", data.getOnegateCount());
						obj.put("onlineCount", data.getOnlineCount());
						obj.put("fromViaPostalCount", data.getFromViaPostalCount());
						obj.put("releaseCount", data.getReleaseCount());
						obj.put("doneCount", data.getDoneCount());
						obj.put("doneOnegateCount", data.getDoneOnegateCount());
						obj.put("doneViaPostalCount", data.getDoneViaPostalCount());
					}
					results.put(obj);
//					if (serviceArr.length() > 0) {
//						groupDomainObj.put("data", serviceArr);					
//						results.put(groupDomainObj);
//					}
				}				
			ResponseBuilder builder = Response.ok(results.toJSONString());
			return builder.build();
		} catch (PortalException e) {
			_log.debug(e);
		}	

		ResponseBuilder builder = Response.ok("");
		return builder.build();	
	}
	
	public void updateDossierStatisticData(RealtimeData statisticData, List<Document> dossiers,
			Date fromStatisticDate, Date toStatisticDate) {
//		int month = LocalDate.now().getMonthValue();
		//int year = LocalDate.now().getYear();
		Calendar dateStatistic = Calendar.getInstance();
		dateStatistic.setTime(fromStatisticDate);
		for (Document dossierData : dossiers) {
			Date dueDate = Validator.isNull(dossierData.get(DossierTerm.DUE_DATE))
					? null
					: APIDateTimeUtils.convertStringToDate(dossierData.get(DossierTerm.CREATE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
			Date extendDate = Validator.isNull(dossierData.get(DossierTerm.EXTEND_DATE))
					? null
					: APIDateTimeUtils.convertStringToDate(dossierData.get(DossierTerm.EXTEND_DATE), APIDateTimeUtils._NORMAL_DATE_TIME);
			Date releaseDate = Validator.isNull(dossierData.get(DossierTerm.RELEASE_DATE))
					? null
					: APIDateTimeUtils.convertStringToDate(dossierData.get(DossierTerm.RELEASE_DATE), APIDateTimeUtils._NORMAL_DATE_TIME);
			Date receviedDate = Validator.isNull(dossierData.get(DossierTerm.RECEIVE_DATE))
					? null
					: APIDateTimeUtils.convertStringToDate(dossierData.get(DossierTerm.RECEIVE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
			Date finishDate = Validator.isNull(dossierData.get(DossierTerm.FINISH_DATE))
					? null
					: APIDateTimeUtils.convertStringToDate(dossierData.get(DossierTerm.FINISH_DATE), APIDateTimeUtils._NORMAL_DATE_TIME);
			int viaPostal = GetterUtil.getInteger(dossierData.get(DossierTerm.VIA_POSTAL));
//			if (viaPostal != 0)
//				_log.info("VIA POSTAL STATISTIC: " + viaPostal);
			if (viaPostal == USED_POSTAL) {
				statisticData.setViaPostalCount(statisticData.getViaPostalCount() + 1);
			}
			else {
				
			}
			boolean online = GetterUtil.getBoolean(dossierData.get(DossierTerm.ONLINE));
			int fromViaPostal = GetterUtil.getInteger(dossierData.get(DossierTerm.FROM_VIA_POSTAL));
			
			if (!online && fromViaPostal > 0) {
				statisticData.setFromViaPostalCount(statisticData.getFromViaPostalCount() + 1);
			}
			Calendar receivedStatistic = Calendar.getInstance();
			receivedStatistic.setTime(receviedDate);
			boolean isReceivedSaturday = (receivedStatistic.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
			Calendar finishStatistic = Calendar.getInstance();
			finishStatistic.setTime(receviedDate);
			boolean isFinishSaturday = (receivedStatistic.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
			if (isReceivedSaturday && isFinishSaturday) {
				statisticData.setSaturdayCount(statisticData.getSaturdayCount() + 1);
			}
			if (isReceivedSaturday) {
				statisticData.setReceiveDossierSatCount(statisticData.getReceiveDossierSatCount() + 1);
			}
			if (isFinishSaturday) {
				statisticData.setReleaseDossierSatCount(statisticData.getReleaseDossierSatCount() + 1);
			}
			int serviceLevel = GetterUtil.getInteger(dossierData.get(DossierTerm.SERVICE_LEVEL));
			if (LEVEL_3 == serviceLevel) {
				statisticData.setDossierOnline3Count(statisticData.getDossierOnline3Count() + 1);
			}
			if (LEVEL_4 == serviceLevel) {
				statisticData.setDossierOnline4Count(statisticData.getDossierOnline4Count() + 1);
			}
			//
			statisticData.setTotalCount(statisticData.getTotalCount() + 1);
			String dossierStatus = dossierData.get(DossierTerm.DOSSIER_STATUS);
			if (dossierStatus.contentEquals(DENIED)) {
				statisticData.setDeniedCount(statisticData.getDeniedCount() + 1);				
			} else {
				// tiep nhan xu ly
				statisticData.setProcessCount(statisticData.getProcessCount() + 1);

				//if (receviedDate != null && receviedDate.after(getFirstDay(month, year))) {
				if (receviedDate != null && receviedDate.after(fromStatisticDate)) {
					// trong ky
					statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
					if (online) {
						statisticData.setOnlineCount(statisticData.getOnlineCount() + 1);
					} else {
						statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
					}
				} else {
					// ton ky truoc
					statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);
				}
				
				if (releaseDate == null || releaseDate.after(toStatisticDate)) {
					// hồ sơ đang xử lý 
					if (dossierStatus.contentEquals(WAITING) || 
							dossierStatus.contentEquals(RECEIVING)) {
						// dang tạm dừng chờ bổ sung
						statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
					} else {
						// đang xử lý
						statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);
						if (!PROCESSING.equals(dossierStatus)) {
							// xử lý nội bộ
							statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
						} else {
							// xử lý ngoài cơ quan
							statisticData.setInsideCount(statisticData.getInsideCount() + 1);
						}

						Date now = new Date();
						if (dueDate != null && !dueDate.after(now.before(toStatisticDate) ? now : toStatisticDate)) {
							// đang quá hạn
							statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
							if (!PROCESSING.equals(dossierStatus)) {
								// đang quá hạn và xử lý bên ngoài 
								statisticData.setInteroperatingCount(statisticData.getInteroperatingCount() + 1);
							}
						} else {
							// đang xử lý còn hạn
							statisticData.setUndueCount(statisticData.getUndueCount() + 1);
						}
					}
				} else {
					// ho so da ket thuc trong thang	
					if (dossierStatus.contentEquals(CANCELLED)) {
						// ho so da bi rut trong thang
						statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);
					} else {
						// hồ sơ đã hoàn thành trong tháng	
						statisticData.setReleaseCount(statisticData.getReleaseCount() + 1);						
						if (dossierStatus.contentEquals(UNRESOLVED)) {
							// từ chối giải quyết => không tính hạn xử lý
							statisticData.setUnresolvedCount(statisticData.getUnresolvedCount() + 1);
						} else { 
							if (finishDate != null) {
								// số đã trả kết quả
								statisticData.setDoneCount(statisticData.getDoneCount() + 1);	
								if (viaPostal == USED_POSTAL) {
									statisticData.setDoneViaPostalCount(statisticData.getDoneViaPostalCount() + 1);
								}
								else {
									statisticData.setDoneOnegateCount(statisticData.getDoneOnegateCount() + 1);
								}
							} else {
								statisticData.setReleasingCount(statisticData.getReleasingCount() + 1);
							}
						}

						// hồ sơ có kết quả hoặc từ chối tính hạn xử lý
						int overdue = 1; // 0: sớm hạn, 1: đúng hạn, 2: quá hạn
						// Check condition filter betimes
						if (dueDate != null) {
							//Check extendDate != null and releaseDate < dueDate
							if (releaseDate != null && releaseDate.before(dueDate) && extendDate != null) overdue = 0;
							//Or check finishDate < dueDate
							if (finishDate != null && finishDate.before(dueDate)) overdue = 0;
							
							//Check overTime condition releaseDate > dueDate
							if (releaseDate != null && releaseDate.after(dueDate)) overdue = 2;
						}

						if (overdue==0) {
							statisticData.setBetimesCount(statisticData.getBetimesCount() + 1);
						} else if (overdue==2) {
							statisticData.setOvertimeCount(statisticData.getOvertimeCount() + 1);
							boolean isOvertimeInside = true;
							if (isOvertimeInside) {
								statisticData.setOvertimeInside(statisticData.getOvertimeInside() + 1);
							} else {
								statisticData.setOvertimeOutside(statisticData.getOvertimeOutside() + 1);
							}
						} else {
							statisticData.setOntimeCount(statisticData.getOntimeCount() + 1);
						}
					}
				}
			}			
		}
	}

	private static final int LEVEL_3 = 3;
	private static final int LEVEL_4 = 4;
	private static final int USED_POSTAL = 2;
	public static final String DENIED = "denied";
	public static final String WAITING = "waiting";
	public static final String RECEIVING = "receiving";
	public static final String PROCESSING = "processing";
	public static final String CANCELLED = "cancelled";
	public static final String UNRESOLVED = "unresolved";

}