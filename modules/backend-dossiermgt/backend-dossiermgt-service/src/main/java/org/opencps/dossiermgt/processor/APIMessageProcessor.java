package org.opencps.dossiermgt.processor;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

public class APIMessageProcessor extends BaseMessageProcessor {
	private OpenCPSRestClient client;
	public APIMessageProcessor(DossierSync ds) {
		super(ds);
		
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(ds.getServerNo());
		try {
			client = OpenCPSRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void processRequest() {
		if (dossierSync.getActionCode().equals(ActionConfigTerm.ACTION_CODE_1300)) {
			if (syncDossierOnline()) {
				try {
					DossierSyncLocalServiceUtil.deleteDossierSync(dossierSync.getDossierSyncId());
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	private boolean syncDossierOnline() {
		Dossier dossier = DossierLocalServiceUtil.getByRef(dossierSync.getGroupId(), dossierSync.getDossierRefUid());
		if (dossier == null) {
			return false;
		}
		DossierInputModel model = OpenCPSConverter.convertDossierToInputModel(dossier);
		model.setOriginality(DossierTerm.ORIGINALITY_LIENTHONG);
		model.setOnline("true");
		DossierDetailModel result = client.postDossier(model);
		if (result == null) {
			return false;
		}
		return true;
	}
}
