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
import java.text.DateFormat;
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
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
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
import org.opencps.statistic.rest.fee.model.FeeSearchModel;
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
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;
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
//@Component( 
//property = { 
//    JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/rest/statistics", 
//    JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.reststatistics"
//}, 
//service = Application.class)
@Consumes("application/json")
@Produces("application/json")
public class OpencpsStatisticRestApplication_bk extends Application {

	private final static Logger LOG = LoggerFactory.getLogger(OpencpsStatisticRestApplication_bk.class);

	private DossierStatisticFinderService dossierStatisticFinderService = new DossierStatisticFinderServiceImpl();
	private DossierStatisticManualFinderService dossierStatisticManualFinderService = new DossierStatisticManualFinderServiceImpl();

	public static final String ALL_MONTH = "-1";

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	private static Log _log = LogFactoryUtil.getLog(OpencpsStatisticRestApplication_bk.class);

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
					//dossierResponse = callDossierRestService.callRestService(payload);
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
					params.put(DossierTerm.DOMAIN_CODE, domain);
					
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
								0);
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

				//throwException(new OpencpsServiceException(serviceExceptionDetails));
			}
		} else {
			try {
//				if (reCalculate == 1) {
//					Date firstDay = StatisticUtils.getFirstDay(month, year);
//					Date lastDay = StatisticUtils.getLastDay(month, year);
//					processUpdateDB(groupId, firstDay, lastDay, month, year, true, new ArrayList<String>());
//				}

				//validInput(month, year, start, end);
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
					_log.info("START DossierStatisticResponse: "+query.getAgency());
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

				//throwException(new OpencpsServiceException(serviceExceptionDetails));
			}
		}

		return null;
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
