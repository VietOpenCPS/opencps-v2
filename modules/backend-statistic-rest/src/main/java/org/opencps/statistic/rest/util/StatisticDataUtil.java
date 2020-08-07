package org.opencps.statistic.rest.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DomainResponse;
import org.opencps.statistic.rest.dto.GetPersonData;
import org.opencps.statistic.rest.dto.GetPersonRequest;
import org.opencps.statistic.rest.dto.GetPersonResponse;
import org.opencps.statistic.rest.dto.GetVotingResultData;
import org.opencps.statistic.rest.dto.GetVotingResultRequest;
import org.opencps.statistic.rest.dto.GetVotingResultResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.ServiceDomainData;
import org.opencps.statistic.rest.dto.ServiceDomainRequest;
import org.opencps.statistic.rest.dto.ServiceDomainResponse;
import org.opencps.statistic.rest.dto.VotingResultRequest;
import org.opencps.statistic.rest.dto.VotingResultResponse;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.feedback.action.VotingActions;
import backend.feedback.action.impl.VotingActionsImpl;
import backend.feedback.constants.VotingResultTerm;
import backend.feedback.constants.VotingTerm;

public class StatisticDataUtil {
	public static final String NUMBER_SORT_ABLE = "Number_sortable";
	public static final String TREE_INDEX_SORT_ABLE = "treeIndex_sortable";
	public static final String REPORT_TYPE = "reportType";
	public static final String YEAR = "year";
	public static final String MONTH = "month";
	public static final String DAY = "day";
	
	public static ServiceDomainResponse getLocalServiceDomain(ServiceDomainRequest payload) {
		ServiceDomainResponse response = new ServiceDomainResponse();

		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			
			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(company.getCompanyId());
			
			long groupId = GetterUtil.getLong(payload.getGroupId());
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ADMINISTRATIVE_REGION).equalsIgnoreCase(DossierStatisticConstants.SERVICE_DOMAIN_CODE))
				groupId = 0;

			params.put(Field.GROUP_ID, groupId);
			params.put(Field.KEYWORD_SEARCH, StringPool.BLANK);
			params.put(ConstantUtils.ITEM_LEVEL, StringPool.BLANK);
			params.put(DictItemTerm.PARENT_ITEM_CODE, StringPool.BLANK);
			params.put(DictItemTerm.DICT_COLLECTION_CODE, DossierStatisticConstants.SERVICE_DOMAIN_CODE);

			Sort[] sorts = null;
			
			sorts = new Sort[] {
				SortFactoryUtil.create(DictItemTerm.SIBLING_SEARCH + NUMBER_SORT_ABLE, Sort.INT_TYPE, false) };
			

			JSONObject jsonData = dictItemDataUtil.getDictItems(-1, company.getCompanyId(), groupId,
					params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());			
			response.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			List<Document> lstDocs = (List<Document>) jsonData.get(ConstantUtils.DATA);
			List<ServiceDomainData> lstSDatas = new ArrayList<>();
			for (Document doc : lstDocs) {
				ServiceDomainData sd = new ServiceDomainData();
				sd.setItemCode(doc.get(DictItemTerm.ITEM_CODE));
				sd.setItemName(doc.get(DictItemTerm.ITEM_NAME));				
				
				lstSDatas.add(sd);
			}
			response.setData(lstSDatas);
		}
		catch (Exception e) {
			_log.error(e);
		}
		return response;
	}
	
	
	public static GetPersonResponse getLocalPersonResponse(GetPersonRequest payload) {
		GetPersonResponse response = new GetPersonResponse();
		VotingActions actions = new VotingActionsImpl();

		long groupId = payload.getGroupId();
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
	
			if (payload.isCalculate()) {
				if (payload.getMonth() != null) {
					params.put(MONTH, payload.getMonth());
				}
				else {
					params.put(MONTH, Integer.toString(LocalDate.now().getMonthValue()));
				}
				if (payload.getYear() != null) {
					params.put(YEAR, payload.getYear());
				}
				else {
					params.put(YEAR, Integer.toString(LocalDate.now().getYear()));
				}
			} else {
				if (Validator.isNotNull(payload.getFromStatisticDate())) {
					params.put(VotingResultTerm.FROM_VOTING_DATE, payload.getFromStatisticDate());
				}
				if (Validator.isNotNull(payload.getToStatisticDate())) {
					params.put(VotingResultTerm.TO_VOTING_DATE, payload.getToStatisticDate());
				}
			}
	
			if (Validator.isNotNull(payload.getGovAgencyCode())) {
				params.put(VotingTerm.GOV_AGENCY_CODE, payload.getGovAgencyCode());
			}
	
			params.put(VotingTerm.CLASS_NAME, DossierStatisticConfig.get(DossierStatisticConstants.VOTING_CLASSNAME_EMPLOYEE));
						
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(TREE_INDEX_SORT_ABLE, Sort.STRING_TYPE, false) };
	
			JSONObject jsonData = actions.getVotingResultStatistic(-1, company.getCompanyId(), groupId, params,
					sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
	
			response.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			List<Document> lstDocs = (List<Document>) jsonData.get(ConstantUtils.DATA);
			List<GetPersonData> lstDatas = new ArrayList<>();
			for (Document doc : lstDocs) {
				GetPersonData data = new GetPersonData();
				data.setUserId(Long.valueOf(doc.get(VotingResultTerm.USER_ID)));
				data.setGroupId(Long.valueOf(doc.get(Field.GROUP_ID)));
				try {
					data.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(VotingResultTerm.CREATE_DATE),
							APIDateTimeUtils._NORMAL_PARTTERN));
					data.setModifiedDate(APIDateTimeUtils.convertDateToString(doc.getDate(Field.MODIFIED_DATE),
							APIDateTimeUtils._NORMAL_PARTTERN));
				} catch (Exception e) {
					_log.error(e);
				}
				data.setSelected(GetterUtil.get(doc.get(VotingResultTerm.SELECTED), 0));
				data.setClassName(doc.get(VotingTerm.CLASS_NAME));
				data.setClassPK(doc.get(VotingTerm.CLASS_PK));
				Long employeeId = GetterUtil.getLong(doc.get(VotingTerm.CLASS_PK));
				if (employeeId > 0) {
					Employee employee = EmployeeLocalServiceUtil.fetchEmployee(employeeId);
					if (employee != null) {
						data.setEmployeeId(employee.getEmployeeId());
						data.setEmployeeName(employee.getFullName());
					}
				}
				data.setGovAgencyCode(doc.get(VotingTerm.GOV_AGENCY_CODE));
				data.setGovAgencyName(doc.get(VotingTerm.GOV_AGENCY_NAME));
				data.setVotingCode(doc.get(VotingTerm.VOTING_CODE));
				data.setVotingSubject(doc.get(VotingTerm.VOTING_SUBJECT));
				
				lstDatas.add(data);
			}
			
			response.setData(lstDatas);
		}
		catch (Exception e) {
			_log.error(e);
		}
		return response;
	}
	
	public static GetVotingResultResponse getLocalVotingResponse(GetVotingResultRequest payload) {
		GetVotingResultResponse response = new GetVotingResultResponse();
		VotingActions actions = new VotingActionsImpl();

		long groupId = payload.getGroupId();
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
	
			if (payload.isCalculate()) {
				if (payload.getMonth() != null) {
					params.put(MONTH, payload.getMonth());
				}
				else {
					params.put(MONTH, Integer.toString(LocalDate.now().getMonthValue()));
				}
				if (payload.getYear() != null) {
					params.put(YEAR, payload.getYear());
				}
				else {
					params.put(YEAR, Integer.toString(LocalDate.now().getYear()));
				}
			} 
			else {
				if (Validator.isNotNull(payload.getFromVotingDate())) {
					params.put(VotingResultTerm.FROM_VOTING_DATE, payload.getFromVotingDate());
				}
				if (Validator.isNotNull(payload.getToVotingDate())) {
					params.put(VotingResultTerm.TO_VOTING_DATE, payload.getToVotingDate());
				}
			}
			params.put(VotingTerm.CLASS_NAME, DossierStatisticConfig.get(DossierStatisticConstants.VOTING_CLASSNAME_DOSSIER));
						
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(TREE_INDEX_SORT_ABLE, Sort.STRING_TYPE, false) };
	
			JSONObject jsonData = actions.getVotingResultStatistic(-1, company.getCompanyId(), groupId, params,
					sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
	
			response.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			List<Document> lstDocs = (List<Document>) jsonData.get(ConstantUtils.DATA);
			List<GetVotingResultData> lstDatas = new ArrayList<>();
			
			for (Document doc : lstDocs) {
				GetVotingResultData data = new GetVotingResultData();
				data.setUserId(Long.valueOf(doc.get(VotingResultTerm.USER_ID)));
				data.setGroupId(Long.valueOf(doc.get(Field.GROUP_ID)));
				try {
					data.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(VotingResultTerm.CREATE_DATE),
							APIDateTimeUtils._NORMAL_PARTTERN));
					data.setModifiedDate(APIDateTimeUtils.convertDateToString(doc.getDate(Field.MODIFIED_DATE),
							APIDateTimeUtils._NORMAL_PARTTERN));
				} catch (Exception e) {
					_log.error(e);
				}
				data.setSelected(GetterUtil.get(doc.get(VotingResultTerm.SELECTED), 0));
				data.setClassName(doc.get(VotingTerm.CLASS_NAME));
				data.setClassPK(doc.get(VotingTerm.CLASS_PK));
				data.setGovAgencyCode(doc.get(VotingTerm.GOV_AGENCY_CODE));
				data.setGovAgencyName(doc.get(VotingTerm.GOV_AGENCY_NAME));
				data.setVotingCode(doc.get(VotingTerm.VOTING_CODE));
				data.setVotingSubject(doc.get(VotingTerm.VOTING_SUBJECT));
				data.setDomain(doc.get(VotingTerm.DOMAIN_CODE));
				data.setDomainName(doc.get(VotingTerm.DOMAIN_NAME));
				
				lstDatas.add(data);
			}
			
			response.setData(lstDatas);
		}
		catch (Exception e) {
			_log.error(e);
		}
		return response;
	}	
	
	private static GovAgencyResponse localResponse = null;
	
	public static GovAgencyResponse getLocalGovAgency(GovAgencyRequest payload) {
//		GovAgencyResponse response = new GovAgencyResponse();
		//_log.info("groupId: "+GetterUtil.getLong(payload.getGroupId()));
		//_log.info("localResponse: "+JSONFactoryUtil.looseSerialize(localResponse));
		if (localResponse != null) return localResponse;
		localResponse = new GovAgencyResponse();
		
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			
			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(company.getCompanyId());
			
			long groupId = GetterUtil.getLong(payload.getGroupId());
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, groupId);
			params.put(Field.KEYWORD_SEARCH, StringPool.BLANK);
			params.put(ConstantUtils.ITEM_LEVEL, StringPool.BLANK);
			params.put(DictItemTerm.PARENT_ITEM_CODE, StringPool.BLANK);
			params.put(DictItemTerm.DICT_COLLECTION_CODE, DossierStatisticConstants.GOV_AGENCY_CODE);
			_log.info("params: "+JSONFactoryUtil.looseSerialize(params));

			Sort[] sorts = null;
			
			sorts = new Sort[] {
				SortFactoryUtil.create(DictItemTerm.SIBLING_SEARCH + ReadFilePropertiesUtils.get(ConstantUtils.NUMBER_SORT_PATTERN), Sort.INT_TYPE, false) };
			

			JSONObject jsonData = dictItemDataUtil.getDictItems(-1, company.getCompanyId(), groupId,
					params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
			localResponse.setTotal(jsonData.getInt("total"));
			List<Document> lstDocs = (List<Document>) jsonData.get("data");
			List<GovAgencyData> lstSDatas = new ArrayList<>();
			for (Document doc : lstDocs) {
				GovAgencyData sd = new GovAgencyData();
				sd.setItemCode(doc.get(DictItemTerm.ITEM_CODE));
				sd.setItemName(doc.get(DictItemTerm.ITEM_NAME));				
				
				lstSDatas.add(sd);
			}
			localResponse.setData(lstSDatas);
		}
		catch (Exception e) {
			_log.error(e);
		}
		return localResponse;
	}	


	public static GovAgencyResponse getLocalGovAgency(List<OpencpsDossierStatistic> lstCurrents) {
		GovAgencyResponse agencyResponse = new GovAgencyResponse();

		GovAgencyData agencyData = null;
		Map<String, GovAgencyData> map = new HashMap<>();
		if (lstCurrents != null && lstCurrents.size() > 0) {
			for (OpencpsDossierStatistic statistic : lstCurrents) {
				if (Validator.isNotNull(statistic.getGovAgencyCode())
						&& Validator.isNotNull(statistic.getGovAgencyName())) {
					agencyData = new GovAgencyData();
					agencyData.setItemCode(statistic.getGovAgencyCode());
					agencyData.setItemName(statistic.getGovAgencyName());

					//
					map.put(statistic.getGovAgencyCode(), agencyData);
				}
			}
		}
		//
		if (map != null && !map.isEmpty()) {
			agencyResponse.setTotal(map.size());
			for (Map.Entry<String, GovAgencyData> entry : map.entrySet()) {
				agencyResponse.getData().add(entry.getValue());
			}
		}

		return agencyResponse;
	}

	private static Log _log = LogFactoryUtil.getLog(StatisticDataUtil.class);
}
