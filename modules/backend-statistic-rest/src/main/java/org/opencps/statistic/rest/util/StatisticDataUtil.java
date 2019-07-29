package org.opencps.statistic.rest.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.constants.DictItemTerm;
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
import org.opencps.statistic.rest.engine.service.StatisticSumYearCalcular;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.feedback.action.VotingActions;
import backend.feedback.action.impl.VotingActionsImpl;
import backend.feedback.constants.VotingResultTerm;
import backend.feedback.constants.VotingTerm;

public class StatisticDataUtil {
	public static ServiceDomainResponse getLocalServiceDomain(ServiceDomainRequest payload) {
		ServiceDomainResponse response = new ServiceDomainResponse();

		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			
			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(company.getCompanyId());
			
			long groupId = GetterUtil.getLong(payload.getGroupId());
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if ("ADMINISTRATIVE_REGION".equalsIgnoreCase(DossierStatisticConstants.SERVICE_DOMAIN_CODE))
				groupId = 0;

			params.put("groupId", groupId);
			params.put("keywords", StringPool.BLANK);
			params.put("itemLv", StringPool.BLANK);
			params.put(DictItemTerm.PARENT_ITEM_CODE, StringPool.BLANK);
			params.put(DictItemTerm.DICT_COLLECTION_CODE, DossierStatisticConstants.SERVICE_DOMAIN_CODE);

			Sort[] sorts = null;
			
			sorts = new Sort[] {
				SortFactoryUtil.create(DictItemTerm.SIBLING_SEARCH + "_Number_sortable", Sort.INT_TYPE, false) };
			

			JSONObject jsonData = dictItemDataUtil.getDictItems(-1, company.getCompanyId(), groupId,
					params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());			
			response.setTotal(jsonData.getInt("total"));
			List<Document> lstDocs = (List<Document>) jsonData.get("data");
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
		params.put("groupId", String.valueOf(groupId));
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
	
			if (payload.isCalculate()) {
				if (payload.getMonth() != null) {
					params.put("month", payload.getMonth());
				}
				else {
					params.put("month", Integer.toString(LocalDate.now().getMonthValue()));
				}
				if (payload.getYear() != null) {
					params.put("year", payload.getYear());
				}
				else {
					params.put("year", Integer.toString(LocalDate.now().getYear()));
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
	
			params.put(VotingTerm.CLASS_NAME, "employee");
						
			Sort[] sorts = new Sort[] { SortFactoryUtil.create("treeIndex_sortable", Sort.STRING_TYPE, false) };
	
			JSONObject jsonData = actions.getVotingResultStatistic(-1, company.getCompanyId(), groupId, params,
					sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
	
			response.setTotal(jsonData.getInt("total"));
			List<Document> lstDocs = (List<Document>) jsonData.get("data");
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
		params.put("groupId", String.valueOf(groupId));
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
	
			if (payload.isCalculate()) {
				if (payload.getMonth() != null) {
					params.put("month", payload.getMonth());
				}
				else {
					params.put("month", Integer.toString(LocalDate.now().getMonthValue()));
				}
				if (payload.getYear() != null) {
					params.put("year", payload.getYear());
				}
				else {
					params.put("year", Integer.toString(LocalDate.now().getYear()));
				}
			} else {
				if (Validator.isNotNull(payload.getFromVotingDate())) {
					params.put(VotingResultTerm.FROM_VOTING_DATE, payload.getFromVotingDate());
				}
				if (Validator.isNotNull(payload.getToVotingDate())) {
					params.put(VotingResultTerm.TO_VOTING_DATE, payload.getFromVotingDate());
				}
			}
		
			params.put(VotingTerm.CLASS_NAME, "dossier");
						
			Sort[] sorts = new Sort[] { SortFactoryUtil.create("treeIndex_sortable", Sort.STRING_TYPE, false) };
	
			JSONObject jsonData = actions.getVotingResultStatistic(-1, company.getCompanyId(), groupId, params,
					sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
	
			response.setTotal(jsonData.getInt("total"));
			List<Document> lstDocs = (List<Document>) jsonData.get("data");
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
	
	public static GovAgencyResponse getLocalGovAgency(GovAgencyRequest payload) {
		GovAgencyResponse response = new GovAgencyResponse();

		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			
			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(company.getCompanyId());
			
			long groupId = GetterUtil.getLong(payload.getGroupId());
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", groupId);
			params.put("keywords", StringPool.BLANK);
			params.put("itemLv", StringPool.BLANK);
			params.put(DictItemTerm.PARENT_ITEM_CODE, StringPool.BLANK);
			params.put(DictItemTerm.DICT_COLLECTION_CODE, DossierStatisticConstants.GOV_AGENCY_CODE);

			Sort[] sorts = null;
			
			sorts = new Sort[] {
				SortFactoryUtil.create(DictItemTerm.SIBLING_SEARCH + "_Number_sortable", Sort.INT_TYPE, false) };
			

			JSONObject jsonData = dictItemDataUtil.getDictItems(-1, company.getCompanyId(), groupId,
					params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());			
			response.setTotal(jsonData.getInt("total"));
			List<Document> lstDocs = (List<Document>) jsonData.get("data");
			List<GovAgencyData> lstSDatas = new ArrayList<>();
			for (Document doc : lstDocs) {
				GovAgencyData sd = new GovAgencyData();
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
	
	private static Log _log = LogFactoryUtil.getLog(StatisticDataUtil.class);
}
