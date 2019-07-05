package org.opencps.statistic.rest.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.statistic.rest.dto.ServiceDomainData;
import org.opencps.statistic.rest.dto.ServiceDomainRequest;
import org.opencps.statistic.rest.dto.ServiceDomainResponse;

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
			
		}
		return response;
	}
}
