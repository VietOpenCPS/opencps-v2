package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.List;

import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import backend.utils.APIDateTimeUtils;

@Component(immediate = true, service = ModelListener.class)
public class DossierDocumentListener extends BaseModelListener<DossierDocument> {
	@Override
	public void onBeforeCreate(DossierDocument model) throws ModelListenerException {
		_log.info("Before Created........... ==> " + model.getDossierId());
	}

	@Override
	public void onAfterCreate(DossierDocument model) throws ModelListenerException {
		_log.info("DossierDocumentCreate........... ");

	}

	@Override
	public void onAfterRemove(DossierDocument model) throws ModelListenerException {
	}

	@Override
	public void onBeforeUpdate(DossierDocument model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierDocumentLocalServiceUtil.getDossierDocument(model.getPrimaryKey());
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}
	}

	@Override
	public void onAfterUpdate(DossierDocument model) throws ModelListenerException {
		_log.info("Update DossierDocument_________-");
		_log.info("DossierDocument file id: " + model.getDocumentFileId());
		if (model.getDocumentFileId() != 0) {
			try {
				List<DossierLog> lstLogs = DossierLogLocalServiceUtil.getByDossierAndType(model.getDossierId(), "PROCESS_TYPE", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				for (DossierLog log : lstLogs) {
					JSONObject payload = JSONFactoryUtil.createJSONObject(log.getPayload());
					_log.info("Payload dossier document update: " + payload.toJSONString());
					if (payload.has("dossierActionId") && model.getDossierActionId() == payload.getInt("dossierActionId")) {
						JSONArray files = payload.getJSONArray("files");
						JSONObject file = JSONFactoryUtil.createJSONObject();

						file.put("dossierDocumentId", model.getDossierDocumentId());
						file.put("fileName", model.getDocumentName());
						file.put("createDate", APIDateTimeUtils.convertDateToString(model.getCreateDate(),
								APIDateTimeUtils._TIMESTAMP));
						file.put("dossierReferenceUid", model.getReferenceUid());
						files.put(file);
						payload.put("files", files);
						log.setPayload(payload.toJSONString());
						
						DossierLogLocalServiceUtil.updateDossierLog(log);
					}
					
				}
			} catch (PortalException e) {
				_log.debug(e);
			}
			
		}
	}

	public DossierDocument modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierDocumentListener.class.getName());
}
