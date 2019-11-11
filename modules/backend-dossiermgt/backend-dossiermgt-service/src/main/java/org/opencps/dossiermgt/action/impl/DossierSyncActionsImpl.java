package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;

import org.opencps.dossiermgt.action.DossierSyncActions;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;

public class DossierSyncActionsImpl implements DossierSyncActions{

	private static Log _log = LogFactoryUtil.getLog(DossierSyncActionsImpl.class);
	@Override
	public JSONObject getDossierSyncByDossierAndInfo(long groupId, String id, Integer info, Integer start,
			Integer end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			long dossierId = GetterUtil.getLong(id);
			
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			List<DossierSync> docList = DossierSyncLocalServiceUtil.findByDossierAndInfoType(groupId, dossier.getReferenceUid(), info, start, end);
			if (docList != null && docList.size() > 0) {
				_log.info("docList:"+docList);
			}
			result.put(ConstantUtils.DATA, docList);
			
			long total = DossierSyncLocalServiceUtil.countByDossierAndInfoType(groupId, dossier.getReferenceUid(), info);
			result.put(ConstantUtils.TOTAL, total);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
		
	}
	@Override
	public JSONObject getDossierSyncByAction(long groupId, String actionCode, Integer start, Integer end,
			ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			List<DossierSync> docList = DossierSyncLocalServiceUtil.findForApplicantAndActionCode(groupId, actionCode, start, end);
			if (docList != null && docList.size() > 0) {
				_log.info("docList:"+docList);
			}
			result.put(ConstantUtils.DATA, docList);
			
			long total = DossierSyncLocalServiceUtil.countForApplicantAndActionCode(groupId, actionCode);
			result.put(ConstantUtils.TOTAL, total);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}
	@Override
	public JSONObject getDossierSyncByDossiers(long groupId, String id, Integer start, Integer end,
			ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			long dossierId = GetterUtil.getLong(id);
			
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			List<DossierSync> docList = DossierSyncLocalServiceUtil.findByDossierAndInfoTypeArr(groupId, dossier.getReferenceUid(), new int[] { ActionConfigTerm.INFO_TYPE_INFO, ActionConfigTerm.INFO_TYPE_NOTIFY }, start, end);
			if (docList != null && docList.size() > 0) {
				_log.info("docList:"+docList);
			}
			result.put(ConstantUtils.DATA, docList);
			
			long total = DossierSyncLocalServiceUtil.countByDossierAndInfoTypeArr(groupId, dossier.getReferenceUid(), new int[] { ActionConfigTerm.INFO_TYPE_INFO, ActionConfigTerm.INFO_TYPE_NOTIFY });
			result.put(ConstantUtils.TOTAL, total);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}

}
