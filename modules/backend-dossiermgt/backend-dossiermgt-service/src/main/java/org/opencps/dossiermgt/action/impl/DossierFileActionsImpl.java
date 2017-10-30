package org.opencps.dossiermgt.action.impl;

import java.io.InputStream;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;

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
import com.liferay.portal.kernel.util.StringPool;

public class DossierFileActionsImpl implements DossierFileActions {
	
	private static final Log _log = LogFactoryUtil.getLog(DossierFileActionsImpl.class);

	@Override
	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, ServiceContext serviceContext) 
		throws SystemException, PortalException {
		
		return DossierFileLocalServiceUtil.addDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo,
				dossierPartNo, fileTemplateNo, displayName, sourceFileName, fileSize, inputStream, serviceContext);
	}

	@Override
	public DossierFile cloneDossierFile(long groupId, long dossierId, long dossierFileId, String dossierTemplateNo,
			String dossierPartNo, ServiceContext serviceContext) 
		throws SystemException, PortalException {
		
		return DossierFileLocalServiceUtil.cloneDossierFile(groupId, dossierId, dossierFileId, dossierTemplateNo,
				dossierPartNo, serviceContext);
	}
	
	@Override
	public DossierFile updateDossierFile(long groupId, long dossierId, String referenceUid, String sourceFileName,
			InputStream inputStream, ServiceContext serviceContext) 
		throws SystemException, PortalException {
		
		return DossierFileLocalServiceUtil.updateDossierFile(groupId, dossierId, referenceUid, StringPool.BLANK,
				sourceFileName, inputStream, serviceContext);
	}

	@Override
	public DossierFile deleteDossierFile(long groupId, long dossierId, String referenceUid,
			ServiceContext serviceContext) throws PortalException {
		
		return DossierFileLocalServiceUtil.deleteDossierFile(dossierId, referenceUid);
	}

	@Override
	public JSONObject getDossierFiles(long groupId, String keyword, String template, Integer type, Boolean owner,
			Boolean original, int start, int end, String sort, String order, ServiceContext serviceContext) {

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
			params.put(Field.KEYWORD_SEARCH, keyword);
			params.put(DossierFileTerm.DOSSIER_TEMPLATE_NO, template);
			params.put(DossierFileTerm.DOSSIER_PART_TYPE, type);
			
			if(owner != null && owner.booleanValue()) {
				params.put(Field.USER_ID, serviceContext.getUserId());
			}
			
			params.put(DossierFileTerm.ORIGINAL, original);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(sort + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(order)) };

			Hits hits = DossierFileLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DossierFileLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
	
	@Override
	public DossierFile updateDossierFileFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException {
		
		return DossierFileLocalServiceUtil.updateFormData(groupId, dossierId, referenceUid, formData, serviceContext);
	}
}
