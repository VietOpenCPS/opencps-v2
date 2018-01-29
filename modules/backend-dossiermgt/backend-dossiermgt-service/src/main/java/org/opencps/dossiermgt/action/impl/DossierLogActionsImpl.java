package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.action.DossierLogActions;
import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class DossierLogActionsImpl implements DossierLogActions {
	
	private static final Log _log = LogFactoryUtil.getLog(DossierLogActionsImpl.class);
	
	@Override
	public DossierLog addDossierLog(long groupId, long dossierId, String author,
            String content, String notificationType, String payload, ServiceContext serviceContext) 
        throws PortalException, SystemException {
		
		return DossierLogLocalServiceUtil.addDossierLog(groupId, dossierId,  author,
				content, notificationType, payload, serviceContext);
	}
	
	@Override
	public JSONObject getDossierLogs(long groupId, String notificationType, Boolean owner,
		 int start, int end, String sort, String order, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(serviceContext.getCompanyId());
		//System.out.println("/////////////////////////////////////// notificationType " + notificationType);
		try {
			
			if (start == 0) {
				start = -1;
				end = -1;
			} 
			
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierLogTerm.NOTIFICATION_TYPE, notificationType);
			
			if(owner != null && owner.booleanValue()) {
				params.put(Field.USER_ID, serviceContext.getUserId());
			}
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(sort + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(order)) };

			Hits hits = DossierLogLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DossierLogLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
	
	
	@Override
	public JSONObject getDossierLogById(long groupId, long id, String password, Boolean owner,
		 int start, int end, String sort, String order, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(serviceContext.getCompanyId());

		try {
			
			if (start == 0) {
				start = -1;
				end = -1;
			} 
			
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierLogTerm.DOSSIER_ID, id);
			
			if(owner != null && owner.booleanValue()) {
				params.put(Field.USER_ID, serviceContext.getUserId());
			}
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create("createDate_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(order)) };

			Hits hits = DossierLogLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DossierLogLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
	
	@Override
	public JSONObject getDossiers(long groupId, long dossierId,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(serviceContext.getCompanyId());
		
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));
		params.put(DossierTerm.DOSSIER_ID, dossierId);

		try {

			hits = DossierLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DossierLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

	}
}
